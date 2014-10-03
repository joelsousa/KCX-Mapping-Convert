package com.kewill.kcx.component.mapping.countries.de.ics.msg;

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
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgAdvanceIntervetionNot<br>
 * Erstellt		: 07.07.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSAdvanceInterventionNot.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgAdvanceInterventionNot extends KCXMessage {

	private String msgName = "ICSAdvanceInterventionNot";
	private String referenceNumber;
	private String mrn;	//PP 20100719
	private TransportMeans meansOfTransportBorder;
	private String totalNumberPosition;
	private String shipmentNumber;
	private String conveyanceReference;
	private String notificationDateTime;
	private String registrationDateTime;
	private String acceptedDateTime;	
	private Party personLodgingSuma;
	private TIN	personLodgingSumaTIN;
	private Address personLodgingSumaAddress;
	private Party representative;
	private TIN	representativeTIN;
	private Address representativeAddress;
	private Party carrier;	
	private TIN carrierTIN;
	private Address carrierAddress;	
	private String customsOfficeFirstEntry;
	private String expectedDateOfArrival;
	private List<CustomsIntervention> customsInterventionList;
	private List<GoodsItemShort> goodsItemList;
	
	//PP 20100719
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
		CustomsIntervention,			//TODO
		GoodsItem;						//same
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EAdvanceInterventionNot) tag) {
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
				personLodgingSumaAddress = new Address(getScanner());
				personLodgingSumaAddress.parse(tag.name());
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
				representativeAddress = new Address(getScanner());
				representativeAddress.parse(tag.name());
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
				carrierAddress = new Address(getScanner());
				carrierAddress.parse(tag.name());
				break;
			case EntryCarrier:
				Trader carrierTrader = new Trader(getScanner());
				carrierTrader.parse(tag.name());
				carrier = setPartyFromTrader(carrierTrader);
				break;				
			case GoodsItem:
				GoodsItemShort goodsItem = new GoodsItemShort(getScanner(), msgName);
				goodsItem.parse(tag.name());
				if (goodsItemList == null) {
					goodsItemList = new Vector<GoodsItemShort>();
				}
				goodsItemList.add(goodsItem);
				break;
			
			case CustomsIntervention://LC 201007019
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
    					 setNotificationDateTimeFormat(EFormat.KIDS_DateTime);					 
    				 } else {
    					//EI20110208: setNotificationDateTimeFormat(EFormat.ST_DateTimeT);
    					 setNotificationDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 
    				 break;
    			case RegistrationDateAndTime:	 
    			case RegistrationDateTime:
    				 setRegistrationDateTime(value);
    				 
    				//PP 20100719
    				 if (tag == EAdvanceInterventionNot.RegistrationDateTime) {
    					 setRegistrationDateTimeFormat(EFormat.KIDS_DateTime);					 
    				 } else {
    					//EI20110208: setRegistrationDateTimeFormat(EFormat.ST_DateTimeT);
    					 setRegistrationDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 
    				 break;
    			case SubmissionDateAndTime:	 
    			case AcceptedDateTime:
    				 setAcceptedDateTime(value);
    				 
    				//PP 20100719
    				 if (tag == EAdvanceInterventionNot.AcceptedDateTime) {
    					 setAcceptedDateTimeFormat(EFormat.KIDS_DateTime);					 
    				 } else {
    					//EI20110208: setAcceptedDateTimeFormat(EFormat.ST_DateTimeT);
    					 setRegistrationDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 
    				 break;						
    			case CustomsOfficeFirstEntry:
    			case OfficeOfFirstEntry:
    				 setCustomsOfficeFirstEntry(value);
    				 break;
    			case DeclaredDateOfArrival:
    			case ExpectedArrivalDateAndTime:
    				 setDeclaredDateOfArrival(value);
    				 
    				//PP 20100719
    				 if (tag == EAdvanceInterventionNot.DeclaredDateOfArrival) {
    					 setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);					 
    				 } else {
    					 setDeclaredDateOfArrivalFormat(EFormat.ST_DateTimeT);
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
		if (carrierTIN != null || carrierAddress != null) {
			if (carrier == null) {
				carrier = new Party();
			}
		}
		if (carrierTIN != null) {
			carrier.setPartyTIN(carrierTIN);				
		}
		if (carrierAddress != null) {
			carrier.setAddress(carrierAddress);				
		}		
		return carrier;
	}

	public void setCarrier(Party party) {		
		this.carrier = party;
	}
	
	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null || personLodgingSumaAddress != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}
		}
		if (personLodgingSumaTIN != null) {
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
		}
		if (personLodgingSumaAddress != null) {
			personLodgingSuma.setAddress(personLodgingSumaAddress);		
		}			
		return this.personLodgingSuma;	
	}
	public void setPersonLodgingSuma(Party party) {
		this.personLodgingSuma = party;
	}
	
	public Party getRepresentative() {
		if (representativeTIN != null || representativeAddress != null) { 
			if (representative == null) {
				representative = new Party();
			}
		}
		if (representativeTIN != null) {
			representative.setPartyTIN(representativeTIN);		
		}
		if (representativeAddress != null) {
			representative.setAddress(representativeAddress);		
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
