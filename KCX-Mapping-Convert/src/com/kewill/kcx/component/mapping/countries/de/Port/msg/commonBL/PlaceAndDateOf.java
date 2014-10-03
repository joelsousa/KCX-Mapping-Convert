package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PlaceAndDateOf extends KCXMessage {
		
	private PlaceAndDate payment;
	private PlaceAndDate ladingIssue;
	
	private enum EPlaceAndDateOf {	
		Payment,
		BillOfLadingIssue;			       		
   }	

	public PlaceAndDateOf() {
		super();  
	}

	public PlaceAndDateOf(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPlaceAndDateOf) tag) {  			
  			case Payment:
  				payment = new PlaceAndDate(getScanner());  	
  				payment.parse(tag.name());  					
				break; 
  			case BillOfLadingIssue:
  				ladingIssue = new PlaceAndDate(getScanner());  	
  				ladingIssue.parse(tag.name());  					
				break; 
			default:
  					break;
  			}
  		} else {

  			switch((EPlaceAndDateOf) tag) {   			  			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EPlaceAndDateOf.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public PlaceAndDate getPayment() {
		return payment;
	}    
	public void setPayment(PlaceAndDate value) {
		this.payment = value;
	}
		
	public PlaceAndDate getBillOfLadingIssue() {
		return ladingIssue;
	}    
	public void setBillOfLadingIssue(PlaceAndDate value) {
		this.ladingIssue = value;
	}	
}

