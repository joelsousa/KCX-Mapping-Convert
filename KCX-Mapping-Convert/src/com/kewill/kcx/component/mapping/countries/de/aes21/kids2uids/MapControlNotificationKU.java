package com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotification;
import com.kewill.kcx.component.mapping.formats.uids.aes21.BodyExpControlNotificationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: Mapping of ControlNotification from Kids-into UIDS-Format. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapControlNotificationKU extends UidsMessage {
	
	private BodyExpControlNotificationUids body;     
	private MsgControlNotification message;
	
	public MapControlNotificationKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			message = new MsgControlNotification(parser);
			this.encoding = encoding;
	}
		
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
           
            body = new BodyExpControlNotificationUids(writer);    
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();           
            //EI20140318: uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            body.setMessage(message);
            body.setUidsHeader(uidsHeader);
            
            if (kidsHeader.getRelease().equals("2.1.00")) {  //EI20140318 diese message ist neu for V21 
         	   uidsHeader.setMessageVersion("2.2");          // aber eigentlich eben for V22- die nicht "offiziel" eine Version ist
            }                    
            uidsHeader.writeHeader(getCommonFieldsDTO());   //EI20140318

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());              
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapExpFupKU getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}

