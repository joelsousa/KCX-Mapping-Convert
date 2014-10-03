package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: RatingTotalType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class RatingTotalType extends KCXMessage {
	
	private String typeCode;
    private AppliedAmount applicableDestinationCurrencyServiceCharge;       
    private AmountTotal applicablePrepaidCollectMonetarySummation;
       
       
    private enum ERatingTotalType {
    	TypeCode,
    	ApplicableDestinationCurrencyServiceCharge,    	
    	ApplicablePrepaidCollectMonetarySummation;
    }

    public RatingTotalType() {
	      	super();	       
    }
    
    public RatingTotalType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ERatingTotalType) tag) {    			
    			case ApplicableDestinationCurrencyServiceCharge:
    				applicableDestinationCurrencyServiceCharge = new AppliedAmount(getScanner());
    				applicableDestinationCurrencyServiceCharge.parse(tag.name());					
					break;
					
    			case ApplicablePrepaidCollectMonetarySummation:
    				applicablePrepaidCollectMonetarySummation = new AmountTotal(getScanner());
    				applicablePrepaidCollectMonetarySummation.parse(tag.name());					
					break;
						
    			default:
    					return;
    			}
    		} else {

    			switch ((ERatingTotalType) tag) {
    				case TypeCode: 
    					setTypeCode(value);
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
    			return ERatingTotalType.valueOf(token);
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

	public AppliedAmount getApplicableDestinationCurrencyServiceCharge() {
		return applicableDestinationCurrencyServiceCharge;
	}

	public void setApplicableDestinationCurrencyServiceCharge(
			AppliedAmount applicableDestinationCurrencyServiceCharge) {
		this.applicableDestinationCurrencyServiceCharge = applicableDestinationCurrencyServiceCharge;
	}

	public AmountTotal getApplicablePrepaidCollectMonetarySummation() {
		return applicablePrepaidCollectMonetarySummation;
	}

	public void setApplicablePrepaidCollectMonetarySummation(
			AmountTotal applicablePrepaidCollectMonetarySummation) {
		this.applicablePrepaidCollectMonetarySummation = applicablePrepaidCollectMonetarySummation;
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(typeCode) && 
				applicableDestinationCurrencyServiceCharge == null &&
				applicablePrepaidCollectMonetarySummation == null);
	}
}
