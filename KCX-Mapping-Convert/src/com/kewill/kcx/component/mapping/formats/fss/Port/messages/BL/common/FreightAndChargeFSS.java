package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsFreightAndCharge;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMonetaryAmount;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPlace;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

	public class FreightAndChargeFSS {
		
	private TsFreightAndCharge		tsFreightAndCharge;	
	private TsPlace					tsPlace;   				
	private CurrencyFSS				currency;   	//2x	
	private List<TsMonetaryAmount> 	monetrayAmountList;  	//9x
	
	public FreightAndChargeFSS() throws FssException {
		super(); 
	}
	
	public void setFreightAndCharge(TsFreightAndCharge ts) {	    	
		tsFreightAndCharge = ts;
    }	    
    public TsFreightAndCharge getFreightAndCharge() {
        return tsFreightAndCharge;
    }     
 
    public void setPlace(TsPlace ts) {	    	
    	tsPlace = ts;
    }	    
    public TsPlace getPlace() {
        return tsPlace;
    }
      
    public void setCurrencyFSS(CurrencyFSS curr) {	    	
    	currency = curr;
    }	    
    public CurrencyFSS  getCurrencyFSS() {
        return currency;
    }
 
    public void setMonetaryAmountList(List<TsMonetaryAmount> list) {	    	
    	monetrayAmountList = list;
    }	    
    public List<TsMonetaryAmount>  getMonetaryAmountList() {
        return monetrayAmountList;
    }
    public void addMonetaryAmountList(TsMonetaryAmount ts) {	
    	if (monetrayAmountList == null) {
    		monetrayAmountList = new ArrayList<TsMonetaryAmount>();	
    	}
    	monetrayAmountList.add(ts);
    }
}
