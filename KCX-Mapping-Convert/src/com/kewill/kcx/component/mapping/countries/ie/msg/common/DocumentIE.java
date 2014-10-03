package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 05.06.2014<br>
* Description	: DocumentIE.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class DocumentIE extends KCXMessage {
		
	private String typeCode;
	private String referenceIdentifier;
      
    private enum EDocumentIE {
    	TypeCode,    	   	
    	ReferenceIdentifier;
    }

    public DocumentIE() {
	      	super();	       
    }
    
    public DocumentIE(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EDocumentIE) tag) {    			    				    		
    			default:
    					return;
    			}
    		} else {
    			switch ((EDocumentIE) tag) {
    				case TypeCode:
    					setTypeCode(value);
    					break;    					
    				case ReferenceIdentifier:
    					setReferenceIdentifier(value);
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
    			return EDocumentIE.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
    public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String value) {
		this.typeCode = value;
	}
	
	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String value) {
		this.referenceIdentifier = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(typeCode) && 
			 Utils.isStringEmpty(referenceIdentifier);				
	}

}
