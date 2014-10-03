package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PortsAndPlaces extends KCXMessage {
		
	private Place portOfLoading;
	private Place portOfDischarge;
	private Place placeOfDeparture;
	private Place placeOfReceipt;
	private Place placeOfDelivery;
	private Place placeOfAcceptance;
	private Place placeOfTranshipment;
	private Place onCarriagePort;
	private Place preCarriagePort;
	private Place placeOfDestination;
	
	private enum ECarriage {	
		PortOfLoading,
		PortOfDischarge,
		PlaceOfDeparture,
		PlaceOfReceipt,
		PlaceOfDelivery,
		PlaceOfAcceptance,
		PlaceOfTranshipment,
		OnCarriagePort,
		PreCarriagePort,		
		PlaceOfDestination;			       		
   }	

	public PortsAndPlaces() {
		super();  
	}

	public PortsAndPlaces(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case PortOfLoading:
  					portOfLoading = new Place(getScanner());  	
  					portOfLoading.parse(tag.name());
					break; 
  				case PortOfDischarge:
  					portOfDischarge = new Place(getScanner());  	
  					portOfDischarge.parse(tag.name());  					
  					break;
  				case PlaceOfDeparture:
  					placeOfDeparture = new Place(getScanner());  	
  					placeOfDeparture.parse(tag.name());  					
  					break;
  				case PlaceOfReceipt:
  					placeOfReceipt = new Place(getScanner());  	
  					placeOfReceipt.parse(tag.name());
					break; 
  				case PlaceOfDelivery:
  					placeOfDelivery = new Place(getScanner());  	
  					placeOfDelivery.parse(tag.name());  					
  					break;
  				case PlaceOfAcceptance:
  					placeOfAcceptance = new Place(getScanner());  	
  					placeOfAcceptance.parse(tag.name());  					
  					break;
  				case PlaceOfTranshipment:
  					placeOfTranshipment = new Place(getScanner());  	
  					placeOfTranshipment.parse(tag.name());
					break; 
  				case OnCarriagePort:
  					onCarriagePort = new Place(getScanner());  	
  					onCarriagePort.parse(tag.name());  					
  					break;
  				case PreCarriagePort:
  					preCarriagePort = new Place(getScanner());  	
  					preCarriagePort.parse(tag.name());  					
  					break;
  				case PlaceOfDestination:
  					placeOfDestination = new Place(getScanner());  	
  					placeOfDestination.parse(tag.name());  					
  					break;
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   			  				   			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public Place getPortOfLoading() {
		return portOfLoading;
	}    
	public void setPortOfLoading(Place value) {
		this.portOfLoading = value;
	}
		
	public Place getPortOfDischarge() {
		return portOfDischarge;
	}    
	public void setPortOfDischarge(Place value) {
		this.portOfDischarge = value;
	}
	
	public Place getPlaceOfDeparture() {
		return placeOfDeparture;
	}    
	public void setPlaceOfDeparture(Place value) {
		this.placeOfDeparture = value;
	}
		
	public Place getPlaceOfReceipt() {
		return placeOfReceipt;
	}    
	public void setPlaceOfReceipt(Place value) {
		this.placeOfReceipt = value;
	}
	
	public Place getPlaceOfDelivery() {
		return placeOfDelivery;
	}    
	public void setPlaceOfDelivery(Place value) {
		this.placeOfDelivery = value;
	}
		
	public Place getPlaceOfAcceptance() {
		return placeOfAcceptance;
	}    
	public void setPlaceOfAcceptance(Place value) {
		this.placeOfAcceptance = value;
	}
	
	public Place getPlaceOfTranshipment() {
		return placeOfTranshipment;
	}    
	public void setPlaceOfTranshipment(Place value) {
		this.placeOfTranshipment = value;
	}
		
	public Place getOnCarriagePort() {
		return onCarriagePort;
	}    
	public void setOnCarriagePort(Place value) {
		this.onCarriagePort = value;
	}
	
	public Place getPreCarriagePort() {
		return preCarriagePort;
	}    
	public void setPreCarriagePort(Place value) {
		this.preCarriagePort = value;
	}
		
	public Place getPlaceOfDestination() {
		return placeOfDestination;
	}    
	public void setPlaceOfDestination(Place value) {
		this.placeOfDestination = value;
	}
	
	public boolean isEmpty() {
		return portOfLoading == null && portOfDischarge == null && placeOfDeparture == null; 
	}
}

