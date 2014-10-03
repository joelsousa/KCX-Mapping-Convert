package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: HeaderNotification.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class HeaderNotification extends KCXMessage {
	
    private String notificationWeight;   
    private ArrayList<String> notificationDetailsList;
       
    private enum EHeaderNotification {
    	NotificationWeight,    	
    	NotificationDetails;
    }

    public HeaderNotification() {
	      	super();	       
    }
    
    public HeaderNotification(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EHeaderNotification) tag) {    			    		
    			default:
    					return;
    			}
    		} else {

    			switch ((EHeaderNotification) tag) {
    			case NotificationWeight:
    				setNotificationWeight(value);
    				break;
    				
    			case NotificationDetails:
    				addNotificationDetailsList(value);
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
    			return EHeaderNotification.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }
	
	public String getNotificationWeight() {
		return notificationWeight;
	}
	public void setNotificationWeight(String value) {
		this.notificationWeight = value;
	}
	
	public ArrayList<String> getNotificationDetailsList() {
		return notificationDetailsList;
	}
	public void setNotificationDetailsList(ArrayList<String> list) {
		this.notificationDetailsList = list;
	}
	public void addNotificationDetailsList(String value) {
		if (notificationDetailsList == null) {
			notificationDetailsList = new ArrayList<String>();
		}
		this.notificationDetailsList.add(value);
	}
	
	public boolean isEmpty() {
		return (notificationDetailsList == null &&
				Utils.isStringEmpty(notificationWeight));
	}

}
