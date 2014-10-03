package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cypern.
 * Created		: 06.07.2011
 * Description	: HEAHEA for CC315A, CC316A, CC328A
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Heahea315 extends KCXMessage {
	
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
	private String	totalNumberPackages;
	private String 	totalGrossMass;
	private String	declarationPlace;
	private String	situationCode;
	private String	paymentType;
	private String	loadingPlace;
	private String	loadingPlaceLng;
	private String	unloadingPlace;
	private String	unloadingPlaceLng;
	private String	declarationTime;	
	private String registrationDateTime;
	private String decRejReaHEA252;        
	private String decRejReaHEA252LNG;     
	private String decRejDatTimHEA116;     
	
	public Heahea315() {
		super();
	}
	
	public Heahea315(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea315(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EHea315 {
		RefNumHEA4,                  
		TraModAtBorHEA76,            
		IdeOfMeaOfTraCroHEA85,       
		IdeOfMeaOfTraCroHEA85LNG,
		NatOfMeaOfTraCroHEA87,
		TotNumOfIteHEA305,
		TotNumOfPacHEA306,
		TotGroMasHEA307,
		DecPlaHEA394,
		DecPlaHEA394LNG,
		SpeCirIndHEA1,
		TraChaMetOfPayHEA1,
		ComRefNumHEA,                    
		ConRefNumHEA ,            
		PlaLoaGOOITE334,
		PlaLoaGOOITE334LNG,
		PlaUnlGOOITE334,
		CodPlUnHEA357LNG,
		DecDatTimHEA114,
		DecRejReaHEA252,
		DecRejReaHEA252LNG,
		DecRejDatTimHEA116,
		DocNumHEA5,                  //MRN>		
		NatHEA001,		
		DecRegDatTimHEA115,         //RegistrationDateTime>
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHea315) tag) {
				default:
						return;
			}
		} else {
			switch((EHea315) tag) {
				case RefNumHEA4:
					setReferenceNumber(value);
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
				case TotNumOfIteHEA305:
					setTotalNumberPositions(value);
					break;
				case TotNumOfPacHEA306:
					setTotalNumberPackages(value);
					break;
				case TotGroMasHEA307:
					setTotalGrossMass(value);
					break;
				case DecPlaHEA394:
					setDeclarationPlace(value);
					break;
				case SpeCirIndHEA1:
					setSituationCode(value);
					break;
				case TraChaMetOfPayHEA1:
					setPaymentType(value);
					break;								
				case ComRefNumHEA:
					setShipmentNumber(value);
					break;					
				case ConRefNumHEA:
					setConvyanceReference(value);
					break;
				case PlaLoaGOOITE334:
					setLoadingPlace(value);
					break;
				case PlaLoaGOOITE334LNG:
					setLoadingPlaceLng(value);
					break;
				case PlaUnlGOOITE334:
					setUnloadingPlace(value);
					break;
				case CodPlUnHEA357LNG:
					setUnloadingPlaceLng(value);
					break;
				case DecDatTimHEA114:
					setDeclarationTime(value);						
					break;
				case DecRejReaHEA252:
					setDeclarationRejectionReason(value);
					break;
				case DecRejReaHEA252LNG:
					setDeclarationRejectionReasonLNG(value);
					break;
				case DecRejDatTimHEA116: 
					setDeclarationRejectionDateAndTime(value);
					break;
				case DocNumHEA5:				
					setMrn(value);
					break;							
				case NatHEA001:
					setTransportationCountry(value);
					break;				
				case DecRegDatTimHEA115:				
					setRegistrationDateTime(value);
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
			return EHea315.valueOf(token);
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

	public String getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(String registrationdateTime) {
		this.registrationDateTime = Utils.checkNull(registrationdateTime);
	}

	public String getTotalNumberPackages() {
		return totalNumberPackages;
	}
	public void setTotalNumberPackages(String totalNumberPackages) {
		this.totalNumberPackages = Utils.checkNull(totalNumberPackages);
	}

	public String getTotalGrossMass() {
		return totalGrossMass;
	}
	public void setTotalGrossMass(String totalGrossMass) {
		this.totalGrossMass = Utils.checkNull(totalGrossMass);
	}

	public String getDeclarationPlace() {
		return declarationPlace;
	}
	public void setDeclarationPlace(String declarationPlace) {
		this.declarationPlace = Utils.checkNull(declarationPlace);
	}

	public String getSituationCode() {
		return situationCode;
	}
	public void setSituationCode(String situationCode) {
		this.situationCode = Utils.checkNull(situationCode);
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = Utils.checkNull(paymentType);
	}
	public String getLoadingPlace() {
		return loadingPlace;
	}
	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = Utils.checkNull(loadingPlace);
	}
	
	public String getLoadingPlaceLng() {
		return loadingPlaceLng;
	}
	public void setLoadingPlaceLng(String loadingPlaceLng) {
		this.loadingPlaceLng = Utils.checkNull(loadingPlaceLng);
	}

	public String getDeclarationTime() {
		return declarationTime;
	}
	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = Utils.checkNull(declarationTime);
	}

	public String getUnloadingPlace() {
		return unloadingPlace;
	}
	public void setUnloadingPlace(String unloadingPlace) {
		this.unloadingPlace = Utils.checkNull(unloadingPlace);
	}

	public String getUnloadingPlaceLng() {
		return unloadingPlaceLng;
	}
	public void setUnloadingPlaceLng(String unloadingPlaceLng) {
		this.unloadingPlaceLng = Utils.checkNull(unloadingPlaceLng);
	}

	public String getDeclarationRejectionReason() {
		return decRejReaHEA252;
	}
	public void setDeclarationRejectionReason(String value) {
		decRejReaHEA252 = value;
	}
	
	public String getDeclarationRejectionReasonLNG() {
		return decRejReaHEA252LNG;
	}
	public void setDeclarationRejectionReasonLNG(String value) {
		decRejReaHEA252LNG = value;
	}
	
	public String getDeclarationRejectionDateAndTime() {
		return decRejDatTimHEA116;
	}
	public void setDeclarationRejectionDateAndTime(String value) {
		decRejDatTimHEA116 = value;
	}
}
