package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;


/*
* Function    : Idemeatragi970 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : MEANS OF TRANSPORT AT BORDER 
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
 * Modul		: Idemeatragi970.<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: MEANS OF TRANSPORT AT BORDER
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Idemeatragi970 extends KCXMessage {
	
	private String transportationCountry;
	private String transportationNumber;
	private String lng;
	
	public Idemeatragi970() {
      	super();
	}

	public Idemeatragi970(XMLEventReader parser) {
		super(parser);
	}      

	public Idemeatragi970(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Idemeatragi970Tags {
		NatlDEMEATRAGI973,
		IdeMeaTraGIMEATRA971,
		IdeMeaTraGIMEATRA972LNG;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Idemeatragi970Tags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((Idemeatragi970Tags) tag) {
		case NatlDEMEATRAGI973:  setTransportationCountry(value);
			break;

		case IdeMeaTraGIMEATRA971:  setTransportationCountry(value);
			break;

		case IdeMeaTraGIMEATRA972LNG:  setTransportationCountry(value);
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
			return Idemeatragi970Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getTransportationCountry() {
		return transportationCountry;
	
	}

	public void setTransportationCountry(String transportationCountry) {
		this.transportationCountry = Utils.checkNull(transportationCountry);
	}

	public String getTransportationNumber() {
		return transportationNumber;
	
	}

	public void setTransportationNumber(String transportationNumber) {
		this.transportationNumber = Utils.checkNull(transportationNumber);
	}

	public String getLng() {
		return lng;
	
	}

	public void setLng(String lng) {
		this.lng = Utils.checkNull(lng);
	}

}
