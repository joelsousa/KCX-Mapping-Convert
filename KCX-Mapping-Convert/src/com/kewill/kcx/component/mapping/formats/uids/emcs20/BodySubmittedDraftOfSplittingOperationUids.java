package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgSplitDetailsPos;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgSubmittedDraftOfSplittingOperation;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ChangedDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.SplitDetailsEAD;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;

/**
 * Module		: EMCS<br>
 * Created		: 27.07.2011<br>
 * Description	: Body of Uids-EMCSSubmittedDraftOfSplittingOperation.
 *              : new for EMCS V20
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodySubmittedDraftOfSplittingOperationUids extends UidsMessageEMCS20  {
	
    private MsgSubmittedDraftOfSplittingOperation   message  = new MsgSubmittedDraftOfSplittingOperation();
   
    public BodySubmittedDraftOfSplittingOperationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgSubmittedDraftOfSplittingOperation getMessage() {
		return message;
	}

	public void setMessage(MsgSubmittedDraftOfSplittingOperation message) {
		this.message = message;
	}
    
	public void writeBody() {		
        try {          	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSSubmittedDraftOfSplittingOperation"); 
            
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());                	
                writeElement("UpstreamARC", message.getUpstreamARC());
                writeElement("MemberStateCode", message.getMemberStateCode());                        
                if (message.getSplitDetailsEADList() != null) {
                    for (SplitDetailsEAD split : message.getSplitDetailsEADList()) {                    		
                    	writeSplitDetailsEAD(split);
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

	private void writeSplitDetailsEAD(SplitDetailsEAD split) throws XMLStreamException {
		if (split == null) {                   //EI20110926
			return;
		}
    	openElement("SplitDetailsEAD");
	    	//EI20111116:writeElement("ReferenceNumber", split.getReferenceNumber());
    		writeElement("LocalReferenceNumber", split.getReferenceNumber());  //EI20111116
	    	writeElement("JourneyTime", split.getJourneyTime());
	    	writeElement("ChangedTransportArrangement", split.getChangedTransportArrangement());
	    	writeChangedDestination(split);	    	
	    	writeTrader("NewTransportArrangerTrader", split.getNewTransportArranger());
	    	writeTrader("NewTransporterTrader", split.getNewTransporter());
	    	writeTransportDetailsList(split.getTransportDetailsList()); 
	    	
	    	if (split.getGoodsItemList() != null) {  //EI20110926
	    		for (MsgSplitDetailsPos item : split.getGoodsItemList()) {      
	    			writeGoodsItem(item);
	    		}
	    	}
	    closeElement(); 
    }
	
	private void writeChangedDestination(SplitDetailsEAD split) throws XMLStreamException {
		if (split == null) {
			return;
		}
    	ChangedDestination temp = new ChangedDestination();
	   	
	   	temp.setDestinationTypeCode(split.getDestinationTypeCode()); 
		temp.setDeliveryPlaceCustomsOffice(split.getDeliveryPlaceCustomsOffice());
	   	temp.setNewConsignee(split.getNewConsignee());
	   	temp.setDeliveryPlace(split.getDeliveryPlace());
		
	   	writeChangedDestination(temp);
	}
	
	private void writeGoodsItem(MsgSplitDetailsPos goodsItem) throws XMLStreamException {
		if (goodsItem == null) {
			return;
		}
		
    	openElement("BodyEaad");    	
    		writeElement("BodyRecordUniqueReference", goodsItem.getItemNumber());
    		writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
    		writeElement("CnCode", goodsItem.getCnCode());
    		//writeElement("ArticleNumber", goodsItem.getArticleNumber());
    		if (goodsItem.getCommercialDescription() != null) {
    			writeElementWithAttribute("CommercialDescription", 
    				goodsItem.getCommercialDescription().getText(), 
    				"language", goodsItem.getCommercialDescription().getLanguage());
    		}    	
    		if (goodsItem.getBrandNameOfProducts() != null) {
    			writeElementWithAttribute("BrandNameOfProducts", 
    				goodsItem.getBrandNameOfProducts().getText(), 
  					"language", goodsItem.getBrandNameOfProducts().getLanguage());
    		}
    		writeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag());    	
    		if (goodsItem.getFiscalMark() != null) {
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
