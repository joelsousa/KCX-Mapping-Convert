package com.kewill.kcx.component.mapping.countries.de.emcs.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgDiversionNotification;
import com.kewill.kcx.component.mapping.formats.uids.emcs.BodyDiversionNotificationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Modul        : EMCS<br>
* Erstellt     : 17.05.2010<br>
* Description : Mapping of KIDS-Format into UIDS-Format of MsgDiversionNotification message.
* 
* @author Iwaniuk
* @version 1.0.00
*/

public class MapDiversionNotificationKU extends UidsMessage {
	
	private BodyDiversionNotificationUids 	body;
	private MsgDiversionNotification 		message;	
	
	public MapDiversionNotificationKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgDiversionNotification(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body   = new BodyDiversionNotificationUids(writer);            
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");           
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                        
            mapKidsToUidsHeader();   
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setUidsHeader(uidsHeader);
            body.setMessage(message);
            body.writeBody();            
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("EMCS DiversionNotification UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
