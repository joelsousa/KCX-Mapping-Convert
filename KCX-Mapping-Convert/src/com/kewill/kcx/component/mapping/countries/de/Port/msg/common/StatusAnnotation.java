package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: GoodsItem.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class StatusAnnotation extends KCXMessage {
	
	private String annotationKey; 	
	private List<String>	textList;
		
	public StatusAnnotation() {
		super();				
	}

	public StatusAnnotation(XmlMsgScanner scanner) {
		super(scanner);
	}
		
	private enum EStatusAnnotation {
		//KIDS:							KFF:
		AnnotationKey,                  
		Text;     
				
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EStatusAnnotation) tag) {					
			
			default:
				return;
			}
	    } else {
	    	switch ((EStatusAnnotation) tag) {
			case AnnotationKey:			
				 setAnnotationKey(value);
				 break;							
			case Text:
				addTextList(value);
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
  			return EStatusAnnotation.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getAnnotationKey() {
		return annotationKey;
	}
	public void setAnnotationKey(String value) {
		this.annotationKey = value;
	}		
	
	
	
	public List<String> getTextList() {
		return textList;
	}
	public void setTextList(List<String> list) {
		this.textList = list;
	}
	public void addTextList(String argument) {
		if (textList == null) {
			textList = new Vector<String>();	
		}
		this.textList.add(argument);
	}						

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.annotationKey) && 
		        this.textList == null);  
	}
}

