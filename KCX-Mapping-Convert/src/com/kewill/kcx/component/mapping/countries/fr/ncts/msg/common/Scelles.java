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
public class Scelles extends KCXMessage {

	private String nb;	
	private Scellesidentites scellesidentites;
	
	public Scelles() {
      	super();
	}

	public Scelles(XMLEventReader parser) {
		super(parser);
	}      

	public Scelles(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EEntete {
		nb,	
		Scellesidentites,
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EEntete) tag) {
		case Scellesidentites:
			scellesidentites = new Scellesidentites(getScanner());  	
			scellesidentites.parse(tag.name());		
			break;
			
		default:
				return;
		}
	  } else {
		switch ((EEntete) tag) {
		case nb:
			setNb(value);
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

	public String getNb() {
		return nb;
	}
	public void setNb(String nb) {
		this.nb = nb;
	}
	
	public Scellesidentites getScellesidentites() {
		return scellesidentites;
	}
	public void setScellesidentites(Scellesidentites atr) {
		this.scellesidentites = atr;
	}
}
