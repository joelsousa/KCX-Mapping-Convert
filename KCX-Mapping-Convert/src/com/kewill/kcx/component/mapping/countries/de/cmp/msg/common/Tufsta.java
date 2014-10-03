package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: TUFSTA - NCTSWriteOffNotification(kids) - VSO (fss NCTS-OUT)
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Tufsta extends KCXMessage {
	
    private HeaderDetail headerDetail;
       
    private enum ETufsta {
    	HeaderDetail;
    }

    public Tufsta() {
	      	super();	       
    }
    
    public Tufsta(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ETufsta) tag) {
    			case HeaderDetail:
    				headerDetail = new HeaderDetail(getScanner());
    				headerDetail.parse(tag.name());
    				break;		
    			default:
    					return;
    			}
    		} else {

    			switch ((ETufsta) tag) {
    				
    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return ETufsta.valueOf(token);
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
	
	public boolean isEmpty() {
		return (headerDetail == null);
	}
}
