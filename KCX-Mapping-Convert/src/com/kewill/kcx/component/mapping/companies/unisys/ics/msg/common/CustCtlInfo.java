package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustCtlInfo<br>  
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustCtlInfo extends KCXMessage {
	 
	private DateAndTime present;
	private DateAndTime control;		
	
	 private enum ECustCtlInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		 PRESENT,				   		      
		 CONTROL;
	 }

	 public CustCtlInfo() {
	      	super();	      	
	 }    
   
	 public CustCtlInfo(XmlMsgScanner scanner) {
		super(scanner);		
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustCtlInfo) tag) {	
			case PRESENT:
				present = new DateAndTime(getScanner());  	
				present.parse(tag.name());				
				break;	
			case CONTROL:	
				control = new DateAndTime(getScanner());
				control.parse(tag.name());				
				break;
				default:
					return;
			}
		} else {

			switch ((ECustCtlInfo) tag) {				
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {		
		try {
			return ECustCtlInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public DateAndTime getPresent() {
		return present;	
	}
	public void setPresent(DateAndTime argument) {
		this.present = argument;
	}

	public DateAndTime getControl() {
		return control;	
	}
	public void setControl(DateAndTime argument) {
		this.control = argument;
	}
	
}
