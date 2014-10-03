/*
 * Function    : MapExtNotUK.java
 * Titel       :
 * Date        : 26.09.2008
 * Author      : Kewill CSF / Alfred Krzoska
 * Description : Mapping of UIDS-Format into KIDS-Format of ExitNotification
 *             : 
 * Parameters  : 
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 23.04.2009
 * Label       :
 * Description : msgUids replaced with MsgExtNot
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNot;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExitNotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExtNotUK<br>
 * Erstellt     : 26.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ExitNotification message.
 * 
 * @author Krzoska
 * @version 1.0.00
 */
public class MapExtNotUK extends KidsMessage {
		
	private BodyExitNotificationKids	body   = null;
	private MsgExtNot msgExtNot;
	
	public MapExtNotUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExtNot = new MsgExtNot(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
		
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body   = new BodyExitNotificationKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
           	setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");            

           	kidsHeader.writeHeader();                
            
           	msgExtNot.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExtNot.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
           	body.setMessage(msgExtNot);
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


