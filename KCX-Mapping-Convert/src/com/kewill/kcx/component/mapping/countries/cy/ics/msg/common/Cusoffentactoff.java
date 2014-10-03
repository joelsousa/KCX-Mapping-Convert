package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CUSOFFENTACTOFF700<br>
 * Created		: 23.06.2011<br>
 * Description 	: Contains Subtree of Cusoffentactoff700 Cyprus.
 *                 
 * @author Jude Eco
 * @version 1.0.00
 */
public class Cusoffentactoff extends KCXMessage {
	private boolean debug = false;
	private XMLEventReader parser = null;
	
	private String actualOfficeFirstEntry;
	
	public Cusoffentactoff() {
		super();
	}
	
	public Cusoffentactoff(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public Cusoffentactoff(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECusoffentactoff {
		RefNumCUSOFFENTACTOFF7001, RefNumCUSOFFENTACTOFF701;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECusoffentactoff) tag) {
				default:
					return;
			}
		} else {
			switch ((ECusoffentactoff) tag) {
			case RefNumCUSOFFENTACTOFF7001:
			case RefNumCUSOFFENTACTOFF701:
				setActualOfficeFirstEntry(value);
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
			return ECusoffentactoff.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getActualOfficeFirstEntry() {
		return actualOfficeFirstEntry;
	}

	public void setActualOfficeFirstEntry(String actualOfficeFirstEntry) {
		this.actualOfficeFirstEntry = Utils.checkNull(actualOfficeFirstEntry);
	}
	
}
