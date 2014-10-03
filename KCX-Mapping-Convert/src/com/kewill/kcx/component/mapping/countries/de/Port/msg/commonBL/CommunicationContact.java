package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

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

public class CommunicationContact extends KCXMessage {
		
	private String communicationNumber;
	private String communicationQualifier;
	
	private enum ECommunicationContact {	
		CommunicationNumber,
		CommunicationQualifier;			       			
   }	

	public CommunicationContact() {
		super();  
	}

	public CommunicationContact(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECommunicationContact) tag) {
  			/*
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break; 
  				*/
				default:
  					break;
  			}
  		} else {

  			switch((ECommunicationContact) tag) {   			
  				case CommunicationNumber:
  					setCommunicationNumber(value);
  					break; 
  				case CommunicationQualifier:
  					setCommunicationQualifier(value);
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
  			return ECommunicationContact.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public String getCommunicationNumber() {
		return communicationNumber;
	}    
	public void setCommunicationNumber(String value) {
		this.communicationNumber = value;
	}
	
	
    public void setCommunicationQualifier(String argument) {
		this.communicationQualifier = argument;
	}
	public String getCommunicationQualifier() {
		return communicationQualifier;
	}
		
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.communicationNumber) && Utils.isStringEmpty(this.communicationQualifier);
	}		
}

