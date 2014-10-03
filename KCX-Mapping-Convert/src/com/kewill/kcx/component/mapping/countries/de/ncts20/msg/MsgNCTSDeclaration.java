package com.kewill.kcx.component.mapping.countries.de.ncts20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportationRoute;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul2		: NCTS OUT<br>.
 * Created		: 14.11.2012<br>
 * Description	: KIDS message NCTSDeclaration
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class MsgNCTSDeclaration extends KCXMessage { 
	
	private String			msgName = "NCTSDeclaration";
	private String			referenceNumber;
	private String			shipmentNumber;
	private String			authorisationNumber;
	private String			typeOfDeclaration;	
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
	private PartyNCTS       consignor;        //TIN, Address and ContactPerson
	private TIN				consignorTIN;
	private ContactPerson	consignorContact;    //V70
	private PartyNCTS       consignee;		  //TIN, Address and ContactPerson
	private TIN				consigneeTIN;
	private ContactPerson	consigneeContact;    //V70
	private PartyNCTS       authorisedConsignee;		 
	private TIN				authorisedConsigneeTIN;
	private ContactPerson	authorisedConsigneeContact;    //V70 ???EI20130325: brauche ich das?
	private PartyNCTS       principal;		  //TIN, Address and ContactPerson
	private TIN				principalTIN;
	private ContactPerson	principalContact;    //V70
	private String			principalIsCarrier;  //V70
	private PartyNCTS		carrier;                
	private TIN				carrierTIN;
	private ContactPerson	carrierContact;    //V70
	private Security		security;
	private PartyNCTS		consignorSecurity;
	private TIN				consignorSecurityTIN; 
	private ContactPerson	consignorSecurityContact;    //V70
	private PartyNCTS		consigneeSecurity;
	private TIN				consigneeSecurityTIN;  
	private ContactPerson	consigneeSecurityContact;    //V70
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
	private List<Guarantee>	guaranteeListH;		
	private TransportationRoute transRoute;
	private List<String>    itineraryList;           //UIDS
	private String			itinerarySuspensionFlag; //UIDS		
	private String          identificationCode;		     	
	private String          bondedWareHouseRefNum;		 
	private String          bondedWarehousePermitNumber; 
	
	private Traders traders;   //EI20130422: only BDP
	
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
		GrossMass,   //BDP
		DeclarationDate,				//same
		DeclarationPlace,				//same
		PlaceOfDeclaration,    //BDP 
		SituationCode,					SpecificCircumstanceIndicator,
	    PaymentType,					TransportMethodOfPayment,
	    SecurityIndicator,				//same
	    ConveyanceNumber,				//same
	    PlaceOfUnLoading,				PlaceOfUnloading,				
	    ConsignorTIN,					Consignor, 
	    ConsignorAddress,
	    ConsignorContact,
	    ConsigneeTIN,					Consignee, 
	    ConsigneeAddress,
	    ConsigneeContact,
	    PrincipalTIN,					Principal, 
	    PrincipalAddress,
	    PrincipalContact,
	    PrincipalIsCarrier,             //same 
	    CarnetID,						//same
	    AuthorisedConsigneeTIN,			AuthorisedConsignee, 
	    AuthorisedConsigneeAddress,
	    AuthorisedConsigneeContact,
	    Clerk,	Contact,				//same
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
		CarrierAddress,
		CarrierContact,
		Security,						//same	
		ConsignorSecurity,				ConsignorSecurityTIN,
		ConsignorSecurityAddress,
	    ConsignorSecurityContact,
		ConsigneeSecurity,				ConsigneeSecurityTIN,
		ConsigneeSecurityAddress,
	    ConsigneeSecurityContact,
		GoodsItem,						//same
		
		Traders,       //only for BDP
		ContactPerson,
		CustomsOfficeDeclaration , CustomsOfficeEntry, IntendedOfficeOfExit, //fuer BDP
		CustomsOfficeOfDeclaration,
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
			case ConsignorAddress: 
				consignor = new PartyNCTS(getScanner());
				consignor.parse(tag.name());					
				break;				
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());
				break;
			case ConsignorContact:   
				consignorContact = new ContactPerson(getScanner());
				consignorContact.parse(tag.name());
				break;			
			case Consignee:
			case ConsigneeAddress: 
				consignee = new PartyNCTS(getScanner());
				consignee.parse(tag.name());					
				break;
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());				
				break;
			case ConsigneeContact:   
				consigneeContact = new ContactPerson(getScanner());
				consigneeContact.parse(tag.name());
				break;	
			case Principal:
			case PrincipalAddress: 
				principal = new PartyNCTS(getScanner());
				principal.parse(tag.name());					
				break;
			case PrincipalTIN:
				principalTIN = new TIN(getScanner());
				principalTIN.parse(tag.name());				
				break;
			case PrincipalContact:   
				principalContact = new ContactPerson(getScanner());
				principalContact.parse(tag.name());
				break;	
			case AuthorisedConsignee:
			case AuthorisedConsigneeAddress: 
				authorisedConsignee = new PartyNCTS(getScanner());
				authorisedConsignee.parse(tag.name());					
				break;
			case AuthorisedConsigneeTIN:
				authorisedConsigneeTIN = new TIN(getScanner());
				authorisedConsigneeTIN.parse(tag.name());				
				break;
			case AuthorisedConsigneeContact:   
				authorisedConsigneeContact = new ContactPerson(getScanner());
				authorisedConsigneeContact.parse(tag.name());
				break;	
			case Clerk:	
			case Contact:   				  //EI20130823
			case ContactPerson:               //EI20130422 BDP
				clerk = new ContactPerson(getScanner());
				clerk.parse(tag.name());
				break;
			case OfficeOfTransit:
				OfficeOfTransit transit = new OfficeOfTransit(getScanner());
				transit.parse(tag.name());				
				addOfficeOfTransitList(transit);
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
			
			case Carrier:
			case CarrierAddress: 
				carrier = new PartyNCTS(getScanner());
				carrier.parse(tag.name());					
				break;
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());				
				break;	
			case CarrierContact:   
				carrierContact = new ContactPerson(getScanner());
				carrierContact.parse(tag.name());
				break;	
			case Security:
				security = new Security(getScanner());
				security.parse(tag.name());
				break;
			
			case ConsignorSecurity:   
			case ConsignorSecurityAddress: 
				consignorSecurity = new PartyNCTS(getScanner());
				consignorSecurity.parse(tag.name());							
				break;
			case ConsignorSecurityTIN:
				consignorSecurityTIN = new TIN(getScanner());
				consignorSecurityTIN.parse(tag.name());				
				break;
			case ConsignorSecurityContact:   
				consignorSecurityContact = new ContactPerson(getScanner());
				consignorSecurityContact.parse(tag.name());
				break;	
			
			case ConsigneeSecurity:
			case ConsigneeSecurityAddress: 
				consigneeSecurity = new PartyNCTS(getScanner());
				consigneeSecurity.parse(tag.name());					
				break;
			case ConsigneeSecurityTIN:
				consigneeSecurityTIN = new TIN(getScanner());
				consigneeSecurityTIN.parse(tag.name());				
				break;
			case ConsigneeSecurityContact:   
				consigneeSecurityContact = new ContactPerson(getScanner());
				consigneeSecurityContact.parse(tag.name());
				break;	
			case GoodsItem:
				MsgNCTSDeclarationPos goodsItem = new MsgNCTSDeclarationPos(getScanner(), msgName);
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
				
			case Traders:   //EI20130422: only BDP
				traders = new Traders(getScanner());
				traders.parse(tag.name());
				this.setBdpTraders(traders);
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
			case GrossMass:              //BDP
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
			case PlaceOfDeclaration:    //BDP
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
			case CustomsOfficeDeclaration:      //BDP 
			case CustomsOfficeOfDeclaration:      //BDP 
				setOfficeOfDeparture(value);
				break;
			case OfficeOfDestination:
			case CustomsOfficeEntry:     //BDP
				setOfficeOfDestination(value);
				break;
				
			case IntendedOfficeOfExit:  //BDP = OfficeOfTransit ist aber CT_OfficeOfTransit
				setIntendedOfficeOfExit(value);
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
			case PrincipalIsCarrier:
				setPrincipalIsCarrier(value);
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
		if (!Utils.isStringEmpty(declarationDate) && declarationDate.length() > 8) {  //EI20130422 for BDP
			this.declarationDate = declarationDate.substring(0, 8);
		}
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
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new PartyNCTS();
			}
			consignor.setPartyTIN(consignorTIN);
		}
		if (consignorContact != null) {
			if (consignor == null) {
				consignor = new PartyNCTS();
			}
			consignor.setContactPerson(consignorContact);
		}
		return consignor;
	}
	public void setConsignor(PartyNCTS consignor) {
		this.consignor = consignor;
	}
	
	public PartyNCTS getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				consignee = new PartyNCTS();
			}
			consignee.setPartyTIN(consigneeTIN);
		}
		if (consigneeContact != null) {
			if (consignee == null) {
				consignee = new PartyNCTS();
			}
			consignee.setContactPerson(consigneeContact);
		}		
		return consignee;
	}
	public void setConsignee(PartyNCTS consignee) {
		this.consignee = consignee;
	}
	
	public PartyNCTS getPrincipal() {
		if (principalTIN != null) {
			if (principal == null) {
				principal = new PartyNCTS();
			}
			principal.setPartyTIN(principalTIN);
		}
		if (principalContact != null) {
			if (principal == null) {
				principal = new PartyNCTS();
			}
			principal.setContactPerson(principalContact);
		}	
		return principal;
	}
	public void setPrincipal(PartyNCTS principal) {
		this.principal = principal;
	}
	
	public String getCarnetID() {
		return carnetID;
	}
	public void setCarnetID(String carnetID) {
		this.carnetID = carnetID;
	}
	public PartyNCTS getAuthorisedConsignee() {
		if (authorisedConsigneeTIN != null) {
			if (authorisedConsignee == null) {
				authorisedConsignee = new PartyNCTS();
			}
			authorisedConsignee.setPartyTIN(authorisedConsigneeTIN);
		}
		if (authorisedConsigneeContact != null) {
			if (authorisedConsignee == null) {
				authorisedConsignee = new PartyNCTS();
			}
			authorisedConsignee.setContactPerson(authorisedConsigneeContact);
		}	
		return authorisedConsignee;
	}
	public void setAuthorisedConsignee(PartyNCTS authorisedConsignee) {
		this.authorisedConsignee = authorisedConsignee;
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
	public void addOfficeOfTransitList(OfficeOfTransit transit) {
		if (officeOfTransitList == null) {
			officeOfTransitList = new Vector<OfficeOfTransit>();
		}
		officeOfTransitList.add(transit);
	}
	public void setIntendedOfficeOfExit(String value) {   //EI20130626
		OfficeOfTransit tr = new OfficeOfTransit();
		tr.setCode(value);	
		addOfficeOfTransitList(tr);
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
		if (carrierTIN != null) {
			if (carrier == null) {
				carrier = new PartyNCTS();
			}
			carrier.setPartyTIN(carrierTIN);
		}
		if (carrierContact != null) {
			if (carrier == null) {
				carrier = new PartyNCTS();
			}
			carrier.setContactPerson(carrierContact);
		}	
		return carrier;
	}
	public void setCarrier(PartyNCTS carrier) {
		this.carrier = carrier;
	}
	
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	public PartyNCTS getConsignorSecurity() {
		if (consignorSecurityTIN != null) {
			if (consignorSecurity == null) {
				consignorSecurity = new PartyNCTS();
			}
			consignorSecurity.setPartyTIN(consignorSecurityTIN);
		}
		if (consignorSecurityContact != null) {
			if (consignorSecurity == null) {
				consignorSecurity = new PartyNCTS();
			}
			consignorSecurity.setContactPerson(consignorSecurityContact);
		}	
		return consignorSecurity;
	}
	public void setConsignorSecurity(PartyNCTS consignorSecurity) {
		this.consignorSecurity = consignorSecurity;
	}	
	
	public PartyNCTS getConsigneeSecurity() {
		if (consigneeSecurityTIN != null) {
			if (consigneeSecurity == null) {
				consigneeSecurity = new PartyNCTS();
			}
			consigneeSecurity.setPartyTIN(consigneeSecurityTIN);
		}
		if (consigneeSecurityContact != null) {
			if (consigneeSecurity == null) {
				consigneeSecurity = new PartyNCTS();
			}
			consigneeSecurity.setContactPerson(consigneeSecurityContact);
		}	
		return consigneeSecurity;
	}
	public void setConsigneeSecurity(PartyNCTS consigneeSecurity) {
		this.consigneeSecurity = consigneeSecurity;
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

	public String getPrincipalIsCarrier() {
		return principalIsCarrier;
	}
	public void setPrincipalIsCarrier(String argument) {
		this.principalIsCarrier = argument;
	}	

	private void setBdpTraders(Traders traders) {   //EI20130422
		if (traders == null) {
			return;
		}		
				
		if (traders.getConsigneeTIN() != null) {
			this.consigneeTIN = new TIN();
			this.consigneeTIN.setTIN(traders.getConsigneeTIN().getTIN());
			this.consigneeTIN.setCustomerIdentifier(traders.getConsigneeTIN().getCustomerId());			
			this.consigneeTIN.setBO(traders.getConsigneeTIN().getBO());
			//EI20130802: this.consigneeTIN.setIdentificationType(traders.getConsigneeTIN().getCustomerIdentifier(());  
			this.consigneeTIN.setIdentificationType(traders.getConsigneeTIN().getIdentificationType());  //EI20130802
		}
		if (traders.getConsigneeAddress() != null) {
			this.consignee = new PartyNCTS();
			this.consignee.setAddress(mapAddressToNCTSaddress(traders.getConsigneeAddress()));			
		}
		
		if (traders.getConsignorTIN() != null) {
			this.consignorTIN = new TIN();
			this.consignorTIN.setTIN(traders.getConsignorTIN().getTIN());
			this.consignorTIN.setCustomerIdentifier(traders.getConsignorTIN().getCustomerId());			
			this.consignorTIN.setBO(traders.getConsignorTIN().getBO());
			//EI20130802: this.consignorTIN.setIdentificationType(traders.getConsignorTIN().getCustomerIdentifier());
			this.consignorTIN.setIdentificationType(traders.getConsignorTIN().getIdentificationType());  //EI20130802
		}
		if (traders.getConsignorAddress() != null) {
			this.consignor = new PartyNCTS();
			this.consignor.setAddress(mapAddressToNCTSaddress(traders.getConsignorAddress()));			
		}		
	}
	private AddressNCTS mapAddressToNCTSaddress(Address address) {
		AddressNCTS nctsAddress = new AddressNCTS();
		
		nctsAddress.setTyp(address.getTyp());  
		nctsAddress.setName(address.getName());  
		nctsAddress.setStreet(address.getStreet());  
		nctsAddress.setHouseNumber(address.getHouseNumber());     
		nctsAddress.setCity(address.getCity());     
		nctsAddress.setPostalCode(address.getPostalCode());  
		nctsAddress.setCountry(address.getCountry());  
		nctsAddress.setDistrict(address.getDistrict());  
		nctsAddress.setPOBox(address.getPOBox());  
		nctsAddress.setLanguage(address.getLanguage());  		
		nctsAddress.setCountrySubEntity(address.getCountrySubEntity());  	
		
		return nctsAddress;
	}
}
