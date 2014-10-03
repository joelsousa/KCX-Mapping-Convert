package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAddressQualified;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAddressRows;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCommunicationContact;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsOneElement;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsParty;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PartyFSS {	
	
    private TsParty tsParty;	     
	private TsAddressQualified tsAddress;
	private TsAddressRows tsAddressRows;	  	     //1+5x
	//private TsContactIwa tsContact;
	private TsOneElement tsContact;
	private List<TsCommunicationContact> commmunicationContactList;	 //8x
	
	public PartyFSS() throws FssException {
		super(); 
	}
	
	public PartyFSS(String type) throws FssException {
		super(); 
	}
	
	public void setTsParty(TsParty ts) {	    	
		tsParty = ts;
    }	    
    public TsParty getTsPatry() {
        return tsParty;
    }
    
    public void setAddress(TsAddressQualified ts) {	    	
    	tsAddress = ts;
    }	    
    public TsAddressQualified  getAddress() {
        return tsAddress;
    }
    
    public void setContact(TsOneElement ts) {	    	
    	tsContact = ts;
    }	    
    public TsOneElement  getContact() {
        return tsContact;
    }
    
	public void setAddressRows(TsAddressRows ts) {	    	
		tsAddressRows = ts;
    }	    
    public TsAddressRows getAddressRows() {
        return tsAddressRows;
    }    
   
    public void setCommmunicationContactList(List<TsCommunicationContact> list) {	    	
    	commmunicationContactList = list;
    }	    
    public List<TsCommunicationContact> getCommmunicationContactList() {
        return commmunicationContactList;
    }   
    public void addCommmunicationContactList(TsCommunicationContact ref) {	    	
    	if (commmunicationContactList == null) {    	
    		commmunicationContactList = new ArrayList<TsCommunicationContact>();		    	
    	}
    	commmunicationContactList.add(ref);
    }
}
