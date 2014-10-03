package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ICS Cyprus.<br>
 * Erstellt		: 08.07.2011<br>
 * Beschreibung	: EntKeyHea200
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class EntKeyHea200 extends KCXMessage {

	private String expDatArrENTKEY201;
	private String traModBorENTKEY202;
	private String ideOfMeaOfTraENTKEY203;
	
	public EntKeyHea200() {
      	super();
	}

	public EntKeyHea200(XMLEventReader parser) {
		super(parser);
	}      

	public EntKeyHea200(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EEntKeyHea200 {
		ExpDatArrENTKEY201,
		TraModBorENTKEY202,
		IDEOfMeaOfTraENTKEY203,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EEntKeyHea200) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EEntKeyHea200) tag) {
		case ExpDatArrENTKEY201:  
			setExpDatArrENTKEY201(value);
			break;
		case TraModBorENTKEY202:  
			setTraModBorENTKEY202(value);
			break;
		case IDEOfMeaOfTraENTKEY203:  
			setIDEOfMeaOfTraENTKEY203(value);
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
			return EEntKeyHea200.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getExpDatArrENTKEY201() {
		return expDatArrENTKEY201;
	}
	public void setExpDatArrENTKEY201(String value) {
		this.expDatArrENTKEY201 = value;
	}

	public String getTraModBorENTKEY202() {
		return traModBorENTKEY202;
	}
	public void setTraModBorENTKEY202(String value) {
		this.traModBorENTKEY202 = value;
	}
	
	public String getIDEOfMeaOfTraENTKEY203() {
		return ideOfMeaOfTraENTKEY203;
	}
	public void setIDEOfMeaOfTraENTKEY203(String value) {
		this.ideOfMeaOfTraENTKEY203 = value;
	}
}
