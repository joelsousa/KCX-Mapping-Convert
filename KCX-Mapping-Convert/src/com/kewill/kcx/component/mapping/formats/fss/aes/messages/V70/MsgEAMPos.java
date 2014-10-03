package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEPO;

/**
* Module    	: Export/aes. 
* Created    	: 24.07.2012
* Description   : FSS Message Completion
* 				: ZABIS FSS-Format Version 7.0.
* 
* @author      : iwaniuk
* @version     : 2.1.00
*/

public class MsgEAMPos extends FssMessage60 {
			
	private TsAPO   apoSubset;
	private TsEPO   epoSubset;	
	private TsADR   consignee;       //empfaenger=consignee, endverwender=finaluser
	private TsADR   finaluser;	     //new for V21	
	private List    <TsAZV>azvList;	
	
	public MsgEAMPos() {
		super();	
		apoSubset = new TsAPO();
		epoSubset = new TsEPO();
		consignee = new TsADR();
		finaluser = new TsADR();
		azvList = new Vector<TsAZV>();	
	}
	public TsAPO getApoSubset() {
		return apoSubset;
	}
	public void setApoSubset(TsAPO argument) {
		this.apoSubset = argument;
	}
	
	public TsEPO getEpoSubset() {
		return epoSubset;
	}
	public void setEpoSubset(TsEPO argument) {
		this.epoSubset = argument;
	}
		
	public List<TsAZV> getAzvList() {
		return azvList;
	}
	public void setAzvList(List <PreviousDocument>list, String beznr, String posnr) {
		this.azvList = createAZVList(list, beznr, posnr);
	}
	public void addAzvList(TsAZV argument) {
		this.azvList.add(argument);
	}
	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR argument) {
		this.consignee = argument;
	}
	public void setConsignee(Party party, String beznr, String posnr) {
		if (consignee == null) {
			consignee = new TsADR();	
		}
		consignee.setAdrSubset(party, "2", beznr, posnr);
	}	
	public TsADR getFinalUser() {
		return finaluser;
	}
	public void setFinalUser(TsADR adr) {
		this.finaluser = adr;
	}
	public void setFinalUser(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (finaluser == null) {
			finaluser = new TsADR();
		}
		finaluser.setAdrSubset(party, "6", beznr, posnr);		
	}
}	




