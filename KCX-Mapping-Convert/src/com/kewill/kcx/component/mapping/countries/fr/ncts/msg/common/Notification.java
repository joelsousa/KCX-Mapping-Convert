package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Notification
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Notification extends KCXMessage {

	private Entete entete;	
	private Gen gen;		
	private Evenements evenements;		

	public Notification() {
      	super();
	}

	public Notification(XMLEventReader parser) {
		super(parser);
	}      

	public Notification(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EfrNotification {
		Entete,
		Gen,   
		Evenements,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EfrNotification) tag) {
			case Entete:
				entete = new Entete(getScanner());  	
				entete.parse(tag.name());
				break;
			case Gen:
				gen = new Gen(getScanner());  	
				gen.parse(tag.name());
				break;
			case Evenements:
				evenements = new Evenements(getScanner());  	
				evenements.parse(tag.name());
				break;
		default:
				return;
		}
	  } else {
		switch ((EfrNotification) tag) {
		
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
			return EfrNotification.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	
	public Entete getEntete() {
		return entete;
	}
	public void setEntete(Entete entete) {
		this.entete = entete;
	}

	public Gen getGen() {
		return gen;
	}
	public void setGen(Gen gen) {
		this.gen = gen;
	}

	public Evenements getEvenements() {
		return evenements;
	}
	public void setEvenements(Evenements evenements) {
		this.evenements = evenements;
	}

	public boolean isEmpty() {
		return 	(entete == null) &&				
				(gen == null || gen.isEmpty()) &&
				(evenements == null);	
	}	

}
