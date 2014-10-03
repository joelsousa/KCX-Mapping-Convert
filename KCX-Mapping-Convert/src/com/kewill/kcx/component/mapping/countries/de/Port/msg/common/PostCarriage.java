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

public class PostCarriage extends KCXMessage {

	 private String transportType;        //akn_ntpcde, 
	 private String callSign;             //ntpid       
	 private String transportationNumber; //ntpken      
	
	 public PostCarriage() {
		 super();  
	 }

	 public PostCarriage(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum ENachlauf {	
			// Kids-TagNames, 			UIDS-TagNames;
		 		TransportType,					
		 		CallSign,					
		 		TransportationNumber;			        				
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ENachlauf) tag) {
				default: return;			
			}
		} else {			
			switch ((ENachlauf) tag) {			
				case TransportType:
					setTransportType(value);
					break;
				case CallSign:
					setCallSign(value);
					break;
				case TransportationNumber:
					setTransportationNumber(value);
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
			return ENachlauf.valueOf(token);
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
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.transportType) && Utils.isStringEmpty(this.callSign) && 		        		      
		        Utils.isStringEmpty(this.transportationNumber));  
	}
}
