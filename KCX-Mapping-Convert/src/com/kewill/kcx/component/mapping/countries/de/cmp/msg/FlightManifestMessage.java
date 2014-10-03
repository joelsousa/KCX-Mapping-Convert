package com.kewill.kcx.component.mapping.countries.de.cmp.msg;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ArrivalEvent;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportMovementFFM;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageBusinessHeader;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageHeaderDocument;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 17.07.2013<br>
* Description	: FlightManifestData (FFM).
* 
* @author iwaniuk
* @version 1.0.00
*/

public class FlightManifestMessage extends KCXMessage {
	
	private MessageHeaderDocument 	messageHeaderDocument;   
	private MessageBusinessHeader 	businessHeaderDocument;  
	private TransportMovementFFM logisticsTransportMovement;       
	private ArrayList<ArrivalEvent> arrivalEventList; 
	
	private enum EFlightManifestData {	
		MessageHeaderDocument,
		BusinessHeaderDocument,  //only two Tags: ID, IncludedHeaderNote
		LogisticsTransportMovement,
		ArrivalEvent,
	}
	
	public FlightManifestMessage() {
		super();  
	}

    public FlightManifestMessage(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EFlightManifestData) tag) {
  			
  			case MessageHeaderDocument:
  				messageHeaderDocument = new MessageHeaderDocument(getScanner());
  				messageHeaderDocument.parse(tag.name());  				
				break;
				
  			case BusinessHeaderDocument:
  				businessHeaderDocument = new MessageBusinessHeader(getScanner());
  				businessHeaderDocument.parse(tag.name());
				break;
				
  			case LogisticsTransportMovement:
  				logisticsTransportMovement = new TransportMovementFFM(getScanner());
  				logisticsTransportMovement.parse(tag.name());
  				break;
				
  			case ArrivalEvent:
  				ArrivalEvent event = new ArrivalEvent(getScanner());
  				event.parse(tag.name());
  				addArrivalEventList(event);
				break;
			
  			default:
  					return;
  			}
  		} else {
  			switch ((EFlightManifestData) tag) {  	
  			
  	  			default:
  					return;
  			}
  		}		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		 try {
				return EFlightManifestData.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}
		

	public MessageHeaderDocument getMessageHeaderDocument() {
		return messageHeaderDocument;
	}

	public void setMessageHeaderDocument(MessageHeaderDocument messageHeaderDocument) {
		this.messageHeaderDocument = messageHeaderDocument;
	}

	public MessageBusinessHeader getBusinessHeaderDocument() {
		return businessHeaderDocument;
	}

	public void setBusinessHeaderDocument(MessageBusinessHeader businessHeaderDocument) {
		this.businessHeaderDocument = businessHeaderDocument;
	}

	public TransportMovementFFM getLogisticsTransportMovement() {
		return logisticsTransportMovement;
	}

	public void setLogisticsTransportMovement(
			TransportMovementFFM logisticsTransportMovement) {
		this.logisticsTransportMovement = logisticsTransportMovement;
	}

	public  ArrayList<ArrivalEvent> getArrivalEventList() {
		return arrivalEventList;
	}
	public void setArrivalEventList(ArrayList<ArrivalEvent> list) {
		this.arrivalEventList = list;
	}
	public void addArrivalEventList(ArrivalEvent dest) {
		if (this.arrivalEventList == null) {
			this.arrivalEventList = new ArrayList<ArrivalEvent>();
		}
		this.arrivalEventList.add(dest);
	}
	
	public boolean isEmpty() {
		return 	(messageHeaderDocument == null || messageHeaderDocument.isEmpty()) &&
				(businessHeaderDocument == null || businessHeaderDocument.isEmpty()) &&
				(logisticsTransportMovement == null || logisticsTransportMovement.isEmpty()) &&
				(arrivalEventList == null); 
	}
}
