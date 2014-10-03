package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 11.02.2013<br>
 * Description	: Contains the CustomsResponse Data with all Fields used in KIDS Manifest.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CustomsResponse extends KCXMessage {

	private String 		type;
	private String 		pointer;
	private String 		reason;	
	
	private enum ECustomsResponse {
		//KIDS							//UIDS
		Type,
		Pointer,
		Reason;						
	}
	
	public CustomsResponse() {
		super();  
	}

    public CustomsResponse(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECustomsResponse) tag) {			  					
  			default:
  					return;
  			}
  		} else {
  			switch ((ECustomsResponse) tag) {
  				case Type:  				
  					setType(value);
  					break;
  				case Pointer:
  					setPointer(value);
  					break;
  				case Reason:
  					setReason(value);
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
  			return ECustomsResponse.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return type;
	}
	public void setType(String value) {
		this.type = value;
	}

	public String getPointer() {
		return pointer;
	}
	public void setPointer(String value) {
		this.pointer = value;
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String value) {
		this.reason = value;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && Utils.isStringEmpty(pointer) &&
			Utils.isStringEmpty(reason));			
	}
}
