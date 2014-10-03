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
 * Description	: Common class for MsgENV: LIG/CAUTION 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Caution extends KCXMessage {
	
	private String marchVal;

	public Caution() {
      	super();
	}

	public Caution(XMLEventReader parser) {
		super(parser);
	}      

	public Caution(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ECaution {
		MARCH_VAL,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ECaution) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ECaution) tag) {		
		case MARCH_VAL:  		
			setMarchVal(value);
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
			return ECaution.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
		
	public String getMarchVal() {
		return marchVal;
	}
	public void setMarchVal(String value) {
		this.marchVal = value;
	}
	/*
	public void addMarchValList(String nr) {
		if (marchValList == null) {
			marchValList = new ArrayList<String>();
		}
		marchValList.add(nr);
	}	
	public boolean isEmpty() {
		return 	marchValList == null; 
	}	
*/
}
