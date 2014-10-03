package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgArrivalNotificationValidation<br>
 * Erstellt		: 22.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSArrivalNotificationValidation.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgArrivalNotificationValidation extends KCXMessage {

	private String  msgName = "ICSArrivalNotificationValidation";
	private String  referenceNumber;	
	private String  mrn;
	private String  registrationDateTime;	
	private Party   carrier;	
	private TIN     carrierTIN;
	private Address carrierAdr;
	
	private String  arrivalRegistrationDateTime;	//EI20130114
	private String  rejectionDateTime;				//EI20130114
	private String  rejectionReason;				//EI20130114
	private FunctionalError  functionalError;		//EI20130114
	private ArrayList<FunctionalError>    functionalErrorList;   //AK20130826:
	private String  customsOfficeFirstEntry;		//EI20130114

	private EFormat registrationDateTimeFormat;  		 //EI20110105
	private EFormat arrivalRegistrationDateTimeFormat;   //EI20130114
	private EFormat rejectionDateTimeFormat;    		 //EI20130114
	
	public MsgArrivalNotificationValidation() {
		super();				
	}

	public MsgArrivalNotificationValidation(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}

	private enum EArrivalNotificationValidation {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		MRN,							//Same
		RegistrationDateTime,			RegistrationDateAndTime,		
		CarrierTIN,						Carrier, //.Tin + Address
		CarrierAddress,					//	
		ArrivalRegistrationDateTime,    ENRRegistrationDateAndTime,
		ArrivalRejectionDateTime, 		RejectionDateTime,
		ArrivalRejectionReason,			RejectionReason,
		FunctionalError,				Error,
		CustomsOfficeFirstEntry,		OfficeFirstEntry;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EArrivalNotificationValidation) tag) {			
    			case CarrierTIN:
    				carrierTIN = new TIN(getScanner());
    				carrierTIN.parse(tag.name());				
    				break;
    			case CarrierAddress:
    				carrierAdr = new Address(getScanner());
    				carrierAdr.parse(tag.name());				
    				break;
    			case Carrier:			
    				Trader carrierTrader = new Trader(getScanner());
    				carrierTrader.parse(tag.name());
    				carrier = setPartyFromTrader(carrierTrader);
    				break;	
    			case FunctionalError:
    			case Error:
    				functionalError = new FunctionalError(getScanner());
    				functionalError.parse(tag.name());	
					addFunctionalErrorList(functionalError);  //AK20130826:
    				break;
    				
    			default:
    				return;
    			}
	    } else {
	    	switch ((EArrivalNotificationValidation) tag) {
    			case ReferenceNumber:
    			case LocalReferenceNumber:
    				 setReferenceNumber(value);
    				 break;
    				 
    			case MRN:
    				 setMrn(value);
    				 break;	
    				 
    			case RegistrationDateTime:
    			case RegistrationDateAndTime:
    				 setRegistrationDateTime(value);
    				 if (tag == EArrivalNotificationValidation.RegistrationDateTime) {
						 setRegistrationDateTimeFormat(EFormat.KIDS_DateTime);
					 } else {
						//EI20110208: setRegistrationDateTimeFormat(EFormat.ST_DateTimeT);
						 setRegistrationDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
					 }
    				 break;
    				 
    			case ArrivalRegistrationDateTime:
    			case ENRRegistrationDateAndTime:
    				 setArrivalRegistrationDateTime(value);
    				 if (tag == EArrivalNotificationValidation.ArrivalRegistrationDateTime) {
						 setArrivalRegistrationDateTimeFormat(EFormat.KIDS_DateTime);
					 } else {
						//EI20110208: setRegistrationDateTimeFormat(EFormat.ST_DateTimeT);
						 setArrivalRegistrationDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
					 }
    				 break;
    				 
    			case ArrivalRejectionDateTime:
    			case RejectionDateTime:
    				setRejectionDateTime(value);
    				 if (tag == EArrivalNotificationValidation.ArrivalRejectionDateTime) {
						 setRejectionDateTimeFormat(EFormat.KIDS_DateTime);
					 } else {						
						 setRejectionDateTimeFormat(getUidsDateAndTimeFormat(value)); 
					 }
    				break;
    				
    			case ArrivalRejectionReason:			
    			case RejectionReason:
    				setRejectionReason(value);
    				break;
    				
    			case CustomsOfficeFirstEntry:		
    			case OfficeFirstEntry:
    				setCustomsOfficeFirstEntry(value);
    				break;
    				
    			default: break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
 		try {
  			return EArrivalNotificationValidation.valueOf(token);
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
		
	public Party getCarrier() {
		if (carrierTIN != null || carrierAdr != null) {
			if (carrier == null) {
				carrier = new Party();
			}
		}
		if (carrierTIN != null) {
			carrier.setPartyTIN(carrierTIN);				
		}
		if (carrierAdr != null) {
			carrier.setAddress(carrierAdr);				
		}	
		return carrier;
	}
	public void setCarrier(Party party) {		
		this.carrier = party;
	}

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String argument) {
		this.mrn = argument;
	}

	public String getRegistrationDateTime() {
		return this.registrationDateTime;
	}
	public void setRegistrationDateTime(String argument) {
		this.registrationDateTime = argument;
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
	public String getArrivalRegistrationDateTime() {
		return arrivalRegistrationDateTime;
	}
	public void setArrivalRegistrationDateTime(String value) {
		this.arrivalRegistrationDateTime = value;
	}
	public EFormat getArrivalRegistrationDateTimeFormat() {
		return arrivalRegistrationDateTimeFormat;
	}
	public void setArrivalRegistrationDateTimeFormat(EFormat eFormat) {
		this.arrivalRegistrationDateTimeFormat = eFormat;
	}
	
	public EFormat getRegistrationDateTimeFormat() {
		return registrationDateTimeFormat;
	}
	public void setRegistrationDateTimeFormat(EFormat eFormat) {
		this.registrationDateTimeFormat = eFormat;
	}
	
	public String getRejectionDateTime() {
		return rejectionDateTime;
	}
	public void setRejectionDateTime(String value) {
		this.rejectionDateTime = value;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String value) {
		this.rejectionReason = value;
	}
/*  EI20131001 auskommentiert: darf jetzt nur Liste verwendet werden
	public FunctionalError getFunctionalError() {
		return functionalError;
	}
	public void setFunctionalError(FunctionalError functionalError) {
		this.functionalError = functionalError;
	}
*/
	public String getCustomsOfficeFirstEntry() {
		return customsOfficeFirstEntry;
	}

	public void setCustomsOfficeFirstEntry(String value) {
		customsOfficeFirstEntry = value;
	}

	public EFormat getRejectionDateTimeFormat() {
		return rejectionDateTimeFormat;
	}

	public void setRejectionDateTimeFormat(EFormat rejectionDateTimeFormat) {
		this.rejectionDateTimeFormat = rejectionDateTimeFormat;
	}

	private EFormat getUidsDateAndTimeFormat(String value) {  //EI20110208
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
	
	//AK20130826:
	private void addFunctionalErrorList(FunctionalError tempError) {
		if (this.functionalErrorList == null) {
	   		this.functionalErrorList = new ArrayList<FunctionalError>();
	   	}
	   	this.functionalErrorList.add(tempError);
	}

	public ArrayList<FunctionalError> getFunctionalErrorList() {
		return functionalErrorList;
	}

	public void setFunctionalErrorList(
			ArrayList<FunctionalError> functionalErrorList) {
		this.functionalErrorList = functionalErrorList;
	}

}
