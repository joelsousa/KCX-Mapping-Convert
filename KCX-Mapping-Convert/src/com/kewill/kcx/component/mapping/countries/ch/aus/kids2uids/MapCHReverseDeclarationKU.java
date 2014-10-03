/*
 * Function    : MapCHReverseDeclarationKU.java
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of KIDS-Format into UIDS-Format of ReverseDeclaration
 *             : 
 * Parameters  : 
 * -----------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 29.04.2009
 * Label       :
 * Description : MsgKids replaced with MsgExpRelCH
 */

package com.kewill.kcx.component.mapping.countries.ch.aus.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.edec.aus.BodyCHReverseDeclarationUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapCHReverseDeclarationKU<br>
 * Erstellt     : 10.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format of ExportRelease into UIDS-Format of ReverseDeclaration message. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapCHReverseDeclarationKU extends UidsMessage {

	private BodyCHReverseDeclarationUids 	body   = null;
	private MsgExpRelCH 		msgExpRelCH;
	
	public MapCHReverseDeclarationKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpRelCH = new MsgExpRelCH(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyCHReverseDeclarationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();            
//            uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
                      
            msgExpRelCH.parse(HeaderType.KIDS);
            body.setMessage(msgExpRelCH);

            getCommonFieldsDTO().setReferenceNumber(msgExpRelCH.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            if (Config.getLogXML()) {
                Utils.log("(MapCHReverseDeclarationKU getMessage) Msg = " + xmlOutputString.toString());
            }
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
	
}
