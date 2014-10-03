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
 * Module		: MsgDeclarationAmendment<br>
 * Created		: 2010.07.19<br>
 * Description	: Contains Message Structure with fields used in ICSDeclarationAmendment.
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */
public class MsgDeclarationAmendment extends KCXMessage {
	
	private String 			msgName = "ICSDeclarationAmendment";
	private String 			referenceNumber;
	private String 			shipmentNumber;
	private String			mrn;
	private String			totalNumberPositions;
	private String 			totalNumberPackages;
	private String 			totalGrossMass;
	private TransportMeans 	meansOfTransportBorder;
	private String 			conveyanceReference;
	private String 			loadingPlace;
	private String 			loadingPlaceLng;
	private String 			unloadingPlace;
	private String 			unloadingPlaceLng;
	private String 			situationCode;
	private String 			paymentType;
	private String 			declarationTime;
	private String 			declarationPlace;
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
	private List<SealNumber>	sealIDList;
	private List<String>	countryOfRoutingList;
	private ConveyanceCall 	conveyanceCall;                      //EI20121004 JIRA:KCXSM-34	
	private List<GoodsItemLong> goodsItemList;
	private EFormat declaredDateOfArrivalFormat;
	private EFormat declarationTimeFormat;
	
	
	public MsgDeclarationAmendment() {
		super();
	}
	
	public MsgDeclarationAmendment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	private enum EDeclarationAmendment {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		ShipmentNumber,					CommercialReferenceNumber,
		MRN,							//same
		TotalNumberPositions,			TotalNumberOfItems,
		TotalNumberPackages,			TotalNumberOfPackages,
		TotalGrossMass,					//same
		MeansOfTransportBorder,			TransportAtBorder,
		ConveyanceReference,			ConveyanceNumber,
		LoadingPlace,					PlaceOfLoading,
		LoadingPlaceLng,				//---
		UnloadingPlace,					PlaceOfUnloading,
		UnloadingPlaceLng,				//--
		SituationCode,					SpecificCircumstanceIndicator,
		PaymentType,					TransportMethodOfPayment,
		DeclarationTime,				DeclarationDateAndTime,
		DeclarationPlace,               //same   
		/* 								AmendmentPlace,--- */
		ConsignorTIN,					Consignor, //.TIN + Address + EntAddress + VatId
		ConsignorAddress,				//""
		ConsigneeTIN,					Consignee, //.TIN + Address + EntAddress + VatId	
		ConsigneeAddress,				//""
		NotifyPartyTIN,					NotifyParty, //.TIN + Address + EntAddress + VatId
		NotifyPartyAddress,				//
		PersonLodgingSumaTIN,			PersonLodgingSumDec, //TIN + Address + EntAddress + VatId * Contact
		PersonLodgingSumaAddress,		//""
		PersonLodgingSumaContact,		//""
		RepresentativeTIN,				Representative, //TIN  + Address + EntAddress + VatId
		RepresentativeAddress,          //"" 
		//RepresentativeContact,			//Representative.Contact
		CarrierTIN,						Carrier, //TIN  + Address + EntAddress + VatId
		CarrierAddress,                 
		CustomsOfficeOfLodgement,		OfficeOfLodgement, 
		CustomsOfficeFirstEntry,		OfficeOfFirstEntry,
		DeclaredDateOfArrival,			ExpectedArrivalDateAndTime,
		CustomsOfficeOfSubsequentEntry, OfficeOfSubsequentEntry,
		SealsID,						SealsIdentity,
		CountryOfRouting,				Itinerary,
		ConveyanceCall,
		GoodsItem;						//same
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		
	    if (value == null) {
			switch ((EDeclarationAmendment) tag) {
			
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
			case PersonLodgingSumDec:
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
				Trader representativeTrader = new Trader(getScanner());
				representativeTrader.parse(tag.name());	
				representative = setPartyFromTrader(representativeTrader);
				break;			
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
				carrierAdr = new Address(getScanner());
				carrierAdr.parse(tag.name());
				break;
			case Carrier:
				Trader carrierTrader = new Trader(getScanner());
				carrierTrader.parse(tag.name());	
				carrier = setPartyFromTrader(carrierTrader);
				break;
			case SealsID:			
				SealNumber sealsID = new SealNumber(getScanner());
				sealsID.parse(tag.name());
				if (!sealsID.isEmpty()) {
					if (sealIDList == null) {
						sealIDList = new ArrayList<SealNumber>();
					}
					addSealIDList(sealsID);
				} 
				break;
			case ConveyanceCall:                           //EI20121004
				conveyanceCall = new ConveyanceCall(getScanner());
				conveyanceCall.parse(tag.name());
				break;

			case GoodsItem:
				//MsgDeclarationAmendmentPos goodsItem = new  MsgDeclarationAmendmentPos(getScanner(), getMsgName());
				GoodsItemLong item = new GoodsItemLong(getScanner(), getMsgName());
				item.parse(tag.name());
				addGoodsItemList(item);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EDeclarationAmendment) tag) {
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
			case SpecificCircumstanceIndicator:
				setSituationCode(value);
				 break;
			case PaymentType:
			case TransportMethodOfPayment:
				 setPaymentType(value);
				 break;		
			case DeclarationTime:
			case DeclarationDateAndTime:
				 setDeclarationTime(value);
				 
				 if (tag == EDeclarationAmendment.DeclarationTime) {
					 setDeclarationTimeFormat(EFormat.KIDS_DateTime);
				 } else {
					//EI20110208: setDeclarationTimeFormat(EFormat.ST_DateTimeT);
					 setDeclarationTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
				 }
				 
				 break;
			case CustomsOfficeOfLodgement:
			case OfficeOfLodgement:
				 setCustomsOfficeOfLodgment(value);
				 break;		
			case DeclaredDateOfArrival:
			case ExpectedArrivalDateAndTime:
				 setDeclaredDateOfArrival(value);
				 
				 if (tag == EDeclarationAmendment.DeclaredDateOfArrival) {
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
				SealNumber sealsID = new SealNumber();
				sealsID.setNumber(value);				
				addSealIDList(sealsID);
				break;
			case CountryOfRouting:
			case Itinerary:
				if (countryOfRoutingList == null) {
					countryOfRoutingList = new ArrayList<String>();
				}
				countryOfRoutingList.add(value);
				break;
			case DeclarationPlace:
				setDeclarationPlace(value);
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
  			return EDeclarationAmendment.valueOf(token);
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

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
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

	public TransportMeans getMeansOfTransportBorder() {
		return meansOfTransportBorder;
	}

	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	}

	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = conveyanceReference;
	}

	public String getLoadingPlace() {
		return loadingPlace;
	}

	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = loadingPlace;
	}

	public String getLoadingPlaceLng() {
		return loadingPlaceLng;
	}

	public void setLoadingPlaceLng(String loadingPlaceLng) {
		this.loadingPlaceLng = loadingPlaceLng;
	}

	public String getUnloadingPlace() {
		return unloadingPlace;
	}

	public void setUnloadingPlace(String unloadingPlace) {
		this.unloadingPlace = unloadingPlace;
	}

	public String getUnloadingPlaceLng() {
		return unloadingPlaceLng;
	}

	public void setUnloadingPlaceLng(String unloadingPlaceLng) {
		this.unloadingPlaceLng = unloadingPlaceLng;
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

	public String getDeclarationTime() {
		return declarationTime;
	}

	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = declarationTime;
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

	public void setConsignor(Party consignor) {
		this.consignor = consignor;
	}	

	public Party getConsignee() {
		if (consigneeTIN != null || consigneeAdr != null) {
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

	public void setConsignee(Party consignee) {
		this.consignee = consignee;
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

	public void setNotifyParty(Party notifyParty) {
		this.notifyParty = notifyParty;
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

	public void setPersonLodgingSuma(Party personLodgingSuma) {
		this.personLodgingSuma = personLodgingSuma;
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

	public void setRepresentative(Party representative) {
		this.representative = representative;
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
	public void setCarrier(Party carrier) {
		this.carrier = carrier;
	}

	public String getCustomsOfficeOfLodgment() {
		return customsOfficeOfLodgment;
	}

	public void setCustomsOfficeOfLodgment(String customsOfficeOfLodgment) {
		this.customsOfficeOfLodgment = customsOfficeOfLodgment;
	}

	public String getCustomsOfficeFirstEntry() {
		return customsOfficeFirstEntry;
	}

	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = customsOfficeFirstEntry;
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}

	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = declaredDateOfArrival;
	}
	
	

	public List<String> getCustomsOfficeOfSubsequentEntryList() {
		return customsOfficeOfSubsequentEntryList;
	}

	public void setCustomsOfficeOfSubsequentEntryList(
			List<String> customsOfficeOfSubsequentEntryList) {
		this.customsOfficeOfSubsequentEntryList = customsOfficeOfSubsequentEntryList;
	}

	public List<SealNumber> getSealIDList() {
		return sealIDList;
	}
	public void setSealIDList(List<SealNumber> sealIDList) {
		this.sealIDList = sealIDList;
	}
	public void addSealIDList(SealNumber argument) {
		if (sealIDList == null) {
			sealIDList = new Vector<SealNumber>();
		}
		sealIDList.add(argument);
	}
	
	public List<String> getCountryOfRoutingList() {
		return countryOfRoutingList;
	}

	public void setCountryOfRoutingList(List<String> countryOfRoutingList) {
		this.countryOfRoutingList = countryOfRoutingList;
	}
	public void addCountryOfRoutingList(String argument) {
		if (countryOfRoutingList == null) {
			countryOfRoutingList = new ArrayList<String>();
		}
		countryOfRoutingList.add(argument);
	}
	public List<GoodsItemLong> getGoodsItemList() {
		return goodsItemList;
	}
	public void addGoodsItemList(GoodsItemLong argument) {
		   	if (this.goodsItemList == null) {
		   		this.goodsItemList = new Vector<GoodsItemLong>();
		   	}
		   	this.goodsItemList.add(argument);
		}
	public void setGoodsItemList(List<GoodsItemLong> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
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

	public void setDeclaredDateOfArrivalFormat(EFormat declaredDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = declaredDateOfArrivalFormat;
	}

	public EFormat getDeclarationTimeFormat() {
		return declarationTimeFormat;
	}

	public void setDeclarationTimeFormat(EFormat declarationTimeFormat) {
		this.declarationTimeFormat = declarationTimeFormat;
	}

	public String getDeclarationPlace() {
		return declarationPlace;
	
	}

	public void setDeclarationPlace(String declarationPlace) {
		this.declarationPlace = Utils.checkNull(declarationPlace);
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
