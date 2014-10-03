package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

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

public class AddressUnqualified extends KCXMessage {
		
	private PartyRows nameAndAddress;	
	
	private enum EAddressUnq {			
		NameAndAddress;			       		
   }	

	public AddressUnqualified() {
		super();  
	}
	public AddressUnqualified(String name, String s1, String s2, String s3, String s4) {
		super();  
		this.nameAndAddress = new PartyRows(name, s1, s2, s3, s4);
	}

	public AddressUnqualified(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAddressUnq) tag) {  			
  				case NameAndAddress:
  					nameAndAddress = new PartyRows(getScanner());  	
  					nameAndAddress.parse(tag.name());				
  					break;  				
				default:
  					break;
  			}
  		} else {
  			switch((EAddressUnq) tag) {   			  				
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EAddressUnq.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public PartyRows getPartyNameAndAddress() {
		return nameAndAddress;
	}    
	public void setPartyNameAndAddress(PartyRows value) {
		this.nameAndAddress = value;
	}
	
	public boolean isEmpty() {
		return nameAndAddress == null;		
	}
}

