package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Deferment Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Deferment extends KCXMessage {

	private String tin;
    private String type;
    private String customerId;
    private String bo;                  //EI20121115 V20 Niederlassungsnummer
    private String accountNumber;       //EI20130227 JIRA KCXSM-42
  
	private enum EDeferment {
		//KIDS							//UIDS
		TIN,
		BO,
		CustomerIdentifier,	CustomerID,		
		Type,
		AccountNumber,
    }

	public Deferment() {
		super();  
	}

    public Deferment(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDeferment) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EDeferment) tag) {
  				case TIN:
  					setTIN(value);
  					break;  					  				 				
  				case CustomerIdentifier:
  				case CustomerID:
  					setCustomerId(value);
  					break;
  				case Type:  				
  					setType(value);
  					break;  
  				case BO:
  					setBO(value);
  					break;
  				case AccountNumber:
  					setAccountNumber(value);
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
  			return EDeferment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getTIN() {
		return tin;
	}
	public void setTIN(String argument) {
		tin = argument;
	}

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String argument) {
		customerId = argument;
	}
		
	public String getType() {
		return type;
	}
	public void setType(String argument) {
		type = argument;
	}
	
	
	public String getBO() {  
		return bo;
	}
	public void setBO(String value) {
		this.bo = value;
	}
	
	public String getAccountNumber() {  
		return accountNumber;
	}
	public void setAccountNumber(String value) {
		this.accountNumber = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.tin) && Utils.isStringEmpty(this.type) && 
		        Utils.isStringEmpty(this.customerId) &&  Utils.isStringEmpty(this.accountNumber)); 
	}
	
}
