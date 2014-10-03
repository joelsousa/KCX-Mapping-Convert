package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Invoice Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Invoice extends KCXMessage {
   
    private String amount;	
    private String currency;
    private String exchangeRate;	
    private String relevantTime;
    private String reductionSurcharge;	
    private String discount;
  
  	private enum EImportInvoice {		
  		Amount,			  InvoicePrice,   //BDP 30130425
		Currency,
		ExchangeRate,
		RelevantTime,
		ReductionSurcharge,
		Discount;
  	} 

    public Invoice() {
    	super();    		
    }
    
    public Invoice(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportInvoice) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EImportInvoice) tag) {
  			   
  				case Amount:
  				case InvoicePrice:
  					setAmount(value);
  					break;
  					
  				case Currency:
  					setCurrency(value);
  					break;
  					
  				case ExchangeRate:
  					setExchangeRate(value);
  					break;
  					
  				case RelevantTime:
  					setRelevantTime(value);
  					break;
  				case ReductionSurcharge:
  					setReductionSurcharge(value);
  					break;
  					
  				case Discount:
  					setDiscount(value);
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
  			return EImportInvoice.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getAmount() {
  		return amount;
  	}
  	public void setAmount(String argument) {
  		this.amount = argument;
  	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String argument) {
		this.currency = argument;
	}
	
	public void setExchangeRate(String argument) {
		this.exchangeRate = argument;
	}		
	
	public String getExchangeRate() {
		return this.exchangeRate;
	}
  
	public void setRelevantTime(String argument) {
		this.relevantTime = argument;
	}		
	
	public String getRelevantTime() {
		return this.relevantTime;
	}

	public void setReductionSurcharge(String argument) {
		this.reductionSurcharge = argument;
	}		
		
	public String getReductionSurcharge() {
		return this.reductionSurcharge;
	}	
	
	
	public void setDiscount(String argument) {
		this.discount = argument;
	}		
		
	public String getDiscount() {
		return this.discount;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.amount) && Utils.isStringEmpty(this.currency)); 		      
               	       
	}	
	
}
