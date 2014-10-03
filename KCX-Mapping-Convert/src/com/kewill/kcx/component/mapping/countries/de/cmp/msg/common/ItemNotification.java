package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: ItemNotification.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ItemNotification extends KCXMessage {
	
    private String type;
    private String details;
      
    private enum EItemNotification {
    	NotificationType,
    	NotificationDetails;
    }

    public ItemNotification() {
	      	super();	       
    }
    
    public ItemNotification(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EItemNotification) tag) {    			    		
    			default:
    					return;
    			}
    		} else {

    			switch ((EItemNotification) tag) {
    			
    			case NotificationType:
    				setNotificationType(value);
    				break;
    				
    			case NotificationDetails:
    				setNotificationDetails(value);
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
    			return EItemNotification.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

	public String getNotificationWeight() {
		return type;
	}

	public void setNotificationType(String type) {
		this.type = type;
	}

	public String getNotificationDetails() {
		return details;
	}

	public void setNotificationDetails(String details) {
		this.details = details;
	}
	
	public boolean isEmpty() {
		return 	Utils.isStringEmpty(type) && Utils.isStringEmpty(details);
	}

}
