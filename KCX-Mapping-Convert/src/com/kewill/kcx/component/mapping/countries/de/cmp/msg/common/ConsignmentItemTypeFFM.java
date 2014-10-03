package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: FFM-ConsignmentItemType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ConsignmentItemTypeFFM extends KCXMessage {
	
    private ArrayList<String> typeCodeList;
       
    private enum EFfmConsignmentItemType {
    	TypeCode;
    }

    public ConsignmentItemTypeFFM() {
	      	super();	       
    }
    
    public ConsignmentItemTypeFFM(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EFfmConsignmentItemType) tag) {
    			default:
    					return;
    			}
    		} else {

    			switch ((EFfmConsignmentItemType) tag) {
    				case TypeCode :
    					addTypeCodeList(value);
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
    			return EFfmConsignmentItemType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }
	
	public ArrayList<String> getTypeCode() {
		return typeCodeList;
	}
	public void setTypeCode(ArrayList<String> list) {
		this.typeCodeList = list;
	}
	public void addTypeCodeList(String typeCode) {
		if (typeCodeList == null) {
			typeCodeList = new 	ArrayList<String>();
		}
		this.typeCodeList.add(typeCode);
	}
	
	public boolean isEmpty() {
		return typeCodeList == null;
	}

}
