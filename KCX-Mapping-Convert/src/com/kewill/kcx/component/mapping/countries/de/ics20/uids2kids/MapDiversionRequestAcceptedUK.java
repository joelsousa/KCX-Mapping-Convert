package com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.ics20.BodyDiversionRequestAcknowledgmentKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

 /**
 * Module		: ICS20<br>
 * Created		: 2012.10.22<br>
 * Description	: Mapping of UIDS-ICSDiversionRequestAccepted to KIDS-DiversionRequestAcknowledgment.
 * 				: (IE325)
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MapDiversionRequestAcceptedUK extends KidsMessage {

    private BodyDiversionRequestAcknowledgmentKids body;
    private MsgDiversionRequestAcknowledgment message;

	public MapDiversionRequestAcceptedUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
	                                                                           throws XMLStreamException {
		message = new MsgDiversionRequestAcknowledgment(parser, "UIDS");

		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyDiversionRequestAcknowledgmentKids(writer);

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
