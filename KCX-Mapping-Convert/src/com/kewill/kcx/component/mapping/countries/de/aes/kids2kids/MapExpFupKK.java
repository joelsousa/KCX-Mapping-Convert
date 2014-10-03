/*
 * Function    : MapExpFupKK.java
 * Titel       :
 * Date        : 24.04.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of KIDS-Format Investigation (Wiedervorlage zur Ausfuhr) 
 * 				 in KIDS-Format (für Encoding)
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
package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;


import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNer;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyNonExitedExportRequestUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpFupKK<br>
 * Erstellt     : 24.04.2009<br>
 * Beschreibung : Mapping of KIDS-Format Investigation (Wiedervorlage zur Ausfuhr) 
 * 				 in KIDS-Format (für Encoding).
 * 
 * @author Kron
 * @version 1.0.00
 */
public class MapExpFupKK extends KidsMessage {
	private BodyNonExitedExportRequestUids	body   = null;
	private MsgExpNer 						msgExpNer;
	
	public MapExpFupKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpNer = new MsgExpNer(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
		 StringWriter xmlOutputString = new StringWriter();
		  XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyNonExitedExportRequestUids(writer);
            kidsHeader.setWriter((writer));   
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
               
            kidsHeader.writeHeader();
	            
            msgExpNer.parse(HeaderType.KIDS);
            
            body.setKidsHeader(kidsHeader);
            body.setMsgExpNer(msgExpNer);
            body.writeBody();
	            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
	            
            writer.flush();
            writer.close();
            Utils.log("(DeclarationResponse getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
}



