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
 * Description	: Common class for MsgENV: CTR - Container
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Ctr extends KCXMessage {
	
	private String nct;

	public Ctr() {
      	super();
	}

	public Ctr(XMLEventReader parser) {
		super(parser);
	}      

	public Ctr(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EGbp {
		NCT,		
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
		case NCT:  		
			setNct(value);
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
		
	public String getNct() {
		return nct;
	}
	public void setNct(String nr) {
		this.nct = nr;
	}
	
}
