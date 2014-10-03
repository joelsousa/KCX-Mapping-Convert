package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Modul		: CustTotals<br>  
* Erstellt		: 02.12.2010<br>
* Beschreibung	: Conversion of Unisys to KIDS.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CustTotals extends KCXMessage	  {
	 
	private String pieces = "";
	private String weight = "";
	private String part = "";
		
	private enum ECustTotals {
	 // Unisys-TagNames, 			KIDS-TagNames
		 PIECES,				   		      
		 WEIGHT,
		 PART;
	}

	public CustTotals() {
	      	super();	      
	}    

	public CustTotals(XmlMsgScanner scanner) {
		super(scanner);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustTotals) tag) {			
				default:
					return;
			}
		} else {

			switch ((ECustTotals) tag) {
				case PIECES:
					setPieces(value);
					break;  	
				case WEIGHT:
					setWeight(value);
					break;  
				case PART:
					setPart(value);
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
			return ECustTotals.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

					
	public String getPieces() {
		return pieces;	
	}
	public void setPieces(String argument) {
		this.pieces = Utils.checkNull(argument);
	}

	public String getWeight() {
		return weight;	
	}
	public void setWeight(String argument) {
		this.weight = Utils.checkNull(argument);
	}	
	
	public String getPart() {
		return part;	
	}
	public void setPart(String argument) {
		this.part = Utils.checkNull(argument);
	}
}
