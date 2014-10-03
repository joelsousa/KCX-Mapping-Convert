package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 17.07.2013<br>
* Description	: CustomsProcedure.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CustomsProcedure extends KCXMessage {
	
    private String	goodsStatusCode;
      
    private enum ECustomsProcedure {    	
    	GoodsStatusCode;
    }

    public CustomsProcedure() {
	      	super();	       
    }
    
    public CustomsProcedure(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECustomsProcedure) tag) {
    			default:
    					return;
    			}
    		} else {

    			switch ((ECustomsProcedure) tag) {    				
    				case GoodsStatusCode:
    					setGoodsStatusCode(value);
    					break;

    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return ECustomsProcedure.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

    public String getGoodsStatusCode() {
		return goodsStatusCode;
	}
	public void setGoodsStatusCode(String typeCode) {
		this.goodsStatusCode = Utils.checkNull(typeCode);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(goodsStatusCode);
	}

}
