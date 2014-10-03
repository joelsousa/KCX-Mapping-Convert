package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: PartyType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PartyType extends KCXMessage {

	private String primaryId;
	private String additionalId;
	private String name;
	private String accountId;
	private String role;
	private String roleCode;
	private String cargoAgentId;
	private AddressDetails address;
	private Location specifiedCargoAgentLocation;
	private ArrayList<ContactDetails> definedTradeContactList;
	
	private enum EPartyType {
		//CMP
		PrimaryId,	PrimaryID,
		AdditionalId, AdditionalID,
		Name,
		AccountID,
		Role,
		RoleCode,
		CargoAgentID,
		PostalStructuredAddress, FreightForwarderAddress,
		SpecifiedCargoAgentLocation,
		DefinedTradeContact;
	}
	
	public PartyType() {
		super();  
	}

    public PartyType(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPartyType) tag) {
  			
  			case PostalStructuredAddress:
  			case FreightForwarderAddress:
  				address = new AddressDetails(getScanner());
  				address.parse(tag.name()); 
  				break;
  				
  			case DefinedTradeContact:
  				ContactDetails contact = new ContactDetails(getScanner());
  				contact.parse(tag.name()); 
  				addContactDetailsList(contact);
  				break;
  				
  			case SpecifiedCargoAgentLocation:  				
  				specifiedCargoAgentLocation = new Location(getScanner());
  				specifiedCargoAgentLocation.parse(tag.name()); 
  				break;
  				
  			default:
  					return;
  			}
  		} else {
  			switch ((EPartyType) tag) {
  			
  				case PrimaryId:  
  				case PrimaryID:
  					setPrimaryId(value); 
  					break;
  					
  				case AdditionalId:
  				case AdditionalID:
  					setAdditionalId(value); 
  					break;
  				
  				case Name:
  					setName(value);  
  					break;
  					
  				case AccountID:
  					setAccountId(value);  
  					break;
  					
  				case Role:
  					setRole(value);  
  					break;
  					
  				case RoleCode:
  					setRoleCode(value);  
  					break;
  					
  				case CargoAgentID:
  					setCargoAgentId(value);  
  					break;
  					
   				default:
  					return;  					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EPartyType.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(String value) {
		this.primaryId = Utils.checkNull(value);
	}

	public String getName() {
		return name;
	}
	public void setName(String value) {
		this.name = Utils.checkNull(value);
	}

	public String getAdditionalId() {
		return additionalId;
	}

	public void setAdditionalId(String additionalId) {
		this.additionalId = additionalId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getCargoAgentId() {
		return cargoAgentId;
	}

	public void setCargoAgentId(String cargoAgentId) {
		this.cargoAgentId = cargoAgentId;
	}

	public AddressDetails getAddress() {
		return address;
	}
	public void setAddress(AddressDetails address) {
		this.address = address;
	}

	public Location getSpecifiedCargoAgentLocation() {
		return specifiedCargoAgentLocation;
	}
	public void setSpecifiedCargoAgentLocation(Location location) {
		this.specifiedCargoAgentLocation = location;
	}

	public ArrayList<ContactDetails> getDefinedTradeContactList() {
		return definedTradeContactList;
	}
	public void setDefinedTradeContactList(
			ArrayList<ContactDetails> definedTradeContactList) {
		this.definedTradeContactList = definedTradeContactList;
	}
	public void addContactDetailsList(ContactDetails contact) {
		if (definedTradeContactList == null) {
			definedTradeContactList = new ArrayList<ContactDetails>();
		}
		definedTradeContactList.add(contact);
	}
	
	public boolean isEmpty() {
		return 	Utils.isStringEmpty(primaryId) && Utils.isStringEmpty(additionalId) &&
			Utils.isStringEmpty(name) && Utils.isStringEmpty(accountId) &&
			Utils.isStringEmpty(role) && Utils.isStringEmpty(roleCode) &&
			Utils.isStringEmpty(cargoAgentId) &&
			cargoAgentId == null && specifiedCargoAgentLocation == null && 
			definedTradeContactList == null;							
	}
	
}
