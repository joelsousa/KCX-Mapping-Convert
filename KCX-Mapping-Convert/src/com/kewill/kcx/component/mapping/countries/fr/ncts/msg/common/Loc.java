package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: LOC: Localisation des marchandises
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Loc extends KCXMessage {

	private String chgt;
	private String cod;
	private String mcon;
	private String maut;
	private String buan;
	
	public Loc() {
      	super();
	}

	public Loc(XMLEventReader parser) {
		super(parser);
	}      

	public Loc(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ELoc {
		CHGT,
		COD,
		MCON,
		MAUT,
		BUAN;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ELoc) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ELoc) tag) {
		case CHGT:
			setChgt(value);
			break;
		case COD:  
			setCod(value);
			break;
		case MCON:
			setMcon(value);
			break;
		case MAUT:
			setMaut(value);
			break;
		case BUAN:
			setBuan(value);
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
			return ELoc.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	

	public String getChgt() {
		return chgt;
	}
	public void setChgt(String chgt) {
		this.chgt = chgt;
	}

	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMcon() {
		return mcon;
	}
	public void setMcon(String mcon) {
		this.mcon = mcon;
	}

	public String getMaut() {
		return maut;
	}
	public void setMaut(String maut) {
		this.maut = maut;
	}

	public String getBuan() {
		return buan;
	}
	public void setBuan(String buan) {
		this.buan = buan;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(chgt) &&	
				Utils.isStringEmpty(cod) &&	
				Utils.isStringEmpty(mcon) &&	
				Utils.isStringEmpty(maut) &&	
				Utils.isStringEmpty(buan)); 
	}	

}
