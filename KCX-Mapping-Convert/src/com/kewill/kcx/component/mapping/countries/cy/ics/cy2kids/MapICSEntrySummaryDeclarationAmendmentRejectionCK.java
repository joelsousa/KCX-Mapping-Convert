package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC305A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAmendmentRejectionKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentRejectionCK<br>
 * Created		: 24.06.2011<br>
 * Description	: ICSEntrySummaryDeclarationAmendmentRejectionCK (CC305A).
 * 
 * @author	Frederick T.
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAmendmentRejectionCK extends KidsMessageCY {
	
	private BodyEntrySummaryDeclarationAmendmentRejectionKids body;
	private MsgCC305A		msgCyprus = null;
	
	public MapICSEntrySummaryDeclarationAmendmentRejectionCK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, CyprusHeader cyprusHeader) throws XMLStreamException {
		
			msgCyprus = new MsgCC305A(parser);
			this.encoding 	= encoding;
			this.kidsHeader = kidsHeader;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		
		this.writer = writer;
		try {
			body =  new BodyEntrySummaryDeclarationAmendmentRejectionKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgCyprus.parse(HeaderType.CYPRUS);
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationAmendmentRejection kidMsg = getKidsFromCyprus();
			
			if (msgCyprus.getHeahea() != null) {
				getCommonFieldsDTO().setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			}
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidMsg);
			body.writeBody();
			

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
        
			writer.flush();
			writer.close();
			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }
		
	}
	
	private void mapKidsHeaderFromMessage() {       
        //MS20110816: this.kidsHeader.setInReplyTo(msgCyprus.getCorIdeMES25());
		//EI20140408;setInReplyTo(msgCyprus.getCorIdeMES25());
        setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");     //EI20140408        
		setMapping();
	}
	
	private MsgEntrySummaryDeclarationAmendmentRejection getKidsFromCyprus() {
		MsgEntrySummaryDeclarationAmendmentRejection msgKids = 
			new MsgEntrySummaryDeclarationAmendmentRejection();
		
		if (msgCyprus.getHeahea() != null) {
			msgKids.setMrn(msgCyprus.getHeahea().getMrn());
			msgKids.setAmendmentDateAndTime(msgCyprus.getHeahea().getAmendmentDateAndTime());
			msgKids.setAmendmentRejectionDateAndTime(msgCyprus.getHeahea().getAmendmentRejectionDateAndTime());
		}
		
		if (msgCyprus.getFunerrerList() != null) {
			List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgCyprus.getFunerrerList()) {
				if (funerrer != null) {
					FunctionalError functionalError = new FunctionalError();
					functionalError.setErrorType(funerrer.getErrorType());
					functionalError.setErrorPointer(funerrer.getErrorPointer());
					//EI:functionalError.setErrorReason(funerrer.getErrorReason());
					functionalError.setErrorReason(funerrer.getText());  //EI20110713
					functionalError.setOriginalAttributeValue(funerrer.getOriginalAttributeValue());
					
					functionalErrorList.add(functionalError);
				}
			}
			msgKids.setFunctionalErrorList(functionalErrorList);
		}
		
		if (msgCyprus.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgCyprus.getTrarep(), "RepresentativeAddress"));
			/* EI20110707
			if (msgKids.getRepresentative() == null) {
				Party party = new Party("RepresentativeAddress");
				msgKids.setRepresentative(party);
			}
			*/
		}
		
		if (msgCyprus.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgCyprus.getPerlodsumdec(), "PersonLodgingSumaAddress"));
			/* EI20110707
			if (msgKids.getPersonLodgingSuma() == null) {
				Party party = new Party("PersonLodgingSumaAddress");
				msgKids.setPersonLodgingSuma(party);
			}
			*/
		}
		
		if (msgCyprus.getCusofffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgCyprus.getCusofffent().getCustomsOfficeOfFirstEntry());
		}
		return msgKids;
	}
}
