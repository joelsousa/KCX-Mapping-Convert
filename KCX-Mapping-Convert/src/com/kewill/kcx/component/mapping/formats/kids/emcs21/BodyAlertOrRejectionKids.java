package com.kewill.kcx.component.mapping.formats.kids.emcs21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.RejectionReason;
import com.kewill.kcx.component.mapping.countries.de.emcs21.msg.MsgAlertOrRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: EMCS<br>
 * Created		: 10.02.2014<br>
 * Description 	: Contains Message Structure with fields used in Kids EMCSAlertorRejection.
 *              : new for EMCS V21: CT_ReasonCode instead of String
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyAlertOrRejectionKids extends KidsMessageEMCS {

	private MsgAlertOrRejection message;		  

    public BodyAlertOrRejectionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgAlertOrRejection getMessage() {
		return message;
	}
	public void setMessage(MsgAlertOrRejection argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");               
            openElement("EMCSAlertOrRejection");                      
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());  
                writeElement("Clerk", message.getClerk());                            
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("DestinationOffice", message.getDestinationOffice()); 
                writeDateTimeToString("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());               
                writeElement("RejectedFlag", message.getRejectedFlag());    //EI20110819
                //writeDateTimeToString("DateOfAlertOrRejection", message.getDateOfAlertOrRejection());
                writeDateToString("DateOfAlertOrRejection", message.getDateOfAlertOrRejection());
                writeTrader("Consignee", message.getConsignee());
                
               if (message.getAlertOrRejectionReasons() != null) {
                   if (message.getAlertOrRejectionReasons().getRejectionReasonList() != null && 
                		   !message.getAlertOrRejectionReasons().getRejectionReasonList().isEmpty()) { 
                	   openElement("AlertOrRejectionReasons");  
                	   for (RejectionReason reason : message.getAlertOrRejectionReasons().getRejectionReasonList()) {
                		   writeRejectionReason(reason);  
                	   }
                	   closeElement();                	   
                   }
                }
                        
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
    private void writeRejectionReason(RejectionReason reason)  throws XMLStreamException {
    	if (reason == null) {
    		return;
    	}
    	if (reason.isEmpty()) {
    		return;
    	}
    	openElement("RejectionReason"); 
    		writeElement("Code", reason.getCode());
    		if (reason.getComplementaryInformation() != null) {   		
                writeElementWithAttribute("ComplementaryInformation", 
                		reason.getComplementaryInformation().getText(), "language",
                		reason.getComplementaryInformation().getLanguage()); 
            }
    	closeElement();
    }
}

