package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Iti<br>
 * Date Created	: 23.06.2011<br>
 * Description	: Itinerary
 * 
 * @author Frederick T.
 * @version 1.0.00
 */
public class Iti extends KCXMessage {

	private String countryOfRouting;
	
	public Iti() {
      	super();
	}

	public Iti(XMLEventReader parser) {
		super(parser);
	}      

	public Iti(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EItiTags {
		CouOfRouCodITI1;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EItiTags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EItiTags) tag) {
		case CouOfRouCodITI1:  setCountryOfRouting(value);
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
			return EItiTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCountryOfRouting() {
		return countryOfRouting;
	
	}

	public void setCountryOfRouting(String countryOfRouting) {
		this.countryOfRouting = Utils.checkNull(countryOfRouting);
	}

}