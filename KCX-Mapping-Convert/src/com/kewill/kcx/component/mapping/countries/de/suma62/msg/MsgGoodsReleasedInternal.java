package com.kewill.kcx.component.mapping.countries.de.suma62.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>.
 * Created		: 05.11.2012<br>
 * Description	: KIDS GoodsReleasedInternal
 * 
 * @author Kron
 * @version 1.0.00
 */
public class MsgGoodsReleasedInternal extends KCXMessage { 
	
	private String			 	msgName = "GoodsReleasedInternal";
	private String			 	dateOfPresentation;
	private String			 	referenceNumber;
	private String			 	registrationNumber;
	private String			 	flightNumber;
	private String			 	placeOfLoading;
	private PreviousDocument	previousDocument;	
	private String				transportationNumber;
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
		FlightNumber,
		ReferenceNumber,					
		RegistrationNumber,
		PlaceOfLoading,
		PreviousDocument,
		TransportationNumber,
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
			case FlightNumber:
				setFlightNumber(value);
				break;
			case PlaceOfLoading:
				setPlaceOfLoading(value);
				break;
			case TransportationNumber:
				setTransportationNumber(value);
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
		
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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
	public String getTransportationNumber() {
		return transportationNumber;
	}
	public void setTransportationNumber(String transportationNumber) {
		this.transportationNumber = Utils.checkNull(transportationNumber);
	}
	public void setGoodsItemList(ArrayList<GoodsItem> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
}
