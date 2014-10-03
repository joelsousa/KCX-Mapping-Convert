package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;


import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: DestinationTrader 
 * Created     	: 31.08.2008
 * Description 	: Contains the DestinationTrader Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
//public class DestinationTrader extends KCXMessage {
public class NCTSTrader extends KCXMessage {
	private String type;
	private String authorisationID;
	private String customerID;
	private String customsID;
    private String eTNAddress;
    private String electronicSignature;
    private String tIN;
    private String tINType;
    private String isTINGermanApprovalNumber;
    private String vATID;
    private String tIRHolderID;
    private String status;
    private AddressNCTS address;
    private ContactPerson contact;

	private XMLEventReader parser = null;
	 
	private enum EnctsTrader {
		 //UIDS						//KIDS
		AuthorisationID,			
		CustomerID,
		CustomsID,
		ETNAddress,					//same
		ElectronicSignature,
		TIN,
		TINType,
		IsTINGermanApprovalNumber,
		VATID,						VATNumber,
		TIRHolderID,
		Status,
		Address,					//same
		Contact;
   }

	public NCTSTrader(String type) {
		super();
		this.type = type;
	}

	public NCTSTrader(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public NCTSTrader(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EnctsTrader) tag) {

			case Address:
			address = new AddressNCTS(getScanner()); 
			address.parse(tag.name()); 			
			break;
			
  			default:
  					return;
  			}
  		} else {

  			switch ((EnctsTrader) tag) {
  					
  				case ETNAddress:
  					seteTNAddress(value);
  					break;
  					
  				case VATID:
  				case VATNumber:
  					setvATID(value);
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
  			return EnctsTrader.valueOf(token);
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

	public String geteTNAddress() {
		return eTNAddress;
	}

	public void seteTNAddress(String eTNAddress) {
		this.eTNAddress = eTNAddress;
	}

	public String getElectronicSignature() {
		return electronicSignature;
	}

	public void setElectronicSignature(String electronicSignature) {
		this.electronicSignature = electronicSignature;
	}

	public String gettIN() {
		return tIN;
	}

	public void settIN(String tIN) {
		this.tIN = tIN;
	}

	public String gettINType() {
		return tINType;
	}

	public void settINType(String tINType) {
		this.tINType = tINType;
	}

	public String getIsTINGermanApprovalNumber() {
		return isTINGermanApprovalNumber;
	}

	public void setIsTINGermanApprovalNumber(String isTINGermanApprovalNumber) {
		this.isTINGermanApprovalNumber = isTINGermanApprovalNumber;
	}

	public String getvATID() {
		return vATID;
	}

	public void setvATID(String vATID) {
		this.vATID = vATID;
	}

	public String gettIRHolderID() {
		return tIRHolderID;
	}

	public void settIRHolderID(String tIRHolderID) {
		this.tIRHolderID = tIRHolderID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddressNCTS getAddress() {
		return address;
	}

	public void setAddress(AddressNCTS address) {
		this.address = address;
	}

	public ContactPerson getContact() {
		return contact;
	}

	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public boolean isEmpty() {
		if (Utils.isStringEmpty(this.authorisationID) && 
				Utils.isStringEmpty(this.customerID) && 
				Utils.isStringEmpty(this.customsID) && 
				Utils.isStringEmpty(this.eTNAddress) && 
				Utils.isStringEmpty(this.electronicSignature) && 
				Utils.isStringEmpty(this.tIN) && 
				Utils.isStringEmpty(this.tINType) && 
				Utils.isStringEmpty(this.isTINGermanApprovalNumber) && 
				Utils.isStringEmpty(this.vATID) && 
				Utils.isStringEmpty(this.tIRHolderID) && 
				Utils.isStringEmpty(this.status) && 
				this.address.isEmpty() && 
				this.contact.isEmpty()) {
				return true;
			} else {
				return false;
			}
	}
}
