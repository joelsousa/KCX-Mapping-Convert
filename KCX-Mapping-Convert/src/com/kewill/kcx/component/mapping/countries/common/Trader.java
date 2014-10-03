/*
 * Function    : PartyU.java
 * Titel       :
 * Date        : 12.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the PartyU Data
 * 			   : with all Fields used in UIDS 
 * Parameters  :

 * Changes
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Trader<br>
 * Erstellt		: 12.09.2008<br>
 * Beschreibung	: Contains the PartyU Data with all Fields used in UIDS. 
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Trader extends KCXMessage {

    private String type;
	private String authorisationID;
	private String customerID;
	private String customsID;
    private String eTNAddress;
    private String electronicSignature;
    private String tin;
    private String tinType;
    private String vATID;
    private String status;
    private Address address;
    private ContactPerson contactPerson;
    private String branch;

	private XMLEventReader parser = null;
	 
	 private enum EPartyU {
		AuthorisationID,
		CustomerID,
		CustomsID,
		ETNAddress, ETNAdress,
		ElectronicSignature,
		TIN,
		TINType,    //EI20110120
		BO, Branch,   //EI20121023
		VATID,
		Status,
		Address,		
		ContactPerson, Contact;
   }

	public Trader(String type) {
		super();
		this.type = type;
	}

	public Trader(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public Trader(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPartyU) tag) {

			case Address:
			address = new Address(getScanner()); 
			address.parse(tag.name()); 			
			break;

			case ContactPerson:
			case Contact:
			contactPerson = new ContactPerson(getScanner());
			contactPerson.parse(tag.name()); 			
			break;
			
  			default:
  					return;
  			}
  		} else {

  			switch ((EPartyU) tag) {
  				case AuthorisationID:
  					setAuthorisationID(value);
  					break;  					
  				case CustomerID:
  					setCustomerID(value);
  					break;  					
  				case CustomsID:
  					setCustomsID(value);
  					break;  					
  				case ETNAddress:
  				case ETNAdress:
  					setETNAddress(value);
  					break;  					
  				case ElectronicSignature:
  					setElectronicSignature(value);
  					break;  					
  				case TIN:
  					setTIN(value);
  					break;  					
  				case TINType:
  					setTINType(value);
  					break;  					
  				case VATID:
  					setVATID(value);
  					break;  				
  				case Status:
  					setStatus(value);
  					break;
  				case BO:
  				case Branch:
  					setBranch(value);
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
  			return EPartyU.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthorisationID() {
		return authorisationID;
	}

	public void setAuthorisationID(String authorisationID) {
		this.authorisationID = authorisationID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomsID() {
		return customsID;
	}

	public void setCustomsID(String customsID) {
		this.customsID = customsID;
	}

	public String getETNAddress() {
		return eTNAddress;
	}

	public void setETNAddress(String address) {
		eTNAddress = address;
	}

	public String getElectronicSignature() {
		return electronicSignature;
	}

	public void setElectronicSignature(String electronicSignature) {
		this.electronicSignature = electronicSignature;
	}

	public String getTIN() {
		return this.tin;
	}

	public void setTIN(String tin) {
		this.tin = tin;
	}
	
	public String getTINType() {
		return this.tinType;
	}

	public void setTINType(String tinType) {
		this.tinType = tinType;
	}

	public String getVATID() {
		return vATID;
	}

	public void setVATID(String vatid) {
		vATID = vatid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String bo) {
		this.branch = bo;
	}

}
