package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cypern.
 * Created		: 06.07.2011
 * Description	: HEAHEA for CC351A
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Heahea351 extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String 	mrn;
	private String	referenceNumber;
	private String	transportMode;
	private String 	transportationCountry;
	private String	transportationNumber;
	private String	totalNumberPositions;
	private String	shipmentNumber;
	private String	convyanceReference;
	private String	notificationDateTime;
	private String	registrationdateTime;
	private String	acceptedDateTime;
	
	public Heahea351() {
		super();
	}
	
	public Heahea351(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea351(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EHea351 {		
		RefNumHEA4,
		DocNumHEA5,
		TraModAtBorHEA76,
		NatHEA001,
		IdeOfMeaOfTraCroHEA85,
		TotNumOfIteHEA305,
		ComRefNumHEA,
		ConRefNumHEA,
		NotDatTimHEA104,
		DecRegDatTimHEA115,
		DecSubDatTimHEA118,			
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHea351) tag) {
				default:
						return;
			}
		} else {
			switch((EHea351) tag) {						
				case RefNumHEA4:
					setMrn(value);									
					break;								
				case DocNumHEA5:
					setReferenceNumber(value);					
					break;					
				case TraModAtBorHEA76:
					setTransportMode(value);					
					break;					
				case NatHEA001:				
					setTransportationCountry(value);
					break;
				case IdeOfMeaOfTraCroHEA85:
					setTransportationNumber(value);
					break;
				case TotNumOfIteHEA305:
					setTotalNumberPositions(value);
					break;
				case ComRefNumHEA:
					setShipmentNumber(value);
					break;					
				case ConRefNumHEA:
					setConvyanceReference(value);
					break;					
				case NotDatTimHEA104:
					setNotificationDateTime(value);
					break;					
				case DecRegDatTimHEA115:				
					setRegistrationdateTime(value);
					break;				
				case DecSubDatTimHEA118:
					setAcceptedDateTime(value);
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
			return EHea351.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mRN) {
		this.mrn = Utils.checkNull(mRN);
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = Utils.checkNull(transportMode);
	}

	public String getTransportationCountry() {
		return transportationCountry;
	}

	public void setTransportationCountry(String transportationCountry) {
		this.transportationCountry = Utils.checkNull(transportationCountry);
	}

	public String getTransportationNumber() {
		return transportationNumber;
	}

	public void setTransportationNumber(String transportationNumber) {
		this.transportationNumber = Utils.checkNull(transportationNumber);
	}

	public String getTotalNumberPositions() {
		return totalNumberPositions;
	}

	public void setTotalNumberPositions(String totalNumberPositions) {
		this.totalNumberPositions = Utils.checkNull(totalNumberPositions);
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}

	public String getConvyanceReference() {
		return convyanceReference;
	}

	public void setConvyanceReference(String convyanceReference) {
		this.convyanceReference = Utils.checkNull(convyanceReference);
	}

	public String getNotificationDateTime() {
		return notificationDateTime;
	}

	public void setNotificationDateTime(String notificationDateTime) {
		this.notificationDateTime = Utils.checkNull(notificationDateTime);
	}

	public String getRegistrationdateTime() {
		return registrationdateTime;
	}

	public void setRegistrationdateTime(String registrationdateTime) {
		this.registrationdateTime = Utils.checkNull(registrationdateTime);
	}

	public String getAcceptedDateTime() {
		return acceptedDateTime;
	}

	public void setAcceptedDateTime(String acceptedDateTime) {
		this.acceptedDateTime = Utils.checkNull(acceptedDateTime);
	}

}
