package com.kewill.kcx.component.mapping.countries.mt.ics.msg;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Malta<br>
 * Created		: 30.08.2013<br>
 * Description 	: Contains Subtree of OPRNAK Functional Malta Error message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */
public class OperationFailure extends KCXMessage {	
	private XMLEventReader parser = null;
	
	private String errorType;
	private String errorMessage;

	public OperationFailure() {
		super();
	}
	
	public OperationFailure(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public OperationFailure(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EOperationFailure {		
		ErrorType,
		ErrorMessage;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EOperationFailure) tag) {
			default:
				return;
			}
		} else {
			switch ((EOperationFailure) tag) {
			case ErrorType:
				setErrorType(value);
				break;

			case ErrorMessage:
				setErrorMessage(value);
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
			return EOperationFailure.valueOf(token);
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = Utils.checkNull(errorMessage);
	}
	
}
