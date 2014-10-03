package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemControl;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>.
 * Created		: 24.05.2013<br>
 * Description	: KIDS==Uids ControlDecision
 * 
 * @author krzoska
 * @version 2.0.00
 */

public class MsgControlDecision extends KCXMessage { 
	
	private String msgName = "ControlDecision";
	private String referenceNumber;
	private String referenceIdentifier;
	private String registrationNumber;	
	private String registrationDate;
	private String dateTimeOfControlDecision;
	private String edifactNumber;
	private String dateTimeOfReceipt;	
	private LocalApplication localApplication;
	private ArrayList<GoodsItemControl> goodsItemList;
	
	public MsgControlDecision() {
		super();
	}
	public MsgControlDecision(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EControlDecision {
		//KIDS 					 	UIDS:
		ReferenceNumber,
		ReferenceIdentifier,
		RegistrationNumber,
		RegistrationDate,
		DateOfControlDecision, DateTimeOfControlDecision,
		EdifactNumber,
		DateOfReceipt, DateTimeOfReceipt,
		LocalApplication,
		GoodsItem;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EControlDecision) tag) {
			
			case GoodsItem:
				GoodsItemControl goodsItem = new GoodsItemControl(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
				
			case LocalApplication:
				localApplication = new LocalApplication(getScanner());
				localApplication.parse(tag.name());	
				break;
				
			default:
				return;
			}
		} else {
			switch ((EControlDecision) tag) {			
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
				
			case ReferenceIdentifier:
				setReferenceIdentifier(value);
				break;
			
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;
				
			case RegistrationDate:
				setRegistrationDate(value);
				break;
				
			case DateOfControlDecision:	
			case DateTimeOfControlDecision:
				setDateTimeOfControlDecision(value);
				break;
				
			case EdifactNumber:			
				setEdifactNumber(value);
				break;
				
			case DateOfReceipt:		
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
			return EControlDecision.valueOf(token);
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
		
	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String referenceIdentifier) {
		this.referenceIdentifier = referenceIdentifier;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getDateTimeOfControlDecision() {
		return dateTimeOfControlDecision;
	}
	public void setDateTimeOfControlDecision(String dateOfControlDecision) {
		this.dateTimeOfControlDecision = Utils.checkNull(dateOfControlDecision);
	}

	public String getEdifactNumber() {
		return edifactNumber;
	}
	public void setEdifactNumber(String edifactNumber) {
		this.edifactNumber = edifactNumber;
	}	
	
	public ArrayList<GoodsItemControl> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(ArrayList<GoodsItemControl> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItemControl goodsItem) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItemControl>();
		}
		this.goodsItemList.add(goodsItem);
	}
	
	public String getDateTimeOfReceipt() {
		return dateTimeOfReceipt;
	}
	public void setDateTimeOfReceipt(String receiveDate) {
		this.dateTimeOfReceipt = Utils.checkNull(receiveDate);
	}
	
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String date) {
		this.registrationDate = Utils.checkNull(date);
	}
	
	public LocalApplication getLocalApplication() {
		return localApplication;
	}
	public void setLocalApplication(LocalApplication local) {
		this.localApplication = local;
	}
}
