package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
//import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EntryCarrier;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.EFormat;
/**
 * Module 		: MsgEntrySummaryDeclarationAmendmentRejection<br>
 * Date Started	: November 09, 2010<br>
 * Description	: Message class of EntrySummaryDeclarationAmendmentRejection message.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class MsgEntrySummaryDeclarationAmendmentRejection extends KCXMessage {
	private String  msgName = "EntrySummaryDeclarationAmendmentRejection";
	private String  referenceNumber;
	private String  mrn;
	private String  amendmentRejectionDateAndTime;
	private String  customsOfficeFirstEntry;
	private Party   personLodgingSuma;
	private TIN     personLodgingSumaTIN;
	private Address personLodgingSumaAdr;
	private Party   representative;
	private TIN     representativeTIN;
	private Address representativeAdr;
	private String  amendmentDateAndTime;
	private EFormat amendmentDateAndTimeFormat;
	private EFormat amendmentRejectionDateAndTimeFormat;
	private List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
	
	public MsgEntrySummaryDeclarationAmendmentRejection() {
		super();
	}
	
	public MsgEntrySummaryDeclarationAmendmentRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	private enum EEntrySummaryDeclarationAmendmentRejection {
		//UIDS:							KIDS:
		LocalReferenceNumber,			ReferenceNumber,
		MRN,							//same
		RejectionDateAndTime,			AmendmentRejectionDateAndTime,
		OfficeOfFirstEntry,				CustomsOfficeFirstEntry,
		PersonLodgingSumaTIN,			PersonLodgingSuma,
		PersonLodgingSumaAddress,
		RepresentativeTIN,				Representative,
		RepresentativeAddress,
		DateAndTimeOfAmendment,			AmendmentDateAndTime,
		Motivation,						//no equivalent
		Error,							FunctionalError
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EEntrySummaryDeclarationAmendmentRejection) tag) {
			case PersonLodgingSumaTIN:
				personLodgingSumaTIN = new TIN(getScanner());
				personLodgingSumaTIN.parse(tag.name());				
				break;						
			case PersonLodgingSumaAddress:
				personLodgingSumaAdr = new Address(getScanner());
				personLodgingSumaAdr.parse(tag.name());					
			case PersonLodgingSuma:
				Trader personLodgingSumaTrader = new Trader(getScanner());
				personLodgingSumaTrader.parse(tag.name());	
				personLodgingSuma = setPartyFromTrader(personLodgingSumaTrader);
				break;
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner());
				representativeTIN.parse(tag.name());				
			case RepresentativeAddress:
				representativeAdr = new Address(getScanner());
				representativeAdr.parse(tag.name());	
				break;			
			case Representative:
				Trader represenstativeTrader = new Trader(getScanner());
				represenstativeTrader.parse(tag.name());	
				representative = setPartyFromTrader(represenstativeTrader);
				break;
			case Error:
			case FunctionalError:
				FunctionalError wrkFunctionalError = new FunctionalError(getScanner());
				wrkFunctionalError.parse(tag.name());
				functionalErrorList.add(wrkFunctionalError);
				break;
			default:
				return;
			}
		} else {
			switch ((EEntrySummaryDeclarationAmendmentRejection) tag) {
			case LocalReferenceNumber:
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			case MRN:
				setMrn(value);
				break;
			case RejectionDateAndTime:
			case AmendmentRejectionDateAndTime:
				setAmendmentRejectionDateAndTime(value);
				
				if (tag == EEntrySummaryDeclarationAmendmentRejection.AmendmentRejectionDateAndTime) {
					setAmendmentRejectionDateAndTimeFormat(EFormat.KIDS_DateTime);
				} else {
					//EI20110208: setAmendmentRejectionDateAndTimeFormat(EFormat.ST_DateTimeT);
					setAmendmentRejectionDateAndTimeFormat(getUidsDateAndTimeFormat(value));    //EI20110208
				}
				break;
			case OfficeOfFirstEntry:
			case CustomsOfficeFirstEntry:
				setCustomsOfficeFirstEntry(value);
				break;
			case DateAndTimeOfAmendment:
			case AmendmentDateAndTime:
				setAmendmentDateAndTime(value);
				
				if (tag == EEntrySummaryDeclarationAmendmentRejection.AmendmentDateAndTime) {
					setAmendmentDateAndTimeFormat(EFormat.KIDS_DateTime);
				} else {
					//EI20110208: setAmendmentDateAndTimeFormat(EFormat.ST_DateTimeT);
					setAmendmentDateAndTimeFormat(getUidsDateAndTimeFormat(value));   //EI20110208: 
				}
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
			return EEntrySummaryDeclarationAmendmentRejection.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getMsgName() {
		return this.msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getAmendmentRejectionDateAndTime() {
		return this.amendmentRejectionDateAndTime;
	}

	public void setAmendmentRejectionDateAndTime(
			String amendmentRejectionDateAndTime) {
		this.amendmentRejectionDateAndTime = amendmentRejectionDateAndTime;
	}

	public String getCustomsOfficeFirstEntry() {
		return this.customsOfficeFirstEntry;
	}

	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = customsOfficeFirstEntry;
	}
	
	
	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null || personLodgingSumaAdr != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}
		}
		if (personLodgingSumaTIN != null) {
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
		}
		if (personLodgingSumaAdr != null) {
			personLodgingSuma.setAddress(personLodgingSumaAdr);		
		}		
		return this.personLodgingSuma;	
	}
	public void setPersonLodgingSuma(Party personLodgingSuma) {
		this.personLodgingSuma = personLodgingSuma;
	}

	public Party getRepresentative() {
		if (representativeTIN != null || representativeAdr != null) { 
			if (representative == null) {
				representative = new Party();
			}
		}
		if (representativeTIN != null) {
			representative.setPartyTIN(representativeTIN);		
		}
		if (representativeAdr != null) {
			representative.setAddress(representativeAdr);		
		}		
		return this.representative;
	}
	public void setRepresentative(Party representative) {
		this.representative = representative;
	}

	public String getAmendmentDateAndTime() {
		return this.amendmentDateAndTime;
	}

	public void setAmendmentDateAndTime(String amendmentDateAndTime) {
		this.amendmentDateAndTime = amendmentDateAndTime;
	}

	public List<FunctionalError> getFunctionalErrorList() {
		return functionalErrorList;
	}

	public void setFunctionalErrorList(List<FunctionalError> functionalErrorList) {
		this.functionalErrorList = functionalErrorList;
	}

	public String getMrn() {
		return this.mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public EFormat getAmendmentDateAndTimeFormat() {
		return this.amendmentDateAndTimeFormat;
	}

	public void setAmendmentDateAndTimeFormat(EFormat amendmentDateAndTimeFormat) {
		this.amendmentDateAndTimeFormat = amendmentDateAndTimeFormat;
	}

	public EFormat getAmendmentRejectionDateAndTimeFormat() {
		return this.amendmentRejectionDateAndTimeFormat;
	}

	public void setAmendmentRejectionDateAndTimeFormat(
			EFormat amendmentRejectionDateAndTimeFormat) {
		this.amendmentRejectionDateAndTimeFormat = amendmentRejectionDateAndTimeFormat;
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
