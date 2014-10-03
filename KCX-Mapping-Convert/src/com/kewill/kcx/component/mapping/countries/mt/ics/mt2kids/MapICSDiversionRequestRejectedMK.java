package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

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
 * Module		: MapICSDiversionRequestRejectedMK (CC324A).
 * Created		: 20.08.2013
 * Description	: .
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */
public class MapICSDiversionRequestRejectedMK extends KidsMessageMT {
	private BodyDiversionRequestRejectedKids	body		= null;
	private MsgCC324A							msgMalta	= null;

	public MapICSDiversionRequestRejectedMK(XMLEventReader parser, KidsHeader kidsHeader, 
			String encoding) throws XMLStreamException {
		msgMalta		= new MsgCC324A(parser);
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
					
					msgMalta.parse(HeaderType.CYPRUS);
					
					mapKidsHeaderFromMessage();
					kidsHeader.writeHeader();
					
					MsgDiversionRequestRejected kidsMsg	= getKidsFromCyprus();
					
					if (msgMalta.getHeahea() != null) {
						getCommonFieldsDTO().setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
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
        setInReplyTo(msgMalta.getCorIdeMES25());
		setMapping();
	}
	
	private MsgDiversionRequestRejected getKidsFromCyprus() {
		MsgDiversionRequestRejected	msgKids	= new MsgDiversionRequestRejected();
		
		if (msgMalta.getHeahea() != null) {
			msgKids.setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			msgKids.setDeclarationRejectionDateAndTime(msgMalta.getHeahea().getDeclarationRejectionDateAndTime());
			msgKids.setDeclarationRejectionReason(msgMalta.getHeahea().getDeclarationRejectionReason());
			msgKids.setDeclarationRejectionReasonLNG(msgMalta.getHeahea().getDeclarationRejectionReasonLng());
		}
		
		if (msgMalta.getFunErrer1List() != null) {
			List<FunctionalError> funErrerList	= new ArrayList<FunctionalError>();
			
			for (Funerrer1 funErrerItem : msgMalta.getFunErrer1List()) {
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
