package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: FlightInfo.<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class FlightInfo extends KCXMessage {
	 
	private String ccd;
	private String flight;
	private String date;
	private String mode;
	private Nationality nationality;
	
	
	private enum EFlightInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		CCD,
		FLIGHT,
		DATE,
		MODE,
		NATIONALITY;
	}

	public FlightInfo() {
		super();
	}    

	public FlightInfo(XmlMsgScanner scanner) {
		super(scanner);
	}
	 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EFlightInfo) tag) {
			case NATIONALITY:
				nationality = new Nationality(getScanner());  	
				nationality.parse(tag.name());				
				break;
			default:
					return;
			}
		} else {

			switch ((EFlightInfo) tag) {
				case CCD:
					setCcd(value);
					break;
				case FLIGHT:
					setFlight(value);
					break;  											
				case DATE:
					setDate(value);
					break;  											
				case MODE:
					setMode(value);
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
			return EFlightInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCcd() {
		return ccd;
	}
	public void setCcd(String ccd) {
		this.ccd = Utils.checkNull(ccd);
	}

	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = Utils.checkNull(flight);
	}

	public String getDate() {
		return date;	
	}
	public void setDate(String date) {
		this.date = Utils.checkNull(date);
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = Utils.checkNull(mode);
	}
	
	public Nationality getNationality() {
		return nationality;
	}
	public void setNationality(Nationality argument) {
		this.nationality = argument;
	}
}