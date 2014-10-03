package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 18.07.2013<br>
* Description	: DepartureEvent.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class DepartureEvent extends KCXMessage {
	
    private String departureOccurrenceDateTime;
    private String departureDateTimeTypeCode;
    private String scheduledOccurrenceDateTime;
    private Location occurrenceDepartureLocation;
    private Location occurrenceDeparturePointLocation; 		//only for CSN
       
    private enum EDepartureEvent {
    	DepartureOccurrenceDateTime,
    	DepartureDateTimeTypeCode,
    	ScheduledOccurrenceDateTime,
    	OccurrenceDepartureLocation,
    	OccurrenceDeparturePointLocation,
    }

    public DepartureEvent() {
	      	super();	       
    }
    
    public DepartureEvent(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EDepartureEvent) tag) {
    			case OccurrenceDepartureLocation:
    				occurrenceDepartureLocation = new Location(getScanner());
    				occurrenceDepartureLocation.parse(tag.name());
    				break;	
    				
    			case OccurrenceDeparturePointLocation:
    				occurrenceDeparturePointLocation = new Location(getScanner());
    				occurrenceDeparturePointLocation.parse(tag.name());
    				break;	
			
    			default:
    					return;
    			}
    		} else {

    			switch ((EDepartureEvent) tag) {
    				case DepartureOccurrenceDateTime:
    					setDepartureOccurrenceDateTime(value);
    					break;
    					
    				case DepartureDateTimeTypeCode:
    					setDepartureDateTimeTypeCode(value);
    					break;
    				
    				case ScheduledOccurrenceDateTime:
    					setScheduledOccurrenceDateTime(value);
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
    			return EDepartureEvent.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

	public String getDepartureOccurrenceDateTime() {
		return departureOccurrenceDateTime;
	}
	public void setDepartureOccurrenceDateTime(String value) {
		this.departureOccurrenceDateTime = value;
	}
	
	public String getDepartureDateTimeTypeCode() {
		return departureDateTimeTypeCode;
	}
	public void setDepartureDateTimeTypeCode(String value) {
		this.departureDateTimeTypeCode = value;
	}

	public Location getOccurrenceDepartureLocation() {
		return occurrenceDepartureLocation;
	}
	public void setOccurrenceDepartureLocation(Location value) {
		this.occurrenceDepartureLocation = value;
	}

	public String getScheduledOccurrenceDateTime() {
		return scheduledOccurrenceDateTime;
	}
	public void setScheduledOccurrenceDateTime(String value) {
		this.scheduledOccurrenceDateTime = value;
	}

	public Location getOccurrenceDeparturePointLocation() {
		return occurrenceDeparturePointLocation;
	}

	public void setOccurrenceDeparturePointLocation(
			Location occurrenceDeparturePointLocation) {
		this.occurrenceDeparturePointLocation = occurrenceDeparturePointLocation;
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(departureOccurrenceDateTime) &&
				Utils.isStringEmpty(scheduledOccurrenceDateTime) &&			
				occurrenceDepartureLocation == null && occurrenceDeparturePointLocation == null; 
	}
	
}
