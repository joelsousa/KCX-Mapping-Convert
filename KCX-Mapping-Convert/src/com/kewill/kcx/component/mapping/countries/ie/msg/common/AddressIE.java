package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 04.06.2014<br>
 * Description	: AddressIE
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class AddressIE extends KCXMessage {
	
	private String lineText;			
    private String cityName;
    private String postcodeIdentifier;
    private String countryCode;
   
 
	private enum EAddressIE {	
		LineText,
		CityName,
		PostcodeIdentifier,
		CountryCode;
   }	

	 public AddressIE() {
	      	super();	       
	 }
 
	 public AddressIE(XmlMsgScanner scanner) {
		 super(scanner);    	
	 }
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAddressIE) tag) {
			
				default:
  					return;
  			}
  		} else {
  			switch((EAddressIE) tag) { 
  			
  				case LineText:  				
  					setLineText(value);
  					break;   				
  				case CityName:
  					setCityName(value);
  					break;   					
  				case PostcodeIdentifier:
  					setPostcodeIdentifier(value);
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
  			return EAddressIE.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	
	public String getLineText() {
		return lineText;
	}
	public void setLineText(String lineText) {
		this.lineText = lineText;
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPostcodeIdentifier() {
		return postcodeIdentifier;
	}
	public void setPostcodeIdentifier(String postcodeIdentifier) {
		this.postcodeIdentifier = postcodeIdentifier;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(this.lineText) && Utils.isStringEmpty(this.cityName) && 
		Utils.isStringEmpty(this.postcodeIdentifier) && Utils.isStringEmpty(this.countryCode);
	}    
}
