package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.EFormat;
/**
 * Module		: MsgEntrySummaryDeclarationRejected<br>
 * Created		: 2010.11.08<br>
 * Description	: Contains Message Structure with fields used in ICSEntrySummaryDeclarationRejected.
 * 				: (IE316)
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class MsgEntrySummaryDeclarationRejected extends KCXMessage {
	
	private String referenceNumber;
	private String declarationRejectionDateAndTime;
	private EFormat declarationRejectionDateAndTimeFormat;
	private String declarationRejectionReason;
	private String declarationRejectionReasonLNG;
	private List<FunctionalError> functionalErrorList;
	
	public MsgEntrySummaryDeclarationRejected() {
		super();				
	}

	public MsgEntrySummaryDeclarationRejected(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	private enum EMsgEntrySummaryDeclarationRejected {
		//KIDS								//UIDS
		ReferenceNumber, 					LocalReferenceNumber,
		DeclarationRejectionDateAndTime,	RejectionDateAndTime,
		DeclarationRejectionReason,			RejectionReason,
		DeclarationRejectionReasonLNG,		//none
		FunctionalError,					Error;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgEntrySummaryDeclarationRejected) tag) {
			case FunctionalError:
			case Error:
				FunctionalError tempError = new FunctionalError(getScanner());
				tempError.parse(tag.name());
				addFunctionalErrorList(tempError);				
				break;
			default:
				return;
			}
		} else {
			switch ((EMsgEntrySummaryDeclarationRejected) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
				
			case DeclarationRejectionDateAndTime:
			case RejectionDateAndTime:
				setDeclarationRejectionDateAndTime(value);
				if (tag == EMsgEntrySummaryDeclarationRejected.DeclarationRejectionDateAndTime) {
					 setDeclarationRejectionDateAndTimeFormat(EFormat.KIDS_DateTime);					
				 } else {
					 //EI20110208: setDeclarationRejectionDateAndTimeFormat(EFormat.ST_DateTimeT);
					 setDeclarationRejectionDateAndTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
				 }
				break;
				
			case DeclarationRejectionReason:
			case RejectionReason:
				setDeclarationRejectionReason(value);
				break;
				
			case DeclarationRejectionReasonLNG:
				setDeclarationRejectionReasonLNG(value);
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
  			return EMsgEntrySummaryDeclarationRejected.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getDeclarationRejectionDateAndTime() {
		return declarationRejectionDateAndTime;
	}

	public void setDeclarationRejectionDateAndTime(
			String declarationRejectionDateAndTime) {
		this.declarationRejectionDateAndTime = declarationRejectionDateAndTime;
	}

	public String getDeclarationRejectionReason() {
		return declarationRejectionReason;
	}

	public void setDeclarationRejectionReason(String declarationRejectionReason) {
		this.declarationRejectionReason = declarationRejectionReason;
	}

	public String getDeclarationRejectionReasonLNG() {
		return declarationRejectionReasonLNG;
	}

	public void setDeclarationRejectionReasonLNG(
			String declarationRejectionReasonLNG) {
		this.declarationRejectionReasonLNG = declarationRejectionReasonLNG;
	}

	public void setDeclarationRejectionDateAndTimeFormat(
			EFormat declarationRejectionDateAndTimeFormat) {
		this.declarationRejectionDateAndTimeFormat = declarationRejectionDateAndTimeFormat;
	}

	public EFormat getDeclarationRejectionDateAndTimeFormat() {
		return declarationRejectionDateAndTimeFormat;
	}	
	
	public void setFunctionalErrorList(List<FunctionalError> functionalErrorList) {
		this.functionalErrorList = functionalErrorList;
	}

	public List<FunctionalError> getFunctionalErrorList() {
		return functionalErrorList;
	}
	
	private void addFunctionalErrorList(FunctionalError tempError) {
		if (this.functionalErrorList == null) {
	   		this.functionalErrorList = new Vector<FunctionalError>();
	   	}
	   	this.functionalErrorList.add(tempError);
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
