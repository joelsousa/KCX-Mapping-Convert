package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustAWB<br>  
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustAwb extends KCXMessage {
	 
	private String awb = "";
	private String hawb = "";			
	
	 private enum ECustAWB {
	 // Unisys-TagNames, 			KIDS-TagNames
		 AWB,				   		      
		 HAWB;	
	 }

	 public CustAwb() {
	      	super();	      	
	 }    
   
	 public CustAwb(XmlMsgScanner scanner) {
		super(scanner);		
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustAWB) tag) {			
				default:
					return;
			}
		} else {

			switch ((ECustAWB) tag) {
				case AWB:
					setAWB(value);
					break;  	
				case HAWB:
					setHAWB(value);
					break;  
				
				default:
					break;
			}
		}
	}

	//Todo
	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {		
		try {
			return ECustAWB.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getAWB() {
		return awb;	
	}
	public void setAWB(String argument) {
		this.awb = Utils.checkNull(argument);
	}

	public String getHAWB() {
		return hawb;	
	}
	public void setHAWB(String argument) {
		this.hawb = Utils.checkNull(argument);
	}			
}
