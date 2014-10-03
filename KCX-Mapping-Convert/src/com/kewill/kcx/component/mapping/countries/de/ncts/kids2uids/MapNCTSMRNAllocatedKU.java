package com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ncts.BodyNCTSMRNAllocatedUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSMRNAllocatedKU<br>
 * Created		: 08.25.2010<br>
 * Description	: Mapping of KIDS-Format into UIDS-Format of NCTSMRNAllocated message. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class MapNCTSMRNAllocatedKU extends UidsMessage {
	
	private BodyNCTSMRNAllocatedUids	body;
	private MsgNCTSMRNAllocated			message;
	
	public MapNCTSMRNAllocatedKU(XMLEventReader parser, String encoding)
		throws XMLStreamException {
		message = new MsgNCTSMRNAllocated(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyNCTSMRNAllocatedUids(writer);
            // CK 2011-03-04
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();   
            
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
           	body.setUidsHeader(uidsHeader);
           	body.setMsgNCTSMRNAllocated(message);          
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("NCTS NCTSMRNAllocated UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	} 
}
