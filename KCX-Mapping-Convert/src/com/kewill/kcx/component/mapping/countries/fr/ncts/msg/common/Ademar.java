package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: ADEMAR
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Ademar extends KCXMessage {

	private String rca;
	private String dpm;
	private String cdc;
	private String mag;
	private String port;
	private String dossier;
	
	public Ademar() {
      	super();
	}

	public Ademar(XMLEventReader parser) {
		super(parser);
	}      

	public Ademar(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Eitot {
		RCA,
		DPM,
		CDC,
		MAG,
		PORT,
		DOSSIER;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Eitot) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((Eitot) tag) {
		case RCA:
			setRca(value);
			break;
		case DPM:  
			setDpm(value);
			break;
		case CDC:
			setCdc(value);
			break;
		case MAG:
			setMag(value);
			break;
		case PORT:
			setPort(value);
			break;
		case DOSSIER:
			setDossier(value);
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
			return Eitot.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	

	public String getRca() {
		return rca;
	}
	public void setRca(String rca) {
		this.rca = rca;
	}

	public String getDpm() {
		return dpm;
	}
	public void setDpm(String dpm) {
		this.dpm = dpm;
	}

	public String getCdc() {
		return cdc;
	}
	public void setCdc(String cdc) {
		this.cdc = cdc;
	}

	public String getMag() {
		return mag;
	}
	public void setMag(String mag) {
		this.mag = mag;
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getDossier() {
		return mag;
	}
	public void setDossier(String mag) {
		this.mag = mag;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(rca) &&	
				Utils.isStringEmpty(dpm) &&	
				Utils.isStringEmpty(cdc) &&	
				Utils.isStringEmpty(mag) &&	
				Utils.isStringEmpty(port) &&	
				Utils.isStringEmpty(dossier)); 
	}	

}
