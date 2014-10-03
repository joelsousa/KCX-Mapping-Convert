package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 18.07.2013<br>
* Description	: TransportMeans.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class TransportMeans extends KCXMessage {
	
	private String	type;
    private String	name;
    private IdType	registrationCountry;
       
    private enum ETransportMeans {
    	Type,
    	Name,
    	RegistrationCountry;
    }

    public TransportMeans() {
	      	super();	       
    }
    
    public TransportMeans(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ETransportMeans) tag) {
    			case RegistrationCountry:
    				registrationCountry = new IdType(getScanner());
    				registrationCountry.parse(tag.name()); 				
					break;
    			default:
    					return;
    			}
    		} else {
    			switch ((ETransportMeans) tag) {
    				case Type:
    					setType(value);
						break;
					
    				case Name:
    					setName(value);
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
    			return ETransportMeans.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String value) {
		this.name = Utils.checkNull(value);
	}

	public IdType getRegistrationCountry() {
		return registrationCountry;
	}
	public void setRegistrationCountry(IdType id) {
		this.registrationCountry = id;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(name) && Utils.isStringEmpty(type) &&
		registrationCountry == null;
	}
}
