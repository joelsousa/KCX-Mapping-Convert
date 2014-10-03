package com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ics20.BodyEntrySummaryDeclarationAcknowledgementUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module		: ICS20<br>
* Created		: 2012.10.22.<br>
* Description	: Mapping of KIDS-ICSEntrySummaryDeclarationAcknowledgment toUIDS-ICSDeclarationAccepted
* 				: (IE328)
* 
* @author iwaniuk
* @version 2.0.00
*/

public class MapEntrySummaryDeclarationAcknowledgmentKU extends UidsMessage {

    private BodyEntrySummaryDeclarationAcknowledgementUids body;
    private MsgEntrySummaryDeclarationAcknowledgment message;

	public MapEntrySummaryDeclarationAcknowledgmentKU(XMLEventReader parser, String encoding) 
	                                     throws XMLStreamException {
		message = new MsgEntrySummaryDeclarationAcknowledgment(parser, "KIDS");
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyEntrySummaryDeclarationAcknowledgementUids(writer);

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

            Utils.log("ICS EntrySummaryDeclarationAcknowledgement kids2uids = " + xmlOutputString.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
