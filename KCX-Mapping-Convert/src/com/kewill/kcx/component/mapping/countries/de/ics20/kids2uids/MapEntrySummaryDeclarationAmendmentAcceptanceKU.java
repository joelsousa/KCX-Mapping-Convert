package com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ics20.BodyEntrySummaryDeclarationAmendmentAcceptanceUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.22<br>
 * Description	: Mapping of KIDS-EntrySummaryDeclarationAmendmentAcceptance to UIDS-ICSDeclarationAmendmentAccepted.
 * 				: (IE304)
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MapEntrySummaryDeclarationAmendmentAcceptanceKU extends UidsMessage {
	
	private BodyEntrySummaryDeclarationAmendmentAcceptanceUids body;
    private MsgEntrySummaryDeclarationAmendmentAcceptance message;

	public MapEntrySummaryDeclarationAmendmentAcceptanceKU(XMLEventReader parser, String encoding) 
							throws XMLStreamException {
		message = new MsgEntrySummaryDeclarationAmendmentAcceptance(parser, "KIDS");
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyEntrySummaryDeclarationAmendmentAcceptanceUids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();   
            
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
            
            Utils.log("ICS EntrySummaryDeclarationAmendmentAcceptance kids2uids = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}  
}
