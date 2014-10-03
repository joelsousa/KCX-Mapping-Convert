package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Spemenmt2<br>
 * Date Created	: 23.06.2011<br>
 * Description	: Special Mention
 * 
 * @author Frederick T.
 * @version 1.0.00
 */

public class Spemenmt2 extends KCXMessage {
	
	private String code;
	
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
		case AddInfCodMT23:		setCode(value);
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = Utils.checkNull(code);
	}

}
