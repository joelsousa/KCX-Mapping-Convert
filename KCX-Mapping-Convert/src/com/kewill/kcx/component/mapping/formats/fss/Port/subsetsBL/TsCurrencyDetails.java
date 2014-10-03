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

public class TsCurrencyDetails extends Teilsatz {

	private String ediQualifier = "";
	private String currencyCode = "";
    private String currencyBaseRate = "";        
    
    public TsCurrencyDetails() {
	    tsTyp = "CURRENCYDETAIL";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    ediQualifier = fields[1];    		   
	    if (size < 3) { return; }
	    currencyCode = fields[2];
	    if (size < 4) { return; }
	    currencyBaseRate = fields[3];	    	   
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(ediQualifier);
    	buff.append(trenner);    	
    	buff.append(currencyCode);
    	buff.append(trenner);    	
    	buff.append(currencyBaseRate);
    	//buff.append(trenner);    	
    	
    	return new String(buff);
    }

    public String getCurrencyCode() {
   	 	return currencyCode;
    }
    public void setCurrencyCode(String code) {
   		this.currencyCode = Utils.checkNull(code);
    }
  
    public String getEdiQualifier() {
  	 	return ediQualifier;
    }
    public void setEdiQualifier(String edi) {
  		this.ediQualifier = Utils.checkNull(edi);
    }
 
    public String getCurrencyBaseRate() {
 	 	return currencyBaseRate;
    }
    public void setCurrencyBaseRate(String base) {
 		this.currencyBaseRate = Utils.checkNull(base);
    }   

    public boolean isEmpty() {
    	return  Utils.isStringEmpty(currencyCode) && Utils.isStringEmpty(currencyBaseRate) &&
    	Utils.isStringEmpty(ediQualifier);
    }

}