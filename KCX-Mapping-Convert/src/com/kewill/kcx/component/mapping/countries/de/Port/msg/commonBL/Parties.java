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

public class Parties extends KCXMessage {
		
	private PartyDetails shipper;
	private PartyDetails consignee;
	private PartyDetails notify;
	private PartyDetails bookingAgent;
	private PartyDetails originalShipper;
	private PartyDetails secondNotify;
	private PartyDetails orderOfShipper;
	private PartyDetails deconsolidator;
	private PartyDetails carrier;
	private PartyDetails freightForwarder;
	private PartyDetails freightForwarderRequestorBranch;
	
	private enum EParties {	
		Shipper,
		Consignee,
		Notify,
		BookingAgent,
		OriginalShipper,
		SecondNotify,
		OrderOfShipper,
		Deconsolidator,
		Carrier,
		FreightForwarder,
		FreightForwarderRequestorBranch;			       		
   }	

	public Parties() {
		super();  
	}

	public Parties(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EParties) tag) {  
  				case Shipper:
  					shipper = new PartyDetails(getScanner());  	
  					shipper.parse(tag.name());
					break; 
  				case Consignee:
  					consignee = new PartyDetails(getScanner());  	
  					consignee.parse(tag.name());  				
  					break;
  				case Notify:
  					notify = new PartyDetails(getScanner());  	
  					notify.parse(tag.name());  					
  					break;
  				case BookingAgent:
  					bookingAgent = new PartyDetails(getScanner());  	
  					bookingAgent.parse(tag.name());
					break; 
  				case OriginalShipper:
  					originalShipper = new PartyDetails(getScanner());  	
  					originalShipper.parse(tag.name());  				
  					break;
  				case SecondNotify:
  					secondNotify = new PartyDetails(getScanner());  	
  					secondNotify.parse(tag.name());  					
  					break;
  				case OrderOfShipper:
  					orderOfShipper = new PartyDetails(getScanner());  	
  					orderOfShipper.parse(tag.name());
					break; 
  				case Deconsolidator:
  					deconsolidator = new PartyDetails(getScanner());  	
  					deconsolidator.parse(tag.name());  				
  					break;
  				case Carrier:
  					carrier = new PartyDetails(getScanner());  	
  					carrier.parse(tag.name());  					
  					break;
  				case FreightForwarder:
  					freightForwarder = new PartyDetails(getScanner());  	
  					freightForwarder.parse(tag.name());  					
  					break;
  				case FreightForwarderRequestorBranch:
  					freightForwarderRequestorBranch = new PartyDetails(getScanner());  	
  					freightForwarderRequestorBranch.parse(tag.name()); 
  					break;
				default:
  					break;
  			}
  		} else {

  			switch((EParties) tag) {  
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EParties.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public PartyDetails getShipper() {
		return shipper;
	}    
	public void setShipper(PartyDetails value) {
		this.shipper = value;
	}
		
	public PartyDetails getConsignee() {
		return consignee;
	}    
	public void setConsignee(PartyDetails value) {
		this.consignee = value;
	}
	
	public PartyDetails getNotify() {
		return notify;
	}    
	public void setNotify(PartyDetails value) {
		this.notify = value;
	}
	
	public PartyDetails getBookingAgent() {
		return bookingAgent;
	}    
	public void setBookingAgent(PartyDetails value) {
		this.bookingAgent = value;
	}
	
	public PartyDetails getOriginalShipper() {
		return originalShipper;
	}    
	public void setOriginalShipper(PartyDetails value) {
		this.originalShipper = value;
	}
	
	public PartyDetails getSecondNotify() {
		return secondNotify;
	}    
	public void setSecondNotify(PartyDetails value) {
		this.secondNotify = value;
	}
	
	public PartyDetails getOrderOfShipper() {
		return orderOfShipper;
	}    
	public void setOrderOfShipper(PartyDetails value) {
		this.orderOfShipper = value;
	}
	
	public PartyDetails getDeconsolidator() {
		return deconsolidator;
	}    
	public void setDeconsolidator(PartyDetails value) {
		this.deconsolidator = value;
	}
	
	public PartyDetails getCarrier() {
		return carrier;
	}    
	public void setCarrier(PartyDetails value) {
		this.carrier = value;
	}
	
	public PartyDetails getFreightForwarder() {
		return freightForwarder;
	}    
	public void setFreightForwarder(PartyDetails value) {
		this.freightForwarder = value;
	}
	
	public PartyDetails getFreightForwarderRequestorBranch() {
		return freightForwarderRequestorBranch;
	}    
	public void setFreightForwarderRequestorBranch(PartyDetails value) {
		this.freightForwarderRequestorBranch = value;
	}
}

