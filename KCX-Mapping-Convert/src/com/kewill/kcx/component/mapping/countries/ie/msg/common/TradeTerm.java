package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 04.06.2014<br>
* Description	: TradeTerm.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class TradeTerm extends KCXMessage {
	
	private String conditionCode;
	private String locationNameText;
	private String countryRelationshipCode;
      
    private enum ETradeTerm {
    	ConditionCode,    	   	
    	LocationNameText,
    	CountryRelationshipCode;
    }

    public TradeTerm() {
	      	super();	       
    }
    
    public TradeTerm(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ETradeTerm) tag) {    			    				    		
    				default:
    					return;
    			}
    		} else {
    			switch ((ETradeTerm) tag) {    				
    				case ConditionCode:
    					setConditionCode(value);
    					break;    					
    				case LocationNameText:
    					setLocationNameText(value);
    					break; 
    				case CountryRelationshipCode:
    					setCountryRelationshipCode(value);
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
    			return ETradeTerm.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(String value) {
		this.conditionCode = value;
	}
	
	public String getLocationNameText() {
		return locationNameText;
	}
	public void setLocationNameText(String value) {
		this.locationNameText = value;
	}
	
	public String getCountryRelationshipCode() {
		return countryRelationshipCode;
	}
	public void setCountryRelationshipCode(String value) {
		this.countryRelationshipCode = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(conditionCode) && Utils.isStringEmpty(locationNameText) &&
		Utils.isStringEmpty(countryRelationshipCode);				
	}

}
