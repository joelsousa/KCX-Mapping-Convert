package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 03.07.2013<br>
 * Description	: LocalApplication: == TsKUN == Zabis-DeclarationStatus, Verarbeitungszustand,  usw.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class LocalApplication extends KCXMessage {
	
	private String messageType;       		
	private String messageSubType;  
	private String messageFunction;  
	private String declarationStatus; 
	private String registrationNumber; //EI20140128
	private String registrationDate;
	//private String positionStatus; 	
	private String flightNumber;  		//EI2013100
	private String airportOfDeparture;  //EI20131002  beladeOrt
	private String airportOfArrival;    //EI20131002 entladeOrt
	private String arrivalDate;    		//EI20140128
	private String departureDate;  		//EI20140128
	private String positionDestinationPlace;  //EI20140314
	private String additionalData;  
	private ArrayList<LocalAppPosition> positionList; 
	
	
	private enum ELocalApplication {
		//KIDS							
		MessageType,						
		MessageSubType,
		MessageFunction,
		RegistrationNumber,
		RegistrationDate,
		DeclarationStatus,				
		PositionStatus,
		FlightNumber,
		AirportOfDeparture, PortOfDepartureCode, DepartureCode, 
		AirportOfArrival, PortOfArrivalCode, ArrivalCode,
		AdditionalInformation,	AdditionalData,
		DepartureDate,
		ArrivalDate,
		LocalApplicationPosition,
	}
	
	public LocalApplication() {
		super();  
	}

    public LocalApplication(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ELocalApplication) tag) {  
  			case LocalApplicationPosition:
  				LocalAppPosition pos = new LocalAppPosition(getScanner());
				pos.parse(tag.name());
				addPositionList(pos);
				break;
  			default:
  					return;
  			}
  		} else {

  			switch ((ELocalApplication) tag) {
  			case MessageType:
  				setMessageType(value);
  				break;  				
  			case MessageSubType:
  				setMessageSubType(value);
  				break;  				  
  			case MessageFunction:
  				setMessageFunction(value);
  				break;  				
  			case DeclarationStatus:
  				setDeclarationStatus(value);
  				break;  				
  			case RegistrationDate:
  				setRegistrationDate(value);
  				break;  				  			
  			case FlightNumber:      //EI20131002
  				setFlightNumber(value);
  				break;
  			case PortOfDepartureCode:
  			case AirportOfDeparture:
  			case DepartureCode:      //EI20131002
  				setAirportOfDeparture(value);
  				break;
  			case PortOfArrivalCode:  //EI20131202
  			case AirportOfArrival:
  				setAirportOfArrival(value);
  				break;
  			case AdditionalInformation:
  			case AdditionalData:
  				setAdditionalData(value);
  				break; 
  			case DepartureDate:
  				setDepartureDate(value);
  				break; 
  			case ArrivalDate:
  				setArrivalDate(value);
  				break; 
  			case RegistrationNumber:
  				setRegistrationNumber(value);
  				break; 
  			 
  			default:
  				break;
  			}
  		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		 try {
				return ELocalApplication.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageSubType() {
		return messageSubType;
	}
	public void setMessageSubType(String messageSubType) {
		this.messageSubType = messageSubType;
	}

	public String getDeclarationStatus() {
		return declarationStatus;
	}
	public void setDeclarationStatus(String declarationStatus) {
		this.declarationStatus = declarationStatus;
	}

	public String getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	
	public String getMessageFunction() {
		return messageFunction;
	}
	public void setMessageFunction(String messageFunction) {
		this.messageFunction = messageFunction;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String number) {
		this.flightNumber = number;
	}
	
	public String getAirportOfDeparture() {
		return airportOfDeparture;
	}
	public void setAirportOfDeparture(String code) {
		this.airportOfDeparture = code;
	}
		
	public String getAirportOfArrival() {
		return airportOfArrival;
	}
	public void setAirportOfArrival(String code) {
		this.airportOfArrival = code;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public ArrayList<LocalAppPosition> getPositionList() {
		return positionList;
	}
	public void setPositionList(ArrayList<LocalAppPosition> list) {
		this.positionList = list;
	}
	public void addPositionList(LocalAppPosition position) {
		if (positionList == null) {
			positionList = new ArrayList<LocalAppPosition>();
		}
		this.positionList.add(position);
	}
	public String getPositionStatus(String posnr) {
		String status = "";
		if (positionList == null || positionList.isEmpty())  {
			return "";
		}
		if (Utils.isStringEmpty(posnr)) {
			return "";
		}
		for (LocalAppPosition pos : positionList) {
			if (pos != null && pos.getPositionNumber() != null) {
				if (posnr.equals(pos.getPositionNumber())) {
					status = pos.getPositionStatus();
					break;
				}
			}
		}
		return status;
	}
}
