/*
 * Function    : MapExpIndUK.java     
 * Titel       :
 * Date        : 18.03.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of UIDS-Format into KIDS-Format of ExportPrenotification
 *             : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *             : there is NO UIDS-Definition for ExportPrenotification
 *             : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *             : should be the similar to ExportDeclaration (?)
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 22.04.2009          
 * Description : msgUids replaced with MsgExpDat
 */

package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportPrenotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpIndUK<br>
 * Erstellt     : 18.03.2009<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ExportPrenotification message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapExpIndUK extends KidsMessage {
	private BodyExportPrenotificationKids body   = null;
	private MsgExpDat msgExpDat;
	
	public MapExpIndUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body  = new BodyExportPrenotificationKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
               
            kidsHeader.writeHeader();
	            
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


