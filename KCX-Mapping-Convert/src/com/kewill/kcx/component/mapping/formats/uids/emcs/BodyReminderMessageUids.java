package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReminderMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSReminderMessageForExciseMovement Uids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyReminderMessageUids extends UidsMessageEMCS {
	
    private MsgReminderMessage  message = new MsgReminderMessage();
    private String version = "";         //EI20110701
 
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
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
             openElement("EMCS");
             openElement("EMCSReminderMessageForExciseMovement"); 
             
                 writeElement("LocalReferenceNumber", message.getReferenceNumber());
                 writeAttributes(message, version);
                 writeExciseMovementEaad(message.getSequenceNumber(), message.getAadReferenceCode(), "", version);
                    
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
