package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains the Member for save the KIDS- and UIDS-tags.                  
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UnsatisfactoryReason extends KCXMessage {

	private String unsatisfactoryReasonCode;
	private Text complementaryInformation;
		
	private enum EUnsatisfactoryReason {
		//KIDS:                     //UIDS:
		UnsatisfactoryReasonCode,   //not defined
		ComplementaryInformation;	//not defined
	}
			 
	public UnsatisfactoryReason(XmlMsgScanner scanner) {
		super(scanner);
	}
			   	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EUnsatisfactoryReason) tag) {	
  			/*
  			case ComplementaryInformation:
  				complementaryInformation = new Text(getScanner());
  				complementaryInformation.parse(tag.name());					
				break;  			
  			*/
			default:
  					return;
  			}
  		} else {
			switch ((EUnsatisfactoryReason) tag) {
			case UnsatisfactoryReasonCode:
				setUnsatisfactoryReasonCode(value);
				break;
			case ComplementaryInformation:
				//complementaryInformation = new Text(value, attr.getValue("language"));
				complementaryInformation = new Text(value, attr);  //EI20110926
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
  			return EUnsatisfactoryReason.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getUnsatisfactoryReasonCode() {
		return unsatisfactoryReasonCode;	
	}
	public void setUnsatisfactoryReasonCode(String memberStateCode) {
		this.unsatisfactoryReasonCode = Utils.checkNull(memberStateCode);
	}

	public Text getComplementaryInformation() {
		return this.complementaryInformation;	
	}
	public void setComplementaryInformation(Text argument) {
		this.complementaryInformation = argument;
	}

	public boolean isEmpty() {
		String text = "";
		if (complementaryInformation != null) {
			text = complementaryInformation.getText();
		}
		return (Utils.isStringEmpty(this.unsatisfactoryReasonCode) && Utils.isStringEmpty(text));		
	}	
}
