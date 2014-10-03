package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgDiversionNotification;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgDiversionNotification<br>
 * Created		: 17.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSDiversionNotification Uids message.
 *              : V20 - DateTime format was changed, new Tag: FollowUpAadReferences
 *                 
 * @author iwaniuk
 * @version 2.0.00 
 */

public class BodyDiversionNotificationUids extends UidsMessageEMCS20 {
	
    private MsgDiversionNotification  message = new MsgDiversionNotification();
    
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
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSNotificationDivertedEAAD");  
                          
            	writeElement("LocalReferenceNumber", message.getReferenceNumber()); 
                writeElement("NotificationType", message.getNotificationType());                   
                 //writeStringToDateTime("NotificationDateAndTime", message.getDateAndTimeOfNotification()); 
                writeFormattedDateTime("NotificationDateAndTime", message.getDateAndTimeOfNotification(),
                		 				EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	       //EI20111017
                writeElement("AadReferenceCode", message.getAadReferenceCode());   
                writeElement("SequenceNumber", message.getSequenceNumber());  
                
                if (message.getFollowUpAadReference() != null) {                             
                    if (message.getFollowUpAadReference().getAadReferenceCodeList() != null && 
                     		   !message.getFollowUpAadReference().getAadReferenceCodeList().isEmpty()) { 
                     	 openElement("FollowUpAadReferences");                                  //V20 - new Tag
                     		for (String aad : message.getFollowUpAadReference().getAadReferenceCodeList()) { 
                     			writeElement("AadReferenceCode", aad);  
                     		}
                     	 closeElement();
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
