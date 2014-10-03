package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: LieuAutorise
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class LieuAutorise extends KCXMessage {

	private String code;	
	private String lieu;
	
	public LieuAutorise() {
      	super();
	}

	public LieuAutorise(XMLEventReader parser) {
		super(parser);
	}      

	public LieuAutorise(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ELieuAutorise {
		Code,	
		Lieu;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ELieuAutorise) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ELieuAutorise) tag) {
		case Code:			
			setCode(value);		
			break;	
		case Lieu:
			setLieu(value);
			break;	
		
		default:
			return;
		} 
	  }
	}
	
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub	
	}

	public Enum translate(String token) {
		try {
			return ELieuAutorise.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(code) &&				
				Utils.isStringEmpty(lieu);	
	}	

}
