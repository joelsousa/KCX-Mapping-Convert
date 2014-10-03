package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;
import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/*
 * Function    : Impope200 
 * Titel       :
 * Date        : 28.12.2010
 * Author      : Cubepoint Frederick Topico
 * Description : GoodsItem EsumaDataReferences 
 * Parameters  :

 * Changes
 * ------------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

/**
 * Modul		: Impope<br>
 * Erstellt		: 28.12.2010<br>
 * Beschreibung	: -.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class Impope extends KCXMessage {
	private boolean debug = false;
	private XMLEventReader parser = null;
	
	private String	mrn;
	private String	officeOfFirstEntryCountryCode;
	
	private enum EImpopeTags {
		MRN,
		OfficeOfFirstEntryCountryCode;
	}
	
	public Impope() {
		super();
	}
	
	public Impope(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public Impope(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
			switch ((EImpopeTags) tag) {
				default:
					return;
			}
		} else {
			switch ((EImpopeTags) tag) {
				case MRN:
					setMrn(value);
					break;
				case OfficeOfFirstEntryCountryCode:
					setOfficeOfFirstEntryCountryCode(value);
					break;
				default:
					return;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}
	
	public Enum translate(String token) {
		try {
			return EImpopeTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getOfficeOfFirstEntryCountryCode() {
		return officeOfFirstEntryCountryCode;
	}

	public void setOfficeOfFirstEntryCountryCode(
			String officeOfFirstEntryCountryCode) {
		this.officeOfFirstEntryCountryCode = Utils.checkNull(officeOfFirstEntryCountryCode);
	}
	
	
}
