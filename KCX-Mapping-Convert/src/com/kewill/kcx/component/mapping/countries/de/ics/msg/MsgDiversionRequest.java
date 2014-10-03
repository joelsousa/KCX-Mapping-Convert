package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgDiversionRequest<br>
 * Erstellt		: 01.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSDiversionRequest.
 *                 
 * @author Pete T
 * @version 1.0.00
 */
public class MsgDiversionRequest extends KCXMessage {

	private String  msgName = "ICSDiversionRequest";
	private String  referenceNumber;
	private TransportMeans meansOfTransportBorder;	
	private String  declaredDateOfArrival;
	private String  declaredCountryOfArrival;
	private Party   submitter;
	private TIN     submitterTIN;
	private Address submitterAddress;
	private ContactPerson submitterContact;
	private String  declaredOfficeOfFirstEntry;
	private String  actualOfficeOfFirstEntry;
	private String  informationType;
	private List<ImportOperation> importOperationList = new ArrayList<ImportOperation>();
	private EFormat declaredDateOfArrivalFormat;
	
	private boolean inUIDSSubmitter = false;

	public MsgDiversionRequest() {
		super();				
	}

	public MsgDiversionRequest(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	private enum EDiversionRequest {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,  
		MeansOfTransportBorder,			TransportAtBorder,		
		DeclaredDateOfArrival,			ExpectedDateOfArrival,
		DeclaredCountryOfArrival,		OriginalCountryOfFirstEntry,										
		SubmitterTIN,                   Submitter,										
		SubmitterAddress,										
		SubmitterContact,
		DeclaredOfficeOfFirstEntry,		OriginalOfficeOfFirstEntry,
		ActualOfficeOfFirstEntry,		NewOfficeOfFirstEntry,
		InformationType,				//Same
		ImportOperation;				//Same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EDiversionRequest) tag) {
			case MeansOfTransportBorder:	
			case TransportAtBorder:	
				TransportMeans wrkMeansOfTransportBorder = new TransportMeans(getScanner());
				wrkMeansOfTransportBorder.parse(tag.name());
				setMeansOfTransportBorder(wrkMeansOfTransportBorder);
				break;			
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
			case ImportOperation:
				ImportOperation wrkImportOperation = new ImportOperation(getScanner());
				wrkImportOperation.parse(tag.name());
				importOperationList.add(wrkImportOperation);
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
	    	switch ((EDiversionRequest) tag) {
    			case ReferenceNumber:
    			case LocalReferenceNumber:
    				 setReferenceNumber(value);
    				 break;	
    			case DeclaredDateOfArrival:
    			case ExpectedDateOfArrival:
    				 setDeclaredDateOfArrival(value);
    				 
    				 if (tag == EDiversionRequest.DeclaredDateOfArrival) {
    					 setDeclaredDateOfArrivalFormat(EFormat.KIDS_Date);
    				 } else {
    					//EI20110208: setDeclaredDateOfArrivalFormat(EFormat.ST_DateTimeT);
    					 setDeclaredDateOfArrivalFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 break;		
    			case DeclaredCountryOfArrival:
    			case OriginalCountryOfFirstEntry:
    				 setDeclaredCountryOfArrival(value);
    				 break;	
    			case DeclaredOfficeOfFirstEntry:
    			case OriginalOfficeOfFirstEntry:
    				 setDeclaredOfficeOfFirstEntry(value);
    				 break;
    			case ActualOfficeOfFirstEntry:
    			case NewOfficeOfFirstEntry:
    				 setActualOfficeOfFirstEntry(value);
    				 break;					 
    			case InformationType:
    				 setInformationType(value);
    				 break;					 
    			
    			default: break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {
		if (tag == EDiversionRequest.Submitter) {
			inUIDSSubmitter = false;
		}
	}

	public Enum translate(String token) {
 		try {
  			return EDiversionRequest.valueOf(token);
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
	
	public TransportMeans getMeansOfTransportBorder() {
		return meansOfTransportBorder;
	}

	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		if (meansOfTransportBorder == null) {
			return;
		}
		
		this.meansOfTransportBorder = meansOfTransportBorder;
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}

	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = declaredDateOfArrival;
	}

	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}

	public void setDeclaredDateOfArrivalFormat(EFormat declaredDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = declaredDateOfArrivalFormat;
	}

	public String getDeclaredCountryOfArrival() {
		return declaredCountryOfArrival;
	}

	public void setDeclaredCountryOfArrival(String declaredCountryOfArrival) {
		this.declaredCountryOfArrival = declaredCountryOfArrival;
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

	public String getDeclaredOfficeOfFirstEntry() {
		return declaredOfficeOfFirstEntry;
	}

	public void setDeclaredOfficeOfFirstEntry(String declaredOfficeOfFirstEntry) {
		this.declaredOfficeOfFirstEntry = declaredOfficeOfFirstEntry;
	}

	public String getActualOfficeOfFirstEntry() {
		return actualOfficeOfFirstEntry;
	}

	public void setActualOfficeOfFirstEntry(String actualOfficeOfFirstEntry) {
		this.actualOfficeOfFirstEntry = actualOfficeOfFirstEntry;
	}

	public String getInformationType() {
		return informationType;
	}

	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}

	public List<ImportOperation> getImportOperationList() {
		return importOperationList;
	}

	public void setImportOperationList(List<ImportOperation> importOperationList) {
		this.importOperationList = importOperationList;
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
