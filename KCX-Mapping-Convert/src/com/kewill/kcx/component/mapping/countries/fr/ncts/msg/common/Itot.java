package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: ITOT: Informations de totalisation
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Itot extends KCXMessage {

	private String nbc;		//Nombre total de conditionnement
	private String nbl;		//Nombre total de listes de chargement
	private String nbu;		//Nombre total d'unités logistiques
	private String pdb;		//Poids brut total
	private String caution; //Libellé caution totale déclarée pour le transit
	
	public Itot() {
      	super();
	}

	public Itot(XMLEventReader parser) {
		super(parser);
	}      

	public Itot(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Eitot {
		NBC,
		NBL,
		NBU,
		PDB,
		CAUTION_MT;
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
		case NBC:
			setNbc(value);
			break;
		case NBL:  
			setNbl(value);
			break;
		case NBU:
			setNbu(value);
			break;
		case PDB:
			setPdb(value);
			break;
		case CAUTION_MT:
			setCautionMt(value);
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
	

	public String getNbc() {
		return nbc;
	}
	public void setNbc(String nbc) {
		this.nbc = nbc;
	}

	public String getNbl() {
		return nbl;
	}
	public void setNbl(String nbl) {
		this.nbl = nbl;
	}

	public String getNbu() {
		return nbu;
	}
	public void setNbu(String nbu) {
		this.nbu = nbu;
	}

	public String getPdb() {
		return pdb;
	}
	public void setPdb(String pdb) {
		this.pdb = pdb;
	}

	public String getCautionMt() {
		return caution;
	}
	public void setCautionMt(String caution) {
		this.caution = caution;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(nbc) &&	
				Utils.isStringEmpty(nbl) &&	
				Utils.isStringEmpty(nbu) &&	
				Utils.isStringEmpty(pdb) &&	
				Utils.isStringEmpty(caution)); 
	}	

}
