package com.kewill.kcx.component.mapping.formats.uids.common;

import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.KcxNoDataFoundException;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: KidsMessageICS<br>
 * Created		: 09.06.2010<br>
 * Description	: Fields and methods to write ICS-KidsMessages .
 * 
 * @author Pete T
 * @version 1.0
 */

public class UidsMessageICS extends UidsMessage {
	
	public void writeTrader(String tag, TIN tin,
			Party address, ContactPerson contactPerson) throws XMLStreamException {
		
		openElement(tag);		
			if (tin != null) {
	        	writeElement("TIN", tin.getTIN());
			}			
			
			if (address != null) {
				if (address.getAddress() != null) {
					writeAddress("Address", address.getAddress());
				}
			}
			writeContact("Contact", contactPerson);			
        closeElement();
	}
	
	public void writeTrader(String tag, Party party) throws XMLStreamException {
		if (party == null) {
    	    return;
    	}
		if (party.isEmpty()) {
    	    return;
    	}
		
		openElement(tag);
		    //AuthorisationID
			if (party.getPartyTIN() != null) {	
				writeElement("CustomerID", party.getPartyTIN().getCustomerIdentifier());
				writeElement("CustomsID", party.getPartyTIN().getIsTINGermanApprovalNumber());
				//EI20110119:writeElement("TINType", party.getPartyTIN().getIdentificationType());
			}
			writeElement("ETNAddress", party.getETNAddress());
			//ElectronicSignature
			if (party.getPartyTIN() != null) {			
				writeElement("TIN", party.getPartyTIN().getTIN());
				writeElement("TINType", party.getPartyTIN().getIdentificationType()); //EI20110119
			}
			writeElement("VATID", party.getVATNumber());
			//Status
			
			writeAddress("Address", party.getAddress());			
			writeContact("Contact", party.getContactPerson());						
        closeElement();
	}
    public void writeAddress(String tag, Address argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	
    	
    	openElement(tag); 
            writeElement("Name", argument.getName());
            writeElement("StreetAndNumber", argument.getStreet());
            writeElement("PostalCode", argument.getPostalCode());
            writeElement("City", argument.getCity());
            writeElement("District", argument.getDistrict());
            writeElement("CountryCodeISO", argument.getCountry());
            writeElement("POBox", argument.getPOBox());
        closeElement();     
    } 
    public void writeTIN(String tag, TIN argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	
    	
    	openElement(tag); 
            writeElement("TIN", argument.getTIN());            
        closeElement();     
    } 

    public void writeContact(String tag, ContactPerson argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {    		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
    		writeElement("Identity", argument.getIdentity()); 
	    	writeElement("Name", argument.getClerk());
    		writeElement("Function", argument.getPosition()); 
	    	writeElement("Phone", argument.getPhoneNumber());
	    	writeElement("Fax", argument.getFaxNumber());
	    	writeElement("Email", argument.getEmail());
	    closeElement();
    } 
   
    // C.K. 1.6.2012 UIDS ICS: replace "Z" in dateTime fields with "+0100" 
    public void writeFormattedDateOrTime(String tag, String dateOrTimeString, EFormat currentFormat,
    		EFormat resultFormat) throws XMLStreamException {
    	KcxDateTime kcx;
		try {
			kcx = new KcxDateTime(currentFormat, dateOrTimeString);
			if (resultFormat.equals(EFormat.ST_DateTimeTZ) && Config.isRemoveTZ()) {
				writeElement(tag, kcx.format(EFormat.ST_DateTimeTNoZ));
			} else {
		    	writeElement(tag, kcx.format(resultFormat));
			}

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
    		writeStringToDateTime(tag, dateTimeString);    	
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
    		writeStringToDate(tag, dateString);
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
    		writeStringToTime(tag, timeString);
    	} else {
    		writeFormattedDateOrTime(tag, timeString, currentFormat, resultFormat);
    	}
    }

	public void writeGoodsItemShort(GoodsItemShort goodsItem) throws XMLStreamException {
		
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
	    	if (goodsItem.getMotivation() != null && !goodsItem.getMotivation().isEmpty()) {	    		
	    		//EI20121023: openElement("GoodsItem");
	    		openElement("Motivation");  //EI20121023
	    			writeElement("Code", goodsItem.getMotivation().getCode());
	    			writeElement("Text", goodsItem.getMotivation().getText());
	    		 closeElement();
	    	}
	    closeElement();
	}

    public void writeImportOperation(ImportOperation argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (Utils.isStringEmpty(argument.getMRN()) && ((argument.getItemNumberList() == null))) {
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
			writeElement("LocalReferenceNumber", argument.getReferenceNumber()); 
			writeElement("MRN", argument.getMRN());     			
			writeElement("ConveyanceNumber", argument.getConveyanceReference()); 
			writeElement("CommercialReference", argument.getShipmentNumber()); 
			
			if (argument.getSealsIdList() != null && argument.getSealsIdList().size() > 0) {				
				for (SealNumber seals : argument.getSealsIdList()) {				
        			writeElement("SealsIdentity", seals.getNumber());        			      	
				}			
			}
			for (GoodsItemShort item : argument.getGoodsItemList()) {
				openElement("GoodsItem"); 
        			writeElement("ItemNumber", item.getItemNumber());
        			writeElement("CommercialReferenceNumber", item.getShipmentNumber());
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
    			writeElement("ContainerNumber", argument.get(i));    				    		
    		}     		
    	}    	                			 	    
    }
	
    public void writeTransport(String tag, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement(tag);             	                    	   
    		writeElement("Identity", argument.getTransportationNumber());
	    	writeElement("Mode", argument.getTransportMode());
	    	writeElement("Nationality", argument.getTransportationCountry());
	    closeElement();
    } 
    
    public void writePackaging(Packages argument) throws XMLStreamException {
    	
		if (argument == null) {
		    return;
		}
		if (argument.isEmpty()) {
		    return;
		}		
		openElement("Packaging");  		
			//EI20010208: writeElement("Number", argument.getSequentialNumber());
		    //EI20010208:writeElement("Quantity", argument.getQuantity());
		if (!Utils.isStringEmpty(argument.getQuantity())) {  //EI20010208
			writeElement("Number", argument.getSequentialNumber());
			writeElement("Quantity", argument.getQuantity());
		} else {
			writeElement("Quantity", argument.getSequentialNumber());
		}
		    writeElement("MarksAndNumbers", argument.getMarks());
		    writeElement("Type", argument.getType());             
		closeElement();
	}	 
    public void writeDocument(IcsDocument argument) throws XMLStreamException {
    	
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}        	
    	openElement("Documents");   	
   			writeElement("Type", argument.getType());
   			//writeElement("DateOfCreation", argument.getIssueDate()); //EI20121023 new ??? in V20
   			writeFormattedDateTime("DateOfCreation", argument.getIssueDate(), 
   					argument.getIssueDateFormat(), EFormat.ST_Date);
   			writeElement("Reference", argument.getReference());
   			  		
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
    		//EI20121023: writeElement("code", argument.getCode());   
			writeElement("Code", argument.getCode());   //EI20121023
			writeElement("Text", argument.getCode());    //EI20121023 new ??? in V20
		closeElement();		    		    
    }
    
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

    	if (goodsItem.getConsignor() != null) {
    		// CK 20101229 write Trader with all tags beyond - not only TIN
    		// writeTIN("Consignor", goodsItem.getConsignor().getPartyTIN());
    		writeTrader("Consignor", goodsItem.getConsignor());
    	}
    	if (goodsItem.getConsignee() != null) {
    		// CK 20101229 write Trader with all tags beyond - not only TIN
    		// writeTIN("Consignee", goodsItem.getConsignee().getPartyTIN());
    		writeTrader("Consignee", goodsItem.getConsignee());
    	}
    	if (goodsItem.getNotifyParty() != null) {
    		// CK 20101229 write Trader with all tags beyond - not only TIN    		
    		// writeTIN("NotifyParty", goodsItem.getNotifyParty().getPartyTIN());
    		writeTrader("NotifyParty", goodsItem.getNotifyParty());
    	}
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
    			/* EI20111010 now for both: Declaration and Amendment
    			if (msg.equalsIgnoreCase("Declaration") && document.getType().equalsIgnoreCase("N703")) {  //EI20110923
    				openElement("Documents");   	
    	   				writeElement("Type", "N741");    	   			
    	   				writeElement("Reference", shipmentNumber);    	   			  	
    	   			closeElement();	
    			} 
    			*/
    		}
    	}
    	if (goodsItem.getSpecialMentionList() != null) {
    		for (SpecialMention special : goodsItem.getSpecialMentionList()) {
    			writeSpecialMention(special);
    		}
    	}

    	closeElement();
    }
    
    public void writeN741(List<IcsDocument> list, String shipmentNumber) throws XMLStreamException {   //EI20111010
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}    	
    	for (IcsDocument document : list) {     		
    		if (document != null && document.getType() != null) {
    			if (document.getType().equalsIgnoreCase("N703")) {  
    				openElement("Documents");   	
   						writeElement("Type", "N741");    	   			
   						writeElement("Reference", shipmentNumber);    	   			  	
   					closeElement();	
   					break;
    			} 
    		}
    	}
    }
    
    public void writeFunctionalError(FunctionalError argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}   
    	openElement("Error"); 
        	writeElement("Type", argument.getErrorType());
        	writeElement("Pointer", argument.getErrorPointer());
        	writeElement("Reason", argument.getErrorReason());
        	writeElement("OriginalValue", argument.getOriginalAttributeValue());
        	writeElement("Description", argument.getErrorDescription());
        closeElement();  
    }
    
    public void writeCustomsInterventions(CustomsIntervention argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	//EI20110407:openElement("CustomsIntervention");
    	openElement("Intervention");
    		writeElement("AffectedItemNumber", argument.getItemNumber());
    		writeElement("Code", argument.getCode());
    		writeElement("Text", argument.getText());
		closeElement();
    }
    
    //EI20110211:
    public void writeCodeElementAE2CZ(String tag, String value, String kcxCodeID) throws XMLStreamException {
        if (value != null) {
            if (!value.trim().equals("")) {
                String targetValue = "";                
                if (uidsHeader.getDirection() != null && uidsHeader.getDirection().equals("FROM_CUSTOMER")) {                	        
                    try {                    	
                        targetValue = Utils.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, "DE", "CZ");
                                                                            //uidsHeader.getFrom().substring(0, 2), 
                                                                            //uidsHeader.getTo()).substring(0, 2);
                    } catch (KcxNoDataFoundException e) {
                        Utils.log("(KidsMessage writeCodeElement) " + e.getMessage());
                        // Wird in der DB nichts gefunden wird ein Leerstring eingetragen
                        // code not found in database so output blank
                        targetValue = " ";
                    }
                } else {
                    targetValue = value;
                }
                Utils.log("(UidsMessage writeCodeElement) alter Wert " + value + " neuer Wert " + targetValue);
                writeElement(tag, targetValue);
            }
        }
    }
}
