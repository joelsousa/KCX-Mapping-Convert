package com.kewill.kcx.component.mapping.countries.de.suma62.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;


/**
 * Module		: Manifest<br>.
 * Created		: 05.02.2013<br>
 * Description	: KIDS ProcessingResults
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgProcessingResults extends KCXMessage { 
	
	private String			 	msgName = "ProcessingResults";
	private String			 	flightNumber;
	private String			 	referenceNumber;
	private String			 	registrationNumber;		
	private String 			    typeOfTransaction;	
	private HeaderExtensions    headerExtensions;
	private ArrayList<ItemProcessingResults> goodsItemList;    
		
	public MsgProcessingResults() {
		super();
	}
	public MsgProcessingResults(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EGoodsReleasedExternal {
		//KIDS:							UIDS:
		FlightNumber,        		
		ReferenceNumber,					
		RegistrationNumber,			
		TypeOfTransaction,
		HeaderExtensions,
		GoodsItem;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EGoodsReleasedExternal) tag) {
			case HeaderExtensions:
				headerExtensions = new HeaderExtensions(getScanner());
				headerExtensions.parse(tag.name());
				break;						
			case GoodsItem:
				ItemProcessingResults item = new ItemProcessingResults(getScanner());
				item.parse(tag.name());				
				this.addGoodsItemList(item);
				break;							
			default:
				return;
			}
		} else {
			switch ((EGoodsReleasedExternal) tag) {
			case FlightNumber:		
				setFlightNumber(value);
				break;
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;
			case TypeOfTransaction:
				setTypeOfTransaction(value);
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
			return EGoodsReleasedExternal.valueOf(token);
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
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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
}
