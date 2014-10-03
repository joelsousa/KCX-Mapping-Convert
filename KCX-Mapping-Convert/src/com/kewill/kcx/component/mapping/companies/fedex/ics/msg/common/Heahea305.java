package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Heahea305<br>
 * Created		: 30.12.2010<br>
 * Description	: .
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class Heahea305 extends KCXMessage {
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String	mRN;
	private String	amendmentDateAndTime;
	private String	amendmentRejectionDateAndTime;
	
	public Heahea305() {
		super();
	}
	
	public Heahea305(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea305(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public String getMRN() {
		return this.mRN;
	}
	public void setMRN(String mrn) {
		this.mRN	= Utils.checkNull(mrn);
	}
	
	public String getAmendmentDateAndTime() {
		return this.amendmentDateAndTime;
	}
	public void setAmendmentDateAndTime(String amendmentDT) {
		this.amendmentDateAndTime	= Utils.checkNull(amendmentDT);
	}
	
	public String getAmendmentRejectionDateAndTime() {
		return this.amendmentRejectionDateAndTime;
	}
	public void setAmendmentRejectionDateAndTime(String amendmentRejDT) {
		this.amendmentRejectionDateAndTime	= Utils.checkNull(amendmentRejDT);
	}
	
	private enum EHeaheaTags {
		// Fedex,
		DocNumHEA5,
		DatTimAmeHEA113,
		AmeRejDatTimHEA112
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHeaheaTags) tag) {
				default:
					return;
			}
		} else {
			switch((EHeaheaTags) tag) {
				case DocNumHEA5:	setMRN(value);
									break;
				
				case DatTimAmeHEA113:	setAmendmentDateAndTime(value);
										break;
				
				case AmeRejDatTimHEA112:	setAmendmentRejectionDateAndTime(value);
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
			return EHeaheaTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
