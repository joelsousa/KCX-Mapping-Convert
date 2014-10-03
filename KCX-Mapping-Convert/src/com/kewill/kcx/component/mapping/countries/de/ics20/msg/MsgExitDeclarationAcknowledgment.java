package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 22.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSMsgExitDeclarationAcknowledgment.
 *
 * @author krzoska
 * @version 2.0.00
 */

public class MsgExitDeclarationAcknowledgment extends KCXMessage {

	private String  msgName = "ExitDeclarationAcknowledgment";
	private String  msgType = "";
	
	private String  referenceNumber;
	private String  mrn;
	private String  registrationDateAndTime;
	private Party   personLodgingSuma;
	private TIN	    personLodgingSumaTIN;
	private String  customsOfficeOfLodgment;
	
	private EFormat registrationDateTimeFormat;
	
	public MsgExitDeclarationAcknowledgment() {
		super();
	}

	public MsgExitDeclarationAcknowledgment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public MsgExitDeclarationAcknowledgment(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
	}

	
	private enum EExitDeclarationAcknowledgment {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		MRN,							//same
		RegistrationDateAndTime,		//same
		RegistrationDateTime,
		PersonLodgingSumaTIN,           PersonLodgingSumDec,
		PersonLodgingSumaAddress,										
		CustomsOfficeOfLodgement,		OfficeOfLodgement;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExitDeclarationAcknowledgment) tag) {
			case PersonLodgingSumaTIN:
				personLodgingSumaTIN = new TIN(getScanner());
				personLodgingSumaTIN.parse(tag.name());
				break;
			case PersonLodgingSumaAddress:
				personLodgingSuma = new Party(getScanner());
				personLodgingSuma.parse(tag.name());
				break;
		
			default:
				return;
			}
	    } else {
	    	switch ((EExitDeclarationAcknowledgment) tag) {
    			case ReferenceNumber:
    			case LocalReferenceNumber:
    				 setReferenceNumber(value);
    				 break;
    			case MRN:
    				 setMrn(value);
    				 break;
    			case RegistrationDateAndTime:
    			case RegistrationDateTime:    
    				 setRegistrationDateAndTime(value);
    				 if (msgType.equals("KIDS")) {
    					 setRegistrationDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));
    				 } else {					
    					 setRegistrationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
    				 }
    				 break;	
    			case CustomsOfficeOfLodgement:
    			case OfficeOfLodgement:
    				 setCustomsOfficeOfLodgment(value);
    				 break;
    			default: break;
			}
	    }
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
 		try {
  			return EExitDeclarationAcknowledgment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	/*
	public void setMsgName(String msgName) {
		this.msgName = msgName;
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

	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);
		}
		return this.personLodgingSuma;	
	}
	public void setPersonLodgingSuma(Party party) {
		this.personLodgingSuma = party;
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getRegistrationDateAndTime() {
		return registrationDateAndTime;
	}

	public void setRegistrationDateAndTime(String registrationDateAndTime) {
		this.registrationDateAndTime = registrationDateAndTime;
	}

	public String getCustomsOfficeOfLodgment() {
		return customsOfficeOfLodgment;
	}

	public void setCustomsOfficeOfLodgment(String customsOfficeOfLodgment) {
		this.customsOfficeOfLodgment = customsOfficeOfLodgment;
	}

	public void setRegistrationDateTimeFormat(EFormat registrationDateTimeFormat) {
		this.registrationDateTimeFormat = registrationDateTimeFormat;
	}
	
	public EFormat getRegistrationDateTimeFormat() {
		return registrationDateTimeFormat;
	}
}
