package com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyConfirmInvestigationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : Export/aes<br>
 * Created     : 24.07.2012<br>
 * Description : V21: Mapping of UIDS-NonExitedExportResponse into KIDS-ConfirmInvestigation.
 * 
 * @author iwaniuk
 * @version 2.1.00
 * 
 * Changes: MsgExpExt will be imported from ...aes21.msg
 */

public class MapExpExtUK extends KidsMessage {
	private MsgExpExt message;
    private BodyConfirmInvestigationKids  body;
    

	public MapExpExtUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		message = new MsgExpExt(parser);        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyConfirmInvestigationKids(writer);
            
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
