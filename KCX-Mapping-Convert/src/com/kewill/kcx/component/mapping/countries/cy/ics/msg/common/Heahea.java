package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cypern.
 * Created		: 08.07.2011
 * Description	: HEAHEA for CD917B
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Heahea extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String 	mrn;	
	private EntKeyHea200 entKey;

	
	public Heahea() {
		super();
	}
	
	public Heahea(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EHea200 {			
		DocNumHEA5,		
		ENTKEYHEA200,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHea200) tag) {
				case ENTKEYHEA200:
					entKey = new EntKeyHea200(getScanner());
					entKey.parse(tag.name());
					break;
				default:
						return;
			}
		} else {
			switch((EHea200) tag) {
				
				case DocNumHEA5:				
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

	@Override
	public Enum translate(String token) {
		try {
			return EHea200.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}	

	public EntKeyHea200 getEntKeyHea200() {
		return entKey;
	}
	public void setEntKeyHea200(EntKeyHea200 hea) {
		this.entKey = hea;
	}
}
