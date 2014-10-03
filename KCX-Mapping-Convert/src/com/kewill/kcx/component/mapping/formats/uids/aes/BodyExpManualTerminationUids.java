/*
 * Function    : BodyExpManualTerminationUids.java
 * Title       : ManualTermination
 * Date        : 10.09.2008
 * Author      : CSF GmbH / iwaniuk
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (ManualTermination) kiff-export.xls 

 * Changes:
 * -----------
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;

public class BodyExpManualTerminationUids extends UidsMessage {
	

    private MsgExpErl  msgExpErl = new MsgExpErl() ;
 
 
    public BodyExpManualTerminationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpErl getMsgKids() {
		return msgExpErl;
	}

	public void setMessage(MsgExpErl message) {
		this.msgExpErl = message;
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
                     openElement("ManualTerminationExport");                      
                        //EI20090609: writeContact(msgExpErl.getClerk()); 
                        writeContact(msgExpErl.getContact()); //EI20090609
                		writeStringToDateTime("DateOfTermination", msgExpErl.getTerminationTime());                   		                 	
                		writeHeaderExtensions(msgExpErl.getSeal(), "ExpErl");   
                		writeElement("ReferenceNumber", msgExpErl.getReferenceNumber());                  		
                		writeElement("Remark", msgExpErl.getAnnotation());	                		
                     closeElement(); // ManualTermination
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }	
}    	
    	
    	
