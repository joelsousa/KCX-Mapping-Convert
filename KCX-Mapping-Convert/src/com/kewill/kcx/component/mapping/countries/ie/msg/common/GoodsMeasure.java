package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: Ireland<br>
* Created		: 05.06.2014<br>
* Description	: GoodsMeasure.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class GoodsMeasure extends KCXMessage {
	
	private String grossMassMeasure;
	private String netNetWeightMeasure;
	private String tariffQuantity;
      
    private enum EGoodsMeasure {
    	GrossMassMeasure,    	   	
    	NetNetWeightMeasure,
    	TariffQuantity;
    }

    public GoodsMeasure() {
	      	super();	       
    }
    
    public GoodsMeasure(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EGoodsMeasure) tag) {    			    				    		
    				default:
    					return;
    			}
    		} else {
    			switch ((EGoodsMeasure) tag) {
    				
    				case GrossMassMeasure:
    					setGrossMassMeasure(value);
    					break;    					
    				case NetNetWeightMeasure:
    					setNetNetWeightMeasure(value);
    					break;  
    				case TariffQuantity:
    					setTariffQuantity(value);
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
    			return EGoodsMeasure.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }	
	
	public String getGrossMassMeasure() {
		return grossMassMeasure;
	}
	public void setGrossMassMeasure(String value) {
		this.grossMassMeasure = value;
	}
	
	public String getNetNetWeightMeasure() {
		return netNetWeightMeasure;
	}
	public void setNetNetWeightMeasure(String value) {
		this.netNetWeightMeasure = value;
	}
	
	public String getTariffQuantity() {
		return tariffQuantity;
	}
	public void setTariffQuantity(String value) {
		this.tariffQuantity = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(grossMassMeasure) && Utils.isStringEmpty(netNetWeightMeasure) &&
		Utils.isStringEmpty(tariffQuantity);				
	}

}
