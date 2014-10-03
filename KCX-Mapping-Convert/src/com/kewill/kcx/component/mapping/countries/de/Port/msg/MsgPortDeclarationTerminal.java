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
 * Created		: 04.06.2013<br>
 * Description	: KIDS MsgPortDeclarationTerminal,
 * 				: eingefuehrt um FSS-BRZ/UV abzufangen, da sie sonst als ERR durchging
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgPortDeclarationTerminal extends KCXMessage { 
	
	private String			msgName = "MsgPortDeclarationTerminal";
	private String			referenceNumber;         //beznr
	private String			portSystem;              //DAK, BHT
	private String			sendingDateTime;         //	     	
	private String			registrationNumber;     //B-/Z-Nummer	
	private String			terminal;           	

	public MsgPortDeclarationTerminal() {
		super();
	}
	public MsgPortDeclarationTerminal(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EPortDeclarationStatus {
		//KIDS:							KFF:
		ReferenceNumber,
		PortSystem,	
		SendingDateTime,				
		PortRegistrationNumber,
		Terminal,							
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
			case Terminal:				
				setPortRegistrationNumber(value);
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
	
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String value) {
		this.terminal = value;
	}
	
}
