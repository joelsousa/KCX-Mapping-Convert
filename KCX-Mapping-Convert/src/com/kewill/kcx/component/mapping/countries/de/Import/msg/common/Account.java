package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 21.09.2011<br>
 * Description	: Contains the Account Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Account extends KCXMessage {

	private String tin;
    private String name;
    private String accountNumber;
    private String dueDate;
    private String regionalFinanceOffice;
    private String paymentType;
    private String kindOfExemption;
    private String amountOfDuty;
    private String defermentAccountType;   //EI20120928
    private String bo;                  //EI20121115 V20 Niederlassungsnummer
    
	private enum EDeferment {
		//KIDS							//UIDS
		TIN,						
		Name,
		AccountNumber,
		DueDate,
		RegionalFinanceOffice,
		PaymentType,
		KindOfExemption,          //eg. A0000, B0000
		DefermentAccountType,     //EI20120928, eg. 0, 1 
		AmountOfDuty,	
		BO,
    }

	public Account() {
		super();  
	}

    public Account(XmlMsgScanner scanner) {
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
  				case Name:  				
  					setName(value);
  					break;
  				case AccountNumber:  				
  					setAccountNumber(value);
  					break;  
  				case DueDate:
  					setDueDate(value);
  					break;  					  				 				
  				case RegionalFinanceOffice:  				
  					setRegionalFinanceOffice(value);
  					break;
  				case PaymentType:  				
  					setPaymentType(value);
  					break;  
  				case KindOfExemption:
  					setKindOfExemption(value);
  					break;  					  				 				
  				case AmountOfDuty:  				
  					setAmountOfDuty(value);
  					break;
  				case DefermentAccountType:
  					setDefermentAccountType(value);    //EI20120928
  					break;
  				case BO:
  					setBO(value);
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

	public String getName() {
		return name;
	}
	public void setName(String argument) {
		name = argument;
	}
		
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String argument) {
		accountNumber = argument;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String argument) {
		dueDate = argument;
	}

	public String getRegionalFinanceOffice() {
		return regionalFinanceOffice;
	}
	public void setRegionalFinanceOffice(String argument) {
		regionalFinanceOffice = argument;
	}
		
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String argument) {
		paymentType = argument;
	}
	
	public String getKindOfExemption() {
		return kindOfExemption;
	}
	public void setKindOfExemption(String argument) {
		kindOfExemption = argument;
	}	
		
	public String getAmountOfDuty() {
		return amountOfDuty;
	}
	public void setAmountOfDuty(String argument) {
		amountOfDuty = argument;
	}
		
	public String getDefermentAccountType() {
		return defermentAccountType;
	}
	public void setDefermentAccountType(String argument) {
		defermentAccountType = argument;
	}	
	
	public String getBO() {  
		return bo;
	}
	public void setBO(String value) {
		this.bo = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(tin) && Utils.isStringEmpty(name) && 
				Utils.isStringEmpty(accountNumber) && Utils.isStringEmpty(dueDate) && 
				Utils.isStringEmpty(regionalFinanceOffice) && Utils.isStringEmpty(paymentType) && 
		        Utils.isStringEmpty(kindOfExemption) && Utils.isStringEmpty(amountOfDuty)); 
	}
	
}
