package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Modul		: Detail<br>
* Erstellt		: 02.12.2010<br>
* Beschreibung	: Conversion of Unisys to KIDS.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Detail extends KCXMessage {

	private HashMap<String, String> enumMap = null;   
	
	private String 				custItem; 
	private CustAwbInfo			custAwbInfo;
	private CustGoodsInfo		custGoodsInfo;
	private CustRespInfo		custRespInfo;		

	private void initEnumMap() {
	    enumMap = new HashMap<String, String>();
	    enumMap.put("CUST-ITEM", "CustItem"); 
	    enumMap.put("CUST-AWB-INFO", "CustAwbInfo"); 
	    enumMap.put("CUST-GOODS-INFO", "CustGoodsInfo");
	    enumMap.put("CUST-RESP-INFO", "CustRespInfo");
	}	
	private enum EDetail {
		CustItem,                    //GoodsItem
		CustAwbInfo,
		CustGoodsInfo,
		CustRespInfo;				
	}
	
	public Detail() {
      	super();
      	initEnumMap(); 
	}
	
	public Detail(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EDetail) tag) {
			case CustAwbInfo:	
				custAwbInfo = new CustAwbInfo(getScanner());
				//custAwbInfo.parse(tag.name());
				custAwbInfo.parse("CUST-AWB-INFO");	
				break;
			case CustGoodsInfo:	
				custGoodsInfo = new CustGoodsInfo(getScanner());
				//custGoodsInfo.parse(tag.name());
				custGoodsInfo.parse("CUST-GOODS-INFO");
				break;
			case CustRespInfo:
				custRespInfo = new CustRespInfo(getScanner());  	
				//custRespInfo.parse(tag.name());
				custRespInfo.parse("CUST-RESP-INFO");
				break;
			default:
				return;
		}
	  } else {
		switch ((EDetail) tag) {
			case CustItem:		
				setCustItem(value);
				break;			
			default:
				return;
		} 
	  }
	}
	
	
	@Override
	public void stoppElement(Enum tag) {		
	}

	public Enum translate(String token) {
		String text = enumMap.get(token);  
		if (text != null) {
			token = text;
		}
		try {
			return EDetail.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCustItem() {
		return custItem;	
	}
	public void setCustItem(String argument) {
		this.custItem = Utils.checkNull(argument);
	}

	public CustAwbInfo getCustAwbInfo() {
		return custAwbInfo;	
	}
	public void setCustAwbInfo(CustAwbInfo argument) {
		this.custAwbInfo = argument;
	}

	public CustGoodsInfo getCustGoodsInfo() {
		return custGoodsInfo;	
	}
	public void setCustGoodsInfo(CustGoodsInfo argument) {
		this.custGoodsInfo = argument;
	}
	
	public CustRespInfo getCustRespInfo() {
		return custRespInfo;	
	}
	
	public void setCustRptInfo(CustRespInfo argument) {
		this.custRespInfo = argument;
	}
}
