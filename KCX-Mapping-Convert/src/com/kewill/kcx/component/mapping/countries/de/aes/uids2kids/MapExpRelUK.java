/*
 * Function    : MapExpRelUK.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF /Houdek
 * Description : Mapping of UIDS-Format into KIDS-Format of Export ReverseDeclaration
 *             :
 * Parameters  :

 * Changes
 * -------------
 * Author      : EI
 * Date        : 08.05.2009
 * Label       :
 * Description : replaced setMsgKids() with setMessage()
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyReverseDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpRelUK<br>
 * Erstellt		: 17.09.2008<br>
 * Beschreibung	: Mapping of UIDS-Format into KIDS-Format of Export ReverseDeclaration (ExportRelease).
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class MapExpRelUK extends KidsMessage {
	private BodyReverseDeclarationKids 	body   = null;
	private MsgExpRel 		    	msgExpRel;

    public MapExpRelUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
        msgExpRel = new MsgExpRel(parser);
        this.kidsHeader = kidsHeader;
        this.encoding = encoding;
    }

	public void getMessage(XMLStreamWriter writer) {
        this.writer = writer;

        try {
            body       = new BodyReverseDeclarationKids(writer);
            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpRel.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpRel.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(msgExpRel);
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
