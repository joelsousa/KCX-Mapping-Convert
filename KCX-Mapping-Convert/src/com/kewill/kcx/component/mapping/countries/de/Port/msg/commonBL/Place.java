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

public class Place extends KCXMessage {
		
	private String unLocode;
	private String text;
	
	private enum EPlace {	
		UNLocode,
		LocationCLearText, LocationClearText;			       		
   }	

	public Place() {
		super();  
	}

	public Place(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPlace) tag) {  			
							
				default:
  					break;
  			}
  		} else {

  			switch((EPlace) tag) {   			
  				case UNLocode:
  					setUnLocode(value);
  					break;  
  				case LocationCLearText:
  				case LocationClearText:
  					setLocationClearText(value);
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
  			return EPlace.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getUnLocode() {
		return unLocode;
	}    
	public void setUnLocode(String value) {
		this.unLocode = value;
	}
		
	public String getLocationClearText() {
		return text;
	}    
	public void setLocationClearText(String value) {
		this.text = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(unLocode) && Utils.isStringEmpty(text); 
	}
}

