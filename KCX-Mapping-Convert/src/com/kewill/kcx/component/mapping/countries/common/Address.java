package com.kewill.kcx.component.mapping.countries.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: KCX<br>
 * Created		: 05.09.2008<br>
 * Description	: Contains the Address Data with all Fields used in UIDS and KIDS. 
 *   			: for all Modules.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Address extends KCXMessage {
	 private String   typ;
	 private String   name;
	 private String   street;
	 private String   houseNumber;
	 private String   city;
	 private String   postalCode;
	 private String   country;
	 private String   district;	//only UIDS
	 private String   poBox;	//only UIDS
	 private String   language;	 //EI20100615 temporary both: CountryCodeISO und Language
	 
	 private String   countrySubEntity;

	 private XMLEventReader 	parser	= null;
	 private boolean debug   = false;
	 
	 private enum EAddressTags {	
		// Kids-TagNames, 			UIDS-TagNames;
			Name,					//same
			Street,					StreetAndNumber,								       
			StreetNumber, HouseNumber, //--		 
			City,					//same
			PostalCode,				//same
			Country,				CountryCodeISO,						
									Language,
			PoBox,					POBox,
									District,
			CountrySubEntity;       //same     
		 	   
		}	 	
	 
	public Address() {
		super();  
	}
	
	 public Address(XMLEventReader parser) {
			super(parser);
			this.parser = parser;			
	 }
	 
	 public Address(XMLEventReader parser, String typ) {
			super(parser);
			this.parser = parser;
			this.typ    = typ;
	 }
	 
	 public Address(XmlMsgScanner scanner) {
	  		super(scanner);
	 }

	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
			//--------------------------
			//  structures
			//--------------------------
				// No complex structure in the Address Tags 
				switch ((EAddressTags) tag) {
				default:
						return;				
				}
			} else {
				//--------------------------
				// values
				//--------------------------
				switch ((EAddressTags) tag) {				
					case Name:
						setName(value);
						break;								
					case Street:
					case StreetAndNumber:
						setStreet(value);
						break;
					case HouseNumber:
					case StreetNumber:
						setHouseNumber(value);
						break;
					case City:
						setCity(value);
						break;							
					case PostalCode:
						setPostalCode(value);
						break;					
					case Country:
					case CountryCodeISO:
						setCountry(value);
						break;
						
					case District:
						setDistrict(value);
						break;
					case POBox:
					case PoBox:
						setPOBox(value);
						break;					
						
					case CountrySubEntity:
						setCountrySubEntity(value);
						break;
					case Language:
						setLanguage(value);
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
				return EAddressTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	   public boolean isDebug() {
			return debug;
		}
		public void setDebug(boolean argument) {
			this.debug = argument;
		}

		public String getName() {
			return name;
		}
		public void setName(String argument) {
			this.name = argument;
		}
		
		public String getStreet() {
			return street;
		}
		public void setStreet(String argument) {
			this.street = argument;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String argument) {
			this.city = argument;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String argument) {
			this.postalCode = argument;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String argument) {
			this.country = argument;
		}

		public String getTyp() {
			return typ;
		}
		public void setTyp(String argument) {
			this.typ = argument;
		}
		
		public void setDistrict(String argument) {
			this.district = argument;
		}
		public String getDistrict() {
			return district;
		}		
		
		public void setPOBox(String argument) {
			this.poBox = argument;
		}
		public String getPOBox() {
			return poBox;
		}

		public String getCountrySubEntity() {
			return countrySubEntity;		
		}
		public void setCountrySubEntity(String argument) {
			countrySubEntity = argument;		
		}
		
		public String getLanguage() {
			return this.language;
		}

		public void setLanguage(String argument) {
			this.language = argument;
		}	
		
		public void setHouseNumber(String value) {
//			try {
//				int nr = Integer.parseInt(value.trim());
//                Integer.parseInt(value.trim());
//				houseNumber = value;
//			} catch (NumberFormatException e) {
//				houseNumber = "";
//			}
			this.houseNumber = value; //EB11112010 changed as instructed by Christine Kron
		}
		
		public String getHouseNumber() {
			return houseNumber;
		}
		
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.city) && Utils.isStringEmpty(this.country) && 
			        Utils.isStringEmpty(this.countrySubEntity) && Utils.isStringEmpty(this.district) && 
			        Utils.isStringEmpty(this.houseNumber) && Utils.isStringEmpty(this.language) && 
			        Utils.isStringEmpty(this.name) && Utils.isStringEmpty(this.poBox) && 
			        Utils.isStringEmpty(this.postalCode) && Utils.isStringEmpty(this.street));  
		}					
}
