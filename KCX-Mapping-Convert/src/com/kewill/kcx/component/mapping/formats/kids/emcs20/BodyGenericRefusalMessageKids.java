package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgGenericRefusalMessage;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 18.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSGenericRefusalMessage.
 *              : V20 new: ErrorType with KCX-Code	
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyGenericRefusalMessageKids extends KidsMessageEMCS20 {

	private MsgGenericRefusalMessage message;	
	
    public BodyGenericRefusalMessageKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgGenericRefusalMessage getMessage() {
		return message;
	}
	public void setMessage(MsgGenericRefusalMessage argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");           
            openElement("EMCSGenericRefusalMessage");  
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());  
                writeElement("SequenceNumber", message.getSequenceNumber());                
                writeDateTimeToString("RejectionDateAndTime", message.getRejectionDateAndTime()); 
                writeElement("RejectionReason", message.getRejectionReason());                         
                writeFunctionalErrorList(message.getFunctionalErrorList());  // V20 new: ErrorType with KCX-Code	
                
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}
