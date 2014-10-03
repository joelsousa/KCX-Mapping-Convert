package com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryDetailsData;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryRelease;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntryDetailsDataKids;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntryReleaseKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS<br>
 * Created		: 08.11.2013<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of ICSEntryDetailsData IE329 message.
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapEntryDetailsDataUK extends KidsMessage {

	private BodyEntryDetailsDataKids body;
    private MsgEntryDetailsData message;

	public MapEntryDetailsDataUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
	                                                                                        throws XMLStreamException {
		message = new MsgEntryDetailsData(parser);

		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyEntryDetailsDataKids(writer);

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
