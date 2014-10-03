package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS/msg/common<br>
 * Created		: 04.10.2012<br>
 * Description	: Conveyance call.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class ConveyanceCall extends KCXMessage {
	
	private String purposeOfCall;
    private String numberOfCrew;
    private String numberOfPassengers;
    private String position;
   
	private enum EConveyanceCall {
		//KIDS:								UIDS:
		PurposeOfCall,						
		NumberOfCrew,	
		NumberOfPassengers,
		PositionOfTheShipOrAirplaneInPort;
    }
	
	public ConveyanceCall() {
		super();		
	}

    public ConveyanceCall(XmlMsgScanner scanner) {
  		super(scanner);		
  	}      
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EConveyanceCall) tag) {				
  				default:
  					return;
  			}
  		} else {

  			switch ((EConveyanceCall) tag) {
  				case PurposeOfCall:
  					setPurposeOfCall(value);
  					break;
  					
  				case NumberOfCrew:
  					setNumberOfCrew(value);
  					break;
  					
  				case NumberOfPassengers:
  					setNumberOfPassengers(value);
  					break;
  					
  				case PositionOfTheShipOrAirplaneInPort:
  					setPositionOfTheShipOrAirplaneInPort(value);
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
  			return EConveyanceCall.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getPurposeOfCall() {
		return purposeOfCall;
	}
	public void setPurposeOfCall(String value) {
		purposeOfCall = value;
	}

	public String getNumberOfCrew() {
		return numberOfCrew;
	}
	public void setNumberOfCrew(String value) {
		this.numberOfCrew = value;
	}
	
	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(String value) {
		numberOfPassengers = value;
	}

	public String getPositionOfTheShipOrAirplaneInPort() {
		return position;
	}
	public void setPositionOfTheShipOrAirplaneInPort(String value) {
		this.position = value;
	}
							
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.purposeOfCall) && Utils.isStringEmpty(this.numberOfCrew) &&
				Utils.isStringEmpty(this.numberOfPassengers) && Utils.isStringEmpty(this.position));				
	}
}
