package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgDiversionNotification;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: BodyDiversionNotificationKids<br>
 * Created		: 18.05.2010<br>
 * Description 	: Contains Message Structure with fields used in Kids EMCSDiversionNotification.
 *              : V20 new Tag: FollowUpAadReferences
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyDiversionNotificationKids extends KidsMessageEMCS {

	private MsgDiversionNotification message;	
	
    public BodyDiversionNotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgDiversionNotification getMessage() {
		return message;
	}
	public void setMessage(MsgDiversionNotification argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");               
            openElement("EMCSDiversionNotification");                      
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk()); 
                writeElement("NotificationType", message.getNotificationType()); 
                writeElement("AadReferenceCode", message.getAadReferenceCode());  
                writeElement("SequenceNumber", message.getSequenceNumber());     
                writeDateTimeToString("DateAndTimeOfNotification", message.getDateAndTimeOfNotification());                
                if (message.getFollowUpAadReference() != null) {                  					 //V20 new
                	if (message.getFollowUpAadReference().getAadReferenceCodeList() != null && 
                		   !message.getFollowUpAadReference().getAadReferenceCodeList().isEmpty()) { 
                		openElement("FollowUpAadReferences");  
                		   for (String aad : message.getFollowUpAadReference().getAadReferenceCodeList()) {
                			   writeElement("AadReferenceCode", aad);  
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

