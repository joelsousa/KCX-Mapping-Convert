package com.kewill.kcx.component.mapping.formats.kids.emcs;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgStatusResponse;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : EMCS V20<br>
 * Created	     : 20.07.2011<br>
 * Description   : Body of Kids-EMCSStatusResponse.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyStatusResponseKids extends KidsMessageEMCS {

	private MsgStatusResponse message; 
	private String version = "";         //EI20110701

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSStatusResponse"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());                        
                writeElement("SequenceNumber", message.getSequenceNumber());                          
                //writeCodeElement("Status", message.getStatus(), "A0050");
                writeElement("Status", message.getStatus());
                //writeCodeElement("LastReceivedMessageType", message.getLastReceivedMessageType(), "A0064");
                writeElement("LastReceivedMessageType", message.getLastReceivedMessageType());
                        
           closeElement();                                        
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
}
