/*
 * Function    : MapExpCanKU.java
 * Titel       :
 * Date        : 16.09.2008
 * Author      : Kewill CSF / SvenHeise
 * Description : Mapping of UIDS-Format into KIDS-Format  of Cancellation
 *    
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Label       :
 * Description : checked for V60 - no changes
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportCancellationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapExpCanUK<br>
 * Erstellt     : 16.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of Cancellation message.
 * 
 * @author heise
 * @version 1.0.00
 */
public class MapExpCanUK extends KidsMessage {
	private BodyExportCancellationKids 	body   = null;
	private MsgExpCan 					msgExpCan;
	
	public MapExpCanUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException  {
		msgExpCan = new MsgExpCan(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body   = new BodyExportCancellationKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
            kidsHeader.writeHeader();
	            
            msgExpCan.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpCan.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgExpCan(msgExpCan);
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


