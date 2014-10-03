package com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportCompletionKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 24.07.2012<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of ExportCompletion message.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MapExpEntUK extends KidsMessage {
	private BodyExportCompletionKids body   = null;
	private MsgExpEnt message;

	public MapExpEntUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		message = new MsgExpEnt(parser);
		this.kidsHeader = kidsHeader;
        this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
        try {
            body = new BodyExportCompletionKids(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            message.setFromFormat("UIDS");    //EI20120914
            
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
