package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustRejInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustRejInfo extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	 
	private String date 	= "";
	private String time = "";
	private String rejCode = "";
	private String rejText = "";
			
	 private enum ECustRejInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		 DATE,				   	
		 TIME,
		 RejCode,
		 RejText;					 
	 }

	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();		   
		    enumMap.put("REJ-CODE", "RejCode");
		    enumMap.put("REJ-TEXT", "RejText");
	}
	 
	 public CustRejInfo() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public CustRejInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustRejInfo) tag) {			
				default:
					return;
			}
		} else {

			switch ((ECustRejInfo) tag) {
				case DATE:
					setDate(value);
					break;  	
				case TIME:
					setTime(value);
					break;
				case RejCode:
					setRejCode(value);
					break;
				case RejText:
					setRejText(value);
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
			return ECustRejInfo.valueOf(token);
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
	
	public String getRejCode() {
		return rejCode;	
	}
	public void setRejCode(String argument) {
		this.rejCode = Utils.checkNull(argument);
	}
	
	public String getRejText() {
		return rejText;	
	}
	public void setRejText(String argument) {
		this.rejText = Utils.checkNull(argument);
	}
	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(date) && 
			Utils.isStringEmpty(time) &&
			Utils.isStringEmpty(rejCode) &&
			Utils.isStringEmpty(rejText)) {
			return true;			
		} else {
			return false;			
		}
	}
	
}
