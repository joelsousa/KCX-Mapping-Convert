package com.kewill.kcx.component.mapping.countries.de.emcs.kids2kids;

/**
* Modul        : MapValidDeclarationKK<br>
* Erstellt     : 26.05.2010<br>
* Beschreibung : Mapping of KIDS-Format into KIDS-Format of ValidDeclaration message.
* 
* @author iwaniuk
* @version 1.0.00
*/

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.emcs.BodyValidDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

public class MapValidDeclarationKK extends KidsMessage {
	
	private BodyValidDeclarationKids body   = null;
	private MsgValidDeclaration		 message;	
	
	public MapValidDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgValidDeclaration(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	 
            kidsHeader.setWriter((writer));
             
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body   = new BodyValidDeclarationKids(writer);                        
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
            
            Utils.log("EMCS MsgValidDeclaration KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
