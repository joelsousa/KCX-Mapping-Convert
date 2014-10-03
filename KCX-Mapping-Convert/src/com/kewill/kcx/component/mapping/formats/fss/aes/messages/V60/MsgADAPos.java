package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;


/*
 * Funktion    : MsgADAPos.java
 * Titel       :
 * Erstellt    : 15.10.2008
 * Author      : Kewill CSF / krzoska
 * Beschreibung: Position  subsets to MsgADA
 * 
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 05.05.2009
 * Label       : EI20090505
 * Description : new setConsignee(...) ( as in MsgADA )
 *
 */



import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;

public class MsgADAPos  {
	private TsAPO   apoSubset; // einmal		
	private TsATP   atpSubset; // einmal
	private TsEPO   epoSubset;
	private TsSAP   sapSubset;
	private TsADR   adrSubset;
	
	private List    <TsACN>acnList;
	private List    <TsACO>acoList;
	private List    <TsAZV>azvList;
	private List 	<TsAED>aedList;
	private List 	<TsBZL>bzlList;
	private List 	<TsBAV>bavList;
	
	public MsgADAPos() {
	
	}

	public TsAPO getApoSubset() {
		return apoSubset;
	}

	public void setApoSubset(TsAPO apoSubset) {
		this.apoSubset = apoSubset;
	}

	public TsATP getAtpSubset() {
		return atpSubset;
	}

	public void setAtpSubset(TsATP atpSubset) {
		this.atpSubset = atpSubset;
	}

	public TsEPO getEpoSubset() {
		return epoSubset;
	}

	public void setEpoSubset(TsEPO epoSubset) {
		this.epoSubset = epoSubset;
	}

	public TsSAP getSapSubset() {
		return sapSubset;
	}

	public void setSapSubset(TsSAP sapSubset) {
		this.sapSubset = sapSubset;
	}

	public TsADR getAdrSubset() {
		return adrSubset;
	}

	public void setAdrSubset(TsADR adrSubset) {
		this.adrSubset = adrSubset;
	}
	
	//EI20090505:
	public void setConsignee(Party party, String beznr, String posnr) {
		if (party == null)
			return;
		if (adrSubset == null)
			adrSubset = new TsADR();	
		adrSubset.setAdrSubset(party, "2", beznr, posnr);
	}

	public List<TsACN> getAcnList() {
		return acnList;
	}

	public void setAcnList(List<TsACN> acnList) {
		this.acnList = acnList;
	}

	public List<TsACO> getAcoList() {
		return acoList;
	}

	public void setAcoList(List<TsACO> acoList) {
		this.acoList = acoList;
	}

	public List<TsAZV> getAzvList() {
		return azvList;
	}

	public void setAzvList(List<TsAZV> azvList) {
		this.azvList = azvList;
	}

	public List<TsAED> getAedList() {
		return aedList;
	}

	public void setAedList(List<TsAED> aedList) {
		this.aedList = aedList;
	}

	public List<TsBZL> getBzlList() {
		return bzlList;
	}

	public void setBzlList(List<TsBZL> bzlList) {
		this.bzlList = bzlList;
	}

	public List<TsBAV> getBavList() {
		return bavList;
	}

	public void setBavList(List<TsBAV> bavList) {
		this.bavList = bavList;
	}
	public void addAcnList(TsACN acn){
		if(acnList==null){
			acnList=new Vector<TsACN>();
		}
		acnList.add(acn);
	}
	public void addAcoList(TsACO aco){
		if(acoList==null){
			acoList=new Vector<TsACO>();
		}
		acoList.add(aco);
	}
	public void addAedList(TsAED aed){
		if(aedList==null){
			aedList=new Vector<TsAED>();
		}
		aedList.add(aed);
	}
	public void addAzvList(TsAZV azv){
		if(azvList==null){
			azvList=new Vector<TsAZV>();
		}
		azvList.add(azv);
	}
	public void addBavList(TsBAV bav){
		if(bavList==null){
			bavList=new Vector<TsBAV>();
		}
		bavList.add(bav);
	}
	public void addBzlList(TsBZL bzl){
		//AK20090407-1
		if (bzl == null) 
			return; 		
		if(bzlList==null){
			bzlList=new Vector<TsBZL>();
		}		
		bzlList.add(bzl);
	}
}	





