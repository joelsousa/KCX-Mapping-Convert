package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V70.common;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGI;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGX;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsMRN;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsTXT;

/**
 * Module		: Port<br>
 * Date Created	: 21.10.2011<br>
 * Description	: PositionsEbene.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class PorPos extends FssMessage {
		
	private TsACO   acoSubset;   
	private List <TsMRN>   mrnSubsetList;
	private AEData    aeData;   
	private List <TsTXT>   txtSubsetList;   
	private TsAGR   agrSubset;   
	private TsAGI   agiSubset;   
	private TsAGT   agtSubset;   
	private TsAGX   agxSubset;   
	
	public PorPos() {
		super();  
	}
	
	public void setACOSubset(TsACO argument) {
		acoSubset = argument;
    }
	public TsACO getACOSubset() {
		return acoSubset;
	}

	public void setAEData(AEData argument) {
		aeData = argument;
    }
	public AEData getAEData() {
		return aeData;
	}
	
	public void setAGRSubset(TsAGR argument) { 
		agrSubset = argument;
    }
	public TsAGR getAGRSubset() {
		return agrSubset;
	}
	
	public void setAGISubset(TsAGI argument) { 
		agiSubset = argument;
    }
	public TsAGI getAGISubset() {
		return agiSubset;
	}
	public void setAGTSubset(TsAGT argument) { 
		agtSubset = argument;
    }
	public TsAGT getAGTSubset() {
		return agtSubset;
	}
	public void setAGXSubset(TsAGX argument) { 
		agxSubset = argument;
    }
	public TsAGX getAGXSubset() {
		return agxSubset;
	}
	
	public void setMRNSubsetList(List <TsMRN> list) {
		mrnSubsetList = list;
    }
	public List <TsMRN> getMRNSubsetList() {
		return mrnSubsetList;
	}
	public void addMRNSubsetList(TsMRN subset) {
		if (mrnSubsetList == null) {
			mrnSubsetList = new Vector<TsMRN>();	
		}
		mrnSubsetList.add(subset);
    }
	
	public void setTXTSubsetList(List <TsTXT> list) {
		txtSubsetList = list;
    }
	public List <TsTXT> getTXTSubsetList() {
		return txtSubsetList;
	}
	public void addTXTSubsetList(TsTXT subset) {
		if (txtSubsetList == null) {
			txtSubsetList = new Vector<TsTXT>();	
		}
		txtSubsetList.add(subset);
    } 
	
}

