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
 * Created		: 28.11.2012<br>
 * Description	: KIDS MsgPortMindermengen
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgPortMindermengen extends KCXMessage { 
	
	private String			msgName = "PortDeclarationStatus";
	private String			referenceNumber;         //beznr
	private String			portSystem;              //aki_hasys: ZAPP, BHT
	private String			sendingDateTime;       
	private String			statusSender;         
	private String			sequenceNumber;
	private String			mrn;        
	private String			portRegistrationNumber;                
	//private String			                
	 
    //     TODO noch nicht definiert weder FSS noch KIDS
		
	public MsgPortMindermengen() {
		super();
	}
	public MsgPortMindermengen(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EPortDeclarationStatus {
		//KIDS:							KFF:
		ReferenceNumber,
		PortSystem,	
		SendingDateTime,
		StatusSender,
		SequenceNumber,
		MRN,		
		PortRegistrationNumber,
		PortRegistrationMode,
	    //TODO	
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
			case StatusSender:			
				setStatusSender(value);
				break;
			case SequenceNumber:
				setSequenceNumber(value);
				break;
			case MRN:	
				setMrn(value);
				break;
			case PortRegistrationNumber:			
				setPortRegistrationNumber(value);
				break;
			
			//TODO
				
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
	
	public String getStatusSender() {
		return statusSender;
	}
	public void setStatusSender(String value) {
		this.statusSender = value;
	}
	
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String value) {
		this.sequenceNumber = value;
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String value) {
		this.mrn = value;
	}
	
	public String getPortRegistrationNumber() {
		return portRegistrationNumber;
	}
	public void setPortRegistrationNumber(String value) {
		this.portRegistrationNumber = value;
	}
	
}
