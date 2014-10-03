package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Date			: 10.02.2014<br>
 * Description  : only UIDS. 
 *                 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class RejectionReason extends KCXMessage {

	private String code;
	private Text complementaryInformation;
		
	private enum ERejectionReason {
		//UIDS:                     
		Code,			
		ComplementaryInformation;		
	}
			 
	public RejectionReason(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ERejectionReason) tag) {	
				default:
  					return;
  			}
  		} else {
			switch ((ERejectionReason) tag) {
			case Code:
				setCode(value);
				break;
			case ComplementaryInformation:
				//setComplementaryInformation(value);
				complementaryInformation = new Text(value, attr); 
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
  			return ERejectionReason.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Text getComplementaryInformation() {
		return complementaryInformation;
	}
	public void setComplementaryInformation(Text complementaryInformation) {
		this.complementaryInformation = complementaryInformation;
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(this.code) && this.complementaryInformation == null);			
	}	
}
