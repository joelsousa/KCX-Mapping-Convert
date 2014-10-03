package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 04.06.2014<br>
 * Description	: Quantity.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Quantity extends KCXMessage {
	
	 private String quantity;   		 
	 
     public Quantity() {
  		super();
  	}
     public Quantity(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum EQuantityIE {
		QuantityQuantity, AmountAmount,	
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((EQuantityIE) tag) {
 			default:
 					return;
 			}
 		} else {
 			switch ((EQuantityIE) tag) { 			 			
 				case QuantityQuantity:
 				case AmountAmount:
 					setQuantity(value);
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
 			return EQuantityIE.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}
	
 	public String getQuantity() {
		return quantity;
	} 	
	public void setQuantity(String value) {
		this.quantity = value;
	}
	
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.quantity);  
	}
		
}
