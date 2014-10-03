/*
 * Funktion    : BodyCancellationUids.java
 * Titel       :
 * Erstellt    : 08.09.2008
 * Author      : Sven Heise  
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Cancelation) kiff-export.xls 
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Description : checked for V60 - no changes
 * 
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk  
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class BodyExportCancellationUids extends UidsMessage {
	
    private MsgExpCan msgExpCan;
    
    public BodyExportCancellationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
        	
        	openElement("soap:Body");
                openElement("Submit");
                // C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                // setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                    openElement("Export");
                    	openElement("Cancelation");
                    		//openElement("Contact");
                    		//	writeElement("Identity", msgExpCan.getIdentity());
                    		//closeElement(); // Contact
                    	    writeContact(msgExpCan.getContact()); //EI20090609
                    		writeElement("TypeOfDocument", msgExpCan.getTypeOfDocument());
                    		writeStringToDateTime("DateOfAnnulment", msgExpCan.getDateOfAnnulment());
                    		writeElement("ReasonOfAnnulment", msgExpCan.getReasonOfAnnulment());
                    		writeElement("ReferenceNumber", msgExpCan.getReferenceNumber());
                    		writeElement("TypeOfAnnulment", msgExpCan.getTypeOfAnnulment());
                        closeElement(); // Cancelation
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }

	public MsgExpCan getMsgExpCan() {
		return msgExpCan;
	}

	public void setMsgExpCan(MsgExpCan msgExpCan) {
		this.msgExpCan = msgExpCan;
	}
	
}
