package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Port.<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS. 
 * 				: (Arrival, Diversion, Offload)
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class Port extends KCXMessage {

	private HashMap<String, String> enumMap = null;
	 
	private String 	station;
	private String 	portCode;
	private String 	airport;
	private DateAndTime sta;
	
	private enum EPort {
	 // Unisys-TagNames, 			KIDS-TagNames
		STATION,
		PortCode,
		STA,
		AIRPORT;
	}

	public Port() {
		super();
		initEnumMap(); 
	}    
  	
	public Port(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	}
	
	private void initEnumMap() {
		enumMap = new HashMap<String, String>();		
		enumMap.put("PORT-CODE", "PortCode");				
	}	
	 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPort) tag) {
			case STA:
				sta = new DateAndTime(getScanner());  	
				sta.parse(tag.name());				
				break;	
			default:
					return;
			}
		} else {
			switch ((EPort) tag) {
			case STATION:
				setStation(value);
				break;
			case PortCode:
				setPortCode(value);
				break;  											
			case AIRPORT:
				setAirport(value);
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
			String text = enumMap.get(token);
			if (text != null) {
				token = text;
			}
			return EPort.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getStation() {
		return station;	
	}
	public void setStation(String station) {
		this.station = Utils.checkNull(station);
	}

	public String getPortCode() {
		return portCode;	
	}
	public void setPortCode(String portCode) {
		this.portCode = Utils.checkNull(portCode);
	}

	public String getAirport() {
		return airport;	
	}
	public void setAirport(String airport) {
		this.airport = Utils.checkNull(airport);
	}

	public DateAndTime getSta() {
		return sta;
	}
	public void setSta(DateAndTime argument) {
		this.sta = argument;
	}
}