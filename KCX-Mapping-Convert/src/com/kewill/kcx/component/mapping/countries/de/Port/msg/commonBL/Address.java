package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Address extends KCXMessage {
		
	private PartyRows name;	
	private PartyRows street;
	private String city;
	private String countrySubEntity;
	private String postalCode;
	private String countryCode;
	
	private enum EAddress {	
		PartyName,
		StreetOrBox,
		City,
		CountrySubEntity,
		PostalCode,
		CountryCode;			       		
   }	

	public Address() {
		super();  
	}

	public Address(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAddress) tag) {  			
  				case PartyName:
  					name = new PartyRows(getScanner());  	
  					name.parse(tag.name());				
  					break;
  				case StreetOrBox:
  					street = new PartyRows(getScanner());  	
  					street.parse(tag.name());				
  					break;
				default:
  					break;
  			}
  		} else {

  			switch((EAddress) tag) {   			
  				case City:
  					setCity(value);
  					break; 
  				case PostalCode:
  					setPostalCode(value);
  					break; 
  				case CountrySubEntity:
  					setCountrySubEntity(value);
  					break; 
  				case CountryCode:
  					setCountryCode(value);
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
  			return EAddress.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public PartyRows getPartyName() {
		return name;
	}    
	public void setPartyName(PartyRows value) {
		this.name = value;
	}
	public void setPartyName(String name) {
		if (this.name == null) {
			this.name = new PartyRows();
		}
		this.name.setFirstRow(name);
	}
	
	public PartyRows getStreetOrBox() {
		return street;
	}    
	public void setStreetOrBox(PartyRows value) {
		this.street = value;
	}
	public void setStreetOrBox(String name) {
		if (this.street == null) {
			this.street = new PartyRows();
		}
		this.street.setFirstRow(name);
	}
	
	public String getCity() {
		return city;
	}    
	public void setCity(String value) {
		this.city = value;
	}
	public String getCountrySubEntity() {
		return countrySubEntity;
	}    
	public void setCountrySubEntity(String value) {
		this.countrySubEntity = value;
	}
	public String getPostalCode() {
		return postalCode;
	}    
	public void setPostalCode(String value) {
		this.postalCode = value;
	}
	public String getCountryCode() {
		return countryCode;
	}    
	public void setCountryCode(String value) {
		this.countryCode = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(city) && Utils.isStringEmpty(postalCode) &&
		Utils.isStringEmpty(countryCode) && Utils.isStringEmpty(countrySubEntity) &&
		name == null && street == null; 
	}
	
}

