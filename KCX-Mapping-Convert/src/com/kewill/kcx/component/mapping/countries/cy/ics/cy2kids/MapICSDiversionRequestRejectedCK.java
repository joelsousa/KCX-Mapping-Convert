package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC324A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestRejectedKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSDiversionRequestRejected (CC324A).
 * Created		: 24.06.2011
 * Description	: .
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */
public class MapICSDiversionRequestRejectedCK extends KidsMessageCY {
	private BodyDiversionRequestRejectedKids	body		= null;
	private MsgCC324A							msgCyprus	= null;

	public MapICSDiversionRequestRejectedCK(XMLEventReader parser, KidsHeader kidsHeader, 
			String encoding, CyprusHeader cyprusHeader) throws XMLStreamException {
		msgCyprus		= new MsgCC324A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer	= writer;
		
		try {
			body	= new BodyDiversionRequestRejectedKids(writer);
			
			writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
					setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
					
					msgCyprus.parse(HeaderType.CYPRUS);
					
					mapKidsHeaderFromMessage();
					kidsHeader.writeHeader();
					
					MsgDiversionRequestRejected kidsMsg	= getKidsFromCyprus();
					
					if (msgCyprus.getHeahea() != null) {
						getCommonFieldsDTO().setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
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
		//MS20110816: this.kidsHeader.setInReplyTo(msgCyprus.getCorIdeMES25());
		//EI20140408;setInReplyTo(msgCyprus.getCorIdeMES25());
        setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");     //EI20140408        
		setMapping();
	}
	
	private MsgDiversionRequestRejected getKidsFromCyprus() {
		MsgDiversionRequestRejected	msgKids	= new MsgDiversionRequestRejected();
		
		if (msgCyprus.getHeahea() != null) {
			msgKids.setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			msgKids.setDeclarationRejectionDateAndTime(msgCyprus.getHeahea().getDeclarationRejectionDateAndTime());
			msgKids.setDeclarationRejectionReason(msgCyprus.getHeahea().getDeclarationRejectionReason());
			msgKids.setDeclarationRejectionReasonLNG(msgCyprus.getHeahea().getDeclarationRejectionReasonLng());
		}
		
		if (msgCyprus.getFunErrer1List() != null) {
			List<FunctionalError> funErrerList	= new ArrayList<FunctionalError>();
			
			for (Funerrer1 funErrerItem : msgCyprus.getFunErrer1List()) {
				if (funErrerItem != null) {
					FunctionalError funErrer = new FunctionalError();
					funErrer.setErrorType(funErrerItem.getErrorType());
					funErrer.setErrorPointer(funErrerItem.getErrorPointer());
					//funErrer.setErrorReason(funErrerItem.getErrorReason());
					funErrer.setErrorReason(funErrerItem.getText());   //EI20110714
					funErrer.setOriginalAttributeValue(funErrerItem.getOriginalAttributeValue());
						
					funErrerList.add(funErrer);
				}
			}
			
			msgKids.setFunctionalErrorList(funErrerList);
		}
		
		return msgKids;
	}
}
