package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 04.06.2014<br>
 * Description	: PartyIE.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class PartyIE extends KCXMessage {
	
	private String statusCode;			
    private String identityIdentifier;
    private String nameText;
    private AddressIE address;
   
 
	private enum EPartyIE {	
		StatusCode,
		IdentityIdentifier,
		NameText,
		Address;
   }	

	public PartyIE() {
		super();  
	}
	public PartyIE(String person) {
		super();  		
	}	

	public PartyIE(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public PartyIE(XmlMsgScanner scanner, String person) {
  		super(scanner);  		
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPartyIE) tag) {
				case Address:
  					address = new AddressIE(getScanner());  	
  					address.parse(tag.name());
  					break;   				
				default:
  					return;
  			}
  		} else {
  			switch((EPartyIE) tag) { 
  			
  				case StatusCode:  				
  					setStatusCode(value);
  					break;   				
  				case IdentityIdentifier:
  					setIdentityIdentifier(value);
  					break;   					
  				case NameText:
  					setNameText(value);
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
  			return EPartyIE.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public void setStatusCode(String argument) {
		this.statusCode = argument;
	}
	public String getStatusCode() {
		return statusCode;
	}
    
    public void setIdentityIdentifier(String argument) {
		this.identityIdentifier = argument;
	}
	public String getIdentityIdentifier() {
		return identityIdentifier;
	}
	
	public void setNameText(String argument) {
		this.nameText = argument;
	}
	public String getNameText() {
		return nameText;
	}
	
	public AddressIE getAddress() {
		return address;
	}
    public void setAddress(AddressIE argument) {
		this.address = argument;
	}
       
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.statusCode) && Utils.isStringEmpty(this.identityIdentifier) && 
			Utils.isStringEmpty(this.nameText) && 
		   (this.address  == null || (this.address  != null && this.address.isEmpty()));
	}    
}
