package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNck;
import com.kewill.kcx.component.mapping.formats.kids.base.BodyInternalStatus;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Modul        : MapExpNckKk<br>
 * Erstellt     : 09.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of InternalStatusInformation message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapExpNckKK extends KidsMessage {
	
	private MsgExpNck 					msgExpNck;
	private BodyInternalStatus body = null;
	
	public MapExpNckKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpNck = new MsgExpNck(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyInternalStatus(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpNck.parse(HeaderType.KIDS);
            body.setMsgExpNck(msgExpNck);
            body.setKidsHeader(kidsHeader);
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportInternalStatusInformation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
