package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 05.06.2014<br>
* Description	: CustomsProcedure.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CustomsValuation extends KCXMessage {
	
	private String amount;
	private String methodCode;
	  
    private enum ECustomsValuation {
    	AmountAmount,    	   	
    	MethodCode,    	
    }

    public CustomsValuation() {
	      	super();	       
    }
    
    public CustomsValuation(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECustomsValuation) tag) {    			    				    		
    				default:
    					return;
    			}
    		} else {
    			switch ((ECustomsValuation) tag) {
    				
    				case AmountAmount:
    					setAmount(value);
    					break;    					
    				case MethodCode:
    					setMethodCode(value);
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
    			return ECustomsValuation.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }		
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String value) {
		this.amount = value;
	}
	
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String value) {
		this.methodCode = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(amount) && 
		Utils.isStringEmpty(methodCode);				
	}

}
