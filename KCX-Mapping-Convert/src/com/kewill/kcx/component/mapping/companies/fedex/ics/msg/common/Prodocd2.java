package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

/*
* Function    : Prodocd2 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : Document data 
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
 * Modul		: Prodocd2<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: -.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Prodocd2 extends KCXMessage {
	
	private String		type;
	private String		reference;
	private String		referenceLng;
	
	public Prodocd2() {
      	super();
	}

	public Prodocd2(XMLEventReader parser) {
		super(parser);
	}      

	public Prodocd2(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EProdocd2Tags {
		DocTypDC21,
		DocRefDC23,
		DocRefDCLNG;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EProdocd2Tags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EProdocd2Tags) tag) {
		case DocTypDC21:		setType(value);
			break;
		case DocRefDC23: 		setReference(value);
			break;
		case DocRefDCLNG: 		setReferenceLng(value);
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
			return EProdocd2Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getType() {
		return type;
	
	}

	public void setType(String type) {
		this.type = Utils.checkNull(type);
	}

	public String getReference() {
		return reference;
	
	}

	public void setReference(String reference) {
		this.reference = Utils.checkNull(reference);
	}

	public String getReferenceLng() {
		return referenceLng;
	
	}

	public void setReferenceLng(String referenceLng) {
		this.referenceLng = Utils.checkNull(referenceLng);
	}
}
