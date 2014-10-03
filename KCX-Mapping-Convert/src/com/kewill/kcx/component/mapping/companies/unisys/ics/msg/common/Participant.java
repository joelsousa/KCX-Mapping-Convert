package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Participant<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Participant extends KCXMessage {
	
	private String type 	= "";
	private PartReference reference;
	private String name 	= "";
	private String addr 	= "";
	private String city 	= "";
	private String state 	= "";
	private String country 	= "";
	private String postcode = "";
	private String contact = "";	
	private String phone = "";	
			
	 private enum EParticipant {
	 // Unisys-TagNames, 			KIDS-TagNames
		TYPE,				  	
		REFERENCE,	    
		NAME,
		ADDR,
		CITY,
		STATE,
		COUNTRY,
		POSTCODE,
		CONTACT,
		PHONE;
	 }

	 public Participant() {
	      	super();	      
	 }    
   
	 public Participant(XmlMsgScanner scanner) {
		super(scanner);
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EParticipant) tag) {	
			case REFERENCE:
				reference = new PartReference(getScanner());  	
				reference.parse(tag.name());										
				break;
			default:
					return;
			}
		} else {

			switch ((EParticipant) tag) {
				case TYPE:
					setType(value);
					break; 				
				case NAME:
					setName(value);
					break;
				case ADDR:
					setAddr(value);
					break;
				case CITY:
					setCity(value);
					break;
				case STATE:
					setState(value);
					break;
				case COUNTRY:
					setCountry(value);
					break;
				case POSTCODE:
					setPostcode(value);
					break;
				case CONTACT:
					setContact(value);
					break;
				case PHONE:
					setPhone(value);
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
			return EParticipant.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getType() {
		return type;	
	}
	public void setType(String argument) {
		this.type = Utils.checkNull(argument);
	}

	public PartReference getReference() {
		return reference;	
	}
	public void setReference(PartReference argument) {
		this.reference = argument;
	}
	
	public String getName() {
		return name;	
	}
	public void setName(String argument) {
		this.name = Utils.checkNull(argument);
	}
	
	public String getAddr() {
		return addr;	
	}
	public void setAddr(String argument) {
		this.addr = Utils.checkNull(argument);
	}
	
	public String getCity() {
		return city;	
	}
	public void setCity(String argument) {
		this.city = Utils.checkNull(argument);
	}
	
	public String getState() {
		return state;	
	}
	public void setState(String argument) {
		this.state = Utils.checkNull(argument);
	}
	
	public String getCountry() {
		return country;	
	}
	public void setCountry(String argument) {
		this.country = Utils.checkNull(argument);
	}
	
	public String getPostcode() {
		return postcode;	
	}
	public void setPostcode(String argument) {
		this.postcode = Utils.checkNull(argument);
	}
	
	public String getContact() {
		return contact;	
	}
	public void setContact(String argument) {
		this.contact = Utils.checkNull(argument);
	}
	
	public String getPhone() {
		return phone;	
	}
	public void setPhone(String argument) {
		this.phone = Utils.checkNull(argument);
	}
	
}
