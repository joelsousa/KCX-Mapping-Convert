package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgBEExpDat;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportDeclarationKidsBE;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Modul        : MapBEExportDeclarationKK<br>
 * Erstellt     : 17.12.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of Export Declaration message of Belgium.
 * 
 * @author krzoska
 * @version 1.1.00
 */
public class MapBEExportDeclarationKK extends KidsMessage {
	
	private MsgBEExpDat 				msgExpDatBE	= null;
	private BodyExportDeclarationKidsBE body 		= null;
	
	public MapBEExportDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpDatBE = new MsgBEExpDat(parser);
	        
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyExportDeclarationKidsBE(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                   
            kidsHeader.writeHeader();
            
            msgExpDatBE.parse(HeaderType.KIDS);
            body.setMessage(msgExpDatBE); 
            body.setKidsHeader(kidsHeader);
            
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportDeclaration getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
