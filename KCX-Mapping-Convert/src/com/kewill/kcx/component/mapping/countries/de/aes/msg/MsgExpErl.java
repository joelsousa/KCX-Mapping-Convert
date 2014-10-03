/*
 * Function    : MsgExpErl.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / 
 * Description : ManualTermination
 *
 * Changes
 * -----------
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 * 
 * 
 * Changes
 * -----------
 * Author      : CK
 * Date        : 08.09.2009
 * Description : ReferenceNumber in Methode startElement() eingefügt
 * 
 */
 
 package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;

/**
 * Modul		: MsgExpErl<br>
 * Erstellt		: 17.09.2008<br>
 * Beschreibung	: ManualTermination.
 * 
 * @author ???
 * @version 1.0.00
 */
public class MsgExpErl extends KCXMessage {
	private String terminationTime = "";
	private String annotation = "";
	private String referenceNumber = "";	
	private Seal seal;
	//EI20090609: private String clerk;     
	private ContactPerson contact;  //EI20090609
	private HeaderExtensions headerExtensions;
	
	private enum EDeclaration {
		//KIDS				//UIDS
		TerminationTime,	DateOfTermination,
		Annotation,			Remark,
		ReferenceNumber,	//same
		Clerk,				
		ContactPerson,      Contact,
		Seal,				HeaderExtensions;

	}
	
	public MsgExpErl() {
	
	}
	public MsgExpErl(XMLEventReader parser) {
		super(parser);
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		switch ((EDeclaration) tag) {
		case Contact:
		case ContactPerson:	
			String clerk = "";
			if (contact != null) { 
				clerk = contact.getClerk(); //EI090609 wohin damit???
			}
			contact = new ContactPerson(getScanner());
			contact.parse(tag.name());
			//this.clerk = contact.getClerk();
			break;
			
		case Seal:					
			seal.parse(tag.name());
			break;
		case HeaderExtensions:
			headerExtensions = new HeaderExtensions(getScanner());
			headerExtensions.parse(tag.name());
			break;
		}
		} else {
			switch ((EDeclaration) tag) {
			case TerminationTime:
			case DateOfTermination:
				terminationTime = value;
				break;
				
			case Annotation:
			case Remark:
				annotation = value;
				break;
				
			// CK 08.09.2009 ReferenceNumber hier eingefügt
			case ReferenceNumber:
				referenceNumber = value;
				break;
				
			case Clerk:
				//setClerk(value);  
				if (contact == null) {					
					contact = new ContactPerson();
				    contact.setClerk(value);
				}
				break;			
			
			default:
				return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EDeclaration.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getTerminationTime() {
		return terminationTime;
	}

	public void setTerminationTime(String terminationTime) {
		this.terminationTime = terminationTime;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Seal getSeal() {
		if (headerExtensions != null) {
			if (seal == null) {
				seal = new Seal(getScanner());
			seal.setUseOfTydenseals(headerExtensions.getTydenSealsFlag());
			seal.setUseOfTydensealStock(headerExtensions.getTydenSealsStockFlag());
			}	
		}
		return seal;
	}

	public void setSeal(Seal seal) {
		this.seal = seal;
	}
	/*
	public String getClerk() {
		return clerk;	
	}
	public void setClerk(String clerk) {
		this.clerk = Utils.checkNull(clerk);
	}
	*/
	public ContactPerson getContact() {
		return contact;	
	}
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}	
	
}
