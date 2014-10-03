package com.kewill.kcx.component.mapping.formats.uids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 28.08.2013<br>
 * Description : BodyGoodsReleasedExternalUids
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyProcessingResultsUids extends UidsMessageManifest20 {

	private MsgProcessingResults message;	

    public BodyProcessingResultsUids(XMLStreamWriter writer) {
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
            	if (message.getTransport() != null) {
            		writeElement("FlightNumber", message.getTransport().getTransportationNumber());  
            	}
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
     		if (item.getCustodianTIN() != null) {
     			writeElement("CustodianTIN", item.getCustodianTIN().getTin());
     		}
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

