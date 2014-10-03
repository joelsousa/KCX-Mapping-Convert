package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.common;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACT;

/**
 * Module		: Port<br>
 * Created		: 21.10.2011<br>
 * Description	: MsgPOR - ContainerAngaben.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ContainerData extends FssMessage {
		
	private TsACR   acrSubset;   	
	private TsACT   actSubset;   
		
	public ContainerData() {
		super();  
	}	
	
	public void setACRSubset(TsACR argument) {
		acrSubset = argument;
    }
	public TsACR getACRSubset() {
		return acrSubset;
	}

	public void setACTSubset(TsACT argument) {
		actSubset = argument;
    }
	public TsACT getACTSubset() {
		return actSubset;
	}	
 
	 public boolean isEmpty() {
		return  acrSubset == null || acrSubset.isEmpty();			    
	}	
}

