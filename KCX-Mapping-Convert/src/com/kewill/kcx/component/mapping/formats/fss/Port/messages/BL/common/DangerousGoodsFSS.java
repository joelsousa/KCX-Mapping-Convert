package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCommunicationContact;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsDangerousGoodsDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsFiveElements;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsOneElement;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTenElements;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;


/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DangerousGoodsFSS {
	
	private TsDangerousGoodsDetails tsDetails;
	private TsTenElements tsTechnicalName;
	private TsFiveElements tsAdditional;	
	private List<ContactFSS> contactFSSList;
	
	public DangerousGoodsFSS() throws FssException {
		super(); 
	}
	
	public void setDGDetails(TsDangerousGoodsDetails ts) {	    	
		tsDetails = ts;
    }	    
    public TsDangerousGoodsDetails  getDGDetails() {
        return tsDetails;
    }
    
    public void setTechnicalName(TsTenElements ts) {	    	
    	tsTechnicalName = ts;
    }	    
    public TsTenElements  getTechnicalName() {
        return tsTechnicalName;
    }
    
    public void setDangerousGoodsAdditional(TsFiveElements ts) {	    	
    	tsAdditional = ts;
    }	    
    public TsFiveElements  getDangerousGoodsAdditional() {
        return tsAdditional;
    }
    
   
	public void setContactFSSList(List<ContactFSS> list) {	    	
		contactFSSList = list;
    }	    
    public List<ContactFSS>  getContactFSSList() {
        return contactFSSList;
    }
    public void addContactFSSList(ContactFSS ts) {	
    	if (contactFSSList == null) {
    		contactFSSList = new ArrayList<ContactFSS>();	
    	}
    	contactFSSList.add(ts);
    }
   
}
