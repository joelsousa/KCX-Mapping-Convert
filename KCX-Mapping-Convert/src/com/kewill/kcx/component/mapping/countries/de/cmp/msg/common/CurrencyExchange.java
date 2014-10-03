package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: CurrencyExchange.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CurrencyExchange extends KCXMessage {
	
	private String currencyCode;
	private String marketId;
    private String conversionRate;
       
    private enum ECurrencyExchange {
    	SourceCurrencyCode,
    	TargetCurrencyCode,
    	MarketID,    	
    	ConversionRate;
    }

    public CurrencyExchange() {
	      	super();	       
    }
    
    public CurrencyExchange(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECurrencyExchange) tag) {    			    				    		
    			default:
    					return;
    			}
    		} else {
    			switch ((ECurrencyExchange) tag) {
    				case SourceCurrencyCode :
    				case TargetCurrencyCode:
    					setCurrencyCode(value);
    					break;

    				case MarketID:
    					setMarketId(value);
    					break;
    					
    				case ConversionRate:
    					setConversionRate(value);
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
    			return ECurrencyExchange.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String value) {
		this.currencyCode = value;
	}
	
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String value) {
		this.marketId = value;
	}

	public String getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(String value) {
		this.conversionRate = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(currencyCode) && 
			Utils.isStringEmpty(marketId) && Utils.isStringEmpty(conversionRate);				
	}

}
