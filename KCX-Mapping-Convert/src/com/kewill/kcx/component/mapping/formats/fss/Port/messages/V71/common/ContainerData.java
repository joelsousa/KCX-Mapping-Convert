package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V71.common;

import java.util.ArrayList;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V70.TsACR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71.TsCPZ;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71.TsCTB;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71.TsLCL;

/**
 * Module		: Port<br>
 * Created		: 12.12.2013<br>
 * Description	: MsgPOR - ContainerAngaben.
 * 				: V71 new for BHT: TsCTB, TsCPZ, TsLCL
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class ContainerData extends FssMessage {
		
	private TsACR   acrSubset; 
	private TsCTB   ctbSubset;  		//EI20131212
	private TsACT   actSubset;  
	private TsCPZ   cpzSubset;   		//EI20131212
	private ArrayList<TsLCL> lclList;  	//EI20131212
		
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
 
	public void setCTBSubset(TsCTB argument) {
		ctbSubset = argument;
    }
	public TsCTB getCTBSubset() {
		return ctbSubset;
	}
	
	public void setCPZSubset(TsCPZ argument) {
		cpzSubset = argument;
    }
	public TsCPZ getCPZSubset() {
		return cpzSubset;
	}
	
	public void setLclList(ArrayList<TsLCL> argument) {
		lclList = argument;
    }
	public ArrayList<TsLCL> getLclList() {
		return lclList;
	}
	public void addLclList(TsLCL subset) {
		if (lclList == null) {
			lclList = new ArrayList<TsLCL>();
		}
		lclList.add(subset);
	}
	
	 public boolean isEmpty() {
		return  acrSubset == null || acrSubset.isEmpty();			    
	}	
}

