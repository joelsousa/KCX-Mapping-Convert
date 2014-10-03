package com.kewill.kcx.component.mapping.countries.de.ics.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapArrivalNotificationUK<br>
 * Erstellt		: 16.05.2010<br>
 * Beschreibung	: Mapping of UIDS-Format into KIDS-Format of ArrivalNotification message.
 * 
 * @author  Pete T
 * @version 1.0.00
 */
public class MapArrivalNotificationUK extends KidsMessage {
	
    private BodyArrivalNotificationKids    body;
    private MsgArrivalNotification 		message;

	public MapArrivalNotificationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
	                                                                                        throws XMLStreamException {
		message = new MsgArrivalNotification(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyArrivalNotificationKids(writer);
            
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
