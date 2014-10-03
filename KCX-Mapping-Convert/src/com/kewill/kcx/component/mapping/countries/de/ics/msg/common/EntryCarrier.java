package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;


import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EntryCarrier<br>
 * Function 	: EntryCarrier(UIDS)<br>
 * Description	: EntryCarrier tag in ICS.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class EntryCarrier extends KCXMessage  {
	
	private String tin;
	private String customerIdentifier;
	private String tinType;
	private Address address;
	
	private boolean debug   = false;
	
	private enum EEntryCarrier {
		//KIDS				//UIDS
		TIN,				//same
		CustomerIdentifier,	//no equivalent
		IdentificationType,	TINType,
		Address;			//same
	}
	
	public EntryCarrier() {
		super();
	}
	
	public EntryCarrier(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
  	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		
  		if (value == null) {
  			switch((EEntryCarrier) tag) {
  				case Address:
  					address = new Address(getScanner());
  					address.parse(tag.name());
  					
  				default:
  						return;
  			}
  		} else {
  			
  			switch((EEntryCarrier) tag) {
  				case TIN:
  					setTin(value);
  					break;
  				case TINType:
  				case IdentificationType:
  					setTinType(value);
  					break;
  				case CustomerIdentifier:
  					setCustomerIdentifier(value);
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
  			return EEntryCarrier.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  		}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getTinType() {
		return tinType;
	}

	public void setTinType(String tinType) {
		this.tinType = tinType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCustomerIdentifier() {
		return customerIdentifier;
	}

	public void setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}

}
