package com.kewill.kcx.component.mapping.countries.de.emcs.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgSubmittedDraftOfSplittingOperation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.emcs.BodySubmittedDraftOfSplittingOperationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
* Module      : EMCS<br>
* Created     : 27.07.2011<br>
* Description : Mapping of KIDS-Format into KIDS-Format of EMCSSubmittedDraftOfSplittingOperation.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MapSubmittedDraftOfSplittingOperationKK extends KidsMessage {
	
	private BodySubmittedDraftOfSplittingOperationKids	body;
	private MsgSubmittedDraftOfSplittingOperation 		message;	
	
	public MapSubmittedDraftOfSplittingOperationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgSubmittedDraftOfSplittingOperation(parser);
        this.encoding = encoding;
	}	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {        	
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	body = new BodySubmittedDraftOfSplittingOperationKids(writer);                  
            kidsHeader.setWriter((writer));
                        
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                      
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.KIDS);    			//member of message will be filled from input
            
            if (this.getCommonFieldsDTO() != null) {		// for what/where is CommonFieldsDTO used???
            	this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());  
            }         
            body.setKidsHeader(kidsHeader);  			//kidsHeader is needed in body for "writeCodeElement(...)"
            body.setMessage(message);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("EMCSSubmittedDraftOfSplittingOperation KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
