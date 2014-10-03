package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Comcodgoditm.<br>
 * Date	Created	: 23.06.2011<br>
 * Description	: Commodity Code.
 * 
 * @author Frederick T.
 * @version 1.0.00
 */
public class Comcodgoditm extends KCXMessage {

	private String commodityCode;
	
	public Comcodgoditm() {
      	super();
	}

	public Comcodgoditm(XMLEventReader parser) {
		super(parser);
	}      

	public Comcodgoditm(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EComcodgoditmTags {
		ComNomCMD1;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EComcodgoditmTags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EComcodgoditmTags) tag) {
		case ComNomCMD1:  setCommodityCode(value);
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
			return EComcodgoditmTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCommodityCode() {
		return commodityCode;
	
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = Utils.checkNull(commodityCode);
	}
}
