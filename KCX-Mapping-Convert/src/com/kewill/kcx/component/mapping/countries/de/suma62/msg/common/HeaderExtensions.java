package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 11.12.2012<br>
 * Description	: Contains the HeaderExtensions Data with all Fields used in KIDS Manifest.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class HeaderExtensions extends KCXMessage {
	
	private String advanceProcedureID;
	private String confirmationCode;
	private String headPosID;
	private String maritimTrafficID;	
	private String registrationID;
	private String flightCompletionCode;
	private String terminationOfFlight;

	public HeaderExtensions() {
		super();  
	}	

    public HeaderExtensions(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
    private enum EHeaderExtensions {
		//KIDS == UIDS
    	AdvanceProcedureID,
    	ConfirmationCode,
    	HeadPosID,    	
    	MaritimTrafficID,
    	RegistrationID,    
    	FlightCompletionCode,
    	TerminationOfFlight;
	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EHeaderExtensions) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EHeaderExtensions) tag) {
  				case RegistrationID:  				
  					setRegistrationID(value);
  					break;
  				case AdvanceProcedureID:  				
  					setAdvanceProcedureID(value);
  					break;
  				case MaritimTrafficID:  				
  					setMaritimTrafficID(value);
  					break;
  				case ConfirmationCode:  				
  					setConfirmationCode(value);
  					break;
  				case TerminationOfFlight:  				
  					setTerminationOfFlight(value);
  					break; 
  				case HeadPosID:
  					setHeadPosID(value);
  					break; 
  				case FlightCompletionCode:
  					setFlightCompletionCode(value);
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

	@Override
	public Enum translate(String token) {
		 try {
				return EHeaderExtensions.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	
	
	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}

	public String getAdvanceProcedureID() {
		return advanceProcedureID;
	}

	public void setAdvanceProcedureID(String advanceProcedureID) {
		this.advanceProcedureID = advanceProcedureID;
	}

	public String getMaritimTrafficID() {
		return maritimTrafficID;
	}

	public void setMaritimTrafficID(String maritimTrafficID) {
		this.maritimTrafficID = maritimTrafficID;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getTerminationOfFlight() {
		return terminationOfFlight;
	}

	public void setTerminationOfFlight(String terminationOfFlight) {
		this.terminationOfFlight = terminationOfFlight;
	}

	public String getHeadPosID() {
		return headPosID;
	}

	public void setHeadPosID(String value) {
		this.headPosID = value;
	}
		
	public String getFlightCompletionCode() {
		return flightCompletionCode;
	}
	public void setFlightCompletionCode(String flightCompletionCode) {
		this.flightCompletionCode = flightCompletionCode;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(registrationID) && Utils.isStringEmpty(advanceProcedureID) &&
				Utils.isStringEmpty(maritimTrafficID) && Utils.isStringEmpty(confirmationCode) &&
				Utils.isStringEmpty(headPosID) && Utils.isStringEmpty(flightCompletionCode) &&
				Utils.isStringEmpty(terminationOfFlight));		       
	}

}
