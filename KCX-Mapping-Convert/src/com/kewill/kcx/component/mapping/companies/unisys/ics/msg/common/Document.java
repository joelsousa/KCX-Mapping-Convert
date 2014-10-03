package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Document<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Document extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	 	
	private String docType = "";
	private String docNumber = "";
		
	private void initEnumMap() {
		    enumMap = new HashMap<String, String>();		   
		    enumMap.put("DOC-TYPE", "DocType");
		    enumMap.put("DOC-NUMBER", "DocNumber");
	}	
	 private enum EDocument {
	 // Unisys-TagNames, 			KIDS-TagNames	
		 DocType,
		 DocNumber;					 
	 }

	 public Document() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public Document(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EDocument) tag) {			
				default:
					return;
			}
		} else {

			switch ((EDocument) tag) {				
				case DocType:
					setDocType(value);
					break;
				case DocNumber:
					setDocNumber(value);
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
			return EDocument.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	
	public String getDocType() {
		return docType;	
	}
	public void setDocType(String argument) {
		this.docType = Utils.checkNull(argument);
	}
	
	public String getDocNumber() {
		return docNumber;	
	}
	public void setDocNumber(String argument) {
		this.docNumber = Utils.checkNull(argument);
	}
	
}
