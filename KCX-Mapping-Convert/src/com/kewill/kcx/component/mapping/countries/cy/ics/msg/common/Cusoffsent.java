package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CUSOFFSENT740<br>
 * Created		: 23.06.2011<br>
 * Description	: Contains Subtree of Cusoffsent740 Cyprus.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */
 
public class Cusoffsent extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String	customsOfficeOfSubsequentEntry;
	
	public Cusoffsent() {
		super();
	}
	
	public Cusoffsent(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Cusoffsent(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECusoffsent740 {
		RefNumSUBENR909;
	}
	
	public String getCustomsOfficeOfSubsequentEntry() {
		return this.customsOfficeOfSubsequentEntry;
	}
	public void setCustomsOfficeOfSubsequentEntry(String customsOSE) {
		this.customsOfficeOfSubsequentEntry	= Utils.checkNull(customsOSE);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECusoffsent740) tag) {
				default:
					return;
			}
		} else {
			switch((ECusoffsent740) tag) {
				case RefNumSUBENR909:	setCustomsOfficeOfSubsequentEntry(value);
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
			return ECusoffsent740.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
