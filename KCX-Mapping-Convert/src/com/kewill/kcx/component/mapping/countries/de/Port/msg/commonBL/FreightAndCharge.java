package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

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

public class FreightAndCharge extends KCXMessage {
		
	private String freightAndChargeId;
	private String chargeText;
	private String indicator;
	private String itemNumber;
	private String rate;
	private String rateText;
	private Place place;
	private Currency currency;	
	private List<MonetaryAmount> monetaryAmountList;
	
	private enum EFreightAndCharge {	
		FreightAndChargeId,
		FreightAndChargeText,
		PrepaidCollectIndicator,
		ItemNumber,
		RateOrTariffClassId,
		RateOrTariffClassText,
		Place,
		Currency,
		MonetaryAmount;			       		
   }	

	public FreightAndCharge() {
		super();  
	}

	public FreightAndCharge(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EFreightAndCharge) tag) {  							 
				case Currency:
					currency = new Currency(getScanner());	
					currency.parse(tag.name());			
					break;
				case Place:
					place = new Place(getScanner());	
					place.parse(tag.name());						
					break;
				case MonetaryAmount:
					MonetaryAmount temp = new MonetaryAmount(getScanner());	
					temp.parse(tag.name());	
					addMonetaryAmountList(temp);
					break;
				default:
  					break;
  			}
  		} else {

  			switch((EFreightAndCharge) tag) {   			
  				case FreightAndChargeId:
  					setFreightAndChargeId(value);
  					break;
  				case FreightAndChargeText:
  					setFreightAndChargeText(value);
  					break;
  				case PrepaidCollectIndicator:
  					setPrepaidCollectIndicator(value);
  					break;
  				case ItemNumber:
  					setItemNumber(value);
  					break;  					  				
  				case RateOrTariffClassId:
  					setRateOrTariffClassId(value);
  					break;  					
  				case RateOrTariffClassText:
  					setRateOrTariffClassText(value);
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
  			return EFreightAndCharge.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getFreightAndChargeId() {
		return freightAndChargeId;
	}    
	public void setFreightAndChargeId(String value) {
		this.freightAndChargeId = value;
	}
		
	public String getFreightAndChargeText() {
		return chargeText;
	}    
	public void setFreightAndChargeText(String value) {
		this.chargeText = value;
	}
    
    public String getPrepaidCollectIndicator() {
		return indicator;
	}		
    public void setPrepaidCollectIndicator(String argument) {
		this.indicator = argument;
	}
    
    public String getItemNumber() {
		return itemNumber;
	}		
    public void setItemNumber(String argument) {
		this.itemNumber = argument;
	}
   
    public String getRateOrTariffClassId() {
		return rate;
	}    
	public void setRateOrTariffClassId(String value) {
		this.rate = value;
	}		
    
	 public String getRateOrTariffClassText() {
		return rateText;
	}    
	public void setRateOrTariffClassText(String value) {
		this.rateText = value;
	}	
		
    public Currency getCurrency() {
		return currency;
	}    
	public void setCurrency(Currency value) {
		this.currency = value;
	}
		
	public Place getPlace() {
		return place;
	}		
    public void setPlace( Place argument) {
		this.place = argument;
	}   
    
    public  List<MonetaryAmount> getMonetaryAmountList() {
		return monetaryAmountList;
	}		
    public void setMonetaryAmountList( List<MonetaryAmount> argument) {
		this.monetaryAmountList = argument;
	}
    public void addMonetaryAmountList(MonetaryAmount argument) {
    	if (monetaryAmountList == null) {
    		monetaryAmountList = new ArrayList<MonetaryAmount>();
    	}
		this.monetaryAmountList.add(argument);
	}
}

