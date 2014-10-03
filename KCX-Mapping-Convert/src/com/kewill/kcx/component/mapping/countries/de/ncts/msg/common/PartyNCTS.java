package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: Party 
 * Created     	: 02.09.2010
 * Description 	: Contains the Party Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class PartyNCTS extends KCXMessage {

	private boolean debug   = false;
	
	private String person;			//EI20090423: consignee, consignor usw.
    private String vatNumber;
    private String etnAddress;
    private AddressNCTS address;
    private TIN partyTIN = null;        
    private ContactPerson partyContact = null;
    private ArrayList<ContactPerson> partyContactList = null;   //EI20131014
 
	private enum EParty {	
		//KIDS:				       UIDS:
		VATNumber,			        VATID,
		ETNAddress,     	        //same
		ETNAdress,	
		Address,	   		        //same
		TIN,				        //same
		IsTINGermanApprovalNumber,	//same
		CustomerIdentifier,			CustomerID,	
									CustomsID,						
		ContactPerson,  			Contact;
   }	

	public PartyNCTS() {
		super();  
	}
	public PartyNCTS(String person) {
		super();  
		this.person = person;
	}	

	public PartyNCTS(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public PartyNCTS(XmlMsgScanner scanner, String person) {
  		super(scanner);
  		this.person = person;
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EParty) tag) {
				case Address:
  					address = new AddressNCTS(getScanner());  	
  					address.parse(tag.name());
  					break; 
  														
				case Contact:
				case ContactPerson:
					partyContact = new ContactPerson(getScanner());
					partyContact.parse(tag.name());	
					addPartyContactList(partyContact);   //EI20131014 
					break;
					
				default:
  					return;
  			}
  		} else {

  			switch((EParty) tag) { 
  			/*
  				case CustomerIdentifier:
					setCustomerIdentifier(value);
					break; 
		   */
  				case VATNumber:
  				case VATID:
  					setVATNumber(value);
  					break; 
  					
  				case ETNAddress:
  				case ETNAdress:
  					setETNAddress(value);
  					break; 
  					
  				case TIN:
  					setPartyTIN("TIN", value);
  					break;
  				case CustomsID:
  					setPartyTIN("CustomsID", value);
  					break;
  				case CustomerID:
  				case CustomerIdentifier:
  					setPartyTIN("CustomerID", value);
  					break;
  				case IsTINGermanApprovalNumber:
  					setPartyTIN("IsTINGermanApprovalNumber", value);
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
  			return EParty.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
  /*
  	public void setCustomerIdentifier(String argument) {
		this.vatNumber = argument;
	}
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}
*/	
	public void setVATNumber(String argument) {
		this.vatNumber = argument;
	}
	public String getVATNumber() {
		return vatNumber;
	}
    
    public void setETNAddress(String argument) {
		this.etnAddress = argument;
	}
	public String getETNAddress() {
		return etnAddress;
	}
	
	public AddressNCTS getAddress() {
		return address;
	}
    public void setAddress(AddressNCTS argument) {
		this.address = argument;
	}
    
    private void setPartyTIN(String tag, String value) {
    	if (partyTIN == null) {
    		partyTIN = new TIN();
    	}
    	if (tag.equals("TIN")) {
    		partyTIN.setTIN(value);
    	}
    	if (tag.equals("CustomerID")) {
    		partyTIN.setCustomerIdentifier(value);
    	}
    	if (tag.equals("IsTINGermanApprovalNumber")) {
    		partyTIN.setIsTINGermanApprovalNumber(value);
    	}
    }
    
    public void setPartyTIN(TIN argument) {
    	this.partyTIN = argument;
    }
    
    public TIN getPartyTIN() {
    	return this.partyTIN;
    }
    
    public ContactPerson getContactPerson() {
		return partyContact;
	}
    public void setContactPerson(ContactPerson argument) {
		this.partyContact = argument;
	} 
    
    public void addPartyContactList(ContactPerson argument) {  //EI20131014
    	if (this.partyContactList == null) {
    		partyContactList = new ArrayList<ContactPerson>(); 
    	}
    	partyContactList.add(argument);	
    }
    public ArrayList<ContactPerson> getContactPersonList() {
		return partyContactList;
	}
    public void setContactPersonList(ArrayList<ContactPerson> list) {
		this.partyContactList = list;
	} 
    
    public String getPerson() {
    	return this.person;
    }
    
	public boolean isEmpty() {
		if (partyTIN != null) {
			if (!partyTIN.isEmpty()) {
				return false;
			}
		}
		if (address != null) {
			if (!address.isEmpty()) {
				return false;
			}
		}		
		if (!Utils.isStringEmpty(vatNumber)) {
			return false;
		}
		if (Utils.isStringEmpty(etnAddress)) {
			return false;
		}
		if (partyContact != null) {
			if (!partyContact.isEmpty()) {
				return false;
			}
		}
		return true;
		
//		if (Utils.isStringEmpty(this.vatNumber) && 
//				Utils.isStringEmpty(this.etnAddress) && 
//				(this.partyTIN == null || 
//						(this.partyTIN != null && this.partyTIN.isEmpty())) && 
//						(this.address == null || 
//								(this.address != null && this.address.isEmpty())) && 
//						(this.partyContact == null || 
//								(this.partyContact != null && 
//										this.partyContact.isEmpty()))) {
//			return true;
//		} else {
//			return false;
//		}
	}    
}
