package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 15.11.2013<br>
 * Description	: Common class for IE07: Scellesidentites
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Scellesidentites extends KCXMessage {
	
	private ArrayList<String> scellesidentiteList;

	public Scellesidentites() {
      	super();
	}

	public Scellesidentites(XMLEventReader parser) {
		super(parser);
	}      

	public Scellesidentites(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EScellesidentites {
		scellesidentite,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EScellesidentites) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EScellesidentites) tag) {		
		case scellesidentite:  		
			addScellesidentiteList(value);
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
			return EScellesidentites.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
		
	public ArrayList<String> getScellesidentiteList() {
		return scellesidentiteList;
	}
	public void setScellesidentiteList(ArrayList<String> list) {
		this.scellesidentiteList = list;
	}
	public void addScellesidentiteList(String code) {
		if (scellesidentiteList == null) {
			scellesidentiteList = new ArrayList<String>();
		}
		scellesidentiteList.add(code);
	}
	
	public boolean isEmpty() {
		return 	scellesidentiteList == null; 
	}	

}
