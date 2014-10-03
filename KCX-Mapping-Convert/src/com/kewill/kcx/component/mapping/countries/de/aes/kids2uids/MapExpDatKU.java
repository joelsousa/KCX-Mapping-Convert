/*
 * Function    : MapExpDatKU.java
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Mapping of KIDS-Format of ExportDeclaration into UIDS-Format of ExportDeclaration
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportDeclarationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpDatKU<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Mapping of KIDS-Format into UIDS-Format of ExportDeclaration. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapExpDatKU extends UidsMessage {
//	private BodyExportDeclarationUids 	body   = null;
//	private MsgKids 		msgKids;
	private BodyExportDeclarationUids 	body   = null;
	private MsgExpDat 		msgExpDat;
	
	public MapExpDatKU(XMLEventReader parser, String encoding) throws XMLStreamException {
//		msgKids = new MsgKids(parser);
		msgExpDat = new MsgExpDat(parser);
//        msgKids.msgName = "ExportDeclaration";
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
//            body   = new BodyExportDeclarationUids(writer);
            body   = new BodyExportDeclarationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument("UTF-8", "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
          
            msgExpDat.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());

            body.setUidsHeader(uidsHeader);
            body.setMsgKids(msgExpDat);

            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
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
