package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;


/**
 * Module		: MsgEntrySummaryDeclarationAcceptance<br>
 * Created		: 2010.07.19<br>
 * Description	: Contains Message Structure with fields used in ICSEntrySummaryDeclarationAcceptance.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class MsgEntrySummaryDeclarationAmendmentAcceptance extends KCXMessage {
	private String  msgName = "ICSEntrySummaryDeclarationAcceptance";
	private String  referenceNumber;
	private String  shipmentNumber;
	private String  mrn;
	private TransportMeans meansOfTransportBorder;
	private String  conveyanceReference;
	//private String  registrationDateAndTime;
	private String  amendmentDateAndTime;
	private Party   personLodgingSuma;
	private TIN     personLodgingSumaTIN;
	private Address personLodgingSumaAdr;
	private Party   representative;
	private TIN     representativeTIN;	
	private Address representativeAdr;
	private Party   carrier;
	private TIN     carrierTIN;
	private Address carrierAdr;
	private String  customsOfficeOfLodgement;
	private String  customsOfficeFirstEntry;
	private String  declaredDateOfArrival;
	private IcsDocument document;
	//private List<MsgEntrySummaryDeclarationAcceptancePos> goodsItemList = new 
	//                                           ArrayList<MsgEntrySummaryDeclarationAcceptancePos>();
	private List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();

	private EFormat declaredDateOfArrivalFormat;
	private EFormat amendmentDateAndTimeFormat;
	
	public MsgEntrySummaryDeclarationAmendmentAcceptance() {
		super();
	}
	
	public MsgEntrySummaryDeclarationAmendmentAcceptance(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	private enum EEntrySummaryDeclarationAcceptance {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		ShipmentNumber,                 CommercialReferenceNumber,
		MRN,							//same 
		MeansOfTransportBorder,			TransportAtBorder,
		ConveyanceReference,			ConveyanceNumber,
		RegistrationDateAndTime,		//same
		AmendmentDateAndTime,			//same										
		PersonLodgingSumaTIN,           PersonLodgingSumDec,
		PersonLodgingSumaAddress,										
		RepresentativeTIN,              Representative,
		RepresentativeAddress,										
		CarrierTIN,                     EntryCarrier, Carrier,
		CarrierAddress,	
		CustomsOfficeOfLodgement,		OfficeOfLodgement,
		//CustomsOfficeOfLodgment,
		CustomsOfficeFirstEntry,		OfficeOfFirstEntry,
		DeclaredDateOfArrival,			ExpectedArivalDateAndTime, 
		ExpectedArrivalDateAndTime, 	ExpectedDateOfArrival,
		Document,
		GoodsItem;						//same
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EEntrySummaryDeclarationAcceptance) tag) {
				case MeansOfTransportBorder:	
				case TransportAtBorder:
					meansOfTransportBorder = new TransportMeans(getScanner());
					meansOfTransportBorder.parse(tag.name());
					break;
				case PersonLodgingSumaTIN:
					personLodgingSumaTIN = new TIN(getScanner());
					personLodgingSumaTIN.parse(tag.name());
					break;
				case PersonLodgingSumaAddress:
					personLodgingSumaAdr = new Address(getScanner());
					personLodgingSumaAdr.parse(tag.name());
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
				case EntryCarrier:
				case Carrier:
					Trader carrierTrader = new Trader(getScanner());
					carrierTrader.parse(tag.name());
					carrier = setPartyFromTrader(carrierTrader);
					break;	
				case Document:
					document = new IcsDocument(getScanner());
					document.parse(tag.name());					
					break;
				case GoodsItem:
					GoodsItemShort goodsItem = new  GoodsItemShort(getScanner(), msgName);
					goodsItem.parse(tag.name());
					addGoodsItemList(goodsItem);
					break;
				default:
					return;
				} 
			} else {
		    	switch ((EEntrySummaryDeclarationAcceptance) tag) {
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
				case ConveyanceReference:
				case ConveyanceNumber:
					 setConveyanceReference(value);
					 break;	
				case RegistrationDateAndTime:  //not in this Message					 
					 break;
				case AmendmentDateAndTime:
					 setAmendmentDateAndTime(value);					 
					 break;
				case CustomsOfficeOfLodgement:
				//case CustomsOfficeOfLodgment:
				case OfficeOfLodgement:
					 setCustomsOfficeOfLodgement(value);
					 break;
				case CustomsOfficeFirstEntry:
				case OfficeOfFirstEntry:
					 setCustomsOfficeFirstEntry(value);
					 break;
				case DeclaredDateOfArrival:
				case ExpectedDateOfArrival:
				case ExpectedArivalDateAndTime:
				case ExpectedArrivalDateAndTime:
					 setDeclaredDateOfArrival(value);
					 if (tag == EEntrySummaryDeclarationAcceptance.DeclaredDateOfArrival) {
						 setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);
						 setAmendmentDateAndTimeFormat(EFormat.KIDS_DateTime);
					 } else {
						//EI20110208:setDeclaredDateOfArrivalFormat(EFormat.ST_DateTimeT);
						//EI20110208: setAmendmentDateAndTimeFormat(EFormat.ST_DateTimeT);
						 setDeclaredDateOfArrivalFormat(getUidsDateAndTimeFormat(value)); //EI20110208
						 //nicht ganz korrekt, aber normalerweise werden die DateTime im gleichen Format gaschickt
						 setAmendmentDateAndTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
					 }					
					 break;
				default :
					return;
				}
		    }
	}
	
	public void stoppElement(Enum tag) {		
	}
	
	public Enum translate(String token) {
		try {
  			return EEntrySummaryDeclarationAcceptance.valueOf(token);
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

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public TransportMeans getMeansOfTransportBorder() {
		return meansOfTransportBorder;
	}

	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		if (meansOfTransportBorder == null) {
			return;
		}
		
		this.meansOfTransportBorder = meansOfTransportBorder;
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	}

	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = conveyanceReference;
	}
	
	public String getAmendmentDateAndTime() {
		return amendmentDateAndTime;
	}
	
	public void setAmendmentDateAndTime(String amendmentDateAndTime) {
		this.amendmentDateAndTime = amendmentDateAndTime;
	}

	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null || personLodgingSumaAdr != null) { 
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

	public String getCustomsOfficeOfLodgement() {
		return customsOfficeOfLodgement;
	}

	public void setCustomsOfficeOfLodgement(String customsOfficeOfLodgement) {
		this.customsOfficeOfLodgement = customsOfficeOfLodgement;
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



	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public List<GoodsItemShort> getGoodsItemList() {
		return goodsItemList;
	}
	
	public IcsDocument getDocument() {
		return document;
	}
	public void setDocument(IcsDocument argument) {
		this.document = argument;
	}
	
	public void addGoodsItemList(GoodsItemShort argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemShort>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	
	public void setGoodsItemList(List<GoodsItemShort> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
	
	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}

	public void setDeclaredDateOfArrivalFormat(EFormat eFormat) {
		this.declaredDateOfArrivalFormat = eFormat;
	}
	
	public EFormat getAmendmentDateAndTimeFormat() {
		return amendmentDateAndTimeFormat;
	}

	public void setAmendmentDateAndTimeFormat(EFormat eFormat) {
		this.amendmentDateAndTimeFormat = eFormat;
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
