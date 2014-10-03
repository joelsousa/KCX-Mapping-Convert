package com.kewill.kcx.component.mapping.formats.kids.common;

import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ConveyanceCall;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: KidsMessageICS<br>
 * Created		: 09.06.2010<br>
 * Description	: Fields and methods to write ICS-KidsMessages .
 * 
 * @author Pete T
 * @version 1.0.00
 */

public class KidsMessageICS extends KidsMessage {
   
    public void writePartyAddress(String tag, Party party) throws XMLStreamException {
    	if (party == null) {
    	    return;
    	}
    	if (party.isEmpty()) {    		
    	    return;
    	} 
    	if (Utils.isStringEmpty(party.getVATNumber()) && Utils.isStringEmpty(party.getETNAddress())
    		&& (party.getAddress()== null || (party.getAddress()!= null && party.getAddress().isEmpty()))) {
        	    return;
        }        	
    	openElement(tag + "Address"); 
            writeElement("VATNumber", party.getVATNumber());
            writeElement("ETNAddress", party.getETNAddress());
        	writeAddress(party.getAddress());
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
	    	writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
//	    	writeElement("isTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());
	    	writeCodeElement("IdentificationType", argument.getIdentificationType(), "KCX0069");   //A1340
	    closeElement();
    } 
    
 
    
    public void writeContactPerson(String tag, ContactPerson contact) throws XMLStreamException {
    	if (contact == null) {
    	    return;
    	}    	
    	if (contact.isEmpty()) {    		
    	    return;
    	}    	
    	openElement(tag + "Contact");             	                    	   
    		writeElement("Position", contact.getPosition()); 
	    	writeElement("Clerk", contact.getClerk());
	    	writeElement("Identity", contact.getIdentity()); 
	    	writeElement("Email", contact.getEmail());
	    	writeElement("FaxNumber", contact.getFaxNumber());
	    	writeElement("PhoneNumber", contact.getPhoneNumber());
	    	writeElement("Initials", contact.getInitials()); 
	    	writeElement("Title", contact.getTitle()); 
	    closeElement();
    } 

    public void writeFormattedDateOrTime(String tag, String dateOrTimeString, EFormat currentFormat,
    		EFormat resultFormat) throws XMLStreamException {
    	KcxDateTime kcx;
    	if (dateOrTimeString == null || Utils.isStringEmpty(dateOrTimeString)) {
    		return;
    	}
    	if (currentFormat == null || resultFormat == null) {
    		writeDateTimeToString(tag, dateOrTimeString);
    		return;
    	}
    	if (currentFormat == resultFormat) {
    		writeElement(tag, dateOrTimeString);
    		return;
    	}
		try {
			kcx = new KcxDateTime(currentFormat, dateOrTimeString);
	    	writeElement(tag, kcx.format(resultFormat)); 
		} catch (ParseException e) {
            Utils.log(Utils.LOG_ERROR, String.format(
            		"Date/Time string '%s' for element '%s' could not be processed for output - %s",
            		dateOrTimeString,
            		tag,
            		e.getLocalizedMessage()));
		}
    }
    public void writeFormattedDateTime(String tag, String dateTimeString, EFormat currentFormat,
    											EFormat resultFormat) throws XMLStreamException {
    	if (dateTimeString == null || Utils.isStringEmpty(dateTimeString)) {
    		return;
    	}
    	if (currentFormat == null || resultFormat == null) {
    		writeDateTimeToString(tag, dateTimeString);
    	} else {
    		writeFormattedDateOrTime(tag, dateTimeString, currentFormat, resultFormat);
    	}
    }
    public void writeFormattedDate(String tag, String dateString, EFormat currentFormat,
					EFormat resultFormat) throws XMLStreamException {
    	if (dateString == null || Utils.isStringEmpty(dateString)) {
    		return;
    	}
    	if (currentFormat == null || resultFormat == null) {
    		writeDateToString(tag, dateString);
    	} else {
    		writeFormattedDateOrTime(tag, dateString, currentFormat, resultFormat);
    	}
    }
    public void writeFormattedTime(String tag, String timeString, EFormat currentFormat,
			EFormat resultFormat) throws XMLStreamException {
    	if (timeString == null || Utils.isStringEmpty(timeString)) {
    		return;
    	}
    	if (currentFormat == null || resultFormat == null) {
    		writeTimeToString(tag, timeString);
    	} else {
    		writeFormattedDateOrTime(tag, timeString, currentFormat, resultFormat);
    	}
    }
    /*
    public void writeGoodsItem(String tag, GoodsItem goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
			return;
		}

    	if (Utils.isStringEmpty(goodsItem.getCountryOfficeFirstEntry())
    			&& Utils.isStringEmpty(goodsItem.getItemNumber())
    			&& Utils.isStringEmpty(goodsItem.getMrn())
    			&& Utils.isStringEmpty(goodsItem.getReference())
    			&& Utils.isStringEmpty(goodsItem.getReferenceLng())
    			&& Utils.isStringEmpty(goodsItem.getType())) {
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	openElement("Document");
	    		writeElement("Type", goodsItem.getType());	//TODO get and use KCX equivalent for C0018 code list
	    		writeElement("Reference", goodsItem.getReference());
	    		writeElement("ReferenceLng", goodsItem.getReferenceLng());
    	    closeElement();
    	    
    	    if (!Utils.isStringEmpty(goodsItem.getMrn())
    	    		|| !Utils.isStringEmpty(goodsItem.getCountryOfficeFirstEntry())
    	    		|| !goodsItem.getItemNumberEsumaList().isEmpty()) {
    	    	
    	    	openElement("EsumaDataReference");
		    		writeElement("MRN", goodsItem.getMrn());
		    		writeElement("CountryOfficeFirstEntry", goodsItem.getCountryOfficeFirstEntry());
		    		
		    		for (String itemNumberEsuma : goodsItem.getItemNumberEsumaList()) {
    	    			openElement("EsumaDetails");
    		    			writeElement("ItemNumberEsuma", itemNumberEsuma);
			    	    closeElement();
		    		}
	    	    closeElement();
    	    }
    	    
	    closeElement();
	}    
    */
	public void writeGoodsItemShort(GoodsItemShort goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
    		return;
    	}
    	
    	openElement("GoodsItem");             	                    	   
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	writeElement("ShipmentNumber", goodsItem.getShipmentNumber());
	    	
	    	if (goodsItem.getContainersList() != null) {
		    	for (String container : goodsItem.getContainersList()) {
			    	writeElement("Containers", container);
		    	}
	    	}
	    	
	    	if (goodsItem.getMeansOfTransportBorderList() != null) {
		    	for (TransportMeans tm : goodsItem.getMeansOfTransportBorderList()) {
		    		writeTransportMeansType("MeansOFTransportBorder", tm);	//TODO check capital F in MeansOFTransportBorder with Daggi
		    	}
	    	}
	    	
	    	if (goodsItem.getDocumentList() != null) {
		    	for (IcsDocument document : goodsItem.getDocumentList()) {
			    	openElement("Document");
			    		writeCodeElement("Type", document.getType(), "KCX0063");
			    		writeElement("Reference", document.getReference());
			    		writeElement("ReferenceLng", document.getReferenceLng());
		    	    closeElement();
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

    public void writeTransportMeansType(String tag, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	
    	openElement(tag);             	                    	   
	    	writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");  //C0018
	    	writeElement("TransportationType", argument.getTransportationType());	    	
	    	//EI20110105: writeDateTimeToString("TransportTime", argument.getTransportTime());
	    	writeFormattedDateTime("TransportTime", argument.getTransportTime(),
	    			argument.getTransportTimeFormat(), EFormat.KIDS_DateTime);	    
	    	writeElement("TransportationNumber", argument.getTransportationNumber());
	    	writeElement("TransportationCountry", argument.getTransportationCountry());  //I0803
	    	writeElement("PlaceOfLoading", argument.getPlaceOfLoading());
	    	writeElement("PlaceOfLoadingCode", argument.getPlaceOfLoadingCode()); 
	    closeElement();
    }
    
    public void writeTransportMeansBorder(TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	
    	openElement("MeansOfTransportBorder");             	                    	   
	    	writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");     //C0018 	
	    	writeElement("TransportationNumber", argument.getTransportationNumber());      
	    	writeElement("TransportationCountry", argument.getTransportationCountry());    //I0803		    	 
	    closeElement();
    }    
    
    public void  writeSealsId(SealNumber argument) throws XMLStreamException {
       	if (argument == null) {
    	    return;
    	} 
    	if (argument.isEmpty()) {
    	    return;
    	}    	    	
    	// openElement("SealsID");    		
    			writeElement("SealsIdentity", argument.getNumber());
        		writeElement("SealsIdentityLng",  argument.getLanguage());	
    	// closeElement();    	
    }  
  
    public void  writeConveyanceCall(ConveyanceCall argument) throws XMLStreamException {  //EI20121004 JIRA: KCXSM-34
       	if (argument == null) {
    	    return;
    	} 
    	if (argument.isEmpty()) {
    	    return;
    	}    	
    	
    	openElement("ConveyanceCall");
    		writeElement("PurposeOfCall", argument.getPurposeOfCall());
    		writeElement("NumberOfCrew", argument.getNumberOfCrew());
        	writeElement("NumberOfPassengers", argument.getNumberOfPassengers());
        	writeElement("PositionOfTheShipOrAirplaneInPort", argument.getPositionOfTheShipOrAirplaneInPort());
    	closeElement();    	
    }  
	
    public void writePackage(Packages argument) throws XMLStreamException {    	if (argument == null) {
    	    return;
    	}
       	if (argument.isEmpty()) {
    	    return;
    	}        	
       	openElement("Packages");  
       		//writeCodeElement("Type", argument.getType(), "C0017");
       		writeCodeElement("Type", argument.getType(), "KCX0064");
       		writeElement("Marks", argument.getMarks());
       		writeElement("MarksLng", argument.getMarksLng());
       		writeElement("Number", argument.getSequentialNumber());
    		writeElement("Quantity", argument.getQuantity());   
    	closeElement();		    		    
    } 
    public void writeDocument(IcsDocument argument) throws XMLStreamException {    
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}        	
    	openElement("Document");     			
    		writeCodeElement("Type", argument.getType(), "KCX0063");   //C0013
   			writeElement("Reference", argument.getReference());
   			writeElement("ReferenceLng", argument.getReferenceLng());   			
		closeElement();		    		    
    }
    public void writeSpecialMention(SpecialMention argument) throws XMLStreamException {    
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}        	
    	openElement("SpecialMentions");   
    		//writeCodeElement("code", argument.getCode(), "C0039");
    		writeCodeElement("Code", argument.getCode(), "KCX0066");
		closeElement();		    		    
    } 	
    
    public void writeFunctionalError(String tag, FunctionalError error) throws XMLStreamException {
		if (error == null) {
			return;
		}
    	openElement(tag);             	                    	   
	    	writeElement("ErrorType", error.getErrorType());
	    	writeElement("ErrorPointer", error.getErrorPointer());	 
	    	//EI20110215: solange description for CZ nicht gesendet wird, hier die haufigsten Errors:
	    	  setDescriptionForCZ(error);	    	
	    	//EI20110207: writeElement("ErrorReason", error.getErrorReason());		    	
	    	if (Utils.isStringEmpty(error.getErrorDescription())) {         //EI20110207
	    		writeElement("ErrorReason", error.getErrorReason());
	    	} else {
	    		writeElement("ErrorReason", error.getErrorDescription());
	    	}
	    	writeElement("OriginalAttributeValue", error.getOriginalAttributeValue());
	    	//EI20110207: writeElement("ErrorDescription", error.getErrorDescription());
		closeElement();	
	}  

    public void writeCustomsInterventions(CustomsIntervention argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	openElement("CustomsIntervention");
    		writeElement("ItemNumber", argument.getItemNumber());
    		writeCodeElement("Code", argument.getCode(), "KCX0067");  //I0108
    		writeElement("Text", argument.getText());
    		writeElement("LNG", argument.getLng());
		closeElement();
    }
    
    public void writeImportDetails(ImportDetails details) throws XMLStreamException {
		if (details == null) {
			return;
		}
		if (details.isEmpty()) {
    		return;
    	}
    	openElement("ImportDetails");             	                    	   
	    	writeElement("MRN", details.getMrn());
	    	writeElement("ItemNumber", details.getItemNumber());
	    	writeElement("ReferenceNumber", details.getReferenceNumber());
	    	writeElement("RejectionReasonPos", details.getRejectionReasonPos());
		closeElement();
	}
    
    public void writeImportOperation(ImportOperation argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	}    	
    	
    	openElement("ImportOperation"); 
        writeElement("MRN", argument.getMRN());            
        for (String itemNumber : argument.getItemNumberList()) {
        	writeElement("ItemNumber", itemNumber);
        }
        closeElement(); 
        	
    } 
    
    public void writeImportOperation(ImportOperationRelease argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	} 
    	
    	openElement("ImportOperation"); 
			writeElement("ReferenceNumber", argument.getReferenceNumber()); 
			writeElement("MRN", argument.getMRN());     
			writeElement("ShipmentNumber", argument.getShipmentNumber()); 
			writeElement("ConveyanceReference", argument.getConveyanceReference()); 
			
			if (argument.getSealsIdList() != null && argument.getSealsIdList().size() > 0) {
				openElement("SealsID"); 
				for (SealNumber seals : argument.getSealsIdList()) {				
        			writeElement("SealsIdentity", seals.getNumber());
        			writeElement("SealsIdentityLng", seals.getLanguage());        		
				}
			closeElement();
			}
			for (GoodsItemShort item : argument.getGoodsItemList()) {
				openElement("GoodsItem"); 
        			writeElement("ItemNumber", item.getItemNumber());
        			writeElement("ShipmentNumber", item.getShipmentNumber());
        			writeContainers(item.getContainersList());
        		closeElement();  
			}
        closeElement();       
    } 
    
    public void writeContainers(List<String> argument) throws XMLStreamException {     	
    	if (argument == null) {
    	    return;
    	}
    	int size = argument.size();
    	if (size > 0) {    		    		
    		for (int i = 0; i < size; i++) {        							    				
    			writeElement("Containers", argument.get(i));    				    		
    		}     		
    	}    	                			 	    
    }
    /*
	//EI20110215: solange description for CZ nicht gesendet wird, hier die haufigsten Errors:
    public void setDescriptionForCZ(FunctionalError error)throws XMLStreamException {  
    	if (this.getKidsHeader() != null && this.getKidsHeader().getCountryCode() != null &&
    		this.getKidsHeader().getCountryCode().equals("CZ")) {
    		if ( error.getErrorPointer() != null && Utils.isStringEmpty(error.getErrorDescription())) {
    			if (error.getErrorPointer().contains("/H/H44")) {
    				error.setErrorDescription("Total number of packages");
    			}
    			if (error.getErrorPointer().contains("GP04")) {
    				error.setErrorDescription("Item: Number of packages ");
    			}
    			if (error.getErrorPointer().contains("/TCE/TCE03")) {
    				error.setErrorDescription("Consignee Postcode");
    			}
				if (error.getErrorPointer().contains("/TCR/TCR03")) {	    					
					error.setErrorDescription("Consignor Postcode");
				}				
				if (error.getErrorPointer().equals("/CZ313A/H")){
					if(error.getOriginalAttributeValue().equals("N009")) {	    									
						error.setErrorDescription("Amendment place");
					 }
				}
				if (error.getErrorPointer().equals("/CZ360A/AO")){
					if(error.getErrorReason().equals("AO05")) {	    									
						error.setErrorDescription("Declaration place");
					}
				}
    		}	    		
    	}
    }
    */
    public void setDescriptionForCZ(FunctionalError error)throws XMLStreamException { //EI20110311
    	
    	String country = "";    	    
    	if (this.getKidsHeader() != null && this.getKidsHeader().getCountryCode() != null) {
    		country = this.getKidsHeader().getCountryCode();
    	}
    	if (!country.equals("CZ")) {  
    		return;
    	}        
    	    	
    	String description = "";	
    	String[] splitedReason;
    	String[] splitedType;
    	
    	if (!Utils.isStringEmpty(error.getErrorPointer())) {
    		description = error.getErrorPointer();
    	}     	
    	if (!Utils.isStringEmpty(error.getOriginalAttributeValue())) {
    		String value = error.getOriginalAttributeValue();
    		String dbValue = "";
    		if (value.startsWith("AO")) {
    			dbValue = Utils.getKCXCodeXmlTag("KCX0095", "CZ", value);   
    			if (!Utils.isStringEmpty(dbValue)) {
    				value = dbValue;
    			}
    		}
			description = description + " = '" + value + "'";   
    	}
    	
    	if (!Utils.isStringEmpty(error.getErrorReason())) {   
    		splitedReason = error.getErrorReason().split(":");
    		if (splitedReason .length > 1) {
    			description = description + " " + splitedReason[1];			
    		} else if (!Utils.isStringEmpty(error.getErrorType())) {
    			splitedType = error.getErrorType().split(":");
    			if (splitedType.length > 1) {
    				description = description + "  " + splitedType[1];	
    			} else {
    				description = description + "  " + error.getErrorType();
    			}  	    			
    		} else { 
    			description = description + " " + error.getErrorReason();	    
    		}    		
    	} else if (!Utils.isStringEmpty(error.getErrorType())) {
    		splitedType = error.getErrorType().split(":");
    		if (splitedType.length > 1) {
				description = description + "  " + splitedType[1];	
			} else {
				description = description + "  " + error.getErrorType();
			}  
    	}
    		
    	if (Utils.isStringEmpty(error.getErrorDescription())) {
    		error.setErrorDescription(description);
    	}    	
    }
    
}
    
