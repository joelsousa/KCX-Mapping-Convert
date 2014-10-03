package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: ChargeType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class ChargeType extends KCXMessage {

	private String categoryCode;
    private String commodityItemId;
    private String chargeableWeightMeasure;
    private String appliedRate;
    private String appliedAmount;
       
    public ChargeType() {
	      	super();	       
    }
    
    public ChargeType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EChargeType {
    	CategoryCode, 
    	CommodityItemID,
    	ChargeableWeightMeasure,
    	AppliedRate,
    	ReferenceICategoryCode,
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EChargeType) tag) {    			
    			default:
    					return;
    			}
    		} else {
    			switch ((EChargeType) tag) {
    				case CategoryCode:
						setCategoryCode(value);    					
						break;
					
    				case CommodityItemID:   
    					setCommodityItemId(value);    					
    					break; 
    					
    				case ChargeableWeightMeasure:
    					setChargeableWeightMeasure(value);    					
    					break;
    					
    				case AppliedRate:
    					setAppliedRate(value);    					
    					break;
    					
    				case ReferenceICategoryCode:
    					setAppliedRate(value);    					
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
    			return EChargeType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		
		public String getCategoryCode() {
			return categoryCode;
		}
		public void setCategoryCode(String value) {
			this.categoryCode = value;
		}

		public String getCommodityItemId() {
			return commodityItemId;
		}

		public void setCommodityItemId(String value) {
			this.commodityItemId = value;
		}

		public String getChargeableWeightMeasure() {
			return chargeableWeightMeasure;
		}

		public void setChargeableWeightMeasure(String value) {
			chargeableWeightMeasure = value;
		}

		public String getAppliedRate() {
			return appliedRate;
		}

		public void setAppliedRate(String value) {
			this.appliedRate = value;
		}

		public String getAppliedAmount() {
			return appliedAmount;
		}

		public void setAppliedAmount(String value) {
			this.appliedAmount = value;
		}

		public boolean isEmpty() {
			return Utils.isStringEmpty(categoryCode) && Utils.isStringEmpty(commodityItemId) &&
			Utils.isStringEmpty(chargeableWeightMeasure) && Utils.isStringEmpty(appliedRate) &&
			Utils.isStringEmpty(appliedAmount);
		}
}
