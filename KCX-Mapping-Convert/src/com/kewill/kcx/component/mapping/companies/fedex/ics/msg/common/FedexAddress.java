package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

/*
 * Function    : Traconco1 
 * Titel       :
 * Date        : 25.11.2010
 * Author      : Kewill CSF / krzoska
 * Description : Addresses 
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
 * Modul		: FedexAddress<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: -.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class FedexAddress extends KCXMessage {
	private boolean debug   = false;
	private XMLEventReader 	parser	= null;
	
	private String 		name;
	private String 		street;
	private String 		houseNumber;
	private String 		postalCode;
	private String 		city;
	private String 		country;
	private String 		lng;
	private String 		tin;

	
	private enum EFedexAddressTags {
		// Consignor Address,
		NamCO17,
		StrAndNumCO122,
		PosCodCO123,
		CitCO124,
		CouCO125,
		TRACONCO1LNG,
		TINCO159,

		// Consignee Address, 					
		NamCE17,
		StrAndNumCE122,
		PosCodCE123,
		CitCE124,
		CouCE125,
		TRACONCE1LNG,
		TINCE159,
		
		//Consignor Address in Items
		NamCO27,
		StrAndNmCO222,
		PosCodCO223,
		CitCO224,
		CouCo225,
		TRACONCO2LNG,
		TINCO259,

		//Consignee Address in Items
		NamCE27,
		StrAndNmCE222,
		PosCodCE223,
		CitCE224,
		CouCE225,
		TRACONCE2LNG,
		TINCE259,
		
		// LODGING SUMMARY DECLARATION PERSON
		NamPLD1,
		StrAndNumPLD1,
		PosCodPLD1,
		CitPLD1,
		CouCodPLD1,
		PERLODSUMDECLNG,
		TINPLD1,

		//  REPRESENTATIVE TRADER
		NamTRE1,
		StrAndNumTRE1,
		PosCodTRE1,
		CitTRE1,
		CouCodTRE1,
		TRAREPLNG,
		TINTRE1,
		
		//  NOTIFY PARTY
		NamNOTPAR642, NamNOTPAR672,
		StrAndNmNOTPAR643, StrNumNOTPAR673,
		PosCodNOTPAR646, PosCodNOTPAR676,
		CitNOTPAR644, CitNOTPAR674,
		CouCodNOTPAR645, CouCodNOTPAR675,
		NOTPAR640LNG, NOTPAR670LNG,
		TINNOTPAR641, TINNOTPAR671,

		//  (ENTRY CARRIER) TRADER
		NamTRACARENT604,
		StrNumTRACARENT607,
		PstCodTRACARENT606,
		CtyTRACARENT603,
		CouCodTRACARENT605,
		TRACARENT601LNG,
		TINTRACARENT602,
		
		//Submitter
		NamTRAREQDIV457,
		StrAndNumTRAREQDIV458,
		CouTRAREQDIV459,
		PosCodTRAREQDIV460,
		CitTRAREQDIV46,
		TRAREQDIVLNG,
		TINTRAREQDIV463;

		
	}
	
	public FedexAddress() {
      	super();
	}

	public FedexAddress(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}      

	public FedexAddress(XmlMsgScanner scanner) {
		super(scanner);
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EFedexAddressTags) tag) {
		default:
				return;
		}
	  } else {
		switch ((EFedexAddressTags) tag) {
		case NamTRAREQDIV457:
		case NamTRACARENT604:
		case NamNOTPAR642:
		case NamNOTPAR672:
		case NamTRE1:
		case NamPLD1:
		case NamCO27:
		case NamCE27:
		case NamCO17:
		case NamCE17:		setName(value);
			break;

		case StrAndNumTRAREQDIV458:
		case StrNumTRACARENT607:
		case StrAndNmNOTPAR643:
		case StrNumNOTPAR673:
		case StrAndNumTRE1:
		case StrAndNumPLD1:
		case StrAndNmCO222:
		case StrAndNmCE222:		
		case StrAndNumCO122:
		case StrAndNumCE122: setStreet(value);
			break;

		case PosCodTRAREQDIV460:
		case PstCodTRACARENT606:
		case PosCodNOTPAR646:
		case PosCodNOTPAR676:
		case PosCodTRE1:
		case PosCodPLD1:
		case PosCodCO223:
		case PosCodCE223:
		case PosCodCO123:
		case PosCodCE123: 	setPostalCode(value);
			break;

		case CitTRAREQDIV46:
		case CtyTRACARENT603:
		case CitNOTPAR644:
		case CitNOTPAR674:
		case CitTRE1:
		case CitPLD1:
		case CitCO224:
		case CitCE224:	
		case CitCO124:
		case CitCE124: 		setCity(value);
			break;

		case CouTRAREQDIV459:
		case CouCodTRACARENT605:
		case CouCodNOTPAR645:
		case CouCodNOTPAR675:
		case CouCodTRE1:
		case CouCodPLD1:
		case CouCo225:
		case CouCE225:	
		case CouCO125:
		case CouCE125: 		setCountry(value);
			break;

		case TRAREQDIVLNG:
		case TRACARENT601LNG:
		case NOTPAR640LNG:
		case NOTPAR670LNG:
		case TRAREPLNG:
		case PERLODSUMDECLNG:
		case TRACONCO2LNG:	
		case TRACONCE2LNG:	
		case TRACONCO1LNG:
		case TRACONCE1LNG: 	setLng(value);
			break;
			
		case TINTRAREQDIV463:
		case TINTRACARENT602:
		case TINNOTPAR641:
		case TINNOTPAR671:
		case TINTRE1:
		case TINPLD1:
		case TINCO259:
		case TINCE259:
		case TINCO159:
		case TINCE159: 		setTin(value);
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
			return EFedexAddressTags.valueOf(token);
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
