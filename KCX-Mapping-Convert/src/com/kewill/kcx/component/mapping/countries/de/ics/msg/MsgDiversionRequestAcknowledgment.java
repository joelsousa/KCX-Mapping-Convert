package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgDiversionRequestAcknowledgment<br>
 * Erstellt		: 15.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSDiversionRequestAcknowledgment.
 *
 * @author Pete T
 * @version 1.0.00
 */

public class MsgDiversionRequestAcknowledgment extends KCXMessage {

	private String  msgName = "ICSDiversionRequestAcknowledgment";
	private String  referenceNumber;
	private String  registrationDateTime;
	private Party   submitter;
	private TIN     submitterTIN;
	private Address submitterAddress;
	private ContactPerson submitterContact;
	private String  customsOfficeOfFirstEntry;
	private EFormat registrationDateTimeFormat;

	private boolean inUIDSSubmitter = false;

	public MsgDiversionRequestAcknowledgment() {
		super();
	}

	public MsgDiversionRequestAcknowledgment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
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
				submitterAddress = new Address(getScanner());
				submitterAddress.parse(tag.name());				
				break;
			case SubmitterContact:
				submitterContact = new ContactPerson(getScanner());
				submitterContact.parse(tag.name());				
				break;
			case Submitter:
				Trader submitterTrader = new Trader(getScanner());
				submitterTrader.parse(tag.name());	
				submitter = setPartyFromTrader(submitterTrader);
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
    					 setRegistrationDateTimeFormat(EFormat.KIDS_DateTime);
    				 } else {
    					//EI20110208: setRegistrationDateTimeFormat(EFormat.ST_DateTimeT);
    					 setRegistrationDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
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
		if (tag == EDiversionRequestAcknowledgment.Submitter) {
			inUIDSSubmitter = false;
		}
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
	public void setMsgName(String argument) {
		this.msgName = argument;
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
		if (submitterTIN != null || submitterAddress != null || submitterContact != null) { 
			if (submitter == null) {
				submitter = new Party();
			}
		}
		if (submitterTIN != null) {
			submitter.setPartyTIN(submitterTIN);		
		}
		if (submitterAddress != null) {
			submitter.setAddress(submitterAddress);		
		}
		if (submitterContact != null) {
			submitter.setContactPerson(submitterContact);		
		}	
		return submitter;
	}
	public void setSubmitter(Party submitter) {		
		this.submitter = submitter;
	}
	
	public Party setPartyFromTrader(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party  = new Party();
		
		TIN	tin = new TIN();
		tin.setTIN(trader.getTIN());
		tin.setIsTINGermanApprovalNumber(trader.getCustomsID());
		tin.setCustomerIdentifier(trader.getCustomerID());	
		tin.setIdentificationType(trader.getTINType());   //EI20110120
		
		party.setPartyTIN(tin);		
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());
		party.setContactPerson(trader.getContactPerson());
		
		return party;		
	}
	
	public EFormat getUidsDateAndTimeFormat(String value) {  //EI20110208
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}
}
