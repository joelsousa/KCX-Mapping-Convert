package com.kewill.kcx.component.mapping.countries.de.ics.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapArrivalNotificationKK<br>
 * Date Started	: July 27, 2010<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of ArrivalNotification message.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class MapArrivalNotificationKK extends KidsMessage   {

	private BodyArrivalNotificationKids		body;
	private MsgArrivalNotification			message;
	
	public MapArrivalNotificationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgArrivalNotification(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyArrivalNotificationKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            kidsHeader.setWriter((writer));
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(message);

            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("ICS ArrivalNotification KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}  
            
}
