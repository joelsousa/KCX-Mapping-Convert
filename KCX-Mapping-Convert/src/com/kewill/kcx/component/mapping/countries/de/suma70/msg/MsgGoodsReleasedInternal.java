package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;

/**
 * Module		: Manifest<br>.
 * Created		: 05.06.2013<br>
 * Description	: KIDS GoodsReleasedInternal
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgGoodsReleasedInternal extends KCXMessage { 
	
	private String			 	msgName = "GoodsReleasedInternal";
	private String			 	dateOfPresentation;
	private String			 	referenceNumber;
	private String			 	registrationNumber;
	private String			 	registrationDate;
	//private String			 	flightNumber;
	private String			 	placeOfLoading;
	private String				dateTimeOfReceipt;
	private PreviousDocument	previousDocument;
	//private String				transportationNumber;
	private Transport			transport;                    
	private String				arrivalDate;
	private String				edifactNumber;
	private LocalApplication localApplication;
	private ArrayList<GoodsItem> goodsItemList;
		
		
	public MsgGoodsReleasedInternal() {
		super();
	}
	public MsgGoodsReleasedInternal(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	
	private enum EGoodsReleasedInternal {
		//KIDS:							UIDS:
		DateOfPresentation,        		
		//FlightNumber,
		ReferenceNumber,					
		RegistrationNumber,
		RegistrationDate,
		//TransportationNumber,
		MeansOfTransport,
		ArrivalDate, DateOfArrival,
		PlaceOfLoading,
		PreviousDocument,		
		DateTimeOfReceipt,
		EDIFACTNumber, EdifactNumber,		
		LocalApplication,
		GoodsItem;	
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EGoodsReleasedInternal) tag) {
			case GoodsItem:				
				GoodsItem goodsItem = new  GoodsItem(getScanner(), msgName);
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
				
			case PreviousDocument:
				previousDocument = new PreviousDocument(getScanner());
				previousDocument.parse(tag.name());
				break;
				
			case MeansOfTransport:     
				transport = new Transport(getScanner());
				transport.parse(tag.name());
				break;	
								
			case LocalApplication:
				localApplication = new LocalApplication(getScanner());
				localApplication.parse(tag.name());	
				break;
				
			default:
				return;
			}
		} else {
			switch ((EGoodsReleasedInternal) tag) {
			case DateOfPresentation:		
				setDateOfPresentation(value);
				break;
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;
			case RegistrationDate:
				setRegistrationDate(value);
				break;
			/*	
			case FlightNumber:
				setFlightNumber(value);
				break;
			case TransportationNumber:
				setTransportationNumber(value);
				break;
			*/	
			case ArrivalDate:
			case DateOfArrival:
				setDateOfArrival(value);
				break;
			case PlaceOfLoading:
				setPlaceOfLoading(value);
				break;
			case DateTimeOfReceipt:
				setDateTimeOfReceipt(value);
				break;
			case EDIFACTNumber:
			case EdifactNumber:
				setEdifactNumber(value);
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
			return EGoodsReleasedInternal.valueOf(token);
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
			
	public String getPlaceOfLoading() {
		return placeOfLoading;
	}
	public void setPlaceOfLoading(String placeOfLoading) {
		this.placeOfLoading = placeOfLoading;
	}
	public PreviousDocument getPreviousDocument() {
		return previousDocument;
	}
	public void setPreviousDocument(PreviousDocument previousDocument) {
		this.previousDocument = previousDocument;
	}
	public ArrayList<GoodsItem> getGoodsItemList() {
		return goodsItemList;
	}
	
	public void addGoodsItemList(GoodsItem goodsItem) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItem>();
		}
		this.goodsItemList.add(goodsItem);
	}
	/*
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getTransportationNumber() {
		return transportationNumber;
	}
	public void setTransportationNumber(String transportationNumber) {
		this.transportationNumber = Utils.checkNull(transportationNumber);
	}
	*/
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getDateOfArrival() {
		return arrivalDate;
	}
	public void setDateOfArrival(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getDateTimeOfReceipt() {
		return dateTimeOfReceipt;
	}
	
	public void setDateTimeOfReceipt(String receiveDate) {
		this.dateTimeOfReceipt = receiveDate;
	}
	public String getEdifactNumber() {
		return edifactNumber;
	}
	public void setEdifactNumber(String edifactNumber) {
		this.edifactNumber = edifactNumber;
	}
	public void setGoodsItemList(ArrayList<GoodsItem> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
	
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public LocalApplication getLocalApplication() {
		return localApplication;
	}
	public void setLocalApplication(LocalApplication local) {
		this.localApplication = local;
	}
}
