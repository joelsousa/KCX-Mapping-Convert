package com.kewill.kcx.component.mapping.formats.uids.emcs;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReportOfReceipt;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReportOfReceiptPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSReportOfReceipt Uids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyReportOfReceiptUids extends UidsMessageEMCS {
	
    private MsgReportOfReceipt  message = new MsgReportOfReceipt();
    private String version = "";         //EI20110701
 
    public BodyReportOfReceiptUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgReportOfReceipt getMessage() {
		return message;
	}

	public void setMessage(MsgReportOfReceipt message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {    
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
	        openElement("EMCS");
	        openElement("EMCSReportOfReceipt"); 
	        
	        if (version.equals("10")) {    //EI20111017
	            writeElement("LocalReferenceNumber", message.getReferenceNumber());
	            writeElement("AadReferenceCode", message.getAadReferenceCode());	 
	            writeElement("SequenceNumber", message.getSequenceNumber());
	            writeElement("DestinationOffice", message.getDestinationOffice());	 
	            // CK20100813 added DateAndTimeOfValidation
	            writeStringToDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());
	            writeStringToDateTime("DateOfArrivalOfExciseProducts", message.getDateOfArrivalOfExciseProducts());
	            writeElement("GlobalConclusionOfReceipt", message.getGlobalConclusionOfReceipt());
	            if (message.getComplementaryInformation() != null) {
	                	writeElementWithAttribute("ComplementaryInformation", 
	                			message.getComplementaryInformation().getText(),
	                    		"language", message.getComplementaryInformation().getLanguage());
	            }
	            writeTrader("ConsigneeTrader", message.getConsignee(), version);              
	            writeTrader("DeliveryPlaceTrader", message.getDeliveryPlace(), version);       
	        } else {
	        	writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeElement("AadReferenceCode", message.getAadReferenceCode());	 
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("DestinationOffice", message.getDestinationOffice());	 
                //V20 - DateTime format changed:
                writeFormattedDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation(),
                		EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	         					 	//EI20111017
                writeFormattedDateTime("DateOfArrivalOfExciseProducts", message.getDateOfArrivalOfExciseProducts(),
                		//EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	   
                		EFormat.KIDS_Date, EFormat.ST_DateTimeTZ);	       //EI20111018
                writeElement("GlobalConclusionOfReceipt", message.getGlobalConclusionOfReceipt());
                if (message.getComplementaryInformation() != null) {
                	writeElementWithAttribute("ComplementaryInformation", 
                			message.getComplementaryInformation().getText(),
                    		"language", message.getComplementaryInformation().getLanguage());
                }
                writeTrader("ConsigneeTrader", message.getConsignee(), version);              
                writeTrader("DeliveryPlaceTrader", message.getDeliveryPlace(), version);      
	        }
	                	                
	           if (message.getGoodsItemList() != null) {                        	                        
                   for (MsgReportOfReceiptPos item : message.getGoodsItemList()) {	                        		
                	   writeGoodsItem(item);
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

	 private void writeGoodsItem(MsgReportOfReceiptPos goodsItem) throws XMLStreamException {
		 
		 //EI20100630: openElement("BodyEaad");	
		 openElement("GoodsItem");	
	      	writeElement("ItemNumber", goodsItem.getItemNumber());
	      	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
	      	writeElement("RefusedQuantity", goodsItem.getRefusedQuantity());
	    	writeElement("IndicatorOfShortageOrExcess", goodsItem.getIndicatorOfShortageOrExcess());
	    	writeElement("ObservedShortageOrExcess", goodsItem.getObservedShortageOrExcess());	    	
	    	writeUnsatisfactoryReasonList(goodsItem.getUnsatisfactoryReasonList());
	    closeElement(); 
	 }	
	 
}
