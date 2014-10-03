package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Modul        : MapConfirmKK<br>
 * Erstellt     : 10.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of Confirmation message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapConfirmKK extends KidsMessage {
	public MapConfirmKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            writer.flush();
            writer.close();
            Utils.log("(MsgExportConfirm getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
