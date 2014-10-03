package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 23.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSAdvanceInterventionNot.
 * 				: (IE351)
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MsgAdvanceInterventionNot extends KCXMessage {

	private String  msgName = "ICSAdvanceInterventionNot";
	private String  msgType = "";
	
	private String  referenceNumber;
	private String  mrn;	
	private TransportMeans meansOfTransportBorder;
	private String  totalNumberPosition;
	private String  shipmentNumber;
	private String  conveyanceReference;
	private String  notificationDateTime;
	private String  registrationDateTime;
	private String  acceptedDateTime;	
	private Party   personLodgingSuma;
	private TIN	    personLodgingSumaTIN;	
	private Party   representative;
	private TIN	    representativeTIN;	
	private Party   carrier;	
	private TIN     carrierTIN;	
	private String  customsOfficeFirstEntry;
	private String  expectedDateOfArrival;
	private List<CustomsIntervention> customsInterventionList;
	private List<GoodsItemShort> goodsItemList;
	
	private EFormat notificationDateTimeFormat;
	private EFormat registrationDateTimeFormat;
	private EFormat acceptedDateTimeFormat;
	private EFormat declaredDateOfArrivalFormat;

	
	public MsgAdvanceInterventionNot() {
		super();				
	}

	public MsgAdvanceInterventionNot(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	public MsgAdvanceInterventionNot(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
	}

	private enum EAdvanceInterventionNot {
		//KIDS:							UIDS (ICSAcceptance):
		ReferenceNumber,        		LocalReferenceNumber,		
		MRN,							//same 
		MeansOfTransportBorder,			TransportAtBorder,
		TotalNumberPositions,			TotalNumberOfItems,
		ShipmentNumber,                 CommercialReferenceNumber,
		ConveyanceReference,			ConveyanceNumber,
		NotificationDateTime,			NotificationDateAndTime,
		RegistrationDateTime,			RegistrationDateAndTime,
		AcceptedDateTime,				SubmissionDateAndTime,										
		PersonLodgingSumaTIN,           PersonLodgingSumDec,
		PersonLodgingSumaAddress,										
		RepresentativeTIN,              Representative,
		RepresentativeAddress,										
		CarrierTIN,                     EntryCarrier, Carrier,
		CarrierAddress,			
		CustomsOfficeFirstEntry,		OfficeOfFirstEntry,
		DeclaredDateOfArrival,			ExpectedArrivalDateAndTime,
		CustomsIntervention,			//same
		GoodsItem;						//same
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EAdvanceInterventionNot) tag) {
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
				carrier = new Party(getScanner());
				carrier.parse(tag.name());
				break;				
			case GoodsItem:
				GoodsItemShort goodsItem = new GoodsItemShort(getScanner(), msgName, msgType);
				goodsItem.parse(tag.name());				
				if (goodsItemList == null) {
					goodsItemList = new Vector<GoodsItemShort>();
				}
				goodsItemList.add(goodsItem);
				break;			
			case CustomsIntervention:
				CustomsIntervention customs = new CustomsIntervention(getScanner());
				customs.parse(tag.name());
				if (customsInterventionList == null) {
					customsInterventionList = new Vector<CustomsIntervention>();
				}
				customsInterventionList.add(customs);
				break;
				
			default:
				return;
			}
	    } else {
	    	switch ((EAdvanceInterventionNot) tag) {
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
    			case TotalNumberOfItems:	 
    			case TotalNumberPositions:
    				 setTotalNumberPosition(value);
    				 break;
    			case ConveyanceReference:
    			case ConveyanceNumber:
    				 setConveyanceReference(value);
    				 break;	
    			case NotificationDateAndTime:	 
    			case NotificationDateTime:
    				 setNotificationDateTime(value);    				
    				 if (tag == EAdvanceInterventionNot.NotificationDateTime) {
    					 setNotificationDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));					 
    				 } else {    					
    					 setNotificationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
    				 }    				 
    				 break;
    			case RegistrationDateAndTime:	 
    			case RegistrationDateTime:
    				 setRegistrationDateTime(value);    				 
    				 if (tag == EAdvanceInterventionNot.RegistrationDateTime) {
    					 setRegistrationDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));					 
    				 } else {    					
    					 setRegistrationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
    				 }    				 
    				 break;
    			case SubmissionDateAndTime:	 
    			case AcceptedDateTime:
    				 setAcceptedDateTime(value);    				
    				 if (tag == EAdvanceInterventionNot.AcceptedDateTime) {
    					 setAcceptedDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));					 
    				 } else {    					
    					 setRegistrationDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
    				 }    				 
    				 break;						
    			case CustomsOfficeFirstEntry:
    			case OfficeOfFirstEntry:
    				 setCustomsOfficeFirstEntry(value);
    				 break;
    			case DeclaredDateOfArrival:
    			case ExpectedArrivalDateAndTime:
    				 setDeclaredDateOfArrival(value);    				 
    				 if (tag == EAdvanceInterventionNot.DeclaredDateOfArrival) {
    					 setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));					 
    				 } else {
    					 setDeclaredDateOfArrivalFormat(Utils.getUidsDateAndTimeFormat(value));
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
  			return EAdvanceInterventionNot.valueOf(token);
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
	
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	}
	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = conveyanceReference;
	}

	public Party getCarrier() {
		if (carrierTIN != null) {
			if (carrier == null) {
				carrier = new Party();
			}		
			carrier.setPartyTIN(carrierTIN);				
		}			
		return carrier;
	}
	
	public void setCarrier(Party party) {		
		this.carrier = party;
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
	
	public String getCustomsOfficeFirstEntry() {
		return customsOfficeFirstEntry;
	}
	
	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = customsOfficeFirstEntry;
	}

	public String getDeclaredDateOfArrival() {
		return expectedDateOfArrival;
	}
	
	public void setDeclaredDateOfArrival(String expectedDateOfArrival) {
		this.expectedDateOfArrival = expectedDateOfArrival;
	}

	public void setGoodsItemList(List<GoodsItemShort> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
	
	public List<GoodsItemShort> getGoodsItemList() {
		return goodsItemList;
	}
	
	public String getTotalNumberPosition() {
		return totalNumberPosition;
	}
	public void setTotalNumberPosition(String argument) {
		this.totalNumberPosition = argument;
	}
	
	public String getNotificationDateTime() {
		return notificationDateTime;
	}
	
	public void setNotificationDateTime(String argument) {
		this.notificationDateTime = argument;
	}
	
	public String getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(String argument) {
		this.registrationDateTime = argument;
	}
	
	public String getAcceptedDateTime() {
		return acceptedDateTime;
	}
	public void setAcceptedDateTime(String argument) {
		this.acceptedDateTime = argument;
	}
	
	public List<CustomsIntervention> getCustomsInterventionList() {
		return customsInterventionList;
	}
	
	public void setCustomsInterventionList(List<CustomsIntervention> argument) {
		this.customsInterventionList = argument;
	}

	public String getMrn() {
		return mrn;
	}
	
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public EFormat getNotificationDateTimeFormat() {
		return notificationDateTimeFormat;
	}
	
	public void setNotificationDateTimeFormat(EFormat notificationDateTimeFormat) {
		this.notificationDateTimeFormat = notificationDateTimeFormat;
	}

	public EFormat getRegistrationDateTimeFormat() {
		return registrationDateTimeFormat;
	}
	
	public void setRegistrationDateTimeFormat(EFormat registrationDateTimeFormat) {
		this.registrationDateTimeFormat = registrationDateTimeFormat;
	}

	public EFormat getAcceptedDateTimeFormat() {
		return acceptedDateTimeFormat;
	}
	
	public void setAcceptedDateTimeFormat(EFormat acceptedDateTimeFormat) {
		this.acceptedDateTimeFormat = acceptedDateTimeFormat;
	}
	
	public EFormat getExpectedDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}
	
	public void setDeclaredDateOfArrivalFormat(EFormat expectedDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = expectedDateOfArrivalFormat;
	}	
	
}
