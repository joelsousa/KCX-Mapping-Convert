package com.kewill.kcx.component.mapping.countries.de.Port.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationRegistration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyPortDeclarationRegistrationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: PORT.<br>
 * Erstellt		: 27.04.2012<br>
 * Beschreibung	: Mapping of KIDS-Format into KIDS-Format of PortDeclarationRegistration.java message.
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapPortDeclarationRegistrationKK extends KidsMessage {

    private BodyPortDeclarationRegistrationKids body;
    private MsgPortDeclarationRegistration message;

	public MapPortDeclarationRegistrationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgPortDeclarationRegistration(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyPortDeclarationRegistrationKids(writer);

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

            Utils.log("PORT BodyPortDeclarationKids KidsMessage = " + xmlOutputString.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
