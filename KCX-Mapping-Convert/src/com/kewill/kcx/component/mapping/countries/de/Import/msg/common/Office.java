package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 21.09.2011<br>
 * Description	: Contains the Office Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Office extends KCXMessage {

	private String code;
    private String name;
    private String officer;
    private String phone;
    private String accountNumber;
    private String bankId;
    private String bank;
    private String iban;
    private String bic;
    
  
	private enum EDeferment {
		//KIDS							//UIDS
		Code,	
		Name,
		Officer,	
		Phone,	
		AccountNumber,	
		BankID,	
		Bank,	
		IBAN,
		BIC;	
    }

	public Office() {
		super();  
	}

    public Office(XmlMsgScanner scanner) {
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
  				case Code:
  					setCode(value);
  					break; 
  				case Name:  				
  					setName(value);
  					break;
  				case Officer:
  					setOfficer(value);
  					break;
  				case Phone:
  					setPhone(value);
  					break;
  				case AccountNumber:
  					setAccountNumber(value);
  					break;
  				case BankID:  				
  					setBankID(value);
  					break;  	
  				case Bank:  				
  					setBank(value);
  					break; 
  				case IBAN:  				
  					setIBAN(value);
  					break;  	
  				case BIC:  				
  					setBIC(value);
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

	public String getCode() {
		return code;
	}
	public void setCode(String argument) {
		code = argument;
	}

	public String getName() {
		return name;
	}
	public void setName(String argument) {
		name = argument;
	}
	
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String argument) {
		officer = argument;
	}
		
	public String getPhone() {
		return phone;
	}
	public void setPhone(String argument) {
		phone = argument;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String argument) {
		accountNumber = argument;
	}
	
	
	public String getBankID() {
		return bankId;
	}
	public void setBankID(String argument) {
		bankId = argument;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String argument) {
		bank = argument;
	}
	
	public String getIBAN() {
		return iban;
	}
	public void setIBAN(String argument) {
		iban = argument;
	}
	
	public String getBIC() {
		return bic;
	}
	public void setBIC(String argument) {
		bic = argument;
	}
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.code) && Utils.isStringEmpty(this.name) && 
				Utils.isStringEmpty(this.officer) && Utils.isStringEmpty(this.phone) &&
				Utils.isStringEmpty(this.accountNumber) && 
				Utils.isStringEmpty(this.bank) && Utils.isStringEmpty(this.bank) &&
		        Utils.isStringEmpty(this.iban) && Utils.isStringEmpty(this.bic)); 
	}
  
}
