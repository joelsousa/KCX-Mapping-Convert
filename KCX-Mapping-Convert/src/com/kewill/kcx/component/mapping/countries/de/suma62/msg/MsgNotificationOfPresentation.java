package com.kewill.kcx.component.mapping.countries.de.suma62.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
//import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;

/**
 * Module		: Manifest<br>.
 * Created		: 11.12.2012<br>
 * Description	: KIDS==Uids NotificationOfPresentation
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgNotificationOfPresentation extends KCXMessage { 
	
	private String msgName = "NotificationOfPresentation";
	private String referenceNumber;
	private String registrationNumber;	
	private String registrationNumberAllocated;	
	private String dateOfPresentation;	
	private String itemNumberAllocated;	
	private String containerQuantity;
	private String placeOfLoading;
	private String agentTIN;
	private String referenceIdentifier;
	private String messageFunction;
	private String flightNumber;
	private String nctsID;
	private String comments;
	private String port;
	private String typeOfPresentation;
	
	 private HeaderExtensions	headerExtensions	= null;
	 private PreviousDocument	previousDocument	= null;
	 private CustomsOffices		customsOffices		= null;
	 private Transport			transport			= null;
	 private ContactPerson		contact				= null;
	 private Trader 			presenter			= null;
	 private ManifestReference	reference			= null;
	 private ArrayList<GoodsItem> 	goodsItemList			= null;
		
		
	public MsgNotificationOfPresentation() {
		super();
	}
	public MsgNotificationOfPresentation(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum ENotificationOfPresentation {
		//KIDS == UIDS:
		AgentTIN,
		Contact,
		Presenter,
		ContainerQuantity,
		Comments,
		CustomsOffices,
		DateOfPresentation, 
		FlightNumber,
		ItemNumberAllocated,
		MessageFunction,
		NCTSID,
		PlaceOfLoading,
		Port,
		PreviousDocument,
		Reference, 
		ReferenceIdentifier,
		ReferenceNumber,
		RegistrationNumber,	
		RegistrationNumberAllocated,
		MeansOfTransport,              Transport,
		TypeOfPresentation,
		HeaderExtensions,
		GoodsItem;
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENotificationOfPresentation) tag) {
			case Contact:
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());
				break;						
			case Presenter:
				presenter = new Trader(getScanner());
				presenter.parse(tag.name());
				break;	
			case CustomsOffices:
				customsOffices = new CustomsOffices(getScanner());
				customsOffices.parse(tag.name());
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
			case HeaderExtensions:
				headerExtensions = new HeaderExtensions(getScanner());
				headerExtensions.parse(tag.name());
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
			switch ((ENotificationOfPresentation) tag) {
			case DateOfPresentation:		
				setDateOfPresentation(value);
				break;
			case FlightNumber:
				setFlightNumber(value);
				break;
			case Comments:
				setComments(value);
				break;
			case Port:
				setPort(value);
				break;
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;
			case RegistrationNumberAllocated:
				setRegistrationNumberAllocated(value);
				break;
			case ItemNumberAllocated:
				setItemNumberAllocated(value);
				break;
			case MessageFunction:
				setMessageFunction(value);
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
			case AgentTIN:
				setAgentTIN(value);
				break;
			case ReferenceIdentifier:
				setReferenceIdentifier(value);
				break;
			case TypeOfPresentation:
				setTypeOfPresentation(value);
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
			return ENotificationOfPresentation.valueOf(token);
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
	public void setDateOfPresentation(String dateOfPresentation) {
		this.dateOfPresentation = dateOfPresentation;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getRegistrationNumberAllocated() {
		return registrationNumberAllocated;
	}
	public void setRegistrationNumberAllocated(String registrationNumberAllocated) {
		this.registrationNumberAllocated = registrationNumberAllocated;
	}
	public String getItemNumberAllocated() {
		return itemNumberAllocated;
	}
	public void setItemNumberAllocated(String itemNumberAllocated) {
		this.itemNumberAllocated = itemNumberAllocated;
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
	public String getAgentTIN() {
		return agentTIN;
	}
	public void setAgentTIN(String agentTIN) {
		this.agentTIN = agentTIN;
	}
	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String referenceIdentifier) {
		this.referenceIdentifier = referenceIdentifier;
	}
	public String getMessageFunction() {
		return messageFunction;
	}
	public void setMessageFunction(String messageFunction) {
		this.messageFunction = messageFunction;
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
	
	public String getComments() {
		return comments;
	}
	public void setComments(String value) {
		this.comments = value;
	}
	
	public String getPort() {
		return port;
	}
	public void setPort(String value) {
		this.port = value;
	}
	
	public String getTypeOfPresentation() {
		return typeOfPresentation;
	}
	public void setTypeOfPresentation(String value) {
		this.typeOfPresentation = value;
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
	public CustomsOffices getCustomsOffices() {
		return customsOffices;
	}
	public void setCustomsOffices(CustomsOffices customsOffices) {
		this.customsOffices = customsOffices;
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
	public Trader getPresenter() {
		return presenter;
	}
	public void setPresenter(Trader presenter) {
		this.presenter = presenter;
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
	
}
