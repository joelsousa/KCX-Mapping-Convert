package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: CUSCAN (Cancellation) - GoodsReleasedExternal (kids), EVK (fss)  
* 				: Rückgabe Verwahrmitteilung aus NCTS
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Cuscan extends KCXMessage {
	
    private HeaderDetail headerDetail;
    private ArrayList<ItemDetails> itemDetailsList;
       
    private enum ECuscan {
    	HeaderDetail,
    	ItemDetails;
    }

    public Cuscan() {
	      	super();	       
    }
    
    public Cuscan(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECuscan) tag) {
    			case HeaderDetail:
    				headerDetail = new HeaderDetail(getScanner());
    				headerDetail.parse(tag.name());
    				break;	
    				
    			case ItemDetails:
    				ItemDetails itemDetails = new ItemDetails(getScanner());
    				itemDetails.parse(tag.name());
    				addItemDetailsList(itemDetails);
    				break;
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((ECuscan) tag) {
    				
    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return ECuscan.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

	public HeaderDetail getHeaderDetail() {
		return headerDetail;
	}
	public void setHeaderDetail(HeaderDetail detail) {
		this.headerDetail = detail;
	}
	
	public ArrayList<ItemDetails> getItemDetailsList() {
		return itemDetailsList;
	}
	public void setItemDetailsList(ArrayList<ItemDetails> list) {
		this.itemDetailsList = list;
	}
	public void addItemDetailsList(ItemDetails item) {
		if (itemDetailsList == null) {
			itemDetailsList = new ArrayList<ItemDetails>();
		}
		this.itemDetailsList.add(item);
	}
	
	public boolean isEmpty() {
		return headerDetail == null && itemDetailsList == null;
	}
}
