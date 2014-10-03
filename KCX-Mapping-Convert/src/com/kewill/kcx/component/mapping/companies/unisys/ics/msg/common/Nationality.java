package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustCountry<br>  
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Nationality extends KCXMessage {
	 	
	private String country = "";	
	
	 private enum ENationality {
	 // Unisys-TagNames, 			KIDS-TagNames		
		 COUNTRY;	
	 }

	 public Nationality() {
	      	super();	      	
	 }    
   
	 public Nationality(XmlMsgScanner scanner) {
		super(scanner);		
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENationality) tag) {			
				default:
					return;
			}
		} else {

			switch ((ENationality) tag) {				 
				case COUNTRY:
					setCountry(value);
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
			return ENationality.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
	
	public String getCountry() {
		return country;	
	}
	public void setCountry(String argument) {
		this.country = Utils.checkNull(argument);
	}	
}
