package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

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
 * Module		: MapICSEntrySummaryDeclarationAmendmentRejectionMK<br>
 * Created		: 20.08.2013<br>
 * Description	: ICSEntrySummaryDeclarationAmendmentRejectionMK (CC305A).
 * 
 * @author	krzoska
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAmendmentRejectionMK extends KidsMessageMT {
	
	private BodyEntrySummaryDeclarationAmendmentRejectionKids body;
	private MsgCC305A		msgMalta = null;
	
	public MapICSEntrySummaryDeclarationAmendmentRejectionMK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		
			msgMalta = new MsgCC305A(parser);
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
			
			msgMalta.parse(HeaderType.CYPRUS);
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationAmendmentRejection kidMsg = getKidsFromMalta();
			
			if (msgMalta.getHeahea() != null) {
				getCommonFieldsDTO().setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
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
        setInReplyTo(msgMalta.getCorIdeMES25());
		setMapping();
	}
	
	private MsgEntrySummaryDeclarationAmendmentRejection getKidsFromMalta() {
		MsgEntrySummaryDeclarationAmendmentRejection msgKids = 
			new MsgEntrySummaryDeclarationAmendmentRejection();
		
		if (msgMalta.getHeahea() != null) {
			msgKids.setMrn(msgMalta.getHeahea().getMrn());
			msgKids.setAmendmentDateAndTime(msgMalta.getHeahea().getAmendmentDateAndTime());
			msgKids.setAmendmentRejectionDateAndTime(msgMalta.getHeahea().getAmendmentRejectionDateAndTime());
		}
		
		if (msgMalta.getFunerrerList() != null) {
			List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgMalta.getFunerrerList()) {
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
		
		if (msgMalta.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgMalta.getTrarep(), "RepresentativeAddress"));
		}
		
		if (msgMalta.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgMalta.getPerlodsumdec(), "PersonLodgingSumaAddress"));
		}
		
		if (msgMalta.getCusofffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgMalta.getCusofffent().getCustomsOfficeOfFirstEntry());
		}
		return msgKids;
	}
}