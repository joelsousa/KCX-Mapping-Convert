/*
 * Function    : MapExpExtKU.java
 * Titel       :
 * Date        : 22.04.2004
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of KIDS-Format of MsgExpExt.java into UIDS-Format of NonExitedExportResponse
 *             : V60
 * 
 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyNonExitedExportResponseUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpExtKU<br>
 * Erstellt		: 22.04.2009<br>
 * Beschreibung	: Mapping of KIDS-Format of MsgExpExt.java into UIDS-Format of NonExitedExportResponse.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MapExpExtKU extends UidsMessage {
	private MsgExpExt 	msgExpExt;
	private BodyNonExitedExportResponseUids body;
	
	public MapExpExtKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpExt = new MsgExpExt(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyNonExitedExportResponseUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
          
            msgExpExt.parse(HeaderType.KIDS);
            body.setMessage(msgExpExt);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpExt.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportDeclaration getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
