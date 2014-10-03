package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 29.10.2013<br>
 * Description	: Common class for MsgDeclnInput: DeclCust-Address
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class DeclnCust extends KCXMessage {

	private String type;
	private String name;
	private String companyName;
	private String street;
	private String city;
	private String zip;
	private String countryCode;
	private String stateCode;
	private String traderId;
	private String vatNbr;
	
	public DeclnCust() {
      	super();
	}

	public DeclnCust(XMLEventReader parser) {
		super(parser);
	}      

	public DeclnCust(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EDeclnCust {
		CUSTOMER_TYPE_CD,   // CZ=Consignor, CN=Consignee, CD=Declarant	
		VAT_NBR,
		CUSTOMER_NM, 		//Name
		CUSTOMER_COMPANY_NM, 		//Name		
		ADDR_STREET_1_NM,   //Street
		ADDR_CITY_NM, 		//City
		ADDR_ZIP_CD,		//PostalCode
		ADDR_COUNTRY_CD,	//CountryCode
		STATE_CD,			//CountrySubEntity		
		TRADER_ID,			//TIN
		ACCOUNT_NBR,
		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EDeclnCust) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EDeclnCust) tag) {
		case CUSTOMER_TYPE_CD:
			setCustomerType(value);
			break;
		case TRADER_ID:
			setTraderId(value);
			break;
		case VAT_NBR:
			setVatNbr(value);
			break;
		case CUSTOMER_NM:  
			setName(value);
			break;
		case CUSTOMER_COMPANY_NM:  
			setCompanyName(value);
			break;
		case ADDR_STREET_1_NM:
			setStreet(value);
			break;
		case ADDR_CITY_NM:
			setCity(value);
			break;
		case ADDR_ZIP_CD:
			setZip(value);
			break;
		case ADDR_COUNTRY_CD:
			setCountryCode(value);
			break;
		case STATE_CD:
			setStateCode(value);
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
			return EDeclnCust.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	public String getCustomerType() {
		return type;
	}
	public void setCustomerType(String code) {
		this.type = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String name) {
		this.companyName = name;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String stateCode) {
		this.traderId = stateCode;
	}
	
	public String getVatNbr() {
		return vatNbr;
	}
	public void setVatNbr(String stateCode) {
		this.vatNbr = stateCode;
	}
	
	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(name) &&
				Utils.isStringEmpty(street) &&
				Utils.isStringEmpty(city) &&
				Utils.isStringEmpty(zip) &&
				Utils.isStringEmpty(countryCode) &&
				Utils.isStringEmpty(stateCode)); 
	}	

}
