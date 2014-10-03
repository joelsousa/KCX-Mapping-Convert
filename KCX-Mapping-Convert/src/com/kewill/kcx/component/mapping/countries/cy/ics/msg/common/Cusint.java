package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CustomsIntervention<br>
 * Created		: 2011.06.22<br>
 * Description	: CustomsIntervention tag in ICS.
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */
public class Cusint extends KCXMessage {
	
	private String itemNumber;
	private String code;
	private String text;
	private String lng;
	
	private boolean debug = false;
	
	private enum ECusint {					
		IteNumConCUSINT668,					
		CusIntCodCUSINT665,						
		CusIntTexCUSINT666,						
		CusIntTexCUSINT667LNG;						
	}
	
	public Cusint() {
		super();
	}
	public Cusint(XmlMsgScanner scanner) {
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
			switch((ECusint) tag) {
			default:
					return;
			}
		} else {
			switch((ECusint) tag) {
			
			case IteNumConCUSINT668:
				setItemNumber(value);
				break;
			case CusIntCodCUSINT665:
				setCode(value);
				break;
			case CusIntTexCUSINT666:
				setText(value);
				break;
			case CusIntTexCUSINT667LNG:
				setLng(value);
				break;
			default:
				return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		
	}

	@Override
	public Enum translate(String token) {
		try {
  			return ECusint.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = Utils.checkNull(code);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = Utils.checkNull(lng);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.itemNumber) &&
				Utils.isStringEmpty(this.code) &&
				Utils.isStringEmpty(this.text) &&
				Utils.isStringEmpty(this.lng));    		
	}

}
