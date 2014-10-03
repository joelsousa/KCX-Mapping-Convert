package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 23.07.2012<br>
 * Description	: new for V21: AmendmentInfo with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class AmendmentInfo extends KCXMessage {
	
	private String kindOfAmendment = "";	
	private String reason = "";
	
	private enum EAmendmentInfo {
		KindOfAmendment,		TypeOfAmendment,
		Reason,                 ReasonOfAmendment;
  	}
		
	public AmendmentInfo() {
		super();  
	}	
	 
	public AmendmentInfo(XmlMsgScanner scanner) {
 		super(scanner);
 	}
	
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EAmendmentInfo) tag) {
				default:
						return;
				}
			} else {				
				switch ((EAmendmentInfo) tag) {			
					case KindOfAmendment:
					case TypeOfAmendment:
						setKindOfAmendment(value);
						break;						
					case Reason:
					case ReasonOfAmendment:
						setReason(value);
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
				return EAmendmentInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
				return null;
		}
	}			

	public String getKindOfAmendment() {
		return kindOfAmendment;	
	}
	public void setKindOfAmendment(String value) {
		this.kindOfAmendment = value;
	}

	public String getReason() {
		return reason;	
	}
	public void setReason(String value) {
		this.reason = value;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.kindOfAmendment) &&  
		        Utils.isStringEmpty(this.reason));       
	}
}
