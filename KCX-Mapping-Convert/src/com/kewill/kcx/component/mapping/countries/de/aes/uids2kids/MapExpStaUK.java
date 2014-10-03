/*
 * Function    : MapExpStaUK.java
 * Titel       :
 * Date        : 24.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of UIDS-Format into KIDS-Format of DeclarationResponse
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
package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyDeclarationResponseKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpStaUK<br>
 * Erstellt     : 24.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of DeclarationResponse message.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapExpStaUK extends KidsMessage {
	private BodyDeclarationResponseKids	body   = null;
	private MsgExpSta 					msgExpSta;
	
	public MapExpStaUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpSta = new MsgExpSta(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body   = new BodyDeclarationResponseKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
               
            kidsHeader.writeHeader();
	            
            msgExpSta.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpSta.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgExpSta(msgExpSta);
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


