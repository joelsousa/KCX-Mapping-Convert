package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgDiversionRequest<br>
 * Created		: 18.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSDiversionRequest.
 * 				: (IE323).                  
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MsgDiversionRequest extends KCXMessage {

	private String   msgName = "ICSDiversionRequest";
	private String   msgType = "";
	
	private String   referenceNumber;
	private TransportMeans meansOfTransportBorder;	
	private String   declaredDateOfArrival;
	private String   declaredCountryOfArrival;
	private Party    submitter;
	private TIN      submitterTIN;	
	private ContactPerson submitterContact;
	private String   declaredOfficeOfFirstEntry;
	private String   actualOfficeOfFirstEntry;
	private String   informationType;
	private String   declarationTime;         //new V20
	private String   declarationPlace;        //new V20
	private List<ImportOperation> importOperationList = new ArrayList<ImportOperation>();
	
	private EFormat  declaredDateOfArrivalFormat;
	private EFormat  declarationTimeFormat;   //new V20
	
	
	public MsgDiversionRequest() {
		super();				
	}

	public MsgDiversionRequest(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	public MsgDiversionRequest(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);	
		msgType = type;
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
		DeclarationTime,                DeclarationDateAndTime,  
		DeclarationPlace,               //same
		InformationType,				//Same
		ImportOperation;				//Same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EDiversionRequest) tag) {
			case MeansOfTransportBorder:	
			case TransportAtBorder:	
				meansOfTransportBorder = new TransportMeans(getScanner(), msgType);
				meansOfTransportBorder.parse(tag.name());				
				break;			
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
			case ImportOperation:
				ImportOperation wrkImportOperation = new ImportOperation(getScanner());
				wrkImportOperation.parse(tag.name());
				importOperationList.add(wrkImportOperation);
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
    					 setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));
    				 } else {    				
    					 setDeclaredDateOfArrivalFormat(Utils.getUidsDateAndTimeFormat(value)); 
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
    			case DeclarationDateAndTime:
    			case DeclarationTime:
    				setDeclarationTime(value);
    				if (tag == EDiversionRequest.DeclarationTime) {
    					setDeclarationTimeFormat(Utils.getKidsDateAndTimeFormat(value));
    				} else {
    					setDeclarationTimeFormat(Utils.getUidsDateAndTimeFormat(value));
    				}	
    				break;
    			case DeclarationPlace:
    				setDeclarationPlace(value);
   				 break;	
    			
    			default: break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {
		
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
	
	public String getDeclarationTime() {
		return declarationTime;
	}

	public void setDeclarationTime(String value) {
		this.declarationTime = value;
	}
	
	public String getDeclarationPlace() {
		return declarationPlace;
	}

	public void setDeclarationPlace(String value) {
		this.declarationPlace = value;
	}
	
	public EFormat getDeclarationTimeFormat() {
		return declarationTimeFormat;
	}

	public void setDeclarationTimeFormat(EFormat format) {
		this.declarationTimeFormat = format;
	}
	
	
}
