package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 04.06.2014<br>
 * Description	: diverse Identifier.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Identifier extends KCXMessage {
	
	 private String identifier; 
	 private String traderIdentifier;
	 
     public Identifier() {
  		super();
  	}
     public Identifier(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum EIdentifier {		
		IdentificationIdentifier, CodeIdentifier, LocationIdentifier,
		TraderReferenceIdentifier, IEPayerIdentifier,		
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((EIdentifier) tag) {
 			default:
 					return;
 			}
 		} else {
 			switch ((EIdentifier) tag) { 
 				case CodeIdentifier:
 				case IdentificationIdentifier:
 				case LocationIdentifier:
 					setIdentifier(value);
 					break; 	
 				case TraderReferenceIdentifier:
 				case IEPayerIdentifier:
 					setTraderIdentifier(value);
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
 			return EIdentifier.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}
	
 	public String getIdentifier() {
		return identifier;
	} 	
	public void setIdentifier(String value) {
		this.identifier = value;
	}
		
	public String getTraderIdentifier() {
		return traderIdentifier;
	} 	
	public void setTraderIdentifier(String value) {
		this.traderIdentifier = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(identifier) && Utils.isStringEmpty(traderIdentifier);  
	}	
}
