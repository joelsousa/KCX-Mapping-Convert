package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: ADEMAR
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Dm extends KCXMessage {

	private String li1;
	private String li2;
	private String li3;
	private String li4;
	
	public Dm() {
      	super();
	}

	public Dm(XMLEventReader parser) {
		super(parser);
	}      

	public Dm(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum Edm {
		LI1,
		LI2,
		LI3,
		LI4;		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((Edm) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((Edm) tag) {
		case LI1:
			setLi1(value);
			break;
		case LI2:
			setLi2(value);
			break;
		case LI3:
			setLi3(value);
			break;
		case LI4:
			setLi4(value);
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
			return Edm.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	
	public String getLi1() {
		return li1;
	}
	public void setLi1(String li1) {
		this.li1 = li1;
	}

	public String getLi2() {
		return li2;
	}
	public void setLi2(String li2) {
		this.li2 = li2;
	}

	public String getLi3() {
		return li3;
	}
	public void setLi3(String li3) {
		this.li3 = li3;
	}

	public String getLi4() {
		return li4;
	}
	public void setLi4(String li4) {
		this.li4 = li4;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(li1) &&	
				Utils.isStringEmpty(li2) &&	
				Utils.isStringEmpty(li3) &&					
				Utils.isStringEmpty(li4)); 
	}	

}
