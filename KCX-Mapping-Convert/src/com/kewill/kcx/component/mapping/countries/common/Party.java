package com.kewill.kcx.component.mapping.countries.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: KCX<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the Party Data with all Fields used in KIDS and UIDS.
 *  			: for all Modules.
 * 				: KIDS: PartyTIN (CT TINType), Party (CT_AddressType), PartyContactPerson (CT_ContactPerson)
 * 				: UIDS: Name (CT_Trader == class Party)
 *              : in all Mappings U2K and K2U in class Party are all Member correct filled  
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Party extends KCXMessage {

	private boolean debug   = false;
	
	private String person;			//EI20090423: Patry == person: consignee, consignor usw.
    private String vatNumber;
    private String etnAddress;
    private Address address;
    private TIN partyTIN = null;        
    private ContactPerson partyContact = null;
 
	private enum EParty {	
		//KIDS:	CT_AddressType      UIDS: CT_Trader 	   KIDS: kommt beim Einlesen nicht vor 
		VATNumber,			        VATID,
		ETNAdress,    				ETNAddress,
		
		Address,					//same	   		       
									TIN,					//PatryTIN.TIN	        
									CustomsID,  			//PatryTIN.IsTINGermanApprovalNumber,	
									CustomerID,			    //PatryTIN.CustomerIdentifier,		
									Contact, ContactPerson,  
									IdentificationType, TinType,TINType,   //new for V20:	PatryTIN.IdentificationType
									BO,	                Branch,	   //new for V20:	PatryTIN.BO
   }	

	public Party() {
		super();  
	}
	public Party(String person) {
		super();  
		this.person = person;
	}	

	public Party(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public Party(XmlMsgScanner scanner, String person) {
  		super(scanner);
  		this.person = person;
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EParty) tag) {
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break; 
  														
				case Contact:
				case ContactPerson:
					partyContact = new ContactPerson(getScanner());
					partyContact.parse(tag.name());					
					break;
					
				default:
  					return;
  			}
  		} else {
  			switch((EParty) tag) { 
  			
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
  					setPartyTIN("CustomerID", value);
  					break;
  				case IdentificationType:
  				case TinType:
  				case TINType:
  					setPartyTIN("IdentificationType", value);
  					break;
  				case BO:
  				case Branch:
  					setPartyTIN("BO", value);
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
	
	public Address getAddress() {
		return address;
	}
    public void setAddress(Address argument) {
		this.address = argument;
	}
    
    private void setPartyTIN(String tag, String value) {
    	if (tag == null) {
    		return;
    	}
    	if (partyTIN == null) { 
    		partyTIN = new TIN(); 
    	}		
    	if (tag.equals("TIN")) {  partyTIN.setTIN(value); }
    	if (tag.equals("CustomerID")) { partyTIN.setCustomerIdentifier(value); }
    	if (tag.equals("CustomsID")) { partyTIN.setIsTINGermanApprovalNumber(value); }
    	if (tag.equals("IdentificationType")) {  partyTIN.setIdentificationType(value); }  //new for V20
    	if (tag.equals("BO")) {  partyTIN.setBO(value); }    //new for V20
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
    
    public String getPerson() {
    	return this.person;
    }
    
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.vatNumber) && Utils.isStringEmpty(this.etnAddress) && 
		        (this.partyTIN == null || (this.partyTIN != null && this.partyTIN.isEmpty())) && 
		        (this.address  == null || (this.address  != null && this.address.isEmpty())) && 
		        (this.partyContact == null || (this.partyContact != null && this.partyContact.isEmpty())));
	}    
}
