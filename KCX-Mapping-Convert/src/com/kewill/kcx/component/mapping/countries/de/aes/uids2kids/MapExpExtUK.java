/*
 * Function    : ConfirmInvestigationUK.java
 * Titel       :
 * Date        : 22.04.2009
 * Author      : Kewill CSF /iwaniuk
 * Description : Mapping of UIDS-Format into KIDS-Format of ConfirmInvestigation
 *             : V60
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyConfirmInvestigationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpEntUK<br>
 * Erstellt     : 22.04.2009<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ConfirmInvestigation.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapExpExtUK extends KidsMessage {
	private MsgExpExt                       msgExpExt;
    private BodyConfirmInvestigationKids    body;
    

	public MapExpExtUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpExt = new MsgExpExt(parser);        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyConfirmInvestigationKids(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpExt.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpExt.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgKids(msgExpExt);
            
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
