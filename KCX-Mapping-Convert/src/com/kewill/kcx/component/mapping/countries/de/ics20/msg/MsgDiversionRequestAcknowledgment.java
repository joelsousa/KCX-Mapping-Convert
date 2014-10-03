package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 15.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSDiversionRequestAcknowledgment.
 *  			: (IE325).
 *
 * @author krzoska
 * @version 2.0.00
 */

public class MsgDiversionRequestAcknowledgment extends KCXMessage {

	private String  msgName = "ICSDiversionRequestAcknowledgment";
	private String  msgType = "";
	
	private String  referenceNumber;
	private String  registrationDateTime;
	private Party   submitter;
	private TIN     submitterTIN;	
	private ContactPerson submitterContact;
	private String  customsOfficeOfFirstEntry;
	
	private EFormat registrationDateTimeFormat;
	
	
	public MsgDiversionRequestAcknowledgment() {
		super();
	}

	public MsgDiversionRequestAcknowledgment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgDiversionRequestAcknowledgment(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
	}

	private enum EDiversionRequestAcknowledgment {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		RegistrationDateTime,			RegistrationDateAndTime,										
		SubmitterTIN,                   Submitter,										
		SubmitterAddress,               									
		SubmitterContact,               									
		CustomsOfficeOfFirstEntry,		OfficeOfFirstEntry,
	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EDiversionRequestAcknowledgment) tag) {
			case SubmitterTIN:			
				submitterTIN = new TIN(getScanner());
				submitterTIN.parse(tag.name());				
				break;
			case SubmitterAddress:
			case Submitter:
				submitter = new Party(getScanner());
				submitter.parse(tag.name());				
				break;
			case SubmitterContact:
				submitterContact = new ContactPerson(getScanner());
				submitterContact.parse(tag.name());								
				break;

			default:
				return;
			}
	    } else {
	    	switch ((EDiversionRequestAcknowledgment) tag) {
    			case ReferenceNumber:
    			case LocalReferenceNumber:
    				 setReferenceNumber(value);
    				 break;
    			case RegistrationDateTime:
    			case RegistrationDateAndTime:
    				 setRegistrationDateTime(value);    
    				 if (tag == EDiversionRequestAcknowledgment.RegistrationDateTime) {
    					 setRegistrationDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));
    				 } else {    					
    					 setRegistrationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
    				 }
    				 break;
    			case CustomsOfficeOfFirstEntry:
    			case OfficeOfFirstEntry:
    				 setCustomsOfficeOfFirstEntry(value);
    				 break;
    			    
    			default: break;
			}
	    }
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
 		try {
  			return EDiversionRequestAcknowledgment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	/*
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
    */
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getRegistrationDateTime() {
		return registrationDateTime;
	}

	public void setRegistrationDateTime(String registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}

	public EFormat getRegistrationDateTimeFormat() {
		return registrationDateTimeFormat;
	}

	public void setRegistrationDateTimeFormat(EFormat registrationDateTimeFormat) {
		this.registrationDateTimeFormat = registrationDateTimeFormat;
	}

	public String getCustomsOfficeOfFirstEntry() {
		return customsOfficeOfFirstEntry;
	}

	public void setCustomsOfficeOfFirstEntry(String customsOfficeOfFirstEntry) {
		this.customsOfficeOfFirstEntry = customsOfficeOfFirstEntry;
	}
	
	public Party getSubmitter() {						
		if (submitterTIN != null) {
			if (submitter == null) {
				submitter = new Party();
			}
			submitter.setPartyTIN(submitterTIN);		
		}		
		if (submitterContact != null) {
			if (submitter == null) {
				submitter = new Party();
			}
			submitter.setContactPerson(submitterContact);		
		}	
		return submitter;
	}
	
	public void setSubmitter(Party submitter) {		
		this.submitter = submitter;
	}

}
