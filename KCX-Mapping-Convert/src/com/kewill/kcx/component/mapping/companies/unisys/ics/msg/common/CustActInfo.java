package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustActInfo<br>  //original CustReqInfo
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustActInfo extends KCXMessage {
	 
	private String date 	= "";
	private String time = "";	
	private String mrn = "";
	
	 private enum ECustActInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		 DATE,				   		      
		 TIME,
		 MRN;
	 }

	 public CustActInfo() {
	      	super();	      	
	 }    
   
	 public CustActInfo(XmlMsgScanner scanner) {
		super(scanner);		
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustActInfo) tag) {			
				default:
					return;
			}
		} else {

			switch ((ECustActInfo) tag) {
				case DATE:
					setDate(value);
					break;  	
				case TIME:
					setTime(value);
					break; 
				case MRN:
					setMRN(value);
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
			return ECustActInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDate() {
		return date;	
	}
	public void setDate(String argument) {
		this.date = Utils.checkNull(argument);
	}

	public String getTime() {
		return time;	
	}
	public void setTime(String argument) {
		this.time = Utils.checkNull(argument);
	}	
	
	public String getMRN() {
		return mrn;	
	}
	public void setMRN(String argument) {
		this.mrn = Utils.checkNull(argument);
	}
}
