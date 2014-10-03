package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: FrProduct
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class FrDocument extends KCXMessage {

	private String tyd;
	private String ref;
	private String cpl;
	
	public FrDocument() {
      	super();
	}

	public FrDocument(XMLEventReader parser) {
		super(parser);
	}      

	public FrDocument(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EFrDocument {
		TYD,
		REF,					
		CPL;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EFrDocument) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EFrDocument) tag) {
		case TYD:
			setTyd(value);
			break;
		case REF:  
			setRef(value);
			break;
		case CPL:
			setCpl(value);
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
			return EFrDocument.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getTyd() {
		return tyd;
	}
	public void setTyd(String tyd) {
		this.tyd = tyd;
	}

	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getCpl() {
		return cpl;
	}
	public void setCpl(String cpl) {
		this.cpl = cpl;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(tyd) &&	
				Utils.isStringEmpty(ref) &&					
				Utils.isStringEmpty(cpl)); 
	}	

}
