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

public class CustomsProcedure extends KCXMessage {
	
	private String currentCode;
	private String previousCode;
	private String categoryCode;
      
    private enum ECustomsProcedure {
    	CurrentCode,    	   	
    	PreviousCode,
    	CategoryCode;
    }

    public CustomsProcedure() {
	      	super();	       
    }
    
    public CustomsProcedure(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECustomsProcedure) tag) {    			    				    		
    				default:
    					return;
    			}
    		} else {
    			switch ((ECustomsProcedure) tag) {
    				
    				case CurrentCode:
    					setCurrentCode(value);
    					break;    					
    				case PreviousCode:
    					setPreviousCode(value);
    					break; 
    				case CategoryCode:
    					setCategoryCode(value);
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
    			return ECustomsProcedure.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getCurrentCode() {
		return currentCode;
	}
	public void setCurrentCode(String value) {
		this.currentCode = value;
	}
	
	public String getPreviousCode() {
		return previousCode;
	}
	public void setPreviousCode(String value) {
		this.previousCode = value;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String value) {
		this.categoryCode = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(currentCode) && Utils.isStringEmpty(previousCode) &&
		Utils.isStringEmpty(categoryCode);				
	}

}
