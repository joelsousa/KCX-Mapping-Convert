package com.kewill.kcx.component.mapping.countries.de.emcs.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

//import com.kewill.kcx.component.mapping.countries.de.emcs.msg.InternalStatusInformation;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNck;
import com.kewill.kcx.component.mapping.formats.kids.base.BodyInternalStatus;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of InternalStatusInformation 
 *              : (InternalStatusInformation as aes.MsgExpNck).
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */

public class MapInternalStatusInformationUK extends KidsMessage {
	
    private BodyInternalStatus  body;
    private MsgExpNck 			message;

	public MapInternalStatusInformationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																					throws XMLStreamException {		
		message = new MsgExpNck(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyInternalStatus(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body .setMsgExpNck(message);            
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
