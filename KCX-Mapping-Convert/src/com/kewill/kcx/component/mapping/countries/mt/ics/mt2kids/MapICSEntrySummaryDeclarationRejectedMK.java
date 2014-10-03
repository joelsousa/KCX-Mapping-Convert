package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

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
 * Module		: MapICSEntrySummaryDeclarationRejectedMK<br>
 * Created		: 20.08.2013<br>
 * Description	: EntrySummaryDeclarationRejectedMK (CC316A).
 * 
 * @author	krzoska
 * @version	1.0.00
 */

public class MapICSEntrySummaryDeclarationRejectedMK extends KidsMessageMT {

	private BodyEntrySummaryDeclarationRejectedKids body;
	private MsgCC316A		msgMalta = null;
	
	public MapICSEntrySummaryDeclarationRejectedMK(XMLEventReader parser, KidsHeader kidsHeader, String encoding
												   ) throws XMLStreamException {
		msgMalta = new MsgCC316A(parser);
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
			
			msgMalta.parse(HeaderType.CYPRUS);  //EI20110706   
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationRejected kidMsg = getKidsFromMalta();
			
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
	
	private MsgEntrySummaryDeclarationRejected getKidsFromMalta() {
		MsgEntrySummaryDeclarationRejected msgKids = new MsgEntrySummaryDeclarationRejected();
		
		if (msgMalta.getHeahea() != null) {
			String refnrOrg = "";
			refnrOrg = Db.getOriginalRefnr(msgMalta.getHeahea().getReferenceNumber(), "MT");
			if (!Utils.isStringEmpty(refnrOrg)) {				
				msgKids.setReferenceNumber(refnrOrg);
			} else {
				msgKids.setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			}

			msgKids.setDeclarationRejectionReason(msgMalta.getHeahea().getDeclarationRejectionReason());
			msgKids.setDeclarationRejectionReasonLNG(msgMalta.getHeahea().getDeclarationRejectionReasonLNG());			
			msgKids.setDeclarationRejectionDateAndTime(msgMalta.getHeahea().getDeclarationRejectionDateAndTime());
		}
		
		if (msgMalta.getFunErrerList() != null) {
			List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgMalta.getFunErrerList()) {
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
