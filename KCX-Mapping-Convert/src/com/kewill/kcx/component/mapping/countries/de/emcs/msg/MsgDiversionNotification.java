package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.StringList;

/**
 * Module		: EMCS<br>
 * Created		: 17.05.2010<br>
 * Description 	: Contains Message Structure with fields used in EMCSDiversionNotification (KIDS)
 *              : EMCSNotificationDivertedEAAD (UIDS)    
 *              : IE803 / C_EAD_NOT.
 * @author iwaniuk
 * @version 1.0.00  
 */

public class MsgDiversionNotification extends KCXMessage {

	private String msgName = "EMCSDiversionNotification";
	private String referenceNumber;	
	private String customerOrderNumber;
	private String clerk;
	private String notificationType;
	private String aadReferenceCode;	
	private String sequenceNumber;
	private String dateAndTimeOfNotification;
	private StringList followUpAadReference;
 
	private enum EDiversionNotification {
		//KIDS:									UIDS:
		ReferenceNumber,        			LocalReferenceNumber,				
		CustomerOrderNumber,     			//not defined	
		Clerk,                  		    //not defined   
		NotificationType,					//same
		AadReferenceCode,					//same
		SequenceNumber,						//same
		DateAndTimeOfNotification, 			NotificationDateAndTime,
		FollowUpAadReference, FollowUpAadReferences;			
	}
	
	public MsgDiversionNotification() {
		super();	
	}

	public MsgDiversionNotification(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EDiversionNotification) tag) {	
			
			case FollowUpAadReference:	
			case FollowUpAadReferences:             //EI20110928
				followUpAadReference = new StringList(getScanner());
				followUpAadReference.parse(tag.name());				
				break;			
			default:
				return;
			}
	    } else {
	    	switch ((EDiversionNotification) tag) {
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
			case DateAndTimeOfNotification:
			case NotificationDateAndTime:
				 setDateAndTimeOfNotification(value);
				 break;
			case NotificationType:
				 setNotificationType(value);
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
  			return EDiversionNotification.valueOf(token);
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
	
	public String getDateAndTimeOfNotification() {
		return this.dateAndTimeOfNotification;
	}
	public void setDateAndTimeOfNotification(String argument) {
		this.dateAndTimeOfNotification = argument;
	}
	
	public String getNotificationType() {
		return this.notificationType;
	}
	public void setNotificationType(String argument) {			
		this.notificationType = argument;
	}	
	
	public StringList getFollowUpAadReference() {
		return this.followUpAadReference;
	}
	public void setFollowUpAadReference(StringList argument) {
		this.followUpAadReference = argument;
	}	
}
