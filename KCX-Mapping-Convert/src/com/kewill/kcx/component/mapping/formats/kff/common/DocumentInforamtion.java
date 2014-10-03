package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: Container Data.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DocumentInforamtion extends KCXMessage {
	
	 private DocumentFormat documentFormat;         
	 private TransmissionDateTime transmissionDateTime;                  
	 private String batchNo;      
	
	 public DocumentInforamtion() {
		 super();  
	 }

	 public DocumentInforamtion(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EDocumentInforamtion {	
			// Kids-TagNames, 			UIDS-TagNames;
		 DocumentFormat,					
		 TransmissionDateTime,					
		 BatchNo;			        				
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EDocumentInforamtion) tag) {
			case DocumentFormat:
				documentFormat = new DocumentFormat(getScanner());  						
				documentFormat.parse(tag.name());					
				break;			
			case TransmissionDateTime:
				transmissionDateTime = new TransmissionDateTime(getScanner());  										
				transmissionDateTime.parse(tag.name());
				break;	
			default: 
					return;			
			}
		} else {			
			switch ((EDocumentInforamtion) tag) {			
				case BatchNo:
					setBatchNo(value);
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
			return EDocumentInforamtion.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public DocumentFormat getDocumentFormat() {
		return documentFormat;
	}
	public void setDocumentFormat(DocumentFormat argument) {
		this.documentFormat = argument;
	}					
		
	public TransmissionDateTime getTransmissionDateTime() {
		return transmissionDateTime;
	}
	public void setTransmissionDateTime(TransmissionDateTime argument) {
		this.transmissionDateTime = argument;
	}	
	
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String argument) {
		this.batchNo = argument;
	}	
}
