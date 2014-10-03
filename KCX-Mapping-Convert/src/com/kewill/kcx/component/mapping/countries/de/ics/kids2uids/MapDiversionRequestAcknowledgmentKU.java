package com.kewill.kcx.component.mapping.countries.de.ics.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ics.BodyDiversionRequestAcknowledgmentUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapDiversionRequestAcknowledgmentKU<br>
 * Erstellt		: 15.06.2010<br>
 * Beschreibung	: Mapping of KIDS-Format into UIDS-Format of DiversionRequestAcknowledgemen message.
 *
 * @author  Pete T
 * @version 1.0.00
 */
public class MapDiversionRequestAcknowledgmentKU extends UidsMessage {

    private BodyDiversionRequestAcknowledgmentUids    body;
    private MsgDiversionRequestAcknowledgment 		message;

	public MapDiversionRequestAcknowledgmentKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgDiversionRequestAcknowledgment(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyDiversionRequestAcknowledgmentUids(writer);

            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");

            mapKidsToUidsHeader();

//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());

            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

            body.setUidsHeader(uidsHeader);
            body.setMessage(message);
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();

            writer.flush();
            writer.close();

            Utils.log("ICS DiversionRequestAccepted UidsMessage = " + xmlOutputString.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
