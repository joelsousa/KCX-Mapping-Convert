/*
 * Function    : AddressType.java
 * Titel       :
 * Date        : 09.06.2010
 * Author      : Pete T
 * Description : Contains the AddressType Data 
 * 			   : with all Fields used in UIDS and KIDS 
 * Parameters  : 
 */

package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: AddressType<br>
 * Erstellt		: 09.06.2010<br>
 * Beschreibung	: Contains the AddressType Data with all Fields used in UIDS and KIDS. 
 * 
 * @author Pete T
 * @version 1.0.00
 */
public class AddressType extends KCXMessage {

	private String vatNumber;
    private String etnAdress;
    private Address address;

    private boolean debug   = false;

	private enum EAddressType {
		//KIDS:							UIDS:
		VATNumber,						//No equivalent
		ETNAdress,						//No equivalent
		Address;
    }
	
	public AddressType() {
		super();
		address = new Address();
	}

    public AddressType(XmlMsgScanner scanner) {
  		super(scanner);
		address = new Address(scanner);
  	}
    
  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAddressType) tag) {
				case Address:
					Address wrkAddress = new Address(getScanner());
					wrkAddress.parse(tag.name());
					setAddress(wrkAddress);
					break;
  			default:
  					return;
  			}
  		} else {

  			switch ((EAddressType) tag) {
  				case VATNumber:
  					setVATNumber(value);
  					break;
  					
  				case ETNAdress:
  					setETNAdress(value);
  					break;
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EAddressType.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getVATNumber() {
		return vatNumber;
	}
	public void setVATNumber(String argument) {
		vatNumber = argument;
	}

	public String getETNAdress() {
		return etnAdress;
	}
	public void setETNAdress(String argument) {
		this.etnAdress = argument;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address argument) {
		address = argument;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.vatNumber) &&
				Utils.isStringEmpty(this.etnAdress) &&
				address == null);    		
	}
	
}
