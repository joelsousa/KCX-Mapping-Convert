package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for IE07: Conteneurs
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Conteneurs extends KCXMessage {
	
	private ArrayList<String> conteneurList;

	public Conteneurs() {
      	super();
	}

	public Conteneurs(XMLEventReader parser) {
		super(parser);
	}      

	public Conteneurs(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EConteneurs {
		conteneur,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EConteneurs) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EConteneurs) tag) {		
		case conteneur:  		
			addConteneurList(value);
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
			return EConteneurs.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
		
	public ArrayList<String> getConteneurList() {
		return conteneurList;
	}
	public void setConteneurList(ArrayList<String> list) {
		this.conteneurList = list;
	}
	public void addConteneurList(String code) {
		if (conteneurList == null) {
			conteneurList = new ArrayList<String>();
		}
		conteneurList.add(code);
	}
	
	public boolean isEmpty() {
		return 	conteneurList == null; 
	}	

}
