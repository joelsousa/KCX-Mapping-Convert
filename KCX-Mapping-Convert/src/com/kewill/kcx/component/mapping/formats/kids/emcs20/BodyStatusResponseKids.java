package com.kewill.kcx.component.mapping.formats.kids.emcs20;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgStatusResponse;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module        : EMCS V20<br>
 * Created	     : 20.07.2011<br>
 * Description   : Body of Kids-EMCSStatusResponse.
 *               : no changes for V20
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyStatusResponseKids extends KidsMessageEMCS {

	private MsgStatusResponse message; 
	
    public BodyStatusResponseKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgStatusResponse getMessage() {
		return message;
	}
	public void setMessage(MsgStatusResponse argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSStatusResponse"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());                        
                writeElement("SequenceNumber", message.getSequenceNumber());                          
                writeElement("Status", message.getStatus());
                writeElement("LastReceivedMessageType", message.getLastReceivedMessageType());
                        
           closeElement();                                        
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
}
