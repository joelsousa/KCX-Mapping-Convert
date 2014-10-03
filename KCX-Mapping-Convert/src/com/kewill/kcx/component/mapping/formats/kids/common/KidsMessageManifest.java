package com.kewill.kcx.component.mapping.formats.kids.common;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Created		: 05.01.2013<br>
 * Description	: Fields and methods to write Manifest Messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsMessageManifest  extends KidsMessage { 	
 	
	
	public void writeTrader(String person, Trader trader) throws XMLStreamException { 
		if (trader == null) { 
    	    return;
    	}
    	
    	openElement(person);
    		writeElement("TIN", trader.getTIN());    		
        	writeAddress(trader.getAddress());    			
        	writeContact(trader.getContactPerson());
		closeElement(); 
	}
	
	
	
	public void writeAddress(Address address) throws XMLStreamException {
		if (address == null) {
    	    return;
    	}    	
    	if (address.isEmpty()) {
    	    return; 
     	}
     	
     	//openElement(adrtype);
    	openElement("Address");
    		   writeElement("Name", address.getName());
    		   writeElement("StreetAndNumber", address.getStreetAndNumber());
    		   writeElement("PostalCode", address.getPostalCode());
    		   writeElement("City", address.getCity());
    		   writeElement("District", address.getDistrict());
    		   writeElement("CountryCodeISO", address.getCountryCodeISO());
    		   writeElement("PoBox", address.getPoBox());
        closeElement();
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
	
	public void writePreviousDocument(PreviousDocument argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}    	
	    	if (argument.isEmpty()) {
	    	    return; 
	     	}
	     	
	     	openElement("PreviousDocument");
	     		writeElement("Type", argument.getType());	    		  	    		  
	    		writeElement("Reference", argument.getReference()); 
	    		writeElement("Marks", argument.getMarks());    //EI20130828
	        closeElement();
	    } 
	
	public void writeCustomsOffices(CustomsOffices argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (Utils.isStringEmpty(argument.getOfficeOfPresentation())) {
    	    return; 
     	}     	
     	openElement("CustomsOffices");
    		   writeElement("OfficeOfPresentation", argument.getOfficeOfPresentation()); 
    		   writeElement("OfficeOfFirstEntry", argument.getOfficeOfFirstEntry());  
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
    		   writeElement("ReferenceNumber", argument.getReferenceNumber());  
    		   writeElement("ReferenceIdentifier", argument.getReferenceIdentifier());  
    		   writeElement("RegistrationNumber", argument.getRegistrationNumber());  
    		   writeElement("FlightNumber", argument.getFlightNumber());      		   
    		   writeElement("PlaceOfLoading", argument.getPlaceOfLoading());  
    		   writePreviousDocument(argument.getPreviousDocument());  
    		   //writeReferencedSpecification("head", argument.getReferencedSpecification());
    		   writeReferencedSpecification("", argument.getReferencedSpecification());  
    		   writeElement("CustodianTIN", argument.getCustodianTIN());  
    		   writeElement("PlaceOfCustodyCode", argument.getPlaceOfCustodyCode());  
        closeElement();
    } 
	
	public void writeReferencedSpecification(String placement, ReferencedSpecification argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return; 
     	}
     	
     	openElement("ReferencedSpecification");
    		   writeElement("TypeOfSpecificationID", argument.getTypeOfSpecificationID()); 
    		   writeElement("SpecificationID", argument.getSpecificationID());
    		   if (placement != null && placement.equalsIgnoreCase("head")) {
    			   writeElement("CustodianTIN", argument.getCustodianTIN()); 
    		   }
        closeElement();
    } 
	
	public void writeTransport(Transport argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return; 
     	}
     	
     	openElement("MeansOfTransport");
    		writeElement("TransportMode", argument.getMode());  			//EI20130116: tagname geaendert
    		writeElement("TransportationNumber", argument.getIdentity());   //EI20130116: tagname geaendert	
    		writeElement("Description", argument.getDescription());    	
        closeElement();
    } 
	
	public void writeHeaderExtensions(HeaderExtensions argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return; 
     	}
     	
     	openElement("HeaderExtensions");
     		writeElement("AdvanceProcedureID", argument.getAdvanceProcedureID());  
     		writeElement("ConfirmationCode", argument.getConfirmationCode()); 
     		writeElement("HeadPosID", argument.getHeadPosID()); 
     		writeElement("MaritimTrafficID", argument.getMaritimTrafficID());      		
     		writeElement("RegistrationID", argument.getRegistrationID()); 
     		writeElement("FlightCompletionCode", argument.getFlightCompletionCode());
     		writeElement("TerminationOfFlight", argument.getTerminationOfFlight()); 
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
		//openElement("Packages");  //EI20130701 doch falsch, erst in Manifest20 ändern!	
			writeElement("Quantity", argument.getQuantity());
		    writeElement("Marks", argument.getMarks());
		    writeElement("Type", argument.getType());             
		closeElement();
	}	 

	public void writeItemExtension(ItemExtension itemExtension) throws XMLStreamException {
		if (itemExtension == null) {
			return;
		}
				
		openElement("ItemExtensions");
			writeElement("ExternalCode", itemExtension.getExternalCode());
			writeElement("TemporaryStorageCode", itemExtension.getTemporaryStorageCode()); 
		closeElement();
	}	
}

