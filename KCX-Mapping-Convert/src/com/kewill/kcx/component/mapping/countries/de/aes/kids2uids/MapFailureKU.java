/*
 * Function    : MapFailureKU.java
 * Titel       :
 * Date        : 09.10.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of KIDS-Format into UIDS-Format of Failure
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyFailureUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapFailureKU<br>
 * Erstellt     : 09.10.2008<br>
 * Beschreibung : Mapping of KIDS-Format of ExportRelease into UIDS-Format of Failure message. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapFailureKU extends UidsMessage {
	
	private BodyFailureUids 	body   = null;
	private MsgFailure 			msgFailure;
	
	public MapFailureKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgFailure = new MsgFailure(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyFailureUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
            // CK090506 erst parsen, dann steht fest ob es einen body gibt
            // dies heiﬂt es ist eine failure, keine confirm
            // dann ist der Name anders zu besetzen!
            msgFailure.parse(HeaderType.KIDS);
            
            if (msgFailure.getErrorInBody()) {
            	uidsHeader.setMessageType("Failure");	
            	uidsHeader.setAct("failure"); 
            } else {
            	uidsHeader.setMessageType("Confirm");	
            	uidsHeader.setAct("confirm");
            }
            
//            uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            // msgFailure.parse(HeaderType.KIDS);
			// CK090506 Nur dann einen Body schreiben, wenn es sich um eine
            // failure handelt, confirm hat keinen Body
            if (msgFailure.getErrorInBody()) {
                body.setMsgFailure(msgFailure);
                body.setUidsHeader(uidsHeader);

                getCommonFieldsDTO().setReferenceNumber(msgFailure.getReferenceNumber());
                body.writeBody();
            }
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportConfirmation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
