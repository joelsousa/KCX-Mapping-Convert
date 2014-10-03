package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.FunctionalError;

/**
 * Modul		: EMCS<br>
 * Date			: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSGenericRefusalMessage and
 * 				: UIDS EMCSDeclarationRejected (MT_EMCSRejection). 
 *              : (IE704 / N_REJ_DAT)   
 * @author krzoska
 * @version 1.0.00
 * 
 */

public class MsgGenericRefusalMessage extends KCXMessage {

	private String msgName = "EMCSGenericRefusalMessage";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;	
	private String sequenceNumber;
	private String rejectionDateAndTime;  //EI20100630
	private String rejectionReason;       //EI20100630
	
	private List<FunctionalError> functionalErrorList;  //von Typ CT_FunctionalError
	
 
	private enum EGenericRefusalMessage {
		//KIDS:									UIDS:
		ReferenceNumber,        				LocalReferenceNumber,  
		CustomerOrderNumber,     				//not defined
		Clerk,                  			    //not defined  
		AadReferenceCode,						//same
		SequenceNumber,							//same
		RejectionDateAndTime,					//same
		RejectionReason,						//same
		FunctionalError,				       Error;		 //Error von Typ CT_Error
	}
	
	public MsgGenericRefusalMessage() {
		super();
	}

	public MsgGenericRefusalMessage(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EGenericRefusalMessage) tag) {
			case FunctionalError:
			case Error:
				FunctionalError functionalError = new FunctionalError(getScanner());
				functionalError.parse(tag.name());
				if (functionalErrorList == null) {
					functionalErrorList = new Vector<FunctionalError>();
				}
				functionalErrorList.add(functionalError);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EGenericRefusalMessage) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;		
			case Clerk:
				 setClerk(value);
				 break;	
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;
			case SequenceNumber:
				setSequenceNumber(value);
				break;
			case RejectionDateAndTime:
				 setRejectionDateAndTime(value);
				 break;
			case RejectionReason:
				setRejectionReason(value);
				break;	
			default:
				break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {		
	}

	public Enum translate(String token) {
 		try {
  			return EGenericRefusalMessage.valueOf(token);
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
		return referenceNumber;	
	}

	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getCustomerOrderNumber() {
		return customerOrderNumber;	
	}

	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}

	public String getClerk() {
		return clerk;	
	}

	public void setClerk(String argument) {
		this.clerk = argument;
	}

	public String getAadReferenceCode() {
		return aadReferenceCode;	
	}

	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}

	public String getSequenceNumber() {
		return sequenceNumber;	
	}

	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getRejectionDateAndTime() {
		return rejectionDateAndTime;	
	}
	public void setRejectionDateAndTime(String argument) {
		this.rejectionDateAndTime = argument;
	}
	
	public String getRejectionReason() {
		return rejectionReason;	
	}
	public void setRejectionReason(String argument) {
		this.rejectionReason = argument;
	}	

	public List<FunctionalError> getFunctionalErrorList() {
		return functionalErrorList;	
	}

	public void setFunctionalErrorList(List<FunctionalError> list) {
		this.functionalErrorList = list;
	}
	
}
