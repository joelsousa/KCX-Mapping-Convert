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

public class CurrencyGroup extends KCXMessage {
		
	private List<Currency> currencyList;
	private String currencyValidationDate;
	
	private enum ECurrencyGroup {	
		Currency,
		CurrencyValidationDate;			       			
   }	

	public CurrencyGroup() {
		super();  
	}

	public CurrencyGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECurrencyGroup) tag) {  		
				case Currency:
					Currency currency = new Currency(getScanner());  	
					currency.parse(tag.name());
					addCurrencyList(currency);
  					break;   				
				default:
  					break;
  			}
  		} else {

  			switch((ECurrencyGroup) tag) {   			  				
  				case CurrencyValidationDate:
  					setCurrencyValidationDate(value);
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
  			return ECurrencyGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public List<Currency> getCurrencyList() {
		return currencyList;
	}    
	public void setCurrencyList(List<Currency> list) {
		this.currencyList = list;
	}
	public void addCurrencyList(Currency value) {
		if (currencyList == null) {
			currencyList = new ArrayList<Currency>();
		}
		this.currencyList.add(value);
	}
	
    public void setCurrencyValidationDate(String argument) {
		this.currencyValidationDate = argument;
	}
	public String getCurrencyValidationDate() {
		return currencyValidationDate;
	}
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(currencyValidationDate) && currencyList == null);	
	}
}

