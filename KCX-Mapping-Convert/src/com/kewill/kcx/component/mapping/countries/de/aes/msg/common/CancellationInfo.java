package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 13.07.2012<br>
 * Description	: new for V21: CancellationInfo with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class CancellationInfo extends KCXMessage {
	
	private String kindOfCancellation = "";	
	private String reason = "";
	
	private enum ECancellationInfo {
		KindOfCancellation,		TypeOfAnnulment,
		Reason,                 ReasonOfAnnulment;
  	}
		
	public CancellationInfo() {
		super();  
	}	
	 
	public CancellationInfo(XmlMsgScanner scanner) {
 		super(scanner);
 	}
	
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((ECancellationInfo) tag) {
				default:
						return;
				}
			} else {				
				switch ((ECancellationInfo) tag) {			
					case KindOfCancellation:
					case TypeOfAnnulment:
						setKindOfCancellation(value);
						break;						
					case Reason:
					case ReasonOfAnnulment:
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
				return ECancellationInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
				return null;
		}
	}			

	public String getKindOfCancellation() {
		return kindOfCancellation;	
	}
	public void setKindOfCancellation(String value) {
		this.kindOfCancellation = value;
	}

	public String getReason() {
		return reason;	
	}
	public void setReason(String value) {
		this.reason = value;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.kindOfCancellation) &&  
		        Utils.isStringEmpty(this.reason));       
	}
	
}
