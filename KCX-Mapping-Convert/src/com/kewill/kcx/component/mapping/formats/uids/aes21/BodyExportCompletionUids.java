package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Export/aes.<br>
 * Created      : 18.07.2012<br>
 * Description	: Uids body of Export Completion
 * 				: V21 - new tags
 * 				: AES22- new Tag in GoodsItem: StatisticalValueSendFlag
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportCompletionUids extends UidsMessageV21 {	

    private MsgExpEnt message = new MsgExpEnt();
  
    public BodyExportCompletionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpEnt getMessage() {
		return message;
	}

	public void setMessage(MsgExpEnt message) {
		this.message = message;
	}
	
	public void writeBody() {
        try { 
        	openElement("soap:Body");
        	openElement("Submit");                
        		//EI20120917: setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        	 	//EI20120917:
        		if (this.getCommonFieldsDTO() != null && 
        				!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {
        			setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
        		} else {
        			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        		}
        	
        		openElement("Export");
                openElement("Completion");
                	writeElement("AddressCombination", message.getAddressCombination());  //new for V20
                	writeContact(message.getContact());                	
                	writeParty("Consignee", message.getConsignee());
                	writeParty("FinalUser", message.getFinalRecipient());				  //new for V20                		
            		writeParty("Declarant", message.getDeclarant());
                    writeCustomsOffices(message.getCustomsOfficeExport(), message.getCustomsOfficeForCompletion(), "", "");
            		writeStringToDateTime("DateOfAdditionalDeclaration", message.getCompletionTime());
            		writeElement("DocumentReferenceNumber", message.getUCRNumber());
            		writeIncoTerms(message.getIncoTerms());
            		writeElement("LocalReferenceNumber", message.getOrderNumber());
                    writeMeansOfTransportInland(message.getTransportInland());                        
                    writeMeansOfTransport(message.getTransportBorder(), "Border");
                    writeElement("ReferenceNumber", message.getReferenceNumber());                        
                    writeTransaction(message.getBusiness()); 
                                                                         
                    if (message.getGoodsItemList() != null) {                        	
                        for (MsgExpEntPos item : message.getGoodsItemList()) {                         	
                        	writeGoodsItemList(item);
                        }  
                    }
                closeElement(); 
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
	 		writeElement("AddressCombination", item.getAddressCombination());       //new for V20
	 		writeParty("Consignee", item.getConsignee());
	 		writeParty("FinalUser", message.getFinalRecipient());					//new for V20
	 		writeTransaction(item.getBusiness());									//new for V20   
	 		writeIncoTerms(item.getIncoTerms());    								//new for V20    	 	
	 		writeElement("OriginRegion", item.getOriginFederalState());	            	        
	 		if (item.getStatistic() != null) {
	 			writeElement("StatisticalValue", item.getStatistic().getStatisticalValue());
	 			writeElement("c", item.getStatistic().getStatisticalValueSendFlag());  //EI20130808 AES22
	 			writeElement("StatisticalQuantity", item.getStatistic().getAdditionalUnit());
	 			writeElement("StatisticalBaseValue", item.getStatistic().getValue());
	 			writeElement("StatisticalBaseCurrency", item.getStatistic().getCurrency());
	 		}	   	
	 		if (item.getPreviousDocumentList() != null) {    	
	 			//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) {
	 			for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {	 				
	 				//writeItemPreviousDocument(prev, message.getFromFormat());     //EI20120914	
	 				writeItemPreviousDocument21(prev);            				    //EI20130827
    			}
	 		}	 	
	 	closeElement();
	}	

}    	
    	
    	
