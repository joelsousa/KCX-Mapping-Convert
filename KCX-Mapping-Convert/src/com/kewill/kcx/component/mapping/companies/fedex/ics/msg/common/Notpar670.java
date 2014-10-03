package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

/*
 * Function    : Notpar670 
 * Titel       :
 * Date        : 25.11.2010
 * Author      : Kewill CSF / krzoska
 * Description : Address of NotifyParty
 * Parameters  :

 * Changes
 * ------------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Notpar670<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: -.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Notpar670 extends KCXMessage {
	private boolean debug   = false;
	
	private String 		name;
	private String 		street;
	private String 		houseNumber;
	private String 		postalCode;
	private String 		city;
	private String 		country;
	private String 		lng;
	private String 		tin;

	
	private enum ENotpar670Tags {
		// Fedex, 					
		NamNOTPAR672,
		StrAndNmNOTPAR673,
		PosCodNOTPAR676,
		CitNOTPAR674,
		CouCodNOTPAR675,
		NOTPAR670LNG,
		TINNOTPAR671;
	}
	
	public Notpar670() {
      	super();
	}

	public Notpar670(XMLEventReader parser) {
		super(parser);
	}      

	public Notpar670(XmlMsgScanner scanner) {
		super(scanner);
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ENotpar670Tags) tag) {
		default:
				return;
		}
	  } else {
		switch ((ENotpar670Tags) tag) {
		case NamNOTPAR672:		setName(name);
			break;
		case StrAndNmNOTPAR673: setStreet(value);
			break;
		case PosCodNOTPAR676: 	setPostalCode(value);
			break;
		case CitNOTPAR674: 		setCity(value);
			break;
		case CouCodNOTPAR675: 	setCountry(value);
			break;
		case NOTPAR670LNG: 		setLng(value);
			break;
		case TINNOTPAR671: 		setTin(value);
			break;
		default:
			return;
		} 
	  }
	}
	
	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	public Enum translate(String token) {
		try {
			return ENotpar670Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getName() {
		return name;
	
	}

	public void setName(String name) {
		this.name = Utils.checkNull(name);
	}

	public String getStreet() {
		return street;
	
	}

	public void setStreet(String street) {
		this.street = Utils.checkNull(street);
	}

	public String getHouseNumber() {
		return houseNumber;
	
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = Utils.checkNull(houseNumber);
	}

	public String getPostalCode() {
		return postalCode;
	
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = Utils.checkNull(postalCode);
	}

	public String getCity() {
		return city;
	
	}

	public void setCity(String city) {
		this.city = Utils.checkNull(city);
	}

	public String getCountry() {
		return country;
	
	}

	public void setCountry(String country) {
		this.country = Utils.checkNull(country);
	}

	public String getLng() {
		return lng;
	
	}

	public void setLng(String lng) {
		this.lng = Utils.checkNull(lng);
	}

	public String getTin() {
		return tin;
	
	}

	public void setTin(String tin) {
		this.tin = Utils.checkNull(tin);
	}

}
