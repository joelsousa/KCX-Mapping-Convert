package com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.uids.aes21.BodyPreAdviceUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: Mapping of KIDS-Format of PreNotification into UIDS-Format of ExportPreAdvice.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapExpIndKU extends UidsMessage {
	
	private BodyPreAdviceUids 	body   = null;
	private MsgExpDat message;
	
	public MapExpIndKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExpDat(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyPreAdviceUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            body.setMessage(message);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  //EI20120917
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapExpStaKU getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
