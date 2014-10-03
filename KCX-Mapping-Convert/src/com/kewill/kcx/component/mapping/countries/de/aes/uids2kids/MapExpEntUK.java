/*
 * Function    : MapExEntUK.java
 * Titel       :
 * Date        : 13.10.2008
 * Author      : Kewill CSF /iwaniuk
 * Description : Mapping of UIDS-Format into KIDS-Format of ManualTermination
 *             :
 * Parameters  :

 * Changes
 * -------------
 * Author      : EI
 * Date        : 22.04.2009
 * Description : replaced MsgKind with MsgExpEnt
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportCompletionKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul		: MapExpEntUK<br>
 * Erstellt		: 26.11.2008<br>
 * Beschreibung	: Mapping of UIDS-Format into KIDS-Format of ManualTermination message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapExpEntUK extends KidsMessage {
	private BodyExportCompletionKids 	body   = null;
	private MsgExpEnt 		    	msgExpEnt;

	public MapExpEntUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
		this.kidsHeader = kidsHeader;
        this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
        try {
            body       = new BodyExportCompletionKids(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpEnt.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpEnt.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgKids(msgExpEnt);
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
