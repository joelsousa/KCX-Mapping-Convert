package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: Event.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Event extends KCXMessage {
	
    private String occurrenceDateTime;
    private String dateTimeTypeCode;
  
    private enum EEvent {
    	OccurrenceDateTime,
    	DateTimeTypeCode,    	
    }

    public Event() {
	      	super();	       
    }
    
    public Event(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EEvent) tag) {    			
    			default:
    					return;
    			}
    		} else {

    			switch ((EEvent) tag) {
    				case OccurrenceDateTime:
    					setOccurrenceDateTime(value);
    					break;
    					
    				case DateTimeTypeCode:
    					setDateTimeTypeCode(value);
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
    			return EEvent.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }


	public String getOccurrenceDateTime() {
		return occurrenceDateTime;
	}
	public void setOccurrenceDateTime(String occurrenceDateTime) {
		this.occurrenceDateTime = occurrenceDateTime;
	}

	public String getDateTimeTypeCode() {
		return dateTimeTypeCode;
	}
	public void setDateTimeTypeCode(String dateTimeTypeCode) {
		this.dateTimeTypeCode = dateTimeTypeCode;
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(occurrenceDateTime) &&
				Utils.isStringEmpty(dateTimeTypeCode);							
	}
}
