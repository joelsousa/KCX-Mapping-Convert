package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: PartyDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class PartyDetails extends KCXMessage {
   
    private String eori;
    private String bo;
    private String name;
    private String street;
    private String zipCode;
    private String city;
    private String country;
    private ContactPerson contact;
    private String description;
    
    private enum EPartyDetails {    	  			     	
    	EORI,
    	Branch,
    	Name,   Code,
    	Street,
    	ZipCode,
    	City,
    	Country,
    	Description,
    	Contact;
    }        

    public PartyDetails() {
	      	super();	       
    }
    
    public PartyDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EPartyDetails) tag) {	
    			case Contact:
    				contact = new ContactPerson(getScanner());
    				contact.parse(tag.name());
    				break;
    				
    			default:
    					return;
    			}
    		} else {
    			switch ((EPartyDetails) tag) {
    			
    			case EORI:
    				setEori(value);
    				break;
    				
    			case Branch:
    				setBranch(value);
    				break;    				    		
    				
    			case Name:
    			case Code:
    				setName(value);
    				break;
    				
    			case Street:
    				setStreet(value);
    				break;
    				
    			case ZipCode:
    				setZipCode(value);
    				break;
    				
    			case City:
    				setCity(value);
    				break;
    				
    			case Country:
    				setCountry(value);
    				break;
    				
    			case Description:
    			setDescription(value);
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
    		return EPartyDetails.valueOf(token);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}
    }

	public String getEori() {
		return eori;
	}
	public void setEori(String eori) {
		this.eori = eori;
	}

	public String getBranch() {
		return bo;
	}
	public void setBranch(String bo) {
		this.bo = bo;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}
	
	public boolean isEmpty() {
		return 	Utils.isStringEmpty(eori) && Utils.isStringEmpty(bo) &&
				Utils.isStringEmpty(name) && Utils.isStringEmpty(street) &&
				Utils.isStringEmpty(zipCode) && Utils.isStringEmpty(city) &&
				Utils.isStringEmpty(country) && Utils.isStringEmpty(description) &&					
				contact == null; 
	}
	
}
