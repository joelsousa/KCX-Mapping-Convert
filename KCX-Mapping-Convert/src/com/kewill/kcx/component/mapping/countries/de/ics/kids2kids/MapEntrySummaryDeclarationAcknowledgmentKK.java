package com.kewill.kcx.component.mapping.countries.de.ics.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAcknowledgmentKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapEntrySummaryDeclarationAcknowledgmentKK<br>
 * Erstellt		: 18.06.2010<br>
 * Beschreibung	: Mapping of KIDS-Format into KIDS-Format of EntrySummaryDeclarationAcknowledgment message.
 *
 * @author  Pete T
 * @version 1.0.00
 */
public class MapEntrySummaryDeclarationAcknowledgmentKK extends KidsMessage {

    private BodyEntrySummaryDeclarationAcknowledgmentKids    body;
    private MsgEntrySummaryDeclarationAcknowledgment 		message;

	public MapEntrySummaryDeclarationAcknowledgmentKK(XMLEventReader parser, String encoding) 
	                                                                                        throws XMLStreamException {
		message = new MsgEntrySummaryDeclarationAcknowledgment(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyEntrySummaryDeclarationAcknowledgmentKids(writer);

            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            kidsHeader.setWriter((writer));

            kidsHeader.writeHeader();

            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

            body.setKidsHeader(kidsHeader);
            body.setMessage(message);

            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();

            writer.flush();
            writer.close();

            Utils.log("ICS EntrySummaryDeclarationAcknowledgment KidsMessage = " + xmlOutputString.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
