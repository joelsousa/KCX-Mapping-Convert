package com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.emcs20.BodyDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module      : EMCS<br>
* Created     : 15.10.2012<br>
* Description : Mapping of KIDS-Format into KIDS-Format of Declaration message.
* 
* @author iwaniuk
* @version 2.0.00
*/

public class MapEMCSDeclarationKK extends KidsMessage {
	
	private BodyDeclarationKids	body;
	private MsgEMCSDeclaration 		message;	
	
	public MapEMCSDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgEMCSDeclaration(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {        	
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body = new BodyDeclarationKids(writer);                  
            kidsHeader.setWriter((writer));
                        
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                      
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.KIDS);    			//member of message will be filled from input
            
            if (this.getCommonFieldsDTO() != null) {		// for what/where is CommonFieldsDTO used???
            	this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());  
            }         
            body.setKidsHeader(kidsHeader);  			//kidsHeader is needed in body for "writeCodeElement(...)"
            body.setMessage(message);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("EMCS Declaration KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
