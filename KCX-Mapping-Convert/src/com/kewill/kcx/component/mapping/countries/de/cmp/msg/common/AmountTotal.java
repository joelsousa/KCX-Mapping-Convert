package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 25.07.2013<br>
 * Description	: AmountTotal.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class AmountTotal extends KCXMessage {

	private String prepaidIndicator;
    private String weightChargeTotalAmount;
    private String valuationChargeTotalAmount;
    private String taxTotalAmount;
    private String agentTotalDuePayableAmount;
    private String carrierTotalDuePayableAmount;
    private String grandTotalAmount;
       
    public AmountTotal() {
	      	super();	       
    }
    
    public AmountTotal(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EAmountTotal {
    	PrepaidIndicator, 
    	WeightChargeTotalAmount,
    	ValuationChargeTotalAmount,
    	TaxTotalAmount,
    	AgentTotalDuePayableAmount,
    	CarrierTotalDuePayableAmount,
    	GrandTotalAmount,
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EAmountTotal) tag) {    			
    			default:
    					return;
    			}
    		} else {
    			switch ((EAmountTotal) tag) {
    				case PrepaidIndicator:
						setPrepaidIndicator(value);    					
						break;
					
    				case WeightChargeTotalAmount:   
    					setWeightChargeTotalAmount(value);    					
    					break; 
    					
    				case ValuationChargeTotalAmount:
    					setValuationChargeTotalAmount(value);    					
    					break;
    					
    				case TaxTotalAmount:
    					setTaxTotalAmount(value);    					
    					break;
    					
    				case AgentTotalDuePayableAmount:
    					setAgentTotalDuePayableAmount(value);    					
    					break;
    					
    				case CarrierTotalDuePayableAmount:
    					setCarrierTotalDuePayableAmount(value);    					
    					break;
    					
    				case GrandTotalAmount:
    					setGrandTotalAmount(value);    					
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
    			return EAmountTotal.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		
		public String getPrepaidIndicator() {
			return prepaidIndicator;
		}
		public void setPrepaidIndicator(String value) {
			this.prepaidIndicator = value;
		}

		public String getWeightChargeTotalAmount() {
			return weightChargeTotalAmount;
		}

		public void setWeightChargeTotalAmount(String value) {
			this.weightChargeTotalAmount = value;
		}

		public String getValuationChargeTotalAmount() {
			return valuationChargeTotalAmount;
		}

		public void setValuationChargeTotalAmount(String value) {
			valuationChargeTotalAmount = value;
		}

		public String getTaxTotalAmount() {
			return taxTotalAmount;
		}

		public void setTaxTotalAmount(String value) {
			this.taxTotalAmount = value;
		}

		public String getAgentTotalDuePayableAmount() {
			return agentTotalDuePayableAmount;
		}

		public void setAgentTotalDuePayableAmount(String value) {
			this.agentTotalDuePayableAmount = value;
		}

		public String getCarrierTotalDuePayableAmount() {
			return carrierTotalDuePayableAmount;
		}
		public void setCarrierTotalDuePayableAmount(String value) {
			this.carrierTotalDuePayableAmount = value;
		}
		
		public String getGrandTotalAmount() {
			return grandTotalAmount;
		}
		public void setGrandTotalAmount(String value) {
			this.grandTotalAmount = value;
		}
		
		public boolean isEmpty() {
			return Utils.isStringEmpty(prepaidIndicator) && Utils.isStringEmpty(weightChargeTotalAmount) &&
		 		Utils.isStringEmpty(valuationChargeTotalAmount) && Utils.isStringEmpty(taxTotalAmount) &&
		 		Utils.isStringEmpty(agentTotalDuePayableAmount) && Utils.isStringEmpty(carrierTotalDuePayableAmount) &&
		 		Utils.isStringEmpty(grandTotalAmount);
		}	
		
}
