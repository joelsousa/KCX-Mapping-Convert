/*
 * Function    : MapExpConKU.java
 * Titel       :
 * Date        : 04.09.2008
 * Author      : Kewill CSF / kron
 * Description : Mapping of KIDS-Format of ExportConfirmation into UIDS-Format of ExportConfirmation
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportConfirmationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpConKU<br>
 * Erstellt     : 04.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of ExportConfirmation message.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapExpConKU extends UidsMessage {
	
	private BodyExportConfirmationUids 	body   = null;
	private MsgExpCon 					msgExpCon;
	
	public MapExpConKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpCon = new MsgExpCon(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportConfirmationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpCon.parse(HeaderType.KIDS);
            body.setUidsHeader(uidsHeader);
            body.setMsgExpCon(msgExpCon);

            getCommonFieldsDTO().setReferenceNumber(msgExpCon.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportConfirmation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
