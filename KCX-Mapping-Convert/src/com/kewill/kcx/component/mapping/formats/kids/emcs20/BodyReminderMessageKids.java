package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReminderMessage;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: EMCS.<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSReminderMessage,
 *              : no changes for V20
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyReminderMessageKids extends KidsMessageEMCS {

	private MsgReminderMessage msgReminder;	

    public BodyReminderMessageKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgReminderMessage getMessage() {
		return msgReminder;
	}
	public void setMessage(MsgReminderMessage argument) {
		this.msgReminder = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSReminderMessage");  
                    
            	writeElement("ReferenceNumber", msgReminder.getReferenceNumber());
                writeElement("CustomerOrderNumber", msgReminder.getCustomerOrderNumber());
                writeElement("Clerk", msgReminder.getClerk()); 
                writeElement("AadReferenceCode", msgReminder.getAadReferenceCode());  
                writeElement("SequenceNumber", msgReminder.getSequenceNumber());     
                writeDateTimeToString("DateAndTimeOfIssuance", msgReminder.getDateAndTimeOfIssuance());
                writeDateTimeToString("LimitDateAndTime", msgReminder.getLimitDateAndTime());
                if (msgReminder.getReminderInformation() != null) {
                    writeElementWithAttribute("ReminderInformation", 
                        			msgReminder.getReminderInformation().getText(),
                        			"language", msgReminder.getReminderInformation().getLanguage());
                }
                writeElement("ReminderMessageType", msgReminder.getReminderMessageType());
                        
           closeElement();
           closeElement();	    
           closeElement();	
           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}
