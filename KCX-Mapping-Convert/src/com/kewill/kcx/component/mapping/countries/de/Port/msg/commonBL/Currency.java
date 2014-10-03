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

public class Currency extends KCXMessage {
		
	private List<CurrencyDetails> currencyDetailList;
	private String rate;
	
	private enum ECurrency {	
		CurrencyDetail,
		RateOfExchange;			       		
   }	

	public Currency() {
		super();  
	}

	public Currency(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECurrency) tag) {  			
  			case CurrencyDetail:
  				CurrencyDetails temp = new CurrencyDetails(getScanner());  	
				temp.parse(tag.name());
				addCurrencyDetailList(temp);
  				break;
				default:
  					break;
  			}
  		} else {

  			switch((ECurrency) tag) {   			
  				case RateOfExchange:
  					setRateOfExchange(value);
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
  			return ECurrency.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getRateOfExchange() {
		return rate;
	}    
	public void setRateOfExchange(String value) {
		this.rate = value;
	}
		
	public List<CurrencyDetails> getCurrencyDetailList() {
		return currencyDetailList;
	}    
	public void setCurrencyDetailList(List<CurrencyDetails> list) {
		this.currencyDetailList = list;
	}
	public void addCurrencyDetailList(CurrencyDetails value) {
		if (currencyDetailList == null) {
			currencyDetailList = new ArrayList<CurrencyDetails>();
		}
		this.currencyDetailList.add(value);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(rate) && currencyDetailList == null);	
	}
}

