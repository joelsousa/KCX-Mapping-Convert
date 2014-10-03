package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ConveyanceCall;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: Contains Message Structure with fields used in ICSDeclarationAmendment.
 * 				: (IE313)
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */
public class MsgDeclarationAmendment extends KCXMessage {
	
	private String 	msgName = "ICSDeclarationAmendment";
	private String  msgType = "";
	
	private String 	referenceNumber;
	private String 	shipmentNumber;
	private String	mrn;
	private String	delFlag;                      //new for V20
	private String	totalNumberPositions;
	private String 	totalNumberPackages;
	private String 	totalGrossMass;
	private TransportMeans 	meansOfTransportBorder;
	private String 	conveyanceReference;
	private String 	loadingPlace;
	private String 	loadingPlaceLng;
	private String 	unloadingPlace;
	private String 	unloadingPlaceLng;
	private String 	situationCode;
	private String 	paymentType;
	private String 	declarationTime;
	private String 	declarationPlace;
	private Party   consignor;        //TIN, Address and ContactPerson
	private TIN		consignorTIN;	
	private Party   consignee;		  //TIN, Address and ContactPerson
	private TIN		consigneeTIN;	
	private Party   notifyParty;      //TIN, Address and ContactPerson
	private TIN		notifyPartyTIN;	
	private Party   personLodgingSuma; //TIN, Address and ContactPerson
	private TIN		personLodgingSumaTIN;	
	private ContactPerson personLodgingSumaContact;
	private Party	representative;
	private TIN		representativeTIN;	
	private ContactPerson 	representativeContact;
	private Party	carrier;
	private TIN		carrierTIN;	
	private String 	customsOfficeOfLodgment;
	private String	customsOfficeFirstEntry;
	private String	declaredDateOfArrival;
	private List<String> customsOfficeOfSubsequentEntryList;  		
	private List<SealNumber> sealIDList;
	private List<String>	 countryOfRoutingList;
	private ConveyanceCall 	 conveyanceCall;                  
	private List<GoodsItemLong> goodsItemList;
	
	private EFormat declaredDateOfArrivalFormat;
	private EFormat declarationTimeFormat;
	
	
	public MsgDeclarationAmendment() {
		super();
	}
	
	public MsgDeclarationAmendment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgDeclarationAmendment(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
	}

	private enum EDeclarationAmendment {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		ShipmentNumber,					CommercialReferenceNumber,
		MRN,							//same
		DelFlag,
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
		RepresentativeContact,			//"" Representative.Contact
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
				meansOfTransportBorder = new TransportMeans(getScanner(), msgType);
				meansOfTransportBorder.parse(tag.name());				
				break;
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());	
				break;
			case ConsignorAddress:
			case Consignor:	
				consignor = new Party(getScanner());
				consignor.parse(tag.name());
				break;			
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());				
				break;
			case ConsigneeAddress:
			case Consignee:
				consignee = new Party(getScanner());
				consignee.parse(tag.name());
				break;			
			case NotifyPartyTIN:				
				notifyPartyTIN = new TIN(getScanner());
				notifyPartyTIN.parse(tag.name());				
				break;
			case NotifyPartyAddress:
			case NotifyParty:
				notifyParty = new Party(getScanner());
				notifyParty.parse(tag.name());				
				break;				
			case PersonLodgingSumaTIN:
				personLodgingSumaTIN = new TIN(getScanner());
				personLodgingSumaTIN.parse(tag.name());
				break;			
			case PersonLodgingSumaAddress:
			case PersonLodgingSumDec:
				personLodgingSuma = new Party(getScanner());
				personLodgingSuma.parse(tag.name());
				break;
			case PersonLodgingSumaContact:
				personLodgingSumaContact = new ContactPerson(getScanner());
				personLodgingSumaContact.parse(tag.name());
				break;			
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner());
				representativeTIN.parse(tag.name());
				break;				
			case RepresentativeAddress:
			case Representative:
				representative = new Party(getScanner());
				representative.parse(tag.name());
				break;
			case RepresentativeContact:
				representativeContact = new ContactPerson(getScanner());
				representativeContact.parse(tag.name());
				break;			

			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
			case Carrier:
				carrier = new Party(getScanner());
				carrier.parse(tag.name());
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
			case ConveyanceCall:                          
				conveyanceCall = new ConveyanceCall(getScanner());
				conveyanceCall.parse(tag.name());
				break;

			case GoodsItem:				
				GoodsItemLong item = new GoodsItemLong(getScanner(), msgName, msgType);
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
			case DelFlag:
				setDelFlag(value);
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
					 setDeclarationTimeFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					
					 setDeclarationTimeFormat(Utils.getUidsDateAndTimeFormat(value));
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
					 setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					
					 setDeclaredDateOfArrivalFormat(Utils.getUidsDateAndTimeFormat(value));
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
    /*
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
    */
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
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
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}		
			consignor.setPartyTIN(consignorTIN);
		}			
		return this.consignor;
	}

	public void setConsignor(Party consignor) {
		this.consignor = consignor;
	}	

	public Party getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				consignee = new Party();
			}		
			consignee.setPartyTIN(consigneeTIN);	
		}		
		return this.consignee;
	}

	public void setConsignee(Party consignee) {
		this.consignee = consignee;
	}	

	public Party getNotifyParty() {
		if (notifyPartyTIN != null) {
			if (notifyParty == null) {
				notifyParty = new Party();
			}		
			notifyParty.setPartyTIN(notifyPartyTIN);				
		}		
		return this.notifyParty;
	}
	
	public void setNotifyParty(Party notifyParty) {
		this.notifyParty = notifyParty;
	}	

	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}		
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
		}		
		if (personLodgingSumaContact != null) {
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}
			personLodgingSuma.setContactPerson(personLodgingSumaContact);		
		}
		return this.personLodgingSuma;	
	}
	
	public void setPersonLodgingSuma(Party personLodgingSuma) {
		this.personLodgingSuma = personLodgingSuma;
	}
	
	public Party getRepresentative() {
		if (representativeTIN != null) { 
			if (representative == null) {
				representative = new Party();
			}		
			representative.setPartyTIN(representativeTIN);		
		}
		if (representativeContact != null) {
			if (representative == null) {
				representative = new Party();
			}
			representative.setContactPerson(representativeContact);		
		}

		return this.representative;
	}
	
	public void setRepresentative(Party representative) {
		this.representative = representative;
	}
	
	public Party getCarrier() {
		if (carrierTIN != null) { 
			if (carrier == null) {
				carrier = new Party();
			}		
			carrier.setPartyTIN(carrierTIN);		
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

	public void setDelFlag(String delFlag) {
		this.delFlag = Utils.checkNull(delFlag);
	}

	public String getDelFlag() {
		return delFlag;
	}
}
