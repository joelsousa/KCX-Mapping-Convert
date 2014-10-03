package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DocumentDetails extends KCXMessage {
		
	private String documentName;
	private String layoutKey;
	private String numberOfCopies;
	private String numberOfOriginals;
	
	private enum EDocumentDetails {	
		DocumentName,
		FormularLayoutKeyDakosy,
		NumberOfCopiesRequired,
		NumberOfOriginalsRequired;
   }	

	public DocumentDetails() {
		super();  
	}

	public DocumentDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDocumentDetails) tag) {
  			/*
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break; 
  				*/
				default:
  					break;
  			}
  		} else {

  			switch((EDocumentDetails) tag) {   			
  				case DocumentName:
  					setDocumentName(value);
  					break; 
  				case FormularLayoutKeyDakosy:
  					setFormularLayoutKeyDakosy(value);
  					break; 	
  				case NumberOfCopiesRequired:
  					setNumberOfCopiesRequired(value);
  					break; 
  				case NumberOfOriginalsRequired:
  					setNumberOfOriginalsRequired(value);
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
  			return EDocumentDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public String getDocumentName() {
		return documentName;
	}    
	public void setDocumentName(String value) {
		this.documentName = value;
	}	
	
	public String getFormularLayoutKeyDakosy() {
		return this.layoutKey;
	}    
	public void setFormularLayoutKeyDakosy(String value) {
		this.layoutKey = value;
	}	
	
	public String getNumberOfCopiesRequired() {
		return numberOfCopies;
	}    
	public void setNumberOfCopiesRequired(String value) {
		this.numberOfCopies = value;
	}	
	
	public String getNumberOfOriginalsRequired() {
		return numberOfOriginals;
	}    
	public void setNumberOfOriginalsRequired(String value) {
		this.numberOfOriginals = value;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(documentName) && Utils.isStringEmpty(layoutKey) && 
				Utils.isStringEmpty(numberOfOriginals) && Utils.isStringEmpty(numberOfCopies));			
	}
}

