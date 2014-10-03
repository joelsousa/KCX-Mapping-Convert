package com.kewill.kcx.component.mapping.countries.de.ics.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapDiversionRequestUK<br>
 * Erstellt		: 05.05.2010<br>
 * Beschreibung	: Mapping of UIDS-Format into KIDS-Format of DiversionRequest message.
 * 
 * @author  Pete T
 * @version 1.0.00
 */
public class MapDiversionRequestUK extends KidsMessage {
	
    private BodyDiversionRequestKids    body;
    private MsgDiversionRequest 		message;

	public MapDiversionRequestUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
	                                                                                        throws XMLStreamException {
		message = new MsgDiversionRequest(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyDiversionRequestKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(message);            
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
