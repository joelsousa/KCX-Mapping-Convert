package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: DepartureLocation.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DepartureLocation extends KCXMessage {

    private String id;
    private String name;
    private String typeCode;
       
    public DepartureLocation() {
	      	super();	       
    }
    
    public DepartureLocation(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EDepartureLocation {
    	ID,
    	Name,
    	TypeCode,    		
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EDepartureLocation) tag) {
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EDepartureLocation) tag) {

    				case ID:   
    					setId(value);    					
    					break;   
    					
    				case Name:   
    					setName(value);    					
    					break;  
    					
    				case TypeCode:   
    					setTypeCode(value);    					
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
    			return EDepartureLocation.valueOf(token);
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

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}
	
		public boolean isEmpty() {
			return (Utils.isStringEmpty(id) && Utils.isStringEmpty(name) &&
					Utils.isStringEmpty(typeCode));
		}
}
