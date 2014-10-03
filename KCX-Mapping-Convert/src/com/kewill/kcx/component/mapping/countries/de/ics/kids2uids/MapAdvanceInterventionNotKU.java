package com.kewill.kcx.component.mapping.countries.de.ics.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ics.BodyAdvanceInterventionNotUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module		: MapAdvanceInterventionNotKU<br>
 * Date Created	: 2010.07.20<br>
 * Description	: Mapping for AdvanceInterventionNot KIDS to UIDS.
 * 
 * @author Lassiter
 * @version 1.0.00
 */

public class MapAdvanceInterventionNotKU extends UidsMessage {
	
	private BodyAdvanceInterventionNotUids body;
	private MsgAdvanceInterventionNot message;
	
	public MapAdvanceInterventionNotKU(XMLEventReader parser, String encoding)
					throws XMLStreamException {
		message = new MsgAdvanceInterventionNot(parser);
		this.encoding = encoding;
		
	}
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyAdvanceInterventionNotUids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();   
            
//            uidsHeader.writeHeader();
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
            
            Utils.log("ICS DiversionRequest UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}  
	
	
}

