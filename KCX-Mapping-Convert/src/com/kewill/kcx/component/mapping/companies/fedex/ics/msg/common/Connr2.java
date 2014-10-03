package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

/*
* Function    : Connr2 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : Container data 
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
 * Modul		: Connr2.<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: Container
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Connr2 extends KCXMessage {

	private String container;
	
	public Connr2() {
      	super();
	}

	public Connr2(XMLEventReader parser) {
		super(parser);
	}      

	public Connr2(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EConnr2Tags {
		ConNumNR21;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EConnr2Tags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EConnr2Tags) tag) {
		case ConNumNR21:  setContainer(value);
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
			return EConnr2Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getContainer() {
		return container;
	
	}

	public void setContainer(String container) {
		this.container = Utils.checkNull(container);
	}

}
