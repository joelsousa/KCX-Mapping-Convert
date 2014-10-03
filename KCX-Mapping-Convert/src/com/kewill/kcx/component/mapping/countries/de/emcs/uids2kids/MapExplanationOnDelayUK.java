package com.kewill.kcx.component.mapping.countries.de.emcs.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.formats.kids.emcs.BodyExplanationOnDelayKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of EMCSExplanationOnDelay message.
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */

public class MapExplanationOnDelayUK extends KidsMessage {
	
    private BodyExplanationOnDelayKids  body;
    private MsgExplanationOnDelay 		message;

	public MapExplanationOnDelayUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																			throws XMLStreamException {
		message = new MsgExplanationOnDelay(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		
	    this.writer = writer;
        try {
            body = new BodyExplanationOnDelayKids(writer);
            
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
