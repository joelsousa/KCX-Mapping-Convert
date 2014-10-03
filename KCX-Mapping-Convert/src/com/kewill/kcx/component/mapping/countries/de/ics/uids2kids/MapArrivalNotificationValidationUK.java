package com.kewill.kcx.component.mapping.countries.de.ics.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotificationValidation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationValidationKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module 		: MapArrivalNotificationValidationUK<br>
 * Date Started	: July 19, 2010<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of DeclarationAmendment message.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class MapArrivalNotificationValidationUK extends KidsMessage  {

	private BodyArrivalNotificationValidationKids		body;
	private MsgArrivalNotificationValidation			message;
	
	public MapArrivalNotificationValidationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding)
		throws XMLStreamException {
		message = new MsgArrivalNotificationValidation(parser);
		
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyArrivalNotificationValidationKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(message);            
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}  
}
