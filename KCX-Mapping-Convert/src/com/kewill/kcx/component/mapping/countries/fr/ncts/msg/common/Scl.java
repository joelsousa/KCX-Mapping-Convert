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
 * Description	: Common class for MsgENV: SCL = Seals
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Scl extends KCXMessage {

	private String nbs; 				//Nombre de scellés
	private ArrayList<String> nusList; 	//Marques des scellés

	public Scl() {
      	super();
	}

	public Scl(XMLEventReader parser) {
		super(parser);
	}      

	public Scl(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EScl {
		NBS,    
		NUS,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EScl) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EScl) tag) {
		case NBS:
			setNbs(value);
			break;
		case NUS:  		
			addNusList(value);
			break;
		
		default:
			return;
		} 
	  }
	}
	
	@Override
	public void stoppElement(Enum tag) {
		// Auto-generated method stub		
	}

	public Enum translate(String token) {
		try {
			return EScl.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getNbs() {
		return nbs;
	}
	public void setNbs(String nbs) {
		this.nbs = nbs;
	}

	public ArrayList<String> getNusList() {
		return nusList;
	}
	public void setNusList(ArrayList<String> nusList) {
		this.nusList = nusList;
	}
	public void addNusList(String nus) {
		if (nusList == null) {
			nusList = new ArrayList<String>();
		}
		nusList.add(nus);
	}
	
	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(nbs) &&				
				nusList == null); 
	}	

}
