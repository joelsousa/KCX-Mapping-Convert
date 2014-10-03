package com.kewill.kcx.component.mapping.countries.de.emcs21.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs21.msg.MsgAlertOrRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.emcs21.BodyAlertOrRejectionKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module        : EMCS<br>
* Created		: 10.02.2014<br>
* Description   : Mapping of KIDS-Format into KIDS-Format of EMCSAlertOrRejection message.
* 				: new in V21: ReasonCode in CT_ReasonCode
* 
* @author iwaniuk
* @version 2.1.00
*/

public class MapAlertOrRejectionKK extends KidsMessage {
	
	private BodyAlertOrRejectionKids	body;
	private MsgAlertOrRejection 		message;	
	
	public MapAlertOrRejectionKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgAlertOrRejection(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {        	
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body   = new BodyAlertOrRejectionKids(writer);                                
            kidsHeader.setWriter((writer));
                       
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                      
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
            
            Utils.log("EMCSAlertOrRejection KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
