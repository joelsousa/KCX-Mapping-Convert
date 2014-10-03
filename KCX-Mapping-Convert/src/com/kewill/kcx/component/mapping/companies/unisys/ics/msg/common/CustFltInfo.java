package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Modul		: CustFltInfo.<br>
* Erstellt		: 02.12.2010<br>
* Beschreibung	: Flight info of Unisys
*  				    
* @author krzoska
* @version 1.0.00
*/

public class CustFltInfo extends KCXMessage {

	private HashMap<String, String> enumMap = null;
	
	private FlightInfo flightInfo;
	private Place origin;
	private Place departure;
	private Port arrival;
	private Port diversion;
	private CtryInfo ctryInfo;		
	
	private enum ECustFltInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		FlightInfo,	
		ORIGIN,
		DEPARTURE,
		ARRIVAL,
		DIVERSION,
		CtryInfo;
	 }

	 private void initEnumMap() {
	    enumMap = new HashMap<String, String>();
	    enumMap.put("FLIGHT-INFO", "FlightInfo"); 		    		    
	    enumMap.put("CTRY-INFO", "CtryInfo");
     }
	
	 public CustFltInfo() {
	      	super();
	      	initEnumMap();
	 }          
   
	 public CustFltInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap();
	 }

	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustFltInfo) tag) {
			case FlightInfo:
				flightInfo = new FlightInfo(getScanner());  	
				//flightInfo.parse(tag.name());		
				flightInfo.parse("FLIGHT-INFO");	  
				break;	
			case ORIGIN:
				origin = new Place(getScanner());  	
				origin.parse(tag.name());				
				break;	
			case DEPARTURE:
				departure = new Place(getScanner());  	
				departure.parse(tag.name());				
				break;	
			case ARRIVAL:	
				arrival = new Port(getScanner());
				arrival.parse(tag.name());
				break;
			case DIVERSION:	
				diversion = new Port(getScanner());
				diversion.parse(tag.name());
				break;
			case CtryInfo:	
				ctryInfo = new CtryInfo(getScanner());
				//ctryInfo.parse(tag.name());
				ctryInfo.parse("CTRY-INFO");
				break;				
			default:
					return;
			}
		} else {

			switch ((ECustFltInfo) tag) {
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		String text = enumMap.get(token);
		if (text != null) {
			token = text;
		}
		try {
			return ECustFltInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}
	
	public Place getOrigin() {
		return origin;
	}
	public void setOrigin(Place argument) {
		this.origin = argument;
	}
	
	public Place getDeparture() {
		return departure;
	}
	public void setDeparture(Place argument) {
		this.departure = argument;
	}
	
	public Port getArrival() {
		return arrival;
	}
	public void setArrival(Port argument) {
		this.arrival = argument;
	}
	
	public Port getDiversion() {
		return diversion;
	}
	public void setDiversion(Port argument) {
		this.diversion = argument;
	}

	public CtryInfo getCtryInfo() {
		return ctryInfo;
	}

	public void setCtryInfo(CtryInfo ctryInfo) {
		this.ctryInfo = ctryInfo;
	}

	
}
