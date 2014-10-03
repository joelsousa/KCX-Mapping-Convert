package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: ---TODO---
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class SoParty extends KCXMessage {

	 private String g;       
	 private String h;             
	
	 public SoParty() {
		 super();  
	 }

	 public SoParty(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EDocumentFormat {				
		 FormatIdentifier,					
		 FormatVersion;							 			        		
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EDocumentFormat) tag) {
				default: return;			
			}
		} else {			
			switch ((EDocumentFormat) tag) {			
				case FormatIdentifier:
					setFormatIdentifier(value);
					break;
				case FormatVersion:
					setFormatVersion(value);
					break;				
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }
	
	 public Enum translate(String token) {
		 try {
			return EDocumentFormat.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public String getFormatIdentifier() {
		return g;
	}
	public void setFormatIdentifier(String argument) {
		this.g = argument;
	}					
		
	public String getFormatVersion() {
		return h;
	}
	public void setFormatVersion(String argument) {
		this.h = argument;
	}		
	
}
