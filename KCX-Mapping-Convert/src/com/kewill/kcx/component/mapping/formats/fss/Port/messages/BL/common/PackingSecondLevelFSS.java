package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAllocatedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsGoodsItemDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMarksAndNumber;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPackingLevel;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PackingSecondLevelFSS {
	
	//private TsCount tsGoodsItem;	
	private PackingDetailsFSS packingDetailsFSS;	
	private List<PackingDetailsFSS> thirdPackingLevelList;	
	
	public PackingSecondLevelFSS() throws FssException {
		super(); 				
	}
	
	public PackingDetailsFSS getPackingDetailsFSS() {
		return packingDetailsFSS;
	}
	public void setPackingDetailsFSS(PackingDetailsFSS packingDetails) {
		this.packingDetailsFSS = packingDetails;
	}
	
    public void setThirdPackingLevelList(List<PackingDetailsFSS> list) {	    	
    	thirdPackingLevelList = list;
    }	    
    public List<PackingDetailsFSS>  getThirdPackingLevelList() {
        return thirdPackingLevelList;
    }
    public void addThirdPackingLevelList(PackingDetailsFSS packingDetails) {	
    	if (thirdPackingLevelList == null) {
    		thirdPackingLevelList = new ArrayList<PackingDetailsFSS>();	
    	}
    	thirdPackingLevelList.add(packingDetails);
    }
    
   
}
