package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAAB;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsABU;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: Abgabenblock.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class FssAbgaben extends FssMessage {
		
	private TsABU        abuSubset;     // Abgabenzeile  		
	private List <TsAAB> aabList;       // Abgabensatz  
	
	public FssAbgaben() {
		super();  
	}
	
	public TsABU getABUSubset() {
		return abuSubset;
	}

	public void setABUSubset(TsABU subset) {
		abuSubset = subset;
	}
    
	public void setAABList(List <TsAAB> list) {
		aabList = list;
    }

	public List <TsAAB> getAABList() {
		return aabList;
	}

	public void addAABList(TsAAB argument) {
		if (aabList == null) {
			aabList = new Vector<TsAAB>();
		}
		aabList.add(argument);
    }	

}

