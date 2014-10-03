package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: Tinfos Colisage
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Ico extends KCXMessage {

	private String ndp;
	private Dm dm;
	private String pdb;
	private String pdn;
	
	public Ico() {
      	super();
	}

	public Ico(XMLEventReader parser) {
		super(parser);
	}      

	public Ico(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Eico {
		NDP,
		DM,		
		PDB,		
		PDN;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Eico) tag) {
			case DM:
				dm = new Dm(getScanner());  	
				dm.parse(tag.name());
				break;
		default:
				return;
		}
	  } else {
		switch ((Eico) tag) {
		case NDP:
			setNdp(value);
			break;		
		case PDB:
			setPdb(value);
			break;
		case PDN:
			setPdn(value);
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
			return Eico.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getNdp() {
		return ndp;
	}

	public void setNdp(String ndp) {
		this.ndp = ndp;
	}

	public Dm getDm() {
		return dm;
	}
	public void setDm(Dm dm) {
		this.dm = dm;
	}

	public String getPdb() {
		return pdb;
	}

	public void setPdb(String pdb) {
		this.pdb = pdb;
	}

	public String getPdn() {
		return pdn;
	}

	public void setPdn(String pdn) {
		this.pdn = pdn;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(ndp) &&					
				Utils.isStringEmpty(pdb) &&					
				Utils.isStringEmpty(pdn) &&
				dm == null); 
	}	

}
