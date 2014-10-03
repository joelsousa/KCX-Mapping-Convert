package com.kewill.kcx.component.mapping.countries.de.cmp.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: MsgCmpCompleteData.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MsgCmpCompleteData extends KCXMessage { 
	
	private String msgName = "CMPCompleteDataFormat";
	private String purposeCode = "";               //EI20130823
	private FlightManifestMessage flightManifestMessage;
	private FreightWayBill waybillData;
	private ArrayList <FreightWayBill> waybillDataList;   //EI20131011
	private FlightStatusUpdate flightStatusUpdate;	
	private CustomsStatusNotification statusInformationData;
		
	public MsgCmpCompleteData() {
		super();
	}
	public MsgCmpCompleteData(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EMsgCmpCompleteData {
		//KIDS == UIDS:
		PurposeCode,
		FlightManifestMessage, 					 FlightManifest,
		FreightWayBill,	FreightWaybill,	 		 WayBill, Waybill,
		FlightStatusUpdate, FlightStatusMessage, StatusMessage,
		CustomsStatusNotification;  //name unveraendern	
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgCmpCompleteData) tag) {
			case FlightManifestMessage:
			case FlightManifest:
				flightManifestMessage = new FlightManifestMessage(getScanner());
				flightManifestMessage.parse(tag.name());
				break;	
				
			case FreightWayBill: case FreightWaybill:			
			case WayBill: case Waybill:
				waybillData = new FreightWayBill(getScanner());
				waybillData.parse(tag.name());
				addWayBillList(waybillData);
				break;	
				
			case FlightStatusUpdate: case FlightStatusMessage:			
			case StatusMessage:
				flightStatusUpdate = new FlightStatusUpdate(getScanner());
				flightStatusUpdate.parse(tag.name());
				break;	
				
			case CustomsStatusNotification:
				statusInformationData = new CustomsStatusNotification(getScanner());
				statusInformationData.parse(tag.name());
				break;
				
			default:
				return;
			}
		} else {
			switch ((EMsgCmpCompleteData) tag) {
			case PurposeCode:
				setPurposeCode(value);
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
			return EMsgCmpCompleteData.valueOf(token);
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
	
	public FlightManifestMessage getFlightManifestMessage() {
		return flightManifestMessage;
	}
	public void setFlightManifestMessage(FlightManifestMessage data) {
		this.flightManifestMessage = data;
	}
	
	public FreightWayBill getFreightWayBill() {
		return waybillData;
	}
	public void setFreightWayBill(FreightWayBill data) {
		this.waybillData = data;
	}
	//EI20131011:
	public ArrayList<FreightWayBill> getFreightWayBillList() {
		return waybillDataList;
	}
	public void setFreightWayBillList(ArrayList<FreightWayBill> data) {
		this.waybillDataList = data;
	}
	public void addWayBillList(FreightWayBill data) {
		if (data == null) {
			return;	
		}
		if (waybillDataList == null) {
			waybillDataList = new ArrayList<FreightWayBill>();
		}
		this.waybillDataList.add(data);
	}
	
	public FlightStatusUpdate getFlightStatusUpdate() {
		return flightStatusUpdate;
	}
	public void setFlightStatusUpdate(FlightStatusUpdate data) {
		this.flightStatusUpdate = data;
	}
	
	public CustomsStatusNotification getCustomsStatusNotification() {
		return statusInformationData;
	}
	public void setCustomsStatusNotification(CustomsStatusNotification value) {
		this.statusInformationData = value;
	}
	public String getPurposeCode() {
		return purposeCode;
	}
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	
}
