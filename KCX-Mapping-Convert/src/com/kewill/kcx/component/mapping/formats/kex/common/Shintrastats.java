package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 12.12.2012<br>
 * Description	: V03.
 * 
 * @author iwaniuk
 * @version 0.3.00
 */
public class Shintrastats extends KCXMessage {
	
	private String natureoftransaction;
	
	 public Shintrastats() {
		 super();  
	 }

	 public Shintrastats(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EShintrastats {	
			// KEX							KIDS			
		 natureoftransaction;					//Business/BusinessTypeCode                                                      		 	
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShintrastats) tag) {
				default: return;			
			}
		} else {			
			switch ((EShintrastats) tag) {			
			
				case natureoftransaction:
					setNatureoftransaction(value);
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
				return EShintrastats.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getNatureoftransaction() {
		return natureoftransaction;
	}

	public void setNatureoftransaction(String value) {
		this.natureoftransaction = Utils.checkNull(value);
	}
	
}
