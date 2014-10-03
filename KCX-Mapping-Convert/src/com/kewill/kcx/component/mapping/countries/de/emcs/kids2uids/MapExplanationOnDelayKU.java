package com.kewill.kcx.component.mapping.countries.de.emcs.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.formats.uids.emcs.BodyExplanationOnDelayUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Modul       : EMCS<br>
* Created     : 11.05.2010<br>
* Description : Mapping of KIDS-Format into UIDS-Format of MsgExplDelay message.
* 
* @author Iwaniuk
* @version 1.0.00
*/

public class MapExplanationOnDelayKU extends UidsMessage {
	
	private BodyExplanationOnDelayUids 	body;
	private MsgExplanationOnDelay 		message;	
	
	public MapExplanationOnDelayKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExplanationOnDelay(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body   = new BodyExplanationOnDelayUids(writer);            
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                        
            mapKidsToUidsHeader();    
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setUidsHeader(uidsHeader);
            body.setMessage(message);
            body.writeBody();
                       
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(EMCS ExplanationOnDelay UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
