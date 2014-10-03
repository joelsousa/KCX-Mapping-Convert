package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 25.07.2013<br>
 * Description	: IdType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class IdType extends KCXMessage {

	private String id;
     
    public IdType() {
	      	super();	       
    }
    
    public IdType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EIdType {
    	ID,     
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EIdType) tag) {
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EIdType) tag) {

    				case ID:   
    					setId(value);    					
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
    			return EIdType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public boolean isEmpty() {
			return Utils.isStringEmpty(id);
		}
		
}
