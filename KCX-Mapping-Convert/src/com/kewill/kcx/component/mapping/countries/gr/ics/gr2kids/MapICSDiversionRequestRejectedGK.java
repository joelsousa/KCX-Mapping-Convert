package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC324A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestRejectedKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 28.06.2011<br>
 * Description	: Mapping of ICSDiversionRequestRejected for Greece to KIDS.
 * 
 * @author	Jude Eco
 * @version	1.0.00
 *
 */
public class MapICSDiversionRequestRejectedGK extends KidsMessageCY {
	
	private BodyDiversionRequestRejectedKids 	body		= null;
	private MsgCC324A							msgGreece	= null;
	
	public MapICSDiversionRequestRejectedGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
		msgGreece		= new MsgCC324A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		
		try {
			body	= new BodyDiversionRequestRejectedKids(writer);
			
			writeStartDocument(encoding, "1.0");			
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
//			msgGreece.parse(HeaderType.GREECE);
			msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgDiversionRequestRejected kidsMsg	= getKidsFromGreece();
			
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
	
	private MsgDiversionRequestRejected getKidsFromGreece() {
		MsgDiversionRequestRejected msgKids = new MsgDiversionRequestRejected();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			msgKids.setDeclarationRejectionDateAndTime(msgGreece.getHeahea().getDeclarationRejectionDateAndTime());
			msgKids.setDeclarationRejectionReason(msgGreece.getHeahea().getDeclarationRejectionReason());
			msgKids.setDeclarationRejectionReasonLNG(msgGreece.getHeahea().getDeclarationRejectionReasonLng());
		}
		
		if (msgGreece.getFunErrer1List() != null) {
			List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgGreece.getFunErrer1List()) {
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
		return msgKids;
	}
}
