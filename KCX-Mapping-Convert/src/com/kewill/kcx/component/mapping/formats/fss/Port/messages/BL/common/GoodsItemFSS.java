package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GoodsItemFSS {
	
	//private TsCount tsGoodsItem;	
	private PackingFirstLevelFSS firstPackingLevel;		     	
	private List<PackingSecondLevelFSS> secondPackingLevelList;
	
	public GoodsItemFSS() throws FssException {
		super(); 				
	}
	
	public PackingFirstLevelFSS getFirstPackingLevel() {
		return firstPackingLevel;
	}
	public void setFirstPackingLevel(PackingFirstLevelFSS tsFirstPackingLevel) {
		this.firstPackingLevel = tsFirstPackingLevel;
	}
    
    public void setSecondPackingLevelList(List<PackingSecondLevelFSS> list) {	    	
    	secondPackingLevelList = list;
    }	    
    public List<PackingSecondLevelFSS>  getSecondPackingLevelListList() {
        return secondPackingLevelList;
    }
    public void addSecondPackingLevelList(PackingSecondLevelFSS ts) {	
    	if (secondPackingLevelList == null) {
    		secondPackingLevelList = new ArrayList<PackingSecondLevelFSS>();	
    	}
    	secondPackingLevelList.add(ts);
    }
    
	
}
