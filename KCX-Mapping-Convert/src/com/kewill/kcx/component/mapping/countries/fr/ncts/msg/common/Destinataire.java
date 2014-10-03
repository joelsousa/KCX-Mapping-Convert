package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Destinataire
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Destinataire extends KCXMessage {

	private String tin;
	private String nomoperateur;
	private String rueoperateur;
	private String paysoperateur;
	private String codepostaloperateur;
	private String villeoperateur;	
	
	public Destinataire() {
      	super();
	}

	public Destinataire(XMLEventReader parser) {
		super(parser);
	}      

	public Destinataire(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Egen {
		tin,
		nomoperateur,				
		rueoperateur,
		paysoperateur,
		codepostaloperateur,
		villeoperateur,	
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Egen) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((Egen) tag) {
		case tin:
			setTin(value);
			break;		
		case nomoperateur:
			setNomoperateur(value);
			break;
		case rueoperateur:
			setRueoperateur(value);
			break;
		case paysoperateur:
			setPaysoperateur(value);
			break;
		case codepostaloperateur:
			setCodepostaloperateur(value);
			break;
		case villeoperateur:
			setVilleoperateur(value);
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

	
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getNomoperateur() {
		return nomoperateur;
	}
	public void setNomoperateur(String nomoperateur) {
		this.nomoperateur = nomoperateur;
	}

	public String getRueoperateur() {
		return rueoperateur;
	}
	public void setRueoperateur(String rueoperateur) {
		this.rueoperateur = rueoperateur;
	}

	public String getPaysoperateur() {
		return paysoperateur;
	}
	public void setPaysoperateur(String paysoperateur) {
		this.paysoperateur = paysoperateur;
	}

	public String getCodepostaloperateur() {
		return codepostaloperateur;
	}
	public void setCodepostaloperateur(String codepostaloperateur) {
		this.codepostaloperateur = codepostaloperateur;
	}

	public String getVilleoperateur() {
		return villeoperateur;
	}

	public void setVilleoperateur(String villeoperateur) {
		this.villeoperateur = villeoperateur;
	}

	
	public boolean isEmpty() {		
		return 	(Utils.isStringEmpty(tin) &&					
				Utils.isStringEmpty(nomoperateur) &&
				Utils.isStringEmpty(rueoperateur) &&
				Utils.isStringEmpty(paysoperateur) &&				
				Utils.isStringEmpty(codepostaloperateur) &&													
				Utils.isStringEmpty(villeoperateur)); 
	}	
	
	
}
