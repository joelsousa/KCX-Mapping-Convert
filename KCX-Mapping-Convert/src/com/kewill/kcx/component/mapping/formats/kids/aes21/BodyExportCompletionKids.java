package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Export/aes.<br>
 * Created      : 10.07.2012<br>
 * Description	: Kids Version 2.1.00: new Tags: in Head-Part: AddressCombination, FinalRecipientTIN, FinalRecipient,
 * 				: and GoodsItem: AddressCombination, Business, IncoTerms, FinalRecipientTIN, FinalRecipient,
* 				: V21: new Tags  
 *              : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportCompletionKids extends KidsMessageV21 {
	
	private MsgExpEnt message;	
	
    public BodyExportCompletionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public MsgExpEnt getMessage() {
		return message;
	}

	public void setMessage(MsgExpEnt argument) {
		this.message = argument;
	}    
    
    public void writeBody() {    	
        try {
            openElement("soap:Body");
            openElement("Completion");
            openElement("GoodsDeclaration");                    
                  writeElement("UCRNumber", message.getUCRNumber());
                  writeDateTimeToString("CompletionTime", message.getCompletionTime());                    	
                  writeElement("ShipmentNumber", message.getShipmentNumber()); 
                  writeElement("ReferenceNumber", message.getReferenceNumber());
                		// Christine Kron 12.08.2010: build up the "DeclarationNumber" 8 digits for application NL                		
                  String refnr = message.getReferenceNumber();
                  int declnum = Utils.getExistingDeclNum(kidsHeader.getReceiver(), refnr);
                  if (declnum != 0) { 
                			message.setDeclarationNumber(declnum);	
                			// Christine Kron 12.08.2010 added because mandatory in NL application
                    		writeElement("DeclarationNumber", message.getDeclarationNumber() + "");
                  }
                  writeElement("OrderNumber", message.getOrderNumber());                                               
                  writeContact("Contact", message.getContact());  
                  writeMeansOfTransport("Inland", message.getTransportInland());                      
                  writeMeansOfTransport("Border", message.getTransportBorder()); 
                  writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());
                  writeElement("CustomsOfficeForCompletion", message.getCustomsOfficeForCompletion());
                  writeBusiness(message.getBusiness());   
                  writeLoadingTime(message.getLoadingTime()); 
                  if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
                	  this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130819
                	  if (message.getDeclarant() != null) {
                	      if (message.getDeclarant().getPartyTIN() != null && !Utils.isStringEmpty(message.getDeclarant().getPartyTIN().getTin()) &&
                	    	  message.getDeclarant().getAddress() != null && !message.getDeclarant().getAddress().isEmpty()) {
                	    	  message.setDeclarant(null);
                	      }
                	  }
                  }
                  writeParty("Declarant", message.getDeclarant());
                  writeParty("Consignee", message.getConsignee());
                  writeIncoTerms(message.getIncoTerms());   
                  if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
                		  this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130819
                	  message.setAddressCombination("2");
                  } 
                  writeElement("AddressCombination", message.getAddressCombination());  //new for V21
                  writeParty("FinalRecipient", message.getFinalRecipient());  			//new for V21
                  
                  if (message.getGoodsItemList() != null) {                        	
                	  for (MsgExpEntPos item : message.getGoodsItemList()) {
                		  writeGoodsItemList(item);
                      }
                  }
                        	
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
    private void writeGoodsItemList(MsgExpEntPos item) throws XMLStreamException {
    	if (item == null) {
    		return;
    	}
    	
    	openElement("GoodsItem");
    		writeElement("ItemNumber", item.getItemNumber());
    		writeCodeElement("OriginFederalState", item.getOriginFederalState(), "KCX0023"); 	    	
    		writeStatistic(item.getStatistic(), "ExpEam");       	    	 
    		writeParty("Consignee", item.getConsignee());
    		writeContainerNumberList(item.getContainers());                  	
    		if (item.getPreviousDocumentList() != null) {     		
    			//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) { 
    			for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {        		     				 
    				//writePreviousDocument(prev, message.getFromFormat()); //EI20120914
    				writePreviousDocument21(prev); //EI20130827
    			} 	
    		}    		 
    		writeBusiness(item.getBusiness());  								//new for V21
    		writeIncoTerms(item.getIncoTerms());    							//new for V21           		 
    		writeElement("AddressCombination", item.getAddressCombination());   //new for V21
    		writeParty("FinalRecipient", item.getFinalRecipient());  			//new for V21        
    	closeElement();
    }         
}

