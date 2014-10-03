package com.kewill.kcx.component.mapping.countries.de.suma70.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentationConfirmed;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyNotificationOfPresentationConfirmedKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module		: Manifest<br>
 * Created		: 04.06.2013<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of NotificationOfPresentationConfirmed message. 
 * 
 * @author krzoska
 * @version 2.0.00
 */

public class MapNotificationOfPresentationConfirmedUK extends KidsMessage {

	private BodyNotificationOfPresentationConfirmedKids				body;
	private MsgNotificationOfPresentationConfirmed					message;
		
	public MapNotificationOfPresentationConfirmedUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
		throws XMLStreamException {
		
		message = new MsgNotificationOfPresentationConfirmed(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
		
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyNotificationOfPresentationConfirmedKids(writer);
            
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
