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
 * Description	: Common class for MsgENV: GAR Guarantee
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Gar extends KCXMessage {

	private String typ;
	private ArrayList<Iga> igaList;

	public Gar() {
      	super();
	}

	public Gar(XMLEventReader parser) {
		super(parser);
	}      

	public Gar(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EGar {
		TYP,    
		IGA,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EGar) tag) {
		case IGA:  	
			Iga iga = new Iga(getScanner());  	
			iga.parse(tag.name());
			addIgaList(iga);
			break;
		default:
				return;
		}
	  } else {
		switch ((EGar) tag) {
		case TYP:
			setTyp(value);
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
			return EGar.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}

	public ArrayList<Iga> getIgaList() {
		return igaList;
	}
	public void setIgaList(ArrayList<Iga> list) {
		this.igaList = list;
	}
	public void addIgaList(Iga iga) {
		if (igaList == null) {
			igaList = new ArrayList<Iga>();
		}
		igaList.add(iga);
	}
	
	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(typ) &&				
				igaList == null); 
	}	

}
