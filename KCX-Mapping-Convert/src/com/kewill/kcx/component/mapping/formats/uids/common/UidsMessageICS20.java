package com.kewill.kcx.component.mapping.formats.uids.common;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDetails;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.StatusInformation;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS Version 2.0<br>
 * Created		: 23.10.2012<br>
 * Description	: Fields and methods to write ICS-UidsMessages Version 20.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class UidsMessageICS20 extends UidsMessageICS {
	
	public void writeTrader(String tag, Party party) throws XMLStreamException {
		if (party == null) {
    	    return;
    	}
		if (party.isEmpty()) {
    	    return;
    	}
		
		openElement(tag);		  
//   Not used in V20		
//			if (party.getPartyTIN() != null) {	
//				writeElement("CustomerID", party.getPartyTIN().getCustomerIdentifier());
//				writeElement("CustomsID", party.getPartyTIN().getIsTINGermanApprovalNumber());				
//			}
//			writeElement("ETNAddress", party.getETNAddress());			
			if (party.getPartyTIN() != null) {			
				writeElement("TIN", party.getPartyTIN().getTIN());
				writeElement("Branch", party.getPartyTIN().getBO());
				writeElement("TINType", party.getPartyTIN().getIdentificationType()); //EI20110119
			}
//			writeElement("VATID", party.getVATNumber());
			
			writeAddress("Address", party.getAddress());			
			writeContact("Contact", party.getContactPerson());						
        closeElement();
	}
      
	public void writeStatusInformation(StatusInformation status) throws XMLStreamException {
		if (status == null) {
    	    return;
    	}
		if (status.isEmpty()) {
    	    return;
    	}
		
		openElement("StatusInformation");		  			
			writeElement("Status", status.getStatus());
			writeFormattedDateTime("StatusDateAndTime", status.getStatusDateAndTime(),
					status.getStatusDateAndTimeFormat(), EFormat.ST_DateTimeTZ);						
        closeElement();
	}
	/* in ICS20 wird nicht mehr verwendet
	public Party setPartyFromTrader(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party  = new Party();
		
		TIN	tin = new TIN();
		tin.setTIN(trader.getTIN());
		tin.setIsTINGermanApprovalNumber(trader.getCustomsID());
		tin.setCustomerIdentifier(trader.getCustomerID());	
		tin.setIdentificationType(trader.getTINType());   
		
		party.setPartyTIN(tin);		
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());
		party.setContactPerson(trader.getContactPerson());
		
		return party;		
	}
    */
	public void writeGoodsItem(GoodsItemLong goodsItem, String msg, String shipmentNumber) throws XMLStreamException {

    	if (goodsItem == null) {
    		return;
    	}

    	openElement("GoodsItem");
    	writeElement("ItemNumber", goodsItem.getItemNumber());
    	writeElement("CommercialReferenceNumber", goodsItem.getShipmentNumber());
    	writeElement("GoodsDescription", goodsItem.getDescription());
    	writeElement("GrossMass", goodsItem.getGrossMass());
    	writeElement("UNDGNumber", goodsItem.getDangerousGoodsNumber());
    	writeElement("TransportMethodOfPayment", goodsItem.getPaymentType());
    	writeElement("PlaceOfLoading", goodsItem.getLoadingPlace());
    	writeElement("PlaceOfUnloading", goodsItem.getUnloadingPlace()); 		
    	writeTrader("Consignor", goodsItem.getConsignor());    		
    	writeTrader("Consignee", goodsItem.getConsignee());    	
    	writeTrader("NotifyParty", goodsItem.getNotifyParty());    	
    	writeElement("CommodityCodeKN8", goodsItem.getCommodityCode());

    	if (goodsItem.getContainersList() != null) {
    		for (String container : goodsItem.getContainersList()) {
    			writeElement("ContainerNumber", container);
    		}
    	}
    	if (goodsItem.getMeansOfTransportBorderList() != null) {
    		for (TransportMeans transport : goodsItem.getMeansOfTransportBorderList()) {
    			writeTransport("MeansOfTransportAtBorder", transport);
    		}
    	}
    	if (goodsItem.getPackagesList() != null) {
    		for (Packages packages : goodsItem.getPackagesList()) {
    			writePackaging(packages);
    		}
    	}
    	if (goodsItem.getDocumentList() != null) {
    		writeN741(goodsItem.getDocumentList(), shipmentNumber);   //EI20111010
    		for (IcsDocument document : goodsItem.getDocumentList()) {    			
    			writeDocument(document);    			
    		}
    	}
    	if (goodsItem.getSpecialMentionList() != null) {
    		for (SpecialMention special : goodsItem.getSpecialMentionList()) {
    			writeSpecialMention(special);
    		}
    	}

    	closeElement();
    }
	
	public void writeGoodsItemShort(GoodsItemShort goodsItem, String msgName) throws XMLStreamException {
		
		openElement("GoodsItem");
	    	writeElement("ItemNumber", goodsItem.getItemNumber());  
	    	writeElement("CommercialReferenceNumber", goodsItem.getShipmentNumber()); 	    	
			if (goodsItem.getContainersList() != null) {
				for (String container : goodsItem.getContainersList()) {
					writeElement("ContainerNumber", container);
				}
			}	
	    	if (goodsItem.getMeansOfTransportBorderList() != null) {
		    	for (TransportMeans tm : goodsItem.getMeansOfTransportBorderList()) {
		    		writeTransport("MeansOfTransportAtBorder", tm);
		    	}
	    	}	    	
	    	if (goodsItem.getDocumentList() != null) {
		    	for (IcsDocument document : goodsItem.getDocumentList()) {
		    		writeDocument(document);
		    	}
	    	}
	    	if (msgName != null && (msgName.equals("IE322") || msgName.equals("IE361") )) {
	    		writeText("Motivation", goodsItem.getMotivation()); 
	    	}
	    closeElement();
	}

	public void writeEsumaDataReference(EsumaDataReference argument, String destinationCountry) throws XMLStreamException {   
		if (argument == null) {
			return;
		}
 		openElement("CustomsDataReference");
 			writeElement("MRN", argument.getMrn()); 	
 			boolean mrndetails = false;
 			if (argument.getEsumaDetailsList() != null) {
 				for (EsumaDetails details : argument.getEsumaDetailsList()) { 					
 					if (details != null) {
 						mrndetails = true;
 						openElement("MRNDetail");
 							writeElement("MRNItemNumber", details.getItemNumberEsuma());
 							writeElement("OfficeOfFinalDestination", details.getOfficeOfFinalDestination()); //new V20
 							writeElement("CountryOfFinalDestination", details.getCountryOfFinalDestination()); //new V20
 						closeElement();
 					}
 				}
 				// CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!! 		    	
 				if (!mrndetails && destinationCountry.equals("FR")) {
 		    		openElement("MRNDetail");
 		    			writeElement("MRNItemNumber", "1");                        			
 					closeElement();
 		    	}  //CK-end
 			} else { 			
 		        // CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!! 				
 				if (destinationCountry.equals("FR")) {
 					openElement("MRNDetail");			    			
 						writeElement("MRNItemNumber", "1");
 					closeElement();
 				}
 			}
 			writeElement("OriginalCountryOfFirstEntry", argument.getCountryOfficeFirstEntry());
 			writeElement("CustomsStatusOfGoods", argument.getCustomsStatusOfGoods());  //new V20 		
 			// CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!!
 			if (destinationCountry.equals("FR") && Utils.isStringEmpty(argument.getCustomsStatusOfGoods())) {
 				writeElement("CustomsStatusOfGoods", "T");
 			}  //CK-end 			
 		closeElement();
	}	
	
	public void  writeText(String tag, Text argument) throws XMLStreamException {
		if (argument == null) {
			return;
	    } 
	    if (argument.isEmpty()) {
	    	return;
	    }    	    	
	    openElement(tag);    		    	
			writeElement("Code", argument.getCode()); 
			writeElement("Text", argument.getText()); 
		closeElement();            	
	 	
	    }  
	 
}
