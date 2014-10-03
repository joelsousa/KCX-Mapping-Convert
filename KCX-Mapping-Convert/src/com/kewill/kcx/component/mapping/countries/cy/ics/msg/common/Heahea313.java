package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cypern.
 * Created		: 06.07.2011
 * Description	: HEAHEA for CC313A.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Heahea313 extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String 	mrn;
	private String	referenceNumber;
	private String	transportMode;
	private String 	transportationCountry;
	private String	transportationNumber;
	private String	totalNumberPositions;
	private String	totalNumberPackages;
	private String 	totalGrossMass;
	private String	declarationPlace;
	private String	situationCode;
	private String	paymentType;
	private String	shipmentNumber;
	private String	conveyanceReference;
	private String	loadingPlace;
	private String	loadingPlaceLng;
	private String	unloadingPlace;
	private String	unloadingPlaceLng;
	private String	declarationTime;
	
	public Heahea313() {
		super();
	}
	
	public Heahea313(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea313(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EHea313 {
		DocNumHEA5,                 
		TraModAtBorHEA76,	 
		IdeOfMeaOfTraCroHEA85,
		IdeOfMeaOfTraCroHEA85LNG,
		NatOfMeaOfTraCroHEA87,
		TotNumOfIteHEA305,
		TotNumOfPacHEA306,
		TotGroMasHEA307,
		AmdPlaHEA598,
		DecPlaHEA394,
		SpeCirIndHEA1,
		TraChaMetOfPayHEA1,
		ComRefNumHEA,                 
		ConRefNumHEA,                 
		PlaLoaGOOITE334,
		PlaLoaGOOITE334LNG,
		PlaUnlGOOITE334,
		CodPlUnHEA357LNG,
		DatTimAmeHEA113,

	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHea313) tag) {
				default:
						return;
			}
		} else {
			switch((EHea313) tag) {
				case DocNumHEA5:
					setMrn(value);
					break;
				case TraModAtBorHEA76:
					setReferenceNumber(value);
					break;
				case IdeOfMeaOfTraCroHEA85:
					setTransportMode(value);
					break;
				case NatOfMeaOfTraCroHEA87:
					//set(value);
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
				case AmdPlaHEA598:
					setDeclarationPlace(value);
					break;
				case DecPlaHEA394:					
					break;
				case SpeCirIndHEA1:
					setSituationCode(value);
					break;
				case TraChaMetOfPayHEA1:
					setPaymentType(value);
					break;
				case ComRefNumHEA :   
					setShipmentNumber(value);
					break;				
				case ConRefNumHEA:   
					setConveyanceReference(value);
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
				case DatTimAmeHEA113:
					setDeclarationTime(value);
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
			return EHea313.valueOf(token);
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

	public String getConveyanceReference() {
		return conveyanceReference;
	}
	public void setConveyanceReference(String convyanceReference) {
		this.conveyanceReference = Utils.checkNull(convyanceReference);
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

}
