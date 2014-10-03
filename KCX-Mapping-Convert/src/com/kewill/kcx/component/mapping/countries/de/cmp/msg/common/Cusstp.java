package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: CUSSTP - ControlDecision (kids) - SST (fss) 
* 			 	: Bekanntgabe einer Massnahme /Customs information about the instructed stop of transport.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Cusstp extends KCXMessage {
	
    private HeaderDetail headerDetail;
    private ArrayList<ItemDetails> itemDetailsList;
       
    private enum ECusstp {
    	HeaderDetail,
    	ItemDetails;
    }

    public Cusstp() {
	      	super();	       
    }
    
    public Cusstp(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECusstp) tag) {
    			
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

    			switch ((ECusstp) tag) {
    				
    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return ECusstp.valueOf(token);
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
