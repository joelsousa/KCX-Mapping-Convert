package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: MSP = SprcialMention
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Msp extends KCXMessage {

	private String eue;
	private String eap;
	private String cod;
	private String inf;
	
	public Msp() {
      	super();
	}

	public Msp(XMLEventReader parser) {
		super(parser);
	}      

	public Msp(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Ecdt {
		EUE,
		EAP,		
		COD,		
		INF;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Ecdt) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((Ecdt) tag) {
		case EUE:
			setEue(value);
			break;
		case EAP:  
			setEap(value);
			break;
		case COD:
			setCod(value);
			break;
		case INF:
			setInf(value);
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
			return Ecdt.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getEue() {
		return eue;
	}

	public void setEue(String eue) {
		this.eue = eue;
	}

	public String getEap() {
		return eap;
	}

	public void setEap(String eap) {
		this.eap = eap;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getInf() {
		return inf;
	}

	public void setInf(String inf) {
		this.inf = inf;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(eue) &&	
				Utils.isStringEmpty(eap) &&	
				Utils.isStringEmpty(cod) &&					
				Utils.isStringEmpty(inf)); 
	}	

}
