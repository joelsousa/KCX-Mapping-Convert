package com.kewill.kcx.component.mapping.formats.kex.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.formats.kex.common.Shheader;

/**
 * Modul		: MsgKex<br>
 * Erstellt		: 08.11.2011<br>
 * Beschreibung	: Export declaration message of Kewill Export
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class MsgKex extends KCXMessage {
	private Shheader			shheader; 	

	public MsgKex() {
		super();		
	}

	public MsgKex(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}		
	
	private enum EMsgKex {
		shheader;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EMsgKex) tag) {	
				case shheader:
					shheader = new Shheader(getScanner());
					shheader.parse(tag.name());	
					break;	
				default:
					return;
			}
		} else {
			switch ((EMsgKex) tag) {
		
			default:
				return;
			}
		}	
	}

	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EMsgKex.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public Shheader getShheader() {
		return shheader;
	}

	public void setShheader(Shheader shheader) {
		this.shheader = shheader;
	}
	
}
