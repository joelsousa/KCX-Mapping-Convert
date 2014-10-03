package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: EMCS<br>
 * Created		: 20.07.2011<br>
 * Description  : Contains Message Structure with fields used in EMCSStatusResponse) 
 *              : IE905 / C_STD_RSP.  
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgStatusResponse extends KCXMessage {

	private String msgName = "EMCSStatusResponse";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;		
	private String sequenceNumber;
	private String status;
	private String lastReceivedMessageType;	
 
	private enum EExplDelay {
		//KIDS:									UIDS:
		ReferenceNumber,        				LocalReferenceNumber,
		CustomerOrderNumber,     				//not defined
		Clerk,                  			    //not defined  
		AadReferenceCode,						//same		
		SequenceNumber,                         //same
		Status,              					//same
		LastReceivedMessageType;				//same
	}
	
	public MsgStatusResponse() {
		super();
	}

	public MsgStatusResponse(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExplDelay) tag) {
			
			default:
				return;
			}
	    } else {
	    	switch ((EExplDelay) tag) {
			case ReferenceNumber:		
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;		
			case Clerk:
				 setClerk(value);
				 break;	
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;		
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;					 			
						
			case Status:
				 setStatus(value);
				 break;				
			case LastReceivedMessageType:			
				 setLastReceivedMessageType(value);
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
  			return EExplDelay.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}	

	public String getClerk() {
		return this.clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String get() {
		return this.aadReferenceCode;
	}
	public void set(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String argument) {
		this.status = argument;
	}

	public String getLastReceivedMessageType() {
		return this.lastReceivedMessageType;
	}
	public void setLastReceivedMessageType(String argument) {
		this.lastReceivedMessageType = argument;
	}	
	
}
