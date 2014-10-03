package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 23.11.2012
 * Description	: contains message structure with fields used in ICSDiversionRequestRejected.
 * 				: (IE324).
 * 
 * @author	: iwaniuk
 * @version 2.0.00
 *
 */

public class MsgDiversionRequestRejected extends KCXMessage {
	
	private String	msgName	= "ICSDiversionRequestRejected";
	private String  msgType = "";
	
	private String	referenceNumber;
	private String	declarationRejectionDateAndTime;
	private String	declarationRejectionReason;
	private String	declarationRejectionReasonLNG;
	private List<FunctionalError> functionalErrorList;
	
	private EFormat	declarationRejectionDateAndTimeFormat;
	
	
	public MsgDiversionRequestRejected() {
		super();
	}
	
	public MsgDiversionRequestRejected(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgDiversionRequestRejected(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
	}
	
	private enum EDiversionRequestRejected {
		// KIDS								// UIDS
		ReferenceNumber,					LocalReferenceNumber,
		DeclarationRejectionDateAndTime,	RejectionDateAndTime,
		DeclarationRejectionReason,			RejectionReason,
		DeclarationRejectionReasonLNG,		// none
		FunctionalError,					Error
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EDiversionRequestRejected) tag) {
				case FunctionalError:
				case Error:
					FunctionalError tmpError	= new FunctionalError(getScanner());
						tmpError.parse(tag.name());
						addFunctionalErrorList(tmpError);
					break;
				
				default:
					return;
			}
		} else {
			switch((EDiversionRequestRejected) tag) {
				case ReferenceNumber:
				case LocalReferenceNumber:
					setReferenceNumber(value);
					break;			
				case DeclarationRejectionDateAndTime:
				case RejectionDateAndTime:
					setDeclarationRejectionDateAndTime(value);
					if (tag == EDiversionRequestRejected.DeclarationRejectionDateAndTime) {
						setDeclarationRejectionDateAndTimeFormat(Utils.getKidsDateAndTimeFormat(value));
					} else {					
						setDeclarationRejectionDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
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
		// TODO Auto-generated method stub
		
	}
	
	public Enum translate(String token) {
		try {
			return EDiversionRequestRejected.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getMsgName() {
		return this.msgName;
	}
	/*
	public void setMsgName(String msgName) {
		this.msgName	= msgName;
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
	
	public void setReferenceNumber(String refNo) {
		this.referenceNumber	= refNo;
	}
	
	public String getDeclarationRejectionDateAndTime() {
		return this.declarationRejectionDateAndTime;
	}
	
	public void setDeclarationRejectionDateAndTime(String decRejDateTime) {
		this.declarationRejectionDateAndTime	= decRejDateTime;
	}
	
	public String getDeclarationRejectionReason() {
		return this.declarationRejectionReason;
	}
	
	public void setDeclarationRejectionReason(String decRejReason) {
		this.declarationRejectionReason	= decRejReason;
	}
	
	public String getDeclarationRejectionReasonLNG() {
		return this.declarationRejectionReasonLNG;
	}
	
	public void setDeclarationRejectionReasonLNG(String decRejReasonLNG) {
		this.declarationRejectionReasonLNG	= decRejReasonLNG;
	}
	
	public List< FunctionalError > getFunctionalErrorList() {
		return this.functionalErrorList;
	}
	
	public void setFunctionalErrorList(List<FunctionalError> errList) {
		this.functionalErrorList	= errList;
	}
	
	public EFormat getDeclarationRejectionDateAndTimeFormat() {
		return this.declarationRejectionDateAndTimeFormat;
	}
	
	public void setDeclarationRejectionDateAndTimeFormat(EFormat decRejDateAndTimeFormat) {
		this.declarationRejectionDateAndTimeFormat	= decRejDateAndTimeFormat;
	}
		
	private void addFunctionalErrorList(FunctionalError tmpError) {
		if (this.functionalErrorList == null) {
			this.functionalErrorList	= new Vector< FunctionalError >();
		}
		this.functionalErrorList.add(tmpError);
	}
	
}
