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

public class ContactFSS {
		
	private TsOneElement tsContact;
	private List<TsCommunicationContact> tsContactList;
	
	public ContactFSS() throws FssException {
		super(); 
	}
		
    public void setContact(TsOneElement ts) {	    	
    	tsContact = ts;
    }	    
    public TsOneElement  getContact() {
        return tsContact;
    }    
    
	public void setCommunicationContactist(List<TsCommunicationContact> list) {	    	
		tsContactList = list;
    }	    
    public List<TsCommunicationContact>  getCommunicationContactList() {
        return tsContactList;
    }
    public void addCommunicationContactList(TsCommunicationContact ts) {	
    	if (tsContactList == null) {
    		tsContactList = new ArrayList<TsCommunicationContact>();	
    	}
    	tsContactList.add(ts);
    }
   
}
