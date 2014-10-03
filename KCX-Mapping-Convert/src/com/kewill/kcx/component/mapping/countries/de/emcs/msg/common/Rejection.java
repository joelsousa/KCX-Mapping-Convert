package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Date			: 28.05.2010<br>
 * Description  : only UIDS. 
 *                 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Rejection extends KCXMessage {

	private String rejectionDateAndTime;
	private String rejectionReasonCode;
		
	private enum ERejection {
		//UIDS:                     
		RejectionDateAndTime,			
		RejectionReasonCode;		
	}
			 
	public Rejection(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ERejection) tag) {	
				default:
  					return;
  			}
  		} else {
			switch ((ERejection) tag) {
			case RejectionDateAndTime:
				setRejectionDateAndTime(value);
				break;
			case RejectionReasonCode:
				setRejectionReasonCode(value);
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
  			return ERejection.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getRejectionDateAndTime() {
		return this.rejectionDateAndTime;	
	}
	public void setRejectionDateAndTime(String argument) {
		this.rejectionDateAndTime = argument;
	}

	public String getRejectionReasonCode() {
		return this.rejectionReasonCode;	
	}
	public void setRejectionReasonCode(String argument) {
		this.rejectionReasonCode = argument;
	}

	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.rejectionDateAndTime) && 
				Utils.isStringEmpty(this.rejectionReasonCode));			
	}	
}
