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
 * Description	: Common class for MsgENV: LAP is in IGA: Limite de validité non CE 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Lap extends KCXMessage {
	
	private ArrayList<String> cpaList;

	public Lap() {
      	super();
	}

	public Lap(XMLEventReader parser) {
		super(parser);
	}      

	public Lap(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EGbp {
		CPA,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EGbp) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EGbp) tag) {		
		case CPA:  		
			addCpaList(value);
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
			return EGbp.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
		
	public ArrayList<String> getCpaList() {
		return cpaList;
	}
	public void setCpaList(ArrayList<String> list) {
		this.cpaList = list;
	}
	public void addCpaList(String code) {
		if (cpaList == null) {
			cpaList = new ArrayList<String>();
		}
		cpaList.add(code);
	}
	
	public boolean isEmpty() {
		return 	cpaList == null; 
	}	

}
