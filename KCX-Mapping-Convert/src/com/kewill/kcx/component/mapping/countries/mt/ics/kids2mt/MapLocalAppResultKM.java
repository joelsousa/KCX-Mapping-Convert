/*
 * Function    : MapFailureKU.java
 * Titel       :
 * Date        : 09.10.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of KIDS-Format into UIDS-Format of Failure
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
package com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MaltaMessage;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapLocalAppResultKM<br>
 * Erstellt     : 09.10.2008<br>
 * Beschreibung : Mapping of KIDS-Format of ExportRelease into UIDS-Format of Failure message. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapLocalAppResultKM extends MaltaMessage {

	private MsgFailure msgKids;	
	
	public MapLocalAppResultKM(XMLEventReader parser, String encoding) throws XMLStreamException {
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
            getCommonFieldsDTO().setTargetMessageType("CD917BType");                      

            openElement("CD917BType");
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
				if (this.getMaltaHeader() != null) {
					String newRefnr = this.getMaltaHeader().getMesIdeMES19();						
					if (Db.updateMessageIdHistory(msgKids.getReferenceNumber(), newRefnr, "MT")) {
						writeElement("RefNumHEA4", newRefnr);
					} else {
						writeElement("RefNumHEA4", msgKids.getReferenceNumber());
					}
				}	
				//EI20111104-end
			}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
