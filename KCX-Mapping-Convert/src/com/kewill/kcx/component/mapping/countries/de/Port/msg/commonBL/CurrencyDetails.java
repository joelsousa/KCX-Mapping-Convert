package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

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

public class CurrencyDetails extends KCXMessage {
		
	private String qualifier;
	private String code;
	private String rate;
	
	private enum ECurrencyDetails {	
		EDIQualifier,
		CurrencyCode,		
		CurrencyRateBase;
   }	

	public CurrencyDetails() {
		super();  
	}

	public CurrencyDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECurrencyDetails) tag) {  			
				default:
  					break;
  			}
  		} else {

  			switch((ECurrencyDetails) tag) {   			
  				case EDIQualifier:
  					setEDIQualifier(value);
  					break; 
  				case CurrencyCode:
  					setCurrencyCode(value);
  					break; 	  
  				case CurrencyRateBase:
  					setCurrencyRateBase(value);
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
  			return ECurrencyDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public String getEDIQualifier() {
		return qualifier;
	}    
	public void setEDIQualifier(String value) {
		this.qualifier = value;
	}	
	
	public String getCurrencyCode() {
		return this.code;
	}    
	public void setCurrencyCode(String value) {
		this.code = value;
	}	
	
	public String getCurrencyRateBase() {
		return this.rate;
	}    
	public void setCurrencyRateBase(String value) {
		this.rate = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(rate) && Utils.isStringEmpty(code);	
	}
}

