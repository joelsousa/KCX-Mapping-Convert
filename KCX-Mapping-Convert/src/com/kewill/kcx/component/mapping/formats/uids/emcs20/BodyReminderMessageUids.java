package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReminderMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSReminderMessageForExciseMovement Uids message.
 *              : V20 - DateTime format was changed
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyReminderMessageUids extends UidsMessageEMCS20 {
	
    private MsgReminderMessage  message = new MsgReminderMessage();
  
    public BodyReminderMessageUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgReminderMessage getMessage() {
		return message;
	}

	public void setMessage(MsgReminderMessage message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {          	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
             openElement("EMCS");
             openElement("EMCSReminderMessageForExciseMovement"); 
             
                 writeElement("LocalReferenceNumber", message.getReferenceNumber());
                 writeAttributes(message);
                 writeExciseMovementEaad(message.getSequenceNumber(), message.getAadReferenceCode(), "");
                    
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
