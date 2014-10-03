package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import java.util.ArrayList;

/**
* Module		: CMP<br>
* Created		: 17.07.2013<br>
* Description	: ArrivalEvent.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ArrivalEvent extends KCXMessage {
	
    private String arrivalOccurrenceDateTime;
    private String arrivalDateTimeTypeCode;
    private String scheduledOccurrenceDateTime;    
    private String departureOccurrenceDateTime;   
    private String departureDateTimeTypeCode;
    private Location occurrenceArrivalLocation;
    private Location occurrenceFirstArrivalLocation;   //only for CSN
    //private TransportCargo associatedTransportCargo;  //eigentlich auch Liste
    private ArrayList<TransportCargo> associatedTransportCargoList;  //EI20140210
   
    private enum EArrivalEvent {
    	ArrivalOccurrenceDateTime,
    	ArrivalDateTimeTypeCode,
    	ScheduledOccurrenceDateTime,    	
    	DepartureOccurrenceDateTime,
    	DepartureDateTimeTypeCode,
    	OccurrenceArrivalLocation,    	
    	OccurrenceFirstArrivalLocation,
    	AssociatedTransportCargo,    	
    }        

    public ArrivalEvent() {
	      	super();	       
    }
    
    public ArrivalEvent(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EArrivalEvent) tag) {
    			
    			case OccurrenceArrivalLocation:   
    				occurrenceArrivalLocation = new Location(getScanner());
    				occurrenceArrivalLocation.parse(tag.name());								
					break;    
					
    			case OccurrenceFirstArrivalLocation:   
    				occurrenceFirstArrivalLocation = new Location(getScanner());
    				occurrenceFirstArrivalLocation.parse(tag.name());								
					break;  
					
				case AssociatedTransportCargo:
					TransportCargo associatedTransportCargo = new TransportCargo(getScanner());
					associatedTransportCargo.parse(tag.name());	
					this.addAssociatedTransportCargoList(associatedTransportCargo);
					break;
    			default:
    					return;
    			}
    		} else {

    			switch ((EArrivalEvent) tag) {
    				case ArrivalOccurrenceDateTime:
    					setArrivalOccurrenceDateTime(value);    					
    					break;
    					
    				case ScheduledOccurrenceDateTime:
    					setScheduledOccurrenceDateTime(value);    					
    					break;
    					
    				case ArrivalDateTimeTypeCode:
    					setArrivalDateTimeTypeCode(value);    					
    					break; 
    					
    				case DepartureOccurrenceDateTime:
    					setDepartureOccurrenceDateTime(value);    					
    					break;
    					
    				case DepartureDateTimeTypeCode:
    					setDepartureDateTimeTypeCode(value);    					
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
    			return EArrivalEvent.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}
	
    	public String getArrivalOccurrenceDateTime() {
			return arrivalOccurrenceDateTime;
		}
		public void setArrivalOccurrenceDateTime(String arrivalOccurrenceDateTime) {
			this.arrivalOccurrenceDateTime = arrivalOccurrenceDateTime;
		}

		public String getArrivalDateTimeTypeCode() {
			return arrivalDateTimeTypeCode;
		}
		public void setArrivalDateTimeTypeCode(String arrivalDateTimeTypeCode) {
			this.arrivalDateTimeTypeCode = arrivalDateTimeTypeCode;
		}

		public String getDepartureOccurrenceDateTime() {
			return departureOccurrenceDateTime;
		}
		public void setDepartureOccurrenceDateTime(String departureOccurrenceDateTime) {
			this.departureOccurrenceDateTime = departureOccurrenceDateTime;
		}

		public String getDepartureDateTimeTypeCode() {
			return departureDateTimeTypeCode;
		}
		public void setDepartureDateTimeTypeCode(String departureDateTimeTypeCode) {
			this.departureDateTimeTypeCode = departureDateTimeTypeCode;
		}

		public Location getOccurrenceArrivalLocation() {
			return occurrenceArrivalLocation;
		}
		public void setOccurrenceArrivalLocation(Location occurrenceArrivalLocation) {
			this.occurrenceArrivalLocation = occurrenceArrivalLocation;
		}

		public ArrayList<TransportCargo> getAssociatedTransportCargoList() {
			return associatedTransportCargoList;
		}
		public void setAssociatedTransportCargoList(ArrayList<TransportCargo> associatedTransportCargo) {
			this.associatedTransportCargoList = associatedTransportCargo;
		}
		public void addAssociatedTransportCargoList(TransportCargo associated) {
			if (associatedTransportCargoList == null) {
				associatedTransportCargoList = new ArrayList<TransportCargo>();
			}
			associatedTransportCargoList.add(associated);
		}
	
		public String getScheduledOccurrenceDateTime() {
			return scheduledOccurrenceDateTime;
		}
		public void setScheduledOccurrenceDateTime(String scheduledOccurrenceDateTime) {
			this.scheduledOccurrenceDateTime = scheduledOccurrenceDateTime;
		}
		
	public Location getOccurrenceFirstArrivalLocation() {
			return occurrenceFirstArrivalLocation;
		}

		public void setOccurrenceFirstArrivalLocation(
				Location occurrenceFirstArrivalLocation) {
			this.occurrenceFirstArrivalLocation = occurrenceFirstArrivalLocation;
		}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(arrivalOccurrenceDateTime) && Utils.isStringEmpty(scheduledOccurrenceDateTime) && 
				Utils.isStringEmpty(departureOccurrenceDateTime) && 
				occurrenceArrivalLocation == null && associatedTransportCargoList == null &&
				occurrenceFirstArrivalLocation == null); 
	}
	
}
