/*
 * Function    : MapExpStaKK.java
 * Titel       :
 * Date        : 26.03.2009
 * Author      : Kewill CSF / Marcus Meﬂer
 * Description : Mapping of KIDS-Format into KIDS-Format of DeclarationResponse
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
package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;


import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyDeclarationResponseKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpStaKK<br>
 * Erstellt     : 26.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of DeclarationResponse message.
 * 
 * @author meﬂer
 * @version 1.0.00
 */
public class MapExpStaKK extends KidsMessage {
	private BodyDeclarationResponseKids	body   = null;
	private MsgExpSta 					msgExpSta;
	
	public MapExpStaKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpSta = new MsgExpSta(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
		 StringWriter xmlOutputString = new StringWriter();
		  XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyDeclarationResponseKids(writer);
            kidsHeader.setWriter((writer));   
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");

            kidsHeader.writeHeader();
	            
            msgExpSta.parse(HeaderType.KIDS);
            
            body.setKidsHeader(kidsHeader);
            body.setMsgExpSta(msgExpSta);
            body.writeBody();
	            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
	            
            writer.flush();
            writer.close();
            Utils.log("(DeclarationResponse getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
}


