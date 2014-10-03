package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Goods extends KCXMessage {
		
	private List<GoodsItem> goodsItemList;
	
	private enum ECarriage {			
		GoodsItem;			       		
   }	

	public Goods() {
		super();  
	}

	public Goods(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case GoodsItem:
  					GoodsItem item = new GoodsItem(getScanner());  	
  					item.parse(tag.name());					   			
  					addGoodsItemList(item);
  					break;  				
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   			  				   			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
		
	public List<GoodsItem> getGoodsItemList() {
		return goodsItemList;
	}    
	public void setGoodsItemList(List<GoodsItem> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItem value) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItem>();
		}
		this.goodsItemList.add(value);
	}
	
	public boolean isEmpty() {
		return (goodsItemList == null || goodsItemList.size() == 0); 
	}
}

