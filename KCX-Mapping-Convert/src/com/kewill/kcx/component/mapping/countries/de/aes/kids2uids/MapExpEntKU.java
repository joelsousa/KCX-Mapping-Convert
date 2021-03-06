/*
 * Function    : MapExpEntKU.java
 * Titel       : Export Completion
 * Date        : 24.09.2008
 * Author      : Kewill CSF / Heise
 * Description : Mapping of KIDS-Format of ExportCompletion into UIDS-Format of ExportCompletion
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportCompletionUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapExpEntKU<br>
 * Erstellt     : 24.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of ExportCompletion message.
 * 
 * @author  heise
 * @version 1.0.00
 */
public class MapExpEntKU extends UidsMessage {
	private BodyExportCompletionUids 	body   = null;
	private MsgExpEnt 					msgExpEnt;
	
	public MapExpEntKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
		msgExpEnt.setMsgName("Completion");
		this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportCompletionUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgExpEnt.parse(HeaderType.KIDS);
            body.setUidsHeader(uidsHeader);
            body.setMsgExpEnt(msgExpEnt);

            getCommonFieldsDTO().setReferenceNumber(msgExpEnt.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportCompletion getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
