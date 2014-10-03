package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Number Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Number extends KCXMessage {
      
	 private String   type;  
	 private String   code;  
	 private String   number;
	 
	 private enum EImportNumber {
		// Kids-TagNames, 			UIDS-TagNames
		 Type,	
		 Code,
	     Number;
	 }
	
	 public Number() {
			super();
	 }	 
    
	 public Number(XmlMsgScanner scanner) {
			super(scanner);
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EImportNumber) tag) {
				
				default:
						return;
				}
			} else {				
				switch ((EImportNumber) tag) {
					case Type:
						setType(value);
						break;
					case Code:
						setCode(value);
						break;	
					case Number:
						setNumber(value);
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
				return EImportNumber.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String value) {
		this.number = value;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String value) {
		this.type = value;
	}
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.type) && Utils.isStringEmpty(this.code) && 
		        Utils.isStringEmpty(this.number));
	}	
}
