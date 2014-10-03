package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Segment<br>  
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Segment extends KCXMessage {
	 
	private Place lading;
	private Place unlading;			
	
	 private enum ESegment {
	 // Unisys-TagNames, 			KIDS-TagNames
		 LADING,				   		      
		 UNLADING;	
	 }

	 public Segment() {
	      	super();	      	
	 }    
   
	 public Segment(XmlMsgScanner scanner) {
		super(scanner);		
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ESegment) tag) {	
			case LADING:
				lading = new Place(getScanner());  	
				lading.parse(tag.name());				
				break;  	
			case UNLADING:
				unlading = new Place(getScanner());  	
				unlading.parse(tag.name());
				break; 
				default:
					return;
			}
		} else {

			switch ((ESegment) tag) {								
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {		
		try {
			return ESegment.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Place getLading() {
		return lading;	
	}
	public void setLading(Place argument) {
		this.lading = argument;
	}

	public Place getUnlading() {
		return unlading;	
	}
	public void setUnlading(Place argument) {
		this.unlading = argument;
	}		
}
