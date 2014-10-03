package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 04.06.2014<br>
* Description	: CurrencyExchange.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CurrencyExchange extends KCXMessage {
	
	private String rateNumeric;
	private String currencyTypeIdentifier;
      
    private enum ECurrencyExchangeIE {    	
    	RateNumeric,    	   	
    	CurrencyTypeIdentifier;
    }

    public CurrencyExchange() {
	      	super();	       
    }
    
    public CurrencyExchange(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECurrencyExchangeIE) tag) {    			    				    		
    			default:
    					return;
    			}
    		} else {
    			switch ((ECurrencyExchangeIE) tag) {
    				case RateNumeric:
    					setRateNumeric(value);
    					break;    					
    				case CurrencyTypeIdentifier:
    					setCurrencyTypeIdentifier(value);
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
    			return ECurrencyExchangeIE.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
    public String getRateNumeric() {
		return rateNumeric;
	}
	public void setRateNumeric(String value) {
		this.rateNumeric = value;
	}
	
	public String getCurrencyTypeIdentifier() {
		return currencyTypeIdentifier;
	}
	public void setCurrencyTypeIdentifier(String value) {
		this.currencyTypeIdentifier = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(rateNumeric) && 
			 Utils.isStringEmpty(currencyTypeIdentifier);				
	}
   
}
