package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: CustomsNote.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustomsNote extends KCXMessage {

	private String 		contentCode;
	private String 		content;
	private String 		subjectCode;
	private String 		countryId;
		
	private enum ECustomsNote {
		//CMP
		ContentCode,
		Content,
		SubjectCode,
		CountryID,
	}
	
	public CustomsNote() {
		super();  
	}

    public CustomsNote(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECustomsNote) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((ECustomsNote) tag) {
  				case ContentCode:  				
  					setContentCode(value);
  					break;
  					
  				case Content:
  					setContent(value);
  					break;
  					
  				case SubjectCode:
  					setSubjectCode(value);
  					break;  					
  					
  				case CountryID:
  					setCountryId(value);
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
  			return ECustomsNote.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getContentCode() {
		return contentCode;
	}
	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(contentCode) && Utils.isStringEmpty(content) &&
		Utils.isStringEmpty(subjectCode) && Utils.isStringEmpty(countryId);
	}	

}
