package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 04.06.2014<br>
* Description	: Text.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Text extends KCXMessage {
	
	private String statementCode;
	private String contentText;
      
    private enum ETextIE {
    	StatementCode,    	   	
    	ContentText;
    }

    public Text() {
	      	super();	       
    }
    
    public Text(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ETextIE) tag) {    			    				    		
    			default:
    					return;
    			}
    		} else {
    			switch ((ETextIE) tag) {    				
    				case StatementCode:
    					setStatementCode(value);
    					break;    					
    				case ContentText:
    					setContentText(value);
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
    			return ETextIE.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getStatementCode() {
		return statementCode;
	}
	public void setStatementCode(String value) {
		this.statementCode = value;
	}
	
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String value) {
		this.contentText = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(statementCode) && 
			 Utils.isStringEmpty(contentText);				
	}

}
