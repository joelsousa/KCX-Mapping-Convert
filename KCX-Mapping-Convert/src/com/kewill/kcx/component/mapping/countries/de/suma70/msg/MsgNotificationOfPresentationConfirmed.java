package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.AddressType;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>.
 * Created		: 11.06.2013<br>
 * Description	: KIDS==Uids NotificationOfPresentationConfirmed
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MsgNotificationOfPresentationConfirmed extends KCXMessage { 
	
	private String msgName = "NotificationOfPresentationConfirmed";
	private String briefCargoDescription;
	private String containerQuantity;
	private String dateOfPresentation;	
	private String dateOfArrival;	
	private String flightNumber;
	private String grossMass;
	private String nctsID;
	private String placeOfLoading;
	private String referenceNumber;
	private String referenceIdentifier;	
	private String sumACustomsOfficeIsOfficeIsOfFirstEntry;	
	private TIN	 agentTIN;		
	private Party presenter;		
	private ContactPerson        contact;
	private ManifestReference	 reference;
	private CustomsOffices		 customsOffices;
	private PreviousDocument	 previousDocument;	
	private Transport		     transport;	
	private Transport			 transportAtBorder;
	private HeaderExtensions	 headerExtensions;	
	private ArrayList<GoodsItem> goodsItemList;
	
	public MsgNotificationOfPresentationConfirmed() {
		super();
	}
	public MsgNotificationOfPresentationConfirmed(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
		  
	private enum ENotificationOfPresentationConfirmed {
		//KIDS == UIDS:
		AgentTIN,					AgentTin,		
		PresenterTIN,
		PresenterAddress,			Presenter,	
		Contact,
		BriefCargoDescription,			
		DateOfPresentation, 
		DateOfArrival,		
		ReferenceNumber,
		ReferenceIdentifier,
		Reference, 	
		NCTSID,
		CustomsOffices,
		ContainerQuantity,
		GrossMass,	
		PlaceOfLoading,
		PreviousDocument,		
		MeansOfTransport,              Transport,
		MeansOfTransportAtBorder, 
		HeaderExtensions,
		SumACustomsOfficeIsOfficeIsOfFirstEntry,
		GoodsItem,		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENotificationOfPresentationConfirmed) tag) {
			case Contact:
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());
				break;						
			
			case AgentTIN:	
			case AgentTin:	
				TIN agentTIN = new TIN(getScanner());
				agentTIN.parse(tag.name());
				
				break;
				
			case PresenterTIN:			
				TIN presenterTIN = new TIN(getScanner());
				presenterTIN.parse(tag.name());		
				if (presenter == null) {
					presenter = new Party();
				}
				presenter.setPartyTIN(presenterTIN);
				break;
				
			case PresenterAddress:			
				AddressType presenterAddress = new AddressType(getScanner());
				presenterAddress.parse(tag.name());
				if (presenter == null) {
					presenter = new Party();
				}
				presenter.setAddress(presenterAddress.getAddress());
				break;
				
			case Presenter:   //kann nur von UIDS-seite kommen
			    Trader preTrader = new Trader(getScanner());						
			    preTrader.parse(tag.name());
			    presenter = mapTraderToParty(preTrader);			    
				break;	
				
			case PreviousDocument:
				previousDocument = new PreviousDocument(getScanner());
				previousDocument.parse(tag.name());
				break;
				
			case Reference:
				reference = new ManifestReference(getScanner());
				reference.parse(tag.name());
				break;
				
			case Transport:
			case MeansOfTransport:      //EI20120116: xsd
				transport = new Transport(getScanner());
				transport.parse(tag.name());
				break;	
				
			case MeansOfTransportAtBorder:
				transportAtBorder = new Transport(getScanner());
				transportAtBorder.parse(tag.name());
				break;
				
			case HeaderExtensions:
				headerExtensions = new HeaderExtensions(getScanner());
				headerExtensions.parse(tag.name());
				break;
				
			case CustomsOffices:
				customsOffices = new CustomsOffices(getScanner());
				customsOffices.parse(tag.name());
				break;
				
			case GoodsItem:
				GoodsItem goodsItem = new GoodsItem(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
			default:
				return;
			}
		} else {
			switch ((ENotificationOfPresentationConfirmed) tag) {
			case DateOfPresentation:		
				setDateOfPresentation(value);
				break;
							
			case DateOfArrival:		
				setDateOfArrival(value);
				break;
				
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			
			case NCTSID:
				setNctsID(value);
				break;
				
			case ContainerQuantity:
				setContainerQuantity(value);
				break;
				
			case PlaceOfLoading:
				setPlaceOfLoading(value);
				break;
				
			case BriefCargoDescription:
				setBriefCargoDescription(value);
				break;
				
			case GrossMass:
				setGrossMass(value);
				break;
				
			case ReferenceIdentifier:
				setReferenceIdentifier(value);
				break;		
				
			case AgentTIN:	//kann nur von UIDS-seite kommen
			case AgentTin:	
				setUidsAgentTin(value);
				break;
				
			case SumACustomsOfficeIsOfficeIsOfFirstEntry:
				setSumACustomsOfficeIsOfficeIsOfFirstEntry(value);
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
			return ENotificationOfPresentationConfirmed.valueOf(token);
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
	
	public String getDateOfPresentation() {
		return dateOfPresentation;
	}
	public void setDateOfPresentation(String date) {
		this.dateOfPresentation = date;
	}
	
	public String getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(String date) {
		this.dateOfArrival = date;
	}
	
	public String getContainerQuantity() {
		return containerQuantity;
	}
	public void setContainerQuantity(String containerQuantity) {
		this.containerQuantity = containerQuantity;
	}
	public String getPlaceOfLoading() {
		return placeOfLoading;
	}
	public void setPlaceOfLoading(String placeOfLoading) {
		this.placeOfLoading = placeOfLoading;
	}
	public String getBriefCargoDescription() {
		return briefCargoDescription;
	}
	public void setBriefCargoDescription(String briefCargoDescription) {
		this.briefCargoDescription = Utils.checkNull(briefCargoDescription);
	}
	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String grossMass) {
		this.grossMass = Utils.checkNull(grossMass);
	}
	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String referenceIdentifier) {
		this.referenceIdentifier = referenceIdentifier;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getNctsID() {
		return nctsID;
	}
	public void setNctsID(String nctsID) {
		this.nctsID = nctsID;
	}
	
	public HeaderExtensions getHeaderExtensions() {
		return headerExtensions;
	}
	public void setHeaderExtensions(HeaderExtensions headerExtensions) {
		this.headerExtensions = headerExtensions;
	}
	public PreviousDocument getPreviousDocument() {
		return previousDocument;
	}
	public void setPreviousDocument(PreviousDocument previousDocument) {
		this.previousDocument = previousDocument;
	}
	
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}
	
	public ManifestReference getReference() {
		return reference;
	}
	public void setReference(ManifestReference reference) {
		this.reference = reference;
	}
	
	public ArrayList<GoodsItem> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(ArrayList<GoodsItem> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItem goodsItem) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItem>();
		}
		this.goodsItemList.add(goodsItem);
	}
	
	public TIN getAgentTIN() {		
		return this.agentTIN;
	}
	public void setAgentTIN(TIN tin) {
		this.agentTIN = tin;
	}
	private void setUidsAgentTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		this.agentTIN = new TIN();
		this.agentTIN.setTIN(value);		
	}
	
	public Party getPresenter() {		
		return presenter;
	}
	public void setPresenter(Party party) {
		this.presenter = party;
	}
	
	private Party mapTraderToParty(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party = new Party();
		if (!Utils.isStringEmpty(trader.getTIN())) {
			TIN partyTIN = new TIN();
			partyTIN.setTIN(trader.getTIN());
			partyTIN.setBO(trader.getBranch());
			partyTIN.setCustomerIdentifier(trader.getCustomerID());
			partyTIN.setIdentificationType(trader.getTINType());
			party.setPartyTIN(partyTIN);
		}
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());	
		
		return party;
	}
	
	public CustomsOffices getCustomsOffices() {
		return customsOffices;
	}
	public void setCustomsOffices(CustomsOffices customsOffices) {
		this.customsOffices = customsOffices;
	}
	
	public Transport getTransportAtBorder() {
		return transportAtBorder;
	}
	public void setTransportAtBorder(Transport transport) {
		this.transportAtBorder = transport;
	}
	
	public String getSumACustomsOfficeIsOfficeIsOfFirstEntry() {
		return sumACustomsOfficeIsOfficeIsOfFirstEntry;
	}
	public void setSumACustomsOfficeIsOfficeIsOfFirstEntry(
			String sumACustomsOfficeIsOfficeIsOfFirstEntry) {
		this.sumACustomsOfficeIsOfficeIsOfFirstEntry = sumACustomsOfficeIsOfficeIsOfFirstEntry;
	}
}
