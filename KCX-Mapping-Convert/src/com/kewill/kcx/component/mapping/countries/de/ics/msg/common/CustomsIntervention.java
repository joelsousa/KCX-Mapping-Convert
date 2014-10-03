package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import org.xml.sax.Attributes;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CustomsIntervention<br>
 * Created		: 2010.07.19<br>
 * Description	: CustomsIntervention tag in ICS.
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */

public class CustomsIntervention extends KCXMessage {
	
	private String itemNumber;
	private String code;
	private String text;
	private String lng;

	private boolean debug = false;
	
	private enum ECustomsIntervention {
		//KIDS:						UIDS
		ItemNumber,					AffectedItemNumber,
		Code,						//same
		Text,						//same
		LNG							//no Equivalent

	}
	
	public CustomsIntervention() {
		super();
	}
	public CustomsIntervention(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch((ECustomsIntervention) tag) {
				default:
						return;
				}
			} else {
				switch((ECustomsIntervention) tag) {
				
				case AffectedItemNumber:
				case ItemNumber:
					setItemNumber(value);
				case Code:
					setCode(value);
				case Text:
					setText(value);
				case LNG:
					setLng(value);
				default:
					return;
				}
				
			}
		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
  			return ECustomsIntervention.valueOf(token);
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
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.itemNumber) &&
				Utils.isStringEmpty(this.code) &&
				Utils.isStringEmpty(this.text) &&
				Utils.isStringEmpty(this.lng));    		
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}		
	

}
