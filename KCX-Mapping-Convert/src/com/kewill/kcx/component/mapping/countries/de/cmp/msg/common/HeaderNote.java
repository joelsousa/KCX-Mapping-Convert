package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: HeaderNote.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class HeaderNote extends KCXMessage {

	private String 	contentCode;
	private String 	content;
		
	private enum EHeaderNote {
		//CMP
		ContentCode,
		Content,
	}
	
	public HeaderNote() {
		super();  
	}

    public HeaderNote(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EHeaderNote) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EHeaderNote) tag) {
  				case ContentCode:  				
  					setContentCode(value);
  					break;
  					
  				case Content:
  					setContent(value);
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
  			return EHeaderNote.valueOf(token);
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
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(contentCode) && Utils.isStringEmpty(content));
	}
	
}
