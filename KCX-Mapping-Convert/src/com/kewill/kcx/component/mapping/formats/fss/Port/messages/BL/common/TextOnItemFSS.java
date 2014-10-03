package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCount;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTextOnBL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TextOnItemFSS {
	
	private List<TsTextOnBL> 					goodsDescriptionList;      		//98
	private List<TsTextOnBL> 					goodsDescriptionNoPrintList;      		//98
	private List<TsTextOnBL> 					remarksBeforeGoodsDescriptionList;	//98
	private List<TsTextOnBL> 					remarksAfterGoodsDescriptionList;	//98	
	private List<TsTextOnBL> 					packagingInformationList;	//98
	
	public TextOnItemFSS() throws FssException {
		super(); 
	}
		
	 public void setGoodsDescriptionList(List<TsTextOnBL> list) {	    	
		 goodsDescriptionList = list;
	 }	    
	 public List<TsTextOnBL>  getGoodsDescriptionList() {
	        return goodsDescriptionList;
	 }
	 public void addGoodsDescriptionList(TsTextOnBL ts) {	
	    	if (goodsDescriptionList == null) {
	    		goodsDescriptionList = new ArrayList<TsTextOnBL>();	
	    	}
	    	goodsDescriptionList.add(ts);
	 }
	    
	public void setGoodsDescriptionNoPrintList(List<TsTextOnBL> list) {	    	
			 goodsDescriptionNoPrintList = list;
	}	    
	public List<TsTextOnBL>  getGoodsDescriptionNoPrintList() {
		        return goodsDescriptionNoPrintList;
	}
	public void addGoodsDescriptionNoPrintList(TsTextOnBL ts) {	
		    	if (goodsDescriptionNoPrintList == null) {
		    		goodsDescriptionNoPrintList = new ArrayList<TsTextOnBL>();	
		    	}
		    	goodsDescriptionNoPrintList.add(ts); 
	}		    
	    
	    public void setPackagingInformationList(List<TsTextOnBL> list) {	    	
	    	packagingInformationList = list;
	    }	    
	    public List<TsTextOnBL>  getPackagingInformationList() {
	        return packagingInformationList;
	    }
	    public void addPackagingInformationList(TsTextOnBL ts) {	
	    	if (packagingInformationList == null) {
	    		packagingInformationList = new ArrayList<TsTextOnBL>();	
	    	}
	    	packagingInformationList.add(ts);
	    }		
	    
	    public void setRemarksBeforeGoodsDescriptionList(List<TsTextOnBL> list) {	    	
	    	remarksBeforeGoodsDescriptionList = list;
	    }	    
	    public List<TsTextOnBL>  getRemarksBeforeGoodsDescriptionList() {
	        return remarksBeforeGoodsDescriptionList;
	    }
	    public void addRemarksBeforeGoodsDescriptionList(TsTextOnBL ts) {	
	    	if (remarksBeforeGoodsDescriptionList == null) {
	    		remarksBeforeGoodsDescriptionList = new ArrayList<TsTextOnBL>();	
	    	}
	    	remarksBeforeGoodsDescriptionList.add(ts);
	    }		
	    
	   
	    public void setRemarksAfterGoodsDescriptionList(List<TsTextOnBL> list) {	    	
	    	remarksAfterGoodsDescriptionList = list;
	    }	    
	    public List<TsTextOnBL>  getRemarksAfterGoodsDescriptionList() {
	        return remarksAfterGoodsDescriptionList;
	    }
	    public void addRemarksAfterGoodsDescriptionList(TsTextOnBL ts) {	
	    	if (remarksAfterGoodsDescriptionList == null) {
	    		remarksAfterGoodsDescriptionList = new ArrayList<TsTextOnBL>();	
	    	}
	    	remarksAfterGoodsDescriptionList.add(ts);
	    }		
	    
}
