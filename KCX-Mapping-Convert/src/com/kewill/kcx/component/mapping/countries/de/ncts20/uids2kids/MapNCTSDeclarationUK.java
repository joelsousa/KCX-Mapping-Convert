package com.kewill.kcx.component.mapping.countries.de.ncts20.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts20.BodyNCTSDeclarationKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module 		: NCTS<br>
 * Created 		: 09.02.2013<br>
 * Description  : Mapping of UIDS-Format into KIDS-Format of NCTSDeclaration
 * message.
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class MapNCTSDeclarationUK extends KidsMessage {

	private BodyNCTSDeclarationKids body;
	private MsgNCTSDeclaration message;

	public MapNCTSDeclarationUK(XMLEventReader parser, KidsHeader kidsHeader,
			String encoding) throws XMLStreamException {
		message = new MsgNCTSDeclaration(parser);
		
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyNCTSDeclarationKids(writer);
            
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
