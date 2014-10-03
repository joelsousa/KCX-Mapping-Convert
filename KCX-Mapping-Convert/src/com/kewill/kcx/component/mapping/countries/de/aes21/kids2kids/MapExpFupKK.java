package com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpFup;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportInvestigationKids;
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
	private BodyExportInvestigationKids	body   = null;
	private MsgExpFup message;    //in den frueheren Versionen MsgExpNer
	
	public MapExpFupKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExpFup(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
		 StringWriter xmlOutputString = new StringWriter();
		  XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportInvestigationKids(writer);
            kidsHeader.setWriter((writer));   
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
               
            kidsHeader.writeHeader();
	            
            message.parse(HeaderType.KIDS);
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(message);
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



