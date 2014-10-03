/*
 * Funktion    : BodyNonExitedExportRequest.java
 * Titel       :
 * Erstellt    : 24.04.2009
 * Author      : Kewill CSF  / Christine Kron
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to NonExitedExportRequest in kids-export-reply.xls 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      : EI
 * Date:       : 06.05.2009
 * Description : DocumentReferenceNumber replaced with ReferenceNumber
 *
 */
package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNer;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: BodyNonExitedExportRequest<br>
 * Erstellt     : 24.04.2009<br>
 * Beschreibung : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to NonExitedExportRequest in kids-export-reply.xls .
 * 
 * @author kron
 * @version 1.0.00
 *
 */
public class BodyNonExitedExportRequestUids extends UidsMessage {
	
    private MsgExpNer msgExpNer;
   
    
    public BodyNonExitedExportRequestUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
        	openElement("soap:Body");
                openElement("Submit");
                // 	C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                    openElement("Export");
                    	openElement("NonExitedExportRequest");
                    	// DocumentReferenceNumber
                    	writeElement("ReferenceNumber", msgExpNer.getDocumentReferenceNumber());                    	
                    	//writeStringToDate("ResponseUntil", msgExpNer.getResponseUntil()); 
                    	writeStringToDate("DateOfInvestigation", msgExpNer.getDateOfInvestigation());
                    	writeFormattedDateTime("ResponseUntil", msgExpNer.getResponseUntil(),   
                    			msgExpNer.getResponseUntilFormat(), EFormat.ST_Date);	
                    	writeFormattedDateTime("DateOfInvestigation", msgExpNer.getDateOfInvestigation(),   
                    			msgExpNer.getDateOfInvestigationFormat(), EFormat.ST_Date);	
                    	closeElement(); 
                    closeElement(); 
                closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

	public MsgExpNer getMsgExpNer() {
		return msgExpNer;
	}

	public void setMsgExpNer(MsgExpNer msgExpNer) {
		this.msgExpNer = msgExpNer;
	}
}
