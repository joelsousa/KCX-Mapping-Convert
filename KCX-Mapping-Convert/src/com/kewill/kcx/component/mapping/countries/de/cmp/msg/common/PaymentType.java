package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: PaymentType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class PaymentType extends KCXMessage {
	
    private String transportPaymentMethodCode;
    private String serviceTypeCode;
       
    private enum EPayment {
    	TransportPaymentMethodCode,
    	ServiceTypeCode;
    }

    public PaymentType() {
	      	super();	       
    }
    
    public PaymentType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EPayment) tag) {
    			default:
    					return;
    			}
    		} else {

    			switch ((EPayment) tag) {
    				case TransportPaymentMethodCode:
    					setTransportPaymentMethodCode(value);
    					break;
    					
    				case ServiceTypeCode:
    					setServiceTypeCode(value);
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
    			return EPayment.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }


	public String getTransportPaymentMethodCode() {
		return transportPaymentMethodCode;
	}
	public void setTransportPaymentMethodCode(String value) {
		this.transportPaymentMethodCode = value;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String value) {
		this.serviceTypeCode = value;
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(transportPaymentMethodCode) && Utils.isStringEmpty(serviceTypeCode));
	}


}
