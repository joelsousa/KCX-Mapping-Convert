package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: AppliedAmount.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class AppliedAmount extends KCXMessage {

	private String collectAppliedAmount;   
    private String destinationAppliedAmount;
    private String totalAppliedAmount;
       
    public AppliedAmount() {
	      	super();	       
    }
    
    public AppliedAmount(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EAppliedAmount {
    	CollectAppliedAmount, 
    	DestinationAppliedAmount,
    	TotalAppliedAmount,    	
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EAppliedAmount) tag) {    			
    			default:
    					return;
    			}
    		} else {
    			switch ((EAppliedAmount) tag) {
    				case CollectAppliedAmount:
						setCollectAppliedAmount(value);    					
						break;
					
    				case DestinationAppliedAmount:   
    					setDestinationAppliedAmount(value);    					
    					break; 
    					
    				case TotalAppliedAmount:
    					setTotalAppliedAmount(value);    					
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
    			return EAppliedAmount.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		public String getCollectAppliedAmount() {
			return collectAppliedAmount;
		}
		public void setCollectAppliedAmount(String value) {
			this.collectAppliedAmount = value;
		}
		
		public String getDestinationAppliedAmount() {
			return destinationAppliedAmount;
		}
		public void setDestinationAppliedAmount(String value) {
			this.destinationAppliedAmount = value;
		}

		public String getTotalAppliedAmount() {
			return totalAppliedAmount;
		}
		public void setTotalAppliedAmount(String value) {
			this.totalAppliedAmount = value;
		}		

		public boolean isEmpty() {
			return Utils.isStringEmpty(collectAppliedAmount) && Utils.isStringEmpty(destinationAppliedAmount) &&
			Utils.isStringEmpty(totalAppliedAmount);
		}
}
