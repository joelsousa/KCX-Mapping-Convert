package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MonetaryAmount extends KCXMessage {
		
	private String qualifier;
	private String value;
	private String currencyCode;
	private String status;
	
	private enum EMonetaryAmount {	
		Qualifier, EDIQualifier,
		Value,
		CurrencyCode,
		Status;
   }	

	public MonetaryAmount() {
		super();  
	}

	public MonetaryAmount(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EMonetaryAmount) tag) {
  			/*
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break; 
  				*/
				default:
  					break;
  			}
  		} else {

  			switch((EMonetaryAmount) tag) {   			
  				case Qualifier:
  				case EDIQualifier:
  					setEdiQualifier(value);
  					break; 
  				case Value:
  					setValue(value);
  					break; 	
  				case CurrencyCode:
  					setCurrencyCode(value);
  					break; 
  				case Status:
  					setStatus(value);
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
  			return EMonetaryAmount.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public String getEdiQualifier() {
		return qualifier;
	}    
	public void setEdiQualifier(String value) {
		this.qualifier = value;
	}	
	
	public String getValue() {
		return this.value;
	}    
	public void setValue(String value) {
		this.value = value;
	}	
	
	public String getCurrencyCode() {
		return currencyCode;
	}    
	public void setCurrencyCode(String value) {
		this.currencyCode = value;
	}	
	
	public String getStatus() {
		return status;
	}    
	public void setStatus(String value) {
		this.status = value;
	}	
}

