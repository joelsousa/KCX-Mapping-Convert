package com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgStatusResponse;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.emcs20.BodyStatusResponseUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module      : EMCS<br>
* Created     : 12.10.2012<br>
* Description : Mapping of KIDS-Format into UIDS-Format of EMCSStatusResponse message.
* 
* @author Iwaniuk
* @version 2.0.00
*/

public class MapStatusResponseKU extends UidsMessage {
	
	private BodyStatusResponseUids body;
	private MsgStatusResponse 	message;	
	
	public MapStatusResponseKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgStatusResponse(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body   = new BodyStatusResponseUids(writer);            
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());     
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");           
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                        
            mapKidsToUidsHeader();  
            
//          uidsHeader.writeHeader();
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
            
            Utils.log("EMCS MsgStatusResponse UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
