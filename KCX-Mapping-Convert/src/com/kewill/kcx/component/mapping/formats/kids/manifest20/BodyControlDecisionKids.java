package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgControlDecision;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemControl;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 03.07.2013<br>
 * Description : BodyControlDecisionKids
 * 
 * @author krzoska
 * @version 2.0.00
 *
 */
public class BodyControlDecisionKids extends KidsMessageManifest20 {

	private MsgControlDecision message;	

    public BodyControlDecisionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgControlDecision getMessage() {
		return message;
	}
	
	public void setMessage(MsgControlDecision argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ControlDecision");
            openElement("GoodsDeclaration"); 
            	writeElement("ReferenceNumber", message.getReferenceNumber());
            	writeElement("ReferenceIdentifier", message.getReferenceIdentifier());
                writeElement("RegistrationNumber", message.getRegistrationNumber());    
                writeDateToString("RegistrationDate", message.getRegistrationDate());
                writeDateTimeToString("DateTimeOfControlDecision", message.getDateTimeOfControlDecision());   
            	writeElement("EdifactNumber", message.getEdifactNumber());
            	//writeDateTimeToString("ReceiveDate", message.getReceiveDate()); ReceiveDate unbenannt in DateTimeOfReceipt
            	writeDateTimeToString("DateTimeOfReceipt", message.getDateTimeOfReceipt()); //EI20131021 
                
                if (message.getGoodsItemList() != null) {
                	for (GoodsItemControl item : message.getGoodsItemList()) {
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

    private void writeGoodItem(GoodsItemControl item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	
    	openElement("GoodsItem");
     		writeElement("ItemNumber", item.getItemNumber());
     		writeElement("ItemDescription", item.getItemDescription());
     		writeParty("Custodian", item.getCustodian());     		
     		writePackageList(item.getPackagesList());    		
     		writeReferencedSpecification(item.getReferencedSpecification());     		
    		if (item.getControlDecision() != null && !item.getControlDecision().isEmpty()) {
    			openElement("ControlDecision");
			   		writeElement("ControlDecisionCode", item.getControlDecision().getControlDecisionCode());
			   		writeElement("DeliverableFlag", item.getControlDecision().getDeliverableFlag());
			   		writeElement("AdditionalInformation", item.getControlDecision().getAdditionalInformation());
    			closeElement();
    		}    		
		   
		   	writeLocalApplication(message.getLocalApplication());
    		
        closeElement();  
    }  
    
}

