package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;

/**
 * Module		:	Export/aes
 * Created		:	19.07.2013
 * Description	:	MsgPos.
 * 				: 	neu in V72: Positionssatz - Kennzeichen AH-stat Wert, mit 0 senden
 *
 * @author 	iwaniuk
 * @version 2.2.00 (Zabis V72)
 */

public class MsgADAPos  {
	private TsAPO   apoSubset; 		
	private TsEPO   epoSubset; 	
	private TsSAP   sapSubset;  
	private TsADR   consigneeSubset;	
	private TsADR   finaluserSubset;	
	
	private List    <TsACO>acoList;
	private List    <TsACN>acnList;
	private List    <TsAZV>azvList;	
	private List 	<TsBZL>bzlList;
	private List 	<TsBAV>bavList;
	private List 	<TsAED>aedList;
	
	
	public MsgADAPos() {
	
	}

	public TsAPO getApoSubset() {
		return apoSubset;
	}
	public void setApoSubset(TsAPO apoSubset) {
		this.apoSubset = apoSubset;
	}

	public TsEPO getEpoSubset() {
		return epoSubset;
	}
	public void setEpoSubset(TsEPO atpSubset) {
		this.epoSubset = atpSubset;
	}

	public TsSAP getSapSubset() {
		return sapSubset;
	}
	public void setSapSubset(TsSAP sapSubset) {
		this.sapSubset = sapSubset;
	}

	public TsADR getConsigneeSubset() {
		return consigneeSubset;
	}
	public void setConsigneeSubset(TsADR adrSubset) {
		this.consigneeSubset = adrSubset;
	}
	
	public TsADR getFinaluserSubset() {
		return finaluserSubset;
	}
	public void setFinaluserSubset(TsADR adrSubset) {
		this.finaluserSubset = adrSubset;
	}
	
	public void setConsignee(Party party, String beznr, String posnr) {
		if (party == null)
			return;
		if (consigneeSubset == null) {
			consigneeSubset = new TsADR();	
		}
		consigneeSubset.setAdrSubset(party, "2", beznr, posnr);
	}
	public void setFinalUser(Party party, String beznr, String posnr) {
		if (party == null)
			return;
		if (finaluserSubset == null) {
			finaluserSubset = new TsADR();	
		}
		finaluserSubset.setAdrSubset(party, "6", beznr, posnr);
	}
	
	public List<TsACN> getAcnList() {
		return acnList;
	}
	public void setAcnList(List<TsACN> acnList) {
		this.acnList = acnList;
	}
	public void addAcnList(TsACN acn){
		if (acnList == null) {
			acnList = new Vector<TsACN>();
		}
		acnList.add(acn);
	}
	
	public List<TsACO> getAcoList() {
		return acoList;
	}
	public void setAcoList(List<TsACO> acoList) {
		this.acoList = acoList;
	}
	public void addAcoList(TsACO aco) {
		if (acoList == null) {
			acoList = new Vector<TsACO>();
		}
		acoList.add(aco);
	}
	
	public List<TsAZV> getAzvList() {
		return azvList;
	}
	public void setAzvList(List<TsAZV> azvList) {
		this.azvList = azvList;
	}
	public void addAzvList(TsAZV azv) {
		if (azvList == null) {
			azvList = new Vector<TsAZV>();
		}
		azvList.add(azv);
	}
	
	public List<TsAED> getAedList() {
		return aedList;
	}
	public void setAedList(List<TsAED> aedList) {
		this.aedList = aedList;
	}
	public void addAedList(TsAED aed) {
		if (aedList == null) {
			aedList = new Vector<TsAED>();
		}
		aedList.add(aed);
	}
	
	public List<TsBZL> getBzlList() {
		return bzlList;
	}
	public void setBzlList(List<TsBZL> bzlList) {
		this.bzlList = bzlList;
	}
	public void addBzlList(TsBZL bzl) {				
		if (bzlList == null) {
			bzlList = new Vector<TsBZL>();
		}		
		bzlList.add(bzl);
	}
	
	public List<TsBAV> getBavList() {
		return bavList;
	}
	public void setBavList(List<TsBAV> bavList) {
		this.bavList = bavList;
	}
	public void addBavList(TsBAV bav) {
		if (bavList == null) {
			bavList = new Vector<TsBAV>();
		}
		bavList.add(bav);
	}
}	





