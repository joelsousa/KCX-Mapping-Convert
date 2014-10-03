package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: TColis
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Cdt extends KCXMessage {

	private String tco;
	private String nbc;
	private String nbp;
	private String txt;
	
	public Cdt() {
      	super();
	}

	public Cdt(XMLEventReader parser) {
		super(parser);
	}      

	public Cdt(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Ecdt {
		TCO,
		NBC,		
		NBP,		
		TXT;
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
		case TCO:
			setTco(value);
			break;
		case NBC:  
			setNbc(value);
			break;
		case NBP:
			setNbp(value);
			break;
		case TXT:
			setTxt(value);
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

	public String getTco() {
		return tco;
	}

	public void setTco(String tco) {
		this.tco = tco;
	}

	public String getNbc() {
		return nbc;
	}

	public void setNbc(String nbc) {
		this.nbc = nbc;
	}

	public String getNbp() {
		return nbp;
	}

	public void setNbp(String nbp) {
		this.nbp = nbp;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(tco) &&	
				Utils.isStringEmpty(nbc) &&	
				Utils.isStringEmpty(nbp) &&					
				Utils.isStringEmpty(txt)); 
	}	

}
