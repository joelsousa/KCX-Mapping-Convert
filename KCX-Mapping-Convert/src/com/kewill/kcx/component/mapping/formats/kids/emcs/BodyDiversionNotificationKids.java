package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgDiversionNotification;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: BodyDiversionNotificationKids<br>
 * Date			: 18.05.2010<br>
 * Description 	: Contains Message Structure with fields used in Kids EMCSDiversionNotification.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyDiversionNotificationKids extends KidsMessageEMCS {

	private MsgDiversionNotification message;	
	private String version = "";         //EI20110701

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
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
                if (!version.equalsIgnoreCase("10")) {   
                   if (message.getFollowUpAadReference() != null) {
                	   if (message.getFollowUpAadReference().getAadReferenceCodeList() != null && 
                		   !message.getFollowUpAadReference().getAadReferenceCodeList().isEmpty()) { 
                		   openElement("FollowUpAadReferences");  
                		   for (String aad : message.getFollowUpAadReference().getAadReferenceCodeList()) {
                			   writeElement("AadReferenceCode", aad);  
                		   }
                		   closeElement();
                	   }
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

