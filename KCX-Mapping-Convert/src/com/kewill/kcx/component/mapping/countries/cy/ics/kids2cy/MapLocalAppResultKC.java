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
package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.db.Db;
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
public class MapLocalAppResultKC extends CyprusMessage {

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
			if (!Utils.isStringEmpty(msgKids.getUCRNumber())) {   //EI20110714								
				writeElement("DocNumHEA5", msgKids.getUCRNumber());	//EI20110714
				//expDatArrENTKEY201 = msgKids.get???;								
			} else {
				//EI20111104-beginn 
				//writeElement("DocNumHEA5", msgKids.getReferenceNumber());	
				if (this.getCyprusHeader() != null) {
					String newRefnr = this.getCyprusHeader().getMesIdeMES19();						
					if (Db.updateMessageIdHistory(msgKids.getReferenceNumber(), newRefnr, "CY")) {
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
