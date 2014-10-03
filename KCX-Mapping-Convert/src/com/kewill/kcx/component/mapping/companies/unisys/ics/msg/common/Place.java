package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Station<br>.  
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS:
 * 				: (Emirate, Dest, Departure, Origin, Loading, Unloading) 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Place extends KCXMessage {
	 
	private String station = "";
	private String country = "";
	private String ccd = "";
	
	 private enum EStation {
	 // Unisys-TagNames, 			KIDS-TagNames
		 STATION,
		 CCD,
		 COUNTRY;	
	 }

	 public Place() {
	      	super();	      	
	 }    
   
	 public Place(XmlMsgScanner scanner) {
		super(scanner);		
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EStation) tag) {			
				default:
					return;
			}
		} else {

			switch ((EStation) tag) {
				case STATION:
					setStation(value);
					break;  	
				case CCD:
					setCCD(value);
					break;  
				case COUNTRY:
					setCountry(value);
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
			return EStation.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getStation() {
		return station;	
	}
	public void setStation(String argument) {
		this.station = Utils.checkNull(argument);
	}

	public String getCCD() {
		return ccd;	
	}
	public void setCCD(String argument) {
		this.ccd = Utils.checkNull(argument);
	}	
	
	public String getCountry() {
		return country;	
	}
	public void setCountry(String argument) {
		this.country = Utils.checkNull(argument);
	}	
}
