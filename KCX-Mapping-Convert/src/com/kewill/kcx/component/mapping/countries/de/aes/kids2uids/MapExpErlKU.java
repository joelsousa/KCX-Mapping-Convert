/*
 * Function    : MapExpErlKU.java
 * Titel       : Manual Termination
 * Date        : 17.09.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : Mapping of KIDS-Format of ManualTerminationExport into UIDS-Format of ManualTermination
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExpManualTerminationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpErlKU<br>
 * Erstellt     : 17.09.2008<br>
 * Beschreibung : Mapping of KIDS-Format into UIDS-Format of ManualTermination message.
 * 
 * @author  Iwaniuk
 * @version 1.0.00
 */
public class MapExpErlKU extends UidsMessage {
	
	private BodyExpManualTerminationUids body = null;
	private MsgExpErl msgExpErl = null;
		
	public MapExpErlKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpErl = new MsgExpErl(parser);		
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExpManualTerminationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
                        
            msgExpErl.parse(HeaderType.KIDS);
            body.setMessage(msgExpErl);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpErl.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(Manual Termination getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
