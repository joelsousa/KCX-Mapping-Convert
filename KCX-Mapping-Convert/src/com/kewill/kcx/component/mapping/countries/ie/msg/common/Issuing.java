package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 04.06.2014<br>
 * Description	: Issuing.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Issuing extends KCXMessage {
	
	 private String place;   	
	
     public Issuing() {
  		super();
  	}
     public Issuing(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum EPlaceIE {
		Place;	
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((EPlaceIE) tag) {
 				default:
 					return;
 			}
 		} else {
 			switch ((EPlaceIE) tag) { 			
 				case Place:
 					setPlace(value);
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
 			return EPlaceIE.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}

	
 	public String getPlace() {
		return place;
	} 	
	public void setPlace(String argument) {
		this.place = argument;
	}
	
	public boolean isEmpty() {
		return  Utils.isStringEmpty(this.place);  
	}	
}
