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
public class Payment extends KCXMessage {
   
    private String amount;	
    private String currency;
    private String rate;	
    private String code;    
 
  	private enum EPayment {		
  		Amount,			
		Currency,
		Rate,		
		Code;
  	} 

    public Payment() {
    	super();    		
    }
    
    public Payment(XmlMsgScanner scanner) {
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
  			   
  				case Amount:
  					setAmount(value);
  					break;
  					
  				case Currency:
  					setCurrency(value);
  					break;
  					
  				case Rate:
  					setRate(value);
  					break;
  					
  				case Code:
  					setCode(value);
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
	
	public void setRate(String argument) {
		this.rate = argument;
	}		
	
	public String getRate() {
		return this.rate;
	}
  
	public void setCode(String argument) {
		this.code = argument;
	}		
	
	public String getCode() {
		return this.code;
	}

	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.amount) && Utils.isStringEmpty(this.currency)); 		      
               	       
	}	
	
}
