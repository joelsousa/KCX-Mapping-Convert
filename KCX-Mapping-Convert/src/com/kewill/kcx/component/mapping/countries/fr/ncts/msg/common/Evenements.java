package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Evenements
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Evenements extends KCXMessage {

	private ArrayList<Evenement>  evenementList;	
	
	public Evenements() {
      	super();
	}

	public Evenements(XMLEventReader parser) {
		super(parser);
	}      

	public Evenements(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EEvenements {
		Evenement,		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EEvenements) tag) {
			case Evenement:
				Evenement evenement = new Evenement(getScanner());  	
				evenement.parse(tag.name());
				addEvenementList(evenement);
				break;
		default:
				return;
		}
	  } else {
		switch ((EEvenements) tag) {
		
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
			return EEvenements.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public ArrayList<Evenement> getEvenementList() {
		return evenementList;
	}
	public void setEvenementList(ArrayList<Evenement> list) {
		this.evenementList = list;
	}	
	public void addEvenementList(Evenement eve) {
		if (this.evenementList == null) {
			this.evenementList = new ArrayList<Evenement>();
		}
		this.evenementList.add(eve);
	}
}
