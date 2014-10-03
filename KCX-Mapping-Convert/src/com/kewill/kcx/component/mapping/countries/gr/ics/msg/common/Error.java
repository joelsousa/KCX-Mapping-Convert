package com.kewill.kcx.component.mapping.countries.gr.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
  * Module		: ICS GREECE.<br>
 * Created		: 09.08.2011<br>
 * Description	: Error
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Error extends KCXMessage {
	
	private XMLEventReader parser = null;
	
	private String errorCode;
	private String errorDescription;
	
	public Error() {
		super();
	}
	
	public Error(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public Error(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EErrorGR {	
		errorCode,
		errorDescription;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EErrorGR) tag) {
			default:
				return;
			}
		} else {
			switch ((EErrorGR) tag) {
			case errorCode:
				setErrorCode(value);
				break;

			case errorDescription:
				setErrorDescription(value);
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
			return EErrorGR.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorType) {
		this.errorCode = Utils.checkNull(errorType);
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorPointer) {
		this.errorDescription = Utils.checkNull(errorPointer);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.errorCode) && Utils.isStringEmpty(this.errorDescription)); 		      
	}	
}
