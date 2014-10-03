package com.kewill.kcx.component.mapping.countries.ch.aus20.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CH20<br>
 * Created		: 29.10.2012<br>
 * Description	: Administrative Infos
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Infos extends KCXMessage {
		
    private String type;	
    private String text;
    private String date;
    private String internalOnly;
  
  	private enum EInfo {
  		 //KIDS               
  		Type,            
		Text,            
		Date,
		InternalOnly;
  	} 

    public Infos() {
    	super();    		
    }
   	
    public Infos(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EInfo) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EInfo) tag) {  			     			
  				case Type:
  					setType(value);
  					break;  					
  				case Text:  				
  					setText(value);  					
  					break;  					
  				case Date:
  					setDate(value);
  					break;
  				case InternalOnly:
  					setInternalOnly(value);
  					break;
  					
  				default: break;
  				
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EInfo.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		this.type = value;
	}
	public void setText(String value) {
		this.text = value;
	}			
	public String getText() {
		return this.text;
	}
	
	public String getDate() {
		return date;	
	}
	public void setDate(String value) {
		this.date = value;
	}

	public String getInternalOnly() {
		return internalOnly;	
	}
	public void setInternalOnly(String value) {
		this.internalOnly = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.type) && Utils.isStringEmpty(this.date) && 
		        Utils.isStringEmpty(this.text));  
	}	

}
