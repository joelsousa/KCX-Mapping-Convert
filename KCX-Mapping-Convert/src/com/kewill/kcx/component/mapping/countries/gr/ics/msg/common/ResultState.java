package com.kewill.kcx.component.mapping.countries.gr.ics.msg.common;

/*
* Function    : Connr2 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : Container data 
* Parameters  :

* Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/


import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS GREECE.<br>
 * Created		: 09.08.2011<br>
 * Description	: ResultState
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ResultState extends KCXMessage {

	private String status;
	private String reasonCode;
	private String explanation;
	
	
	public ResultState() {
      	super();
	}

	public ResultState(XMLEventReader parser) {
		super(parser);
	}      

	public ResultState(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EConnr2Tags {
		status,
		reasonCode,
		explanation;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EConnr2Tags) tag) {
		
		default:
				break;
		}
	  } else {
		switch ((EConnr2Tags) tag) {
		case status:  
			setStatus(value);
			break;
		case reasonCode:  
			setReasonCode(value);
			break;
		case explanation:  
			setExplanation(value);
			break;
		default:
			break;
		} 
	  }
	}
	
	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	public Enum translate(String token) {
		try {
			return EConnr2Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getStatus() {
		return status;	
	}

	public void setStatus(String value) {
		this.status = Utils.checkNull(value);
	}
	
	public String getReasonCode() {
		return reasonCode;	
	}

	public void setReasonCode(String value) {
		this.reasonCode = Utils.checkNull(value);
	}
	
	public String getExplanation() {
		return explanation;	
	}

	public void setExplanation(String value) {
		this.explanation = Utils.checkNull(value);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.status) && 
				Utils.isStringEmpty(this.reasonCode) &&
				Utils.isStringEmpty(this.explanation)); 		      
	}	
	
}
