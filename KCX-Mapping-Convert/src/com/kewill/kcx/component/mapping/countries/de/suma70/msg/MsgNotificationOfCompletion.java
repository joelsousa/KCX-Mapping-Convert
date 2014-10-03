package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>.
 * Created		: 24.05.2013<br>
 * Description	: KIDS==Uids NotificationOfCompletion/NotficationOfSettlement
 * 
 * @author krzoska
 * @version 2.0.00
 */

public class MsgNotificationOfCompletion extends KCXMessage { 
	
	private String msgName = "NotificationOfCompletion";
	
	private String referenceNumber;
	private String registrationNumber;
	private String registrationDate;
	private String typeOfTransaction;
	private String registrationNumberOfCompletion;
	//private String transportationNumber;
	private Transport transport;
	private String dateOfArrival;
	private String dateTimeOfReceipt;
	private ManifestReference reference;
	private String edifactNumber;
	private ArrayList<GoodsItemCompletion> goodsItemList;
	private LocalApplication localApplication;
	
	public MsgNotificationOfCompletion() {
		super();
	}
	public MsgNotificationOfCompletion(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum ENotificationOfCompletion {
		//KIDS 					 	UIDS:		
		ReferenceNumber,
		RegistrationNumber,
		RegistrationDate,
		TypeOfTransaction,
		RegistrationNumberOfCompletion,
		MeansOfTransport, //TransportationNumber,
		DateOfArrival,
		DateOfReceipt, DateTimeOfReceipt,
		Reference,
		EdifactNumber, EDIFACTNumber,
		LocalApplication,
		GoodsItem;		
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENotificationOfCompletion) tag) {
			
			case Reference: 
				ManifestReference reference = new ManifestReference(getScanner());
				reference.parse(tag.name());				
				break;
				
			case GoodsItem:
				GoodsItemCompletion goodsItem = new GoodsItemCompletion(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
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
			switch ((ENotificationOfCompletion) tag) {	
						
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
							
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;
				
			case RegistrationDate:
				setRegistrationDate(value);
				break;
			
			case TypeOfTransaction:			
				setTypeOfTransaction(value);
				break;
				
			case RegistrationNumberOfCompletion:
				setRegistrationNumberOfCompletion(value);
				break;
			
			case DateOfArrival:
				setDateOfArrival(value);
				break;
				
			case DateOfReceipt:	
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
			return ENotificationOfCompletion.valueOf(token);
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
		
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public ManifestReference getReference() {
		return reference;  
	}
	public void setReference(ManifestReference reference) {
		this.reference = reference;
	}
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String value) {
		this.typeOfTransaction = value;
	}
	
	public String getRegistrationNumberOfCompletion() {
		return registrationNumberOfCompletion;
	}
	public void setRegistrationNumberOfCompletion(String regnr) {
		this.registrationNumberOfCompletion = regnr;
	}
	
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public ArrayList<GoodsItemCompletion> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(ArrayList<GoodsItemCompletion> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItemCompletion goodsItem) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItemCompletion>();
		}
		this.goodsItemList.add(goodsItem);
	}
	
	public String getDateTimeOfReceipt() {
		return dateTimeOfReceipt;
	}
	public void setDateTimeOfReceipt(String receiveDate) {
		this.dateTimeOfReceipt = Utils.checkNull(receiveDate);
	}
	
	public String getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(String dateOfArrival) {
		this.dateOfArrival = Utils.checkNull(dateOfArrival);
	}
	public String getEdifactNumber() {
		return edifactNumber;
	}
	public void setEdifactNumber(String edifactNumber) {
		this.edifactNumber = edifactNumber;
	}	

	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public LocalApplication getLocalApplication() {
		return localApplication;
	}
	public void setLocalApplication(LocalApplication local) {
		this.localApplication = local;
	}	
		
	public String getCompletionOtherReference() {
		String ref = "";
		if (this.reference != null) {
			ref = this.reference.getRegistrationNumber();  //TODO: ???
		}
		return ref;
	}
}
