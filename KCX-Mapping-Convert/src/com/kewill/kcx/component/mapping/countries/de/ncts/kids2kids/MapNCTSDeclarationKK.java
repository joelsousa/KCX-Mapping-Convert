


/*
 * Function    : MapNCTSDeclarationKK.java
 * Date        : 04.11.2011
 * Author      : Kewill GmbH / Christine Kron
 * Description : Mapping of KIDS-Format of NCTSDeclaration into KIDS-Format with code mapping.
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2kids;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapNCTSDeclarationKK<br>
 * Erstellt     : 04.11.2011<br>
 * Beschreibung : Mapping of KIDS-Format of NCTSDeclaration into KIDS-Format with code mapping.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapNCTSDeclarationKK extends KidsMessage {
	
	private BodyNCTSDeclarationKids 	body   = null;
	private MsgNCTSDeclaration 			msgNCTSDeclaration;
	
	public MapNCTSDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgNCTSDeclaration = new MsgNCTSDeclaration(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyNCTSDeclarationKids(writer);
            body.setKidsHeader(kidsHeader);
            kidsHeader.setWriter((writer));
            
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgNCTSDeclaration.parse(HeaderType.KIDS);
            body.setMessage(msgNCTSDeclaration);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapNCTSDeclarationKK getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
