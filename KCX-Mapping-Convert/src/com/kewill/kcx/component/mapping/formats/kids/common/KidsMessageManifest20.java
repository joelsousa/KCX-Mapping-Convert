package com.kewill.kcx.component.mapping.formats.kids.common;

import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ErrorDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.GuarantyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderParty;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseHeader;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.AddressType;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.CustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.CustomsNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.FlightDetails;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalAppPosition;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Notification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUP;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Created		: 04.06.2013<br>
 * Description	: Fields and methods to write Manifest Messages.
 * 
 * @author krzoska
 * @version 2.0.00
 */

	public class KidsMessageManifest20  extends KidsMessageManifest { 
		
		
	public void writeDateTimeToDate(String tag, String date) throws XMLStreamException {
		//input: should be formated as ST_Date ==> "2008-10-20"
		//or input: YYYYMMDDhhmm
	    //output:formated date will be converted into String ==> YYYYMMDD (KIDS_Date)		
		
	   	if (date == null || Utils.isStringEmpty(date)) {
    		return;
	   	}
    	//EI20100531: if date isn't formated:
    	if (!date.contains("-")) {      		
    		if (date.length() == 12) {
    			writeElement(tag, date.substring(0, 8));
    		} else {
    			writeElement(tag, date);
    		}
    		return;
    	}
    	
	    String dateString = ""; 
	    KcxDateTime kcx;
			    	    	
    	date.trim();              	 	
	    try {					
	    	kcx = new KcxDateTime(EFormat.ST_Date, date);
	    	dateString = kcx.format(EFormat.KIDS_Date);
	    	writeElement(tag, dateString);
	    } catch (ParseException e) {
	    	e.printStackTrace();	    		
	    }		
    }   
	
	 public void mapKidsToCmpHeader(String beznr) throws XMLStreamException {
		 this.kidsHeader.setMethod("CMP");
		 this.kidsHeader.setProcedure("TEMPSTORAGE");
		 //EI20130715 hat mir Max so gesagt: this.kidsHeader.setMessageName("CustomsResponse");
		 this.kidsHeader.setMessageName(beznr); //EI20130715 nicht "CustomsResponse" sondern beznr
		 this.kidsHeader.setRelease("1.0.00");	
		 this.kidsHeader.setMap("");
		 this.kidsHeader.setMapFrom("");
		 this.kidsHeader.setMapTo("");
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
		    	writeCodeElement("IdentificationType", argument.getIdentificationType(), "KCX0069");   //A1340
		    closeElement();
	 }
	 public void writePartyAddress(String tag, AddressType argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}    	
	    	if (Utils.isStringEmpty(tag)) {
	    	    return;  
	    	}
	    	if (argument.isEmpty()) {
	    	    return;
	    	} 	    	
	    	openElement(tag + "Address");
	    		writeElement("VATNumber", argument.getVATNumber());      
	    		writeElement("ETNAddress", argument.getETNAdress());  
	    		writeSumaAddress(argument.getAddress()); 
	    	closeElement();     		
	 }
	
	   public void writeParty(String person, Party argument) throws XMLStreamException  {    	
	    	if (argument == null) {
	    		return;
	    	}
	    	if (Utils.isStringEmpty(person)) {
	    		return;
	    	}	  
	    	writePartyTIN(person, argument.getPartyTIN());
	    	
	     	if (argument.getAddress() != null) {
	     		openElement(person + "Address");     
	        	    writeElement("VATNumber", argument.getVATNumber());      
	         	    writeElement("ETNAddress", argument.getETNAddress());        		
	     			writeSumaAddress(argument.getAddress());     			
	     		closeElement();     		
	     	}
	     	  /*   	
	     	if (argument.getContactPerson() != null) {
	    		writeContact(person, argument.getContactPerson());
	    	}
	    	*/
	    } 
	 
	   public void writeContact(ContactPerson argument) throws XMLStreamException { 
	    	if (argument == null) {
	    	    return;
	    	}	    	
	    	if (Utils.isStringEmpty(argument.getIdentity()) && Utils.isStringEmpty(argument.getName()) &&
	    		Utils.isStringEmpty(argument.getPosition()) && Utils.isStringEmpty(argument.getPhoneNumber())) {
	    		return;
	    	}
	    	openElement("Contact"); 	
	    		writeElement("Position", argument.getPosition());
	    		writeElement("Name", argument.getName());
	    		writeElement("Identity", argument.getIdentity());	
	    		writeElement("Email", argument.getEmail());	    
	    		writeElement("FaxNumber", argument.getFaxNumber());
	    		writeElement("PhoneNumber", argument.getPhoneNumber());	    		
	    		writeElement("Initials", argument.getInitials());
	    		writeElement("Title", argument.getTitle());
	    	closeElement(); 
	}	
	  
	public void writeTransport(String tagname, Transport argument) throws XMLStreamException {
		 if (argument == null) {
	    	   return;
	     }    	
	     if (argument.isEmpty()) {
	    	   return; 
	     }	     
	     openElement(tagname);	   
	        writeElement("TransportMode", argument.getTransportMode());  	
	    	writeElement("TransportationType", argument.getTransportationType());  
	    	writeElement("TransportationNumber", argument.getTransportationNumber());	    		
	    	writeElement("TransportationCountry", argument.getTransportationCountry());   
	    	writeElement("Description", argument.getDescription());  
	    closeElement();
	} 
	//iwa: eingefuegt, um auf kcxcodes zu erinnern:
	  public void writeMeansOfTransport(Transport argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}	    	       
	        openElement("MeansOfTransport");   	        	
	             writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");
	             writeCodeElement("TransportationType", argument.getTransportationType(), "KCX0004");	
	             writeElement("TransportationNumber", argument.getTransportationNumber()); 
	             writeElement("TransportationCountry", argument.getTransportationCountry());         	             
	             writeElement("Description", argument.getDescription());
	         closeElement();   
	  }
	  public void writeFlightDetails(FlightDetails argument) throws XMLStreamException {
			if (argument == null) {
				return;
		    }    	
		    if (argument.isEmpty()) {
		    	return; 
		    }	     
		    openElement("FlightDetails");	   		        
		    	writeElement("FlightNumber", argument.getFlightNumber());  
		    	writeElement("CarrierCode", argument.getCarrierCode());  
		    	writeElement("NumberOfFlight", argument.getNumberOfFlight());  	
		    	writeElement("AdditionalQualifier", argument.getAdditionalQualifier());	    		
		    	writeElement("AirportOfDeparture", argument.getAirportOfDeparture());   
		    	writeDateTimeToString("DepartureDateTime", argument.getDepartureDateTime());  
		    	writeElement("AirportOfArrival", argument.getAirportOfArrival());  
		    	writeDateTimeToString("ArrivalDateTime", argument.getArrivalDateTime());  
		    closeElement();  
		} 
	  
	public void writeCustomsNotification(String tagname, CustomsNotification argument) throws XMLStreamException { 
	    if (argument == null) {
	    	 return;
	    }
	    openElement(tagname); 	
			writeElement("Content", argument.getContent());
			writeElement("ItemNumber", argument.getItemNumber());
			writeElement("ContentCode", argument.getContentCode());
			writeElement("ContentSubCode", argument.getContentSubCode());
			writeElement("CountryCode", argument.getCountryCode());			
		closeElement(); 
	}
	   
	public void writeHeaderParty(String tagName, HeaderParty party) throws XMLStreamException { 
	    if (party == null) {
	    	 return;
	    }
	    openElement(tagName); 	
			writeElement("Qualifier", party.getQualifier());
			//EI20140228: writeElement("Identity", party.getIdentity()); 
			writeElement("Identification", party.getIdentity()); //EI20140228: 
		closeElement(); 
	}
	
	public void writeReference(ManifestReference argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}    	
	    	if (argument.isEmpty()) {
	    	    return; 
	     	}
	     	
	     	openElement("Reference");
	    		   writeElement("ItemNumber", argument.getItemNumber());   
	    		   //writeElement("ReferenceNumber", argument.getReferenceNumber());  
	    		   //writeElement("ReferenceIdentifier", argument.getReferenceIdentifier());  
	    		   writeElement("RegistrationNumber", argument.getRegistrationNumber());  
	    		   writeElement("PlaceOfLoading", argument.getPlaceOfLoading());  
	    		   writePreviousDocument(argument.getPreviousDocument());  
	    		   //writeReferencedSpecification("head", argument.getReferencedSpecification());
	    		   writeReferencedSpecification("", argument.getReferencedSpecification());  
	    		   writePartyTIN("Custodian", argument.getCustodianTIN());  
	    		   writeElement("PlaceOfCustodyCode", argument.getPlaceOfCustodyCode());  
	        closeElement();
	    }
	
	public void writeItemCustomsResponse(CustomsResponse argument) throws XMLStreamException { 
	    if (argument == null) {
	    	 return;
	    }
	    if (argument.isEmpty()) {
    	    return; 
     	}
	    openElement("CustomsResponse"); 			
		   	writeElement("Type", argument.getType());
		   	writeElement("Pointer", argument.getPointer());
		   	writeElement("Reason", argument.getReason());
		closeElement();
	      		
	}
	
	public void writeReferencedSpecification(ReferencedSpecification argument) throws XMLStreamException { 
	    if (argument == null) {
	    	 return;
	    }
	    if (argument.isEmpty()) {
    	    return; 
     	}
	    openElement("ReferencedSpecification"); 	
			writeElement("TypeOfSpecificationID", argument.getTypeOfSpecificationID());
			writeElement("SpecificationID", argument.getSpecificationID());
		closeElement(); 
	}	
	 
	  public void writeSumaAddress(Address address) throws XMLStreamException {
			if (address == null) {
	    	    return;
	    	}    	
	    	if (address.isEmpty()) {
	    	    return; 
	     	}
	    	String streetAndNumber = address.getStreet();
	    	if (!Utils.isStringEmpty(address.getHouseNumber())) {
	    		streetAndNumber = streetAndNumber + " " + address.getHouseNumber();
	    	}
	     	openElement("Address");
	    		   writeElement("Name", address.getName());
	    		   writeElement("StreetAndNumber", streetAndNumber);
	    		   writeElement("PostalCode", address.getPostalCode());
	    		   writeElement("City", address.getCity());
	    		   writeElement("District", address.getDistrict());
	    		   writeElement("Country", address.getCountry());
	        closeElement();
	  } 
	  
	  public void writeAddress(String tagname, Address address) throws XMLStreamException {
			if (address == null) {
	    	    return;
	    	}    	
	    	if (address.isEmpty()) {
	    	    return; 
	     	}
	    	if(Utils.isStringEmpty(tagname)) {
	    		return;
	    	}
	    	String streetAndNumber = address.getStreet();
	    	if (!Utils.isStringEmpty(address.getHouseNumber())) {
	    		streetAndNumber = streetAndNumber + " " + address.getHouseNumber();
	    	}
	     	//openElement("PlaceOfCustody");
	     	openElement(tagname);
	    		   writeElement("Name", address.getName());
	    		   writeElement("StreetAndNumber", streetAndNumber);
	    		   writeElement("PostalCode", address.getPostalCode());
	    		   writeElement("City", address.getCity());
	    		   writeElement("District", address.getDistrict());
	    		   writeElement("Country", address.getCountry());
	        closeElement();
	  } 
	  
	  
	  public void writePackageList(ArrayList<Packages> list) throws XMLStreamException {
		  if (list == null) {
			  return;
		  }
		  for (Packages pack : list) {
			  writePackage(pack);
		  }
	  }
	  public void writePackage(Packages argument) throws XMLStreamException {
	    	
			if (argument == null) {
			    return;
			}
			if (argument.isEmpty()) {
			    return;
			}						
			openElement("Package");
				writeElement("SequentialNumber", argument.getSequentialNumber());
				writeElement("Quantity", argument.getQuantity());
			    writeElement("Type", argument.getType());
			    writeElement("Marks", argument.getMarks());
			closeElement();
	 }
	 
	  public void writeHeaderDetail(HeaderDetail detail) throws XMLStreamException {
		  if (detail == null) {
	    	    return;
	    	}    	    	    
	    	openElement("HeaderDetail");
	    		writeElement("FlightNumber", detail.getFlightNumber());
	    		writeElement("AirportOfDeparture", detail.getAirportOfDeparture());
	    		writeElement("DepartureDate", detail.getDepartureDate());
	    		writeElement("AirportOfArrival", detail.getAirportOfArrival());
			   	writeElement("ArrivalDate", detail.getArrivalDate());
			   	writeElement("CustomsRegistration", detail.getCustomsRegistration());
			   	writeElement("RegistrationDate", detail.getRegistrationDate());		  
			closeElement();
	  }
	  public void writeItemDetails(ItemDetails argument) throws XMLStreamException {
		  
	  }
	  public void writeHeaderNotification(HeaderNotification argument) throws XMLStreamException {
		  if (argument == null) {
	    	    return;
	    	}    	    	    
	    	openElement("HeaderNotification");
	    		writeElement("NotificationWeight", argument.getNotificationWeight());
	    		if (argument.getNotificationDetailsList() != null) {
	    			for (String detail : argument.getNotificationDetailsList()) {
	    				writeElement("NotificationDetails", detail);
	    			}
	    		}	    		 
			closeElement();		  
	  }

	public void writeLocalApplication(LocalApplication argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}		
		openElement("LocalApplication");  
			writeElement("MessageType", argument.getMessageType());
			writeElement("MessageSubType", argument.getMessageSubType());
			writeElement("MessageFunction", argument.getMessageFunction());
			writeElement("RegistrationNumber", argument.getRegistrationNumber());   //EI20140128
			writeDateToString("RegistrationDate", argument.getRegistrationDate());
		    writeElement("DeclarationStatus", argument.getDeclarationStatus());
		    //writeElement("PositionStatus", argument.getPositionStatus());
		    writeElement("FlightNumber", argument.getFlightNumber());	
		    writeElement("DepartureDate", argument.getDepartureDate());  			//EI20140128
		    writeElement("AirportOfDeparture", argument.getAirportOfDeparture());	//EI20140128
		    writeElement("ArrivalDate", argument.getArrivalDate());  				//EI20140128
		    writeElement("AirportOfArrival", argument.getAirportOfArrival());  		//EI20140128
		    writeElement("AdditionalInformation", argument.getAdditionalData());
		    writePositionList(argument.getPositionList());                          //EI20140314
		closeElement();
	}	
	
	private  void writePositionList(ArrayList<LocalAppPosition> list) throws XMLStreamException  {
		if (list == null) {
		    return;
		}
		if (list.isEmpty()) {
		    return;
		}
		for (LocalAppPosition appPos : list) {
			openElement("LocalApplicationPosition");  
				writeElement("PositionNumber", appPos.getPositionNumber());
				writeElement("PositionStatus", appPos.getPositionStatus());
				writeElement("PositionDestinationPlace", appPos.getPositionDestinationPlace());
			closeElement();
		}
	}
	
	public String calculateDepartureDate(String referenceNumber, String flugnr)  {  //EI20140212
		String departureDate = "";
		String date = "";     //ddmmjj
		
		if (referenceNumber == null) {  // fnr + ddmmjj + ABC
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - beznr/referenceNumber ist leer: " + referenceNumber);  //EI20140214
			return "";
		} 
		if (flugnr == null) {
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - Flugnr ist leer: " + flugnr);  //EI20140214
			return "";
		}
		referenceNumber = referenceNumber.trim();
		flugnr = flugnr.trim();
		
		if (Utils.isStringEmpty(referenceNumber)) {
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - beznr/referenceNumber ist leer: " + referenceNumber);  //EI20140214
			return "";
		}
		if (Utils.isStringEmpty(flugnr)) {
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - Flugnr ist leer: " + flugnr);  //EI20140214
			return "";
		}
		
		int lenFnr = flugnr.length();
		int lenRef = referenceNumber.length();
		if (lenRef < 14 || lenRef > 16) {
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - beznr/referenceNumber ist kuerzer/laenger als 14/16 Zeichen: " + referenceNumber);  //EI20140214
			return "";
		}		
		if (lenFnr == 5) {								
			date = referenceNumber.substring(5, 11);  		
		} else if (lenFnr == 6) {		
			date = referenceNumber.substring(6, 12);  
		} else if (lenFnr == 7) {		
			date = referenceNumber.substring(7, 13);  
		} else  {
			date = "";  
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - Flugnr entspricht nicht den Vorgaben: " + flugnr);  //EI20140214
			return "";
		}  
		String dd = date.substring(0, 2);
		String mm = date.substring(2, 4);
		String yy = date.substring(4, 6);
		departureDate = "20" + yy + mm + dd;
		
		return departureDate;
	}
	
	public String calculateDepartureCode(String referenceNumber)   {  //EI20140212
		if (referenceNumber == null) {
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - ReferenceNumber ist leer: " + referenceNumber);  //EI20140214
			return "";
		}		
		referenceNumber = referenceNumber.trim();
		if (Utils.isStringEmpty(referenceNumber)) {
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - ReferenceNumber ist leer: " + referenceNumber);  //EI20140214
			return "";
		}		
		int len = referenceNumber.length();			
		if (len < 14 || len > 16) {			
			Utils.log("(SUMA: calculateDepartureDate abgebrochen - ReferenceNumber entspricht nicht den Vorgaben: " + referenceNumber);  //EI20140214
			return "";
		}		
			
		return referenceNumber.substring(len - 3, len);
	}	
	public String calculateAirportCodeFromKcxid(KidsHeader header) {    //EI20140212
		String ndl = "";
			
		if (header == null) { 
			return "";
		}
		String receiver = header.getReceiver();
		if (Utils.isStringEmpty(receiver)) {
			Utils.log("(SUMA: KidsHeader.Receiver  ist leer: " + receiver);  //EI20140214
			return "";
		}
		receiver = receiver.trim();
		String[] kcxid = receiver.split("\\."); 
		int len = kcxid.length;
			
		if (len == 3 && kcxid[1] != null && kcxid[2] != null) {  
			if (kcxid[1].trim().equalsIgnoreCase("LCAG")) {
				ndl = kcxid[2].trim();
			} else {
				return "";
			}
		} else {
			return ""; 
		}
		
		return ndl;
	}
   
   	public void writePartyDetails(String name, PartyDetails argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}
		if (argument.isEmpty()) {
		    return;
		}
		if (Utils.isStringEmpty(name)) {
		    return;
		}
		openElement(name);  
			writeElement("EORI", argument.getEori());
			writeElement("Branch", argument.getBranch());
		    writeElement("Name", argument.getName());		   
		    writeElement("Street", argument.getStreet());
		    writeElement("ZipCode", argument.getZipCode());
		    writeElement("City", argument.getCity());	
		    if (!name.equalsIgnoreCase("CustodyDetails")) {
		    	writeElement("Country", argument.getCountry());
		    	writeContactCMP(argument.getContact());
		    }
		closeElement();
	}
	public void writeDisposalDetails(PartyDetails argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}		
		openElement("DisposalDetails");  
			writeElement("EORI", argument.getEori());
			writeElement("Branch", argument.getBranch());		   		   
		closeElement();
	}	
	public void writeCustodyWarehouse(PartyDetails argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}
		openElement("CustodyWarehouse");  
			writeElement("Code", argument.getName());
			writeElement("Description", argument.getDescription());		   		  
		    writeElement("Street", argument.getStreet());
		    writeElement("ZipCode", argument.getZipCode());
		    writeElement("City", argument.getCity());			    
		closeElement();
	}
   	public void writeContactCMP(ContactPerson argument) throws XMLStreamException { 
    	if (argument == null) {
    	    return;
    	}	    	
    	
    	openElement("Contact"); 
    		writeElement("Name", argument.getName());
    		writeElement("Position", argument.getPosition());
    		writeElement("PhoneNumber", argument.getPhoneNumber());
    		writeElement("FaxNumber", argument.getFaxNumber());    		
    		writeElement("EmailAddress", argument.getEmail());	        		
    	closeElement(); 
}	
   	public void writeGuarantyDetails(GuarantyDetails argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}		
		openElement("GuarantyDetails");  
			writeElement("GRN", argument.getGrn());
			writeElement("InvalidCode", argument.getInvalidCode());
		    writeElement("nvalidText", argument.getInvalidText());		   		   
		closeElement();
	}
   	public void writeGuarantyDetails(ArrayList<GuarantyDetails> list) throws XMLStreamException  {
   		if (list == null) {
   			return;
   		}
   		for (GuarantyDetails detail : list) {
   			writeGuarantyDetails(detail);
   		}
   	}
   	public void writeErrorDetails(ErrorDetails argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}	
		if (Utils.isStringEmpty(argument.getErrorText())) {
			 return;
		}
		openElement("ErrorDetails");  
			writeElement("ErrorText", argument.getErrorText());			
		closeElement();
	}
   	public void writeErrorDetails(ArrayList<ErrorDetails> list) throws XMLStreamException  {
   		if (list == null) {
   			return;
   		}
   		for (ErrorDetails detail : list) {
   			writeErrorDetails(detail);
   		}
   	}
   	public void writeItemNotification(ItemNotification argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}	
		if (argument.isEmpty()) {
			return;
		}
		openElement("ItemNotification");
		   	writeElement("NotificationWeight", argument.getNotificationWeight());
		   	writeElement("NotificationDetails", argument.getNotificationDetails());			   
		closeElement();		
	}
   	
   	public ResponseHeader mapResponseHeader(LocalApplication localApplication, String cusType, String refnr) {	
		ResponseHeader responseHeader = new ResponseHeader();		
		if (this.kidsHeader == null) {
			return responseHeader;
		}
		HeaderParty sender = new HeaderParty();		
		HeaderParty receiver = new HeaderParty();
		sender.setIdentity("FSS");
		sender.setQualifier("O");   //EI20140221
		receiver.setIdentity(this.kidsHeader.getReceiver());
		receiver.setQualifier("O");  //EI20140221
		
		responseHeader.setMessageType(cusType);
		responseHeader.setMessageFunction("RESPONSE");
		responseHeader.setMessageName(refnr);		
		responseHeader.setMessageDateTime(this.kidsHeader.getSentAt());
		responseHeader.setMessageVersion("1.0.00");
		responseHeader.setMessageReferenceID(this.kidsHeader.getMessageID());
		responseHeader.setMessageConversationID(this.kidsHeader.getInReplyTo());
		responseHeader.setMessageSender(sender);
		responseHeader.setMessageRecipient(receiver);		
		
		if (localApplication != null) {		
			if (!Utils.isStringEmpty(localApplication.getMessageType())) {
				responseHeader.setMessageType(localApplication.getMessageType());
			} 
			if (!Utils.isStringEmpty(localApplication.getMessageSubType())) {
				responseHeader.setMessageSubType(localApplication.getMessageSubType());
			} 
			if (!Utils.isStringEmpty(localApplication.getMessageFunction())) {
				//EI20130930: responseHeader.setMessageFunction(localApplication.getMessageFunction());
				//EI20130930: nur RESPONSE oder ERR zulaessig:
				if (localApplication.getMessageFunction().equalsIgnoreCase("ERR")) { 
					responseHeader.setMessageFunction("ERR");
				}
			} 
		} else {
			
		}
		
		return responseHeader;
	}
   	//EI20140314: public LocalApplication mapLocalApplication(TsHead head, TsKUN kun, String messageName) {
   	public LocalApplication mapLocalApplication(String messageName, TsHead head, TsKUN kun, 
   			ArrayList<TsKUP> kupList) {
		if (head == null) {
			return null;
		}
		if (kun == null) {
			return null;
		}
		LocalApplication app = new LocalApplication();
		String flnr = this.calculateFlightNumber(kun.getBeznr());  //EI20140212
		String departureDate = this.calculateDepartureDate(kun.getBeznr(), flnr);
		String departureCode = this.calculateDepartureCode(kun.getBeznr());
		String arrivalCode = head.getNl().trim();    //EI20140214
		String arrivalDate = "";   //EI20140617
		if (head.getNl() != null) {
			arrivalCode = head.getNl().trim();
			if (arrivalCode != null && arrivalCode.equals("TST")) {
				arrivalCode = "FRA";
			}
			if (arrivalCode != null && arrivalCode.equals("TVI")) {
				arrivalCode = "VIE";
			}
			if (arrivalCode != null && arrivalCode.equals("TMU")) {
				arrivalCode = "MUE";
			}
		}
		/* EI20131202: jetzt mit trenner "|"
		String inhalt = kun.getInhalt();
		int len = inhalt.length();
		while (inhalt.length() < 30) {
			inhalt = inhalt + " ";
		}
		len = inhalt.length();
		app.setMessageType(messageName);		        	//CUSREC, CUSCAN, usw
		app.setMessageSubType(inhalt.substring(0, 3));		//VSA, ESA usw
		app.setMessageFunction(inhalt.substring(3, 6));		//ERR, INF, 
		app.setDeclarationStatus(inhalt.substring(6, 8));   //41, 49, usw		
		app.setDepartureCode(inhalt.substring(8, 11));		//FRA, usw			
		app.setRegistrationDate(inhalt.substring(11, 25));	//20131004	
		app.setPositionStatus(inhalt.substring(25, 27));	//41, 49, usw	
		*/
		//EI20131202: jetzt mit trenner "|": gleich in TsKUN auseinander gepflueckt
		app.setMessageType(messageName);		        //CUSREC, CUSCAN, usw
		app.setMessageSubType(kun.getVerZstd());		//VSA, ESA usw
		if (kun.getBeznr().startsWith("ATB")) {   //EI20140310: Externe-SWV fix ESV
			app.setMessageSubType("ESV");
		}
		if (!Utils.isStringEmpty(kun.getFlugNr())) {  //EI20140617
			flnr = kun.getFlugNr();
		}
		if (!Utils.isStringEmpty(kun.getAnkDat())) {  //EI20140617
			arrivalDate = kun.getAnkDat();						
		}
		if (!Utils.isStringEmpty(departureCode)) {  //EI20140627
			departureCode = kun.getDepartureCode();						
		}
		app.setMessageFunction(kun.getFehGew());		//ERR, INF, 
		app.setRegistrationDate(kun.getAtbDat());	    
		app.setDeclarationStatus(kun.getKpfStat());     
		//app.setPositionStatus(kun.getPosStat());	    			   	
		//app.setAirportOfDeparture(kun.getDepartureCode());	
		app.setFlightNumber(flnr);					//EI20140212
		app.setAirportOfDeparture(departureCode);   //EI20140212
		app.setDepartureDate(departureDate);		//EI20140212		
		app.setAirportOfArrival(arrivalCode);  		//EI20140214
		app.setArrivalDate(arrivalDate);   		//EI20140617
		if (kupList != null) {   					//EI20140314
			for (TsKUP kup : kupList) {
				LocalAppPosition appPos = new LocalAppPosition();
				appPos.setPositionNumber(kup.getPosnr());
				appPos.setPositionStatus(kup.getStauts());
				appPos.setPositionDestinationPlace(kup.getBesOrt());
				app.addPositionList(appPos);
			}
		}
		return app;		
	}

	

////////
   	public String calculateDate(String dateTime, String format) {
		//dateTime = 2002-07-01T05:10:10
		
		String date = "";
		String dd = "";		
		String mm = "";	
		String yyyy = "";	
		String yy = "";	
		
		if (Utils.isStringEmpty(dateTime)) {
			return "";
		}
		if (dateTime.length() != 19) {
			return "";
		}			
		yyyy = dateTime.substring(0, 4);
		yy = yyyy.substring(2, 4);
		mm = dateTime.substring(5, 7);
		dd = dateTime.substring(8, 10);
			
		if (format.equals("YYYYMMDD")) {
			date = yyyy + mm + dd;
		} else if (format.equals("DDMMYYYY")) {  
			date = dd + mm + yyyy;
		} else if (format.equals("DD.MM.YYYY")) {
			date = dd + "." + mm + "." + yyyy;
		//EI20140606: } else if (format.equals("MMDDYY")) {   //EI20140114
		} else if (format.equals("DDMMYY")) {   //EI20140606
			date = dd +  mm + yy;
		} else {
			date = yyyy + mm + dd;
		}
		return date;
	}
   	public String calculateDateTime(String dateTime, String format) {
		//dateTime = 2002-07-01T05:10:10
		
		String date = "";
		String dd = "";		
		String mm = "";	
		String yyyy = "";	
		String hh = "";	
		String mi = "";	
		String se = "";	
		
		if (Utils.isStringEmpty(dateTime)) {
			return "";
		}
		if (dateTime.length() != 19) {
			return "";
		}			
		yyyy = dateTime.substring(0, 4);
		mm = dateTime.substring(5, 7);
		dd = dateTime.substring(8, 10);
		hh = dateTime.substring(11, 13);
		mi = dateTime.substring(14, 16);
		se = dateTime.substring(17, 19);
			
		if (format.equals("YYYYMMDD")) {
			date = yyyy + mm + dd;
		} else if (format.equals("DDMMYYYY")) {
			date = dd + mm + yyyy;
		} else if (format.equals("DD.MM.YYYY")) {
			date = dd + "." + mm + "." + yyyy;
		} else if (format.equals("YYYYMMDDhhmm")) {
			date = yyyy + mm + dd + hh + mi;		 
		} else if (format.equals("YYYYMMDDhhmmss")) {
			date = yyyy + mm + dd + hh + mi + se;	
		} else {
			date = yyyy + mm + dd + hh + mi;
		}
		return date;
	}
   	public String calculateDate(String dateIn, String formatIn, String formatOut) {
		//dateIn = 20140631 oder 140631
		
		String dateOut = "";
		String dd = "";		
		String mm = "";	
		String yyyy = "";	
		String yy = "";	
		
		if (Utils.isStringEmpty(dateIn)) {
			return "";
		}
		int len = dateIn.length();
		if (dateIn.length() == 8 && formatIn.equals("YYYYMMDD")) {
			yyyy = dateIn.substring(0, 4);
			yy = yyyy.substring(2, 4);
			mm = dateIn.substring(4, 6);
			dd = dateIn.substring(6, 8);
		} else if (dateIn.length() == 6 && formatIn.equals("YYMMDD")) {			
			yy = yyyy.substring(0, 2);
			yyyy = "20" + yy;
			mm = dateIn.substring(2, 4);
			dd = dateIn.substring(4, 6);
		} else {
			return "";
		}
		
		if (formatOut.equals("YYYYMMDD")) {
			dateOut = yyyy + mm + dd;
		} else if (formatOut.equals("DDMMYYYY")) {  
			dateOut = dd + mm + yyyy;
		} else if (formatOut.equals("DD.MM.YYYY")) {
			dateOut = dd + "." + mm + "." + yyyy;
		} else if (formatOut.equals("DDMMYY")) {   
			dateOut = dd +  mm + yy;
		} else {
			dateOut = yyyy + mm + dd;
		}
		return dateOut;
	}
   	
   	public String getToday() {
		String timestamp = Utils.getSystemTimestamp(); //21.06.2013 10:13:42,885
		String date = "";
		if (timestamp != null) {
	    	String dat = timestamp.substring(0, 10);	    	
	    	String k3 = dat.substring(2, 3);
	    	String k4 = dat.substring(5, 6);
	    	if (dat.substring(2, 3).equals(".") && dat.substring(5, 6).equals(".")) {
	    		date = dat.substring(6, 10) + dat.substring(3, 5) + dat.substring(0, 2);
	    	} 
	    }
		return date;
	}
	
	  public String mapDate(String day, String month) {
			String timestamp = Utils.getSystemTimestamp(); //21.06.2013 10:13:42,885
			String date = "";		
			String mo = "";		
			int yyyy = 0;
			
			if (Utils.isStringEmpty(day) || Utils.isStringEmpty(month)) {
				return "";
			}
			if (Utils.isStringEmpty(timestamp)) {
				return "";
			}			
			mo = mapMonthToMM(month);
			date = timestamp.substring(0, 10);
			yyyy = Integer.parseInt(timestamp.substring(6, 10));	
				//String mm = timestamp.substring(3, 5);		
				//String year = timestamp.substring(6, 10);		
								
			if (mo.equalsIgnoreCase("12") && day.equals("31")) {			
				yyyy = yyyy - 1;					
			}
			date = yyyy + mo + day;
			return date;
		}
	  
	  public String mapMonthToMM(String month) {
			String mm = "";
			if (Utils.isStringEmpty(month)) {
				return "";
			}
			if (month.equalsIgnoreCase("JAN")) {
				mm = "01";
			} else if (month.equalsIgnoreCase("FEB")) {
				mm = "02";
			} else if (month.equalsIgnoreCase("MAR")) {
				mm = "03";
			} else if (month.equalsIgnoreCase("ARP")) {
				mm = "04";
			} else if (month.equalsIgnoreCase("MAY")) {
				mm = "05";
			} else if (month.equalsIgnoreCase("JUN")) {
				mm = "06";
			} else if (month.equalsIgnoreCase("JUL")) {
				mm = "07";
			}  else if (month.equalsIgnoreCase("AUG")) {
				mm = "08";
			} else if (month.equalsIgnoreCase("SEP")) {
				mm = "09";
			} else if (month.equalsIgnoreCase("OCT")) {
				mm = "10";
			} else if (month.equalsIgnoreCase("NOV")) {
				mm = "11";
			} else if (month.equalsIgnoreCase("DEC")) {
				mm = "12";
			} else {
				mm = month;
			}
			return mm;
		}
	  public String calculatePreviousDocumentType(String flightnr, String land, String destLand) {
			if (Utils.isStringEmpty(flightnr)) {
				return "";
			}
			String type = "";
			int flnr = Integer.parseInt(flightnr);
		/*
		 * (Wenn EU Land und Flugnummer ungleich 7000-7999: Vorpapierart 445, 
		 * wenn Nicht-EU und Flugnummer ungleich 7000-7999:  Vorpapierart OHNE, 
		 * wenn Flugnr 7000<=x<=7999 UND mind. 1 Position hat T1 oder TF dann Vorpapierart T1)
		 *  
		 * Wenn Packstück-Bes. = leer und EU Land: Vorpapierart 445, wenn nicht-EU: Vorpapierart OHNE. 
		 * Wenn Packstück-Bes. = 0, wenn in Position=T1 oder TF, dann T1, 
		 * wenn Best.Ort in Italien, dann T2, sonst 445 	
		 */
			// TODO den richtigen type ermitteln
			
			if (land.equals("EU")) {
				
			} else {			
				if (flnr < 7000 && flnr > 7999) {
					type = "OHNE";
				}
			}
			return type;
	}
	
	public String calculateReferenceNumber(String flightnr, String ddmmjj, String airportCode) {
		String beznr = "";
		if (Utils.isStringEmpty(flightnr)) {
			return "";
		}
		if (Utils.isStringEmpty(ddmmjj)) {
			return "";
		}
		if (Utils.isStringEmpty(airportCode)) {
			return "";
		}
		flightnr = flightnr.trim();
		
		//fuer LCAG kann am Ende von flightnr zg. Zusatzkennzeichen stehen, aber grundsaetzlich
		//ist der Aufbau: 2-stelliger CarrierCode + 4 Ziffer + ev.1 Buchstabe = 7-stellig (HH12324 oder LH1234S),
		// also ist flightnr 6- oder 7-stellig
		// fuer andere Kunden ist die NR 3 oder 4 Ziffern, also ist flightnr 5- oder 6-stellig: CC123 oder CC1234,
	
		/* EI20140211: der Bchstabe soll doch nicht abgeschnitten werden!!!
		int len = flightnr.length();
		String end = flightnr.substring(len - 1);
		
		//if (Utils.isSignNumeric(end)) {  		 
		//	beznr = flightnr + ddmmjj + airportCode; 
		//} else if (len > 6 && Utils.isSignNumeric(end)) {
		//	beznr = flightnr.substring(0, len - 1) + ddmmjj + airportCode; 			
		//}
		
		if (len == 5 || len == 6) {
			beznr = flightnr + ddmmjj + airportCode;
		} else if (len == 7 && !Utils.isSignNumeric(end)) {
			beznr = flightnr.substring(0, len - 1) + ddmmjj + airportCode; 	
		}
		*/
		
		beznr = flightnr + ddmmjj + airportCode;  //EI20140211
		
		return beznr;
	}
	public String calculateFlightNumber(String beznr) {    //EI20140212
		String flightNumber = "";
			
		if (Utils.isStringEmpty(beznr)) {
			Utils.log("(SUMA: calculateFlightNumber abgebrochen - beznr/referenceNumber ist leer: " + beznr);  //EI20140214
			return "";
		}
		beznr = beznr.trim();
		int len = beznr.length();
		if (len < 14 || len > 16) {       //5/6 + 6 + 3
			Utils.log("(SUMA: calculateFlightNumber abgebrochen - beznr/referenceNumber entspricht nicht den Vorgaben: " + beznr);  //EI20140214
			return "";
		}
		if (!Utils.isSignNumeric(beznr.substring(6, 7))) { //7 + 6 + 3 = 16
			flightNumber = beznr.substring(0, 7);
		} else {
			if (len == 14) {
				flightNumber = beznr.substring(0, 5); //5 + 6 + 3
			} else if (len == 15) {
				flightNumber = beznr.substring(0, 6); //6 + 6 + 3
			} else {
				Utils.log("(SUMA: calculateFlightNumber abgebrochen - beznr/referenceNumber entspricht nicht den Vorgaben: " + beznr);  //EI20140214
			}
		}
		
		return flightNumber;
	}
	
	public void writeNotification(Notification argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}		
		openElement("Notification");  
			writeElement("NotificationType", argument.getNotificationType());
			writeElement("NotificationSubType", argument.getNotificationSubType());
		    writeElement("NotificationCode", argument.getNotificationCode());		   
		    writeElement("NotificationDescription", argument.getNotificationDescription());
		closeElement();
	}

	public String calculateCustomsStatus(String cmpStatus) {    //EI20131007
		String fssStatus = cmpStatus;
		if (cmpStatus != null) {
			if (cmpStatus.equalsIgnoreCase("TD")) { fssStatus = "D"; }
			if (cmpStatus.equalsIgnoreCase("TF")) { fssStatus = "F"; }
			if (cmpStatus.equalsIgnoreCase("T1")) { fssStatus = "N"; }	
			if (cmpStatus.equalsIgnoreCase("T2")) { fssStatus = "C"; }
		}
		return fssStatus;
	}
	
	public String calculateKcxId(KidsHeader kidsHeader, String stationCode, boolean testmode) {   //EI20140321
		String kcxId = "";
		/* 
		  fuer Suma stationCode = ArrivalLocationCode.Id, wird in der ArrivalEvent-Schleife gelesen		  	
		  fuer NCTS, Reexport stationCode = FFM.LogisticsTransportMovement.departureLocationCode.Id
		*/
		
		if (kidsHeader == null) {
			Utils.log("KcxId can not be calculated: kidsHeader is null");			
			return "";
		}
		if (Utils.isStringEmpty(stationCode)) {
			Utils.log("KcxId can not be calculated - stationCode = " + stationCode + " is empty");
			kidsHeader.setReceiver("");
			return "";
		}
			
		if (stationCode.equalsIgnoreCase("VIE")) {					
			//kcxId = Utils.getKewillIdFromCustomer("VIE", "CMP");  //EI20140211
			kidsHeader.setCountryCode("AT");	
			if (testmode) {	
				kcxId = "AT.LCAG.TVI";
			} else {
				kcxId = "AT.LHC.VIE";   //EI20140519
			}			
		} else if (stationCode.equalsIgnoreCase("MUC")) {			
			//kcxId = Utils.getKewillIdFromCustomer("MUC", "CMP");  //EI20140211
			if (testmode) {	
				kcxId = "DE.LCAG.TMU";
			} else {
				kcxId = "DE.LHC.MUC";  //EI20140519
			}							
		} else if (stationCode.equalsIgnoreCase("FRA")) {					
			//kcxId = Utils.getKewillIdFromCustomer("FRA", "CMP");  //EI20140211
			if (testmode) {	
				kcxId = "DE.LCAG.TST";  //EI20140519
			} else {
				kcxId = "DE.LHC.FRA";
			}				
		} else {
			//der rest wird beim Testen auf die Nase fallen, da es auf der pilotdb diese Mdt/Ndl nicht gibt
			if (testmode) {	
				kcxId = "DE.LCAG." + stationCode;	
			} else {
				kcxId = "DE.LHC." + stationCode;	 //EI20140519
			}
		} 
		
		//kidsHeader.setReceiver(kcxId);		
		Utils.log("calculateKcxId = " + kcxId);
	
		return kcxId;
	}
	
	public boolean checkDBforCalculatedKcxid(String kcxId) { //throws XMLStreamException  {
		//DB-Tabellen: customer_licencen und customer_procedure werden überprüft
		
		if (Utils.isStringEmpty(kcxId)) {
			Utils.log("(checkDBforCalculatedKcxid: calculated KcxId is empty or null");  //EI20140220
			return false;
		}
		kcxId = kcxId.trim();
		
		// dies müsste in CmpToKidsConverter passieren - dort aber lesen wir die gesamte Nachricht nicht!
		//daher wird erst beim Mapping die richtige kcx_id ermittelt
		
		Utils.getKewillIdFromCustomer(kcxId, "KIDS");		
		Utils.isProcedureLicensed(kcxId, this.kidsHeader.getProcedure(), this.kidsHeader.getCountryCode());      
		Utils.getCustomerProceduresFromKcxId(kcxId, this.kidsHeader.getProcedure().toUpperCase());
		
		return true;
	}
}

