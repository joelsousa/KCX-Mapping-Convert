package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: IABO: Informations sur l'abonné NSTI
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Iabo extends KCXMessage {

	private String uti;   //Code utilisateur
	private String mtp;	  //Code utilisateur

	public Iabo() {
      	super();
	}

	public Iabo(XMLEventReader parser) {
		super(parser);
	}      

	public Iabo(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EIABO {
		UTI,   
		MTP,	
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EIABO) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EIABO) tag) {
		case UTI:
			setUti(value);
			break;
		case MTP:  
			setMtp(value);
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
			return EIABO.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	public String getUti() {
		return uti;
	}
	public void setUti(String value) {
		this.uti = value;
	}
	
	public String getMtp() {
		return mtp;
	}
	public void setMtp(String value) {
		this.mtp = value;
	}

	

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(uti) &&				
				Utils.isStringEmpty(mtp)); 
	}	

}
