package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AttributesType;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description 	: Contains Message Structure with fields used in EMCSReminderMessage (KIDS)
 *              : EMCSReminderMessageForExciseMovement (UIDS)  
 *              : IE802 / C_EXP_REJ. 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgReminderMessage extends KCXMessage {

	private String msgName = "EMCSReminderMessage";
	private String referenceNumber;	
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;	
	private String sequenceNumber;
	private String dateAndTimeOfIssuance;
	private String limitDateAndTime;
	private Text reminderInformation;
	private String reminderMessageType;
	
 
	private enum EReminder {
		//KIDS:									UIDS:
		ReferenceNumber,        				LocalReferenceNumber,  		
		CustomerOrderNumber,     				//not defined
		Clerk,                  			    //not defined  
		AadReferenceCode,						ExciseMovementEaad, //ExciseMovementEaad.AadReferenceCode
		SequenceNumber,												//ExciseMovementEaad.SequenceNumber
		DateAndTimeOfIssuance, 					Attributes, //Attributes.DateAndTimeOfIssuanceOfReminder
		LimitDateAndTime,									//Attributes.LimitDateAndTime
		ReminderInformation,								//Attributes.ReminderInformation
		ReminderMessageType;				    			//Attributes.ReminderMessageType
	}
	
	public MsgReminderMessage() {
		super();
	}

	public MsgReminderMessage(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EReminder) tag) {			
			case ExciseMovementEaad:				
				ExciseMovementEaad tempExc = new ExciseMovementEaad(getScanner());
				tempExc.parse(tag.name());
				setExciseMovementEaad(tempExc);
				break;
			case Attributes:				
				AttributesType tempAttr = new AttributesType(getScanner());
				tempAttr.parse(tag.name());
				setAttributes(tempAttr);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EReminder) tag) {
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
			case DateAndTimeOfIssuance:
				 setDateAndTimeOfIssuance(value);
				 break;
			case LimitDateAndTime:
				 setLimitDateAndTime(value);
				 break;				 
			case ReminderInformation:
				 //reminderInformation = new Text(value, attr.getValue("language"));
				 reminderInformation = new Text(value, attr);  //EI20110926
				 break;	
			case ReminderMessageType:
				 setReminderMessageType(value);
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
  			return EReminder.valueOf(token);
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
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getDateAndTimeOfIssuance() {
		return this.dateAndTimeOfIssuance;
	}
	public void setDateAndTimeOfIssuance(String argument) {
		this.dateAndTimeOfIssuance = argument;
	}
	
	public String getLimitDateAndTime() {
		return this.limitDateAndTime;
	}
	public void setLimitDateAndTime(String argument) {
		this.limitDateAndTime = argument;
	}	
	
	public String getReminderMessageType() {
		return this.reminderMessageType;
	}
	public void setReminderMessageType(String argument) {
		this.reminderMessageType = argument;
	}	
	
	public Text getReminderInformation() {
		return this.reminderInformation;
	}
	public void setReminderInformation(Text argument) {
		this.reminderInformation = argument;
	}	
	
	public void setExciseMovementEaad(ExciseMovementEaad argument) {
		if (argument == null) {
			return;
		}		
		this.aadReferenceCode = argument.getAadReferenceCode();
		this.sequenceNumber = argument.getSequenceNumber();		
	}
	
	public void setAttributes(AttributesType argument) {
		if (argument == null) {
			return;
		}		
		this.dateAndTimeOfIssuance = argument.getDateAndTimeOfIssuance();	 
		this.limitDateAndTime = argument.getLimitDateAndTime();			
		this.reminderInformation = argument.getReminderInformation();							
		this.reminderMessageType = argument.getReminderMessageType();					    				
	}
	
	
}
