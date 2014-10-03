package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE13
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class IE29 extends KCXMessage {

	private String ctr2;	//Code traitement
	private String mrn;		//Numéro du transit MRN
	private IE13 ie13;		//NCTSDeclaration

	public IE29() {
      	super();
	}

	public IE29(XMLEventReader parser) {
		super(parser);
	}      

	public IE29(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EIE29 {
		CTR2,
		IE13,   
		MRN,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EIE29) tag) {
			case IE13:
				ie13 = new IE13(getScanner());  	
				ie13.parse(tag.name());
				break;
		default:
				return;
		}
	  } else {
		switch ((EIE29) tag) {
		case CTR2:
			setCtr2(value);
			break;
		case MRN:
			setMrn(value);
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
			return EIE29.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCtr2() {
		return ctr2;
	}
	public void setCtr2(String ctr2) {
		this.ctr2 = ctr2;
	}

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public IE13 getIE13() {
		return ie13;
	}
	public void setIE13(IE13 ie13) {
		this.ie13 = ie13;
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(ctr2) &&				
				Utils.isStringEmpty(mrn) &&
				(ie13 == null || ie13.isEmpty());	
	}	

}
