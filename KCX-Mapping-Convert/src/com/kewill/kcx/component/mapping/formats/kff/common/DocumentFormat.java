package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: DocumentFormat
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DocumentFormat extends KCXMessage {

	 private String formatIdentifier;       
	 private String formatVersion;             
	
	 public DocumentFormat() {
		 super();  
	 }

	 public DocumentFormat(XmlMsgScanner scanner) {
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
		return formatIdentifier;
	}
	public void setFormatIdentifier(String argument) {
		this.formatIdentifier = argument;
	}					
		
	public String getFormatVersion() {
		return formatVersion;
	}
	public void setFormatVersion(String argument) {
		this.formatVersion = argument;
	}		
	
}
