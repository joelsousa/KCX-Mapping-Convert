package com.kewill.kcx.component.mapping.countries.de.ics.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgImportControlDecision;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyImportControlDecisionKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapImportControlDecisionUK<br>
 * Erstellt		: 04.02.2011<br>
 * Beschreibung	: Mapping of UIDS-Format into KIDS-Format of ICSImportControlDecision IE361 message.
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapImportControlDecisionUK extends KidsMessage {

    private BodyImportControlDecisionKids body;
    private MsgImportControlDecision message;

	public MapImportControlDecisionUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
	                                                                                        throws XMLStreamException {
		message = new MsgImportControlDecision(parser);

		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyImportControlDecisionKids(writer);

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
