package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

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
 * Modul		: MsgEntrySummaryDeclarationAcknowledgment<br>
 * Erstellt		: 17.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSMsgEntrySummaryDeclarationAcknowledgment.
 *
 * @author Pete T
 * @version 1.0.00
 */

public class MsgEntrySummaryDeclarationAcknowledgment extends KCXMessage {

	private String msgName = "ICSEntrySummaryDeclarationAcknowledgment";	
	private String  referenceNumber;
	private String  mrn;
	private TransportMeans meansOfTransportBorder;
	private String  shipmentNumber;
	private String  conveyanceReference;
	private String  registrationDateAndTime;
	//private String  amendmentDateAndTime;
	private Party   personLodgingSuma;
	private TIN	    personLodgingSumaTIN;
	private Address personLodgingSumaAdr;
	private Party   representative;
	private TIN	    representativeTIN;
	private Address representativeAdr;
	private Party   carrier;
	private TIN     carrierTIN;
	private Address  carrierAdr;	
	private String  customsOfficeOfLodgment;
	private String  customsOfficeFirstEntry;
	//private String  expectedDateOfArrival;
	private String  declaredDateOfArrival;
	private IcsDocument document;
	//private List<MsgEntrySummaryDeclarationPos> goodsItemList = new ArrayList<MsgEntrySummaryDeclarationPos>();
	private List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();

	private EFormat declaredDateOfArrivalFormat;
	private EFormat registrationDateAndTimeFormat;
	
//	private boolean inUIDSEntryCarrier = false;
	private String msgType;     //EI20110208

	public MsgEntrySummaryDeclarationAcknowledgment() {
		super();
	}

	public MsgEntrySummaryDeclarationAcknowledgment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	private enum EEntrySummaryDeclarationAcknowledgment {
		//KIDS:							UIDS (ICSAcceptance):
		ReferenceNumber,        		LocalReferenceNumber,
		MRN,							//same
		MeansOfTransportBorder,			TransportAtBorder,
		ShipmentNumber,                 CommercialReferenceNumber,
		ConveyanceReference,			ConveyanceNumber,
		RegistrationDateAndTime,		//same
		//AmendmentDateAndTime,			//same										
		PersonLodgingSumaTIN,           PersonLodgingSumDec,
		PersonLodgingSumaAddress,										
		RepresentativeTIN,              Representative,
		RepresentativeAddress,										
		CarrierTIN,                     EntryCarrier, Carrier,
		CarrierAddress,
		CustomsOfficeOfLodgement,		OfficeOfLodgement,
		CustomsOfficeFirstEntry,		OfficeOfFirstEntry,
		ExpectedDateOfArrival, DeclaredDateOfArrival, ExpectedArrivalDateAndTime,
		Document,
		GoodsItem;						//same
	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEntrySummaryDeclarationAcknowledgment) tag) {
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
				Trader personLodgingSumDec = new Trader(getScanner());
				personLodgingSumDec.parse(tag.name());
				personLodgingSuma = setPartyFromTrader(personLodgingSumDec);
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
			case GoodsItem:
				GoodsItemShort wrkGoodsItem =
				new GoodsItemShort(getScanner(), msgName);
				wrkGoodsItem.parse(tag.name());
				goodsItemList.add(wrkGoodsItem);
				break;
			case Document:
				document = new IcsDocument(getScanner());
				document.parse(tag.name());					
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EEntrySummaryDeclarationAcknowledgment) tag) {
    			case ReferenceNumber:
    			case LocalReferenceNumber:
    				 setReferenceNumber(value);
    				 if (tag == EEntrySummaryDeclarationAcknowledgment.ReferenceNumber) {
    					 msgType = "KIDS";						 						 
					 } else {
						 msgType = "UIDS";												
					 }
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
    			case RegistrationDateAndTime:
    				 setRegistrationDateAndTime(value);
    				 if (msgType.equals("KIDS")) {
    					 setRegistrationDateAndTimeFormat(EFormat.KIDS_DateTime);
    				 } else {
    					 setRegistrationDateAndTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 break;
    			//case AmendmentDateAndTime:
    			//	 setAmendmentDateAndTime(value);
    			//	 break;
    			case CustomsOfficeOfLodgement:
    			case OfficeOfLodgement:
    				 setCustomsOfficeOfLodgment(value);
    				 break;
    			case CustomsOfficeFirstEntry:
    			case OfficeOfFirstEntry:
    				 setCustomsOfficeFirstEntry(value);
    				 break;
    			case ExpectedDateOfArrival:
    			case DeclaredDateOfArrival:
    			case ExpectedArrivalDateAndTime:
    				 setDeclaredDateOfArrival(value);
    				 if (tag == EEntrySummaryDeclarationAcknowledgment.DeclaredDateOfArrival) {
    					 setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);
    				 } else {
    					 setRegistrationDateAndTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208 
    				 }
    				 break;
    			default: break;
			}
	    }
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
 		try {
  			return EEntrySummaryDeclarationAcknowledgment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
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

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	}

	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = conveyanceReference;
	}

	public String getRegistrationDateAndTime() {
		return registrationDateAndTime;
	}

	public void setRegistrationDateAndTime(String registrationDateAndTime) {
		this.registrationDateAndTime = registrationDateAndTime;
	}
    /*
	public String getAmendmentDateAndTime() {
		return amendmentDateAndTime;
	}

	public void setAmendmentDateAndTime(String amendmentDateAndTime) {
		this.amendmentDateAndTime = amendmentDateAndTime;
	}
	*/
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

	public void setDeclaredDateOfArrival(String expectedDateOfArrival) {
		this.declaredDateOfArrival = expectedDateOfArrival;
	}

	public IcsDocument getDocument() {
		return document;
	}
	public void setDocument(IcsDocument argument) {
		this.document = argument;
	}
	
	public void setGoodsItemList(List<GoodsItemShort> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	public List<GoodsItemShort> getGoodsItemList() {
		return goodsItemList;
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
	
	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}
	public void setDeclaredDateOfArrivalFormat(EFormat declaredDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = declaredDateOfArrivalFormat;
	}
	
	public EFormat getRegistrationDateAndTimeFormat() {
		return registrationDateAndTimeFormat;
	}
	public void setRegistrationDateAndTimeFormat(EFormat eFormat) {
		this.registrationDateAndTimeFormat = eFormat;
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
