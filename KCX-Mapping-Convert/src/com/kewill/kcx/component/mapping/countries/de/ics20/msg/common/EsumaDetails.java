package com.kewill.kcx.component.mapping.countries.de.ics20.msg.common;


import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.25<br>
 * Description	: EsumaDetails.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class EsumaDetails extends KCXMessage {

	private String itemNumberEsuma;
	private String officeOfFinalDestination;     //new V20
	private String countryOfFinalDestination;    //new V20
	
	private boolean debug   = false;
	
	private enum EEsumaDetails {
		//KIDS						//UIDS
		ItemNumberEsuma	,			MRNItemNumber,
		OfficeOfFinalDestination,   //same                          
		CountryOfFinalDestination,  //same
	}
	
	public EsumaDetails() {
		super();
	}
	
	public EsumaDetails(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEsumaDetails) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch((EEsumaDetails) tag) {
  				case ItemNumberEsuma:
  				case MRNItemNumber:
  						if (Utils.isStringEmpty(getItemNumberEsuma())) {
  							setItemNumberEsuma(value);
  						}
  					break;
  				case OfficeOfFinalDestination:
  					setOfficeOfFinalDestination(value);
  					break;
  				case CountryOfFinalDestination:
  					setCountryOfFinalDestination(value);
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
  			return EEsumaDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumberEsuma() {
		return itemNumberEsuma;
	}

	public void setItemNumberEsuma(String value) {
		this.itemNumberEsuma = value;
	}
  		
	public String getOfficeOfFinalDestination() {
		return officeOfFinalDestination;
	}

	public void setOfficeOfFinalDestination(String value) {
		this.officeOfFinalDestination = value;
	}
	
	public String getCountryOfFinalDestination() {
		return countryOfFinalDestination;
	}

	public void setCountryOfFinalDestination(String value) {
		this.countryOfFinalDestination = value;
	}
}
