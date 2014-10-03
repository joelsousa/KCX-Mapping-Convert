/*
 * Function    : MapExtNotKU.java
 * Titel       :
 * Date        : 26.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Mapping of KIDS-Format ExitNotification into UIDS-Format of ExitNotification
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 23.04.2009
 * Label       :
 * Description : MsgKids replaced with MsgExtNot
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNot;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExitNotificationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapExpNotKU<br>
 * Erstellt     : 26.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format of ExportRelease into UIDS-Format of ExitNotification message. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapExtNotKU extends UidsMessage {
	private BodyExitNotificationUids 	body   = null;
	private MsgExtNot 		msgExtNot;
	
	public MapExtNotKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExtNot = new MsgExtNot(parser);		
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExitNotificationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExtNot.parse(HeaderType.KIDS);
            body.setMessage(msgExtNot);
            body.setUidsHeader(uidsHeader);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExitNotification getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
