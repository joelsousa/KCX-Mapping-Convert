package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: Iga = Guarantee Item
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Iga extends KCXMessage {

	private String num;	
	private String cod;
	private String aut;	
	private String lue;
	private String cautionMt;	
	private String cautionDev;
	private Lap lap;	 //Limite de validité non CE

	public Iga() {
      	super();
	}

	public Iga(XMLEventReader parser) {
		super(parser);
	}      

	public Iga(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EIga {
		NUM,
		COD,   
		AUT,
		LUE,
		CAUTION_MT,   
		CAUTION_DEV,
		LAP;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EIga) tag) {
			case LAP:
				lap = new Lap(getScanner());  	
				lap.parse(tag.name());
				break;
		default:
				return;
		}
	  } else {
		switch ((EIga) tag) {
		case NUM:
			setNum(value);
			break;
		case COD:
			setCod(value);
			break;
		case AUT:
			setAut(value);
			break;
		case LUE:
			setLue(value);
			break;
		case CAUTION_MT:
			setCautionMt(value);
			break;
		case CAUTION_DEV:
			setCautionDev(value);
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
			return EIga.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getAut() {
		return aut;
	}
	public void setAut(String aut) {
		this.aut = aut;
	}
	
	public String getLue() {
		return lue;
	}
	public void setLue(String lue) {
		this.lue = lue;
	}
	
	public String getCautionMt() {
		return cautionMt;
	}
	public void setCautionMt(String caution) {
		this.cautionMt = caution;
	}
	
	public String getCautionDev() {
		return cautionDev;
	}
	public void setCautionDev(String caution) {
		this.cautionDev = caution;
	}
	
	public Lap getLap() {
		return lap;
	}
	public void setLap(Lap lap) {
		this.lap = lap;
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(num) &&				
				Utils.isStringEmpty(cod) &&
				Utils.isStringEmpty(cod) &&
				Utils.isStringEmpty(cod) &&
				Utils.isStringEmpty(cod) &&
				Utils.isStringEmpty(cod) &&				
				(lap == null || lap.isEmpty());	
	}	

}
