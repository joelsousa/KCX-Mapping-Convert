package com.kewill.kcx.component.mapping.formats.kids.emcs;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReportOfReceipt;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReportOfReceiptPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 10.05.2010<br>
 * Description	: Body of emcs Kids-ReportOfReceiptKids.
 * 
 * @author krzoska
 * @version 1.0.00
 */


public class BodyReportOfReceiptKids extends KidsMessageEMCS {

	private MsgReportOfReceipt message; 
	private String version = "";         //EI20110701

    public BodyReportOfReceiptKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgReportOfReceipt getMessage() {
		return message;
	}
	public void setMessage(MsgReportOfReceipt argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	           
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
     
            openElement("soap:Body");
            openElement("EMCS");
            openElement("EMCSReportOfReceipt");  
            	writeElement("ReferenceNumber", message.getReferenceNumber());
            	writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk ", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("DestinationOffice", message.getDestinationOffice());
                writeDateTimeToString("DateAndTimeOfValidation", message.getDateAndTimeOfValidation()); 
                writeDateToString("DateOfArrivalOfExciseProducts", message.getDateOfArrivalOfExciseProducts());
                if (version.equalsIgnoreCase("10")) {   
                	writeElement("GlobalConclusionOfReceipt", message.getGlobalConclusionOfReceipt());                	
                } else {
                    //writeCodeElement("GlobalConclusionOfReceipt", message.getGlobalConclusionOfReceipt(), "A0016"); 
                    writeElement("GlobalConclusionOfReceipt", message.getGlobalConclusionOfReceipt()); 
                }   
                if (message.getComplementaryInformation() != null) {   //EI20110921
            		//writeCodeElementWithAttribute("ComplementaryInformation", 
                    //		message.getComplementaryInformation().getText(),
                    //		"C0012", "language", message.getComplementaryInformation().getLanguage());
            		writeElementWithAttribute("ComplementaryInformation", 
                    		message.getComplementaryInformation().getText(),
                    		"language", message.getComplementaryInformation().getLanguage());
            	}
                writeTrader("Consignee", message.getConsignee());
                writeTrader("DeliveryPlace", message.getDeliveryPlace()); //EI20100706
                        
                List<MsgReportOfReceiptPos> list = message.getGoodsItemList();
                if (list != null) {                        	
                   int size = list.size();
                   for (int i = 0; i < size; i++) {	                        		
                	   writeGoodsItem((MsgReportOfReceiptPos) list.get(i));
                   }
               }
           closeElement();  //EMCSReportOfReceipt
           closeElement();	     //Export
           closeElement();	         //soap:Body  
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
    private void writeGoodsItem(MsgReportOfReceiptPos goodsItem) throws XMLStreamException {
    	openElement("GoodsItem");
    	
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	//writeCodeElement("ExciseProductCode", goodsItem.getExciseProductCode(), "C0036");	  
	    	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
	    	writeElement("RefusedQuantity", goodsItem.getRefusedQuantity());	    	
	    	writeElement("IndicatorOfShortageOrExcess", goodsItem.getIndicatorOfShortageOrExcess());
	    	writeElement("ObservedShortageOrExcess", goodsItem.getObservedShortageOrExcess());
	    	
	    	writeUnsatisfactoryReasonList(goodsItem.getUnsatisfactoryReasonList());
	    	
	    closeElement(); //GoodsItem
    }    

}
