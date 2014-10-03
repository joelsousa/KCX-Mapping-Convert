package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cypern.
 * Created		: 06.07.2011
 * Description	: HEAHEA for CC323A, CC324A, CC325A
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Heahea323 extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String	referenceNumber;
	private String	transportMode;	
	private String	transportationNumber;
	private String  declaredCountryOfArrival;
	private String  informationType;
	private String  declaredDateOfArrival;	
	private String  registrationDateTime;
	private String  declarationRejectionDateAndTime;
	private String  declarationRejectionReason;
	private String  declarationRejectionReasonLng;
	
	public Heahea323() {
		super();
	}
	
	public Heahea323(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea323(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EHea323 {		
		DivRefNumHEA119,
		TraModAtBorHEA76,
		CouCodOffFirEntDecHEA100,	
		InfTypHEA122,
		UniIdeDivHEA132,
		ExpDatArrHEA701,
		RegDatTimHEA125,
		RejDatTimHEA126,
		RejReaHEA127,
		RejReaHEA128LNG,

	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHea323) tag) {
				default:
						return;
			}
		} else {
			switch((EHea323) tag) {						
				case DivRefNumHEA119:
					setReferenceNumber(value);
					break;				
				case TraModAtBorHEA76:
					setTransportMode(value);
					break;										
				case RegDatTimHEA125:
					setRegistrationDateTime(value);
					break;							
				case InfTypHEA122:
					setInformationType(value);
					break;				
				case CouCodOffFirEntDecHEA100:
					setDeclaredCountryOfArrival(value);
					break;
				case UniIdeDivHEA132:
					setTransportationNumber(value);
					break;
				case ExpDatArrHEA701:
					setDeclaredDateOfArrival(value);	
					break;
				case RejDatTimHEA126:
					setDeclarationRejectionDateAndTime(value);
					break;
				case RejReaHEA127:
					setDeclarationRejectionReason(value);
					break;
				case RejReaHEA128LNG:
					setDeclarationRejectionReasonLng(value);
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
			return EHea323.valueOf(token);
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
	
	public String getTransportationNumber() {
		return transportationNumber;
	}
	public void setTransportationNumber(String transportationNumber) {
		this.transportationNumber = Utils.checkNull(transportationNumber);
	}

	public String getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(String registrationdateTime) {
		this.registrationDateTime = Utils.checkNull(registrationdateTime);
	}
	public String getInformationType() {
		return informationType;
	}
	public void setInformationType(String informationType) {
		this.informationType = Utils.checkNull(informationType);
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}
	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = Utils.checkNull(declaredDateOfArrival);
	}

	public String getDeclaredCountryOfArrival() {
		return declaredCountryOfArrival;
	}
	public void setDeclaredCountryOfArrival(String declaredCountryOfArrival) {
		this.declaredCountryOfArrival = Utils.checkNull(declaredCountryOfArrival);
	}
	
	public String getDeclarationRejectionDateAndTime() {
		return declarationRejectionDateAndTime;
	}
	public void setDeclarationRejectionDateAndTime(String value) {
		declarationRejectionDateAndTime = value;
	}
	
	public String getDeclarationRejectionReason() {
		return declarationRejectionReason;
	}
	public void setDeclarationRejectionReason(String value) {
		declarationRejectionReason = value;
	}

	public String getDeclarationRejectionReasonLng() {
		return declarationRejectionReasonLng;
	}
	public void setDeclarationRejectionReasonLng(String value) {
		declarationRejectionReasonLng = value;
	}
}
