package com.kewill.kcx.component.mapping.countries.de.ics20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.formats.kids.ics20.BodyDiversionRequestAcknowledgmentKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS20<br>
 * Created		: 19.10.2012<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of DiversionRequestAcknowledgment message.
 *
 * @author krzoska
 * @version 2.0.00
 */
public class MapDiversionRequestAcknowledgmentKK extends KidsMessageICS20 {

    private BodyDiversionRequestAcknowledgmentKids    body;
    private MsgDiversionRequestAcknowledgment 		message;

	public MapDiversionRequestAcknowledgmentKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgDiversionRequestAcknowledgment(parser, "KIDS");
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyDiversionRequestAcknowledgmentKids(writer);

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

            Utils.log("ICS DiversionRequestAcknowledgment kids2kids = " + xmlOutputString.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
