package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCD917B;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.XmlErr805;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.MsgLocalAppResultGR;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.common.Error;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyLocalAppResultKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS Greece<br>
 * Created		: 08.07.2011<br>
 * Description	: LocalAppResultGK (new message with KCX-Header, for GR only).
 * 
 * @author	iwaniuk
 * @version	1.0.00
 */

public class MapLocalAppResultGK extends KidsMessageCY {

	private BodyLocalAppResultKids body;
	private MsgLocalAppResultGR msgGreece = null;
	private GreeceHeader greeceHeader = null;

	
	public MapLocalAppResultGK(XMLEventReader parser, KidsHeader kidsHeader, String encoding,
													  GreeceHeader greeceHeader) throws XMLStreamException {
		msgGreece = new MsgLocalAppResultGR(parser);
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;		
		this.greeceHeader = greeceHeader;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body = new BodyLocalAppResultKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgGreece.parse(HeaderType.CYPRUS);   
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgFailure kidMsg = getKidsFromGreece();
			
			if (greeceHeader != null) {
	           getCommonFieldsDTO().setReferenceNumber(greeceHeader.getReferenceNumber());	 				
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
        //MS20110816: this.kidsHeader.setInReplyTo(greeceHeader.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgGreece.getCorIdeMES25()); 
	    setInReplyTo(greeceHeader.getCorIdeMES25(), "GR");       
		setMapping();
	}
	
	private MsgFailure getKidsFromGreece() {   
		MsgFailure msg = new MsgFailure();		
		String status = "";
		String reason = "";
		String explanation = "";
		
		if (greeceHeader != null) {
	           msg.setReferenceNumber(greeceHeader.getReferenceNumber());	         
	        }
				
		msg.setCorrectionNumber("0"); 							 
		msg.setProcedureType(this.kidsHeader.getProcedure());   
		
		if (msgGreece.getProcessingErrors() != null && 
				msgGreece.getProcessingErrors().getProcessingErrorList() != null) {
			List<Error> listGR = msgGreece.getProcessingErrors().getProcessingErrorList();
			List<PositionError> errorList = new ArrayList<PositionError>();
			if (msgGreece.getResultState() != null) {
				status = msgGreece.getResultState().getStatus();
				reason = msgGreece.getResultState().getReasonCode();
				explanation = msgGreece.getResultState().getExplanation();
				if (Utils.isStringEmpty(reason)) {
					reason = explanation;
				}
				if (!Utils.isStringEmpty(reason)) {
					reason = reason + ": ";
				}
			}
			for (Error errorGR : listGR) {
				if (errorGR != null) {
					PositionError error = new PositionError();					
					error.setKindOfError(status);					
					error.setErrorCode(errorGR.getErrorCode());					
					error.setErrorText(reason + errorGR.getErrorDescription());
					//error.setModul();
					//error.setOriginOfError();					
					//error.setDateOfErrorMessage(date);  
					//error.setTimeOfErrorMessage(time); 
					
					errorList.add(error);
				}
			}
			msg.setErrorList(errorList);
		}
		 //EI20131209:
        if (msgGreece.getResultState() != null && 
        	(msgGreece.getProcessingErrors() == null || (msgGreece.getProcessingErrors() != null && msgGreece.getProcessingErrors().isEmpty()))) {        	
        	status = msgGreece.getResultState().getStatus();
        	reason = msgGreece.getResultState().getReasonCode();
        	explanation = msgGreece.getResultState().getExplanation();
        	
        	if (!Utils.isStringEmpty(status) && status.contains("OtherError")) {        	
        		List<PositionError> errorList = new ArrayList<PositionError>();
        		PositionError error = new PositionError();	
        		error.setKindOfError(status);					
				error.setErrorCode(reason);					
				error.setErrorText(explanation);				
				errorList.add(error);
				msg.setErrorList(errorList);
        	}
        	
        }
        
		return msg;
	}

}
