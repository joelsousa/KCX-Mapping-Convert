package com.kewill.kcx.component.mapping.countries.de.ics20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics20.BodyEntrySummaryDeclarationAmendmentAcceptanceKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS20<br>
 * Created		: 2010.07.28<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of EntrySummaryDeclarationAcceptance message.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MapEntrySummaryDeclarationAmendmentAcceptanceKK extends KidsMessage {
	private BodyEntrySummaryDeclarationAmendmentAcceptanceKids body;
    private MsgEntrySummaryDeclarationAmendmentAcceptance 		message;

	public MapEntrySummaryDeclarationAmendmentAcceptanceKK(XMLEventReader parser, String encoding) 
											throws XMLStreamException {
		message = new MsgEntrySummaryDeclarationAmendmentAcceptance(parser, "KIDS");
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyEntrySummaryDeclarationAmendmentAcceptanceKids(writer);
            
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
            
            Utils.log("ICS EntrySummaryDeclarationAmendmentAcceptance kids2kids = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
