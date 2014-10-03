package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: ICS20<br>
 * Created		: 2012.10.19<br>
 * Description	: Contains Message Structure with fields used in ICSEntrySummaryDeclarationAcceptance.
 * 				: (IE304)
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MsgEntrySummaryDeclarationAmendmentAcceptance extends KCXMessage {
	
	private String  msgName = "ICSEntrySummaryDeclarationAcceptance";
	private String  msgType = "";
	
	private String  referenceNumber;
	private String  shipmentNumber;
	private String  mrn;
	private TransportMeans meansOfTransportBorder;
	private String  conveyanceReference;
	private String  registrationDateAndTime;
	private String  amendmentDateAndTime;
	private Party   personLodgingSuma;
	private TIN     personLodgingSumaTIN;	
	private Party   representative;
	private TIN     representativeTIN;		
	private Party   carrier;
	private TIN     carrierTIN;	
	private String  customsOfficeOfLodgement;
	private String  customsOfficeFirstEntry;
	private String  declaredDateOfArrival;
	private IcsDocument document;	
	private List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();

	private EFormat registrationDateAndTimeFormat;
	private EFormat declaredDateOfArrivalFormat;
	private EFormat amendmentDateAndTimeFormat;
	
	
	public MsgEntrySummaryDeclarationAmendmentAcceptance() {
		super();
	}
	
	public MsgEntrySummaryDeclarationAmendmentAcceptance(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	public MsgEntrySummaryDeclarationAmendmentAcceptance(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);	
		msgType = type;
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
					meansOfTransportBorder = new TransportMeans(getScanner(), msgType);
					meansOfTransportBorder.parse(tag.name());
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
				case RepresentativeTIN:
					representativeTIN = new TIN(getScanner());
					representativeTIN.parse(tag.name());
					break;
				case RepresentativeAddress:
				case Representative:
					representative = new Party(getScanner());
					representative.parse(tag.name());
					break;				
				case CarrierTIN:
					carrierTIN = new TIN(getScanner());
					carrierTIN.parse(tag.name());
					break;
				case CarrierAddress:
				case EntryCarrier:
				case Carrier:
					carrier = new Party(getScanner());
					carrier.parse(tag.name());
					break;				
				case Document:
					document = new IcsDocument(getScanner());
					document.parse(tag.name());					
					break;
				case GoodsItem:
					GoodsItemShort goodsItem = new  GoodsItemShort(getScanner(), msgName, msgType);
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
				case RegistrationDateAndTime:  
					 setRegistrationDateAndTime(value);
					 if (msgType.equals("KIDS")) {
						 setRegistrationDateAndTimeFormat(Utils.getKidsDateAndTimeFormat(value));
					 } else {
						 setRegistrationDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
					 }
					 break;
				case AmendmentDateAndTime:
					 setAmendmentDateAndTime(value);	
					 if (msgType.equals("KIDS")) {
						 setAmendmentDateAndTimeFormat(Utils.getKidsDateAndTimeFormat(value));
					 } else {
						 setAmendmentDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
					 }
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
						 setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));						 
					 } else {						
						 setDeclaredDateOfArrivalFormat(Utils.getUidsDateAndTimeFormat(value)); 						 						 
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
		if (personLodgingSumaTIN != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}		
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
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
	}	public String getReferenceNumber() {
		return referenceNumber;
	}

	public String getMrn() {
		return mrn;
	}
	
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	
	public IcsDocument getDocument() {
		return document;
	}
	
	public void setDocument(IcsDocument argument) {
		this.document = argument;
	}
	
	public List<GoodsItemShort> getGoodsItemList() {
		return goodsItemList;
	}
	
	public void setGoodsItemList(List<GoodsItemShort> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
	
	public void addGoodsItemList(GoodsItemShort argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemShort>();
	   	}
	   	this.goodsItemList.add(argument);
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
	
	public String getRegistrationDateAndTime() {
		return registrationDateAndTime;
	}
	
	public void setRegistrationDateAndTime(String registrationDateAndTime) {
		this.registrationDateAndTime = Utils.checkNull(registrationDateAndTime);
	}

	public EFormat getRegistrationDateAndTimeFormat() {
		return registrationDateAndTimeFormat;
	}
	
	public void setRegistrationDateAndTimeFormat(
			EFormat registrationDateAndTimeFormat) {
		this.registrationDateAndTimeFormat = registrationDateAndTimeFormat;
	}
}
