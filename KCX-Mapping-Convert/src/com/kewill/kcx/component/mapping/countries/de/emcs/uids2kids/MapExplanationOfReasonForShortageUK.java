package com.kewill.kcx.component.mapping.countries.de.emcs.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOfReasonForShortage;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.emcs.BodyExplanationOfReasonForShortageKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: EMCS<br>
 * Created		: 20.07.2011<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of EMCSExplanationOnReasonForShortage message.
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */

public class MapExplanationOfReasonForShortageUK extends KidsMessage {
	
    private BodyExplanationOfReasonForShortageKids    body;
    private MsgExplanationOfReasonForShortage 		message;

	public MapExplanationOfReasonForShortageUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																					throws XMLStreamException {
		message = new MsgExplanationOfReasonForShortage(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyExplanationOfReasonForShortageKids(writer);
            
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
