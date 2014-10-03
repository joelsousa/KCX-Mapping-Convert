/*
 * Function    : MapExpErlUK.java
 * Titel       :
 * Date        : 26.09.2008
 * Author      : Kewill CSF /Houdek
 * Description : Mapping of UIDS-Format into KIDS-Format of Export ManualTermination
 *             :
 * Parameters  :

 * Changes
 * -------------
 * Author      : EI
 * Date        : 27.04.2009
 * Label       :
 * Description : replaced msgUids with MsgExpErl
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyManualTerminationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapExpErlUK<br>
 * Erstellt     : 26.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ManualTermination message.
 * 
 * @author houdek
 * @version 1.0.00
 */
public class MapExpErlUK extends KidsMessage {
	private BodyManualTerminationKids 	body   = null;
	private MsgExpErl 		    	msgExpErl;

	public MapExpErlUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpErl = new MsgExpErl(parser);
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}	

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;

        try {
            body       = new BodyManualTerminationKids(writer);
            
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpErl.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpErl.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(msgExpErl);
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
