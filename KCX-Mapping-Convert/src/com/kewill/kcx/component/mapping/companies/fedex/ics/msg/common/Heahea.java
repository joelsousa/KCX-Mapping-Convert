package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/*
* Function    : Heahea
* Titel       :
* Date        : 25.11.2010
* Author      : krzoska
* Description : Heahea  Class to contain Header Information. Contains also
* 				data in tag ArrivalItem in ArrivalNotification
* Parameters  :

* Changes
* ------------
* Author      : krzoska 
* Date        : 01.02.2011 
* Label       : AK20110201
* Description : BureauNotification to contain CustomsOfficeFirstEntry
*
** Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*

*/
public class Heahea extends KCXMessage{

	private boolean debug   = false;
	private XMLEventReader 	parser	= null;

	
	private String   	referenceNumber;
	private String   	transportMode;
	private String   	transportationType;
	private String   	transportationNumber;
	private String   	transportationNumberLng;
	private String   	transportationCountry;
	private String   	totalNumberPackages;
	private String   	totalGrossMass;
	//AK20110103
	private String		totalNumberPositions;
	private String   	totGroMasHEA307;
	private String   	declarationPlace;
	private String   	declarationPlaceLng;
	private String   	situationCode;
	private String   	paymentType;
	private String   	shipmentNumber;
	private String   	conveyanceReference;
	private String   	loadingPlace;
	private String   	loadingPlaceLng;
	private String   	unloadingPlace;
	private String   	unloadingPlaceLng;
	private String   	declarationTime;
	
	//CC323
	private String		declaredCountryOfArrival;
	private String 		informationType;
	private String 		declaredDateOfArrival;
	
	//CC328 
	private String 		mRN;
	private String		registrationDateAndTime;
	
	//CC347
	private String		bureauNotification;
	
	private enum EHeaheaTags {
		// Fedex, 				            //ArrivalOperation	
		RefNumHEA4,                         //same
		TraModAtBorHEA76,				    //same
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
		ConRefNumHEA,
		PlaLoaGOOITE334,
		PlaLoaGOOITE334LNG,
		PlaUnlGOOITE334,
		CodPlUnHEA357LNG,
		DecDatTimHEA114,
		
		CouCodOffFirEntDecHEA100,
		InfTypHEA122,
		DivRefNumHEA119,
		UniIdeDivHEA132,
		ExpDatArrHEA701,
		DatTimAmeHEA113,
		//CC328
		DecRegDatTimHEA115,
		DocNumHEA5,
		NatHEA001,
		//CC313
		AmdPlaHEA598,
		AmdPlaHEA598LNG,
		//CC347FR
		DateArrivee,
		NumberOfArrivalItems,
		BureauNotification;
	}
	
	public Heahea() {
	      	super();
	}

	public Heahea(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
	}      
    
	public Heahea(XmlMsgScanner scanner) {
		super(scanner);
 	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			
			switch ((EHeaheaTags) tag) {
			default:
					return;
			}
		} else {
			switch ((EHeaheaTags) tag) {
			case DivRefNumHEA119:
			case RefNumHEA4: 				setReferenceNumber(value);
				break;
			case DocNumHEA5: 				setmRN(value);
				break;
				
			case TraModAtBorHEA76: 			setTransportMode(value); //setTransportationType(value);
				break;

			case UniIdeDivHEA132:
			case IdeOfMeaOfTraCroHEA85: 	
				setTransportationNumber(value);
				
				break;

			case IdeOfMeaOfTraCroHEA85LNG: 	setTransportationNumberLng(value);
				break;
				
			case NatOfMeaOfTraCroHEA87: 	
			case NatHEA001:					setTransportationCountry(value);
				break;
				
			case TotNumOfPacHEA306: 		setTotalNumberPackages(value);
				break;
				
			case TotGroMasHEA307: 			setTotalGrossMass(value);
				break;
				
			case DecPlaHEA394:
			case AmdPlaHEA598:				setDeclarationPlace(value);
				break;
				
			case DecPlaHEA394LNG:
			case AmdPlaHEA598LNG: 			setDeclarationPlaceLng(value);
				break;
				
			case SpeCirIndHEA1: 			setSituationCode(value);
				break;
				
			case TraChaMetOfPayHEA1: 		setPaymentType(value);
				break;
				
			case ComRefNumHEA: 				setShipmentNumber(value);
				break;
				
			case ConRefNumHEA: 				setConveyanceReference(value);
											setTransportationNumber(value);
				break;
				
			case PlaLoaGOOITE334: 			setLoadingPlace(value);
				break;
				
			case PlaLoaGOOITE334LNG: 		setLoadingPlaceLng(value);
				break;
				
			case PlaUnlGOOITE334:			setUnloadingPlace(value);
				break;
				
			case CodPlUnHEA357LNG: 			setUnloadingPlaceLng(value);
				break;
				
			case DecDatTimHEA114: 			setDeclarationTime(value);
				break;
			
			case CouCodOffFirEntDecHEA100:	setDeclaredCountryOfArrival(value);
				break;
				
			case InfTypHEA122:				setInformationType(value);
				break;
				
			case ExpDatArrHEA701:			
			case DateArrivee:				setDeclaredDateOfArrival(value);
				break;
			
			case DecRegDatTimHEA115:		setRegistrationDateAndTime(value);
				break;
			//AK20110103	
			case TotNumOfIteHEA305:			
			case NumberOfArrivalItems:		setTotalNumberPositions(value);
				break;
			case DatTimAmeHEA113:			setDeclarationTime(value);
				break;
			//AK20110201
			case BureauNotification:		setBureauNotification(value);
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

	public Enum translate(String token) {
		try {
			return EHeaheaTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
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

	public String getTransportationType() {
		return transportationType;
	
	}

	public void setTransportationType(String transportationType) {
		this.transportationType = Utils.checkNull(transportationType);
	}

	public String getTransportationNumber() {
		return transportationNumber;
	
	}

	public void setTransportationNumber(String transportationNumber) {
		this.transportationNumber = Utils.checkNull(transportationNumber);
	}

	public String getTransportationNumberLng() {
		return transportationNumberLng;
	
	}

	public void setTransportationNumberLng(String transportationNumberLng) {
		this.transportationNumberLng = Utils.checkNull(transportationNumberLng);
	}

	public String getTransportationCountry() {
		return transportationCountry;
	
	}

	public void setTransportationCountry(String transportationCountry) {
		this.transportationCountry = Utils.checkNull(transportationCountry);
	}

	public String getTotalGrossMass() {
		return totalGrossMass;
	
	}

	public void setTotalGrossMass(String totalGrossMass) {
		this.totalGrossMass = Utils.checkNull(totalGrossMass);
	}

	public String getTotGroMasHEA307() {
		return totGroMasHEA307;
	
	}

	public void setTotGroMasHEA307(String totGroMasHEA307) {
		this.totGroMasHEA307 = Utils.checkNull(totGroMasHEA307);
	}

	public String getDeclarationPlace() {
		return declarationPlace;
	
	}

	public void setDeclarationPlace(String declarationPlace) {
		this.declarationPlace = Utils.checkNull(declarationPlace);
	}

	public String getDeclarationPlaceLng() {
		return declarationPlaceLng;
	
	}

	public void setDeclarationPlaceLng(String declarationPlaceLng) {
		this.declarationPlaceLng = Utils.checkNull(declarationPlaceLng);
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

	public String getShipmentNumber() {
		return shipmentNumber;
	
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	
	}

	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = Utils.checkNull(conveyanceReference);
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

	public String getDeclarationTime() {
		return declarationTime;
	
	}

	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = Utils.checkNull(declarationTime);
	}

	public String getTotalNumberPackages() {
		return totalNumberPackages;
	
	}

	public void setTotalNumberPackages(String totalNumberPackages) {
		this.totalNumberPackages = Utils.checkNull(totalNumberPackages);
	}

	public void setDeclaredCountryOfArrival(String declaredCountryOfArrival) {
		this.declaredCountryOfArrival = declaredCountryOfArrival;
	}

	public String getDeclaredCountryOfArrival() {
		return declaredCountryOfArrival;
	}

	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}

	public String getInformationType() {
		return informationType;
	}

	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = declaredDateOfArrival;
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}

	public String getmRN() {
		return mRN;
	}

	public void setmRN(String mRN) {
		this.mRN = mRN;
	}

	public String getRegistrationDateAndTime() {
		return registrationDateAndTime;
	}

	public void setRegistrationDateAndTime(String registrationDateAndTime) {
		this.registrationDateAndTime = registrationDateAndTime;
	}

	public String getTotalNumberPositions() {
		return totalNumberPositions;
	
	}

	public void setTotalNumberPositions(String totalNumberPositions) {
		this.totalNumberPositions = Utils.checkNull(totalNumberPositions);
	}

	public String getBureauNotification() {
		return bureauNotification;
	
	}

	public void setBureauNotification(String bureauNotification) {
		this.bureauNotification = Utils.checkNull(bureauNotification);
	}
	
	
}
