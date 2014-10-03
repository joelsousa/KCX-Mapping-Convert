package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Evenement
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Evenement extends KCXMessage {

	private String lieu;
	private String pays;
	private String nsti;
	private Incident incident;
	private Scelles scelles;
	private Transbordement transbordement;	
	private Conteneurs conteneurs;
	
	public Evenement() {
      	super();
	}

	public Evenement(XMLEventReader parser) {
		super(parser);
	}      

	public Evenement(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EEvenem {
		lieu,
		pays,				
		nsti,
		Incident,
		Scelles,
		Transbordement,	
		Conteneurs;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EEvenem) tag) {
		case Incident:
			incident = new Incident(getScanner());  	
			incident.parse(tag.name());			
			break;
		case Scelles:
			scelles = new Scelles(getScanner());  	
			scelles.parse(tag.name());			
			break;
		case Transbordement:
			transbordement = new Transbordement(getScanner());  	
			transbordement.parse(tag.name());			
			break;
		case Conteneurs:
			conteneurs = new Conteneurs(getScanner());  	
			conteneurs.parse(tag.name());			
			break;
		default:
				return;
		}
	  } else {
		switch ((EEvenem) tag) {
		case lieu:
			setLieu(value);
			break;		
		case pays:
			setPays(value);
			break;
		case nsti:
			setNsti(value);
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
			return EEvenem.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
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

	public String getNsti() {
		return nsti;
	}
	public void setNsti(String nsti) {
		this.nsti = nsti;
	}

	public Incident getIncident() {
		return incident;
	}
	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Scelles getScelles() {
		return scelles;
	}
	public void setScelles(Scelles scelles) {
		this.scelles = scelles;
	}

	public Transbordement getTransbordement() {
		return transbordement;
	}
	public void setTransbordement(Transbordement transbordement) {
		this.transbordement = transbordement;
	}

	public Conteneurs getConteneurs() {
		return conteneurs;
	}
	public void setConteneurs(Conteneurs conteneurs) {
		this.conteneurs = conteneurs;
	}

	public boolean isEmpty() {		
		return 	(Utils.isStringEmpty(lieu) &&					
				Utils.isStringEmpty(pays) &&
				Utils.isStringEmpty(nsti) &&
				incident == null &&
				scelles == null &&
				transbordement == null &&				
				conteneurs == null); 
	}	
	
	
}
