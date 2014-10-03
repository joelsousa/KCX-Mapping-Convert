package com.kewill.kcx.component.mapping.countries.de.ics.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryRelease;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgImportControlDecision;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ics.BodyEntryReleaseUids;
import com.kewill.kcx.component.mapping.formats.uids.ics.BodyImportControlDecisionUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapImportControlDecisionKU<br>
 * Erstellt		: 04.02.2011<br>
 * Beschreibung	: Mapping of KIDS-Format into UIDS-Format of ICSImportControlDecision IE361 message.
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapImportControlDecisionKU extends UidsMessage {
	
    private BodyImportControlDecisionUids body;
    private MsgImportControlDecision 		message;

	public MapImportControlDecisionKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgImportControlDecision(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyImportControlDecisionUids(writer);
            
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
            
            Utils.log("ICS BodyAcceptance UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}  
}
