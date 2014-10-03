package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgArrivalNotification<br>
 * Erstellt		: 16.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSArrivalNotification.
 *                 
 * @author Pete T
 * @version 1.0.00
 */
public class MsgArrivalNotification extends KCXMessage {

	private String msgName = "ICSArrivalNotification";	
	
	private String referenceNumber;
	private String totalNumberPositions;
	private String totalNumberPackages;
	private String totalGrossMass;
	private TransportMeans meansOfTransportBorder;
	private String conveyanceReference; 
	private String dateOfPresentation;
	private String declaredDateOfArrival;
	private Party   carrier;
	private TIN     carrierTIN;
	private Address carrierAddress;
	private ContactPerson carrierContact;
	private String customsOfficeFirstEntry; 
	private String countryOfficeFirstEntry;
	//private List<GoodsItem> goodsItemList = new ArrayList<GoodsItem>();
	private List<GoodsItemArn> goodsItemList = new ArrayList<GoodsItemArn>();
	
	private EFormat dateOfPresentationFormat;
	private EFormat declaredDateOfArrivalFormat;			
	
	private String shipmentNumber;
	private String mrn;
	
	private boolean inUIDSEntryCarrier = false;

	public MsgArrivalNotification() {
		super();				
	}

	public MsgArrivalNotification(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}

	private enum EArrivalNotification {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		TotalNumberPositions,			TotalNumberOfItems,
		TotalNumberPackages,			TotalNumberOfPackages,
		TotalGrossMass,					//same
		MeansOfTransportBorder,			TransportAtBorder,
		ConveyanceReference,			ConveyanceNumber,
		DateOfPresentation,				ArrivalDateAndTime,		
		DeclaredDateOfArrival,			ExpectedArrivalDateAndTime,
		CarrierTIN,						EntryCarrier, Carrier,
		CarrierAddress,		
		CarrierContact,					Clerk,
		CustomsOfficeFirstEntry,		OfficeOfFirstEntry,
		CountryOfficeFirstEntry,		OriginalCountryOfFirstEntry,
		GoodsItem,						ArrivalItem,
		ShipmentNumber,					CommercialReferenceNumber,
		MRN,							//Same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EArrivalNotification) tag) {
			case MeansOfTransportBorder:	
			case TransportAtBorder:				//TODO Check - was in yellow in spreadsheet
				TransportMeans wrkMeansOfTransportBorder = new TransportMeans(getScanner());
				wrkMeansOfTransportBorder.parse(tag.name());
				setMeansOfTransportBorder(wrkMeansOfTransportBorder);
				break;
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());				
				break;
			case CarrierAddress:
				carrierAddress = new Address(getScanner());
				carrierAddress.parse(tag.name());				
				break;
			case CarrierContact:
			case Clerk:							//TODO Check - was in blue in spreadsheet
				carrierContact = new ContactPerson(getScanner());
				carrierContact.parse(tag.name());				
				break;
			case GoodsItem:
			case ArrivalItem:					//TODO Check - was in yellow in spreadsheet
				GoodsItemArn wrkGoodsItem = new GoodsItemArn(getScanner());
				wrkGoodsItem.parse(tag.name());
				goodsItemList.add(wrkGoodsItem);
				break;			
			case EntryCarrier:
			case Carrier:
				Trader carrierTrader = new Trader(getScanner());
				carrierTrader.parse(tag.name());	
				carrier = setPartyFromTrader(carrierTrader);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EArrivalNotification) tag) {
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
    			case DateOfPresentation:
    			case ArrivalDateAndTime:
    				 setDateOfPresentation(value);
    				 
    				 if (tag == EArrivalNotification.DateOfPresentation) {
    					 setDateOfPresentationFormat(EFormat.KIDS_DateTime);
    				 } else {
    					//EI20110208: setDateOfPresentationFormat(EFormat.ST_DateTimeT);
    					 setDateOfPresentationFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 break;		
    			case DeclaredDateOfArrival:
    			case ExpectedArrivalDateAndTime:
    				 setDeclaredDateOfArrival(value);
    				 
    				 if (tag == EArrivalNotification.DeclaredDateOfArrival) {
    					 setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);
    				 } else {
    					//EI20110208: setDeclaredDateOfArrivalFormat(EFormat.ST_DateTimeT);
    					 setDeclaredDateOfArrivalFormat(getUidsDateAndTimeFormat(value)); //EI20110208
    				 }
    				 break;		
    			case CountryOfficeFirstEntry:
    			case OriginalCountryOfFirstEntry:
    				 setCountryOfficeFirstEntry(value);    				 
    				 break;
    			case ConveyanceNumber:
    			case ConveyanceReference:
    				 setConveyanceReference(value);
    				 break;
    			case CustomsOfficeFirstEntry:
    			case OfficeOfFirstEntry:
    				 setCustomsOfficeFirstEntry(value);
    				 break;
    			default: break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {
		if (tag == EArrivalNotification.EntryCarrier) {
			inUIDSEntryCarrier = false;
		}
	}

	public Enum translate(String token) {
 		try {
  			return EArrivalNotification.valueOf(token);
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

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}

	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = declaredDateOfArrival;
	}

	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}

	public void setDeclaredDateOfArrivalFormat(EFormat declaredDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = declaredDateOfArrivalFormat;
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

	public String getDateOfPresentation() {
		return dateOfPresentation;
	}

	public void setDateOfPresentation(String dateOfPresentation) {
		this.dateOfPresentation = dateOfPresentation;
	}

	public String getCountryOfficeFirstEntry() {
		return countryOfficeFirstEntry;
	}

	public void setCountryOfficeFirstEntry(String countryOfficeFirstEntry) {
		this.countryOfficeFirstEntry = countryOfficeFirstEntry;
	}

	public List<GoodsItemArn> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<GoodsItemArn> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	public EFormat getDateOfPresentationFormat() {
		return dateOfPresentationFormat;
	}

	public void setDateOfPresentationFormat(EFormat dateOfPresentationFormat) {
		this.dateOfPresentationFormat = dateOfPresentationFormat;
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	}

	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = conveyanceReference;
	}
	public String getCustomsOfficeFirstEntry() {
		return customsOfficeFirstEntry;
	}

	public void setCustomsOfficeFirstEntry(String customsOfficeFirstEntry) {
		this.customsOfficeFirstEntry = customsOfficeFirstEntry;
	}
	public Party getCarrier() {
		if (carrierTIN != null || carrierAddress != null || carrierContact != null) {
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
		if (carrierContact != null) {
			carrier.setContactPerson(carrierContact);				
		}
		return carrier;
	}

	public void setCarrier(Party party) {		
		this.carrier = party;
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

	
	public void addGoodsItemList(GoodsItemArn goodsItem) {
		goodsItemList.add(goodsItem);
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
