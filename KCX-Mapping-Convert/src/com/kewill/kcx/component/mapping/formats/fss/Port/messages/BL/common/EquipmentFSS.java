package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;


import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAttachedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsEquipmentDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsSeals;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class EquipmentFSS {
		
	private TsEquipmentDetails tsEquipmenDetails;	
	private List<TsSeals> sealsList;	
	private List<TsAttachedEquipment> attachedEquipmentList;	
	
	public EquipmentFSS() throws FssException {
		super(); 
	}

	public TsEquipmentDetails getEquipmentDetails() {
		return tsEquipmenDetails;
	}
	public void setEquipmentDetails(TsEquipmentDetails ts) {
		this.tsEquipmenDetails = ts;
	}

	 public void setSealsList(List<TsSeals> list) {	    	
		 sealsList = list;
	 }	    
	 public List<TsSeals>  getSealsList() {
	     return sealsList;
	 }
	 public void addSealsList(TsSeals ts) {	
		if (sealsList == null) {
			sealsList = new ArrayList<TsSeals>();	
	    }
		sealsList.add(ts);
	 }
	    
	 public void setAttachedEquipmentList(List<TsAttachedEquipment> list) {	    	
		 attachedEquipmentList = list;
	 }	    
	 public List<TsAttachedEquipment>  getAttachedEquipmentList() {
	    return attachedEquipmentList;
	 }
	 public void addAttachedEquipmentList(TsAttachedEquipment ts) {	
	    if (attachedEquipmentList == null) {
	    	attachedEquipmentList = new ArrayList<TsAttachedEquipment>();	
	    }
	    attachedEquipmentList.add(ts);
	 }
	
}
