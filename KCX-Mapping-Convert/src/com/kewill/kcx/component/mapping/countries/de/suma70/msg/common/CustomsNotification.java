package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 12.07.2013<br>
 * Description	: 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CustomsNotification extends KCXMessage {
	
	private String 	mrn;       		
	private String  itemNumber;   
	private String 	contentCode;  	 	//CSN only
	private String 	contentSubCode;    	//CSN only
	private String 	countryCode;    	//CSN only
	
	private enum EEntrySuma {
		//KIDS							
		Content,	MRN,
		ItemNumber,
		ContentCode,
		ContentSubCode,
		CountryCode,
	}
	
	public CustomsNotification() {
		super();  
	}

    public CustomsNotification(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EEntrySuma) tag) {
  			
  			default:
  					return;
  			}
  		} else {

  			switch ((EEntrySuma) tag) {
  			case MRN:
  			case Content:
  				setContent(value);
  				break;
  				
  			case ItemNumber:
  				setItemNumber(value);
  				break;
  				
  			case ContentCode:
  				setContentCode(value);
  				break;
  			case ContentSubCode:
  				setContentSubCode(value);
  				break;
  			case CountryCode:
  				setCountryCode(value);
  				break;
  				
  	  		default:
  					return;
  			}
  		}
		
	}

	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub	
	}

	public Enum translate(String token) {
		 try {
				return EEntrySuma.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getContent() {
		return mrn;
	}
	public void setContent(String mrn) {
		this.mrn = mrn;
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String value) {
		this.itemNumber = value;
	}

	
	public String getContentCode() {
		return contentCode;
	}
	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}

	public String getContentSubCode() {
		return contentSubCode;
	}
	public void setContentSubCode(String contentSubCode) {
		this.contentSubCode = contentSubCode;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(itemNumber));
	}
}
