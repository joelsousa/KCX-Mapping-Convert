/*
 * Function    : Notifications(KIDS CH)
 * Titel       :
 * Date        : 23.10.2008
 * Author      : Kewill CSF / Krzoska
 * Description : Contains the NotificationCode
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Notifications<br>
 * Erstellt		: 23.10.2008<br>
 * Beschreibung	: Contains the NotificationCode with all Fields used in  KIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class Notifications  extends KCXMessage {
	
	private String notificationCode;
	private boolean debug   = false;

	private enum ENotifications {
		NotificationCode;		
   }
	
	public Notifications() {
		super();  
	}
	
	public Notifications(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ENotifications) tag) {
					
  			default:
  					return;  			
  			}
  		} else {
   			  	
  			switch ((ENotifications) tag) {
  				case NotificationCode:
  					setNotificationCode(value);
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return ENotifications.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

	public String getNotificationCode() {
		return notificationCode;
	
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

}
