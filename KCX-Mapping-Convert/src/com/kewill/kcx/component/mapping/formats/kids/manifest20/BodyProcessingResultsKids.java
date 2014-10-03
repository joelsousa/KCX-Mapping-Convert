package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Notification;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 27.06.2013<br>
 * Description : BodyGoodsReleasedExternalKids
 * 
 * @author krzoska
 * @version 2.0.00
 *
 */
public class BodyProcessingResultsKids extends KidsMessageManifest20 {

	private MsgProcessingResults message;	

    public BodyProcessingResultsKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgProcessingResults getMessage() {
		return message;
	}
	
	public void setMessage(MsgProcessingResults argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ProcessingResults");
            openElement("GoodsDeclaration");             	          
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("RegistrationNumber", message.getRegistrationNumber());                  
                writeDateToString("RegistrationDate", message.getRegistrationDate());                  
                writeElement("TypeOfTransaction", message.getTypeOfTransaction());   
                writeTransport("MeansOfTransport", message.getTransport());
                writeDateToString("DateOfArrival", message.getDateOfArrival()); 
                writeElement("EdifactNumber", message.getEdifactNumber());   
                if (message.getHeaderExtensions() != null) {
                	writeHeaderExtensions(message.getHeaderExtensions());
                }
                writeDateTimeToString("DateTimeOfReceipt", message.getDateTimeOfReceipt());  //EI20131021
                if (message.getNotificationList() != null) {    //EI20130703
                	for (Notification noti : message.getNotificationList()) {
                		writeNotification(noti);
                	}
                }
                if (message.getGoodsItemList() != null) {
                	for (ItemProcessingResults item : message.getGoodsItemList()) {
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

    private void writeGoodItem(ItemProcessingResults item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	
    	if (item.isEmpty()) {
    	    return; 
     	}     	
     	openElement("GoodsItem");
     		writeElement("ItemNumber", item.getItemNumber());
     		writePartyTIN("Custodian", item.getCustodianTIN());
     		writeItemCustomsResponse(item.getCustomsResponse());     		
			writeReferencedSpecification(item.getReferencedSpecification());					
            writeNotification(item.getNotification());   //EI20130703                	
        closeElement();
    }  
    
}

