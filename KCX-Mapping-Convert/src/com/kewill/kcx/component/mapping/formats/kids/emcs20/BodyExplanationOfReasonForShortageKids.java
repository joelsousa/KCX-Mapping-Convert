package com.kewill.kcx.component.mapping.formats.kids.emcs20;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOfReasonForShortage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOfReasonForShortagePos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module        : EMCS V20<br>
 * Created       : 20.07.2011<br>
 * Description   : Body of Kids-EMCSExplanationOnReasonForShortage.
 *                : no changes for V20
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyExplanationOfReasonForShortageKids extends KidsMessageEMCS {

	private MsgExplanationOfReasonForShortage message; 
	
    public BodyExplanationOfReasonForShortageKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExplanationOfReasonForShortage getMessage() {
		return message;
	}
	public void setMessage(MsgExplanationOfReasonForShortage argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");               
            //openElement("EMCSExplanationOfReasonForShortage");  
            openElement("EMCSExplanationOnReasonForShortage");      //EI20110927
                    
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeDateTimeToString("DateAndTimeOfValidation",  message.getDateAndTimeOfValidation());
                writeElement("SequenceNumber", message.getSequenceNumber()); 
                writeElement("DispatchImportOffice", message.getDispatchImportOffice()); 
                writeElement("SubmitterType", message.getSubmitterType());                        
                writeDateToString("DateOfAnalysis", message.getDateOfAnalysis());
                if (message.getGlobalExplanation() != null) {
                        	writeElementWithAttribute("GlobalExplanation",
                          	message.getGlobalExplanation().getText(), "language", 
                        	message.getGlobalExplanation().getLanguage());
                }                      
                writeTrader("Consignee", message.getConsignee());                        
                writeTrader("Consignor", message.getConsignor());
                        
                List<MsgExplanationOfReasonForShortagePos> list = message.getGoodsItemList();
                if (list != null) {
                	for (MsgExplanationOfReasonForShortagePos item : list) {	                        		
                    	writeGoodsItem(item);                    		
                	}
                }        
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
    private void writeGoodsItem(MsgExplanationOfReasonForShortagePos goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
    		return;
    	}
    	openElement("GoodsItem");
    	
    	writeElement("ItemNumber", goodsItem.getItemNumber());
    	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
    	writeElement("Quantity", goodsItem.getQuantity());
    	 if (goodsItem.getExplanation() != null) {
         	writeElementWithAttribute("Explanation",
         			goodsItem.getExplanation().getText(),  "language", 
         			goodsItem.getExplanation().getLanguage());
    	 }  
    	
    closeElement();	 
    }
}
