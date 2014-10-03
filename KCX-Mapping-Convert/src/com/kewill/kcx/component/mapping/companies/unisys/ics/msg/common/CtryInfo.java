package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CtryInfo.<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class CtryInfo extends KCXMessage {

	private HashMap<String, String> enumMap = null;
	
	private String 	country;
	private String	conveyNo;
	private String  fltRef;
	private CustRef	refNo;	
	 
	private void initEnumMap() {
		enumMap = new HashMap<String, String>();		
		enumMap.put("CONVEY-NO", "ConveyNo");
		enumMap.put("FLT-REF", "FltRef");
		enumMap.put("REF-NO", "RefNo");
	}	
	
	private enum ECtryInfo {
		 // Unisys-TagNames, 			KIDS-TagNames
			COUNTRY,
			ConveyNo,
			FltRef,
			RefNo;
	}
	
	public CtryInfo() {
		super();
	   	initEnumMap();
	}    
   	
	public CtryInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap();
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECtryInfo) tag) {
			case RefNo:
				refNo = new CustRef(getScanner());  	
				//refNo.parse(tag.name());	
				refNo.parse("REF-NO");
				break;	
			default:
					return;
			}
		} else {
	
			switch ((ECtryInfo) tag) {
				case COUNTRY:
					setCountry(value);
					break;  											
				case ConveyNo:
					setConveyNo(value);
					break;  											
				case FltRef:
					setFltRef(value);
					break;  											

				default:
					break;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {	
	}

	@Override
	public Enum translate(String token) {
		String text = enumMap.get(token);
		if (text != null) {
			token = text;
		}
		try {
			return ECtryInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCountry() {
		return country;	
	}
	public void setCountry(String country) {
		this.country = Utils.checkNull(country);
	}

	public String getConveyNo() {
		return conveyNo;	
	}
	public void setConveyNo(String conveyNo) {
		this.conveyNo = Utils.checkNull(conveyNo);
	}

	public CustRef getRefNo() {
		return refNo;	
	}
	public void setRefNo(CustRef argument) {
		this.refNo = argument;
	}

	public String getFltRef() {
		return fltRef;	
	}
	public void setFltRef(String fltRef) {
		this.fltRef = Utils.checkNull(fltRef);
	}

}
