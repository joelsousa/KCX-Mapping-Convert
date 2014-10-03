package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: FWB-TransportMovement.
* 
* @author iwaniuk
* @version 1.0.00
*/


public class TransportMovementFWB extends KCXMessage {

	  private String stageCode;
	  private String modeCode;
	  private String mode;
	  private String id;
	  private String sequenceNumeric;
	  private TransportMeans usedLogisticsTransportMeans;
	  private ArrivalEvent arrivalEvent;	  
	  private DepartureEvent departureEvent;
	  private ArrivalEvent firstArrivalEvent; 			//only for CSN
	  private DepartureEvent departurePointEvent;		//only for CSN
	  
	  private enum EFwbTransportMovement {    	  			     	
		  StageCode,
		  ModeCode,
		  Mode,
	      ID,
	      SequenceNumeric,	     
	      UsedLogisticsTransportMeans,
	      ArrivalEvent,	 	      
	      DepartureEvent,
	      FirstArrivalEvent,
	      DeparturePointEvent,
	  }        

	    public TransportMovementFWB() {
		      	super();	       
	    }
	    
	    public TransportMovementFWB(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EFwbTransportMovement) tag) {
	    		
	    			case UsedLogisticsTransportMeans:
	    				usedLogisticsTransportMeans = new TransportMeans(getScanner());
	    				usedLogisticsTransportMeans.parse(tag.name());
	    				break;
	    				
	    			case DepartureEvent:
	    				departureEvent = new DepartureEvent(getScanner());
	    				departureEvent.parse(tag.name());
	    				break;
	    					    			
	    			case ArrivalEvent:
	    				arrivalEvent = new ArrivalEvent(getScanner());
	    				arrivalEvent.parse(tag.name());
	    				break;
	    				
	    			case DeparturePointEvent:
	    				departurePointEvent = new DepartureEvent(getScanner());
	    				departurePointEvent.parse(tag.name());
	    				break;
	    					    			
	    			case FirstArrivalEvent:
	    				firstArrivalEvent = new ArrivalEvent(getScanner());
	    				firstArrivalEvent.parse(tag.name());
	    				break;
	    			default:
	    					return;
	    			}
	    		} else {
	    			switch ((EFwbTransportMovement) tag) {
	    			case StageCode:
	    				setStageCode(value);
	    				break;
	    				
	    			case ModeCode:
	    				setModeCode(value);
	    				break;
	    				
	    			case Mode:
	    				setMode(value);
	    				break;
	    				
	    			case ID:
	    				setId(value);
	    				break;
	    				
	    			case SequenceNumeric:
	    				setSequenceNumeric(value);
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
	    		return EFwbTransportMovement.valueOf(token);
	    	} catch (IllegalArgumentException e) {
	    		return null;
	    	}
	    }

		public String getStageCode() {
			return stageCode;
		}
		public void setStageCode(String stageCode) {
			this.stageCode = stageCode;
		}

		public String getModeCode() {
			return modeCode;
		}
		public void setModeCode(String modeCode) {
			this.modeCode = modeCode;
		}

		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getId() {
			return id;		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSequenceNumeric() {
			return sequenceNumeric;
		}
		public void setSequenceNumeric(String sequenceNumeric) {
			this.sequenceNumeric = sequenceNumeric;
		}

		public TransportMeans getUsedLogisticsTransportMeans() {
			return usedLogisticsTransportMeans;
		}
		public void setUsedLogisticsTransportMeans(
				TransportMeans usedLogisticsTransportMeans) {
			this.usedLogisticsTransportMeans = usedLogisticsTransportMeans;
		}

		public DepartureEvent getDepartureEvent() {
			return departureEvent;
		}
		public void setDepartureEvent(DepartureEvent event) {
			this.departureEvent = event;
		}

		public ArrivalEvent getArrivalEvent() {
			return arrivalEvent;
		}
		public void setArrivalEvent(ArrivalEvent event) {
			this.arrivalEvent = event;
		}

		public ArrivalEvent getFirstArrivalEvent() {
			return firstArrivalEvent;
		}

		public void setFirstArrivalEvent(ArrivalEvent firstArrivalEvent) {
			this.firstArrivalEvent = firstArrivalEvent;
		}

		public DepartureEvent getDeparturePointEvent() {
			return departurePointEvent;
		}

		public void setDeparturePointEvent(DepartureEvent departurePointEvent) {
			this.departurePointEvent = departurePointEvent;
		}

		public boolean isEmpty() {
			return 	Utils.isStringEmpty(stageCode) && Utils.isStringEmpty(modeCode) &&
					Utils.isStringEmpty(mode) && Utils.isStringEmpty(id) && Utils.isStringEmpty(sequenceNumeric) &&
					(arrivalEvent == null || arrivalEvent.isEmpty()) &&
					(departureEvent == null || departureEvent.isEmpty()) &&
					(usedLogisticsTransportMeans == null || usedLogisticsTransportMeans.isEmpty() &&
					 departurePointEvent == null && firstArrivalEvent == null); 
		}		
}