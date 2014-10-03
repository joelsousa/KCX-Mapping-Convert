package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyFailureKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Modul        : MapFailureKK<br>
 * Erstellt     : 10.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of Failure message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapFailureKK extends KidsMessage {
	
	private BodyFailureKids		body	   = null;
	private MsgFailure      	msgConFail = null;
	
	public MapFailureKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgConFail = new MsgFailure(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyFailureKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                   
            kidsHeader.writeHeader();
            
            msgConFail.parse(HeaderType.KIDS);
            body.setMsgConfirmFailure(msgConFail);
            body.setKidsHeader(kidsHeader);
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(ConfirmFailure getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
