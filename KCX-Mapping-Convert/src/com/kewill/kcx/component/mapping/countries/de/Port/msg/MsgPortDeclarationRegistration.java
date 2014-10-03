package com.kewill.kcx.component.mapping.countries.de.Port.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.StatusAnnotation;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Port<br>.
 * Created		: 27.04.2012<br>
 * Description	: KIDS MsgPortDeclarationRegistration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgPortDeclarationRegistration extends KCXMessage { 
	
	private String			msgName = "MsgPortDeclarationRegistration";
	private String			referenceNumber;         //beznr
	private String			portSystem;              //DAK, BHT
	private String			sendingDateTime;         //	     	
	private String			registrationNumber;     //B-/Z-Nummer	
	private String			registrationMode;            // NRL RSL (not)released		
	private String			portContactName;         
	private String			portContactPhone;    
	private String			portContactEmail;          	
		
	public MsgPortDeclarationRegistration() {
		super();
	}
	public MsgPortDeclarationRegistration(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EPortDeclarationStatus {
		//KIDS:							KFF:
		ReferenceNumber,
		PortSystem,	
		SendingDateTime,				
		PortRegistrationNumber,
		PortRegistrationMode,
		PortContactName,
		PortContactPhone,		
		PortContactEmail;							
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPortDeclarationStatus) tag) {
			
			default:
				return;
			}			 				
		} else {
			switch ((EPortDeclarationStatus) tag) {
			case ReferenceNumber:			
				setReferenceNumber(value);
				break;
			case PortSystem:		
				setPortSystem(value);
				break;			
			case SendingDateTime:		
				setSendingDateTime(value);
				break;													
			case PortRegistrationNumber:				
				setPortRegistrationNumber(value);
				break;				
			case PortRegistrationMode:
				setPortRegistrationMode(value);
				break;	
			case PortContactName:
				setPortContactName(value);
				break;	
			case PortContactPhone:
				setPortContactPhone(value);
				break;	
			case PortContactEmail:								
				setPortContactEmail(value);
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
			return EPortDeclarationStatus.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getPortSystem() {
		return portSystem;
	}
	public void setPortSystem(String value) {
		this.portSystem = value;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getSendingDateTime() {
		return sendingDateTime;
	}
	public void setSendingDateTime(String value) {
		this.sendingDateTime = value;
	}	
			
	public String getPortRegistrationNumber() {
		return registrationNumber;
	}
	public void setPortRegistrationNumber(String value) {
		this.registrationNumber = value;
	}
	
	public String getPortRegistrationMode() {
		return registrationMode;
	}
	public void setPortRegistrationMode(String value) {
		this.registrationMode = value;
	}
	public String getPortContactName() {
		return portContactName;
	}
	public void setPortContactName(String value) {
		this.portContactName = value;
	}
		
	public String getPortContactPhone() {
		return portContactPhone;
	}
	public void setPortContactPhone(String value) {
		this.portContactPhone = value;
	}
	
	public String getPortContactEmail() {
		return portContactEmail;
	}
	public void setPortContactEmail(String value) {
		this.portContactEmail = value;
	}
	
	
}
