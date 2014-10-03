package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: Container Data.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UserInforamtion extends KCXMessage {
	
	 private String senderID;        
	 private String senderUserID;                   
	 
	 public UserInforamtion() {
		 super();  
	 }

	 public UserInforamtion(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EUserInforamtion {	
			// Kids-TagNames, 			UIDS-TagNames;
		 SenderID,					
		 SenderUserID;							 			        			
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EUserInforamtion) tag) {
				default: return;			
			}
		} else {			
			switch ((EUserInforamtion) tag) {			
				case SenderID:
					setSenderID(value);
					break;
				case SenderUserID:
					setSenderUserID(value);
					break;				
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }

	
	 public Enum translate(String token) {
		 try {
			return EUserInforamtion.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String argument) {
		this.senderID = argument;
	}					
		
	 public String getSenderUserID() {
			return senderUserID;
		}
		public void setSenderUserID(String argument) {
			this.senderUserID = argument;
		}					
			
}
