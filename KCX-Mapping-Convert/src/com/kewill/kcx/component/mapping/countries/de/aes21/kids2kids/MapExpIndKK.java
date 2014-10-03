package com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportPrenotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : Export/aes<br>
 * Created     : 21.07.2012<br>
 * Description : Mapping of KIDS-Format into KIDS-Format of ExportPrenotification message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapExpIndKK extends KidsMessage {
	
	private MsgExpDat msgExpDat;
	private BodyExportPrenotificationKids body = null;
	
	public MapExpIndKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyExportPrenotificationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            msgExpDat.parse(HeaderType.KIDS);
            body.setMessage(msgExpDat);
            body.setKidsHeader(kidsHeader);
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportPrenotificationKids getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
