package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Transbordement
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Transbordement extends KCXMessage {

	private String identite;
	private String nationalite;
	private String date;
	private String autorite;
	private String lieu;
	private String pays;	
	
	public Transbordement() {
      	super();
	}

	public Transbordement(XMLEventReader parser) {
		super(parser);
	}      

	public Transbordement(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ETransbordement {
		identite,
		nationalite,				
		date,
		autorite,
		lieu,
		pays,	
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ETransbordement) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ETransbordement) tag) {
		case identite:
			setIdentite(value);
			break;		
		case nationalite:
			setNationalite(value);
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
			return ETransbordement.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	public String getIdentite() {
		return identite;
	}
	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
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
		return 	(Utils.isStringEmpty(identite) &&					
				Utils.isStringEmpty(nationalite) &&
				Utils.isStringEmpty(date) &&
				Utils.isStringEmpty(autorite) &&				
				Utils.isStringEmpty(lieu) &&													
				Utils.isStringEmpty(pays)); 
	}	
	
	
}
