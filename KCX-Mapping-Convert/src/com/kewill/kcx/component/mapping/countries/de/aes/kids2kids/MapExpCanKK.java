package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportCancellationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Modul        : MapExpCanKK<br>
 * Erstellt     : 10.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of Cancellation message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapExpCanKK extends KidsMessage {
	
	private MsgExpCan 					msgExpCan;
	private BodyExportCancellationKids body = null;
	
	public MapExpCanKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpCan = new MsgExpCan(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyExportCancellationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpCan.parse(HeaderType.KIDS);
            body.setMsgExpCan(msgExpCan);
            body.setKidsHeader(kidsHeader);
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportCancellation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
