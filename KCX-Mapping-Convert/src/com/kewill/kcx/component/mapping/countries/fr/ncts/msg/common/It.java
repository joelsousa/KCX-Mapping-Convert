package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: ITD ITF
 * 				: Information sur le transport au départ; Information sur le transport frontière 
 * @author iwaniuk
 * @version 1.0.00
 */
public class It extends KCXMessage {

	private String mod;
	private String typ;
	private String idt;
	private String nat;
	
	public It() {
      	super();
	}

	public It(XMLEventReader parser) {
		super(parser);
	}      

	public It(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EIT {
		MOD,
		TYP,
		IDT,
		NAT,		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EIT) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EIT) tag) {
		case MOD:
			setMod(value);
			break;
		case TYP:  
			setTyp(value);
			break;
		case IDT:
			setIdt(value);
			break;
		case NAT:
			setNat(value);
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
			return EIT.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	

	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getIdt() {
		return idt;
	}
	public void setIdt(String idt) {
		this.idt = idt;
	}

	public String getNat() {
		return nat;
	}
	public void setNat(String nat) {
		this.nat = nat;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(mod) &&	
				Utils.isStringEmpty(typ) &&	
				Utils.isStringEmpty(idt) &&	
				Utils.isStringEmpty(nat)); 
	}	

}
