package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;



/**
 * Module		: Import<br>
 * Created		: 12.04.2013<br>
 * Description	: GoodsItem.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CtnItems extends KCXMessage {
		
	private List<CtnItem> ctnList;     
		
	public CtnItems() {
		super();				
	}

	public CtnItems(XmlMsgScanner scanner) {
		super(scanner);
	}
		
	private enum ECntItem {
		//KIDS:							KFF:
		CtnItem,                   		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ECntItem) tag) {
			case CtnItem:
				CtnItem ctn = new CtnItem(getScanner());
				ctn.parse(tag.name());	
				addCtnItemList(ctn);
				break;	
			default:
				return;
			}
	    } else {
	    	switch ((ECntItem) tag) {			
			default:
				return;
			}
	    }		
	}

	public void stoppElement(Enum tag) {		
	}

	public Enum translate(String token) {
 		try {
  			return ECntItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	
	public List <CtnItem> getCtnItemList() {
		return ctnList;
	}
	public void setCtnItemList(List <CtnItem> list) {
		this.ctnList = list;
	}	
	public void addCtnItemList(CtnItem item) {
		if (ctnList == null) {
			ctnList = new Vector <CtnItem>();
		}
		ctnList.add(item);
	}
	public boolean isEmpty() {
		return (ctnList == null || ctnList.isEmpty());		      
	}
}

