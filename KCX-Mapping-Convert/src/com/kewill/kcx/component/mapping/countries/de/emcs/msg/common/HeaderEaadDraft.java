package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Date			: 05.05.2010<br>
 * Description  : Contains the Member for save tags from the UIDS message.   
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class HeaderEaadDraft extends KCXMessage {

	private String journeyTime;
	private String destinationTypeCode;
	private String transportArrangement;
	private String sequenceNumber;
	private String dateOfUpdateValidation;
	
		
	private enum EHeaderEaadDraft {
		//KIDS : are simple tags UIDS:
								JourneyTime,								
								DestinationTypeCode,         
								TransportArrangement,
								DateOfUpdateValidation,
								SequenceNumber;                   									
	}
	
	public HeaderEaadDraft() {
  		super();
  	}
	 
	public HeaderEaadDraft(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EHeaderEaadDraft) tag) {
			default:
				return;
			}
	    } else {
	    	switch ((EHeaderEaadDraft) tag) {
	    	case SequenceNumber:
	    		 setSequenceNumber(value);
				 break;
			case DestinationTypeCode:			
				 setDestinationTypeCode(value);
				 break;	
			case JourneyTime:
				 setJourneyTime(value);
				 break;						 
			case TransportArrangement:
				 setTransportArrangement(value);
				 break;	
			case DateOfUpdateValidation:
				 setDateOfUpdateValidation(value);
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
  			return EHeaderEaadDraft.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getDestinationTypeCode() {
		return this.destinationTypeCode;
	}
	public void setDestinationTypeCode(String argument) {
		this.destinationTypeCode = argument;
	}	

	public String getJourneyTime() {
		return this.journeyTime;
	}
	public void setJourneyTime(String argument) {
		this.journeyTime = argument;
	}

	public String getTransportArrangement() {
		return this.transportArrangement;
	}
	public void setTransportArrangement(String argument) {
		this.transportArrangement = argument;
	}	
	
	public String getDateOfUpdateValidation() {
		return this.dateOfUpdateValidation;
	}
	public void setDateOfUpdateValidation(String argument) {
		this.dateOfUpdateValidation = argument;
	}

	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.journeyTime) && 
			Utils.isStringEmpty(this.destinationTypeCode) && 
    		Utils.isStringEmpty(this.transportArrangement) && 
    		Utils.isStringEmpty(this.dateOfUpdateValidation) && 
    		Utils.isStringEmpty(this.sequenceNumber));					
	}
}
