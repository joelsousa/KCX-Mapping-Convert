package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC316A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationRejectedKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 23.06.2010<br>
 * Description	: ICSEntrySummaryDeclarationRejectedGK.
 * 
 * @author	Lassiter.
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationRejectedGK extends KidsMessageCY {
	
	private BodyEntrySummaryDeclarationRejectedKids body;
	private MsgCC316A 		msgGreece = null;
	
	public MapICSEntrySummaryDeclarationRejectedGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
			msgGreece = new MsgCC316A(parser);
			this.encoding = encoding;
			this.kidsHeader = kidsHeader;
			
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body =  new BodyEntrySummaryDeclarationRejectedKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			//msgGreece.parse(HeaderType.GREECE);  ist the same as CYPRUS 
			msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationRejected kidsMsg = getKidsFromGreece();
			
			if (msgGreece.getHeahea() != null) {
	            getCommonFieldsDTO().setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());	            	
	        }
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidsMsg);
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
        //MS20110816: this.kidsHeader.setInReplyTo(msgGreece.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgGreece.getCorIdeMES25()); 
        setInReplyTo(msgGreece.getCorIdeMES25(), "GR");       
		setMapping();
	}
	
	private MsgEntrySummaryDeclarationRejected getKidsFromGreece() {
		MsgEntrySummaryDeclarationRejected msgKids =  new MsgEntrySummaryDeclarationRejected();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			msgKids.setDeclarationRejectionDateAndTime(msgGreece.getHeahea().getDeclarationRejectionDateAndTime());
			msgKids.setDeclarationRejectionReason(msgGreece.getHeahea().getDeclarationRejectionReason());
			msgKids.setDeclarationRejectionReasonLNG(msgGreece.getHeahea().getDeclarationRejectionReasonLNG());	
		}
		
		if (msgGreece.getFunErrerList() != null) {
			List<FunctionalError> funcErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funErrer : msgGreece.getFunErrerList()) {
				if (funErrer != null) {
					FunctionalError functionalError =  new FunctionalError();
					
					functionalError.setErrorType(funErrer.getErrorType());
					functionalError.setErrorPointer(funErrer.getErrorPointer());
					//funcError.setErrorReason(funErrer.getErrorReason());
					functionalError.setErrorReason(funErrer.getText()); 
					functionalError.setOriginalAttributeValue(funErrer.getOriginalAttributeValue());
					
					funcErrorList.add(functionalError);
				}
			}
			msgKids.setFunctionalErrorList(funcErrorList);
		}
		return msgKids;
	}
}
