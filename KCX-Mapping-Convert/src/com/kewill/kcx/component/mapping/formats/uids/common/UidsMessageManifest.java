package com.kewill.kcx.component.mapping.formats.uids.common;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;

/**
 * Module : Manifest<br>
 * Date Started : 18.01.2013<br>
 * Description : Fields and methods used in Manifest Messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UidsMessageManifest extends UidsMessage {
			
	public void writePreviousDocument(PreviousDocument argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}

		openElement("PreviousDocuments");
			writeElement("Type", argument.getType());			
			writeElement("Reference", argument.getReference());	
		closeElement();
	}

	public void writeAddress(Address address, String adrtype) throws XMLStreamException {
	    if (address == null) {
	        return;
	    }    	
	    if (address.isEmpty()) {
	        return; 
	    }
	     	
	    openElement(adrtype);
    	   writeElement("Name", address.getName());
    	   writeElement("StreetAndNumber", address.getStreetAndNumber());
    	   writeElement("PostalCode", address.getPostalCode());
    	   writeElement("City", address.getCity());
    	   writeElement("District", address.getDistrict());
    	   writeElement("CountryCodeISO", address.getCountryCodeISO());
    	   writeElement("PoBox", address.getPoBox());
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
	
	public void writePackaging(Packages argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}		
		openElement("Packaging");  		
			writeElement("Quantity", argument.getQuantity());
			writeElement("MarksAndNumbers", argument.getMarks());
			writeElement("Type", argument.getType());             
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
     		writeElement("TerminationOfFlight", argument.getTerminationOfFlight()); 
        closeElement();
    }
}
