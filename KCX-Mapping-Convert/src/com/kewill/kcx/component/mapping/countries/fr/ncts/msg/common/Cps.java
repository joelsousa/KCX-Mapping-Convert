package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: CPS
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Cps extends KCXMessage {

	private String cod;
	private String qte;
	
	public Cps() {
      	super();
	}

	public Cps(XMLEventReader parser) {
		super(parser);
	}      

	public Cps(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Ecps {
		COD,					
		QTE;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Ecps) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((Ecps) tag) {
		case COD:
			setCod(value);
			break;
		case QTE:  
			setQte(value);
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
			return Ecps.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCod() {
		return cod;
	}
	public void setCod(String code) {
		this.cod = code;
	}
	
	public String getQte() {
		return qte;
	}
	public void setQte(String qte) {
		this.qte = qte;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(cod) &&					
				Utils.isStringEmpty(qte)); 
	}	

}
