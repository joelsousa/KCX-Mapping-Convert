package com.kewill.kcx.component.mapping.countries.de.Import20.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Document Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class PreviousDocument extends KCXMessage {

	private String type;
    private String number;    
    private String additionalInformation; 		
    private String registrationNumber;			//EI20130903 BDP
    private String registrationItemNumber;   	//EI20130903 BDP     
    private String registrationAmount;			//EI20130903 BDP
    private TIN custodyTIN;						//EI20130903 BDP
    private String registrationCollectiveTerm1; //EI20130903 BDP
    private String registrationCollectiveTerm2; //EI20130903 BDP
    
	private enum EImportPrevDocument {
		Type,				
		Number,					
		AdditionalInformation,		
		RegistrationNumber,
		RegistrationItemNumber, 
		RegistrationAmount,
		CustodyTIN,
		RegistrationCollectiveTerm1,
		RegistrationCollectiveTerm2;
    }
	
	public PreviousDocument() {
		super();  
	}

    public PreviousDocument(XmlMsgScanner scanner) {
  		super(scanner);
  	}  	
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportPrevDocument) tag) {
  			case CustodyTIN:
  				custodyTIN = new TIN(getScanner());
  				custodyTIN.parse(tag.name());
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportPrevDocument) tag) {
  				case Type:
  					setType(value);
  					break;
  				case Number:
  	  				setNumber(value);
  	  				break;  					  								
  				case AdditionalInformation:  				
  					setAdditionalInformation(value);
  					break;
  				case RegistrationNumber:
  					setRegistrationNumber(value);
  					break;	
  				case RegistrationItemNumber:
  					setRegistrationItemNumber(value);
  					break;	
  				case RegistrationAmount:
  					setRegistrationAmount(value);
  					break;  					
  				case RegistrationCollectiveTerm1:
  					setRegistrationCollectiveTerm1(value);
  					break;
  				case RegistrationCollectiveTerm2:
  					setRegistrationCollectiveTerm2(value);
  					break;
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EImportPrevDocument.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return type;
	}
	public void setType(String argument) {
		type = argument;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String value) {
		this.number = value;
	}
	
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String argument) {
		additionalInformation = argument;
	}
	
	public void setRegistrationNumber(String argument) {
		this.registrationNumber = argument;
	}			
	public String getRegistrationNumber() {
		return this.registrationNumber;
	}
	
	public void setRegistrationItemNumber(String argument) {
		this.registrationItemNumber = argument;
	}			
	public String getRegistrationItemNumber() {
		return this.registrationItemNumber;
	}
	
	public void setRegistrationAmount(String argument) {
		this.registrationAmount = argument;
	}			
	public String getRegistrationAmount() {
		return this.registrationAmount;
	}
	
	public void setRegistrationCollectiveTerm1(String argument) {
		this.registrationCollectiveTerm1 = argument;
	}			
	public String getRegistrationCollectiveTerm1() {
		return this.registrationCollectiveTerm1;
	}	
	 
	
	public void setRegistrationCollectiveTerm2(String argument) {
		this.registrationCollectiveTerm2 = argument;
	}			
	public String getRegistrationCollectiveTerm2() {
		return this.registrationCollectiveTerm2;
	}	
	
	public TIN getCustodyTIN() {
		return custodyTIN;
	}
	public void setCustodyTIN(TIN custodyTIN) {
		this.custodyTIN = custodyTIN;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.type) && Utils.isStringEmpty(this.number) && 
		        Utils.isStringEmpty(this.additionalInformation) && Utils.isStringEmpty(this.registrationNumber) && 		        
		        Utils.isStringEmpty(this.registrationItemNumber) && Utils.isStringEmpty(this.registrationAmount) &&
		        Utils.isStringEmpty(this.registrationCollectiveTerm1) && Utils.isStringEmpty(this.registrationCollectiveTerm2) &&
		        custodyTIN == null);
	}    	
}
