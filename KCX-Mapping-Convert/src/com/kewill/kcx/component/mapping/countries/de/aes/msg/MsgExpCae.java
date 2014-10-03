/*
 * Function    : MsgExpCae.java
 * Titel       :
 * Date        : 22.10.2008
 * Author      : Kewill CSF / Marcus Messer
 * Description : Contains the Message Cancellation Response
 * 			   : with all Fields used in UIDS and (!!) KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

/**
 * Modul		: MsgExpCae<br>
 * Erstellt		: 22.10.2008<br>
 * Beschreibung	: Contains the Message Cancellation Response with all Fields used in UIDS and (!!) KIDS. 
 * 
 * @author Messer
 * @version 1.0.00
 */
public class MsgExpCae extends KCXMessage {

	private String typeOfDocument;
	private String reasonOfAnnulment;
	private String registrationNumber;
	private String typeOfAnnulment;
	private String decisionFlag;
	
	private enum EExpCaeTags {
		// Kids-TagNames, 			UIDS-TagNames;		
		DeclarationKind,			TypeOfDocument,
		DeclarationNumberCustoms,	RegistrationNumber,
		Reason,						ReasonOfAnnulment,
		CancellationType,			TypeOfAnnulment,
		CancellationAcceptance,		DecisionFlag;
	}

	public MsgExpCae() {
		super();
	}
	public MsgExpCae(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}	

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
		
			switch ((EExpCaeTags) tag) {
			default:
					return;
			}
		} else {
			
			switch ((EExpCaeTags) tag) {
		
				case DeclarationKind: case TypeOfDocument:
					setTypeOfDocument(value);
					break;
				
				case CancellationType: case TypeOfAnnulment:
					setTypeOfAnnulment(value);
					break;
					
				case Reason: case ReasonOfAnnulment:
					setReasonOfAnnulment(value);
					break;
					
				case DeclarationNumberCustoms: case RegistrationNumber:
					setRegistrationNumber(value);
					break;
									
				case CancellationAcceptance: case DecisionFlag:
					setDecisionFlag(value);
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EExpCaeTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getTypeOfDocument() {
		return typeOfDocument;
	}

	public void setTypeOfDocument(String typeOfDocument) {
		this.typeOfDocument = typeOfDocument;
	}

	public String getReasonOfAnnulment() {
		return reasonOfAnnulment;
	}

	public void setReasonOfAnnulment(String reasonOfAnnulment) {
		this.reasonOfAnnulment = reasonOfAnnulment;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getTypeOfAnnulment() {
		return typeOfAnnulment;
	}

	public void setTypeOfAnnulment(String typeOfAnnulment) {
		this.typeOfAnnulment = typeOfAnnulment;
	}

	public String getDecisionFlag() {
		return decisionFlag;
	}

	public void setDecisionFlag(String decisionFlag) {
		this.decisionFlag = decisionFlag;
	}	

}
