package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: PlaceOfLoading
 * Created		: 01.09.2010
 * Description	: contains PlaceOfLoading Data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */

public class PlaceOfLoading extends KCXMessage {

	    private String 	code;   	         	 
	    private String 	street;
	    private String 	postalCode;
	    private String 	city;
	    private String 	agreedLocationOfGoods;
	    private String	agreedLocationOfGoodsCode;
	    private String	authorisedLocationOfGoodsCode;
		
	    public PlaceOfLoading() {
	    	super();
	    }

	    public PlaceOfLoading(XmlMsgScanner scanner) {
	    	super(scanner);
	   	}

	 	private enum EPlaceOfLoading {
	 	// Kids-TagNames, 			UIDS-TagNames
	 	Code,						PlaceOfLoading, 
	 	Street,						
	 	PostalCode,					
	 	City,						
	 	AgreedLocationOfGoods, 		//same
	 	AgreedLocationOfGoodsCode,	//same
	 	AuthorisedLocationOfGoodsCode, AuthorizedLocationOfGoods;								
	    }

	   	public void startElement(Enum tag, String value, Attributes attr) {
	   		if (value == null) {
	   			switch ((EPlaceOfLoading) tag) {
	   			default:
	   					return;
	   			}
	   		} else {
	   			switch ((EPlaceOfLoading) tag) {
	   			
	   				case PlaceOfLoading:
	   				case Code:
	   					setCode(value);
	   					break;

	   				case Street:
	   					setStreet(value);
	   					break;

	   				case PostalCode:
	   					setPostalCode(value);
	   					break;

	   				case City:
	   					setCity(value);
	   					break;
	   				
	   				case AgreedLocationOfGoods:
	   					setAgreedLocationOfGoods(value);
	   					break;
	   				case AgreedLocationOfGoodsCode:
	   					setAgreedLocationOfGoodsCode(value);
	   					break;
	   				case AuthorisedLocationOfGoodsCode:
	   				case AuthorizedLocationOfGoods:
	   					setAuthorisedLocationOfGoodsCode(value);
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
	   			return EPlaceOfLoading.valueOf(token);
	   		} catch (IllegalArgumentException e) {
	   			return null;
	   		}
	   	}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getAgreedLocationOfGoods() {
			return agreedLocationOfGoods;
		}

		public void setAgreedLocationOfGoods(String agreedLocationOfGoods) {
			this.agreedLocationOfGoods = agreedLocationOfGoods;
		}

		public String getAgreedLocationOfGoodsCode() {
			return agreedLocationOfGoodsCode;
		}

		public void setAgreedLocationOfGoodsCode(String agreedLocationOfGoodsCode) {
			this.agreedLocationOfGoodsCode = agreedLocationOfGoodsCode;
		}

		public String getAuthorisedLocationOfGoodsCode() {
			return authorisedLocationOfGoodsCode;
		}

		public void setAuthorisedLocationOfGoodsCode(
				String authorisedLocationOfGoodsCode) {
			this.authorisedLocationOfGoodsCode = authorisedLocationOfGoodsCode;
		}	

		public boolean isEmpty() {    //EI20110524			
			return (Utils.isStringEmpty(this.code) && 
					Utils.isStringEmpty(this.street) && 
					Utils.isStringEmpty(this.postalCode) && 
					Utils.isStringEmpty(this.city) && 
					Utils.isStringEmpty(this.agreedLocationOfGoods) && 
					Utils.isStringEmpty(this.agreedLocationOfGoodsCode) && 
					Utils.isStringEmpty(this.authorisedLocationOfGoodsCode)); 					
		}		
		
	}
