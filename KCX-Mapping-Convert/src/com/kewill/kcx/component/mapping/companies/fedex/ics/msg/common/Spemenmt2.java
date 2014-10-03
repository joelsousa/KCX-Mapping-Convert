package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;


/*
* Function    : Prodocd2 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : Special mention  
* Parameters  :

* Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/
import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Spemenmt2<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: -.
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class Spemenmt2 extends KCXMessage {
	
	private String addInfCodMT23;
	
	private enum ESpemenmt2Tags {
		AddInfCodMT23;
	}
	
	public Spemenmt2() {
      	super();
	}

	public Spemenmt2(XMLEventReader parser) {
		super(parser);
	}      

	public Spemenmt2(XmlMsgScanner scanner) {
		super(scanner);
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
	  if (value == null) {
		
		switch ((ESpemenmt2Tags) tag) {
		default:
				return;
		}
	  } else {
		switch ((ESpemenmt2Tags) tag) {
		case AddInfCodMT23:		setAddInfCodMT23(value);
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

	public Enum translate(String token) {
		try {
			return ESpemenmt2Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getAddInfCodMT23() {
		return addInfCodMT23;
	
	}

	public void setAddInfCodMT23(String addInfCodMT23) {
		this.addInfCodMT23 = Utils.checkNull(addInfCodMT23);
	}
}
