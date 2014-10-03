package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: TransportCarriage.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TransportCarriage extends KCXMessage {

	
	private String 	id;
	private PartyType carrierParty;
	private DepartureEvent onCarriageEvent;
	private LocationEvent arrivalDestinationEvent;

	
	private enum ETransportCarriage {
		 ID,
		 CarrierParty,
		 OnCarriageEvent,
		 ArrivalDestinationEvent;
	}        

	    public TransportCarriage() {
		      	super();	       
	    }
	    
	    public TransportCarriage(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((ETransportCarriage) tag) {
	    			case CarrierParty:   
	    				carrierParty = new PartyType(getScanner());
	    				carrierParty.parse(tag.name());					
    					break;
    					
    				case OnCarriageEvent:
    					onCarriageEvent = new DepartureEvent(getScanner());
    					onCarriageEvent.parse(tag.name());        				
    					break;
    					
    				case ArrivalDestinationEvent:   
    					arrivalDestinationEvent = new LocationEvent(getScanner());
    					arrivalDestinationEvent.parse(tag.name());					
    					break;
    					
	    			default:
	    					return;
	    			}
	    		} else {

	    			switch ((ETransportCarriage) tag) {

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
	    			return ETransportCarriage.valueOf(token);
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

			public PartyType getCarrierParty() {
				return carrierParty;
			}

			public void setCarrierParty(PartyType carrierParty) {
				this.carrierParty = carrierParty;
			}

			public DepartureEvent getOnCarriageEvent() {
				return onCarriageEvent;
			}

			public void setOnCarriageEvent(DepartureEvent onCarriageEvent) {
				this.onCarriageEvent = onCarriageEvent;
			}

			public LocationEvent getArrivalDestinationEvent() {
				return arrivalDestinationEvent;
			}

			public void setArrivalDestinationEvent(LocationEvent arrivalDestinationEvent) {
				this.arrivalDestinationEvent = arrivalDestinationEvent;
			}

		public boolean isEmpty() {
			return Utils.isStringEmpty(id) && carrierParty == null  && 
			onCarriageEvent == null && arrivalDestinationEvent == null; 
		}
}
