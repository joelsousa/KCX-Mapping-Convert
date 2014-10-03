package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSI;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSP;

/**
 * Module		: Manifest<br>
 * Created		: 17.06.2013<br>
 * Description	: MsgSST - CUSSTP - Bekanntgabe einer Maﬂnahme 
 * 				  
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgSSTPos extends FssMessage {
	
	private TsSSP   sspSubset; 	
	private TsSSA   ssaSubset;
	private TsSSI   ssiSubset;
	
	public MsgSSTPos() {
		super();  
		sspSubset = new TsSSP(); 
	}
	
	public TsSSP getSspSubset() {
		return sspSubset;
	}
	public void setSspSubset(TsSSP ts) {
		this.sspSubset = ts;
	}
    	
	public TsSSA getSsaSubset() {
		return ssaSubset;
	}
	public void setSsaSubset(TsSSA ts) {
		this.ssaSubset = ts;
	}

	public TsSSI getSsiSubset() {
		return ssiSubset;
	}
	public void setSsiSubset(TsSSI ts) {
		this.ssiSubset = ts;
	}	

}


