package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;
import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TextBL extends KCXMessage {
		
	private List<String> textList;	
	private String textReference;
	private String languageCoded;
	
	private enum EBLText {	
		Text,
		TextReference,
		LanguageCoded, LanguageCode;			       			
   }	

	public TextBL() {
		super();  
	}

	public TextBL(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EBLText) tag) {
  			/*
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break; 
  				*/
				default:
  					break;
  			}
  		} else {

  			switch((EBLText) tag) {   			
  				case Text:
  					addTextList(value);
  					break; 
  				case TextReference:
  					setTextReference(value);
  					break; 	
  				case LanguageCode:
  				case LanguageCoded:
  					setLanguageCoded(value);
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
  			return EBLText.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public List<String> getTextList() {
		return textList;
	}    
	public void setTextList(List<String> list) {
		this.textList = list;
	}
	public void addTextList(String value) {
		if (value == null) {
			return;
		}
		if (textList == null) {
			textList = new ArrayList<String>();
		}
		this.textList.add(value);
	}
	
	public String getTextReference() {
		return textReference;
	}
    public void setTextReference(String value) {
		this.textReference = value;
	}	
		
	public String getLanguageCoded() {
		return languageCoded;
	}    
	public void setLanguageCoded(String value) {
		this.languageCoded = value;
	}
	
	public boolean isEmpty() {
		return textList == null && Utils.isStringEmpty(textReference) &&
		Utils.isStringEmpty(languageCoded); 
	}
}

