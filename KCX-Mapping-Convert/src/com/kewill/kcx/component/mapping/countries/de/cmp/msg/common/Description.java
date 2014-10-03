package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: Description.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Description extends KCXMessage {


    private String description;    
    private String descriptionCode;
       
    public Description() {
	      	super();	       
    }
    
    public Description(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EDescription {
    	Description,    	
    	DescriptionCode,    	
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EDescription) tag) {
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EDescription) tag) {

    				case Description:   
    					setDescription(value);    					
    					break;    				    					
    				 
    				case DescriptionCode:   
    					setDescriptionCode(value);    					
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
    			return EDescription.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public String getDescriptionCode() {
			return descriptionCode;
		}
		public void setDescriptionCode(String code) {
			descriptionCode = code;
		}

		public boolean isEmpty() {
			return (Utils.isStringEmpty(description) && Utils.isStringEmpty(descriptionCode));
		}
}
