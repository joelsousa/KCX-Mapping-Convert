/*
 * Function    : MsgErrInf.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains the Message Error 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.countries.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: KCX<br>
 * Created		: 19.01.2012<br>
 * Description	: UIDS - Failure message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Reason extends KCXMessage {
	
	private List<String> reasonList;			
    
	public Reason() {
		super();
	}
	
	 public Reason(XmlMsgScanner scanner) {
	  		super(scanner);
	  	}

	public Enum translate(String token) {
		try {
			return EReason.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public void stoppElement(Enum tag) {
	}
	
	private enum EReason {	
		// Uids-TagNames, 			
           Text;				
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EReason) tag) {				
				default:
						return;
			}
		} else {	
			switch ((EReason) tag) {
				
				case Text:
					addReasonList(value);
					break;				
			
				default:
			}
		}
	}	

	public List<String> getReasonList() {
		return reasonList;
	}	
	public void setReasonList(List<String> list) {
		this.reasonList = list;
	}
	public void addReasonList(String argument) {
		if (reasonList == null) {
			reasonList = new ArrayList<String>();	
		}
		this.reasonList.add(argument);
	}
}
