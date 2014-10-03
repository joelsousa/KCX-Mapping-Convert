package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Duty Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Duties extends KCXMessage {
   
    private String type;
    private String amount;	   
    private List<String> rateList;	
  
  	private enum EPayment {	
  		Type,
  		Amount,					
		Rate;		
  	} 

    public Duties() {
    	super();    		
    }
    
    public Duties(XmlMsgScanner scanner) {
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
  				case Rate:  						
  					this.addRateList(value);
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
  		
	public void setRate(List<String> list) {
		rateList = list;
	}			
	public List<String> getRateList() {
		return rateList;
	}
	public void addRateList(String rate) {
		if (rateList == null) {
			rateList = new Vector<String>();
		}
		rateList.add(rate);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && 
				Utils.isStringEmpty(amount) && 
				(rateList == null)); 		                     	      
	}	
	
}
