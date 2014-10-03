package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module 		: ICS20<br>
 * Created   	: 19.10.2012<br>
 * Description	: Message class of EntrySummaryDeclarationAmendmentRejection message.
 * 				: (IE305)
 * 
 * @author krzoska
 * @version 2.0.00
 */

public class MsgEntrySummaryDeclarationAmendmentRejection extends KCXMessage {
	
	private String  msgName = "EntrySummaryDeclarationAmendmentRejection";
	private String  msgType = "";
	
	private String  referenceNumber;
	private String  mrn;
	private String  amendmentRejectionDateAndTime;
	private String  customsOfficeFirstEntry;
	private Party   personLodgingSuma;
	private TIN     personLodgingSumaTIN;
	private Party   representative;
	private TIN     representativeTIN;	
	private String  amendmentDateAndTime;	
	private Text    motivation;                    //new V20 - only UIDS
	private List<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
	
	private EFormat amendmentDateAndTimeFormat;
	private EFormat amendmentRejectionDateAndTimeFormat;
	
	
	public MsgEntrySummaryDeclarationAmendmentRejection() {
		super();
	}
	
	public MsgEntrySummaryDeclarationAmendmentRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgEntrySummaryDeclarationAmendmentRejection(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
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
			case PersonLodgingSuma:
				personLodgingSuma = new Party(getScanner());
				personLodgingSuma.parse(tag.name());								
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner());
				representativeTIN.parse(tag.name());				
			case RepresentativeAddress:
			case Representative:
				representative = new Party(getScanner());
				representative.parse(tag.name());	
				break;						
			case Error:
			case FunctionalError:
				FunctionalError wrkFunctionalError = new FunctionalError(getScanner());
				wrkFunctionalError.parse(tag.name());
				functionalErrorList.add(wrkFunctionalError);
				break;
			case Motivation:
				motivation = new Text(getScanner());
				motivation.parse(tag.name());	
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
					setAmendmentRejectionDateAndTimeFormat(Utils.getKidsDateAndTimeFormat(value));
				} else {					
					setAmendmentRejectionDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value));   
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
					setAmendmentDateAndTimeFormat(Utils.getKidsDateAndTimeFormat(value));
				} else {					
					setAmendmentDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value));  
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
	
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getAmendmentRejectionDateAndTime() {
		return this.amendmentRejectionDateAndTime;
	}
	
	public void setAmendmentRejectionDateAndTime(String amendmentRejectionDateAndTime) {
		this.amendmentRejectionDateAndTime = amendmentRejectionDateAndTime;
	}

	public String getCustomsOfficeFirstEntry() {
		return this.customsOfficeFirstEntry;
	}
	
	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = customsOfficeFirstEntry;
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
	
	public void setPersonLodgingSuma(Party personLodgingSuma) {
		this.personLodgingSuma = personLodgingSuma;
	}

	public Party getRepresentative() {
		if (representativeTIN != null) { 
			if (representative == null) {
				representative = new Party();
			}		
			representative.setPartyTIN(representativeTIN);		
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
	
	public Text getMotivation() {
		return motivation;
	}
	
	public void setMotivation(Text text) {
		motivation = text;
	}
}
