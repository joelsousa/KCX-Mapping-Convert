package com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportPrenotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : Export/aes<br>
 * Created     : 24.07.2012<br>
 * Description : V21: Mapping of UIDS-ExportPreAdvice into KIDS-ExportPrenotification.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
 

public class MapExpIndUK extends KidsMessage {
	private BodyExportPrenotificationKids body   = null;
	private MsgExpDat message;
	
	public MapExpIndUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		message = new MsgExpDat(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body  = new BodyExportPrenotificationKids(writer);
	            
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


