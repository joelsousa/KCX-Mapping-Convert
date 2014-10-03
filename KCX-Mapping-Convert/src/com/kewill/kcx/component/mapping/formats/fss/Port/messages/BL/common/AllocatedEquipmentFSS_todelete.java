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
 * Created		: 04.05.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class AllocatedEquipmentFSS_todelete {
		
		    
	private TsAllocatedEquipment tsAllocatedEquipment;		
	//private List<TsDistributedOverSeveralContainer> distributedOverSeveralContainerList;

	public AllocatedEquipmentFSS_todelete() throws FssException {
		super(); 				
	}
	
	public TsAllocatedEquipment getAllocatedEquipment() {
		return tsAllocatedEquipment;
	}
	public void setAllocatedEquipment(TsAllocatedEquipment ts) {
		this.tsAllocatedEquipment = ts;
	}
/*
	public void setDistributedOverSeveralContainerList(TsDistributedOverSeveralContainer textOnItem) {
		this.distributedOverSeveralContainerList = textOnItem;
	}
	
	public void getDistributedOverSeveralContainerList(List<TsDistributedOverSeveralContainer> list) {	    	
		distributedOverSeveralContainerList = list;
    }	    
  
    public void addDistributedOverSeveralContainerList(TsDistributedOverSeveralContainer ts) {	
    	if (distributedOverSeveralContainerList == null) {
    		distributedOverSeveralContainerList = new ArrayList<TsDistributedOverSeveralContainer>();	
    	}
    	distributedOverSeveralContainerList.add(ts);
    }
   */ 
    
}
