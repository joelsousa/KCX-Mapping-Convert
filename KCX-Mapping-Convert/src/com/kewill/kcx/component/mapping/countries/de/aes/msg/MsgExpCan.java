/*
 * Function    : MsgExpCan.java
 * Titel       :
 * Date        : 08.09.2008
 * Author      : Kewill CSF / Sven Heise
 * Description : Contains the Message Cancellation 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Description : checked for V60 - no changes
 *
 * Author      : EI
 * Date        : 09.06.2009
 * Description : ContactPerson instead of clerk
 */
 
package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;

/**
 * Modul		: MsgExpCan<br>
 * Erstellt		: 08.09.2008<br>
 * Beschreibung	: Contains the Message Cancellation  with all Fields used in UIDS and  KIDS.
 * 
 * @author Heise
 * @version 1.0.00
 */
public class MsgExpCan extends KCXMessage {

	//EI20090609: private String identity;
	private ContactPerson contact; //EI20090609
	private String typeOfDocument;
	private String dateOfAnnulment;		
	private String reasonOfAnnulment;
	private String referenceNumber;
	private String typeOfAnnulment;
	private String declarationNumberCustoms;
	
	private enum EExpCanTags {
		// Kids-TagNames, 			UIDS-TagNames;
		//Cancellation,				Cancelation,
		//GoodsDeclaration,			Submit, Export,	
		KindOfDeclaration,			TypeOfDocument,  //only CH
		CancellationTime,			DateOfAnnulment,
		KindOfCancellation,			TypeOfAnnulment,
		Reason,						ReasonOfAnnulment,
		DeclarationNumberCustoms,	// not in UIDS
		ReferenceNumber,			// UIDS same KIDS
		Clerk,
		ContactPerson,              Contact; //EI20081029: Identity;
	}
	
	public MsgExpCan(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public MsgExpCan() {
		super();
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((EExpCanTags) tag) {
			case Contact: 				//EI20081029
			case ContactPerson: 	    //EI20090609
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());			
				/* if (contact != null) {
					this.identity = contactPerson.getClerk();
					
				} */				
				break;	

			default:
					return;
			}
		} else {			
			switch ((EExpCanTags) tag) {
			
				case KindOfDeclaration: case TypeOfDocument:
					setTypeOfDocument(value);
					break;	
					
				case CancellationTime: case DateOfAnnulment:
					setDateOfAnnulment(value);
					break;	
					
				case KindOfCancellation: case TypeOfAnnulment:
					setTypeOfAnnulment(value);
					break;	
					
				case Reason: case ReasonOfAnnulment:
					setReasonOfAnnulment(value);
					break;	
					
				case DeclarationNumberCustoms:
					setDeclarationNumberCustoms(value);
					break;	
					
				case ReferenceNumber:
					setReferenceNumber(value);
					break;
					
				case Clerk: 
					//setIdentity(value);
					if (contact == null) {
						contact = new ContactPerson();
						contact.setClerk(value);
					}
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EExpCanTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
    /*
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}	
    */
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}	
	
	public String getTypeOfDocument() {
		return typeOfDocument;
	}

	public void setTypeOfDocument(String typeOfDocument) {
		this.typeOfDocument = typeOfDocument;
	}

	public String getDateOfAnnulment() {
		return dateOfAnnulment;
	}

	public void setDateOfAnnulment(String dateOfAnnulment) {
		this.dateOfAnnulment = dateOfAnnulment;
	}

	public String getReasonOfAnnulment() {
		return reasonOfAnnulment;
	}

	public void setReasonOfAnnulment(String reasonOfAnnulment) {
		this.reasonOfAnnulment = reasonOfAnnulment;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTypeOfAnnulment() {
		return typeOfAnnulment;
	}

	public void setTypeOfAnnulment(String typeOfAnnulment) {
		this.typeOfAnnulment = typeOfAnnulment;
	}

	public String getDeclarationNumberCustoms() {
		return declarationNumberCustoms;
	}

	public void setDeclarationNumberCustoms(String declarationNumberCustoms) {
		this.declarationNumberCustoms = declarationNumberCustoms;
	}
		
}

