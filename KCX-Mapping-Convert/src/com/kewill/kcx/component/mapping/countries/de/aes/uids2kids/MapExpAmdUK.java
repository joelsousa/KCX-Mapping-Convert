/*
 * Function    : MapExpAmdUK.java     
 * Titel       :
 * Date        : 17.03.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of UIDS-Format into KIDS-Format of ExportAmendment
 *
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 27.04.2009           
 * Label       :
 * Description : msgUids replaced with msgExpAdn
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportAmendmentKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpAmdUK<br>
 * Erstellt     : 17.03.2009<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ExportPrenotification message.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapExpAmdUK extends KidsMessage {
	private BodyExportAmendmentKids body   = null;
	private MsgExpAmd msgExpAdn;
	
	public MapExpAmdUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpAdn = new MsgExpAmd(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body  = new BodyExportAmendmentKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
               
            kidsHeader.writeHeader();
	            
            msgExpAdn.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpAdn.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(msgExpAdn);
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


