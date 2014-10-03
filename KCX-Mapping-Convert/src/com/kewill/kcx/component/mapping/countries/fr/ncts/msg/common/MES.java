package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: MES = TMessage
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MES extends KCXMessage {

	private IE29 ie29;	

	public MES() {
      	super();
	}

	public MES(XMLEventReader parser) {
		super(parser);
	}      

	public MES(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EMes {
		IE29,   
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EMes) tag) {
			case IE29:
				ie29 = new IE29(getScanner());  	
				ie29.parse(tag.name());
				break;
		default:
				return;
		}
	  } else {
		switch ((EMes) tag) {
		
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
			return EMes.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public IE29 getIE29() {
		return ie29;
	}
	public void setIE29(IE29 ie) {
		this.ie29 = ie;
	}
		
	public boolean isEmpty() {
		return (ie29 == null || ie29.isEmpty());							
	}	

}
