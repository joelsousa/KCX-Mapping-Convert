package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC305A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAmendmentRejectionKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 28.06.2011<br>
 * Description	: Mapping of ICSEntrySummaryDeclarationAmendmentRejected for Greece to KIDS.
 * 
 * @author	Jude Eco
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAmendmentRejectedGK extends KidsMessageCY {
	
	private BodyEntrySummaryDeclarationAmendmentRejectionKids	body		= null;
	private MsgCC305A		msgGreece	= null;	
	
	public MapICSEntrySummaryDeclarationAmendmentRejectedGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
		msgGreece		= new MsgCC305A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		
		try {
			body	= new BodyEntrySummaryDeclarationAmendmentRejectionKids(writer);
			
			writeStartDocument(encoding, "1.0");			
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
//			msgGreece.parse(HeaderType.GREECE);
			msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationAmendmentRejection kidsMsg = getKidsFromGreece();
			
			if (msgGreece.getHeahea() != null) {
				getCommonFieldsDTO().setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			}
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidsMsg);
			body.writeBody();
			
			closeElement();
			
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void mapKidsHeaderFromMessage() {        
        //MS20110816: this.kidsHeader.setInReplyTo(msgGreece.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgGreece.getCorIdeMES25()); 
        setInReplyTo(msgGreece.getCorIdeMES25(), "GR");        
		setMapping();
	}
	
	private MsgEntrySummaryDeclarationAmendmentRejection getKidsFromGreece() {
		MsgEntrySummaryDeclarationAmendmentRejection msgKids = 
			new MsgEntrySummaryDeclarationAmendmentRejection();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setMrn(msgGreece.getHeahea().getMrn());
			msgKids.setAmendmentDateAndTime(msgGreece.getHeahea().getAmendmentDateAndTime());
			msgKids.setAmendmentRejectionDateAndTime(msgGreece.getHeahea().getAmendmentRejectionDateAndTime());
		}
		
		if (msgGreece.getFunerrerList() != null) {
			List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgGreece.getFunerrerList()) {
				if (funerrer != null) {
					FunctionalError functionalError = new FunctionalError();
					functionalError.setErrorType(funerrer.getErrorType());
					functionalError.setErrorPointer(funerrer.getErrorPointer());
					//functionalError.setErrorReason(funerrer.getErrorReason());
					functionalError.setErrorReason(funerrer.getText()); 
					functionalError.setOriginalAttributeValue(funerrer.getOriginalAttributeValue());
					
					functionalErrorList.add(functionalError);
				}
			}
			msgKids.setFunctionalErrorList(functionalErrorList);
		}
		
		if (msgGreece.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgGreece.getTrarep(), "RepresentativeAddress"));			
		}
		
		if (msgGreece.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgGreece.getPerlodsumdec(), "PersonLodgingSumaAddress"));
		}
		
		if (msgGreece.getCusofffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgGreece.getCusofffent().getCustomsOfficeOfFirstEntry());
		}
		return msgKids;
	}
}
