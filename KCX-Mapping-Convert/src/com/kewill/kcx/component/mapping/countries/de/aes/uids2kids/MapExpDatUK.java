/*
 * Function    : ExportDeclarationUK.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF /Houdek
 * Description : Mapping of UIDS-Format into KIDS-Format of ExportDeclaration
 *             :
 * Parameters  :

 * Changes
 * -------------
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpDatUK<br>
 * Erstellt		: 05.11.2008<br>
 * Beschreibung	: Mapping of UIDS-Format into KIDS-Format of ExportDeclaration message.
 * 
 * @author  houdek
 * @version 1.0.00
 */
public class MapExpDatUK extends KidsMessage {
    private BodyExportDeclarationKids    body    = null;
    private MsgExpDat                    msgExpDat = null;

	public MapExpDatUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
        msgExpDat = new MsgExpDat(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body       = new BodyExportDeclarationKids(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
//            msgUids.parse(HeaderType.UIDS);
            msgExpDat.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(msgExpDat);
            
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
