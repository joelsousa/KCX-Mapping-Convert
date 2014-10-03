package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: Location.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Location extends KCXMessage {


    private String id;
    private String name;
    private String typeCode;      
    private String firstArrivalCountryID;  //only for ArrivalLocation
        
    public Location() {
	      	super();	       
    }
    
    public Location(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum ELocation {
    	ID,							// 3-letter-code: DUS, MIA	
    	Name,
    	TypeCode,                 	//bsp: "Airport"
    	FirstArrivalCountryID,    	 
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ELocation) tag) {    			
    			default:
    					return;
    			}
    		} else {

    			switch ((ELocation) tag) {

    				case ID:   
    					setId(value);    					
    					break;    				    					
    				case Name:   
    					setName(value);    					
    					break;  
    				case TypeCode:   
    					setTypeCode(value);    					
    					break;  
    				case FirstArrivalCountryID:   
    					setFirstArrivalCountryID(value);    					
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
    			return ELocation.valueOf(token);
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

		public String getFirstArrivalCountryID() {
			return firstArrivalCountryID;
		}
		public void setFirstArrivalCountryID(String firstArrivalCountryID) {
			this.firstArrivalCountryID = firstArrivalCountryID;
		}       

		public boolean isEmpty() {
			return Utils.isStringEmpty(id) && Utils.isStringEmpty(name) &&
			Utils.isStringEmpty(typeCode) && Utils.isStringEmpty(firstArrivalCountryID);
		}
}
