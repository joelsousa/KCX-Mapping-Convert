package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : ExportAcceptance.    
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class ExportAcceptance extends KCXMessage {

	private String senderCustomsOffice;
	private String dateOfAcceptance;
	private String documentReferenceNumber;
	private String customsOfficerID;
	
	private enum EExportAcceptance {
		//KIDS:					  UIDS:
		SenderCustomsOffice,      ReferenceNumberOfSenderCustomsOffice,    
		DateOfAcceptance,    	  //same
		DocumentReferenceNumber,  //same                 
		CustomsOfficerID,		  IdentificationOfSenderCustomsOfficer;	
	}
	
	public ExportAcceptance() {
  		super();
  	}
			 
	public ExportAcceptance(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExportAcceptance) tag) {					
			default:
				return;
			}
	    } else {
	    	switch ((EExportAcceptance) tag) {
			case SenderCustomsOffice:	
			case ReferenceNumberOfSenderCustomsOffice:
				 setSenderCustomsOffice(value);
				 break;	
			case DateOfAcceptance:
				 setDateOfAcceptance(value);
				 break;	
			case DocumentReferenceNumber:			
				 setDocumentReferenceNumber(value);
				 break;	
			case CustomsOfficerID:
			case IdentificationOfSenderCustomsOfficer:
				 setCustomsOfficerID(value);
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
  			return EExportAcceptance.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getSenderCustomsOffice() {	
		return this.senderCustomsOffice;
	}
	public void setSenderCustomsOffice(String argument) {
		this.senderCustomsOffice = argument;
	}	

	public String getDateOfAcceptance() {
		return this.dateOfAcceptance;
	}
	public void setDateOfAcceptance(String argument) {
		this.dateOfAcceptance = argument;
	}	
 	
	public String getDocumentReferenceNumber() {
		return this.documentReferenceNumber;
	}
	public void setDocumentReferenceNumber(String argument) {
		this.documentReferenceNumber = argument;
	}

	public String getCustomsOfficerID() {
		return this.customsOfficerID;
	}
	public void setCustomsOfficerID(String argument) {
		this.customsOfficerID = argument;
	}	
	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.senderCustomsOffice) && 
				Utils.isStringEmpty(this.dateOfAcceptance) &&
				Utils.isStringEmpty(this.documentReferenceNumber) &&     		
				Utils.isStringEmpty(this.customsOfficerID));
			
	}	
}
