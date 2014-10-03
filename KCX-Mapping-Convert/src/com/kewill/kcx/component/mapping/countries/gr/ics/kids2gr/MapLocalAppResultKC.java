package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapFailureKU<br>
 * Erstellt     : 09.10.2008<br>
 * Beschreibung : Mapping of KIDS-Format of ExportRelease into UIDS-Format of Failure message. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapLocalAppResultKC extends GreeceMessage {

	private MsgFailure msgKids;	
	
	public MapLocalAppResultKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgFailure(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            writeStartDocument(encoding, "1.0");    
            
            msgKids.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());  
            getCommonFieldsDTO().setTargetMessageType("CD917B");                      

            openElement("CD917B");
            	setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
            	setAttribute("xmlns", "http://www.eurodyn.com");
    			setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		
    			writeHeaderFields();    			
        		writeHeahea();
        		writeErrorList(msgKids.getErrorList());        		
            closeElement(); 
            
            writer.writeEndDocument();            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportConfirmation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
	
	private void writeHeahea() {
		try {	
			openElement("HEAHEA");
			if (!Utils.isStringEmpty(msgKids.getUCRNumber())) {   							
				writeElement("DocNumHEA5", msgKids.getUCRNumber());										
			} else {
				writeElement("DocNumHEA5", msgKids.getReferenceNumber());	
			}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	

}
