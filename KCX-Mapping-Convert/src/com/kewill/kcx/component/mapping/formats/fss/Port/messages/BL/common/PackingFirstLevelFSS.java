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

public class PackingFirstLevelFSS {
	
	//private TsCount tsGoodsItem;	
	private PackingDetailsFSS PackingDetailsFSS;		
	private List<TsAllocatedEquipment> allocatedEquipmentList;			
	private List<DangerousGoodsFSS> dangerousGoodsList;

	public PackingFirstLevelFSS() throws FssException {
		super(); 				
	}
	
	public PackingDetailsFSS getPackingDetailsFSS() {
		return PackingDetailsFSS;
	}
	public void setPackingDetailsFSS(PackingDetailsFSS tsFirstPackingLevel) {
		this.PackingDetailsFSS = tsFirstPackingLevel;
	}
	
	public void setAllocatedEquipmentList(List<TsAllocatedEquipment> list) {	    	
		allocatedEquipmentList = list;
    }	    
    public List<TsAllocatedEquipment>  getAllocatedEquipmentList() {
        return allocatedEquipmentList;
    }
    public void addAllocatedEquipmentList(TsAllocatedEquipment ts) {	
    	if (allocatedEquipmentList == null) {
    		allocatedEquipmentList = new ArrayList<TsAllocatedEquipment>();	
    	}
    	allocatedEquipmentList.add(ts);
    }
	
    public void setDangerousGoodsList(List<DangerousGoodsFSS> list) {	    	
    	dangerousGoodsList = list;
    }	    
    public List<DangerousGoodsFSS>  getDangerousGoodsListList() {
        return dangerousGoodsList;
    }
    public void addDangerousGoodsList(DangerousGoodsFSS ts) {	
    	if (dangerousGoodsList == null) {
    		dangerousGoodsList = new ArrayList<DangerousGoodsFSS>();	
    	}
    	dangerousGoodsList.add(ts);
    }
    
   
}
