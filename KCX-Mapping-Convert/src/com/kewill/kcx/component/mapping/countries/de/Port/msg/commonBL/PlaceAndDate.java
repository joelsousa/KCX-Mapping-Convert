package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PlaceAndDate extends KCXMessage {
		
	private Place place;
	private String date;
	
	private enum EPlaceAndDate {	
		Place,
		Date;			       		
   }	

	public PlaceAndDate() {
		super();  
	}

	public PlaceAndDate(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPlaceAndDate) tag) {  			
  				case Place:
  					place = new Place(getScanner());	
  					place.parse(tag.name());				
  					break;  			
				default:
  					break;
  			}
  		} else {
  			switch((EPlaceAndDate) tag) {   			  				
  				case Date:
  					setDate(value);
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
  			return EPlaceAndDate.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public Place getPlace() {
		return place;
	}    
	public void setPlace(Place place) {
		this.place = place;
	}
		
	public String getDate() {
		return date;
	}    
	public void setDate(String value) {
		this.date = value;
	}	
}

