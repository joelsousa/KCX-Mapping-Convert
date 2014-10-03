/*
 * Function    : MapExpCaeKU.java
 * Titel       :
 * Date        : 22.10.2008
 * Author      : Kewill CSF / Marcus Messer
 * Description : Mapping of KIDS-Format into UIDS-Format 
 *               of CancelationResponse
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.ch.aus.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.edec.aus.BodyExportCaeUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpCaeKU<br>
 * Erstellt     : 22.10.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of 
 *                Export CancellationResponse message for CH.
 * 
 * @author Messer
 * @version 1.0.00
 */
public class MapExpCaeKU extends UidsMessage {
	
	private BodyExportCaeUids 	body   = null;
	private MsgExpCae msgExpCae;
	
	public MapExpCaeKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpCae = new MsgExpCae(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportCaeUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpCae.parse(HeaderType.KIDS);
            body.setMsgExpCae(msgExpCae);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgCancellation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
