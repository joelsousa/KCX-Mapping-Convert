package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCurrencyDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsOneElement;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CurrencyFSS {
	private TsOneElement tsCurrency;	
	private List<TsCurrencyDetails> currencyDetailsList;	  		//max 10 different Places
	
	public CurrencyFSS() throws FssException {
		super(); 
	}
	
	public void setCurrency(TsOneElement ts) {	    	
		tsCurrency = ts;
    }	    
    public TsOneElement  getCurrency() {
        return tsCurrency;
    }
        
	public void setCurrencyDetailsList(List<TsCurrencyDetails> list) {	    	
		currencyDetailsList = list;
    }	    
    public List<TsCurrencyDetails>  getCurrencyDetailsList() {
        return currencyDetailsList;
    }
    public void addCurrencyDetailsList(TsCurrencyDetails ts) {	
    	if (currencyDetailsList == null) {
    		currencyDetailsList = new ArrayList<TsCurrencyDetails>();	
    	}
    	currencyDetailsList.add(ts);
    }
}
