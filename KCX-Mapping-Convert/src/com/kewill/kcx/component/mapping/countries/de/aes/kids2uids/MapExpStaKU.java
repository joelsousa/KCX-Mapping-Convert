/*
 * Function    : MapExpStaKU.java
 * Titel       :
 * Date        : 25.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of KIDS-Format of DeclarationResponse into UIDS-Format of ExportDeclarationResponse
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportDeclarationResponseUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpStaKU<br>
 * Erstellt     : 04.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format of DeclarationResponse into UIDS-Format of ExportDeclarationResponse. 
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class MapExpStaKU extends UidsMessage {
	
	private BodyExportDeclarationResponseUids 	body   = null;
	private MsgExpSta 					msgExpSta;
	
	public MapExpStaKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpSta = new MsgExpSta(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportDeclarationResponseUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpSta.parse(HeaderType.KIDS);
            body.setMsgExpSta(msgExpSta);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpSta.getReferenceNumber());
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
