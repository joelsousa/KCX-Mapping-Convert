package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemCompletion;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 04.07.2013<br>
 * Description : BodyNotificationOfCompletionKids
 * 
 * @author krzoska
 * @version 2.0.00
 *
 */
public class BodyNotificationOfCompletionKids extends KidsMessageManifest20 {

	private MsgNotificationOfCompletion message;	

    public BodyNotificationOfCompletionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgNotificationOfCompletion getMessage() {
		return message;
	}
	
	public void setMessage(MsgNotificationOfCompletion argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("NotificationOfCompletion");
            openElement("GoodsDeclaration"); 
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("RegistrationNumber", message.getRegistrationNumber());   
                writeDateToString("RegistrationDate", message.getRegistrationDate());  
                writeElement("TypeOfTransaction", message.getTypeOfTransaction());
                writeElement("RegistrationNumberOfCompletion", message.getRegistrationNumberOfCompletion());
                writeTransport("MeansOfTransport", message.getTransport());                
                writeDateToString("DateOfArrival", message.getDateOfArrival());  
                writeReference(message.getReference());                
            	writeElement("EdifactNumber", message.getEdifactNumber());
            	writeDateToString("DateTimeOfReceipt", message.getDateTimeOfReceipt());
                    
                if (message.getGoodsItemList() != null) {
                	for (GoodsItemCompletion item : message.getGoodsItemList()) {
                		writeGoodItem(item);
                	}
                }
                
                writeLocalApplication(message.getLocalApplication());
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

    private void writeGoodItem(GoodsItemCompletion item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	
    	openElement("GoodsItem");
     		writeElement("ItemNumber", item.getItemNumber());
     		writeElement("RegistrationNumber", item.getRegistrationNumber());
     		writeDateToString("DateOfCompletion", item.getDateOfCompletion());
     		writeContact(item.getContact());
     		writePartyTIN("Custodian", item.getCustodianTIN());     		
     		writePackageList(item.getPackagesList());     		
     		writeReferencedSpecification(item.getReferencedSpecification());    		
    		writeElement("CancellationCode", item.getCancellationCode());     		
    		
        closeElement();  //GoodsItem
    }  
    
}

