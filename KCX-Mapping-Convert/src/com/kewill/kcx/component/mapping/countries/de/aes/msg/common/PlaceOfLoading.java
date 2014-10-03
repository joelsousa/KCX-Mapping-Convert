/*
 * Function    : PlaceOfLoading (KIDS) == PlaceOfLoading (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the PlaceOFLoading Data
 * 			   : with all Fields used in UIDS and KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        :
 * Label       : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: PlaceOfLoading<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the PlaceOFLoading Data with all Fields used in UIDS and KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class PlaceOfLoading extends KCXMessage {

    private String code   	         	 = "";
    private String street	             = "";
    private String postalCode            = "";
    private String city                  = "";
    private String agreedLocationOfGoods = "";
    //private Address address;

    private boolean debug   = false;
    
    public PlaceOfLoading() {
    	super();
    }

    public PlaceOfLoading(XmlMsgScanner scanner) {
    	super(scanner);
   	}

   	public boolean isDebug() {
   		return debug;
   	}

 	private enum EPlaceOfLoading {
 	// Kids-TagNames, 			UIDS-TagNames
 		Code,					//same 
 		Street,					//Address.StreetAndNumber
 		PostalCode,				//Address.PostalCode
 		City,					//Address.City
 		                        Address,
 		AgreedLocationOfGoods, 	Addition;
    }

   	public void startElement(Enum tag, String value, Attributes attr) {
   		if (value == null) {
   			switch ((EPlaceOfLoading) tag) {
   			
   			case Address:   				
   				Address address = new Address(getScanner());  	
				address.parse(tag.name());
				this.street = address.getStreet();
				this.city = address.getCity();
				this.postalCode = address.getPostalCode();
   				break;
   				
   			default:
   					return;
   			}
   		} else {

   			switch ((EPlaceOfLoading) tag) {

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
   				case Addition:
   					setAgreedLocationOfGoods(value);
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

   	public void setDebug(boolean debug) {
   		this.debug = debug;
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
	
	public boolean isEmpty() {  //EI20120917
		return (Utils.isStringEmpty(this.code) && Utils.isStringEmpty(this.street) && 
		        Utils.isStringEmpty(this.postalCode) && Utils.isStringEmpty(this.city) &&                
		        Utils.isStringEmpty(this.agreedLocationOfGoods));
	}	
}
