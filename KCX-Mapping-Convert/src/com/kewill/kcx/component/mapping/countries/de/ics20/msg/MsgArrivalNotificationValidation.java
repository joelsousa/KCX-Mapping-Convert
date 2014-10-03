package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgArrivalNotificationValidation<br>
 * Created		: 22.06.2010<br>
 * Description  : Contains Message Structure with fields used in ICSArrivalNotificationValidation.
 * 				: (IE348)
 *                 
 * @author krzoska
 * @version 2.0.00
 *
 */
public class MsgArrivalNotificationValidation extends KCXMessage {

	private String  msgName = "ICSArrivalNotificationValidation";
	private String  msgType = "";
	
	private String  referenceNumber;	
	private String  mrn;
	private String  registrationDateTime;	
	private Party   carrier;	
	private TIN     carrierTIN;	
	private String 	arrivalRegistrationDateTime;
	private String 	arrivalRejectionDateTime;
	private String 	arrivalRejectionReason;
	private ArrayList <FunctionalError> functionalErrorList = null;
	private String  customsOfficeFirstEntry;

	private EFormat registrationDateTimeFormat;
	private EFormat arrivalRegistrationDateTimeFormat;
	private EFormat arrivalRejectionDateTimeFormat;
	
	
	public MsgArrivalNotificationValidation() {
		super();				
	}

	public MsgArrivalNotificationValidation(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	public MsgArrivalNotificationValidation(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);	
		msgType = type;
	}

	private enum EArrivalNotificationValidation {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		MRN,							//Same
		RegistrationDateTime,			RegistrationDateAndTime,		
		CarrierTIN,						Carrier, EntryCarrier, //.Tin + Address
		CarrierAddress,					//
		ArrivalRegistrationDateTime,    ENRRegistrationDateTime,
		ArrivalRejectionDateTime,       RejectionDateTime,
		ArrivalRejectionReason,         RejectionReason,
		FunctionalError,                Error,
		CustomsOfficeFirstEntry;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EArrivalNotificationValidation) tag) {			
    			case CarrierTIN:
    				carrierTIN = new TIN(getScanner());
    				carrierTIN.parse(tag.name());				
    				break;
    			case CarrierAddress:
    			case Carrier:	
    			case EntryCarrier:
    				carrier = new Party(getScanner());
    				carrier.parse(tag.name());				
    				break;
    				
    			case FunctionalError:
    			case Error:
    				FunctionalError functionalError = new FunctionalError(getScanner());
    				functionalError.parse(tag.name());
    				addFunctionalErrorList(functionalError);

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
						 setRegistrationDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));
					 } else {				
						 setRegistrationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
					 }
    				 break;
    			case ArrivalRegistrationDateTime:
    			case ENRRegistrationDateTime:
    				setArrivalRegistrationDateTime(value);
    				 if (tag == EArrivalNotificationValidation.ArrivalRegistrationDateTime) {
						 setArrivalRegistrationDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));
					 } else {				
						 setArrivalRegistrationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
					 }
    				break;
    			case ArrivalRejectionDateTime:
    			case RejectionDateTime:
    				setArrivalRejectionDateTime(value);
    				if (tag == EArrivalNotificationValidation.ArrivalRejectionDateTime) {
						 setArrivalRejectionDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));
					 } else {				
						 setArrivalRejectionDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
					 }
    				break;
    			case ArrivalRejectionReason:
    			case RejectionReason:
    				setArrivalRejectionReason(value);    				
    				break;
    			case CustomsOfficeFirstEntry:
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
		
	public Party getCarrier() {
		if (carrierTIN != null) {
			if (carrier == null) {
				carrier = new Party();
			}		
			carrier.setPartyTIN(carrierTIN);				
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
	
	public EFormat getRegistrationDateTimeFormat() {
		return registrationDateTimeFormat;
	}

	public void setRegistrationDateTimeFormat(EFormat eFormat) {
		this.registrationDateTimeFormat = eFormat;
	}
	
	public EFormat getArrivalRegistrationDateTimeFormat() {
		return arrivalRegistrationDateTimeFormat;
	}

	public void setArrivalRegistrationDateTimeFormat(EFormat arrivalRegistrationDateTimeFormat) {
		this.arrivalRegistrationDateTimeFormat = arrivalRegistrationDateTimeFormat;
	}
	
	public void setArrivalRejectionDateTimeFormat(
			EFormat arrivalRejectionDateTimeFormat) {
		this.arrivalRejectionDateTimeFormat = arrivalRejectionDateTimeFormat;
	}

	public EFormat getArrivalRejectionDateTimeFormat() {
		return arrivalRejectionDateTimeFormat;
	}	
	
	private void addFunctionalErrorList(FunctionalError functionalError) {
		if (functionalErrorList == null) {
			functionalErrorList = new ArrayList<FunctionalError>();
		}
		functionalErrorList.add(functionalError);
	}

	public ArrayList<FunctionalError> getFunctionalErrorList() {
		return functionalErrorList;
	}

	public String getArrivalRegistrationDateTime() {
		return arrivalRegistrationDateTime;
	}

	public void setArrivalRegistrationDateTime(String arrivalRegistrationDateTime) {
		this.arrivalRegistrationDateTime = Utils.checkNull(arrivalRegistrationDateTime);
	}

	public String getArrivalRejectionDateTime() {
		return arrivalRejectionDateTime;
	}

	public void setArrivalRejectionDateTime(String arrivalRejectionDateTime) {
		this.arrivalRejectionDateTime = Utils.checkNull(arrivalRejectionDateTime);
	}

	public String getArrivalRejectionReason() {
		return arrivalRejectionReason;
	}

	public void setArrivalRejectionReason(String arrivalRejectionReason) {
		this.arrivalRejectionReason = Utils.checkNull(arrivalRejectionReason);
	}

	public String getCustomsOfficeFirstEntry() {
		return customsOfficeFirstEntry;
	}

	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = Utils.checkNull(customsOfficeFirstEntry);
	}
}
