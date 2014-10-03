package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Entete
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Entete extends KCXMessage {

	private String refdec;	
	
	public Entete() {
      	super();
	}

	public Entete(XMLEventReader parser) {
		super(parser);
	}      

	public Entete(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EEntete {
		refdec,		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EEntete) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EEntete) tag) {
		case refdec:
			setRefdec(value);
			break;		
		
		default:
			return;
		} 
	  }
	}
	
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub	
	}

	public Enum translate(String token) {
		try {
			return EEntete.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getRefdec() {
		return refdec;
	}
	public void setRefdec(String refdec) {
		this.refdec = refdec;
	}
}
