package com.kewill.kcx.component.mapping.countries.de.Import20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationConfirmation;
import com.kewill.kcx.component.mapping.formats.kids.Import20.BodyImportDeclarationConfirmationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module       : Import V20<br>
 * Created     	: 22.11.2012<br>
 * Description 	: Mapping of KIDS-Format into KIDS-Format of ImportDeclarationConfirmation message.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MapImportDeclarationConfirmationKK extends KidsMessage {
	
	private MsgImportDeclarationConfirmation message;
	private BodyImportDeclarationConfirmationKids body 		= null;
	
	public MapImportDeclarationConfirmationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgImportDeclarationConfirmation(parser);	       
		this.encoding = encoding;
	}
		
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyImportDeclarationConfirmationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                  
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.KIDS);
            body.setMessage(message); 
            body.setKidsHeader(kidsHeader);
            
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(ImportDeclarationConfirmation k2k  = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
