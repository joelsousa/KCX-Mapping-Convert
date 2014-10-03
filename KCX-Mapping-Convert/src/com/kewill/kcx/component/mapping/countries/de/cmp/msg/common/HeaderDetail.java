package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: HeaderDetail.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class HeaderDetail extends KCXMessage {
   
    private String flightNumber;
    private String airportOfDeparture;
    private String departureDate;
    private String airportOfArrival;
    private String arrivalDate;
    private String customsStatus;
    private String mrn;
    private String temporaryMRN;
    private String releaseDate;
    private String completionDate;
    private String identity;
    private String reason;
    private String customsOfficeOfDeparture;
    private PartyDetails principalDetails;   
    private ArrayList<GuarantyDetails>  guarantyDetailsList; // selber in xsd eingefuegt;
    private ArrayList<ErrorDetails>  errorDetailsList;		 // selber in xsd eingefuegt;
    private String customsRegistration;
    private String registrationDate;
   
    private enum EHeaderDetail {    	  			     	
    	FlightNumber,
    	AirportOfDeparture,
    	DepartureDate,
    	AirportOfArrival,
    	ArrivalDate,
    	CustomsStatus,
    	MRN,
    	TemporaryMRN,
    	ReleaseDate,
    	CompletionDate,
    	Identity,
    	Reason,
    	CustomsOfficeOfDeparture,
    	PrincipalDetails,
    	GuarantyDetails,
    	ErrorDetails,
    	CustomsRegistration,
    	RegistrationDate,
    }        

    public HeaderDetail() {
	      	super();	       
    }
    
    public HeaderDetail(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EHeaderDetail) tag) {	
    			case PrincipalDetails:
    				principalDetails = new PartyDetails(getScanner());    				
    				principalDetails.parse(tag.name());
    				break;
    				
    			case GuarantyDetails:
    				GuarantyDetails guarantyDetails = new GuarantyDetails(getScanner());    				
    				guarantyDetails.parse(tag.name());
    				addGuarantyDetailsList(guarantyDetails);
    				break;
    				
    			case ErrorDetails:
    				ErrorDetails errorDetails = new ErrorDetails(getScanner());    				
    				errorDetails.parse(tag.name());  
    				addErrorDetailsList(errorDetails);
    				break;
    				
    			default:
    					return;
    			}
    		} else {
    			switch ((EHeaderDetail) tag) {
    			case FlightNumber:
    				setFlightNumber(value);
    				break;
    				
    			case AirportOfDeparture:
    				setAirportOfDeparture(value);
    				break;
    			
    			case DepartureDate:
    				setDepartureDate(value);
    				break;
    				
    			case AirportOfArrival:
    				setAirportOfArrival(value);
    				break;
    				
    			case ArrivalDate:
    				setArrivalDate(value);
    				break;
    				
    			case CustomsStatus:
    				setCustomsStatus(value);
    				break;
    				
    			case MRN:
    				setMrn(value);
    				break;
    				
    			case TemporaryMRN:
    				setTemporaryMRN(value);
    				break;
    				
    			case ReleaseDate:
    				setReleaseDate(value);
    				break;
    				
    			case CompletionDate:
    				setCompletionDate(value);
    				break;
    				
    			case Reason:
    				setReason(value);
    				break;
    				
    			case CustomsOfficeOfDeparture:
    				setCustomsOfficeOfDeparture(value);
    				break;
    				
    			case Identity:
    				setIdentity(value);
    				break;
    				
    			case RegistrationDate:
    				setRegistrationDate(value);
    				break;
    				
    			case CustomsRegistration:
    				setCustomsRegistration(value);
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
    		return EHeaderDetail.valueOf(token);
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

	public String getAirportOfDeparture() {
		return airportOfDeparture;
	}

	public void setAirportOfDeparture(String airportOfDeparture) {
		this.airportOfDeparture = airportOfDeparture;
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

	public String getCustomsStatus() {
		return customsStatus;
	}

	public void setCustomsStatus(String customsStatus) {
		this.customsStatus = customsStatus;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getTemporaryMRN() {
		return temporaryMRN;
	}

	public void setTemporaryMRN(String temporaryMRN) {
		this.temporaryMRN = temporaryMRN;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCustomsOfficeOfDeparture() {
		return customsOfficeOfDeparture;
	}

	public void setCustomsOfficeOfDeparture(String customsOfficeOfDeparture) {
		this.customsOfficeOfDeparture = customsOfficeOfDeparture;
	}

	public PartyDetails getPrincipalDetails() {
		return principalDetails;
	}

	public void setPrincipalDetails(PartyDetails principalDetails) {
		this.principalDetails = principalDetails;
	}
	
	public ArrayList<GuarantyDetails> getGuarantyDetailsList() {
		return guarantyDetailsList;
	}

	public void setGuarantyDetailsList(ArrayList<GuarantyDetails> list) {
		this.guarantyDetailsList = list;
	}
	public void addGuarantyDetailsList(GuarantyDetails guaranty) {
		if (this.guarantyDetailsList == null) {
			this.guarantyDetailsList = new ArrayList<GuarantyDetails>();
		}
		this.guarantyDetailsList.add(guaranty);
	}
	
	public ArrayList<ErrorDetails> getErrorDetailsList() {
		return errorDetailsList;
	}

	public void setErrorDetailsList(ArrayList<ErrorDetails> list) {
		this.errorDetailsList = list;
	}
	public void addErrorDetailsList(ErrorDetails error) {
		if (this.errorDetailsList == null) {
			this.errorDetailsList = new ArrayList<ErrorDetails>();
		}
		this.errorDetailsList.add(error);
	}
	
	public String getAirportOfArrival() {
		return airportOfArrival;
	}

	public void setAirportOfArrival(String airportOfArrival) {
		this.airportOfArrival = airportOfArrival;
	}

	public String getCustomsRegistration() {
		return customsRegistration;
	}

	public void setCustomsRegistration(String customsRegistration) {
		this.customsRegistration = customsRegistration;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		if (registrationDate != null && registrationDate.length() > 8) {  //EI20140217
			registrationDate = registrationDate.substring(0, 8);
		}
		this.registrationDate = registrationDate;
	}
	
	public boolean isEmpty() {
		return 	Utils.isStringEmpty(flightNumber) && Utils.isStringEmpty(airportOfDeparture) &&
				Utils.isStringEmpty(departureDate) && Utils.isStringEmpty(airportOfArrival) &&
				Utils.isStringEmpty(arrivalDate) && Utils.isStringEmpty(customsStatus) &&
				Utils.isStringEmpty(mrn) && Utils.isStringEmpty(temporaryMRN) &&	
				Utils.isStringEmpty(releaseDate) && Utils.isStringEmpty(completionDate) &&	
				Utils.isStringEmpty(identity) && Utils.isStringEmpty(reason) &&	
				Utils.isStringEmpty(customsOfficeOfDeparture) && Utils.isStringEmpty(customsRegistration) &&	
				Utils.isStringEmpty(registrationDate) &&
				(principalDetails == null || principalDetails.isEmpty()) &&
				guarantyDetailsList == null && errorDetailsList == null; 
	}	
	
}
