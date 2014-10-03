package com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgDiversionNotification;
import com.kewill.kcx.component.mapping.formats.kids.emcs20.BodyDiversionNotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: EMCS<br>
 * Created		: 15.10.2012<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of EMCSDiversionNotification message.
 * 
 * @author  iwaniuk
 * @version 2.0.00
 */

public class MapDiversionNotificationUK extends KidsMessage {
	
    private BodyDiversionNotificationKids body;
    private MsgDiversionNotification 	  message;

	public MapDiversionNotificationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																			throws XMLStreamException {
		message = new MsgDiversionNotification(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyDiversionNotificationKids(writer);
            
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
