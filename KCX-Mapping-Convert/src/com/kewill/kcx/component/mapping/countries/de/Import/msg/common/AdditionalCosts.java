package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the AdditionalCosts Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class AdditionalCosts extends KCXMessage {
   
    private String type;
    private String amount;	
    private String currency;
    private String iataRateCode;	
    private String rateAgreedCode;	
    private String rate;	
    private String date; 
    private String percentageFreightCost;	
 
  	private enum EPayment {	
  		Type,
  		Amount,			
		Currency,
		IATARateCode,
		RateAgreedCode,
		Rate,		
		Date,
		PercentageFreightCost;
  	} 

    public AdditionalCosts() {
    	super();    		
    }
    
    public AdditionalCosts(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPayment) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EPayment) tag) {
  				case Type:
					setType(value);
					break;
  				case Amount:
  					setAmount(value);
  					break;  					
  				case Currency:
  					setCurrency(value);
  					break;
  				case IATARateCode:
  					setIATARateCode(value);
  					break;	
  				case RateAgreedCode:
  					setRateAgreedCode(value);
  					break;	
  				case Rate:
  					setRate(value);
  					break;  				
  				case Date:
  					setDate(value);
  					break;
  				case PercentageFreightCost:
  					setPercentageFreightCost(value);
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
  			return EPayment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getType() {
  		return type;
  	}
  	public void setType(String argument) {
  		this.type = argument;
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
	
	public String getIATARateCode() {
  		return iataRateCode;
  	}
  	public void setIATARateCode(String argument) {
  		this.iataRateCode = argument;
  	}
  		
  	public String getRateAgreedCode() {
  		return rateAgreedCode;
  	}
  	public void setRateAgreedCode(String argument) {
  		this.rateAgreedCode = argument;
  	}
  	
	public void setRate(String argument) {
		this.rate = argument;
	}			
	public String getRate() {
		return this.rate;
	}
  
	public void setDate(String argument) {
		this.date = argument;
	}			
	public String getDate() {
		return this.date;
	}
	
	public String getPercentageFreightCost() {
  		return percentageFreightCost;
  	}
  	public void setPercentageFreightCost(String argument) {
  		this.percentageFreightCost = argument;
  	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.amount) && Utils.isStringEmpty(this.currency)); 		      
               	       
	}	
	
}
