/*
 * Function    : BodyConfirmationInvestigation.java
 * Title       :
 * Erstellt    : 22.04.2009
 * Author      : Kewill CSF  / iwaniuk
 * Description : NonExitedExportResponse (Uids) from ConfirmInvestigation (Kids) 
 * 			   : relates to (ConfirmInvestigation) kiff-export.xls 
 *             : V60
 * -----------
 * Changes 
 * -----------
 * Author      : 
 * Date        :
 * Label       : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class BodyNonExitedExportResponseUids extends UidsMessage {
	
    private MsgExpExt msgExpExt;
   
    public BodyNonExitedExportResponseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
        	int listLength = 0;
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
                    	openElement("NonExitedExportResponse");
                    	writeElement("ReferenceNumber", msgExpExt.getReferenceNumber());
                    	writeElement("Remark", msgExpExt.getAnnotation());
                    	if (!Utils.isStringEmpty(msgExpExt.getRealOfficeOfExit()) ){
                    		openElement("CustomsOffices");
                    		writeElement("OfficeOfActualExit", msgExpExt.getRealOfficeOfExit());
                    		closeElement();
                    	}
                        writeStringToDate("DateOfExit",msgExpExt.getDateOfExit());
                        writeStringToDate("DateOfAwaitedExit", msgExpExt.getIntendentExitDate());
                        writeElement("TypeOfExit", msgExpExt.getExitType());                        
                        closeElement(); // NonExitedExportResponse
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

/*	public void setSampleFields() {
		msgExpExt.setReferenceNumber("ChristinesBezugsnummer");
		msgExpExt.setKindOfAnswer("1");
		msgExpExt.setUCRNumber("08DE655102276486E5");
		msgExpExt.setReceiveTime("2008-09-02T15:23:00 +0200");
		
	}*/
	
	public MsgExpExt getMessage() {
		return msgExpExt;
	}

	public void setMessage(MsgExpExt msgExpExt) {
		this.msgExpExt = msgExpExt;
	}
	
}
