package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: ContactDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ContactDetails extends KCXMessage {
   
    private String personName;
    private String departmentName;
    private CommunicationType directTelephoneCommunication;
    private CommunicationType faxCommunication;
    private CommunicationType uriEmailCommunication;
    private CommunicationType telexCommunication;
    
    private enum EContactDetails {    	  			     	
    	PersonName,
    	DepartmentName,
    	DirectTelephoneCommunication,
    	FaxCommunication,
    	URIEmailCommunication,    	
    	TelexCommunication;
    }        

    public ContactDetails() {
	      	super();	       
    }
    
    public ContactDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EContactDetails) tag) {	
    			case DirectTelephoneCommunication:
    				directTelephoneCommunication = new CommunicationType(getScanner());
    				directTelephoneCommunication.parse(tag.name());
    				break;
    				
    			case FaxCommunication:
    				faxCommunication = new CommunicationType(getScanner());
    				faxCommunication.parse(tag.name());
    				break;
    				
    			case URIEmailCommunication:
    				uriEmailCommunication = new CommunicationType(getScanner());
    				uriEmailCommunication.parse(tag.name());
    				break;
    				
    			case TelexCommunication:
    				telexCommunication = new CommunicationType(getScanner());
    				telexCommunication.parse(tag.name());
    				break;
    				
    			default:
    					return;
    			}
    		} else {
    			switch ((EContactDetails) tag) {
    			
    			case PersonName:
    				setPersonName(value);
    				break;
    				
    			case DepartmentName:
    				setDepartmentName(value);
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
    		return EContactDetails.valueOf(token);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}
    }

	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String name) {
		this.personName = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String name) {
		this.departmentName = name;
	}

	public CommunicationType getDirectTelephoneCommunication() {
		return directTelephoneCommunication;
	}
	public void setDirectTelephoneCommunication(
			CommunicationType directTelephoneCommunication) {
		this.directTelephoneCommunication = directTelephoneCommunication;
	}

	public CommunicationType getFaxCommunication() {
		return faxCommunication;
	}
	public void setFaxCommunication(CommunicationType faxCommunication) {
		this.faxCommunication = faxCommunication;
	}

	public CommunicationType getUriEmailCommunication() {
		return uriEmailCommunication;
	}
	public void setUriEmailCommunication(CommunicationType uriEmailCommunication) {
		this.uriEmailCommunication = uriEmailCommunication;
	}

	public CommunicationType getTelexCommunication() {
		return telexCommunication;
	}
	public void setTelexCommunication(CommunicationType telexCommunication) {
		this.telexCommunication = telexCommunication;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(personName) && Utils.isStringEmpty(departmentName) &&
		directTelephoneCommunication == null && faxCommunication == null &&
		uriEmailCommunication == null && telexCommunication == null;
	}
}
