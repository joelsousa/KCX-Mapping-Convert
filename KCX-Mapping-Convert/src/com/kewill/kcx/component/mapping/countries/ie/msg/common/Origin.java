package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 05.06.2014<br>
* Description	: Origin.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Origin extends KCXMessage {
	
	private String originCountryIdentifier;
	private String originRegionIdentifier;
      
    private enum EOrigin {    	
    	OriginCountryIdentifier,    	   	
    	OriginRegionIdentifier;    	
    }

    public Origin() {
	      	super();	       
    }
    
    public Origin(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EOrigin) tag) {    			    				    		
    			default:
    					return;
    			}
    		} else {
    			switch ((EOrigin) tag) {
    				case OriginCountryIdentifier:
    					setOriginCountryIdentifier(value);
    					break;    					
    				case OriginRegionIdentifier:
    					setOriginRegionIdentifier(value);
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
    			return EOrigin.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
    public String getOriginCountryIdentifier() {
		return originCountryIdentifier;
	}
	public void setOriginCountryIdentifier(String value) {
		this.originCountryIdentifier = value;
	}
	
	public String getOriginRegionIdentifier() {
		return originRegionIdentifier;
	}
	public void setOriginRegionIdentifier(String value) {
		this.originRegionIdentifier = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(originCountryIdentifier) && 
			 Utils.isStringEmpty(originRegionIdentifier);				
	}
   
}
