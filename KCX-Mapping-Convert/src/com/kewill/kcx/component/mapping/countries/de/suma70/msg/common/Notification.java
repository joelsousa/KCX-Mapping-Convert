package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 03.07.2013<br>
 * Description	: Notification.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Notification extends KCXMessage {
		     	
	private String type;  
	private String subType; 
	private String code;  
	private String text;  
	
	private enum ENotification {
		//KIDS												
		NotificationType,
		NotificationSubType,
		NotificationCode,
		NotificationDescription,
	}
	
	public Notification() {
		super();  
	}

    public Notification(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ENotification) tag) {  			
  			default:
  					return;
  			}
  		} else {

  			switch ((ENotification) tag) {
  			
  			case NotificationType:
  				setNotificationType(value);
  			
  			case NotificationSubType:
  				setNotificationSubType(value);
  				
  			case NotificationCode:
  				setNotificationCode(value);
  				break;
  				
  			case NotificationDescription:
  				setNotificationDescription(value);
  				break;
  			
  	  			default:
  					return;
  			}
  		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		 try {
				return ENotification.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getNotificationType() {
		return type;
	}
	public void setNotificationType(String type) {
		this.type = type;
	}

	public String getNotificationSubType() {
		return subType;
	}
	public void setNotificationSubType(String type) {
		this.subType = type;
	}
	
	public String getNotificationCode() {
		return code;
	}
	public void setNotificationCode(String code) {
		this.code = code;
	}
	
	public String getNotificationDescription() {
		return text;
	}

	public void setNotificationDescription(String text) {
		this.text = text;
	}
	
}
