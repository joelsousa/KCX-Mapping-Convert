package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: FSU-TransportMovement.
* 
* @author iwaniuk
* @version 1.0.00
*/


public class TransportMovementFSU extends KCXMessage {

	  private String id;
	  private TransportMeans usedLogisticsTransportMeans;
	  private ArrivalEvent scheduledArrivalEvent;
	  private ArrivalEvent arrivalEvent;	  
	  private DepartureEvent departureEvent;
	  private PartyType carrierParty;
	  private Location specifiedLocation;	  
	  private Event specifiedEvent;
	 
	  
	  private enum EFsuTransportMovement {    	  			     	
		  ID,
		  UsedLogisticsTransportMeans,
		  ScheduledArrivalEvent,
		  ArrivalEvent,
		  DepartureEvent,
		  CarrierParty,	     
		  SpecifiedLocation,
		  SpecifiedEvent,
	  }        

	    public TransportMovementFSU() {
		      	super();	       
	    }
	    
	    public TransportMovementFSU(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EFsuTransportMovement) tag) {
	    		
	    			case UsedLogisticsTransportMeans:
	    				usedLogisticsTransportMeans = new TransportMeans(getScanner());
	    				usedLogisticsTransportMeans.parse(tag.name());
	    				break;
	    				
	    			case ScheduledArrivalEvent:
	    				scheduledArrivalEvent = new ArrivalEvent(getScanner());
	    				scheduledArrivalEvent.parse(tag.name());
	    				break;
	    					    			
	    			case ArrivalEvent:
	    				arrivalEvent = new ArrivalEvent(getScanner());
	    				arrivalEvent.parse(tag.name());
	    				break;
	    				
	    			case DepartureEvent:
	    				departureEvent = new DepartureEvent(getScanner());
	    				departureEvent.parse(tag.name());
	    				break;
	    				
	    			case CarrierParty:
	    				carrierParty = new PartyType(getScanner());
	    				carrierParty.parse(tag.name());
	    				break;
	    				
	    			case SpecifiedLocation:
	    				specifiedLocation = new Location(getScanner());
	    				specifiedLocation.parse(tag.name());
	    				break;
	    				
	    			case SpecifiedEvent:
	    				specifiedEvent = new Event(getScanner());
	    				specifiedEvent.parse(tag.name());
	    				break;
	    			default:
	    					return;
	    			}
	    		} else {
	    			switch ((EFsuTransportMovement) tag) {
	    			
	    			case ID:
	    				setId(value);
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
	    		return EFsuTransportMovement.valueOf(token);
	    	} catch (IllegalArgumentException e) {
	    		return null;
	    	}
	    }
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		public TransportMeans getUsedLogisticsTransportMeans() {
			return usedLogisticsTransportMeans;
		}
		public void setUsedLogisticsTransportMeans(
				TransportMeans usedLogisticsTransportMeans) {
			this.usedLogisticsTransportMeans = usedLogisticsTransportMeans;
		}

		public ArrivalEvent getScheduledArrivalEvent() {
			return scheduledArrivalEvent;
		}
		public void setScheduledArrivalEvent(ArrivalEvent scheduledArrivalEvent) {
			this.scheduledArrivalEvent = scheduledArrivalEvent;
		}

		public ArrivalEvent getArrivalEvent() {
			return arrivalEvent;
		}
		public void setArrivalEvent(ArrivalEvent arrivalEvent) {
			this.arrivalEvent = arrivalEvent;
		}

		public DepartureEvent getDepartureEvent() {
			return departureEvent;
		}
		public void setDepartureEvent(DepartureEvent departureEvent) {
			this.departureEvent = departureEvent;
		}

		public PartyType getCarrierParty() {
			return carrierParty;
		}
		public void setCarrierParty(PartyType carrierParty) {
			this.carrierParty = carrierParty;
		}
		
		public Location getSpecifiedLocation() {
			return specifiedLocation;
		}
		public void setSpecifiedLocation(Location specifiedLocation) {
			this.specifiedLocation = specifiedLocation;
		}
		
		public Event getSpecifiedEvent() {
			return specifiedEvent;
		}
		public void setSpecifiedEvent(Event specifiedEvent) {
			this.specifiedEvent = specifiedEvent;
		}

		public boolean isEmpty() {
			return 	Utils.isStringEmpty(id) && usedLogisticsTransportMeans == null &&				
					scheduledArrivalEvent == null && arrivalEvent == null &&
					departureEvent == null  && carrierParty == null &&
					specifiedLocation == null && specifiedEvent == null; 
		}		
}
