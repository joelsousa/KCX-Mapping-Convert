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
 * Created		: 19.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSMsgEntrySummaryDeclarationAcknowledgment.
  * 			: (IE328).
 *
 * @author krzoska
 * @version 2.0.00
 */

public class MsgEntrySummaryDeclarationAcknowledgment extends KCXMessage {

	private String  msgName = "ICSEntrySummaryDeclarationAcknowledgment";	
	private String  msgType;    
	
	private String  referenceNumber;
	private String  mrn;
	private TransportMeans meansOfTransportBorder;
	private String  shipmentNumber;
	private String  conveyanceReference;
	private String  registrationDateAndTime;
	//private String  amendmentDateAndTime;
	private Party   personLodgingSuma;
	private TIN	    personLodgingSumaTIN;	
	private Party   representative;
	private TIN	    representativeTIN;	
	private Party   carrier;
	private TIN     carrierTIN;	
	private String  customsOfficeOfLodgment;
	private String  customsOfficeFirstEntry;	
	private String  declaredDateOfArrival;
	private IcsDocument document;
	private List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();

	private EFormat declaredDateOfArrivalFormat;
	private EFormat registrationDateAndTimeFormat;

	
	public MsgEntrySummaryDeclarationAcknowledgment() {
		super();
	}

	public MsgEntrySummaryDeclarationAcknowledgment(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgEntrySummaryDeclarationAcknowledgment(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
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
			case GoodsItem:
				GoodsItemShort wrkGoodsItem =
				new GoodsItemShort(getScanner(), msgName, msgType);
				wrkGoodsItem.parse(tag.name());
				addGoodsItemList(wrkGoodsItem);
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
    				 } else if (msgType.equals("UIDS")) {    					     					
    					 setRegistrationDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
    				 } else {
    					 setRegistrationDateAndTimeFormat(null);
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
    			case DeclaredDateOfArrival:
    			case ExpectedDateOfArrival:
    			case ExpectedArrivalDateAndTime:
    				 setDeclaredDateOfArrival(value);
    				 if (tag == EEntrySummaryDeclarationAcknowledgment.DeclaredDateOfArrival) {
    					 setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));
    				 } else {
    					 setRegistrationDateAndTimeFormat(Utils.getUidsDateAndTimeFormat(value));  
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
	/*
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
    */
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
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
		
	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}		
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
		}			
		return this.personLodgingSuma;	
	}
	
	public void setPersonLodgingSuma(Party party) {
		this.personLodgingSuma = party;
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
	
	public void addGoodsItemList(GoodsItemShort argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemShort>();
	   	}
	   	this.goodsItemList.add(argument);
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

}
