package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: Transport 
 * Created     	: 17.10.2013
 * Description 	: Contains the Transport Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author iwaniuk 
 * @version 1.0.00
 */
public class Transport extends KCXMessage {
	
	private String description;
	private String identity;
	private String mode;						//Uids:TransportType.TransportModeCode
	private String nationality;	
	private String type;						
	
	private enum ETransport {
		//KIDS:					UIDS: 		
		TransportationNumber,	Identity,
		TransportMode,			Mode, TransportModeCode,
		TransportationType, 	Type,
		TransportationCountry,	Nationality,								               		
		Description,			//same 		
	}

	public Transport() {
  		super();
  	}
			 
	public Transport(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ETransport) tag) {			
			default:
				return;
			}
	    } else {
	    	switch ((ETransport) tag) {			
			case TransportationNumber:
			case Identity:
				 setTransportationNumber(value);
				 break;
			case TransportMode:
			case Mode:			
			case TransportModeCode:	
				 setTransportMode(value);
				 break;	
			case Type:
			case TransportationType:  
				setTransportationType(value);
				break;
			case TransportationCountry:
			case Nationality:
				setTransportationCountry(value);
				 break;	
			case Description:			
				 setDescription(value);
				 break;							
			default:
				return;
	    	}
	    }		
	}

	public void stoppElement(Enum tag) {	
	}

	public Enum translate(String token) {
		try {
  			return ETransport.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
		

	public String getTransportationNumber() {
		return this.identity;
	}
	public void setTransportationNumber(String argument) {
		this.identity = argument;
	}	
 	
	public String getTransportationType() {
		return this.type;
	}
	public void setTransportationType(String argument) {
		this.type = argument;
	}
	
	public String getTransportMode() {
		return this.mode;
	}
	public void setTransportMode(String argument) {
		this.mode = argument;
	}
	
	public String getTransportationCountry() {
		return this.nationality;
	}
	public void setTransportationCountry(String argument) {
		this.nationality = argument;
	}
	
	public String getDescription() {	
		return this.description;
	}
	public void setDescription(String argument) {
		this.description = argument;
	}
		
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.description) && 
				Utils.isStringEmpty(this.identity) && 
				Utils.isStringEmpty(this.mode) && 
				Utils.isStringEmpty(this.type) && 									
				Utils.isStringEmpty(this.nationality)) {
			return true;
		} else {
			return false;
		}
	}
		
}
