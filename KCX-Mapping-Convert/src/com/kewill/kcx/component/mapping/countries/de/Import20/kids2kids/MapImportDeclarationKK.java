package com.kewill.kcx.component.mapping.countries.de.Import20.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.Import20.BodyImportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module       : Import 20<br>
 * Created     	: 12.11.2012<br>
 * Description 	: Mapping of KIDS-Format into KIDS-Format of Import Declaration message.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MapImportDeclarationKK extends KidsMessage {
	
	private MsgImportDeclaration message;
	private BodyImportDeclarationKids body 		= null;
	
	public MapImportDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgImportDeclaration(parser);	       
		this.encoding = encoding;
	}
		
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyImportDeclarationKids(writer);
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
            
            Utils.log("ImportDeclaration k2k= " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
