package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 05.06.2014<br>
 * Description	: diverse Codes.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class DeliveryDestination extends KCXMessage {
	
	 private String countryCode; 
	 private String countrySubEntityIdentifier; 
	 
     public DeliveryDestination() {
  		super();
  	}
     public DeliveryDestination(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum ECountryCodes {
		CountryCode,
		CountrySubEntityIdentifier,
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((ECountryCodes) tag) {
 			default:
 					return;
 			}
 		} else {
 			switch ((ECountryCodes) tag) { 			 			
 				case CountryCode:
 					setCountryCode(value);
 					break; 	
 				case CountrySubEntityIdentifier:
 					setCountrySubEntityIdentifier(value);
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
 			return ECountryCodes.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}
	
 	public String getCountryCode() {
		return countryCode;
	} 	
	public void setCountryCode(String value) {
		this.countryCode = value;
	}
	
	public String getCountrySubEntityIdentifier() {
		return countrySubEntityIdentifier;
	} 	
	public void setCountrySubEntityIdentifier(String value) {
		this.countrySubEntityIdentifier = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.countryCode) && Utils.isStringEmpty(this.countrySubEntityIdentifier);  
	}	
}
