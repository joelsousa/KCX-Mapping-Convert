package com.kewill.kcx.component.mapping.countries.de.emcs.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReportOfReceipt;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.emcs.BodyReportOfReceiptKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module       : EMCS
* Created      : 26.05.2010<br>
* Description : Mapping of KIDS-Format into KIDS-Format of ReportOfReceipt message.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MapReportOfReceiptKK extends KidsMessage {
	
	private BodyReportOfReceiptKids body   = null;
	private MsgReportOfReceipt		message;	
	
	public MapReportOfReceiptKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgReportOfReceipt(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	 
             kidsHeader.setWriter((writer));
             
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body   = new BodyReportOfReceiptKids(writer);                        
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
            
            Utils.log("(EMCS ReportOfReceipt KidsMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
