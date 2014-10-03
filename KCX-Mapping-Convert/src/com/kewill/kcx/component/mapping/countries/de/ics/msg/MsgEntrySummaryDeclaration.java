package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ConveyanceCall;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgEntrySummaryDeclaration<br>
 * Erstellt		: 10.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSEntrySummaryDeclaration.
 *                 
 * @author Alfred Krzoska
 * @version 1.0.00
 */

public class MsgEntrySummaryDeclaration extends KCXMessage {

	private String msgName = "ICSEntrySummaryDeclaration";
	private String 			referenceNumber;
	private String 			shipmentNumber;
	private String			mrn;            //EI20110415: mrn darf nicht entfernt werden!!! wird benötigt um Amendment zu füllen
	private String 			totalNumberPositions;
	private String 			totalNumberPackages;
	private String 			totalGrossMass;
	private TransportMeans	meansOfTransportBorder;  // is from ...ics.msg.common!
	private String 			conveyanceReference;
	private String 			loadingPlace;
	private String 			loadingPlaceLng;
	private String 			unloadingPlace;
	private String 			unloadingPlaceLng;
	private String 			situationCode;
	private String 			paymentType;
	private String 			declarationTime;
	private String			declarationPlace;
	private Party           consignor;        //TIN, Address and ContactPerson
	private TIN				consignorTIN;
	private Address			consignorAdr;
	private Party           consignee;		  //TIN, Address and ContactPerson
	private TIN				consigneeTIN;
	private Address			consigneeAdr;
	private Party           notifyParty;      //TIN, Address and ContactPerson
	private TIN				notifyPartyTIN;
	private Address			notifyPartyAdr;
	private Party 			personLodgingSuma; //TIN, Address and ContactPerson
	private TIN				personLodgingSumaTIN;
	private Address			personLodgingSumaAdr;
	private ContactPerson 	personLodgingSumaContact;
	private Party			representative;	
	private TIN				representativeTIN;
	private Address			representativeAdr;
	//private ContactPerson 	representativeContact;
	private Party			carrier;
	private TIN				carrierTIN;
	private Address			carrierAdr;
	private String 			customsOfficeOfLodgment;
	private String			customsOfficeFirstEntry;	
	private String			declaredDateOfArrival;
	private List<String>	customsOfficeOfSubsequentEntryList;  		
	private List<SealNumber> sealIDList;
	private SealNumber 		sealsID;
	private List<String>	countryOfRoutingList;
	private ConveyanceCall 	conveyanceCall;                      //EI20121004 JIRA:KCXSM-34
	private List<GoodsItemLong> goodsItemList;
	
	private EFormat declaredDateOfArrivalFormat;
	private EFormat declarationTimeFormat;
	
	public MsgEntrySummaryDeclaration() {
		super();				
	}

	public MsgEntrySummaryDeclaration(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	private enum EDeclaration {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		ShipmentNumber,                 CommercialReferenceNumber,
		MRN,							//same
		TotalNumberPositions,			TotalNumberOfItems,
		TotalNumberPackages,			TotalNumberOfPackages,
		TotalGrossMass,					//same
		MeansOfTransportBorder,			TransportAtBorder,
		ConveyanceReference,			ConveyanceNumber,
		LoadingPlace,					PlaceOfLoading,
		LoadingPlaceLng,				//---
		UnloadingPlace,					PlaceOfUnloading,
		UnloadingPlaceLng,				//---
		SituationCode,					SepcificCircumstanceIndicator,
		PaymentType,					TransportMethodOfPayment,
		DeclarationTime,				DeclarationDateAndTime,
		DeclarationPlace,				//same
		ConsignorTIN,					Consignor, //.TIN		
		ConsignorAddress,				//Consignor.Address + EntAddress + VatId
		ConsigneeTIN,					Consignee, //.TIN	
		ConsigneeAddress,				//Consignee.Address + EntAddress + VatId
		NotifyPartyTIN,					NotifyParty, //.TIN
		NotifyPartyAddress,				//NotifyParty.Address + EntAddress + VatId
		PersonLodgingSumaTIN,			PersonLodgingSuma, //TIN
		PersonLodgingSumaAddress,		//PersonLodgingSuma.Address + EntAddress + VatId
		PersonLodgingSumaContact,		//PersonLodgingSuma.Contact
		RepresentativeTIN,				Representative, //.TIN		
		RepresentativeAddress,			//Representative.Address + EntAddress + VatId
		//RepresentativeContact,			//Representative.Contact
		CarrierTIN,						EntryCarrier, Carrier, //.TIN		
		CarrierAddress,					//Carrier.Address + EntAddress + VatId
		CustomsOfficeOfLodgement,		OfficeOfLodgement,
		CustomsOfficeFirstEntry,		OfficeOfFirstEntry,
		DeclaredDateOfArrival,			ExpectedArivalDateAndTime,
		CustomsOfficeOfSubsequentEntry, OfficeOfSubsequentEntry,
		SealsID,						SealsIdentity,
		SealsIdentityLng,				// entfällt - not applicalble
		CountryOfRouting,				Itinerary,
		ConveyanceCall, 
		GoodsItem;						//same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EDeclaration) tag) {
			case MeansOfTransportBorder:
			case TransportAtBorder:
				meansOfTransportBorder = new TransportMeans(getScanner());
				meansOfTransportBorder.parse(tag.name());				
				break;
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());	
				break;
			case ConsignorAddress:
				consignorAdr = new Address(getScanner());
				consignorAdr.parse(tag.name());
				break;
			case Consignor:		
				Trader consignorTrader = new Trader(getScanner());
				consignorTrader.parse(tag.name());	
				consignor = setPartyFromTrader(consignorTrader);	
				break;
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());		
				break;
			case ConsigneeAddress:
				consigneeAdr = new Address(getScanner());
				consigneeAdr.parse(tag.name());				
				break;
			case Consignee:
				Trader consigneeTrader = new Trader(getScanner());
				consigneeTrader.parse(tag.name());	
				consignee = setPartyFromTrader(consigneeTrader);				
				break;				
			case NotifyPartyTIN:
				notifyPartyTIN = new TIN(getScanner());
				notifyPartyTIN.parse(tag.name());	
				break;
			case NotifyPartyAddress:
				notifyPartyAdr = new Address(getScanner());
				notifyPartyAdr.parse(tag.name());	
				break;
			case NotifyParty:
				Trader notifyPartyTrader = new Trader(getScanner());
				notifyPartyTrader.parse(tag.name());	
				notifyParty = setPartyFromTrader(notifyPartyTrader);
				break;					
			case PersonLodgingSumaTIN:
				personLodgingSumaTIN = new TIN(getScanner());
				personLodgingSumaTIN.parse(tag.name());				
				break;	
			case PersonLodgingSumaAddress:
				personLodgingSumaAdr = new Address(getScanner());
				personLodgingSumaAdr.parse(tag.name());	
				break;
			case PersonLodgingSumaContact:
				personLodgingSumaContact = new ContactPerson(getScanner());
				personLodgingSumaContact.parse(tag.name());				
				break;				
			case PersonLodgingSuma:
				Trader personLodgingSumaTrader = new Trader(getScanner());
				personLodgingSumaTrader.parse(tag.name());	
				personLodgingSuma = setPartyFromTrader(personLodgingSumaTrader);
				break;
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner());
				representativeTIN.parse(tag.name());	
				break;
			case RepresentativeAddress:
				representativeAdr = new Address(getScanner());
				representativeAdr.parse(tag.name());	
				break;			
			case Representative:
				Trader represenstativeTrader = new Trader(getScanner());
				represenstativeTrader.parse(tag.name());	
				representative = setPartyFromTrader(represenstativeTrader);
				break;
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
				carrierAdr = new Address(getScanner());
				carrierAdr.parse(tag.name());	
				break;
			case EntryCarrier:
			case Carrier:
				Trader carrierTrader = new Trader(getScanner());
				carrierTrader.parse(tag.name());	
				carrier = setPartyFromTrader(carrierTrader);
				break;	

			// works only with ONE SealsIdentification, but several are allowed
			// CK 2010-12-30
//			case SealsID:			
//				SealNumber sealsID = new SealNumber(getScanner());
//				sealsID.parse(tag.name());				
//				addSealIDList(sealsID);
//				break;

			case ConveyanceCall:                           //EI20121004
				conveyanceCall = new ConveyanceCall(getScanner());
				conveyanceCall.parse(tag.name());
				break;
				
			case GoodsItem:
				GoodsItemLong goodsItem = 
					new  GoodsItemLong(getScanner(), getMsgName());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EDeclaration) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;	
			case ShipmentNumber:
			case CommercialReferenceNumber:
				 setShipmentNumber(value);
				 break;
			case MRN:
				 setMrn(value);	
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
			case ConveyanceReference:
			case ConveyanceNumber:
				 setConveyanceReference(value);
				 break;					 
			case LoadingPlace:
			case PlaceOfLoading:
				 setLoadingPlace(value);
				 break;					 
			case LoadingPlaceLng:
				 setLoadingPlaceLng(value);
				 break;					 
			case UnloadingPlace:
			case PlaceOfUnloading:
				 setUnloadingPlace(value);
				 break;					 
			case UnloadingPlaceLng:
				 setUnloadingPlaceLng(value);
				 break;	
			case SituationCode:
			case SepcificCircumstanceIndicator:
				setSituationCode(value);
				 break;
			case PaymentType:
			case TransportMethodOfPayment:
				 setPaymentType(value);
				 break;		
			case DeclarationTime:
			case DeclarationDateAndTime:
				 setDeclarationTime(value);				 				
				 if (tag == EDeclaration.DeclarationTime) {
					 setDeclarationTimeFormat(EFormat.KIDS_DateTime);
				 } else {
					//EI20110208: setDeclarationTimeFormat(EFormat.ST_DateTimeT);
					 setDeclarationTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
				 }
				 break;	
			case DeclarationPlace:
				 setDeclarationPlace(value);				 
				 break;
			case CustomsOfficeOfLodgement:
			case OfficeOfLodgement:
				 setCustomsOfficeOfLodgment(value);
				 break;		
			case DeclaredDateOfArrival:
			case ExpectedArivalDateAndTime:
				 setDeclaredDateOfArrival(value);
				 if (tag == EDeclaration.DeclaredDateOfArrival) {
					 setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);
				 } else {
					//EI20110208: setDeclaredDateOfArrivalFormat(EFormat.ST_DateTimeT);
					 setDeclaredDateOfArrivalFormat(getUidsDateAndTimeFormat(value)); //EI20110208
				 }
				 break;						 
			case CustomsOfficeFirstEntry:
			case OfficeOfFirstEntry:
				 setCustomsOfficeFirstEntry(value);
				 break;		
			case CustomsOfficeOfSubsequentEntry:
			case OfficeOfSubsequentEntry:
				if (customsOfficeOfSubsequentEntryList == null) {
					customsOfficeOfSubsequentEntryList = new Vector<String>();
				}
				customsOfficeOfSubsequentEntryList.add(value);
				break;	
			case SealsIdentity:
				sealsID = new SealNumber();
				sealsID.setNumber(value);	
				addSealIDList(sealsID);
				break;
			// CK 2010-12-30
			// add Language in last SealNumber of according list: 
			case SealsIdentityLng:
				sealsID = getLastSealNum();
				if (sealsID != null) {
					sealsID.setLanguage(value);	
					addSealIDList(sealsID);
				}
				break;
			case CountryOfRouting:
			case Itinerary:				
				addCountryOfRoutingList(value);
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
  			return EDeclaration.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;	
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public String getReferenceNumber() {
		return this.referenceNumber;	
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getShipmentNumber() {
		return this.shipmentNumber;	
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}

	public String getTotalNumberPositions() {
		return this.totalNumberPositions;
	}
	public void setTotalNumberPositions(String totalNumberPositions) {
		this.totalNumberPositions = Utils.checkNull(totalNumberPositions);
	}

	public String getTotalNumberPackages() {
		return this.totalNumberPackages;
	}
	public void setTotalNumberPackages(String totalNumberPackages) {
		this.totalNumberPackages = Utils.checkNull(totalNumberPackages);
	}

	public TransportMeans getMeansOfTransportBorder() {
		return this.meansOfTransportBorder;	
	}
	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}

	public String getConveyanceReference() {
		return this.conveyanceReference;	
	}
	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = Utils.checkNull(conveyanceReference);
	}

	public String getLoadingPlace() {
		return this.loadingPlace;	
	}
	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = Utils.checkNull(loadingPlace);
	}

	public String getLoadingPlaceLng() {
		return this.loadingPlaceLng;	
	}
	public void setLoadingPlaceLng(String loadingPlaceLng) {
		this.loadingPlaceLng = Utils.checkNull(loadingPlaceLng);
	}

	public String getUnloadingPlace() {
		return this.unloadingPlace;	
	}
	public void setUnloadingPlace(String unloadingPlace) {
		this.unloadingPlace = Utils.checkNull(unloadingPlace);
	}

	public String getUnloadingPlaceLng() {
		return this.unloadingPlaceLng;	
	}
	public void setUnloadingPlaceLng(String unloadingPlaceLng) {
		this.unloadingPlaceLng = Utils.checkNull(unloadingPlaceLng);
	}

	public String getPaymentType() {
		return this.paymentType;	
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = Utils.checkNull(paymentType);
	}

	public Party getConsignor() {		
		if (consignorTIN != null || consignorAdr != null) {
			if (consignor == null) {
				consignor = new Party();
			}
		}
		if (consignorTIN != null) {
			consignor.setPartyTIN(consignorTIN);
		}
		if (consignorAdr != null) {
			consignor.setAddress(consignorAdr);
		}
		return this.consignor;	
	}
	public void setConsignor(Party party) {
		this.consignor = party;
	}

	public Party getConsignee() {
		if (consigneeTIN != null  || consigneeAdr != null) {
			if (consignee == null) {
				consignee = new Party();
			}
		}
		if (consigneeTIN != null) {
			consignee.setPartyTIN(consigneeTIN);	
		}	
		if (consigneeAdr != null) {
			consignee.setAddress(consigneeAdr);
		}
		return this.consignee;	
	}
	public void setConsignee(Party party) {
		this.consignee = party;
	}

	public Party getNotifyParty() {
		if (notifyPartyTIN != null || notifyPartyAdr != null) {
			if (notifyParty == null) {
				notifyParty = new Party();
			}
		}
		if (notifyPartyTIN != null) {
			notifyParty.setPartyTIN(notifyPartyTIN);	
		}	
		if (notifyPartyAdr != null) {			
			notifyParty.setAddress(notifyPartyAdr);	
		}	
		return this.notifyParty;	
	}
	public void setNotifyParty(Party party) {
		this.notifyParty = party;
	}

	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null || personLodgingSumaAdr != null || personLodgingSumaContact != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}
		}
		if (personLodgingSumaTIN != null) {
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
		}
		if (personLodgingSumaAdr != null) {
			personLodgingSuma.setAddress(personLodgingSumaAdr);		
		}
		if (personLodgingSumaContact != null) {
			personLodgingSuma.setContactPerson(personLodgingSumaContact);		
		}		
		return this.personLodgingSuma;	
	}
	public void setPersonLodgingSuma(Party party) {
		this.personLodgingSuma = party;
	}
	
	public Party getRepresentative() {
		if (representativeTIN != null || representativeAdr != null) { 
			if (representative == null) {
				representative = new Party();
			}
		}
		if (representativeTIN != null) {
			representative.setPartyTIN(representativeTIN);		
		}
		if (representativeAdr != null) {
			representative.setAddress(representativeAdr);		
		}		
		return this.representative;
	}	
	public void setRepresentative(Party argument) {
		this.representative = argument;
	}
	
	public Party getCarrier() {
		if (carrierTIN != null || carrierAdr != null) { 
			if (carrier == null) {
				carrier = new Party();
			}
		}
		if (carrierTIN != null) {
			carrier.setPartyTIN(carrierTIN);		
		}
		if (carrierAdr != null) {
			carrier.setAddress(carrierAdr);		
		}		
		return this.carrier;
	}
	public void setCarrier(Party argument) {
		this.carrier = argument;
	}

	public String getCustomsOfficeOfLodgment() {
		return customsOfficeOfLodgment;	
	}
	public void setCustomsOfficeOfLodgment(String customsOfficeOfLodgment) {
		this.customsOfficeOfLodgment = Utils.checkNull(customsOfficeOfLodgment);
	}

	public String getCustomsOfficeFirstEntry() {
		return customsOfficeFirstEntry;	
	}
	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = Utils.checkNull(customsOfficeFirstEntry);
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;	
	}
	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = Utils.checkNull(declaredDateOfArrival);
	}

	public List<String> getCustomsOfficeOfSubsequentEntryList() {
		return this.customsOfficeOfSubsequentEntryList;	
	}
	public void setCustomsOfficeOfSubsequentEntryList(List<String> list) {
		this.customsOfficeOfSubsequentEntryList = list;
	}
	
	public List<String> getCountryOfRoutingList() {
		return countryOfRoutingList;
	}
	public void setCountryOfRoutingList(List<String> list) {
		this.countryOfRoutingList = list;
	}
	public void addCountryOfRoutingList(String argument) {
		if (countryOfRoutingList == null) {
			countryOfRoutingList = new ArrayList<String>();
		}
		countryOfRoutingList.add(argument);
	}
    public List <GoodsItemLong>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(GoodsItemLong argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemLong>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	public void setGoodsItemList(List <GoodsItemLong> argument) {
	   	this.goodsItemList = argument;  	
	}

	public String getTotalGrossMass() {
		return this.totalGrossMass;	
	}	
	public void setTotalGrossMass(String argument) {
		this.totalGrossMass = argument;
	}
	
	public String getSituationCode() {
		return this.situationCode;	
	}	
	public void setSituationCode(String argument) {
		this.situationCode = argument;
	}
	
	public String getDeclarationTime() {
		return this.declarationTime;	
	}	
	public void setDeclarationTime(String argument) {
		this.declarationTime = argument;
	}	

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String argument) {
		this.mrn = argument;
	}

	public String getDeclarationPlace() {
		return declarationPlace;
	}

	public void setDeclarationPlace(String argument) {
		this.declarationPlace = argument;
	}

	public List<SealNumber> getSealIDList() {
		return sealIDList;
	}
	public void setSealIDList(List<SealNumber> list) {
		this.sealIDList = list;
	}	
	public void addSealIDList(SealNumber argument) {
		if (sealIDList == null) {
			sealIDList = new ArrayList<SealNumber>();
		}
		sealIDList.add(argument);
	}
	public SealNumber getLastSealNum() {
		// CK 2010-12-30
		// returns the last SealNumber object to fill in Language
		// has to be removed because the calling method will add the updated SealNumber again
		if (sealIDList != null) {
			int s = sealIDList.size();
			SealNumber sn = sealIDList.get(s - 1);
			sealIDList.remove(s - 1);
			return sn;
		} else {
			return null;			
		}
	}
	
	public Party setPartyFromTrader(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party  = new Party();
		
		TIN	tin = new TIN();
		tin.setTIN(trader.getTIN());
		tin.setIsTINGermanApprovalNumber(trader.getCustomsID());
		tin.setCustomerIdentifier(trader.getCustomerID());	
		tin.setIdentificationType(trader.getTINType());   //EI20110120
		
		party.setPartyTIN(tin);		
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());
		party.setContactPerson(trader.getContactPerson());
		
		return party;		
	}
	
	public ConveyanceCall getConveyanceCall() {
		return conveyanceCall;
	}
	public void setConveyanceCall(ConveyanceCall argument) {
		this.conveyanceCall = argument;
	}		

	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}

	public void setDeclaredDateOfArrivalFormat(EFormat eFormat) {
		this.declaredDateOfArrivalFormat = eFormat;
	}
	
	public EFormat getDeclarationTimeFormat() {
		return declarationTimeFormat;
	}

	public void setDeclarationTimeFormat(EFormat eFormat) {
		this.declarationTimeFormat = eFormat;
	}
	
	public EFormat getUidsDateAndTimeFormat(String value) {  //EI20110208
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}
}
