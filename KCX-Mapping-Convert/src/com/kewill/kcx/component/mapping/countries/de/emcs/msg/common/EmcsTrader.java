package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>		
 * Created		: 04.05.2010<br>
 * Description  : EmcsTrader.                
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class EmcsTrader extends KCXMessage {
	
	private String countryCodeISO;	
    private String customerID;
    private String exciseTaxNumber;
    private String vatId;
    private String name;    
    private String street;
    private String streetNumber;
    private String postalCode;  
    private String city;      
    //private String language;   //EI20100615
    
    private enum ETrader {	
		//KIDS:				       			UIDS:				       
		CustomerID,     	                //same
		ExciseTaxNumber,				    //same
		VATID,	   		      				//same
		Name,				    			Address, //Name, StreetAndNumber,usw.   
		Street,						        					
		StreetNumber,						
		PostalCode,
		City,
		CountryCode,
		CountryCodeISO,						
		Language; //ist nicht ganz klar ob statt CountryCodeISO oder zusätzlich zu CountryCodeISO
				  // (was DIEHL schicken wird)	
				  //damit language durch CountryCodeISO nicht überschrieben	wird, mach ich ertsmal beides	 
   }			  //die eigentliche "Umleitung" findet in KidsBody statt, bzw in KidsMessageEMCS.writeTrader(...)
													  
    
	public EmcsTrader() {
  		super();
  	}	
	 	
	private XMLEventReader parser = null;

	public EmcsTrader(XMLEventReader parser) {
		  	super(parser);
		  	this.parser = parser;
	}
			 
	public EmcsTrader(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETrader) tag) {  			
				case Address:
					Address address  = new Address(getScanner());
					address.parse(tag.name());
					setAddress(address);
					break;
										
				default:
  					return;
  			}
  		} else {
  			switch ((ETrader ) tag) {   
  				case CountryCodeISO:
  				case CountryCode:
  					setCountryCodeISO(value);
					break;   			
  				case CustomerID:
  					setCustomerID(value);
  					break;   					  
  				case ExciseTaxNumber:
  					setExciseTaxNumber(value);
  					break; 
  				case VATID:
  					 setVatId(value);
  					 break;
  				case Name:
  					setName(value);
  					break;  					
  				case Street:
  	  				setStreet(value);
  	  				break;
  				case StreetNumber:
 					 setStreetNumber(value);
 					 break;
  				case PostalCode:
 					 setPostalCode(value);
 					 break;
  				case City:
 					 setCity(value);
 					 break;
 					 /*
  				case Language:
					 setLanguage(value);
					 break; 
					 */
  				default:
  					break;
  			}
  		}
		
	}
					      	
	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
  			return ETrader.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}	
	
	public String getCountryCodeISO() {	
		return this.countryCodeISO;
	}
	public void setCountryCodeISO(String argument) {
		this.countryCodeISO = argument;
	}		
	
	public String getCustomerID() {	
		return this.customerID;
	}
	public void setCustomerID(String argument) {
		this.customerID = argument;
	}

	public String getExciseTaxNumber() {	
		return this.exciseTaxNumber;
	}
	public void setExciseTaxNumber(String argument) {
		this.exciseTaxNumber = argument;
	}
	
	public String getVatId() {	
		return this.vatId;
	}
	public void setVatId(String argument) {
		this.vatId = argument;
	}
	
	public String getName() {	
		return this.name;
	}
	public void setName(String argument) {
		this.name = argument;
	}
	public String getStreet() {	
		return this.street;
	}
	public void setStreet(String argument) {
		this.street = argument;
	}
	
	public String getStreetNumber() {	
		return this.streetNumber;
	}
	public void setStreetNumber(String argument) {
		this.streetNumber = argument;
	}
	
	public String getPostalCode() {	
		return this.postalCode;
	}
	public void setPostalCode(String argument) {
		this.postalCode = argument;
	}
	public String getCity() {	
		return this.city;
	}
	public void setCity(String argument) {
		this.city = argument;
	}
	/*
	public String getLanguage() {	
		return this.language;
	}
	public void setLanguage(String argument) {
		this.language = argument;
	}
	*/
	public void setAddress(Address argument) {
		if (argument == null) {
			return;
		}
	    name = argument.getName();    
	    street = argument.getStreet();
	    streetNumber = argument.getHouseNumber();
	    postalCode = argument.getPostalCode();  
	    city = argument.getCity();  
	    //EI20110930: countryCodeISO = argument.getCountry();	
	    //EI20110930: language = argument.getLanguage();	
	    countryCodeISO = argument.getLanguage();   //EI20110930
	}
	
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.countryCodeISO) 
			&& Utils.isStringEmpty(this.customerID) 
			//&& Utils.isStringEmpty(this.language) 
    		&& Utils.isStringEmpty(this.exciseTaxNumber)
    		&& Utils.isStringEmpty(this.vatId)
    		&& Utils.isStringEmpty(this.name) 
    		&& Utils.isStringEmpty(this.street)
    		&& Utils.isStringEmpty(this.streetNumber)    		
    		&& Utils.isStringEmpty(this.postalCode)
    		&& Utils.isStringEmpty(this.city) ){    		
			return true;
		} else {
			return false;
		}
	}

}
