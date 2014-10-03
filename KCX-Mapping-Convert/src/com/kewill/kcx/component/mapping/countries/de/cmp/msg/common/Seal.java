package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 25.07.2013<br>
 * Description	: Seal.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Seal extends KCXMessage {

    private String id;
       
    public Seal() {
	      	super();	       
    }
    
    public Seal(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum ESeal {
    	ID,    	 	
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ESeal) tag) {    			
    			default:
    					return;
    			}
    		} else {
    			switch ((ESeal) tag) {
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
    			return ESeal.valueOf(token);
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
