package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 04.06.2014<br>
* Description	: ControlResult.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ControlResult extends KCXMessage {
	
	private String controlResultCode;
	private String dateLimit;
      
    private enum EControlResultIE {
    	ControlResultCode,    	   	
    	DateLimit;
    }

    public ControlResult() {
	      	super();	       
    }
    
    public ControlResult(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EControlResultIE) tag) {    			    				    		
    				default:
    					return;
    			}
    		} else {
    			switch ((EControlResultIE) tag) {
    				
    				case ControlResultCode:
    					setControlResultCode(value);
    					break;    					
    				case DateLimit:
    					setDateLimit(value);
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
    			return EControlResultIE.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getControlResultCode() {
		return controlResultCode;
	}
	public void setControlResultCode(String value) {
		this.controlResultCode = value;
	}
	
	public String getDateLimit() {
		return dateLimit;
	}
	public void setDateLimit(String value) {
		this.dateLimit = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(controlResultCode) && Utils.isStringEmpty(dateLimit);				
	}

}
