/*
 * Function    : MapExpCanKU.java
 * Titel       :
 * Date        : 08.09.2008
 * Author      : Kewill CSF / Heise
 * Description : Mapping of KIDS-Format of Cancellation into UIDS-Format of Cancellation
 *             : 
 * Parameters  : 

* Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Label       : 
 * Description : checked for V60 - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportCancellationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpCanKU<br>
 * Erstellt     : 08.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of Cancellation message.
 * 
 * @author  heise
 * @version 1.0.00
 */
public class MapExpCanKU extends UidsMessage {
	
	private BodyExportCancellationUids 	body   = null;
	private MsgExpCan msgExpCan;
	
	public MapExpCanKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpCan = new MsgExpCan(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportCancellationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpCan.parse(HeaderType.KIDS);
            body.setUidsHeader(uidsHeader);
            body.setMsgExpCan(msgExpCan);

            getCommonFieldsDTO().setReferenceNumber(msgExpCan.getReferenceNumber());
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
