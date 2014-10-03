package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC316A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationRejectedKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationRejectedCK<br>
 * Created		: 04.07.2011<br>
 * Description	: EntrySummaryDeclarationRejectedCK (CC316A).
 * 
 * @author	Frederick T.
 * @version	1.0.00
 */

public class MapICSEntrySummaryDeclarationRejectedCK extends KidsMessageCY {

	private BodyEntrySummaryDeclarationRejectedKids body;
	private MsgCC316A		msgCyprus = null;
	
	public MapICSEntrySummaryDeclarationRejectedCK(XMLEventReader parser, KidsHeader kidsHeader, String encoding,
												   CyprusHeader cyprusHeader) throws XMLStreamException {
		msgCyprus = new MsgCC316A(parser);
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;	
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body = new BodyEntrySummaryDeclarationRejectedKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgCyprus.parse(HeaderType.CYPRUS);  //EI20110706   
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationRejected kidMsg = getKidsFromCyprus();
			
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
		//EI20140408: setInReplyTo(msgCyprus.getCorIdeMES25()); 
        setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");    //EI20140408    
		setMapping();
	}
	
	private MsgEntrySummaryDeclarationRejected getKidsFromCyprus() {
		MsgEntrySummaryDeclarationRejected msgKids = new MsgEntrySummaryDeclarationRejected();
		
		if (msgCyprus.getHeahea() != null) {
			//EI20111103: 
			//msgKids.setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			String refnrOrg = "";
			/*
			String kcxId = "";			
			if (this.getCommonFieldsDTO() != null) {
				kcxId = this.getCommonFieldsDTO().getKcxId();			
				refnrOrg = Db.getOriginalRefnr(kcxId, msgCyprus.getHeahea().getReferenceNumber());
			}
			msgKids.setReferenceNumber(refnrOrg);
			*/
			refnrOrg = Db.getOriginalRefnr(msgCyprus.getHeahea().getReferenceNumber(), "CY");
			if (!Utils.isStringEmpty(refnrOrg)) {				
				msgKids.setReferenceNumber(refnrOrg);
			} else {
				msgKids.setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			}
			//EI20111104-end
			msgKids.setDeclarationRejectionReason(msgCyprus.getHeahea().getDeclarationRejectionReason());
			msgKids.setDeclarationRejectionReasonLNG(msgCyprus.getHeahea().getDeclarationRejectionReasonLNG());			
			msgKids.setDeclarationRejectionDateAndTime(msgCyprus.getHeahea().getDeclarationRejectionDateAndTime());
		}
		
		if (msgCyprus.getFunErrerList() != null) {
			List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgCyprus.getFunErrerList()) {
				if (funerrer != null) {
					FunctionalError error = new FunctionalError();					
					error.setErrorType(funerrer.getErrorType());
					error.setErrorPointer(funerrer.getErrorPointer());					
					//EI: error.setErrorReason(funerrer.getErrorReason());
					error.setErrorReason(funerrer.getText());
					error.setOriginalAttributeValue(funerrer.getOriginalAttributeValue());
					
					functionalErrorList.add(error);
				}
			}
			msgKids.setFunctionalErrorList(functionalErrorList);
		}
		return msgKids;
	}
}
