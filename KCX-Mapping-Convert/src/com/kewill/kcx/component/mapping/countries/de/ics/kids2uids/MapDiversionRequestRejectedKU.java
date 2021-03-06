package com.kewill.kcx.component.mapping.countries.de.ics.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ics.BodyDiversionRequestRejectedUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module		: MapDiversionRequestRejectedKU<br>
 * Created		: 09.11.2010<br>
 * Description	: Mapping of KIDS-Format into UIDS-Format of DiversionRequestRejectedKU message.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 */
public class MapDiversionRequestRejectedKU extends UidsMessage {
	private BodyDiversionRequestRejectedUids    body;
    private MsgDiversionRequestRejected 		message;

	public MapDiversionRequestRejectedKU(XMLEventReader parser, String encoding) 
							throws XMLStreamException {
		message = new MsgDiversionRequestRejected(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter		xmlOutputString	= new StringWriter();
        XMLOutputFactory	factory			= XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyDiversionRequestRejectedUids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();   
            
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setUidsHeader(uidsHeader);
            body.setMessage(message);            
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("ICS DiversionRequestRejected UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}  
}
