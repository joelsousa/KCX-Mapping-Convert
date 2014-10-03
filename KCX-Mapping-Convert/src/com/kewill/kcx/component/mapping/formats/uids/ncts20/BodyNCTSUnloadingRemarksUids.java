package com.kewill.kcx.component.mapping.formats.uids.ncts20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.UnloadingRemark;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingRemarksPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSUnloadingRemarksUids<br>
 * Created		: 09.02.2013<br>
 * Description	: Contains message structure for NCTSUnloadingRemarks UIDS. 
 *              : TODO - erstmal nur kopiert von V 4.0.00
 *              
 * @author iwaniuk
 * @version 4.1.00
 */

public class BodyNCTSUnloadingRemarksUids extends UidsMessageNCTS20 {
	private MsgNCTSUnloadingRemarks message;
	
	public BodyNCTSUnloadingRemarksUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgNCTSUnloadingRemarks getMessage() {
		return message;
	}
	
	public void setMessage(MsgNCTSUnloadingRemarks argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSUnloadingRemarks");
			
				writeElement("MRN", message.getmRN());
				writeTransportAtDeparture("TransportAtDeparture", message.getTransportAtDeparture());
				writeElement("TotalNumberOfItems", message.getTotalNumberOfItems());
				writeElement("TotalNumberOfPackages", message.getTotalNumberOfPackages());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				writeNCTSTrader("DestinationTrader", message.getDestinationTrader());
				writeElement("OfficeOfPresentation", message.getOfficeOfPresentation());
				writeUnloadingRemark("UnloadingRemark", message.getUnloadingRemark());
				writeResultsOfControl(message.getResultsOfControl());
				writeElement("TotalNumberOfSeals", message.getTotalNumberOfSeals());
				writeSealNumberList(message.getListSealNumbers());
				writeEnRouteEvent(message.getEnRouteEvent());	
				
				if (message.getGoodsItemList() != null) {  
					for (MsgNCTSUnloadingRemarksPos item : message.getGoodsItemList()) {
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
		
	public void writeUnloadingRemark(String tag, UnloadingRemark argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
    		writeElement("StateOfSealsOk", argument.getStateOfSealsOk());
	    	writeElement("Remarks", argument.getUnloadingRemark());
	    	writeElement("Conform", argument.getConform());
	    	writeElement("UnloadingCompletion", argument.getUnloadingCompletion());	    	
	    	writeFormattedDateOrTime("UnloadingDate", argument.getUnloadingDate(), 
	    			argument.getUnloadingDateFormat(),	EFormat.ST_Date);	   	    	
	    closeElement();
    }	
		
	public void writeGoodsItem(MsgNCTSUnloadingRemarksPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement("GoodsItem");      
    	
    		writeElement("ItemNumber", argument.getItemNumber());
    		if (argument.getCommodityCode() != null) {
    			writeElement("CommodityCodeKN8", argument.getCommodityCode().getTarifCode());
    		}
    		writeElement("GoodsDescription", argument.getGoodsDescription());
    		writeElement("GrossMass", argument.getGrossMass());
    		writeElement("NetMass", argument.getNetMass());
    		writeElement("MissingLineFlag", argument.getMissingLineFlag());
    		writeProducedDocumentsList(argument.getListProducedDocuments());		
    		writeContainerList(argument.getContainer());	    		
    		writeResultsOfControl(argument.getResultsOfControl());
    		writePackagesList(argument.getListPackages());
    		writeSGICodesList(argument.getSensitiveGoodsList());	
    		
	    closeElement();
    }
	
}
