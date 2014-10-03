package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

/*
 * Function    : MapExpConKU.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / messer
 * Description : Mapping of UIDS-Format into KIDS-Format of NonExitedExportRequest
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

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNer;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportInvestigationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpNerUK<br>
 * Erstellt     : 05.03.2009<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of NonExitedExportRequest message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapExpFupUK extends KidsMessage {
		
	private BodyExportInvestigationKids 	body   = null;
	private MsgExpNer 					msgExpNer;
	
	public MapExpFupUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpNer = new MsgExpNer(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
	        
        try {
            body   = new BodyExportInvestigationKids(writer);
	            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
            kidsHeader.writeHeader();
	            
            msgExpNer.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpNer.getDocumentReferenceNumber());

            body.setKidsHeader(kidsHeader);
            body.setMsgExpNer(msgExpNer);
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


