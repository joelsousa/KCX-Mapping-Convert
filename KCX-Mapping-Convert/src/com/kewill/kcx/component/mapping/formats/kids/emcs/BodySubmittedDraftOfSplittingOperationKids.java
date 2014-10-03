package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgSplitDetailsPos;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgSubmittedDraftOfSplittingOperation;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.SplitDetailsEAD;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: BodyExportDeclarationKids<br>
 * Created		: 27.07.2011<br>
 * Description	: Body of emcs Kids-EMCSSubmittedDraftOfSplittingOperation.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodySubmittedDraftOfSplittingOperationKids extends KidsMessageEMCS {

	private MsgSubmittedDraftOfSplittingOperation message; 

    public BodySubmittedDraftOfSplittingOperationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgSubmittedDraftOfSplittingOperation getMessage() {
		return message;
	}
	public void setMessage(MsgSubmittedDraftOfSplittingOperation argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("EMCS");              
            openElement("EMCSSubmittedDraftOfSplittingOperation");  
                    
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk ", message.getClerk());
                writeElement("UpstreamARC", message.getUpstreamARC());
                writeElement("MemberStateCode", message.getMemberStateCode());
                if (message.getSplitDetailsEADList() != null) {    	
                	for (SplitDetailsEAD split : message.getSplitDetailsEADList()) {                    		
                		writeSplitDetailsEAD(split);
                	}
                }         
           closeElement();  //EMCSDeclaration
           closeElement();	     //EMCS
           closeElement();	         //soap:Body  
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
    private void writeSplitDetailsEAD(SplitDetailsEAD split) throws XMLStreamException {
    	if (split == null) {
    		return;
    	}
    	openElement("SplitDetailsEAD");
	    	writeElement("ReferenceNumber", split.getReferenceNumber());
	    	writeElement("JourneyTime", split.getJourneyTime());
	    	//writeCodeElement("ChangedTransportArrangement", split.getChangedTransportArrangement(), "AC0070");
	    	writeElement("ChangedTransportArrangement", split.getChangedTransportArrangement());
	    	//writeCodeElement("DestinationTypeCode", split.getDestinationTypeCode(), "A0097");
	    	writeElement("DestinationTypeCode", split.getDestinationTypeCode());
	    	writeElement("DeliveryPlaceCustomsOffice", split.getDeliveryPlaceCustomsOffice());
	    	writeTrader("NewConsignee", split.getNewConsignee());
	    	writeTrader("DeliveryPlace", split.getDeliveryPlace());
	    	writeTrader("NewTransportArranger", split.getNewTransportArranger());
	    	writeTrader("NewTransporter", split.getNewTransporter());
	    	writeTransportDetailsList(split.getTransportDetailsList()); 
	    	if (split.getGoodsItemList() != null) {
	    		for (MsgSplitDetailsPos item : split.getGoodsItemList()) {      
	    			writeGoodsItem(item);
	    		}
	    	}
	    closeElement(); 
    }
    
    private void writeGoodsItem(MsgSplitDetailsPos goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
			return;
		}    	
    	openElement("GoodsItem");
    		writeElement("ItemNumber", goodsItem.getItemNumber());
    		//writeCodeElement("ExciseProductCode", goodsItem.getExciseProductCode(), "C0036");
    		writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
    		//writeCodeElement("CnCode", goodsItem.getCnCode(), "C0037");
    		writeElement("CnCode", goodsItem.getCnCode());
    		writeElement("ArticleNumber", goodsItem.getArticleNumber());
    		if (goodsItem.getCommercialDescription() != null) {
    			//writeCodeElementWithAttribute("CommercialDescription", 
    			//	goodsItem.getCommercialDescription().getText(), "C0012", 
    			//	"language", goodsItem.getCommercialDescription().getLanguage());
    			writeElementWithAttribute("CommercialDescription", 
        				goodsItem.getCommercialDescription().getText(), 
        				"language", goodsItem.getCommercialDescription().getLanguage());
    		}    	
    		if (goodsItem.getBrandNameOfProducts() != null) {
    			//writeCodeElementWithAttribute("BrandNameOfProducts", 
    			//	goodsItem.getBrandNameOfProducts().getText(), "C0012",
  				//	"language", goodsItem.getBrandNameOfProducts().getLanguage());
    			writeElementWithAttribute("BrandNameOfProducts", 
        				goodsItem.getBrandNameOfProducts().getText(), 
      					"language", goodsItem.getBrandNameOfProducts().getLanguage());
    		}
    		//writeCodeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag(), "A0027");    	
    		writeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag());    
    		if (goodsItem.getFiscalMark() != null) {
    			//writeCodeElementWithAttribute("FiscalMark", 
    			//	goodsItem.getFiscalMark().getText(), "C0012",
    			 //   "language", goodsItem.getFiscalMark().getLanguage());
    			writeElementWithAttribute("FiscalMark", 
        				goodsItem.getFiscalMark().getText(), 
        			    "language", goodsItem.getFiscalMark().getLanguage());
    		}
    		writeElement("Quantity", goodsItem.getQuantity());    	
    		writeElement("Density", goodsItem.getDensity());
    		writeElement("NetWeight", goodsItem.getNetWeight());
    		writeElement("GrossWeight", goodsItem.getGrossWeight());
    		writePackageList(goodsItem.getPackageList());   
	    closeElement(); //GoodsItem
    }
}