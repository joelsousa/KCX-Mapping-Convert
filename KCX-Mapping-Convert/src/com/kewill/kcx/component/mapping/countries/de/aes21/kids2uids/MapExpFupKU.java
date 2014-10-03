package com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpFup;
import com.kewill.kcx.component.mapping.formats.uids.aes21.BodyNonExitedExportRequestUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: Mapping of KIDS-Format of Investigation into UIDS-Format of NonExitedExportRequest.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapExpFupKU extends UidsMessage {
	
	private BodyNonExitedExportRequestUids	body;
	private MsgExpFup message;	
	
	public MapExpFupKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			message = new MsgExpFup(parser);
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
            //EI20140318: uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            body.setMessage(message);
            body.setUidsHeader(uidsHeader);

            if (message.isSubVersion22() && kidsHeader.getRelease().equals("2.1.00")) {     //EI20140318
        	   uidsHeader.setMessageVersion("2.2");
            }
            uidsHeader.writeHeader(getCommonFieldsDTO());   //EI20140318
            
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  //EI20120917
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

