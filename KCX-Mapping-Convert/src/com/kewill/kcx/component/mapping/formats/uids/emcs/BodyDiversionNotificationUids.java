package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgDiversionNotification;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgDiversionNotification<br>
 * Created		: 17.05.2010<br>
 * Description : Contains Message Structure with fields used in EMCSDiversionNotification Uids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00 
 */

public class BodyDiversionNotificationUids extends UidsMessageEMCS {
	
    private MsgDiversionNotification  message = new MsgDiversionNotification();
    private String version = "";         //EI20110701
 
    public BodyDiversionNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgDiversionNotification getMessage() {
		return message;
	}

	public void setMessage(MsgDiversionNotification message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {  
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSNotificationDivertedEAAD");  
                
            if (version.equalsIgnoreCase("10")) {   //EI20111017
                writeElement("LocalReferenceNumber", message.getReferenceNumber()); 
                writeElement("NotificationType", message.getNotificationType());  
                writeStringToDateTime("NotificationDateAndTime", message.getDateAndTimeOfNotification());   
                writeElement("AadReferenceCode", message.getAadReferenceCode());   
                writeElement("SequenceNumber", message.getSequenceNumber());                       
            } else { 
            	 writeElement("LocalReferenceNumber", message.getReferenceNumber()); 
                 writeElement("NotificationType", message.getNotificationType());  
                 //V20 - DateTime format changed:
                 //writeStringToDateTime("NotificationDateAndTime", message.getDateAndTimeOfNotification()); 
                 writeFormattedDateTime("NotificationDateAndTime", message.getDateAndTimeOfNotification(),
                		 				EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	       //EI20111017
                 writeElement("AadReferenceCode", message.getAadReferenceCode());   
                 writeElement("SequenceNumber", message.getSequenceNumber());  
                 //V20 - new Tag:
                 if (message.getFollowUpAadReference() != null) {                              //EI20110701
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
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
