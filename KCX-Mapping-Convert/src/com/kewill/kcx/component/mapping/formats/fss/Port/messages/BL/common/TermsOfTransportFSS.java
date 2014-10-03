package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPlace;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTermsOfTransport;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TermsOfTransportFSS {
	
	private TsTermsOfTransport tsTermsOfTransport;		     	
	private TsPlace tsPlace;
	
	public TermsOfTransportFSS() throws FssException {
		super(); 				
	}
	
	public TsTermsOfTransport getTermsOfTransport() {
		return tsTermsOfTransport;
	}
	public void setTermsOfTransport(TsTermsOfTransport ts) {
		this.tsTermsOfTransport = ts;
	}
    
    public void setPlace(TsPlace ts) {	    	
    	tsPlace = ts;
    }	    
    public TsPlace  getPlace() {
        return tsPlace;
    }
   
}
