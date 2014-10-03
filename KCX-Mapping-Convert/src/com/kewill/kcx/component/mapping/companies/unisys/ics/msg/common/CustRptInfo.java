package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;


import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustRptInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */


public class CustRptInfo extends KCXMessage {
	  	 
	 private PartInfo trader;
		
	 private enum ECustRptInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		TRADER;				  			
	 }

	 public CustRptInfo() {
	      	super();	     
	 }    
   
	 public CustRptInfo(XmlMsgScanner scanner) {
		super(scanner);	
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustRptInfo) tag) {
			case TRADER:
				trader = new PartInfo(getScanner());
				trader.parse(tag.name());				
				break;				
			default:
					return;
			}
		} else {

			switch ((ECustRptInfo) tag) {				  									
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {		
		try {
			return ECustRptInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public PartInfo getTrader() {
		return this.trader;	
	}
	public void setTrader(PartInfo argument) {
		this.trader = argument;
	}
	
}
