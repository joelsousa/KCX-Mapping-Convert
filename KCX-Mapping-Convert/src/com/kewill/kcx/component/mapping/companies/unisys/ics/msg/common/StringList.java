package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustGoodsInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class StringList extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	
	private String tagName = "";
	private List <String> descList;
	private List <String> htxCodeList;
	private List <String> uldIdList;
	private List <String> unIdList;			
	
	 private enum EStringList {
	 // Unisys-TagNames, 			KIDS-TagNames
		 HtxCode,
		 UNID,
		 ULDID,
		 DESC;				   
	 }

	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("HTX-CODE", "HtxCode"); 			    		  
	 }	
	 	 
	 public StringList(String tagName) {
	    super();
	    this.tagName = tagName;
	    initEnumMap();
	    initLists();
	 }       
	 
	 public StringList(XmlMsgScanner scanner, String tagName) {
		super(scanner);
		this.tagName = tagName;
		initEnumMap();
		initLists(); 
	 }
	 public StringList(XmlMsgScanner scanner) {
			super(scanner);			
			initEnumMap();
			initLists(); 
		 }
	 private void initLists() {
		descList = new Vector<String>();
		htxCodeList = new Vector<String>();
		uldIdList = new Vector<String>();
		unIdList = new Vector<String>();
	 }
	 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EStringList) tag) {					
			default:
					return;
			}
		} else {
			switch ((EStringList) tag) {	
				case HtxCode:									
					htxCodeList.add(value);				
					break;
				case DESC:							
					descList.add(value);				
					break;
				case UNID:									
					unIdList.add(value);				
					break;
				case ULDID:								
					uldIdList.add(value);				
					break;	
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		String text = enumMap.get(token);  
		if (text != null) {
			token = text;
		}
		try {
			return EStringList.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	/*	
	public List<String> getList() {
		if (tagName.equals("DESC")) {
			return descList;
		} else if (tagName.equals("HTX-CODE")) {	
			return htxCodeList;
		} else if (tagName.equals("ULDID")) {
			return uldIdList;
		} else if (tagName.equals("UNID")) {
			return unIdList;
		} else {
			return null;	
		}
	}
	*/
	public List<String> getDescList() {		
			return descList;
	}
	public List<String> getHtxList() {		
		return htxCodeList;
}
	public List<String> getUldList() {		
		return uldIdList;
}
	public List<String> getUnList() {		
		return unIdList;
}
	/*
	public void setList(List<String> list) {
		if (tagName.equals("DESC")) {
			this.descList = list;
		} else if (tagName.equals("HTX-CODE")) {	
			this.htxCodeList = list;
		} else if (tagName.equals("ULDID")) {
			this.uldIdList = list;
		} else if (tagName.equals("UNID")) {
			this.unIdList = list;
		} 
	}
	*/	
	public void setDescList(List<String> list) {		
		this.descList = list;
	}
	public void setHtxList(List<String> list) {		
		this.htxCodeList = list;
	}
	public void setUldList(List<String> list) {		
		this.uldIdList = list;
	}
	public void setUnList(List<String> list) {		
		this.unIdList = list;
	}

}
