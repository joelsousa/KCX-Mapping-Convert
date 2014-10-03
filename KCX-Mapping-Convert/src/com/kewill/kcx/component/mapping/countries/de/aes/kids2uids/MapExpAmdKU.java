/*
 * Function    : MapExpAmdKU.java
 * Titel       :
 * Date        : 16.03.2009
 * Author      : Kewill CSF / Alfred Krzoska
 * Description : Mapping of KIDS-Format of Amendment into UIDS-Format of ExportAmendment
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

package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportAmendmentUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpAmdKU<br>
 * Erstellt     : 16.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format of Amendment into UIDS-Format of ExportAmendment. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapExpAmdKU extends UidsMessage {
	
	private BodyExportAmendmentUids 	body   = null;
	private MsgExpAmd 					msgExpAmd;
	
	public MapExpAmdKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpAmd = new MsgExpAmd(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportAmendmentUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpAmd.parse(HeaderType.KIDS);
            body.setMsgExpAdn(msgExpAmd);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpAmd.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapExpAmdKU getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
