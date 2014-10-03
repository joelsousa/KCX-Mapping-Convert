package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : Export/aes.<br>
 * Created      : 2.07.2012<br>
 * Description	: Kids Version 2.0.00
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class Reentry extends KCXMessage {

    private String country;			
   
  	private enum EReentry {
  	// Kids-TagNames, 			UIDS-TagNames
  		Country;																	
  	} 

    public Reentry() {
    	super();    		
    }
    
    public Reentry(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EReentry) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EReentry) tag) {  			
  				case Country:  				
  					setCountry(value);
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
  			return EReentry.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}  

	public String getCountry() {
		return country;
	}
	public void setCountry(String value) {
		this.country = value;
	}	
	
	public boolean isEmpty() {
		return  Utils.isStringEmpty(country);				
	}
}
