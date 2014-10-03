/*
 * Function    : MsgConfirmFailure.java
 * Titel       :
 * Date        : 25.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Contains the Message FailureOrConfirmation 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 23.04.2009
 * Label       : EI20090423
 * Description : added Member: fileName, procedureType
 *
 * -----------
 * Author      : CK
 * Date        : 11.05.2010
 * 
 * Description : If KindOfError is Information "INF" which can be produced if customers want
 *				all logging information sent back then
 * 				the errortext does NOT describe an error!!
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Reason;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;

/**
 * Modul		: MsgFailure<br>
 * Erstellt		: 25.09.2008<br>
 * Beschreibung	: Contains the Message FailureOrConfirmation with all Fields used in UIDS and  KIDS.
 * 
 * @author Kron
 * @version 1.0.00
 */
public class MsgFailure extends KCXMessage {

	// simple tags
	private String uCRNumber;
	private String referenceNumber;
	private String orderNumber;
	private String correctionNumber;
	private String temporaryUCR;
	private String fileName;         //EI20090423
	private String procedureType;    //EI20090423
	// structure tags
	private PositionError error;
	private List <PositionError>errorList = new ArrayList<PositionError>();	
	
	// festhalten ob in der localApplicationResult tatsächlich eine
	// failure enthalten ist, sonst ist es eine confirm
	private Boolean errorInBody = false;
		
	private enum EErrConTags {

		// Kids-TagNames, 					UIDS-TagNames;
		  //GoodsDeclaration,					// UIDS: no equivalence
		  //GoodsItem,						// UIDS: no equivalence
		  PositionError,					Reason, //EI20120119   // UIDS: no equivalence
		  //ErrorCode,						// UIDS: no equivalence
		  //ErrorText,						Text, // oder soap:Text ?????  		  									
		  UCRNumber,	                    // UIDS: no equivalence
		  ReferenceNumber,					// UIDS: no equivalence
		  OrderNumber,						// UIDS: no equivalence
		  CorrectionNumber,					// UIDS: no equivalence
		  TemporaryUCR,						// UIDS: no equivalence		
		  FileName,
		  ProcedureType;
	}
	
	public MsgFailure() {
		super();		
	}
	
	public MsgFailure(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((EErrConTags) tag) {
						
			case PositionError: 
				//EI20081028: error = new PositionError();
				error = new PositionError(getScanner());	//EI20081028:		
				error.parse(tag.name());					//EI20081028:
				errorList.add(error);
				// If KindOfError is Information "INF" which can be produced if customers want
				// all logging information sent back then
				// the errortext does NOT describe an error!!
				// other valid values for KindOfError: ERR, WRG
				//EI20120625-commented: if (!error.getKindOfError().equalsIgnoreCase("INF")) {
				if (error.getKindOfError() != null && !error.getKindOfError().equalsIgnoreCase("INF")) {  //EI20120625 added check if != null
					errorInBody = true;
				} 				
				break;
			case Reason:                   //EI20120119
				Reason reason = new Reason(getScanner());			
				reason.parse(tag.name());				
				setErrorList(getPositionErrorList(reason));				
				break;
			
			default:
					return;
			}
		} else {
			
			switch ((EErrConTags) tag) {
		
				case UCRNumber: 
					setUCRNumber(value);
					break;
				
				case ReferenceNumber:
					setReferenceNumber(value);
					break;
					
				case OrderNumber:
					setOrderNumber(value);
					break;
					
				case CorrectionNumber:
					setCorrectionNumber(value);
					break;

				case TemporaryUCR: 
					setTemporaryUCR(value);
					break;
					
				case ProcedureType: 					
					setProcedureType(value);					
					break;
					
				case FileName: 					
					setFileName(value);					
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
			return EErrConTags.valueOf(token);
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

	public String getUCRNumber() {
		return uCRNumber;
	}

	public void setUCRNumber(
			String uCRNumber) {
		this.uCRNumber = uCRNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCorrectionNumber() {
		return correctionNumber;
	}
	public void setCorrectionNumber(String correctionNumber) {
		this.correctionNumber = correctionNumber;
	}
	public String getTemporaryUCR() {
		return temporaryUCR;
	}
	public void setTemporaryUCR(String temporaryUCR) {
		this.temporaryUCR = temporaryUCR;
	}
				
	public String getProcedureType() {
		return this.procedureType;
	}
	public void setProcedureType(String argument) {
		this.procedureType = argument;
	}	 					
	
	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String argument) {
		this.fileName = argument;
	}	
	
	public List getErrorList() {
		return errorList;
	}
	public void setErrorList(List<PositionError> errorList) {
		this.errorList = errorList;
	}
	public void addErrorList(PositionError error) {
		if (errorList == null) {
			errorList = new ArrayList<PositionError>();	
		}
		if (error != null) {
			this.errorList.add(error);
		}
	}
	private List<PositionError> getPositionErrorList(Reason reason) {  //EI20120119
		if (reason == null) {
			return null;
		}
		if (reason.getReasonList() == null) {
			return null;
		}
		
		
		if (reason.getReasonList().size() == 0) {
			errorList = null;
		} else {
			errorList = new ArrayList<PositionError>();		
			String texte = "";
			/* Version 1:
			for (String text : reason.getReasonList()) {				
				//PositionError error = new PositionError();
				//error.setErrorText(text);
				//addErrorList(error);
			} */
			//Version 2:
			PositionError error = new PositionError();
			for (String text : reason.getReasonList()) {									
				if (text != null) {
					texte = texte + " " + text;
				}
			}			
			error.setErrorText(texte.trim());
			addErrorList(error);
		}
		return errorList;
	}
	
	public Boolean getErrorInBody() {
		return errorInBody;
	}
	public void setErrorInBody(Boolean errorInBody) {
		this.errorInBody = errorInBody;
	}	
	
}
