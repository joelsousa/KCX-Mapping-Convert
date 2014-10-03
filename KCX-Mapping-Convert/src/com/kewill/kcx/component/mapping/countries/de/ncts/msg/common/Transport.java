package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: Transport 
 * Created     	: 31.08.2008
 * Description 	: Contains the Transport Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
public class Transport extends KCXMessage {
	
	private String description;
	private String identity;
	private String mode;						//Uids:TransportType.TransportModeCode
	private String nationality;
	private String shippingLine;				//??? is language (attribute) necessary? 
	private String type;						//??? is language (attribute) necessary? 
	private String complementaryInformation;	//Uids:TransportType
	
	private enum ETransport {
		//KIDS:					UIDS: 
								Description, 
		TransportationNumber,	Identity,
		TransportMode,			Mode,
								TransportModeCode,
		TransportationCountry,	Nationality,
								ShippingLine,                   
		TransportationType, 	Type,	
								ComplementaryInformation;
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
			case Description:			
				 setDescription(value);
				 break;	
			case TransportationNumber:
			case Identity:
				 setIdentity(value);
				 break;
			case TransportMode:
			case Mode:			
			case TransportModeCode:	
				 setMode(value);
				 break;	
			case TransportationCountry:
			case Nationality:
				 setNationality(value);
				 break;	
			case Type:
			case TransportationType:  //EI20130624
				setType(value);
				break;
			case ShippingLine:
				 setShippingLine(value);
				 break;
			case ComplementaryInformation:
				setComplementaryInformation(value);
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
	
	public String getDescription() {	
		return this.description;
	}
	public void setDescription(String argument) {
		this.description = argument;
	}	

	public String getIdentity() {
		return this.identity;
	}
	public void setIdentity(String argument) {
		this.identity = argument;
	}	
 	
	public String getMode() {
		return this.mode;
	}
	public void setMode(String argument) {
		this.mode = argument;
	}

	public String getsetNationality() {
		return this.nationality;
	}
	public String getNationality() {
		return this.nationality;
	}
	public void setNationality(String argument) {
		this.nationality = argument;
	}
	
	public String getShippingLine() {
		return this.shippingLine;
	}
	public void setShippingLine(String argument) {
		this.shippingLine = argument;
	}
	
	public String getType() {
		return this.type;
	}
	public void setType(String argument) {
		this.type = argument;
	}
	public String getComplementaryInformation() {
		return this.complementaryInformation;
	}
	public void setComplementaryInformation(String argument) {
		this.complementaryInformation = argument;
	}	
	
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.description) && 
				Utils.isStringEmpty(this.identity) && 
				Utils.isStringEmpty(this.mode) && 
				Utils.isStringEmpty(this.nationality) && 
				Utils.isStringEmpty(this.shippingLine) && 
				Utils.isStringEmpty(this.type) && 
				Utils.isStringEmpty(this.complementaryInformation)) {
			return true;
		} else {
			return false;
		}
	}
		
}
