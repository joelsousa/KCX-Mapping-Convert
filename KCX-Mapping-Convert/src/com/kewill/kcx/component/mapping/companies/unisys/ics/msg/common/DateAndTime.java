package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: DateAndTime<br>  
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DateAndTime extends KCXMessage {
	 
	private String date = "";     // 2n3a2n
	private String time = "";	  // 4n		
	
	private enum EDateAndTime {
	 // Unisys-TagNames, 			KIDS-TagNames
		 DATE,				   		      
		 TIME;	
	}

	public DateAndTime() {
	      	super();	      	
	}    
   
	public DateAndTime(XmlMsgScanner scanner) {
		super(scanner);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EDateAndTime) tag) {			
				default:
					return;
			}
		} else {

			switch ((EDateAndTime) tag) {
				case DATE:
					setDate(value);
					break;  	
				case TIME:
					setTime(value);
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
			return EDateAndTime.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDate() {
		return this.date;
	}
	public void setDate(String argument) {		
		this.date = Utils.checkNull(argument);		
	}
		
	public String getTime() {	
		return this.time;		
	}
	public void setTime(String argument) {		
		this.time = Utils.checkNull(argument);		
	}		
}
