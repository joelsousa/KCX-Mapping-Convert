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

public class TsPaymentInstructions extends Teilsatz {

	private String count = "";
	private String chargeCategoryCode = "";
	private String transportChargeMethodOfPaymentCode = "";
	private String prepaidCollectIndicator = "";
	
    public TsPaymentInstructions() {
	    tsTyp = "PAYMENTINSTRUCTIONS";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;


    	if (size < 1) { return; }
    	tsTyp = fields[0];    	
	    if (size < 2) { return; }
	    count = fields[1];
	    if (size < 3) { return; }
	    chargeCategoryCode = fields[2];    	
	    if (size < 4) { return; }
	    transportChargeMethodOfPaymentCode = fields[3];
	    if (size < 5) { return; }
	    prepaidCollectIndicator = fields[4];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);
    	buff.append(chargeCategoryCode);
    	buff.append(trenner);
    	buff.append(transportChargeMethodOfPaymentCode);
    	buff.append(trenner);
    	buff.append(prepaidCollectIndicator);
    	//buff.append(trenner);
    	
    	return new String(buff);
    }
    
    public String getChargeCategoryCode() {
    	 return chargeCategoryCode;
    }
    public void setChargeCategoryCode(String value) {
    	this.chargeCategoryCode = Utils.checkNull(value);
    }

    public String getTransportChargeMethodOfPaymentCode() {
   	 	return transportChargeMethodOfPaymentCode;
    }
    public void setTransportChargeMethodOfPaymentCode(String value) {
    	this.transportChargeMethodOfPaymentCode = Utils.checkNull(value);
    }
    
    public String getPrepaidCollectIndicator() {
	   return prepaidCollectIndicator;
    }
    public void setPrepaidCollectIndicator(String value) {
	   this.prepaidCollectIndicator = Utils.checkNull(value);
    }
       
    public String getCount() {
   	 	return count;
    }
    public void setCount(String unLocode) {
    	this.count = Utils.checkNull(unLocode);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "";
    	}
    }

    public boolean isEmpty() {
		return Utils.isStringEmpty(chargeCategoryCode) && 
			Utils.isStringEmpty(transportChargeMethodOfPaymentCode) &&
			Utils.isStringEmpty(prepaidCollectIndicator);
		//TODO
    }
}
