package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Gen
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Gen extends KCXMessage {

	private String lieusousdouane;
	private String lieunotif;
	private LieuAutorise lieuAutorise;
	private String lieuagree;
	private String procsimpl;
	private String date;
	private String burpres;
	private Destinataire destinataire;
	
	public Gen() {
      	super();
	}

	public Gen(XMLEventReader parser) {
		super(parser);
	}      

	public Gen(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Egen {
		lieusousdouane,
		lieunotif,		
		LieuAutorise,	
		lieuagree,
		procsimpl,
		date,
		burpres,
		Destinataire;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Egen) tag) {
			case LieuAutorise:
				lieuAutorise = new LieuAutorise(getScanner());  	
				lieuAutorise.parse(tag.name());
				break;
			case Destinataire:
				destinataire = new Destinataire(getScanner());  	
				destinataire.parse(tag.name());
				break;
		default:
				return;
		}
	  } else {
		switch ((Egen) tag) {
		case lieusousdouane:
			setLieusousdouane(value);
			break;		
		case lieunotif:
			setLieunotif(value);
			break;
		case lieuagree:
			setLieuagree(value);
			break;
		case procsimpl:
			setProcsimpl(value);
			break;
		case date:
			setDate(value);
			break;
		case burpres:
			setBurpres(value);
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
			return Egen.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	public String getLieusousdouane() {
		return lieusousdouane;
	}

	public void setLieusousdouane(String lieusousdouane) {
		this.lieusousdouane = lieusousdouane;
	}

	public String getLieunotif() {
		return lieunotif;
	}

	public void setLieunotif(String lieunotif) {
		this.lieunotif = lieunotif;
	}

	public LieuAutorise getLieuAutorise() {
		return lieuAutorise;
	}

	public void setLieuAutorise(LieuAutorise lieuAutorise) {
		this.lieuAutorise = lieuAutorise;
	}

	public String getLieuagree() {
		return lieuagree;
	}

	public void setLieuagree(String lieuagree) {
		this.lieuagree = lieuagree;
	}

	public String getProcsimpl() {
		return procsimpl;
	}

	public void setProcsimpl(String procsimpl) {
		this.procsimpl = procsimpl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBurpres() {
		return burpres;
	}

	public void setBurpres(String burpres) {
		this.burpres = burpres;
	}

	public Destinataire getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Destinataire destinataire) {
		this.destinataire = destinataire;
	}

	public boolean isEmpty() {		
		return 	(Utils.isStringEmpty(date) &&	
				Utils.isStringEmpty(lieusousdouane) &&
				Utils.isStringEmpty(lieunotif) &&
				Utils.isStringEmpty(lieuagree) &&
				Utils.isStringEmpty(procsimpl) &&				
				Utils.isStringEmpty(burpres) &&									
				lieuAutorise == null &&
				destinataire == null); 
	}	
	
	
}
