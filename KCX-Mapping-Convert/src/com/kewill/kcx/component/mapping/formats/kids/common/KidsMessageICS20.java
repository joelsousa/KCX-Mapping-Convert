package com.kewill.kcx.component.mapping.formats.kids.common;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDetails;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.StatusInformation;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: KidsMessageICS<br>
 * Created		: 19.10.2012<br>
 * Description	: Fields and methods to write ICS-KidsMessages .
 * 
 * @author Alfred Krzoska	
 * @version 2.0.00
 */

public class KidsMessageICS20 extends KidsMessageICS {

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
	
    public void writePartyTIN(String tag, TIN argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (Utils.isStringEmpty(tag)) {
    	    return;  
    	}
    	if (argument.isEmpty()) {
    	    return;
    	} 
    	
    	openElement(tag + "TIN");
	    	writeElement("TIN", argument.getTIN());
	    	writeElement("BO", argument.getBO());
	    	writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
//	    	writeElement("isTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());
	    	writeCodeElement("IdentificationType", argument.getIdentificationType(), "KCX0069");   //A1340
	    closeElement();
    } 
   
    public void writeText(String tag, Text argument) throws XMLStreamException {   
     	if (argument == null) {
    	    return;
    	}
    	String code = argument.getCode();
     	String text = argument.getText();     
     	String lng  = argument.getLanguage();
     	if (Utils.isStringEmpty(code) && Utils.isStringEmpty(text))  {
     		return;
     	}
     	openElement(tag);
     		writeElement("Code", code); 
     		writeElement("Text", text); 
     		writeElement("Lng", lng);
     	closeElement();   
    }
    
    public void writeGoodsItemShort(GoodsItemShort goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
    		return;
    	}
    	
    	openElement("GoodsItem");             	                    	   
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	writeElement("ShipmentNumber", goodsItem.getShipmentNumber());
	    	
	    	writeText("EntryRejectionMotivation", goodsItem.getMotivation());
	    	if (goodsItem.getMeansOfTransportBorderList() != null) {
		    	for (TransportMeans tm : goodsItem.getMeansOfTransportBorderList()) {
		    		writeTransportMeansType("MeansOfTransportBorder", tm);
		    	}
	    	}
	    	
	    	if (goodsItem.getDocumentList() != null) {
		    	for (IcsDocument document : goodsItem.getDocumentList()) {
			    	openElement("Documents");
			    		writeCodeElement("Type", document.getType(), "KCX0063");
			    		writeElement("Reference", document.getReference());
			    		writeElement("ReferenceLng", document.getReferenceLng());
		    	    closeElement();
		    	}
	    	}
	    	if (goodsItem.getContainersList() != null) {
		    	for (String container : goodsItem.getContainersList()) {
			    	writeElement("Containers", container);
		    	}
	    	}
	    closeElement();
	}
    public void writeGoodsItem(GoodsItemLong goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
    		return;
    	}    	        
    	openElement("GoodsItem");
    	    writeElement("ItemNumber", goodsItem.getItemNumber());
    	    writeElement("ShipmentNumber", goodsItem.getShipmentNumber());
    	    writeElement("Description", goodsItem.getDescription());
    	    writeElement("DescriptionLng", goodsItem.getDescriptionLng());
    	    writeElement("GrossMass", goodsItem.getGrossMass());  
    	    writeCodeElement("DangerousGoodsNumber", goodsItem.getDangerousGoodsNumber(), "KCX0064");   //C0101
    	    writeCodeElement("PaymentType", goodsItem.getPaymentType(), "KCX0068");   //C0116
    	  //EI20130704: zukuenftig wird NL auch ICS machen, aus DXB -> AEDXB, AMS->NLAMS
            if (this.kidsHeader != null && kidsHeader.getCountryCode().equalsIgnoreCase("NL")) {                
            	writeCodeElement("LoadingPlace", goodsItem.getLoadingPlace(), "KCX0101");
            	writeElement("LoadingPlaceLng", goodsItem.getLoadingPlaceLng());     
            	writeCodeElement("UnloadingPlace", goodsItem.getUnloadingPlace(), "KCX0100");                   	
            	writeElement("UnloadingPlaceLng", goodsItem.getUnloadingPlaceLng());                 	
            } else {               
            	writeElement("LoadingPlace", goodsItem.getLoadingPlace());
            	writeElement("LoadingPlaceLng", goodsItem.getLoadingPlaceLng());     
            	writeElement("UnloadingPlace", goodsItem.getUnloadingPlace());
            	writeElement("UnloadingPlaceLng", goodsItem.getUnloadingPlaceLng()); 
            } 
    	    if (goodsItem.getConsignor() != null) {   
    	    		writePartyTIN("Consignor", goodsItem.getConsignor().getPartyTIN());
    	    		writePartyAddress("Consignor", goodsItem.getConsignor());
    	    }
    	    if (goodsItem.getConsignee() != null) {   
    	    		writePartyTIN("Consignee", goodsItem.getConsignee().getPartyTIN());
    	    		writePartyAddress("Consignee", goodsItem.getConsignee());
    	    }
    	    if (goodsItem.getNotifyParty() != null) {   
    	    		writePartyTIN("NotifyParty", goodsItem.getNotifyParty().getPartyTIN());
    	    		writePartyAddress("NotifyParty", goodsItem.getNotifyParty());
    	    }    	    	
    	    if (goodsItem.getContainersList() != null) {
    				for (String container : goodsItem.getContainersList()) {
    					writeElement("Containers", container);   
    				}
    	    }    	    
    	    if (goodsItem.getPackagesList() != null) {
    		    	for (Packages packages : goodsItem.getPackagesList()) {
    		    		writePackage(packages);   
    		    	}    	    	
    	    }    	    
    	    if (goodsItem.getMeansOfTransportBorderList() != null) {
    		    	for (TransportMeans border : goodsItem.getMeansOfTransportBorderList()) {
    		    		writeTransportMeansBorder(border);   
    		    	}
    	    }    	    
    	    writeElement("ArticleNumber", goodsItem.getArticleNumber());       //AK20121019
    	    writeElement("CommodityCode", goodsItem.getCommodityCode());    	    	
    	    if (goodsItem.getDocumentList() != null) {
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

    public void writeEsumaDataReference(EsumaDataReference argument) throws XMLStreamException {   
     	if (argument == null) {
    	    return;
    	}
     	openElement("EsumaDataReference");
     		writeElement("MRN", argument.getMrn());
     		writeElement("CountryOfficeFirstEntry", argument.getCountryOfficeFirstEntry());
     		if (argument.getEsumaDetailsList() != null) {
     			for (EsumaDetails details : argument.getEsumaDetailsList()) {
     				if (details != null) {
     					openElement("EsumaDetails");
     					writeElement("ItemNumberEsuma", details.getItemNumberEsuma());
     					writeElement("OfficeOfFinalDestination", details.getOfficeOfFinalDestination()); //new V20
     					writeElement("CountryOfFinalDestination", details.getCountryOfFinalDestination()); //new V20
	    				closeElement();
     				}
     			}
     		}
     		writeElement("CustomsStatusOfGoods", argument.getCustomsStatusOfGoods());  //new V20
		closeElement();
    }
    
//    public void  writeSealsId(SealNumber argument) throws XMLStreamException {
//       	if (argument == null) {
//    	    return;
//    	} 
//    	if (argument.isEmpty()) {
//    	    return;
//    	}    	    	
//    	openElement("SealsID");    	//new V20: now with SealsID	
//    		writeElement("SealsIdentity", argument.getNumber());
//        	writeElement("SealsIdentityLng",  argument.getLanguage());	
//    	closeElement();    	
//    }  
    
 }


   
