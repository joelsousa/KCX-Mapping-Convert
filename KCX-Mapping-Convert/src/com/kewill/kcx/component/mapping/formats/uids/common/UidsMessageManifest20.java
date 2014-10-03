package com.kewill.kcx.component.mapping.formats.uids.common;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;

/**
 * Module : Manifest<br>
 * Date Started : 18.01.2013<br>
 * Description : Fields and methods used in Manifest Messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UidsMessageManifest20 extends UidsMessageManifest {
	
	public void writeAddress(Address address, String adrtype) throws XMLStreamException {
    	if (address == null) {
    	    return;
    	}    	
    	if (address.isEmpty()){
    	    return; 
     	}
     	
     	openElement(adrtype);
    		   writeElement("Name", address.getName());
    		   writeElement("StreetAndNumber", address.getStreet());
    		   writeElement("PostalCode", address.getPostalCode());
    		   writeElement("City", address.getCity());
    		   writeElement("CountryCodeISO", address.getCountry());
        closeElement();
    }  	
}
