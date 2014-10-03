package com.kewill.kcx.component.mapping.countries.de.suma62.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgProcessingResults;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.manifest.BodyProcessingResultsUids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module        : Manifest / Suma<br>
 * Created       : 11.02.2013<br>
 * Description   : Mapping of UidsBody of ProcessingResults.
 * 				 

 * @author iwaniuk
 * @version 1.0.00
 */

public class MapProcessingResultsKU extends UidsMessage {
	
	private BodyProcessingResultsUids body = null;
	private MsgProcessingResults message = null;
	
	public MapProcessingResultsKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgProcessingResults(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyProcessingResultsUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();           
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            body.setUidsHeader(uidsHeader);
            body.setMessage(message);

            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
}
