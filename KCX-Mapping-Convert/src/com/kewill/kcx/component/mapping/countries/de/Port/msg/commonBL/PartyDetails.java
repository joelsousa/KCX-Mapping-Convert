package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PartyDetails extends KCXMessage {
		
	private String partyId;
	private Address addressQualified;
	private AddressUnqualified addressUnqualified;
	private Contact contact;
	private String taxRegistrationNumber;
	private List<String> referenceList;
	
	private enum EPartyDetails {	
		PartyId,
		AddressQualified,
		AddressUnqualified,
		Contact,
		TaxRegistrationNumber,			
		Reference;			       		
   }	

	public PartyDetails() {
		super();  
	}

	public PartyDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPartyDetails) tag) {  
  				case AddressQualified:
  					addressQualified = new Address(getScanner());  	
  					addressQualified.parse(tag.name());
					break; 
  				case AddressUnqualified:
  					addressUnqualified = new AddressUnqualified(getScanner());  	
  					addressUnqualified.parse(tag.name());
  					break;
  				case Contact:
  					contact = new Contact(getScanner());  	
  					contact.parse(tag.name());  					
  					break;  				
				default:
  					break;
  			}
  		} else {

  			switch((EPartyDetails) tag) {  
  				case PartyId:
  					setPartyId(value);
  					break;
  				case TaxRegistrationNumber:
  					setTaxRegistrationNumber(value);
  					break;
  				case Reference:
  					addReferenceList(value);
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
  			return EPartyDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getPartyId() {
		return partyId;
	}    
	public void setPartyId(String value) {
		this.partyId = value;
	}
		
	public String getTaxRegistrationNumber() {
		return taxRegistrationNumber;
	}    
	public void setTaxRegistrationNumber(String value) {
		this.taxRegistrationNumber = value;
	}	
	
	public Address getAddressQualified() {
		return addressQualified;
	}    
	public void setAddressQualified(Address address) {
		this.addressQualified = address;
	}
	
	public AddressUnqualified getAddressUnqualified() {
		return addressUnqualified;
	}    
	public void setAddressUnqualified(AddressUnqualified address) {
		this.addressUnqualified = address;
	}	
	public void setAddressUnqualified(String name, String s1, String s2, String s3, String s4 ) {
		AddressUnqualified adr = new AddressUnqualified(name, s1, s2, s3, s4);
		this.addressUnqualified = adr;
	}	
	
	public Contact getContact() {
		return contact;
	}    
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public List<String> getReferenceList() {
		return referenceList;
	}    
	public void setReferenceList(List<String> list) {
		this.referenceList = list;
	}
	public void addReferenceList(String value) {
		if (referenceList == null) {
			referenceList = new ArrayList<String>();
		}
		this.referenceList.add(value);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(partyId) && Utils.isStringEmpty(taxRegistrationNumber) &&
		addressQualified == null && addressUnqualified == null && contact == null &&
		referenceList == null;
	}
}

