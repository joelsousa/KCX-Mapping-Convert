package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 18.07.2013<br>
* Description	: LocationEvent.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class LocationEvent extends KCXMessage {
	
    private Location location;
       
    private enum ELocationEvent {
    	OccurrenceDestinationLocation,
    	OccurrencePositioningLocation,
    	
    }

    public LocationEvent() {
	      	super();	       
    }
    
    public LocationEvent(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ELocationEvent) tag) {
    			case OccurrenceDestinationLocation:
    			case OccurrencePositioningLocation:
    				location = new Location(getScanner());
    				location.parse(tag.name());			
    				break;
    			default:
    					return;
    			}
    		} else {

    			switch ((ELocationEvent) tag) {    			
    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return ELocationEvent.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

	public Location getOccurrenceDestinationLocation() {
		return location;
	}
	public void setOccurrenceDestinationLocation(Location argument) {
		this.location = argument;
	}
	
	public Location getOccurrencePositioningLocation() {
		return location;
	}
	public void setOccurrencePositioningLocation(Location argument) {
		this.location = argument;
	}
	
	public boolean isEmpty() {
		return location == null;
	}
	
}
