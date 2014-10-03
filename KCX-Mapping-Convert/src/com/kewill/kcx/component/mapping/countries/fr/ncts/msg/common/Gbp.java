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
 * Description	: Common class for MsgENV: GBP = Guarantee/Reference/AccessCode
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Gbp extends KCXMessage {
	
	private ArrayList<String> codList;

	public Gbp() {
      	super();
	}

	public Gbp(XMLEventReader parser) {
		super(parser);
	}      

	public Gbp(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EGbp {
		COD,		
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
		case COD:  		
			addCodList(value);
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
		
	public ArrayList<String> getCodList() {
		return codList;
	}
	public void setCodList(ArrayList<String> list) {
		this.codList = list;
	}
	public void addCodList(String code) {
		if (codList == null) {
			codList = new ArrayList<String>();
		}
		codList.add(code);
	}
	
	public boolean isEmpty() {
		return 	codList == null; 
	}	

}
