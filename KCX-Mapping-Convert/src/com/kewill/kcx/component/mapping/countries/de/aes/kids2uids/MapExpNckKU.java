/*
 * Function    : MapExpNckKU.java
 * Title       :
 * Date        : 08.09.2008
 * Author      : Kewill CSF / Heise
 * Description : Mapping of KIDS-Format into UIDS-Format of InternalStatusInformation
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

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
 * Modul        : MapExpNckKU<br>
 * Erstellt     : 08.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of InternalStatusInformation message.
 * 
 * @author Heise
 * @version 1.0.00
 */
public class MapExpNckKU extends UidsMessage {
	
	private MsgExpNck msgExpNck;
	
	public MapExpNckKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpNck = new MsgExpNck(parser);
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
            msgExpNck.parse(HeaderType.KIDS);
            uidsHeader.setAct(msgExpNck.getNewStatus());
            uidsHeader.setAdditionalInformation(msgExpNck.getNewStatusText());

//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());

            // body is empty
            writeElement("soap:Body", null);
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgInternalStatus getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
