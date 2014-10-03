package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Notification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>.
 * Created		: 05.02.2013<br>
 * Description	: KIDS ProcessingResults
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgProcessingResults extends KCXMessage { 
	
	private String	msgName = "ProcessingResults";	
	private String  dateOfArrival;      
	private String	referenceNumber;
	private String	registrationNumber;		
	private String  dateOfRegistration;      
	private String 	typeOfTransaction;
	private Transport transport;
	private String  edifactNumber;
	private HeaderExtensions    headerExtensions;
	private ArrayList<Notification> notificationList;
	private ArrayList<ItemProcessingResults> goodsItemList;    
	private LocalApplication localApplication;
	private String  dateTimeOfReceipt;                      //EI20131021
		
	public MsgProcessingResults() {
		super();
	}
	public MsgProcessingResults(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EProcessingResults {
		//KIDS:		
		ReferenceNumber,
		RegistrationNumber,		
		RegistrationDate,
		TypeOfTransaction,
		MeansOfTransport,
		DateOfArrival,		
		EdifactNumber,
		HeaderExtensions,
		Notification,			
		GoodsItem,
		LocalApplication,
		DateTimeOfReceipt,
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EProcessingResults) tag) {
			
			case MeansOfTransport:      
				transport = new Transport(getScanner());
				transport.parse(tag.name());
				break;
				
			case HeaderExtensions:
				headerExtensions = new HeaderExtensions(getScanner());
				headerExtensions.parse(tag.name());
				break;
				
			case GoodsItem:
				ItemProcessingResults item = new ItemProcessingResults(getScanner());
				item.parse(tag.name());				
				this.addGoodsItemList(item);
				break;	
				
			case Notification:
				Notification notification = new Notification(getScanner());
				notification.parse(tag.name());				
				this.addNotificationList(notification);
				break;	
			
			case LocalApplication:
				localApplication = new LocalApplication(getScanner());
				localApplication.parse(tag.name());	
				break;				
				
			default:
				return;
			}
		} else {
			switch ((EProcessingResults) tag) {
			
			case DateOfArrival:		
				setDateOfArrival(value);
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
			case TypeOfTransaction:
				setTypeOfTransaction(value);
				break;
			case EdifactNumber:
				setTypeOfTransaction(value);
				break;
			case DateTimeOfReceipt:
				setDateTimeOfReceipt(value);
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
			return EProcessingResults.valueOf(token);
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
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}
	
	public HeaderExtensions getHeaderExtensions() {
		return headerExtensions;
	}
	public void setHeaderExtensions(HeaderExtensions headerExtensions) {
		this.headerExtensions = headerExtensions;
	}
	
	public LocalApplication getLocalApplication() {
		return localApplication;
	}
	public void setLocalApplication(LocalApplication local) {
		this.localApplication = local;
	}
	
	public ArrayList<Notification> getNotificationList() {
		return notificationList;
	}
	public void setNotificationList(ArrayList<Notification> list) {
		this.notificationList = list;
	}
	
	public void addNotificationList(Notification item) {
		if (notificationList == null) {
			notificationList = new ArrayList<Notification>();
		}
		notificationList.add(item);		
	}
		
	public String getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(String dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}	
	
	public String getRegistrationDate() {
		return dateOfRegistration;
	}
	public void setRegistrationDate(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public ArrayList<ItemProcessingResults> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(ArrayList<ItemProcessingResults> list) {
		this.goodsItemList = list;
	}
	
	public void addGoodsItemList(ItemProcessingResults item) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<ItemProcessingResults>();
		}
		goodsItemList.add(item);		
	}
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = Utils.checkNull(dateOfRegistration);
	}
	public String getEdifactNumber() {
		return edifactNumber;
	}
	public void setEdifactNumber(String edifactNumber) {
		this.edifactNumber = Utils.checkNull(edifactNumber);
	}
		
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public String getDateTimeOfReceipt() {
		return dateTimeOfReceipt;
	}
	public void setDateTimeOfReceipt(String messageDateTime) {
		this.dateTimeOfReceipt = messageDateTime;
	}
	
	
}
