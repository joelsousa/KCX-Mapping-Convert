package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: Container Data.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TransmissionDateTime extends KCXMessage {

	 private String transmissionDate;        
	 private String transmissionTime;                 
	 private String transmissionTimeZone;     
	
	 public TransmissionDateTime() {
		 super();  
	 }

	 public TransmissionDateTime(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EJobInforamtion {	
			// Kids-TagNames, 			UIDS-TagNames;
		 TransmissionDate,					
		 TransmissionTime,					
		 TransmissionTimeZone;			        				
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EJobInforamtion) tag) {
				default: return;			
			}
		} else {			
			switch ((EJobInforamtion) tag) {			
				case TransmissionDate:
					setTransmissionDate(value);
					break;
				case TransmissionTime:
					setTransmissionTime(value);
					break;
				case TransmissionTimeZone:
					setTransmissionTimeZone(value);
					break;
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }

	
	 public Enum translate(String token) {
		 try {
			return EJobInforamtion.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public String getTransmissionDate() {
		return transmissionDate;
	}
	public void setTransmissionDate(String argument) {
		this.transmissionDate = argument;
	}					
		
	public String getTransmissionTime() {
		return transmissionTime;
	}
	public void setTransmissionTime(String argument) {
		this.transmissionTime = argument;
	}
	
	public String getTransmissionTimeZone() {
		return transmissionTime;
	}
	public void setTransmissionTimeZone(String argument) {
		this.transmissionTime = argument;
	}	
}
