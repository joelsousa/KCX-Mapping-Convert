package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 20.09.2011<br>
 * Description	: Contains the Manifest Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Manifest extends KCXMessage {

	private String 	registrationNumber;
    private String 	itemNumber;    
    private String 	numberOfPieces; 
    private TIN 	custodian;
    private Number  specificKey;        
    private String  atlasAlignment;
    
	private enum EImportDocument {
		RegistrationNumber,				
		ItemNumber,					
		NumberOfPieces,		
		Custodian, CustodianTIN,
		SpecificKey, 		
		ATLASAlignment;
    }
	
	public Manifest() {
		super();  
	}

    public Manifest(XmlMsgScanner scanner) {
  		super(scanner);
  	}  	
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportDocument) tag) {
  			case Custodian:
  			case CustodianTIN:
  				custodian = new TIN(getScanner());
  				custodian.parse(tag.name());
					break;	
  			case SpecificKey:
  				specificKey = new Number(getScanner());
  				specificKey.parse(tag.name());
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportDocument) tag) {
  				case RegistrationNumber:
  					setRegistrationNumber(value);
  					break;
  				case ItemNumber:
  	  				setItemNumber(value);
  	  				break;  					  								
  				case NumberOfPieces:  				
  					setNumberOfPieces(value);
  					break;
  				case ATLASAlignment:
  					setATLASAlignment(value);
  					break;	
  				
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EImportDocument.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String argument) {
		registrationNumber = argument;
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String value) {
		this.itemNumber = value;
	}
	
	public String getNumberOfPieces() {
		return numberOfPieces;
	}
	public void setNumberOfPieces(String argument) {
		numberOfPieces = argument;
	}
	
	public void setCustodian(TIN argument) {
		this.custodian = argument;
	}			
	public TIN getCustodian() {
		return this.custodian;
	}
	
	public void setSpecificKey(Number argument) {
		this.specificKey = argument;
	}			
	public Number getSpecificKey() {
		return this.specificKey;
	}
	
	public void setATLASAlignment(String argument) {
		this.atlasAlignment = argument;
	}			
	public String getATLASAlignment() {
		return this.atlasAlignment;
	}
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.registrationNumber) && Utils.isStringEmpty(this.itemNumber) && 
				Utils.isStringEmpty(this.numberOfPieces) && Utils.isStringEmpty(this.atlasAlignment) && 
				custodian == null && specificKey == null);	        				
	}    
	   
}
