/*
 * Function    : MapFailureUK.java
 * Titel       :
 * Date        : 25.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of UIDS-Format into KIDS-Format of FailureOrConfirm
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyFailureKids;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyLocalAppResultKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapFailureUK<br>
 * Erstellt     : 25.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of FailureOrConfirm message.
 * 
 * @author Kron
 * @version 1.0.00
 */
public class MapFailureUK extends KidsMessage {
	// ck120109 private BodyFailureKids		body   = null;
	private BodyLocalAppResultKids body = null;
	private MsgFailure 	msgConFail;
	
	public MapFailureUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgConFail = new MsgFailure(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            // ck120109 body   = new BodyFailureKids(writer);
            body = new BodyLocalAppResultKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
            kidsHeader.writeHeader();
	            
            msgConFail.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgConFail.getReferenceNumber());

            body.setKidsHeader(kidsHeader);
            // ck120109 body.setMsgConfirmFailure(msgConFail);
            body.setMessage(msgConFail);
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


