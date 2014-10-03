package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportationRoute;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgNCTSDeclaration<br>.
 * Erstellt		: 31.08.2010<br>
 * Beschreibung	: 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgNCTSDeclaration extends KCXMessage { 
	
	private String			msgName = "NCTSDeclaration";
	private String			referenceNumber;
	private String			shipmentNumber;
	private String			authorisationNumber;
	private String			typeOfDeclaration;
	//EI20110524 is UIDS: private String			placeOfLoadingCode; //PlaceOfLoading/Code
	//EI20110524 is UIDS: private String			agreedLocationOfGoods; //PlaceOfLoading/AgreedLocationOfGoods
	//EI20110524 is UIDS: private String			agreedLocationOfGoodsCode; //PlaceOfLoading/AgreedLocationOfGoodsCode
	//EI20110524 is UIDS: private String			authorisedLocationOfGoodsCode; //PlaceOfLoading/AuthorisedLocationOfGoodsCode
	private PlaceOfLoading	placeOfLoading;
	private String			destinationCountry;
	private String			dispatchCountry;
	private String			customsSubPlace;
	private TransportMeans	meansOfTransportInland;
	private TransportMeans	meansOfTransportBorder;
	private TransportMeans  meansOfTransportDeparture;
	private TransportMeans	meansOfTransportCrossingBorder;
	private String			transportInContainer;
	private String 			nctsDocumentLanguage;
	private String			totalNumberPositions;
	private String			totalNumberPackages;
	private String			totalGrossMass;
	private String			declarationDate;
	private String			declarationPlace;
	private String			situationCode;
	private String			paymentType;
	private String			securityIndicator;
	private String			conveyanceNumber;
	private String			placeOfUnloading;
	private PartyNCTS           consignor;        //TIN, Address and ContactPerson
	private TIN				consignorTIN;
	private PartyNCTS           consignee;		  //TIN, Address and ContactPerson
	private TIN				consigneeTIN;
	private PartyNCTS           authorisedConsignee;		 
	private TIN				authorisedConsigneeTIN;
	private PartyNCTS           principal;		  //TIN, Address and ContactPerson
	private TIN				principalTIN;
	private String			carnetID;	
	private ContactPerson 	clerk;
	private String			officeOfDeparture;
	private List<OfficeOfTransit> officeOfTransitList;
	private String			officeOfDestination;
	private String			controlResultCode;
	private String			controlResultDateLimit;
	private String			simplifiedProcedureIndicator;
	private String			representativeName;
	private String			representativeCapacity;
	private String			totalNumberOfSeals;
	private Seal			seals;	
	private List<Guarantee>	guaranteeListH;		// Header Guarantees renamed C.K. 06.02.2012
	private TransportationRoute transRoute;
	private List<String>    itineraryList; 	//UIDS
	private String			itinerarySuspensionFlag;                   //UIDS
	//EI20110523: private Party	carrierTIN;
	private TIN				carrierTIN;             //EI20110523
	private PartyNCTS			carrier;                //EI20110523
	private Security		security;
	private PartyNCTS			consignorSecurity;
	private TIN				consignorSecurityTIN;   //EI20110523
	private PartyNCTS			consigneeSecurity;
	private TIN				consigneeSecurityTIN;   //EI20110523
	private String          identificationCode;		     //EI20110517	
	private String          bondedWareHouseRefNum;		 //EI20110517
	private String          bondedWarehousePermitNumber; //EI20110517
	
	private List<MsgNCTSDeclarationPos>	goodsItemList = new ArrayList<MsgNCTSDeclarationPos>();
	
	
	private EFormat			controlResultDateLimitFormat;
	private EFormat         declarationDateFormat;
	
	public MsgNCTSDeclaration() {
		super();
	}
	public MsgNCTSDeclaration(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum ENCTSDeclaration {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		ShipmentNumber,					CommercialReferenceNumber,
		AuthorisationNumber,			AuthorisationID,
		TypeOfDeclaration,				//same
		PlaceOfLoading,					//not same: KIDS-complexTag, UIDS simpleTag "PlaceOfLoading"		
										AgreedLocationOfGoods,			
										AgreedLocationOfGoodsCode,		
										AuthorizedLocationOfGoods,		
		DestinationCountry,				CountryOfDestination,
		DispatchCountry,				CountryOfDispatch,
		CustomsSubPlace,				//same
		MeansOfTransportInland,			InlandTransport,
		MeansOfTransportBorder,			TransportAtBorder,
		MeansOfTransportDeparture,		TransportAtDeparture,
		MeansOfTransportCrossingBorder,	//---
		TransportInContainer,			ContainerIndicator,
		NCTSDocumentLanguage,			//same
		TotalNumberPositions,			TotalNumberOfItems,
		TotalNumberPackages,			TotalNumberOfPackages,
		TotalGrossMass,					//same
		DeclarationDate,				//same
		DeclarationPlace,				//same
		SituationCode,					SpecificCircumstanceIndicator,
	    PaymentType,					TransportMethodOfPayment,
	    SecurityIndicator,				//same
	    ConveyanceNumber,				//same
	    PlaceOfUnLoading,				PlaceOfUnloading,				
	    ConsignorTIN,					Consignor, //.TIN
	    ConsigneeTIN,					Consignee, //.TIN
	    PrincipalTIN,					Principal, //.TIN
	    CarnetID,						//same
	    AuthorisedConsigneeTIN,			AuthorisedConsignee, //.TIN
	    Clerk,							//same
	    OfficeOfDeparture,				//same
	    OfficeOfTransit,				//same
		OfficeOfDestination,			//same
		
		ControlResultCode,				//same
		ControlResultDateLimit,			PresentationTime, //EI20110620: no (more) in xsd
		SimplifiedProcedureIndicator,	SimplifiedProcedureFlag,
		RepresentativeName,				//same
		RepresentativeCapacity,			//same
		TotalNumberOfSeals,				//same
		Seals,							TydenSeals,
										SealsType,
										SealsIdentity,
		Guarantee,						//same														
		TransportationRoute,			Itinerary,	ItinerarySuspensionFlag,										
		IdentificationCode,             //same	
		BondedWareHouseRefNum,			//same
		BondedWarehouseRefNum,			//same
		BondedWarehousePermitNumber,    BondedWarehouseAuthorisationID,
		BondedWareHousePermitNumber,    BondedWareHouseAuthorisationID,
		CarrierTIN,						Carrier,	
		Security,						//same	
		ConsignorSecurity,				ConsignorSecurityTIN,
		ConsigneeSecurity,				ConsigneeSecurityTIN,
		GoodsItem;						//same
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENCTSDeclaration) tag) {
			case PlaceOfLoading:
				placeOfLoading = new PlaceOfLoading(getScanner());
				placeOfLoading.parse(tag.name());
				break;
			case MeansOfTransportInland:
			case InlandTransport:
				meansOfTransportInland = new TransportMeans(getScanner());
				meansOfTransportInland.parse(tag.name());
				break;
			case MeansOfTransportBorder:
			case TransportAtBorder:
				meansOfTransportBorder = new TransportMeans(getScanner());
				meansOfTransportBorder.parse(tag.name());
				break;
			case MeansOfTransportDeparture:
			case TransportAtDeparture:
				meansOfTransportDeparture = new TransportMeans(getScanner());
				meansOfTransportDeparture.parse(tag.name());
				break;
			case MeansOfTransportCrossingBorder:
				meansOfTransportCrossingBorder = new TransportMeans(getScanner());
				meansOfTransportCrossingBorder.parse(tag.name());
				break;
			case Consignor:		
				consignor = new PartyNCTS(getScanner());
				consignor.parse(tag.name());	
				if (consignor.getPartyTIN() != null) {
					setConsignorTIN(consignor.getPartyTIN());
				}
				break;				
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());
				break;
			case Consignee:
				consignee = new PartyNCTS(getScanner());
				consignee.parse(tag.name());	
				if (consignee.getPartyTIN() != null) {
					setConsigneeTIN(consignee.getPartyTIN());
				}
				break;
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());				
				break;
			case Principal:
				principal = new PartyNCTS(getScanner());
				principal.parse(tag.name());	
				if (principal.getPartyTIN() != null) {
					setPrincipalTIN(principal.getPartyTIN());
				}
				break;
			case PrincipalTIN:
				principalTIN = new TIN(getScanner());
				principalTIN.parse(tag.name());				
				break;
			case AuthorisedConsignee:
				authorisedConsignee = new PartyNCTS(getScanner());
				authorisedConsignee.parse(tag.name());	
				if (authorisedConsignee.getPartyTIN() != null) {
					setAuthorisedConsigneeTIN(authorisedConsignee.getPartyTIN());
				}
				break;
			case AuthorisedConsigneeTIN:
				authorisedConsigneeTIN = new TIN(getScanner());
				authorisedConsigneeTIN.parse(tag.name());				
				break;	
			case Clerk:
				clerk = new ContactPerson(getScanner());
				clerk.parse(tag.name());
				break;
			case OfficeOfTransit:
				OfficeOfTransit transit = new OfficeOfTransit(getScanner());
				transit.parse(tag.name());
				if (officeOfTransitList == null) {
					officeOfTransitList = new Vector<OfficeOfTransit>();
				}
				officeOfTransitList.add(transit);
				break;
			case Seals:
			case TydenSeals:
				seals = new Seal(getScanner());
				seals.parse(tag.name());
				break;
			case Guarantee:
				Guarantee guarantee = new Guarantee(getScanner());
				guarantee.parse(tag.name());
				if (guaranteeListH == null) {
					guaranteeListH = new ArrayList<Guarantee>();
				}
				guaranteeListH.add(guarantee);
				break;
			case TransportationRoute:
				transRoute = new TransportationRoute(getScanner());
				transRoute.parse(tag.name());
				break;
				/* EI20110523:
			case CarrierTIN:
			case Carrier:
				carrierTIN = new Party(getScanner());
				carrierTIN.parse(tag.name());				
				break;	
				*/
			case Carrier:
				carrier = new PartyNCTS(getScanner());
				carrier.parse(tag.name());	
				if (carrier.getPartyTIN() != null) {
					carrierTIN = carrier.getPartyTIN();
				}
				break;
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());				
				break;	
			case Security:
				security = new Security(getScanner());
				security.parse(tag.name());
				break;
				/* EI20110523:
			case ConsignorSecurity:	
			case ConsignorSecurityTIN:
				consignorSecurity = new Party(getScanner());
				consignorSecurity.parse(tag.name());
				break;
				*/
			case ConsignorSecurity:    
				consignorSecurity = new PartyNCTS(getScanner());
				consignorSecurity.parse(tag.name());	
				if (consignorSecurity.getPartyTIN() != null) {
					consignorSecurityTIN = consignorSecurity.getPartyTIN();
				}				
				break;
			case ConsignorSecurityTIN:
				consignorSecurityTIN = new TIN(getScanner());
				consignorSecurityTIN.parse(tag.name());				
				break;
				/* EI20110523:
			case ConsigneeSecurity:	
			case ConsigneeSecurityTIN:
				consigneeSecurity = new Party(getScanner());
				consigneeSecurity.parse(tag.name());
				break;
				*/
			case ConsigneeSecurity:
				consigneeSecurity = new PartyNCTS(getScanner());
				consigneeSecurity.parse(tag.name());	
				if (consigneeSecurity.getPartyTIN() != null) {
					consigneeSecurityTIN = consigneeSecurity.getPartyTIN();
				}
				break;
			case ConsigneeSecurityTIN:
				consigneeSecurityTIN = new TIN(getScanner());
				consigneeSecurityTIN.parse(tag.name());				
				break;
			case GoodsItem:
				MsgNCTSDeclarationPos goodsItem = new MsgNCTSDeclarationPos(getScanner(), msgName);
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
				
			default:
				return;
			}
		} else {
			switch ((ENCTSDeclaration) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case ShipmentNumber:
			case CommercialReferenceNumber:
				setShipmentNumber(value);
				break;
			case AuthorisationNumber:
			case AuthorisationID:
				setAuthorisationNumber(value);
				break;
			case TypeOfDeclaration:
				setTypeOfDeclaration(value);
				break;
			case PlaceOfLoading:
				//setPlaceOfLoadingCode(value);
				if (placeOfLoading == null) {
					placeOfLoading = new PlaceOfLoading();					
				}
				placeOfLoading.setCode(value);
				break;
			case AgreedLocationOfGoods:
				//setAgreedLocationOfGoods(value);
				if (placeOfLoading == null) {
					placeOfLoading = new PlaceOfLoading();					
				}
				placeOfLoading.setAgreedLocationOfGoods(value);
				break;
			case AgreedLocationOfGoodsCode:
				//setAgreedLocationOfGoodsCode(value);
				if (placeOfLoading == null) {
					placeOfLoading = new PlaceOfLoading();					
				}
				placeOfLoading.setAgreedLocationOfGoodsCode(value);
				break;
			//case AuthorisedLocationOfGoodsCode:
			case AuthorizedLocationOfGoods:
				//setAuthorisedLocationOfGoodsCode(value);
				if (placeOfLoading == null) {
					placeOfLoading = new PlaceOfLoading();					
				}
				placeOfLoading.setAuthorisedLocationOfGoodsCode(value);
				break;
			case DestinationCountry:
			case CountryOfDestination:
				setDestinationCountry(value);
				break;
			case DispatchCountry:
			case CountryOfDispatch:
				setDispatchCountry(value);
				break;
			case CustomsSubPlace:
				setCustomsSubPlace(value);
				break;
			case TransportInContainer:
			case ContainerIndicator:
				setTransportInContainer(value);
				break;
			case NCTSDocumentLanguage:
				setNctsDocumentLanguage(value);
				break;
			case TotalNumberPositions:
			case TotalNumberOfItems:
				setTotalNumberPositions(value);
				break;
			case TotalNumberPackages:
			case TotalNumberOfPackages:
				setTotalNumberPackages(value);
				break;
			case TotalGrossMass:
				setTotalGrossMass(value);
				break;			
			case DeclarationDate:
				setDeclarationDate(value);				
				if (value.indexOf('-') == -1) {
					setDeclarationDateFormat(EFormat.KIDS_Date);
				} else {
					setDeclarationDateFormat(EFormat.ST_Date);
				}
				break;
			case DeclarationPlace:
				setDeclarationPlace(value);
				break;
			case SituationCode:
			case SpecificCircumstanceIndicator:
				setSituationCode(value);
				break;
			case PaymentType:
			case TransportMethodOfPayment:
				setPaymentType(value);
				break;
			case SecurityIndicator:
				setSecurityIndicator(value);
				break;
			case ConveyanceNumber:
				setConveyanceNumber(value);
				break;
			case PlaceOfUnloading:
			case PlaceOfUnLoading:
				setPlaceOfUnloading(value);
				break;
			case CarnetID:
				setCarnetID(value);
				break;
			case OfficeOfDeparture:
				setOfficeOfDeparture(value);
				break;
			case OfficeOfDestination:
				setOfficeOfDestination(value);
				break;
			case ControlResultCode:
				setControlResultCode(value);
				break;
			case ControlResultDateLimit:
			case PresentationTime:		
				setControlResultDateLimit(value);
				if (value.indexOf('-') == -1) {
					setControlResultDateLimitFormat(EFormat.KIDS_Date);
				} else {					 
					setControlResultDateLimitFormat(EFormat.ST_Date);
				}				 			
				break;
			case SimplifiedProcedureIndicator:
			case SimplifiedProcedureFlag:
				setSimplifiedProcedureIndicator(value);
				break;
			case RepresentativeName:
				setRepresentativeName(value);
				break;
			case RepresentativeCapacity:
				setRepresentativeCapacity(value);
				break;
			case TotalNumberOfSeals:
				setTotalNumberOfSeals(value);
				break;
			case SealsIdentity:
				if (seals == null) {
					seals = new Seal();
				}
				SealNumber sealNumber = new SealNumber();
				sealNumber.setNumber(value);
				seals.addSealNumberList(sealNumber);
				break;
			case SealsType:
				if (seals == null) {
					seals = new Seal();
				}
				seals.setKind(value);
				break;
			case Itinerary:	
				if (itineraryList == null) {
					itineraryList = new ArrayList<String>();
				}
				itineraryList.add(value);
				break;
			case ItinerarySuspensionFlag:
				this.itinerarySuspensionFlag = value;				
				break;
			case IdentificationCode:
				setIdentificationCode(value);
				break;
			case BondedWarehouseRefNum:
			case BondedWareHouseRefNum:
				setBondedWareHouseRefNum(value);
				break;
			case BondedWarehouseAuthorisationID:
			case BondedWarehousePermitNumber:
			case BondedWareHouseAuthorisationID:
			case BondedWareHousePermitNumber:
				setBondedWarehousePermitNumber(value);
				break;
			default:
				return;	
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return ENCTSDeclaration.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}
	public String getAuthorisationNumber() {
		return authorisationNumber;
	}
	public void setAuthorisationNumber(String authorisationNumber) {
		this.authorisationNumber = authorisationNumber;
	}
	public String getTypeOfDeclaration() {
		return typeOfDeclaration;
	}
	public void setTypeOfDeclaration(String typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}
	/*
	public String getPlaceOfLoadingCode() {
		return placeOfLoadingCode;
	}
	public void setPlaceOfLoadingCode(String placeOfLoadingCode) {
		this.placeOfLoadingCode = placeOfLoadingCode;
	}
	public String getAgreedLocationOfGoods() {
		return agreedLocationOfGoods;
	}
	public void setAgreedLocationOfGoods(String agreedLocationOfGoods) {
		this.agreedLocationOfGoods = agreedLocationOfGoods;
	}
	public String getAgreedLocationOfGoodsCode() {
		return agreedLocationOfGoodsCode;
	}
	public void setAgreedLocationOfGoodsCode(String agreedLocationOfGoodsCode) {
		this.agreedLocationOfGoodsCode = agreedLocationOfGoodsCode;
	}
	public String getAuthorisedLocationOfGoodsCode() {
		return authorisedLocationOfGoodsCode;
	}
	public void setAuthorisedLocationOfGoodsCode(String authorisedLocationOfGoodsCode) {
		this.authorisedLocationOfGoodsCode = authorisedLocationOfGoodsCode;
	}
	*/
	public PlaceOfLoading getPlaceOfLoading() {
		return placeOfLoading;
	}
	public void setPlaceOfLoading(PlaceOfLoading placeOfLoading) {
		this.placeOfLoading = placeOfLoading;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	public String getDispatchCountry() {
		return dispatchCountry;
	}
	public void setDispatchCountry(String dispatchCountry) {
		this.dispatchCountry = dispatchCountry;
	}
	public String getCustomsSubPlace() {
		return customsSubPlace;
	}
	public void setCustomsSubPlace(String customsSubPlace) {
		this.customsSubPlace = customsSubPlace;
	}
	public TransportMeans getMeansOfTransportInland() {
		return meansOfTransportInland;
	}
	public void setMeansOfTransportInland(TransportMeans meansOfTransportInland) {
		this.meansOfTransportInland = meansOfTransportInland;
	}
	public TransportMeans getMeansOfTransportBorder() {
		return meansOfTransportBorder;
	}
	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}
	public TransportMeans getMeansOfTransportDeparture() {
		return meansOfTransportDeparture;
	}
	public void setMeansOfTransportDeparture(
			TransportMeans meansOfTransportDeparture) {
		this.meansOfTransportDeparture = meansOfTransportDeparture;
	}
	public TransportMeans getMeansOfTransportCrossingBorder() {
		return meansOfTransportCrossingBorder;
	}
	public void setMeansOfTransportCrossingBorder(
			TransportMeans meansOfTransportCrossingBorder) {
		this.meansOfTransportCrossingBorder = meansOfTransportCrossingBorder;
	}
	public String getTransportInContainer() {
		return transportInContainer;
	}
	public void setTransportInContainer(String transportInContainer) {
		this.transportInContainer = transportInContainer;
	}
	public String getNctsDocumentLanguage() {
		return nctsDocumentLanguage;
	}
	public void setNctsDocumentLanguage(String nctsDocumentLanguage) {
		this.nctsDocumentLanguage = nctsDocumentLanguage;
	}
	public String getTotalNumberPositions() {
		return totalNumberPositions;
	}
	public void setTotalNumberPositions(String totalNumberPositions) {
		this.totalNumberPositions = totalNumberPositions;
	}
	public String getTotalNumberPackages() {
		return totalNumberPackages;
	}
	public void setTotalNumberPackages(String totalNumberPackages) {
		this.totalNumberPackages = totalNumberPackages;
	}
	public String getTotalGrossMass() {
		return totalGrossMass;
	}
	public void setTotalGrossMass(String totalGrossMass) {
		this.totalGrossMass = totalGrossMass;
	}
	public String getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(String declarationDate) {
		this.declarationDate = declarationDate;
	}
	public String getDeclarationPlace() {
		return declarationPlace;
	}
	public void setDeclarationPlace(String declarationPlace) {
		this.declarationPlace = declarationPlace;
	}
	
	public String getSituationCode() {
		return situationCode;
	}
	public void setSituationCode(String situationCode) {
		this.situationCode = situationCode;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getSecurityIndicator() {
		return securityIndicator;
	}
	public void setSecurityIndicator(String securityIndicator) {
		this.securityIndicator = securityIndicator;
	}
	public String getConveyanceNumber() {
		return conveyanceNumber;
	}
	public void setConveyanceNumber(String conveyanceNumber) {
		this.conveyanceNumber = conveyanceNumber;
	}
	public String getPlaceOfUnloading() {
		return placeOfUnloading;
	}
	public void setPlaceOfUnloading(String placeOfUnloading) {
		this.placeOfUnloading = placeOfUnloading;
	}
	public PartyNCTS getConsignor() {			
		return consignor;
	}
	public void setConsignor(PartyNCTS consignor) {
		this.consignor = consignor;
	}
	public TIN getConsignorTIN() {
		return consignorTIN;
	}
	public void setConsignorTIN(TIN consignorTIN) {
		this.consignorTIN = consignorTIN;
	}
	public PartyNCTS getConsignee() {
		return consignee;
	}
	public void setConsignee(PartyNCTS consignee) {
		this.consignee = consignee;
	}
	public TIN getConsigneeTIN() {
		return consigneeTIN;
	}
	public void setConsigneeTIN(TIN consigneeTIN) {
		this.consigneeTIN = consigneeTIN;
	}
	public PartyNCTS getPrincipal() {
		return principal;
	}
	public void setPrincipal(PartyNCTS principal) {
		this.principal = principal;
	}
	public TIN getPrincipalTIN() {
		return principalTIN;
	}
	public void setPrincipalTIN(TIN principalTIN) {
		this.principalTIN = principalTIN;
	}
	public String getCarnetID() {
		return carnetID;
	}
	public void setCarnetID(String carnetID) {
		this.carnetID = carnetID;
	}
	public PartyNCTS getAuthorisedConsignee() {
		return authorisedConsignee;
	}
	public void setAuthorisedConsignee(PartyNCTS authorisedConsignee) {
		this.authorisedConsignee = authorisedConsignee;
	}
	public TIN getAuthorisedConsigneeTIN() {
		return authorisedConsigneeTIN;
	}
	public void setAuthorisedConsigneeTIN(TIN authorisedConsigneeTIN) {
		this.authorisedConsigneeTIN = authorisedConsigneeTIN;
	}
	public ContactPerson getClerk() {
		return clerk;
	}
	public void setClerk(ContactPerson clerk) {
		this.clerk = clerk;
	}
	public String getOfficeOfDeparture() {
		return officeOfDeparture;
	}
	public void setOfficeOfDeparture(String officeOfDeparture) {
		this.officeOfDeparture = officeOfDeparture;
	}
	public EFormat getDeclarationDateFormat() {
		return declarationDateFormat;
	}
	public void setDeclarationDateFormat(EFormat declarationDateFormat) {
		this.declarationDateFormat = declarationDateFormat;
	}

	public List<OfficeOfTransit> getOfficeOfTransitList() {
		return officeOfTransitList;
	}
	public void setOfficeOfTransitList(List<OfficeOfTransit> officeOfTransitList) {
		this.officeOfTransitList = officeOfTransitList;
	}
	public String getOfficeOfDestination() {
		return officeOfDestination;
	}
	public void setOfficeOfDestination(String officeOfDestination) {
		this.officeOfDestination = officeOfDestination;
	}
	public String getControlResultCode() {
		return controlResultCode;
	}
	public void setControlResultCode(String controlResultCode) {
		this.controlResultCode = controlResultCode;
	}
	public String getControlResultDateLimit() {
		return controlResultDateLimit;
	}
	public void setControlResultDateLimit(String controlResultDateLimit) {
		this.controlResultDateLimit = controlResultDateLimit;
	}
	public String getSimplifiedProcedureIndicator() {
		return simplifiedProcedureIndicator;
	}
	public void setSimplifiedProcedureIndicator(String simplifiedProcedureIndicator) {
		this.simplifiedProcedureIndicator = simplifiedProcedureIndicator;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getRepresentativeCapacity() {
		return representativeCapacity;
	}
	public void setRepresentativeCapacity(String representativeCapacity) {
		this.representativeCapacity = representativeCapacity;
	}
	public String getTotalNumberOfSeals() {
		return totalNumberOfSeals;
	}
	public void setTotalNumberOfSeals(String totalNumberOfSeals) {
		this.totalNumberOfSeals = totalNumberOfSeals;
	}
	public EFormat getControlResultDateLimitFormat() {
		return controlResultDateLimitFormat;
	}
	public void setControlResultDateLimitFormat(EFormat controlResultDateLimitFormat) {
		this.controlResultDateLimitFormat = controlResultDateLimitFormat;
	}
	public Seal getSeals() {
		return seals;
	}
	public void setSeals(Seal seals) {
		this.seals = seals;
	}
	public List<Guarantee> getGuaranteeListH() {
		return guaranteeListH;
	}
	public void setGuaranteeListH(List<Guarantee> guaranteeListH) {
		this.guaranteeListH = guaranteeListH;
	}
	/*
	public List<String> getItineraryList() {
		return itineraryList;
	}
	public void setItineraryList(List<String> itineraryList) {
		this.itineraryList = itineraryList;
	}
	*/
	public TransportationRoute getTransRoute() {
		if (itineraryList != null) { //EI20110524
			if (transRoute == null) {
				transRoute = new TransportationRoute();
			}
			transRoute.setCountryList(itineraryList);
			transRoute.setSuspensionIndicator(itinerarySuspensionFlag);
		}
		return transRoute;
	}
	public void setTransRoute(TransportationRoute transRoute) {
		this.transRoute = transRoute;
	}
	/*
	public String getSuspensionIndicator() {
		return suspensionIndicator;
	}
	public void setSuspensionIndicator(String suspensionIndicator) {
		this.suspensionIndicator = suspensionIndicator;
	}
	*/
	public PartyNCTS getCarrier() {
		return carrier;
	}
	public void setCarrier(PartyNCTS carrier) {
		this.carrier = carrier;
	}
	public TIN getCarrierTIN() {
		return carrierTIN;
	}
	public void setCarrierTIN(TIN carrierTIN) {
		this.carrierTIN = carrierTIN;
	}
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	public PartyNCTS getConsignorSecurity() {
		return consignorSecurity;
	}
	public void setConsignorSecurity(PartyNCTS consignorSecurity) {
		this.consignorSecurity = consignorSecurity;
	}
	
	public TIN getConsignorSecurityTIN() {
		return consignorSecurityTIN;
	}
	public void setConsignorSecurityTIN(TIN tIN) {
		this.consignorSecurityTIN = tIN;
	}
	
	public PartyNCTS getConsigneeSecurity() {
		return consigneeSecurity;
	}
	public void setConsigneeSecurity(PartyNCTS consigneeSecurity) {
		this.consigneeSecurity = consigneeSecurity;
	}
	public TIN getConsigneeSecurityTIN() {
		return consigneeSecurityTIN;
	}
	public void setConsigneeSecurityTIN(TIN tIN) {
		this.consigneeSecurityTIN = tIN;
	}
	
	public void addGoodsItemList(MsgNCTSDeclarationPos argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<MsgNCTSDeclarationPos>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	public List<MsgNCTSDeclarationPos> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(List<MsgNCTSDeclarationPos> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
	
	public String getIdentificationCode() {
		return identificationCode;
	}
	public void setIdentificationCode(String argument) {
		this.identificationCode = argument;
	}
	
	public String getBondedWareHouseRefNum() {
		return bondedWareHouseRefNum;
	}
	public void setBondedWareHouseRefNum(String argument) {
		this.bondedWareHouseRefNum = argument;
	}	
	
	public String getBondedWarehousePermitNumber() {
		return bondedWarehousePermitNumber;
	}
	public void setBondedWarehousePermitNumber(String argument) {
		this.bondedWarehousePermitNumber = argument;
	}	


}
