package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: CommunicationType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CommunicationType extends KCXMessage {
	
	private String completeNumber;
	private String uriId;
       
    private enum ECommunicationType {
    	CompleteNumber,
    	URIID,    	
    }

    public CommunicationType() {
	      	super();	       
    }
    
    public CommunicationType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECommunicationType) tag) {    			
    			default:
    					return;
    			}
    		} else {

    			switch ((ECommunicationType) tag) {
    				case CompleteNumber :
    					setCompleteNumber(value);
    					break;

    				case URIID:
    					setUriId(value);
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
    			return ECommunicationType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getCompleteNumber() {
		return completeNumber;
	}

	public void setCompleteNumber(String number) {
		this.completeNumber = number;
	}

	public String getUriId() {
		return uriId;
	}

	public void setUriId(String number) {
		this.uriId = number;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(completeNumber) && Utils.isStringEmpty(uriId);
	}

}
