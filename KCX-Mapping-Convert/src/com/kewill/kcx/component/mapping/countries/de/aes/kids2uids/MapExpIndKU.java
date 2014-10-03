/*
 * Function    : MapExpIndKU.java
 * Titel       :
 * Date        : 18.03.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of KIDS-Format of pre-notification into UIDS-Format of ExportPrenotification
 *             : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *             : there is NO UIDS-Definition for ExportPrenotification in etn_export_V20.xsd
 *             : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *             : should be the similar to ExportDeclaration (?)

 * Changes 
 * -----------
 ** Author      : EI
 * Date        : 22.04.2009
 * Description : msgUids  replaced with MsgExpDat 
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyPreAdviceUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpIndKU<br>
 * Erstellt     : 16.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format of Prenotification into UIDS-Format of ExportPrenotification. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapExpIndKU extends UidsMessage {
	
	private BodyPreAdviceUids 	body   = null;
	private MsgExpDat msgExpDat;
	
	public MapExpIndKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyPreAdviceUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpDat.parse(HeaderType.KIDS);
            body.setMessage(msgExpDat);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapExpStaKU getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
