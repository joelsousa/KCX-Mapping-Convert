package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: CUSREC (OK/Error) - ProcessingResults (kids) - SCK (fss) Verarbeitungsergebnisse.
* @author iwaniuk
* @version 1.0.00
*/

public class Cusrec extends KCXMessage {
	
    private HeaderDetail headerDetail;
    private HeaderNotification headerNotification;
    private ArrayList<ItemDetails> itemDetailsList;
       
    private enum ECusrec {
    	HeaderDetail,
    	HeaderNotification,
    	ItemDetails;
    }

    public Cusrec() {
	      	super();	       
    }
    
    public Cusrec(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECusrec) tag) {
    			
    			case HeaderDetail:
    				headerDetail = new HeaderDetail(getScanner());
    				headerDetail.parse(tag.name());
    				break;	
    				
    			case HeaderNotification:
    				headerNotification = new HeaderNotification(getScanner());
    				headerNotification.parse(tag.name());
    				break;
    				
    			case ItemDetails:
    				ItemDetails item = new ItemDetails(getScanner());
    				item.parse(tag.name());
    				addItemDetailsList(item);
    				break;
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((ECusrec) tag) {
    				
    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return ECusrec.valueOf(token);
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
	
	public HeaderNotification getHeaderNotification() {
		return headerNotification;
	}
	public void setHeaderNotification(HeaderNotification header) {
		this.headerNotification = header;
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
		return headerDetail == null && headerNotification == null && itemDetailsList == null;
	}
}
