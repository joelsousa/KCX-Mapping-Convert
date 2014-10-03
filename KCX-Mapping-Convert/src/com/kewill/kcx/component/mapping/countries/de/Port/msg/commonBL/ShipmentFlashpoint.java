package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class ShipmentFlashpoint extends KCXMessage {
		
	private String flashpoint;
	private String flashpointQualifier;
	
	private enum EContact {	
		Flashpoint,
		FlashpointQualifier;			       		
   }	

	public ShipmentFlashpoint() {
		super();  
	}

	public ShipmentFlashpoint(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EContact) tag) {  							   			
				default:
  					break;
  			}
  		} else {

  			switch((EContact) tag) {   			
  				case Flashpoint:
  					setFlashpoint(value);
  					break;
  				case FlashpointQualifier:
  					setFlashpointQualifier(value);
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
  			return EContact.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getFlashpoint() {
		return flashpoint;
	}    
	public void setFlashpoint(String value) {
		this.flashpoint = value;
	}
		
	public String getFlashpointQualifier() {
		return flashpointQualifier;
	}    
	public void setFlashpointQualifier(String value) {
		this.flashpointQualifier = value;
	}
}

