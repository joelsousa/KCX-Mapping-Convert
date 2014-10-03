package com.kewill.kcx.component.mapping.countries.ch.aus20.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CH20<br>
 * Created		: 29.10.2012<br>
 * Description	: Dispatch Code and Code confirmation
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Dispatch extends KCXMessage {
		
    private String code;	
    private String confirmation;
   
  	private enum EDispatch {
  		//KIDS
  		Code,            
  		Confirmation,            		
  	} 

    public Dispatch() {
    	super();    		
    }
   	
    public Dispatch(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDispatch) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EDispatch) tag) {  			     			
  				case Code:
  					setCode(value);
  					break;  					
  				case Confirmation:  				
  					setConfirmation(value);  					
  					break;  					
  				
  				default: break;
  				
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EDispatch.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String value) {
		this.code = value;
	}
	
	public void setConfirmation(String value) {
		this.confirmation = value;
	}			
	public String getConfirmation() {
		return this.confirmation;
	}
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.code) && 
		        Utils.isStringEmpty(this.confirmation));  
	}	

}
