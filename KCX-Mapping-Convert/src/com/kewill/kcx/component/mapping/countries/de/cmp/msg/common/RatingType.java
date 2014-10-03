package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: RatingType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class RatingType extends KCXMessage {
	
	private String typeCode;
    private String totalChargeAmount;    
    private String consignmentItemQuantity;
    private ArrayList<ConsignmentItemTypeFWB> includedMasterConsignmentItemList;
       
       
    private enum ERatingType {
    	TypeCode,
    	TotalChargeAmount,
    	ConsignmentItemQuantity,
    	IncludedMasterConsignmentItem;
    }

    public RatingType() {
	      	super();	       
    }
    
    public RatingType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ERatingType) tag) {
    			case IncludedMasterConsignmentItem:
    				ConsignmentItemTypeFWB item = new ConsignmentItemTypeFWB(getScanner());
    				item.parse(tag.name());
    				addIncludedMasterConsignmentItemList(item);					
					break;
					
    			default:
    					return;
    			}
    		} else {

    			switch ((ERatingType) tag) {
    				case TypeCode: 
    					setTypeCode(value);
    					break;
    				
    				case TotalChargeAmount: 
    					setTotalChargeAmount(value);
    					break;
    				
    				case ConsignmentItemQuantity: 
    					setConsignmentItemQuantity(value);
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
    			return ERatingType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

    public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String value) {
		this.typeCode = value;
	}

	public String getTotalChargeAmount() {
		return totalChargeAmount;
	}
	public void setTotalChargeAmount(String totalChargeAmount) {
		this.totalChargeAmount = totalChargeAmount;
	}

	public String getConsignmentItemQuantity() {
		return consignmentItemQuantity;
	}
	public void setConsignmentItemQuantity(String consignmentItemQuantity) {
		this.consignmentItemQuantity = consignmentItemQuantity;
	}
	
	public ArrayList<ConsignmentItemTypeFWB> getIncludedMasterConsignmentItemList() {
		return includedMasterConsignmentItemList;
	}
	public void setIncludedMasterConsignmentItemList(ArrayList<ConsignmentItemTypeFWB> argument) {
		this.includedMasterConsignmentItemList = argument;
	}
	public void addIncludedMasterConsignmentItemList(ConsignmentItemTypeFWB argument) {
		if (includedMasterConsignmentItemList == null) {
			includedMasterConsignmentItemList = new ArrayList<ConsignmentItemTypeFWB>();
		}
		includedMasterConsignmentItemList.add(argument);
	}
	public boolean isEmpty() {		
		return (Utils.isStringEmpty(typeCode) && 
				Utils.isStringEmpty(totalChargeAmount) &&
				Utils.isStringEmpty(consignmentItemQuantity) &&
				includedMasterConsignmentItemList == null);
	}
}
