package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the IncoTerms Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class IncoTerms extends KCXMessage {
    private String key;
    private String code;
    private String details;
    private String place;
    private String locationCode;

    
  	private enum EImportTerms {
  	// Kids-TagNames, 		UIDS-TagNames
		Key,   		
		Code,
		Details,				
		Place,
		LocationCode;     //EI20120308
     }

    public IncoTerms() {
    	super();
    }
    
    public IncoTerms(XmlMsgScanner scanner) {
  		super(scanner);
  	}
    
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
			return;
  		} else {
  			switch ((EImportTerms) tag) {
  				case Key:
  					setKey(value);
  					break;  					
  				case Code:
  					setCode(value);
  					break;
  				case Details:
  					setDetails(value);
  					break;
  				case Place:  				
  					setPlace(value);
  					break;
  				case LocationCode:
  					setLocationCode(value);
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
  			return EImportTerms.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	
	public String getKey() {
		return key;
	}
	public void setKey(String value) {
		this.key = value;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String value) {
		this.code = value;
	}

	public String getDetails() {
		return details;
	}
	public void setDetails(String value) {
		this.details = value;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String value) {
		this.place = value;
	}
	
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String value) {
		this.locationCode = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.key) && Utils.isStringEmpty(this.code) && 		      
                Utils.isStringEmpty(this.details)); 		       		       
	}		
}
