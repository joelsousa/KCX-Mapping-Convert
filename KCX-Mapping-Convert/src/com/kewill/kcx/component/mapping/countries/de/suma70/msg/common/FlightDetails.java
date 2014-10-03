package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: FlightDetails 
 * Created     	: 17.10.2013
 * Description 	: Contains the FlightDetails (used for Kids-Reexport / FSS-SAK)
 * 
 * @author iwaniuk 
 * @version 1.0.00
 */
public class FlightDetails extends KCXMessage {

	private String flightNumber;	
	private String carrierCode;						//Uids:TransportType.TransportModeCode
	private String numberOfFlight;					// immer 4-stellig
	private String additionalQualifier;				// alles von  flightNumber.lenght > 6 
	private String departureCode;
	private String departureDateTime;
	private String arrivalCode;
	private String arrivalDateTime;
	
	private enum ETransport {
		//KIDS:							
		FlightNumber,
		NumberOfFlight,
		CarrierCode,
		AdditionalQualifier, 							
		AirportOfDeparture,	
		DepartureDateTime,
		AirportOfArrival,
		ArrivalDateTime,
	}

	public FlightDetails() {
  		super();
  	}
			 
	public FlightDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ETransport) tag) {			
			default:
				return;
			}
	    } else {
	    	switch ((ETransport) tag) {			
			case FlightNumber:
				 setFlightNumber(value);
				 break;
			case CarrierCode:	
				 setCarrierCode(value);
				 break;
			case NumberOfFlight:
				 setNumberOfFlight(value);
				 break;
			case AdditionalQualifier:
				 setAdditionalQualifier(value);
				 break;									
			case AirportOfDeparture:  
				setAirportOfDeparture(value);
				break;
			case DepartureDateTime:			
				setDepartureDateTime(value);
				 break;	
			case AirportOfArrival:  
				setAirportOfArrival(value);
				break;			
			case ArrivalDateTime:
				setArrivalDateTime(value);
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
  			return ETransport.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getNumberOfFlight() {
		return numberOfFlight;
	}
	public void setNumberOfFlight(String numberOfFlight) {
		this.numberOfFlight = numberOfFlight;
	}

	public String getAdditionalQualifier() {
		return additionalQualifier;
	}
	public void setAdditionalQualifier(String additionalQualifier) {
		this.additionalQualifier = additionalQualifier;
	}

	public String getAirportOfDeparture() {
		return departureCode;
	}
	public void setAirportOfDeparture(String departureCode) {
		this.departureCode = departureCode;
	}

	public String getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getAirportOfArrival() {
		return arrivalCode;
	}
	public void setAirportOfArrival(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.flightNumber) && 
				Utils.isStringEmpty(this.numberOfFlight) && 
				Utils.isStringEmpty(this.carrierCode) && 
				Utils.isStringEmpty(this.additionalQualifier) && 	
				Utils.isStringEmpty(this.departureCode) &&
				Utils.isStringEmpty(this.departureDateTime) &&
				Utils.isStringEmpty(this.arrivalCode) &&
				Utils.isStringEmpty(this.arrivalDateTime)) {
			return true;
		} else {
			return false;
		}
	}
		
}
