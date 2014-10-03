package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;
import java.util.ArrayList;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 17.06.2014<br>
 * Description	: Itinerary.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Itinerary extends KCXMessage {
	
	 private ArrayList<String> countryCodeList;   		 
	 
     public Itinerary() {
  		super();
  	}
     public Itinerary(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum EItineraryIE {
		CountryCode,	
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((EItineraryIE) tag) {
 			default:
 					return;
 			}
 		} else {
 			switch ((EItineraryIE) tag) { 			 			
 				case CountryCode:
 					addCountryCodeList(value);
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
 			return EItineraryIE.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}
	
 	public ArrayList<String> getCountryCodeList() {
		return countryCodeList;
	} 	
	public void setCountryCodeList(ArrayList<String> list) {
		this.countryCodeList = list;
	}
	public void addCountryCodeList(String code) {
		if (countryCodeList == null) {
			countryCodeList = new ArrayList<String>();
		}
		countryCodeList.add(code);
	}
		
	public boolean isEmpty() {
		return countryCodeList == null;  
	}	
}
