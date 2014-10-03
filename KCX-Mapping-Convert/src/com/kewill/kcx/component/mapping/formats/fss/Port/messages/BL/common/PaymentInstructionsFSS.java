package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMonetaryAmount;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPaymentInstructions;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPlace;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsReference;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PaymentInstructionsFSS {
		
	private TsPaymentInstructions 	tsPaymentInstructions;
	private List<TsReference> 		paymentReferenceList;   //99x	
	private CurrencyFSS			    currencyFSS;   		//2x
	private List<TsPlace>			placeList;   			//9x
	private List<TsMonetaryAmount> 	monetrayAmountList;  	//9x
	
	public PaymentInstructionsFSS() throws FssException {
		super(); 
	}
	
	public void setPaymentInstructions(TsPaymentInstructions ts) {	    	
		tsPaymentInstructions = ts;
    }	    
    public TsPaymentInstructions getPaymentInstructions() {
        return tsPaymentInstructions;
    }
    
    public void setPaymentReferenceList(List<TsReference> list) {	    	
    	paymentReferenceList = list;
    }	    
    public List<TsReference>  getPaymentReferenceList() {
        return paymentReferenceList;
    }
    public void addPaymentReferenceList(TsReference ts) {	
    	if (paymentReferenceList == null) {
    		paymentReferenceList = new ArrayList<TsReference>();	
    	}
    	paymentReferenceList.add(ts);
    }
       
    public void setCurrencyFSS(CurrencyFSS list) {	    	
    	currencyFSS = list;
    }	    
    public CurrencyFSS  getCurrencyFSS() {
        return currencyFSS;
    }
   
    public void setPlaceList(List<TsPlace> list) {	    	
    	placeList = list;
    }	    
    public List<TsPlace>  getPlaceList() {
        return placeList;
    }
    public void addPlaceList(TsPlace ts) {	
    	if (placeList == null) {
    		placeList = new ArrayList<TsPlace>();	
    	}
    	placeList.add(ts);
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
