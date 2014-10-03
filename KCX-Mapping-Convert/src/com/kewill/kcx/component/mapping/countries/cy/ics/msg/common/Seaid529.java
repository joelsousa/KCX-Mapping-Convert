package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Seaid529.<br>
 * Date Created	: 23.06.2011<br>
 * Description	: Seals Identity
 * 
 * @author Frederick T.
 * @version 1.0.00
 */

public class Seaid529 extends KCXMessage {
	
	private String sealsIdentity;
	private String sealsIdentityLng;
	
	public Seaid529() {
      	super();
	}

	public Seaid529(XMLEventReader parser) {
		super(parser);
	}      

	public Seaid529(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ESeaid529Tags {
		SeaIdSEAID530,
		SeaIdSEAID530LNG;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ESeaid529Tags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ESeaid529Tags) tag) {
		case SeaIdSEAID530:  setSealsIdentity(value);
			break;
		case SeaIdSEAID530LNG: setSealsIdentityLng(value);
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
			return ESeaid529Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getSealsIdentity() {
		return sealsIdentity;
	
	}

	public void setSealsIdentity(String sealsIdentity) {
		this.sealsIdentity = Utils.checkNull(sealsIdentity);
	}

	public String getSealsIdentityLng() {
		return sealsIdentityLng;
	
	}

	public void setSealsIdentityLng(String sealsIdentityLng) {
		this.sealsIdentityLng = Utils.checkNull(sealsIdentityLng);
	}

}
