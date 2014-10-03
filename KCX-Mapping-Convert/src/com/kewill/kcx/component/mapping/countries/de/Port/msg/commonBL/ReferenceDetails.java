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

public class ReferenceDetails extends KCXMessage {
		
	private String reference;
	private String date;
	private String dateTime;
	
	private enum EReferenceDetails {	
		Reference,
		Date,		
		DateTime;
   }	

	public ReferenceDetails() {
		super();  
	}

	public ReferenceDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EReferenceDetails) tag) {  			
				default:
  					break;
  			}
  		} else {

  			switch((EReferenceDetails) tag) {   			
  				case Reference:
  					setReference(value);
  					break; 
  				case Date:
  					setDate(value);
  					break; 	  
  				case DateTime:
  					setDateTime(value);
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
  			return EReferenceDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public String getReference() {
		return reference;
	}    
	public void setReference(String value) {
		this.reference = value;
	}	
	
	public String getDate() {
		return this.date;
	}    
	public void setDate(String value) {
		this.date = value;
	}	
	
	public String getDateTime() {
		return this.dateTime;
	}    
	public void setDateTime(String value) {
		this.dateTime = value;
	}	
}

