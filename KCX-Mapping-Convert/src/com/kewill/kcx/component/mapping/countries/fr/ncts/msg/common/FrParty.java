package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: IEXP, IDES, IPO, IRE
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class FrParty extends KCXMessage {

	private String sir;  //tin
	private String nom;  //rep
	private String rue;
	private String vil;
	private String cdp;
	private String pay;
	private String lng;
	private String pvr;
	
	public FrParty() {
      	super();
	}

	public FrParty(XMLEventReader parser) {
		super(parser);
	}      

	public FrParty(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Eitot {
		SIR, CONSIGNEE_TIN, CONSIGNOR_TIN, CARRIER_TIN,
		NOM, CONSIGNEE_NOM, CONSIGNOR_NOM, CARRIER_NOM,
		REP, 
		RUE, CONSIGNEE_RUE, CONSIGNOR_RUE, CARRIER_RUE,
		VIL, CONSIGNEE_VIL, CONSIGNOR_VIL, CARRIER_VIL,
		CDP, CONSIGNEE_CDP, CONSIGNOR_CDP, CARRIER_CDP,
		PAY, CONSIGNEE_PAY, CONSIGNOR_PAY, CARRIER_PAY,
		LNG, CONSIGNEE_LNG, CONSIGNOR_LNG, CARRIER_LNG,		
		PVR;
		
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
		case SIR:
		case CONSIGNEE_TIN: 
		case CONSIGNOR_TIN:
		case CARRIER_TIN:
			setSir(value);
			break;
		case NOM:
		case REP:  
		case CONSIGNEE_NOM: 
		case CONSIGNOR_NOM:
		case CARRIER_NOM:
			setNom(value);
			break;
		case RUE:
		case CONSIGNEE_RUE: 
		case CONSIGNOR_RUE:
		case CARRIER_RUE:
			setRue(value);
			break;
		case VIL:
		case CONSIGNEE_VIL: 
		case CONSIGNOR_VIL:
		case CARRIER_VIL:
			setVil(value);
			break;
		case CDP:
		case CONSIGNEE_CDP: 
		case CONSIGNOR_CDP:
		case CARRIER_CDP:
			setCdp(value);
			break;
		case PAY:
		case CONSIGNEE_PAY: 
		case CONSIGNOR_PAY:
		case CARRIER_PAY:
			setPay(value);
			break;
		case PVR:
			setPvr(value);
			break;
		case LNG:
		case CONSIGNEE_LNG: 
		case CONSIGNOR_LNG:
		case CARRIER_LNG:
			setLng(value);
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
	
	public String getSir() {
		return sir;
	}
	public void setSir(String sir) {
		this.sir = sir;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVil() {
		return vil;
	}
	public void setVil(String vil) {
		this.vil = vil;
	}

	public String getCdp() {
		return cdp;
	}
	public void setCdp(String cdp) {
		this.cdp = cdp;
	}

	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPvr() {
		return pvr;
	}
	public void setPvr(String pvr) {
		this.pvr = pvr;
	}

	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}

	
	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(sir) &&	
				Utils.isStringEmpty(nom) &&	
				Utils.isStringEmpty(rue) &&	
				Utils.isStringEmpty(vil) &&	
				Utils.isStringEmpty(cdp) &&	
				Utils.isStringEmpty(pay) &&					
				Utils.isStringEmpty(pvr)); 
	}	

}
