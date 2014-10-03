package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V70.common;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAEP;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V70.TsAED;

/**
 * Module		: Port<br>
 * Created		: 21.10.2011<br>
 * Description	: MsgPOR - ContainerAngaben.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class AEData extends FssMessage {
		
	private TsAED   aedSubset;   
	private TsADR   adr1Subset;   
	private TsADR   adr2Subset;  
	private TsADR   adr3Subset;   
	private TsADR   adr4Subset; 
	private TsAEP   aepSubset;   
		
	public AEData() {
		super();  
	}	
	
	public void setAEDSubset(TsAED argument) {
		aedSubset = argument;
    }
	public TsAED getAEDSubset() {
		return aedSubset;
	}

	public void setAEPSubset(TsAEP argument) {
		aepSubset = argument;
    }
	public TsAEP getAEPSubset() {
		return aepSubset;
	}
	
	public void setADR1Subset(TsADR argument) {
		adr1Subset = argument;
    }
	public TsADR getADR1Subset() {
		return adr1Subset;
	}	
 
	public void setADR2Subset(TsADR argument) {
		adr2Subset = argument;
    }
	public TsADR getADR2Subset() {
		return adr2Subset;
	}	
	
	public void setADR3Subset(TsADR argument) {
		adr3Subset = argument;
    }
	public TsADR getADR3Subset() {
		return adr3Subset;
	}	
 
	public void setADR4Subset(TsADR argument) {
		adr4Subset = argument;
    }
	public TsADR getADR4Subset() {
		return adr4Subset;
	}		
	 public boolean isEmpty() {
		return  aedSubset == null || aedSubset.isEmpty();			    
	}	
}

