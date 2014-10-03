package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: AddressDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class AddressDetails extends KCXMessage {
   
    private String postcodeCode;
    private String streetName;
    private String cityName;
    private String countryId;
    private String countryName;
    private String countrySubDivisionName;
    private String postOfficeBox;
    private String cityId;
    private String countrySubDivisionId;
    private Location specifiedAddressLocation;
    
    private enum EAddressDetails {    	  			     	
    	PostcodeCode,
    	StreetName,
    	CityName,   
    	CountryID,
    	CountryName,
    	CountrySubDivisionName,
    	PostOfficeBox,
    	CityID,
    	CountrySubDivisionID,
    	SpecifiedAddressLocation;
    }        

    public AddressDetails() {
	      	super();	       
    }
    
    public AddressDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EAddressDetails) tag) {	
    			
    			case SpecifiedAddressLocation:
    				specifiedAddressLocation = new Location(getScanner());
    				specifiedAddressLocation.parse(tag.name());    
    				break;
    				
    			default:
    					return;
    			}
    		} else {
    			switch ((EAddressDetails) tag) {
    			
    			case PostcodeCode:
    				setPostcodeCode(value);
    				break;
    				
    			case StreetName:
    				setStreetName(value);
    				break;    				    		
    				
    			case CityName:    			
    				setCityName(value);
    				break;
    				
    			case CountryID:
    				setCountryId(value);
    				break;
    				
    			case CountryName:
    				setCountryName(value);
    				break;
    				
    			case CountrySubDivisionName:
    				setCountrySubDivisionName(value);
    				break;
    				
    			case PostOfficeBox:
    				setPostOfficeBox(value);
    				break;
    				
    			case CityID:
    				setCityId(value);
    				break;
    				
    			case CountrySubDivisionID:
    				setCountrySubDivisionId(value);
    				break;
    				
    				default:
    					break;
    			}
    		}
    }

    

	public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
    	try {
    		return EAddressDetails.valueOf(token);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}
    }

	public String getPostcodeCode() {
		return postcodeCode;
	}
	public void setPostcodeCode(String eori) {
		this.postcodeCode = eori;
	}

	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String id) {
		this.streetName = id;
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String name) {
		this.cityName = name;
	}

	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String id) {
		this.countryId = id;
	}

	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String name) {
		this.countryName = name;
	}

	public String getCountrySubDivisionName() {
		return countrySubDivisionName;
	}
	public void setCountrySubDivisionName(String name) {
		this.countrySubDivisionName = name;
	}

	public String getPostOfficeBox() {
		return postOfficeBox;
	}
	public void setPostOfficeBox(String country) {
		this.postOfficeBox = country;
	}
	
	public Location getSpecifiedAddressLocation() {
		return specifiedAddressLocation;
	}
	public void setSpecifiedAddressLocation(Location location) {
		this.specifiedAddressLocation = location;
	}

	public String getCityId() {
		return cityId;
	}
	public void setCityId(String id) {
		this.cityId = id;
	}
	public String getCountrySubDivisionId() {
		return countrySubDivisionId;
	}
	public void setCountrySubDivisionId(String id) {
		this.countrySubDivisionId = id;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(postcodeCode) && Utils.isStringEmpty(streetName) &&
		 	Utils.isStringEmpty(cityName) && Utils.isStringEmpty(countryId) &&
		 	Utils.isStringEmpty(countryName) && Utils.isStringEmpty(countrySubDivisionName) &&
		 	Utils.isStringEmpty(postOfficeBox) && Utils.isStringEmpty(cityId) &&
		 	Utils.isStringEmpty(countrySubDivisionId) && specifiedAddressLocation == null; 
	}
		
}
