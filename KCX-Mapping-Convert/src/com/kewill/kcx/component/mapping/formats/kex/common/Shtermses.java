package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 15.11.2012<br>
 * Description	: V03.
 * 
 * @author krzoska
 * @version 0.3.00
 */
public class Shtermses extends KCXMessage {
	
	private String deliverytermincoterm;  //EI121212
	private String placeTerm;
	
	 public Shtermses() {
		 super();  
	 }

	 public Shtermses(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EShtermses{	
			// KEX							KIDS	
		 deliverytermincoterm,      //GoodsDeclaration/IncoTerms/IncoTerm
		 placeTerm;					//GoodsDeclaration/IncoTerms/Place		 	
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShtermses) tag) {
				default: return;			
			}
		} else {			
			switch ((EShtermses) tag) {			
			
				case placeTerm:
					setPlaceTerm(value);
					break;	
				case deliverytermincoterm:
					setDeliverytermincoterm(value);
					break;
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
				return EShtermses.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getPlaceTerm() {
		return placeTerm;
	}
	public void setPlaceTerm(String placeTerm) {
		this.placeTerm = Utils.checkNull(placeTerm);
	}
	
	public String getDeliverytermincoterm() {
		return deliverytermincoterm;
	}
	public void setDeliverytermincoterm(String value) {
		this.deliverytermincoterm = Utils.checkNull(value);
	}
}
