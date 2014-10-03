package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotification;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotificationPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module		: Export/aes<br>
 * Created		: 24.07.2013<br>
 * Description	: BodyControlNotificationKids. 
* 				: V21: new for Zabis V2.2 (== AES 2.1.00)
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExpControlNotificationKids extends KidsMessageV21 {
	
	private MsgControlNotification message;
	
    public BodyExpControlNotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
         try {
            openElement("soap:Body");
            openElement("ExportControlNotification");
            openElement("GoodsDeclaration");            	
                writeElement("UCRNumber", message.getUCRNumber());   
                writeElement("TimeOfInspection", message.getTimeOfInspection());
                writeElement("KindOfInspection", message.getKindOfInspection());
                writeElement("UCROtherSystem", message.getUCROtherSystem());               
                writeElement("Annotation", message.getAnnotation());
                writeElement("ReferenceNumber", message.getReferenceNumber());                               
                writeContact("Contact", message.getContact());  
                writeElement("RealOfficeOfExit", message.getRealOfficeOfExit());
                writeElement("PreCustomsClearance", message.getPreCustomsClearance());      //EI20130812 
                writeTIN("Forwarder", message.getForwarderTin());     		
                
                if (message.getGoodsItemList() != null) {
                	for (MsgControlNotificationPos item : message.getGoodsItemList()) {
                		writeElement("ItemNumber", item.getItemNumber());
                		writeElement("UCROtherSystem", item.getUcrOtherSystem());              		
                        writeElement("Annotation", item.getAnnotation());
                        writeElement("KindOfInspection", item.getKindOfInspection());                        
                	}                	               
                }
                
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgControlNotification getMessage() {
		return message;
	}

	public void setMessage(MsgControlNotification message) {
		this.message = message;
	}
    
}
