package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Preferences Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Preference extends KCXMessage {
   
    private String preferenceCertificate;	
    private String preferenceCertificateNumber;
    private String date;	
    private String codeExists;    
 
  	private enum EPreferences {		
  		PreferenceCertificate,			
  		PreferenceCertificateNumber,
		Date,		
		CodeExists;
  	} 

    public Preference() {
    	super();    		
    }
    
    public Preference(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPreferences) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EPreferences) tag) {
  			   
  				case PreferenceCertificate:
  					setPreferenceCertificate(value);
  					break;
  					
  				case PreferenceCertificateNumber:
  					setPreferenceCertificateNumber(value);
  					break;
  					
  				case Date:
  					setDate(value);
  					break;
  					
  				case CodeExists:
  					setCodeExists(value);
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
  			return EPreferences.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getPreferenceCertificate() {
		return preferenceCertificate;
	}
	public void setPreferenceCertificate(String argument) {
		this.preferenceCertificate = argument;
	}

	public String getPreferenceCertificateNumber() {
		return preferenceCertificateNumber;
	}
	public void setPreferenceCertificateNumber(String argument) {
		this.preferenceCertificateNumber = argument;
	}
	
	public void setDate(String argument) {
		this.date = argument;
	}		
	
	public String getDate() {
		return this.date;
	}
  
	public void setCodeExists(String argument) {
		this.codeExists = argument;
	}		
	
	public String getCodeExists() {
		return this.codeExists;
	}

	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.preferenceCertificate) && Utils.isStringEmpty(this.date) &&
			Utils.isStringEmpty(this.preferenceCertificateNumber) && Utils.isStringEmpty(this.codeExists)); 		      
               	       
	}	
	
}
