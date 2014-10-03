package com.kewill.kcx.component.mapping.countries.de.ics.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationRejectedKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module 		: MapEntrySummaryDeclarationRejectedUK<br>
 * Date Started	: 2010.11.09<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of EntrySummaryDeclarationRejected message.
 * 				: (IE316)
 * @author Elbert Jude Eco
 * @version 1.0.00
 */
public class MapDeclarationRejectedUK extends KidsMessage {
	private BodyEntrySummaryDeclarationRejectedKids		body;
	private MsgEntrySummaryDeclarationRejected			message;
	
	public MapDeclarationRejectedUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding)
		throws XMLStreamException {
		message = new MsgEntrySummaryDeclarationRejected(parser);
		
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyEntrySummaryDeclarationRejectedKids(writer);
            
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
