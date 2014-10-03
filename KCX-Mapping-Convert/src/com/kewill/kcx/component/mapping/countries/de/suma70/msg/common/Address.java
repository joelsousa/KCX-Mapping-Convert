package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 28.06.2013<br>
 * Description	: Contains the Adress Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class Address extends KCXMessage {

    private String name;
    private String streetAndNumber;
    private String postalCode;
    private String city;
    private String countryCodeISO;
    private String district;
    private String poBox;
  
	private enum EDeferment {
		//KIDS							//UIDS
		Name,
		StreetAndNumber,	
		PostalCode,	
		City,	
		CountryCodeISO,
		District,
		PoBox;
    }

	public Address() {
		super();  
	}

    public Address(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDeferment) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EDeferment) tag) {
  				case Name:  				
  					setName(value);
  					break;
  				case StreetAndNumber:
  					setStreetAndNumber(value);
  					break;
  				case PostalCode:
  					setPostalCode(value);
  					break;
  				case City:
  					setCity(value);
  					break;
  				case CountryCodeISO:
  					setCountryCodeISO(value);
  					break;
  				case District:
  					setDistrict(value);
  					break;
  				case PoBox:
  					setPoBox(value);
  					break;  					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EDeferment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getName() {
		return name;
	}
	public void setName(String argument) {
		name = argument;
	}
	
	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
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

	public String getCountryCodeISO() {
		return countryCodeISO;
	}

	public void setCountryCodeISO(String countryCodeISO) {
		this.countryCodeISO = countryCodeISO;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.streetAndNumber) && Utils.isStringEmpty(this.name) && 
				Utils.isStringEmpty(this.postalCode) && Utils.isStringEmpty(this.city) &&
				Utils.isStringEmpty(this.countryCodeISO) && 
				Utils.isStringEmpty(postalCode) && Utils.isStringEmpty(poBox)); 
	}
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = Utils.checkNull(district);
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = Utils.checkNull(poBox);
	}
  
}
