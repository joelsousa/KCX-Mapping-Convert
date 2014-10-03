package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Date			: 18.05.2010<br>
 * Description  : Contains the Member for save tags from the UIDS message.   
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class HeaderEaadType extends KCXMessage {
	
	private String sequenceNumber;
	private String dateOfUpdateValidation;
	private HeaderEaadDraft headerEaadDraft;
		
	private enum EHeaderEaadType {
		//KIDS : are simple tags UIDS:
								SequenceNumber,								
								DateOfUpdateValidation,         
								DTCandJTandTA;
	}
	
	public HeaderEaadType() {
  		super();
  	}
	 
	public HeaderEaadType(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EHeaderEaadType) tag) {
			case DTCandJTandTA:
				HeaderEaadDraft headerEaadDraft = new HeaderEaadDraft(getScanner());
				headerEaadDraft.parse(tag.name());
			default:
				return;
			}
	    } else {
	    	switch ((EHeaderEaadType) tag) {
	    	case SequenceNumber:
	    		 setSequenceNumber(value);
				 break;			
			case DateOfUpdateValidation:
				 setDateOfUpdateValidation(value);
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
  			return EHeaderEaadType.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}

	public String getDateOfUpdateValidation() {
		return this.dateOfUpdateValidation;
	}
	public void setDateOfUpdateValidation(String argument) {
		this.dateOfUpdateValidation = argument;
	}
	
	public HeaderEaadDraft getHeaderEaadDraft() {
		return this.headerEaadDraft;
	}
	public void setHeaderEaadDraft(HeaderEaadDraft argument) {
		this.headerEaadDraft = argument;
	}	

	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.sequenceNumber) && 
			Utils.isStringEmpty(this.dateOfUpdateValidation) &&     		
    		(this.headerEaadDraft != null));			
	}
}
