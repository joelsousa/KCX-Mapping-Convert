package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class PreCarriage extends KCXMessage {

	 private String transportType;        //akv_vtpcde
	 private String callSign;             //ntpid       
	 private String transportationNumber; //ntpken   
	 private String deliveryDate;         //vlifda
	 private String annotation;           //trkvrm
	 private String forwarder;       //trknam
	 private String forwarderCode;          //trkcde
	
	 public PreCarriage() {
		 super();  
	 }

	 public PreCarriage(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum EVorlauf {	
			// Kids-TagNames, 			UIDS-TagNames;
		 		TransportType,					
		 		CallSign,					
		 		TransportationNumber,
		 		ExpectedDeliveryDate,
		 		Annotation,
		 		Forwarder,
		 		ForwarderCode;
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EVorlauf) tag) {
				default: return;			
			}
		} else {			
			switch ((EVorlauf) tag) {			
				case TransportType:
					setTransportType(value);
					break;
				case CallSign:
					setCallSign(value);
					break;
				case TransportationNumber:
					setTransportationNumber(value);
					break;
				case ExpectedDeliveryDate:
					setExpectedDeliveryDate(value);
					break;
				case Annotation:
					setAnnotation(value);
					break;
				case Forwarder:
					setForwarder(value);
					break;
				case ForwarderCode:
					setForwarderCode(value);
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
			return EVorlauf.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String argument) {
		this.transportType = argument;
	}					
		
	public String getCallSign() {
		return callSign;
	}
	public void setCallSign(String argument) {
		this.callSign = argument;
	}	
	
	public String getTransportationNumber() {
		return transportationNumber;
	}
	public void setTransportationNumber(String argument) {
		this.transportationNumber = argument;
	}	
		
	public String getExpectedDeliveryDate() {
		return deliveryDate;
	}
	public void setExpectedDeliveryDate(String argument) {
		this.deliveryDate = argument;
	}					
			
		public String getAnnotation() {
			return annotation;
		}
		public void setAnnotation(String argument) {
			this.annotation = argument;
		}	
		
		public String getForwarder() {
			return forwarder;
		}
		public void setForwarder(String argument) {
			this.forwarder = argument;
		}	
	
		public String getForwarderCode() {
			return forwarderCode;
		}
		public void setForwarderCode(String argument) {
			this.forwarderCode = argument;
		}
	
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.transportType) && Utils.isStringEmpty(this.callSign) && 
			        Utils.isStringEmpty(this.transportationNumber) && Utils.isStringEmpty(this.deliveryDate) && 
			        Utils.isStringEmpty(this.annotation) && 			       
			        Utils.isStringEmpty(this.forwarder) && Utils.isStringEmpty(this.forwarderCode));  
		}
}
