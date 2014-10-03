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

public class CustGoodsInfo extends KCXMessage {
	
	 private HashMap<String, String> enumMap = null;   
	
	 private List <GdsDetail> gdsDetailList;
			
	 private enum ECustGoodsInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		GdsDetail;				   
	 }

	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("GDS-DETAIL", "GdsDetail"); 		   
	 }	
	 
	 public CustGoodsInfo() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public CustGoodsInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustGoodsInfo) tag) {
			case GdsDetail:
				GdsDetail gdsDetail = new GdsDetail(getScanner());  	
				//gdsDetail.parse(tag.name());
				gdsDetail.parse("GDS-DETAIL");
				addGdsDetailList(gdsDetail);				
				break;				
			default:
					return;
			}
		} else {

			switch ((ECustGoodsInfo) tag) {				 										
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
			return ECustGoodsInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public List<GdsDetail> getGdsDetailList() {
		return gdsDetailList;	
	}
	public void setGdsDetailList(List<GdsDetail> list) {
		this.gdsDetailList = list;
	}

	public void addGdsDetailList(GdsDetail argument) {
		if (gdsDetailList == null) {
			gdsDetailList = new Vector<GdsDetail>();
		}
		gdsDetailList.add(argument);
	}
	
}
