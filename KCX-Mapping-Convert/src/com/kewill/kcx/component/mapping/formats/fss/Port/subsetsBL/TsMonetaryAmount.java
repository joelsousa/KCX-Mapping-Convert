package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       PORT - BL.
 * Created               :       10.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsMonetaryAmount extends Teilsatz {

	private String count 			= "";
	private String ediQualifier 	= "";
    private String value		 	= "";	 //  Geldbetrag
    private String currencyCode		= "";	 // Währungscode 
    private String status		 	= "";	 

    public TsMonetaryAmount() {
	    tsTyp = "MONETARYAMOUNT";	    
    }

    public void setFields(String[] fields) {
    	int size = fields.length;


    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    count = fields[1];
	    if (size < 3) { return; }
	    ediQualifier = fields[2];
	    if (size < 4) { return; }
	    value = fields[3];
	    if (size < 5) { return; }
	    currencyCode = fields[4];
	    if (size < 6) { return; }
	    status = fields[5];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);
    	buff.append(ediQualifier);
    	buff.append(trenner);
    	buff.append(value);
    	buff.append(trenner);
    	buff.append(currencyCode);
    	buff.append(trenner);
    	buff.append(status);
    	//buff.append(trenner);

    	return new String(buff);    
    }
    
    public String getValue() {
    	 return value;
    }
    public void setValue(String value) {
    	this.value = Utils.checkNull(value);
    }

    public String getCount() {
   	 	return count;
    }
    public void setCount(String value) {
    	this.count = Utils.checkNull(value);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "";
   		}
    }
   
    public String getCurrencyCode() {
    	 return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
    	this.currencyCode = Utils.checkNull(currencyCode);
    }

    public String getStatus() {
    	 return status;
    }
    public void setStatus(String status) {
    	this.status = Utils.checkNull(status);
    }

    public String getEdiQualifier() {
		return ediQualifier;
	}
	public void setEdiQualifier(String value) {
		this.ediQualifier = Utils.checkNull(value);
	}
	
    public boolean isEmpty() {
    	return    	Utils.isStringEmpty(value) &&
    		  Utils.isStringEmpty(currencyCode) &&
    		  Utils.isStringEmpty(status);
    }
}
