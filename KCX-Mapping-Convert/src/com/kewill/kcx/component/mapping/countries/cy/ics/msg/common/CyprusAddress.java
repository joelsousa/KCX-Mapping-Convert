package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CyprusAddress<br>
 * Created		: 22.06.2011<br>
 * Description 	: Contains Subtree of all Addresses in Cyprus.
 *                 
 * @author Frederick T.
 * @version 1.0.00
 */

public class CyprusAddress extends KCXMessage {
	private boolean debug   = false;
	private XMLEventReader 	parser	= null;
	
	private String 		name;
	private String 		street;
	private String 		houseNumber;
	private String 		postalCode;
	private String 		city;
	private String 		country;
	private String 		tin;
	
	private enum ECyprusAddressTags {
		//Representative Address
		NamTRE1,
		StrAndNumTRE1,
		PosCodTRE1,
		CitTRE1,
		CouCodTRE1,
		TINTRE1,
		
		//PersonLodgingSuma Address
		NamPLD1,
		StrAndNumPLD1,
		PosCodPLD1,
		CitPLD1,
		CouCodPLD1,
		TINPLD1,
		
		//Carrier ADdress
		NamTRACARENT604,
		StrNumTRACARENT607,
		PstCodTRACARENT606,
		CtyTRACARENT603,
		CouCodTRACARENT605,
		TINTRACARENT602,
		
		// Submitter Address
		NamTRAREQDIV457,
		StrAndNumTRAREQDIV458,
		CouTRAREQDIV459,
		PosCodTRAREQDIV460,
		CitTRAREQDIV46, CitTRAREQDIV461,
		TINTRAREQDIV463,
		
		//Consignor Address
		NamCO17,
		StrAndNmCO122, StrAndNumCO122,
		PosCodCO123,
		CitCO124,
		CouCo125,
		TINCO159,
		
		//Consignee Address
		NamCE17,
		StrAndNmCE122, StrAndNumCE122,
		PosCodCE123,
		CitCE124,
		CouCE125,
		TINCE159,
		
		//Notify Party Address
		NamNOTPAR672,
		StrAndNmNOTPAR673, StrNumNOTPAR673,
		PosCodNOTPAR676,
		CitNOTPAR674,
		CouCodNOTPAR675,
		TINNOTPAR671,
		
		//GoodsItem Consignor Address
		NamCO27,
		StrAndNmCO222, StrAndNumCO222,
		PosCodCO223,
		CitCO224,
		CouCo225,
		TINCO259,

		//GoodsItem Consignee Address
		NamCE27,
		StrAndNmCE222, StrAndNumCE222,
		PosCodCE223,
		CitCE224,
		CouCE225,
		TINCE259,
		
		//GoodsItem Notify Party Address
		NamPRTNOT642,
		StrAndNmPRTNOT643, StrNumPRTNOT643,
		PstCodPRTNOT646,
		CitPRTNOT644,
		CouCodPRTNOT645,
		TINPRTNOT641;
	}
	
	public CyprusAddress() {
		super();
	}
	
	public CyprusAddress(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public CyprusAddress(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ECyprusAddressTags) tag) {
		default:
				return;
		}
	  } else {
		switch ((ECyprusAddressTags) tag) {
		case NamPRTNOT642:
		case NamCE27:
		case NamCO27:
		case NamNOTPAR672:
		case NamCE17:
		case NamCO17:
		case NamTRAREQDIV457:
		case NamTRACARENT604:
		case NamPLD1:
		case NamTRE1:
				setName(value);
			break;
			
		case StrAndNmPRTNOT643: case StrNumPRTNOT643:
		case StrAndNmCE222: 	case StrAndNumCE222:
		case StrAndNmCO222: 	case StrAndNumCO222:
		case StrAndNmNOTPAR673: case StrNumNOTPAR673:
		case StrAndNmCE122: 	case StrAndNumCE122:
		case StrAndNmCO122:		case StrAndNumCO122:
		case StrAndNumTRAREQDIV458:
		case StrNumTRACARENT607:
		case StrAndNumPLD1:
		case StrAndNumTRE1:
				setStreet(value);
			break;
			
		case PstCodPRTNOT646:
		case PosCodCE223:
		case PosCodCO223:
		case PosCodNOTPAR676:
		case PosCodCE123:
		case PosCodCO123:
		case PosCodTRAREQDIV460:
		case PstCodTRACARENT606:
		case PosCodPLD1:
		case PosCodTRE1:
				setPostalCode(value);
			break;
		
		case CitPRTNOT644:
		case CitCE224:
		case CitCO224:
		case CitNOTPAR674:
		case CitCE124:
		case CitCO124:
		case CitTRAREQDIV46: case CitTRAREQDIV461:
		case CtyTRACARENT603:
		case CitPLD1:
		case CitTRE1:
				setCity(value);
			break;
			
		case CouCodPRTNOT645:
		case CouCE225:
		case CouCo225:
		case CouCodNOTPAR675:
		case CouCE125:
		case CouCo125:
		case CouTRAREQDIV459:
		case CouCodTRACARENT605:
		case CouCodPLD1:
		case CouCodTRE1:
				setCountry(value);
			break;
			
		case TINPRTNOT641:
		case TINCE259:
		case TINCO259:
		case TINNOTPAR671:
		case TINCE159:
		case TINCO159:
		case TINTRAREQDIV463:
		case TINTRACARENT602:
		case TINPLD1:
		case TINTRE1:
				setTin(value);
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
			return ECyprusAddressTags.valueOf(token);
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

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = Utils.checkNull(tin);
	}
	
}
