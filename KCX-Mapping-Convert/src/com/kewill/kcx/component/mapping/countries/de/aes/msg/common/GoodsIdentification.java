package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class GoodsIdentification extends KCXMessage {
	private List<String> additionalCodeList;
	private XMLEventReader 		parser	= null;
//	private boolean debug   = false;
		
	private enum EGoodsIdentificationTags {
		//KIDS
		AdditionalCode;		
	}
	 	 
	 public GoodsIdentification(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	 }
	 
	 public GoodsIdentification(XmlMsgScanner scanner) {
	  	super(scanner);
	 }
	 
	 public GoodsIdentification() {   
		super();			
	 }
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EGoodsIdentificationTags) tag) {
			default:
				    return;
			}
		} else {				
			switch ((EGoodsIdentificationTags) tag) {
				case AdditionalCode:
					addAdditionalCode(value);
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}
	
	public Enum translate(String token) {
		try {
				return EGoodsIdentificationTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	public void addAdditionalCode(String value) {
		if (this.additionalCodeList == null) {
			this.additionalCodeList = new ArrayList<String>();
		}
		this.additionalCodeList.add(value);
	}

	public List<String> getAdditionalCodeList() {
		return additionalCodeList;
	}
}
