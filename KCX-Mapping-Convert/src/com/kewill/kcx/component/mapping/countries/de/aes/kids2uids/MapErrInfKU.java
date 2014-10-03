/*
 * Function    : MapErrInfKU.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyErrorInformationUIDS;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapErrInfKU<br>
 * Erstellt     : 17.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of ErrorInformation message.
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MapErrInfKU extends UidsMessage {
	
	private BodyErrorInformationUIDS body = null;
	private MsgErrInf msgErrInf = null;
	
	public MapErrInfKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgErrInf = new MsgErrInf(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyErrorInformationUIDS(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgErrInf.parse(HeaderType.KIDS);
            body.setUidsHeader(uidsHeader);
            body.setMsgErrInf(msgErrInf);

            getCommonFieldsDTO().setReferenceNumber(msgErrInf.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(ErrorInformation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
}
