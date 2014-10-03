package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

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

public class TextOnEntireBLFSS {
	private List<TsTextOnBL> 					blRemarksList;      		//98
	private List<TsTextOnBL> 					packagingInformationList;	//98
	private List<TsTextOnBL> 					documentInstructionsList;	//98
	private TsTextOnBL 						tsGoodsDimensions;		
	private List<TsTextOnBL> 					additionalInformationList;	//98
	private TsTextOnBL						tsCargoRemarks;			
	private TsTextOnBL 						tsCompilanceChecked;		

	public TextOnEntireBLFSS() throws FssException {
		super(); 
	}
	
	 public void setBLRemarksList(List<TsTextOnBL> list) {	    	
	    	blRemarksList = list;
	    }	    
	    public List<TsTextOnBL>  getBLRemarksList() {
	        return blRemarksList;
	    }
	    public void addBLRemarksList(TsTextOnBL ts) {	
	    	if (blRemarksList == null) {
	    		blRemarksList = new ArrayList<TsTextOnBL>();	
	    	}
	    	blRemarksList.add(ts);
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
	    
	    public void setDocumentInstructionsList(List<TsTextOnBL> list) {	    	
	    	documentInstructionsList = list;
	    }	    
	    public List<TsTextOnBL>  getDocumentInstructionsList() {
	        return documentInstructionsList;
	    }
	    public void addDocumentInstructionsList(TsTextOnBL ts) {	
	    	if (documentInstructionsList == null) {
	    		documentInstructionsList = new ArrayList<TsTextOnBL>();	
	    	}
	    	documentInstructionsList.add(ts);
	    }		
	    
	    public void setGoodsDimensions(TsTextOnBL ts) {	    	
	    	tsGoodsDimensions = ts;
	    }	    
	    public TsTextOnBL  getGoodsDimensions() {
	        return tsGoodsDimensions;
	    }
	   
	    public void setAdditionalInformationList(List<TsTextOnBL> list) {	    	
	    	additionalInformationList = list;
	    }	    
	    public List<TsTextOnBL>  getAdditionalInformationList() {
	        return additionalInformationList;
	    }
	    public void addAdditionalInformationList(TsTextOnBL ts) {	
	    	if (additionalInformationList == null) {
	    		additionalInformationList = new ArrayList<TsTextOnBL>();	
	    	}
	    	additionalInformationList.add(ts);
	    }		
	    
	    public void setCargoRemarks(TsTextOnBL ts) {	    	
	    	tsCargoRemarks = ts;
	    }	    
	    public TsTextOnBL  getCargoRemarks() {
	        return tsCargoRemarks;
	    }	   
	    
	    public void setCompilanceChecked(TsTextOnBL ts) {	    	
	    	tsCompilanceChecked = ts;
	    }	    
	    public TsTextOnBL  getCompilanceChecked() {
	        return tsCompilanceChecked;
	    }	   	    
}
