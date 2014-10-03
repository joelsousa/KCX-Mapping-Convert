package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgAlertOrRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: EMCS<br>
 * Created		: 05.07.2011<br>
 * Description 	: Contains Message Structure with fields used in Kids EMCSAlertorRejection.
 *              : new for EMCS V20
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
                   if (message.getAlertOrRejectionReasons().getReasonCodeList() != null && 
                		   !message.getAlertOrRejectionReasons().getReasonCodeList().isEmpty()) { 
                		   openElement("AlertOrRejectionReasons");  
                		   for (String code : message.getAlertOrRejectionReasons().getReasonCodeList()) {
                			   writeElement("ReasonCode", code);  
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
}

