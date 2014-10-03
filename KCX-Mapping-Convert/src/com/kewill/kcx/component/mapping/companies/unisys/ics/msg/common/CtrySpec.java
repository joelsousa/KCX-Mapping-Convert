package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CtrySpec.<br>
 * Erstellt		: 03.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class CtrySpec extends KCXMessage {

	private Customs customs;
	
	private enum ECtrySpec {
	 // Unisys-TagNames,
		CUSTOMS;		
	 }

	 public CtrySpec() {
	      	super();
	 }    
   
	 public CtrySpec(XmlMsgScanner scanner) {
		super(scanner);
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECtrySpec) tag) {
			case CUSTOMS:
				customs = new Customs(getScanner());  	
				customs.parse(tag.name());				
				break;	
			default:
					return;
			}
		} else {

			switch ((ECtrySpec) tag) {
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return ECtrySpec.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Customs getCustoms() {
		return customs;
	}
	public void setCustoms(Customs argument) {
		this.customs = argument;
	}
}
