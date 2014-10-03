package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: FunctionalErrorNcts 
 * Created     	: 31.08.2008
 * Description 	: Contains the FunctionalErrorNcts Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Michelle Bauza 
 * @version 1.0.00
 */
public class FunctionalErrorNcts extends KCXMessage {
	
	private String code;
	private String text;
	private String pointer;	
	
	private enum EFunctionalErrorNcts {
		//KIDS:				UIDS;:
		Code,				Type,
		Text,				Reason,
		Pointer,			//same	
		GRN,      //EI20110518 for MsgNCTSWriteOffNotification.GRNError(
	}
	
	public FunctionalErrorNcts() {
		super();
	}
	
	public FunctionalErrorNcts(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EFunctionalErrorNcts) tag) {
			default:
				return;
			}
	    } else {
			switch((EFunctionalErrorNcts) tag) {
			case Code:
			case Type:
				setCode(value);
				break;
			case Text:
			case Reason:
				setText(value);
				break;
			case Pointer:
			case GRN:
				setPointer(value);
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
  			return EFunctionalErrorNcts.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPointer() {
		return pointer;
	}
	public void setPointer(String pointer) {
		this.pointer = pointer;
	}

	public String getGRN() {    //EI20110518
		return pointer;
	}

	public void setGRN(String pointer) {   //EI20110518
		this.pointer = pointer;
	}
	
	public boolean isEmpty() {
		
		return Utils.isStringEmpty(this.code) &&
    		Utils.isStringEmpty(this.text) &&
    		Utils.isStringEmpty(this.pointer);
	}
	
}
