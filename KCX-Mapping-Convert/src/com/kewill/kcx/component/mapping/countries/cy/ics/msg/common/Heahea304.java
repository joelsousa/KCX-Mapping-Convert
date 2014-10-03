package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cypern.
 * Created		: 06.07.2011
 * Description	: HEAHEA for CC304A, CC305A
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Heahea304 extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String 	mrn;
	private String	referenceNumber;
	private String	transportMode;
	private String 	transportationCountry;
	private String	transportationNumber;	
	private String	shipmentNumber;
	private String	convyanceReference;	
	private String 	declarationTime;
	private String 	amendmentDateAndTime;
	private String 	amendmentRejectionDateAndTime;
	private String	declarationRejectionDateAndTime;
	private String	declarationRejectionReason;
	private String	declarationRejectionReasonLNG;

	
	public Heahea304() {
		super();
	}
	
	public Heahea304(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea304(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EHea304 {			
		DocNumHEA5,
		TraModAtBorHEA76,		
		IdeOfMeaOfTraCroHEA85,
		IdeOfMeaOfTraCroHEA85LNG,		
		NatOfMeaOfTraCroHEA87,
		ComRefNumHEA,
		ConRefNumHEA,
		AmeAccDatTimHEA111,
		DatTimAmeHEA113,		
		AmeRejMotCodHEA604,
		AmeRejMotTexHEA605,
		AmeRejMotTexHEA605LNG,		
		AmeRejDatTimHEA112,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHea304) tag) {
				default:
						return;
			}
		} else {
			switch((EHea304) tag) {
				
				case DocNumHEA5:				
					setMrn(value);
					break;				
				case TraModAtBorHEA76:
					setTransportMode(value);
					break;
				case IdeOfMeaOfTraCroHEA85:
					setTransportationNumber(value);
					break;				
				case NatOfMeaOfTraCroHEA87:
					setTransportationCountry(value);
					break;
				case ComRefNumHEA:
					setReferenceNumber(value);
					break;					
				case ConRefNumHEA:
					setConvyanceReference(value);
					break;												
				case AmeAccDatTimHEA111:
				case DatTimAmeHEA113:		
					setAmendmentDateAndTime(value);							
					setDeclarationTime(value);
					break;						
				case AmeRejDatTimHEA112:
					setAmendmentRejectionDateAndTime(value);
					break;
				case AmeRejMotCodHEA604:
				case AmeRejMotTexHEA605:
				case AmeRejMotTexHEA605LNG:		
					//will be added
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
			return EHea304.valueOf(token);
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

	public String getAmendmentDateAndTime() {
		return amendmentDateAndTime;
	}
	public void setAmendmentDateAndTime(String amendmentDateAndTime) {
		this.amendmentDateAndTime = Utils.checkNull(amendmentDateAndTime);
	}

	public String getAmendmentRejectionDateAndTime() {
		return amendmentRejectionDateAndTime;
	}
	public void setAmendmentRejectionDateAndTime(String datetime) {
		this.amendmentRejectionDateAndTime = Utils.checkNull(datetime);
	}
	
	public String getDeclarationRejectionDateAndTime() {
		return this.declarationRejectionDateAndTime;
	}
	public void setDeclarationRejectionDateAndTime(String datetime) {
		this.declarationRejectionDateAndTime = Utils.checkNull(datetime);
	}
	
	public String getDeclarationRejectionReason() {
		return this.declarationRejectionReason;
	}
	public void setDeclarationRejectionReason(String decRejRea) {
		this.declarationRejectionReason	= Utils.checkNull(decRejRea);
	}
	
	public String getDeclarationRejectionReasonLNG() {
		return this.declarationRejectionReasonLNG;
	}
	public void setDeclarationRejectionReasonLNG(String decRejReaLNG) {
		this.declarationRejectionReasonLNG	= Utils.checkNull(decRejReaLNG);
	}
	
	public String getDeclarationTime() {
		return declarationTime;
	}
	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = Utils.checkNull(declarationTime);
	}
}
