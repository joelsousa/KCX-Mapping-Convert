package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustErrInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustErrInfo extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	
	private String ensLref 	= "";
	private String arrLref = "";
	private String divLref = "";
			
	 private enum ECustErrInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		 EnsLref,				   	
		 ArrLref,	      	
		 DivLref;					 
	 }

	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("ENS-LREF", "EnsLref"); 
		    enumMap.put("ARR-LREF", "ArrLref"); 
		    enumMap.put("DIV-LREF", "DivLref");
	 }	
	 
	 public CustErrInfo() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public CustErrInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustErrInfo) tag) {			
				default:
					return;
			}
		} else {

			switch ((ECustErrInfo) tag) {
				case EnsLref:
					setEnsLref(value);
					break;  	
				case ArrLref:
					setArrLref(value);
					break;  	
				case DivLref:
					setDivLref(value);
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
			return ECustErrInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getEnsLref() {
		return ensLref;	
	}
	public void setEnsLref(String argument) {
		this.ensLref = Utils.checkNull(argument);
	}

	public String getArrLref() {
		return arrLref;	
	}
	public void setArrLref(String argument) {
		this.arrLref = Utils.checkNull(argument);
	}
	
	public String getDivLref() {
		return divLref;	
	}
	public void setDivLref(String argument) {
		this.divLref = Utils.checkNull(argument);
	}
	
}
