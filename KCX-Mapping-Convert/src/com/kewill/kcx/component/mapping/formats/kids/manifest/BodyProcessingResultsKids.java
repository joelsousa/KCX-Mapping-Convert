package com.kewill.kcx.component.mapping.formats.kids.manifest;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest;

/**
 * Module	   : Manifest.<br>
 * Created	   : 12.02.2013<br>
 * Description : BodyGoodsReleasedExternalKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyProcessingResultsKids extends KidsMessageManifest {

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
            	writeElement("FlightNumber", message.getFlightNumber());  
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("RegistrationNumber", message.getRegistrationNumber());     
                writeElement("TypeOfTransaction", message.getTypeOfTransaction());   
                if (message.getHeaderExtensions() != null) {
                	writeHeaderExtensions(message.getHeaderExtensions());
                }
                if (message.getGoodsItemList() != null) {
                	for (ItemProcessingResults item : message.getGoodsItemList()) {
                		writeGoodItem(item);
                	}
                }
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
    		writeElement("CustodianTIN", item.getCustodianTIN());
    		if (item.getCustomsResponse() != null && !item.getCustomsResponse().isEmpty()) {
    			openElement("CustomsResponse");
    			   	writeElement("Type", item.getCustomsResponse().getType());
    			   	writeElement("Pointer", item.getCustomsResponse().getPointer());
    			   	writeElement("Reason", item.getCustomsResponse().getReason());
    			closeElement();
    		}  
    		if (item.getReferencedSpecification() != null && !item.getReferencedSpecification().isEmpty()) {
    			openElement("ReferencedSpecification");
			   	writeElement("TypeOfSpecificationID", item.getReferencedSpecification().getTypeOfSpecificationID());
			   	writeElement("SpecificationID", item.getReferencedSpecification().getSpecificationID());			   
			closeElement();
    		}
        closeElement();
    }  
    
}

