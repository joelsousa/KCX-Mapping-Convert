package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
/**
 * Modul		: CustRef.<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Reference Data of Unisys
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class CustRef extends KCXMessage {

	private HashMap<String, String> enumMap = null;
	
	private String ensLref;
	private String divLref;
	private String arrLref;		
	
	private enum ECustRef {
		 // Unisys-TagNames, 			KIDS-TagNames
			EnsLref,
			DivLref,
			ArrLref;
	}
	
	private void initEnumMap() {
		enumMap = new HashMap<String, String>();
		enumMap.put("ENS-LREF", "EnsLref"); 
		enumMap.put("DIV-LREF", "DivLref"); 
		enumMap.put("ARR-LREF", "ArrLref");
	}	

	public CustRef() {
		super();
	   	initEnumMap();
	}    
   	
	public CustRef(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap();
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustRef) tag) {			
				default:
					return;
			}
		} else {
			switch ((ECustRef) tag) {
				case EnsLref:
					setEnsLref(value);
					break;  	
				case DivLref:
					setDivLref(value);
					break;
				case ArrLref:
					setArrLref(value);
					break;
					
				default:
					break;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
	}

	@Override
	public Enum translate(String token) {
		String text = enumMap.get(token);
		if (text != null) {
			token = text;
		}
		try {
			return ECustRef.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getEnsLref() {
		return ensLref;	
	}
	public void setEnsLref(String divLref) {
		this.ensLref = Utils.checkNull(divLref);
	}

	public String getDivLref() {
		return divLref;	
	}
	public void setDivLref(String divLref) {
		this.divLref = Utils.checkNull(divLref);
	}

	public String getArrLref() {
		return arrLref;	
	}
	public void setArrLref(String arrLref) {
		this.arrLref = Utils.checkNull(arrLref);
	}

}
