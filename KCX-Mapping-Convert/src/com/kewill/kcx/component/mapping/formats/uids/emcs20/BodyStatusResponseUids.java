package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgStatusResponse;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;

 /**
 * Module        : EMCS V20<br>
 * Created	     : 20.07.2011<br>
 * Description   : Body of Uids-EMCSStatusResponse.
 *               : new for EMCS V20
 *              
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyStatusResponseUids extends UidsMessageEMCS20 {
	
	private MsgStatusResponse message; 
	
    public BodyStatusResponseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgStatusResponse getMessage() {
		return message;
	}

	public void setMessage(MsgStatusResponse message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {          	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSStatusResponse"); 
                
                writeElement("LocalReferenceNumber", message.getReferenceNumber());                	
                writeElement("AadReferenceCode", message.getAadReferenceCode());                        
                writeElement("SequenceNumber", message.getSequenceNumber());                          
                writeElement("Status", message.getStatus());
                writeElement("LastReceivedMessageType", message.getLastReceivedMessageType()); 
                            
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
