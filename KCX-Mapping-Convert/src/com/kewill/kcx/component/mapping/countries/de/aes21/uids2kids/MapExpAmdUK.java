package com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportAmendmentKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : Export/aes<br>
 * Created     : 17.07.2012<br>
 * Description : V21: Mapping of UIDS-Format into KIDS-Format of Amendment message.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapExpAmdUK extends KidsMessage {
	private BodyExportAmendmentKids body   = null;
	private MsgExpAmd message;
	
	public MapExpAmdUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		message = new MsgExpAmd(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body  = new BodyExportAmendmentKids(writer);
	            
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


