package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Incident
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Incident extends KCXMessage {

	private String incident;
	private String info;
	private String date;
	private String autorite;
	private String lieu;
	private String pays;	
	
	public Incident() {
      	super();
	}

	public Incident(XMLEventReader parser) {
		super(parser);
	}      

	public Incident(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EIncident {
		incident,
		info,				
		date,
		autorite,
		lieu,
		pays,	
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EIncident) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EIncident) tag) {
		case incident:
			setIncident(value);
			break;		
		case info:
			setInfo(value);
			break;
		case date:
			setDate(value);
			break;
		case autorite:
			setAutorite(value);
			break;
		case lieu:
			setLieu(value);
			break;
		case pays:
			setPays(value);
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
			return EIncident.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getAutorite() {
		return autorite;
	}
	public void setAutorite(String autorite) {
		this.autorite = autorite;
	}

	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	
	public boolean isEmpty() {		
		return 	(Utils.isStringEmpty(incident) &&					
				Utils.isStringEmpty(info) &&
				Utils.isStringEmpty(date) &&
				Utils.isStringEmpty(autorite) &&				
				Utils.isStringEmpty(lieu) &&													
				Utils.isStringEmpty(pays)); 
	}	
	
	
}
