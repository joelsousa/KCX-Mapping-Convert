/*
 * Function    : MapExpFupKU.java
 * Titel       :
 * Date        : 24.04.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of KIDS-Format of Investigation into 
 * 				 UIDS-Format of NonExitedExportRequest (AUP)
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNer;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyNonExitedExportRequestUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpFupKU<br>
 * Erstellt     : 24.04.2009<br>
 * Beschreibung : Mapping of KIDS-Format of Investigation into 
 * 				 UIDS-Format of NonExitedExportRequest (AUP) .
 * 
 * @author kron
 * @version 1.0.00
 */

public class MapExpFupKU extends UidsMessage {
	
	private BodyNonExitedExportRequestUids 	body   = null;
	private MsgExpNer 						msgExpNer;
	
	public MapExpFupKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpNer = new MsgExpNer(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyNonExitedExportRequestUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpNer.parse(HeaderType.KIDS);
            body.setMsgExpNer(msgExpNer);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpNer.getDocumentReferenceNumber());
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

