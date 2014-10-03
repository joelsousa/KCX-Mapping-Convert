package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustIntInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CodeText extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	 	
	private String intCode = "";
	private String intText = "";
			
	 private enum ECustIntInfo {
	 // Unisys-TagNames, 			KIDS-TagNames	
		 IntCode,
		 IntText;					 
	 }

	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();		   
		    enumMap.put("INT-CODE", "IntCode");
		    enumMap.put("INT-TEXT", "IntText");
	}	
	 
	 public CodeText() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public CodeText(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustIntInfo) tag) {			
				default:
					return;
			}
		} else {

			switch ((ECustIntInfo) tag) {				
				case IntCode:
					setIntCode(value);
					break;
				case IntText:
					setIntText(value);
					break;  	
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		String text = enumMap.get(token); 
		if (text != null) {
			token = text;
		}
		try {
			return ECustIntInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	
	public String getIntCode() {
		return intCode;	
	}
	public void setIntCode(String argument) {
		this.intCode = Utils.checkNull(argument);
	}
	
	public String getIntText() {
		return intText;	
	}
	public void setIntText(String argument) {
		this.intText = Utils.checkNull(argument);
	}
	
}
