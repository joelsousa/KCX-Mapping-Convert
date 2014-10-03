/*
 * Function    : MapErrInfKU.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of UIDS format of ErrorInformation to KIDS Format of ErrorMessage
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyErrorInformationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapErrInfUK<br>
 * Erstellt     : 17.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of ErrorInformation message.
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapErrInfUK extends KidsMessage {
	private BodyErrorInformationKids body = null;
	private MsgErrInf msgErrInf = null;
	
	public MapErrInfUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgErrInf = new MsgErrInf(parser);	
        this.kidsHeader = kidsHeader;
        this.encoding = encoding;

	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
        try {
            body   = new BodyErrorInformationKids(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
           	kidsHeader.writeHeader();
           	
           	msgErrInf.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgErrInf.getReferenceNumber());
           	
            body.setKidsHeader(kidsHeader);
           	body.setMsgErrInf(msgErrInf);
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
