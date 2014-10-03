/*
 * Function    : MapExpConKU.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of UIDS-Format into KIDS-Format  of ExportConfirmation
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportConfirmationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpConUK<br>
 * Erstellt     : 05.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ExportConfirmation message.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapExpConUK extends KidsMessage {
		
	private BodyExportConfirmationKids 	body   = null;
	private MsgExpCon 					msgExpCon;
	
	public MapExpConUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpCon = new MsgExpCon(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body   = new BodyExportConfirmationKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
            kidsHeader.writeHeader();
	            
            msgExpCon.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpCon.getReferenceNumber());

            body.setKidsHeader(kidsHeader);
            body.setMsgExpCon(msgExpCon);
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


