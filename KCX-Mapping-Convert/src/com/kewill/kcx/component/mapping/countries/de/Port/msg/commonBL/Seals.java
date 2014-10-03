package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Seals extends KCXMessage {
		
	private String sealingPartyCode;
	private String sealingParty;
	private String sealingNumber;
	
	private enum ESeals {	
		SealingPartyCode,
		SealingParty,
		SealingNumber;			       		
   }	

	public Seals() {
		super();  
	}

	public Seals(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ESeals) tag) {  									
				default:
  					break;
  			}
  		} else {

  			switch((ESeals) tag) {   			
  				case SealingPartyCode:
  					setSealingPartyCode(value);
  					break; 
  				case SealingParty:
  					setSealingParty(value);
  					break;
  				case SealingNumber:
  					setSealingNumber(value);
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
  			return ESeals.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getSealingPartyCode() {
		return sealingPartyCode;
	}    
	public void setSealingPartyCode(String value) {
		this.sealingPartyCode = value;
	}
	
	public String getSealingParty() {
		return sealingParty;
	}    
	public void setSealingParty(String value) {
		this.sealingParty = value;
	}
	
	public String getSealingNumber() {
		return sealingNumber;
	}    
	public void setSealingNumber(String value) {
		this.sealingNumber = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(sealingPartyCode) && Utils.isStringEmpty(sealingParty) &&		
		Utils.isStringEmpty(sealingNumber);
	}	
}

