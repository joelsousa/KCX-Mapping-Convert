package com.kewill.kcx.component.mapping.countries.de.emcs.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNck;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module      : EMCS<br>
* Created     : 12.05.2010<br>
* Description : Mapping of KIDS-Format into UIDS-Format of InternalStatusInformation message
*             : (InternalStatusInformation as aes.MsgExpNck) wiht empty body.
* 
* @author Iwaniuk
* @version 1.0.00
*/

public class MapInternalStatusInformationKU extends UidsMessage {
	
	private MsgExpNck message;	
	
	public MapInternalStatusInformationKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExpNck(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();

            // body for InternalStatusUids is empty, NewStatus is written in the header
            // ==> parse KIDS-Message before writing UIDS-header
            message.parse(HeaderType.KIDS);
            uidsHeader.setAct(message.getNewStatus());
            uidsHeader.setAdditionalInformation(message.getNewStatusText());
//            uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            writeElement("soap:Body", null);
            // body is empty
            //writeElements("soap:Body", null);
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("InternalStatusInformation uidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
