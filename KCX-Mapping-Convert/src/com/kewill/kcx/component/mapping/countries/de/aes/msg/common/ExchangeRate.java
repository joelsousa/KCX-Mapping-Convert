package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 16.07.2012<br>
 * Description	: for UIDS only.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class ExchangeRate extends KCXMessage {

    private String rate;			
    private String currency;	//n(15,2)	
   
 
  	private enum EExchangeRate {
  		Rate,	  	
  		Currency;
  	} 

    public ExchangeRate() {
    	super();    		
    }
    
    public ExchangeRate(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EExchangeRate) tag) {
  				default:
  					return;
  			}
  		} else {
  			switch ((EExchangeRate) tag) {  			   
  				case Rate:
  					setRate(value);
  					break;  				
  				case Currency:
  					setCurrency(value);
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
  			return EExchangeRate.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	
	public String getRate() {
		return rate;
	}
	public void setRate(String argument) {
		this.rate = argument;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String argument) {
		this.currency = argument;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.rate) &&  
		        Utils.isStringEmpty(this.currency));       
	}

}
