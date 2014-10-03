package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FUNERRER1305<br>
 * Created		: 22.06.2011<br>
 * Description 	: Contains Subtree of Funerrer1305 Cyprus.
 *                 
 * @author Frederick T.
 * @version 1.0.00
 */
public class Funerrer1 extends KCXMessage {
	private boolean debug = false;
	private XMLEventReader parser = null;
	
	private String errorType;
	private String errorPointer;
	private String errorReason;
	private String originalAttributeValue;

	public Funerrer1() {
		super();
	}
	
	public Funerrer1(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public Funerrer1(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EFunerrer1305Tags {
		//Cyprus
		ErrTypER11,
		ErrPoiER12,
		ErrReaER13,
		OriAttValER14
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EFunerrer1305Tags) tag) {
			default:
				return;
			}
		} else {
			switch ((EFunerrer1305Tags) tag) {
			case ErrTypER11:
				setErrorType(value);
				break;

			case ErrPoiER12:
				setErrorPointer(value);
				break;

			case ErrReaER13:
				setErrorReason(value);
				break;

			case OriAttValER14:
				setOriginalAttributeValue(value);
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
			return EFunerrer1305Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = Utils.checkNull(errorType);
	}

	public String getErrorPointer() {
		return errorPointer;
	}

	public void setErrorPointer(String errorPointer) {
		this.errorPointer = Utils.checkNull(errorPointer);
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = Utils.checkNull(errorReason);
	}

	public String getOriginalAttributeValue() {
		return originalAttributeValue;
	}

	public void setOriginalAttributeValue(String originalAttributeValue) {
		this.originalAttributeValue = Utils.checkNull(originalAttributeValue);
	}
	
	public String getText() {    //Ei20110713
		String text = "";   
		String number = ""; 
		if (!Utils.isStringEmpty(errorReason)) {
			number = errorReason;
		}
		if (!Utils.isStringEmpty(errorType)) {
			number = number + "/" + errorType;
		}
		text = errorPointer;
		if (!Utils.isStringEmpty(originalAttributeValue)) {		
			text = text + ", Value: " + originalAttributeValue; 
		}
		if (!Utils.isStringEmpty(number)) {
			text = text + ", Type: " + number;
		}
		
		return text;
	}
}
