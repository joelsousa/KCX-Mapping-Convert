package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: Itinerary = Routing
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Itinerary extends KCXMessage {
	
	private String country;

	public Itinerary() {
      	super();
	}

	public Itinerary(XMLEventReader parser) {
		super(parser);
	}      

	public Itinerary(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EItinerary {
		ROUT_COUNTRY,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EItinerary) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EItinerary) tag) {		
		case ROUT_COUNTRY:  		
			setCountry(value);
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
			return EItinerary.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
		
	public String getCountry() {
		return country;
	}
	public void setCountry(String value) {
		this.country = value;
	}
		
}
