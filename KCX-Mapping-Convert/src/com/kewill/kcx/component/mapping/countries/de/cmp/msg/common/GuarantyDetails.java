package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: GuarantyDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class GuarantyDetails extends KCXMessage {
	
    private String grn;   
    private String invalidCode;
    private String invalidText;
       
    private enum EGuarantyDetails {
    	GRN,
    	InvalidCode,    	
    	InvalidText;
    }

    public GuarantyDetails() {
	      	super();	       
    }
    
    public GuarantyDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EGuarantyDetails) tag) {    			    		
    			default:
    					return;
    			}
    		} else {

    			switch ((EGuarantyDetails) tag) {
    			case GRN:
    				setGrn(value);
    				break;
    				
    			case InvalidCode:
    				setInvalidCode(value);
    				break;
    				
    			case InvalidText:
    				setInvalidText(value);
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
    			return EGuarantyDetails.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

	public String getGrn() {
		return grn;
	}
	public void setGrn(String grn) {
		this.grn = grn;
	}

	public String getInvalidCode() {
		return invalidCode;
	}
	public void setInvalidCode(String invalidCode) {
		this.invalidCode = invalidCode;
	}

	public String getInvalidText() {
		return invalidText;
	}
	public void setInvalidText(String invalidText) {
		this.invalidText = invalidText;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(grn) && Utils.isStringEmpty(invalidCode) &&
				Utils.isStringEmpty(invalidText));
	}

}
