package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: PartReference<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PartReference extends KCXMessage {
			
	 private String eori;

	 private enum EPartReference {
		 // Unisys-TagNames, 			KIDS-TagNames
			 EORI;
	 }
	 
	 public PartReference() {
	      	super();	
	 }    
   
	 public PartReference(XmlMsgScanner scanner) {
		super(scanner);		
	 }	
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPartReference) tag) {						
							
			default:
				return;
			}
		} else {
			switch ((EPartReference) tag) {	
				case EORI:
					setEori(value);
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
			return EPartReference.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getEori() {
		return eori;	
	}
	public void setEori(String list) {
		this.eori = list;
	}

	
	
}
