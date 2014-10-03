package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module       	: EMCS<br>
* Created   	: 30.04.2010<br>
* Description 	: Contains the Member for save the UIDS-tags from ChangeOfDestination message.      
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ChangedDestination extends KCXMessage {

	private String destinationTypeCode;
	private String deliveryPlaceCustomsOffice;
	private EmcsTrader newConsignee;
	private EmcsTrader deliveryPlace;
	
	private enum EChangedDestination {
		//KIDS:					UIDS:
								DestinationTypeCode,         
								DeliveryPlaceCustomsOffice,    
								NewConsigneeTrader,                   
								DeliveryPlaceTrader;			
	}
	
	public ChangedDestination() {
  		super();
  	}
			 
	public ChangedDestination(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EChangedDestination) tag) {
			case NewConsigneeTrader:				
				newConsignee = new EmcsTrader(getScanner());
				newConsignee.parse(tag.name());
				break;
			case DeliveryPlaceTrader:				
				deliveryPlace = new EmcsTrader(getScanner());
				deliveryPlace.parse(tag.name());
				break;				
			default:
				return;
			}
	    } else {
	    	switch ((EChangedDestination) tag) {
			case DestinationTypeCode:			
				 setDestinationTypeCode(value);
				 break;	
			case DeliveryPlaceCustomsOffice:
				 setDeliveryPlaceCustomsOffice(value);
				 break;
			default:
				break;
			}
	    }		
	}	

	public void stoppElement(Enum tag) {
	}

	@Override
	public Enum translate(String token) {
		try {
  			return EChangedDestination.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	public String getDestinationTypeCode() {	
		return this.destinationTypeCode;
	}
	public void setDestinationTypeCode(String argument) {
		this.destinationTypeCode = argument;
	}	

	public String getDeliveryPlaceCustomsOffice() {
		return this.deliveryPlaceCustomsOffice;
	}
	public void setDeliveryPlaceCustomsOffice(String argument) {
		this.deliveryPlaceCustomsOffice = argument;
	}	
 	
	public EmcsTrader getNewConsignee() {
		return this.newConsignee;
	}
	public void setNewConsignee(EmcsTrader argument) {
		this.newConsignee = argument;
	}

	public EmcsTrader getDeliveryPlace() {
		return this.deliveryPlace;
	}
	public void setDeliveryPlace(EmcsTrader argument) {
		this.deliveryPlace = argument;
	}	
	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.destinationTypeCode) &&
				Utils.isStringEmpty(this.deliveryPlaceCustomsOffice) &&   		
    			(newConsignee == null) &&
    			(deliveryPlace == null));		
	}	
}
