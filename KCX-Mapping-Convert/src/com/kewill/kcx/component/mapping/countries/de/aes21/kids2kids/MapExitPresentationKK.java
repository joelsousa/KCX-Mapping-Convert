package com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtPre;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExitPresentationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : Export/aes<br>
 * Craeted     : 26.07.2012<br>
 * Description : Mapping of KIDS-Format into KIDS-Format of MsgExitPresentation message.
 * 
 * @author iwaaniuk
 * @version 1.0.00
 */
public class MapExitPresentationKK extends KidsMessage {
	
	private MsgExtPre message;
	private BodyExitPresentationKids body = null;
	
	public MapExitPresentationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExtPre(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyExitPresentationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            message.parse(HeaderType.KIDS);
            body.setMessage(message);
            body.setKidsHeader(kidsHeader);
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(BodyExitPresentationKids getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
