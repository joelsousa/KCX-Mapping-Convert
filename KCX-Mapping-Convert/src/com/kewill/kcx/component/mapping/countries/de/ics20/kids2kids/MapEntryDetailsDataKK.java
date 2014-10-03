package com.kewill.kcx.component.mapping.countries.de.ics20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryDetailsData;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics20.BodyEntryDetailsDataKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapEntryReleaseKK<br>
 * Created		: 08.11.2013<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of ICSEntryDetailsData IE329 message.
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapEntryDetailsDataKK extends KidsMessage {

    private BodyEntryDetailsDataKids body;
    private MsgEntryDetailsData message;

	public MapEntryDetailsDataKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgEntryDetailsData(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyEntryDetailsDataKids(writer);

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

            Utils.log("ICSEntryDetailsDataKids KidsMessage = " + xmlOutputString.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
