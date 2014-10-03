package com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSMRNAllocatedKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSMRNAllocatedUK<br>
 * Created		: 08.26.2010<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of NCTSMRNAllocated message. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class MapNCTSMRNAllocatedUK extends KidsMessage {

	private BodyNCTSMRNAllocatedKids 	body;
	private MsgNCTSMRNAllocated 		message;
	
	public MapNCTSMRNAllocatedUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
		throws XMLStreamException {
		message = new MsgNCTSMRNAllocated(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyNCTSMRNAllocatedKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgNCTSMRNAllocated(message);            
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
	
}
