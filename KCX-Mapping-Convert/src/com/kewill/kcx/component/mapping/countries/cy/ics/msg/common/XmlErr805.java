package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Cyprus<br>
 * Created		: 08.07.2011<br>
 * Description 	: Contains Subtree of XmlErr805 Cyprus.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */
public class XmlErr805 extends KCXMessage {	
	private XMLEventReader parser = null;
	
	private String errLocXMLER803;
	private String errLinNumXMLEr800;
	private String errColNumXMLER801;
	private String errReaXMLER802;
	private String oriAttValXMLER804;
	private String errCodXMLER806;

	public XmlErr805() {
		super();
	}
	
	public XmlErr805(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public XmlErr805(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EXmlErr805 {		
		ErrLocXMLER803,
		ErrLinNumXMLEr800,
		ErrColNumXMLER801,
		ErrReaXMLER802,
		OriAttValXMLER804,
		ErrCodXMLER806;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EXmlErr805) tag) {
			default:
				return;
			}
		} else {
			switch ((EXmlErr805) tag) {
			case ErrLocXMLER803:
				setErrLocXMLER803(value);
				break;

			case ErrLinNumXMLEr800:
				setErrLinNumXMLEr800r(value);
				break;

			case ErrColNumXMLER801:
				setErrColNumXMLER801n(value);
				break;

			case ErrReaXMLER802:
				setErrReaXMLER802(value);
				break;
				
			case OriAttValXMLER804:
				setOriAttValXMLER804(value);
				break;

			case ErrCodXMLER806:
				setErrCodXMLER806(value);
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
			return EXmlErr805.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getErrLocXMLER803() {
		return errLocXMLER803;
	}

	public void setErrLocXMLER803(String value) {
		this.errLocXMLER803 = value;
	}

	public String getErrLinNumXMLEr800r() {
		return errLinNumXMLEr800;
	}

	public void setErrLinNumXMLEr800r(String value) {
		this.errLinNumXMLEr800 = value;
	}

	public String getErrColNumXMLER801n() {
		return errColNumXMLER801;
	}

	public void setErrColNumXMLER801n(String value) {
		this.errColNumXMLER801 = value;
	}

	public String getErrReaXMLER802() {
		return errReaXMLER802;
	}

	public void setErrReaXMLER802(String value) {
		this.errReaXMLER802 = value;
	}
	
	public String getOriAttValXMLER804() {
		return oriAttValXMLER804;
	}

	public void setOriAttValXMLER804(String value) {
		this.oriAttValXMLER804 = value;
	}
	
	public String getErrCodXMLER806() {
		return errCodXMLER806;
	}

	public void setErrCodXMLER806(String value) {
		this.errCodXMLER806 = value;
	}
	
}
