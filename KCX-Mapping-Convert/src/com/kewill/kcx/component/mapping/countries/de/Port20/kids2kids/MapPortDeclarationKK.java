package com.kewill.kcx.component.mapping.countries.de.Port20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port20.BodyPortDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: PORT.<br>
 * Erstellt		: 08.05.2013<br>
 * Beschreibung	: Mapping of KIDS-Format into KIDS-Format of PortDeclaration.java message.
 * 
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapPortDeclarationKK extends KidsMessage {

    private BodyPortDeclarationKids body;
    private MsgPortDeclaration 		message;

	public MapPortDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgPortDeclaration(parser);
		this.encoding = encoding;
	}

	public String getMessage(boolean isBDP) {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyPortDeclarationKids(writer);

            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            kidsHeader.setWriter((writer));

            kidsHeader.writeHeader();

            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

            body.setKidsHeader(kidsHeader);
            body.setMessage(message);

            //body.writeBody();
            body.writeBody(isBDP);   //EI20130508

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
