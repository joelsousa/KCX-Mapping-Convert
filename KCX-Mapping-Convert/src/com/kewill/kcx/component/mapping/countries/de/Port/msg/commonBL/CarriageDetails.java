package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CarriageDetails extends KCXMessage {
		
	private MeansOfTransport meansOfTransport;
	private String departureDate;
	private String arrivalDate;
	private PortsAndPlaces portsAndPlaces;
	private String shipReferenceNumber;
	
	private enum ECarriage {	
		MeansOfTransport,
		EstimatedDepartureDate,
		EstimatedArrivalDate,
		PortsAndPlaces,
		ShipReferenceNumber;	//VoyageNumberPortSpecific		       		
   }	

	public CarriageDetails() {
		super();  
	}

	public CarriageDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  			
  			case MeansOfTransport:
  				meansOfTransport = new MeansOfTransport(getScanner());  	
  				meansOfTransport.parse(tag.name());				
  				break;
  			case PortsAndPlaces:
  				portsAndPlaces = new PortsAndPlaces(getScanner());  	
  				portsAndPlaces.parse(tag.name());				
  				break;
			default:
  				break;
  			}
  		} else {

  			switch((ECarriage) tag) {   			
  				case EstimatedDepartureDate:
  					setEstimatedDepartureDate(value);
  					break;    
  				case EstimatedArrivalDate:
  					setEstimatedArrivalDate(value);
  					break;   
  				case ShipReferenceNumber:
  					setShipReferenceNumber(value);
  					break;   
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
	
	public String getEstimatedDepartureDate() {
		return departureDate;
	}    
	public void setEstimatedDepartureDate(String value) {
		this.departureDate = value;
	}
		
	public String getEstimatedArrivalDate() {
		return arrivalDate;
	}    
	public void setEstimatedArrivalDate(String value) {
		this.arrivalDate = value;
	}
	
	public String getShipReferenceNumber() {
		return shipReferenceNumber;
	}    
	public void setShipReferenceNumber(String value) {
		this.shipReferenceNumber = value;
	}
	
	public MeansOfTransport getMeansOfTransport() {
		return meansOfTransport;
	}    
	public void setMeansOfTransport(MeansOfTransport value) {
		this.meansOfTransport = value;
	}
	
	public PortsAndPlaces getPortsAndPlaces() {
		return portsAndPlaces;
	}    
	public void setPortsAndPlaces(PortsAndPlaces value) {
		this.portsAndPlaces = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(shipReferenceNumber) && Utils.isStringEmpty(departureDate) && 
				Utils.isStringEmpty(arrivalDate) &&
				meansOfTransport == null &&  portsAndPlaces == null); 
	}
	
}

