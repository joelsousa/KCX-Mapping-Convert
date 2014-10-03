package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import java.util.ArrayList;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: HeaderParty.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class HeaderParty extends KCXMessage {
   
    private String qualifier;
    private String identity;
   
    private enum EHeaderParty {    	  			     	
    	Qualifier,
    	Identification, Identity;
    }        

    public HeaderParty() {
	      	super();	       
    }
    
    public HeaderParty(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EHeaderParty) tag) {				
    			default:
    					return;
    			}
    		} else {
    			switch ((EHeaderParty) tag) {
    			case Qualifier:
    				setQualifier(value);
    				break;
    				
    			case Identity:
    			case Identification:
    				setIdentity(value);
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
    		return EHeaderParty.valueOf(token);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}
    }

	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}       
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(qualifier) && Utils.isStringEmpty(identity));
	}
}
