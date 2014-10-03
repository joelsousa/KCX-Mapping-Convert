package com.kewill.kcx.component.mapping.countries.de.suma62.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.formats.kids.manifest.BodyNotificationOfPresentationKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Manifest<br>
 * Created		: 12.12.2012<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of NotificationOfPresentation message. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapNotificationOfPresentationUK extends KidsMessageNCTS {
	private BodyNotificationOfPresentationKids		body;
	private MsgNotificationOfPresentation			message;
	
	public MapNotificationOfPresentationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
		throws XMLStreamException {
		
		message = new MsgNotificationOfPresentation(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyNotificationOfPresentationKids(writer);
            
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
