package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 17.06.2014<br>
 * Description	: diverse Codes.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class TransportMeans extends KCXMessage {
	
	 private String modeAndTypeCode;   
	 private String transportType;
	 private String registrationNationalityIdentifier;   
	 private String identificationText;  
	 private Itinerary itinerary;
	 
     public TransportMeans() {
  		super();
  	}
     public TransportMeans(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum TransportMeansIE {
		ModeAndTypeCode,
		TransportType,
		RegistrationNationalityIdentifier,
		IdentificationText,	IdentityText,
		Itinerary,
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((TransportMeansIE) tag) {
 				case Itinerary:
 					itinerary = new Itinerary(getScanner());  	
 					itinerary.parse(tag.name());
 					break;
 				default:
 					return;
 			}
 		} else {
 			switch ((TransportMeansIE) tag) { 
 				case ModeAndTypeCode:
 					setModeAndTypeCode(value);
					break; 
 				case TransportType:
 					setTransportType(value);
 					break;
				case RegistrationNationalityIdentifier:
					setRegistrationNationalityIdentifier(value);
	 				break; 	
 				case IdentificationText:
 				case IdentityText:
 					setIdentificationText(value);
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
 			return TransportMeansIE.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}	 
		
	public String getModeAndTypeCode() {
		return modeAndTypeCode;
	}
	public void setModeAndTypeCode(String modeAndTypeCode) {
		this.modeAndTypeCode = modeAndTypeCode;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getRegistrationNationalityIdentifier() {
		return registrationNationalityIdentifier;
	}
	public void setRegistrationNationalityIdentifier(String registrationNationalityIdentifier) {
		this.registrationNationalityIdentifier = registrationNationalityIdentifier;
	}
	public String getIdentificationText() {
		return identificationText;
	}
	public void setIdentificationText(String identificationText) {
		this.identificationText = identificationText;
	}
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.modeAndTypeCode) && Utils.isStringEmpty(this.transportType) &&
			Utils.isStringEmpty(this.registrationNationalityIdentifier) &&Utils.isStringEmpty(this.identificationText) &&
			itinerary == null;  
	}	
}
