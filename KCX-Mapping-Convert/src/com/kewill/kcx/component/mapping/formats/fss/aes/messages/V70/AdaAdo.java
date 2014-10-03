package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADB;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADD;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: V70 Complex class ADO in MsgADA (Dokomente).
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class AdaAdo {

	private TsADO   adoSubset;  
	private TsADR   adrSubset1;	
	private TsADR   adrSubset2;	
	private List<TsADB> adbList;   //Bemerkungen
	private List<TsADD> addList;   //Dokomentenposition	 
		
	
	public AdaAdo() {
		
		adoSubset = new TsADO();
		adrSubset1 = new TsADR("?");
		adrSubset2 = new TsADR("?");
		adbList = new Vector <TsADB>();		
		addList = new Vector <TsADD>();		
	}	
	

	public TsADO getAdoSubset() {
		return adoSubset;
	}
	public void setAdoSubset(TsADO argument) {
		this.adoSubset = argument;
	}
	
	public TsADR getAdrSubset1() {
		return adrSubset1;
	}
	public void setAdrSubset1(TsADR argument) {
		this.adrSubset1 = argument;
	}
	
	public TsADR getAdrSubset2() {
		return adrSubset2;
	}
	public void setAdrSubset2(TsADR argument) {
		this.adrSubset2 = argument;
	}
	
	public List<TsADB> getAdbList() {
		return adbList;
	}
	public void setAdbList(List<TsADB> argument) {
		this.adbList = argument;
	}
	public void addAdbList(TsADB pos) {
		if (adbList == null) {
			adbList = new Vector <TsADB>();	
		}
		this.adbList.add(pos);
	}
	
	public List<TsADD> getAddList() {
		return addList;
	}
	public void setAddList(List<TsADD> argument) {
		this.addList = argument;
	}
	public void addAdbList(TsADD pos) {
		if (addList == null) {
			addList = new Vector <TsADD>();	
		}
		this.addList.add(pos);
	}
	
	public StringBuffer writeAdaAdo() throws FssException {
		StringBuffer res = new StringBuffer();
		
		if (!adoSubset.isEmpty()) {
			res = appendString(res, adoSubset.teilsatzBilden());
		}
		if (!adrSubset1.isEmpty()) {
			res = appendString(res, adrSubset1.teilsatzBilden());
		}
		if (!adrSubset2.isEmpty()) {
			res = appendString(res, adrSubset2.teilsatzBilden());
		}
		if (adbList != null) {
			for (TsADB adbSubset : adbList) {				
			if (!adbSubset.isEmpty()) {
				res = appendString(res, adbSubset.teilsatzBilden());	
			}
			}
		}
		if (addList != null) {
			for (TsADD addSubset : addList) {				
			if (!addSubset.isEmpty()) {
				res = appendString(res, addSubset.teilsatzBilden());	
			}
			}
		}
		return res;
	}
	
	private StringBuffer appendString(StringBuffer in, String appendStr) throws FssException {
		StringBuffer stb = new StringBuffer();
		
		stb.append(in);
		stb.append(appendStr);
		stb.append(Utils.LF);
		return stb;
	}
	
}


