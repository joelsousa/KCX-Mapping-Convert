package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CUSOFFFENT730305<br>
 * Created		: 22.06.2011<br>
 * Description 	: Contains Subtree of Cusofffent730305 Cyprus.
 *                 
 * @author Frederick T.
 * @version 1.0.00
 */
public class Cusofffent extends KCXMessage {
	private boolean debug   = false;
	private XMLEventReader 	parser	= null;
	
	private String customsOfficeOfFirstEntry;
	private String declaredDateOfArrival;
	
	public Cusofffent() {
		super();
	}
	
	public Cusofffent(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public Cusofffent(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECusofffent730305 {
		RefNumCUSOFFFENT731,
		ExpDatOfArrFIRENT733;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ECusofffent730305) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ECusofffent730305) tag) {
		case RefNumCUSOFFFENT731:  
			setCustomsOfficeOfFirstEntry(value);
			break;
		case ExpDatOfArrFIRENT733:
			setDeclaredDateOfArrival(value);
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
			return ECusofffent730305.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCustomsOfficeOfFirstEntry() {
		return customsOfficeOfFirstEntry;
	}

	public void setCustomsOfficeOfFirstEntry(String customsOfficeOfFirstEntry) {
		this.customsOfficeOfFirstEntry = Utils.checkNull(customsOfficeOfFirstEntry);
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}

	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival =  Utils.checkNull(declaredDateOfArrival);
	}
	
	
}
