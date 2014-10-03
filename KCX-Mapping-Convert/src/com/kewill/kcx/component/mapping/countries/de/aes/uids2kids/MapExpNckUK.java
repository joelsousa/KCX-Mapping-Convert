/*
 * Function    : MapExpNckUK.java
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
package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNck;
import com.kewill.kcx.component.mapping.formats.kids.base.BodyInternalStatus;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Modul        : MapExpNckUk<br>
 * Erstellt     : 08.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of InternalStatusInformation message.
 * 
 * @author heise
 * @version 1.0.00
 */
public class MapExpNckUK extends KidsMessage {
	private BodyInternalStatus body = null;
	private MsgExpNck msgExpNck;
	
	public MapExpNckUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpNck = new MsgExpNck(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
        
        try {
            body   = new BodyInternalStatus(writer);
            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            // field NewStatus is set from AdditionalInformation in UidsHeader of this Message 
            msgExpNck.setNewStatus(getUidsHeader().getAdditionalInformation().substring(0, 2));
            body.setKidsHeader(kidsHeader);
            body.setMsgExpNck(msgExpNck);
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
