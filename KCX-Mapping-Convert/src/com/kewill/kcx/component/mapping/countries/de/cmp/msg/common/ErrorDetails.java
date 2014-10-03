package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: ErrorDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ErrorDetails extends KCXMessage {
	
    private String errorText;   
      
    private enum EErrorDetails {
    	ErrorText;
    }

    public ErrorDetails() {
	      	super();	       
    }
    
    public ErrorDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EErrorDetails) tag) {    			    		
    			default:
    					return;
    			}
    		} else {

    			switch ((EErrorDetails) tag) {
    			case ErrorText:
    				setErrorText(value);
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
    			return EErrorDetails.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String value) {
		this.errorText = value;
	}
	
	public boolean isEmpty() {
		return 	Utils.isStringEmpty(errorText);
	}

}
