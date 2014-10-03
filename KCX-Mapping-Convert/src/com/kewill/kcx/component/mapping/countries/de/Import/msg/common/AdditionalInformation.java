package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class AdditionalInformation extends KCXMessage {

	private String type;
	private String text;
	private String code;

	private enum EPayment {
		StatementCode, StatementText, StatementType;
	}

	public AdditionalInformation() {
		super();
	}

	public AdditionalInformation(XmlMsgScanner scanner) {
		super(scanner);
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPayment) tag) {
			default:
				return;
			}
		} else {
			switch ((EPayment) tag) {
			case StatementCode:
				setStatementCode(value);
				break;
			case StatementText:
				setStatementText(value);
				break;
			case StatementType:
				setStatementType(value);
				break;

			default:
				break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EPayment.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getStatementType() {
		return type;
	}

	public void setStatementType(String argument) {
		this.type = argument;
	}

	public String getStatementText() {
		return text;
	}

	public void setStatementText(String argument) {
		this.text = argument;
	}

	public String getStatementCode() {
		return code;
	}

	public void setStatementCode(String argument) {
		this.code = argument;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(text) && Utils.isStringEmpty(code) && Utils
				.isStringEmpty(type));

	}

}
