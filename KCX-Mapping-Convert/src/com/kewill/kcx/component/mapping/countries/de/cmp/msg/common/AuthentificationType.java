package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: AuthentificationType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class AuthentificationType extends KCXMessage {
	
	private String actualDateTime;
	private String signatory;
    private Location issueAuthenticationLocation;
       
    private enum EAuthentificationType {
    	ActualDateTime,
    	Signatory,
    	IssueAuthenticationLocation;
    }

    public AuthentificationType() {
	      	super();	       
    }
    
    public AuthentificationType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EAuthentificationType) tag) {
    			case IssueAuthenticationLocation:
    				issueAuthenticationLocation = new Location(getScanner());
    				issueAuthenticationLocation.parse(tag.name());
    				break;
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EAuthentificationType) tag) {
    				case ActualDateTime :
    					setActualDateTime(value);
    					break;

    				case Signatory:
    					setSignatory(value);
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
    			return EAuthentificationType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getActualDateTime() {
		return actualDateTime;
	}

	public void setActualDateTime(String actualDateTime) {
		this.actualDateTime = actualDateTime;
	}

	public String getSignatory() {
		return signatory;
	}

	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}

	public Location getIssueAuthenticationLocation() {
		return issueAuthenticationLocation;
	}

	public void setIssueAuthenticationLocation(Location issueAuthenticationLocation) {
		this.issueAuthenticationLocation = issueAuthenticationLocation;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(actualDateTime) && 
				Utils.isStringEmpty(signatory) &&
				issueAuthenticationLocation == null;
	}

}
