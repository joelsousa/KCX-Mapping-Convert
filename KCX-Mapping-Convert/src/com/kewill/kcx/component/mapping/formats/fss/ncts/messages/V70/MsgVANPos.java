package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVSP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVSU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAG;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVED;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVTV;

/**
 * Module		: NCTS OUT<br>
 * Created		: 12.11.2012<br>
 * Description	: FSS format VAN-Positions.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVANPos {
	
	private TsVPO   vpoSubset;        //V70 changes
	private List    <TsVAP>vapList;   //Adressen
	private List    <TsVSP>vspList;   //Contactdaten
	private List    <TsVCO>vcoList;
	private List    <TsVCN>vcnList;
	private List    <TsBSU>bsuList;   //V70 changes
	private List    <TsBZL>bzlList;     
	private List    <TsBAV>bavList;     
	private List    <TsVTV>vtvList;
	private List    <TsVED>vedList;
	private List    <TsVAG>vagList;      
	private List    <TsVSU>vsuList;   //V70 changes  
	private List    <TsSAP>sapList;      

	public TsVPO getVpoSubset() {
		return vpoSubset;
	}
	public void setVpoSubset(TsVPO vpoSubset) {
		this.vpoSubset = vpoSubset;
	}	
	public void addVAP(TsVAP vap) {
		if (vapList == null) {
			vapList = new Vector<TsVAP>();
		}
		vapList.add(vap);
	}
	public void addVSP(TsVSP vsp) {
		if (vspList == null) {
			vspList = new Vector<TsVSP>();
		}
		vspList.add(vsp);
	}
	public void addVCO(TsVCO vco) {
		if (vcoList == null) {
			vcoList = new Vector<TsVCO>();
		}
		vcoList.add(vco);
	}
	public void addVCN(TsVCN vcn) {
		if (vcnList == null) {
			vcnList = new Vector<TsVCN>();
		}
		vcnList.add(vcn);
	}
	public void addVTV(TsVTV vtv) {
		if (vtvList == null) {
			vtvList = new Vector<TsVTV>();
		}
		vtvList.add(vtv);
	}
	public void addVED(TsVED ved) {
		if (vedList == null) {
			vedList = new Vector<TsVED>();
		}
		vedList.add(ved);
	}
	
	public List<TsVAP> getVapList() {
		return vapList;
	}
	public void setVapList(List<TsVAP> vapList) {
		this.vapList = vapList;
	}

	public List<TsVSP> getVspList() {
		return vspList;
	}
	public void setVspList(List<TsVSP> list) {
		this.vspList = list;
	}
	
	public List<TsVCO> getVcoList() {
		return vcoList;
	}

	public void setVcoList(List<TsVCO> vcoList) {
		this.vcoList = vcoList;
	}

	public List<TsVCN> getVcnList() {
		return vcnList;
	}

	public void setVcnList(List<TsVCN> vcnList) {
		this.vcnList = vcnList;
	}

	public List<TsVTV> getVtvList() {
		return vtvList;
	}

	public void setVtvList(List<TsVTV> vtvList) {
		this.vtvList = vtvList;
	}

	public List<TsVED> getVedList() {
		return vedList;
	}
	
	public void setVedList(List<TsVED> vedList) {
		this.vedList = vedList;
	}
	
	public void setBsuList(List<TsBSU> bsuList) {
		this.bsuList = bsuList;
	}
	public List<TsBSU> getBsuList() {
		return bsuList;
	}
	public void addBSU(TsBSU bsu) {
		if (bsuList == null) {
			bsuList = new Vector<TsBSU>();
		}
		bsuList.add(bsu);
	}
	
	public void setBavList(List<TsBAV> list) {
		this.bavList = list;
	}
	public List<TsBAV> getBavList() {
		return bavList;
	}
	public void addBAV(TsBAV bav) {
		if (bavList == null) {
			bavList = new Vector<TsBAV>();
		}
		bavList.add(bav);
	}
	
	public void setBzlList(List<TsBZL> list) {
		this.bzlList = list;
	}
	public List<TsBZL> getBzlList() {
		return bzlList;
	}
	public void addBZL(TsBZL bzl) {
		if (bzlList == null) {
			bzlList = new Vector<TsBZL>();
		}
		bzlList.add(bzl);
	}
	
	public List<TsVAG> getVagList() {
		return vagList;
	}
	public void setVagList(List<TsVAG> list) {
		this.vagList = list;
	}	
	public void addVAG(TsVAG vag) {
		if (vagList == null) {
			vagList = new Vector<TsVAG>();
		}
		vagList.add(vag);
	}
	
	public List<TsVSU> getVsuList() {
		return vsuList;
	}
	public void setVsuList(List<TsVSU> list) {
		this.vsuList = list;
	}	
	public void addVSU(TsVSU vsu) {
		if (vsuList == null) {
			vsuList = new Vector<TsVSU>();
		}
		vsuList.add(vsu);
	}
	
	public List<TsSAP> getSapList() {
		return sapList;
	}
	public void setSapList(List<TsSAP> list) {
		this.sapList = list;
	}	
	public void addSAP(TsSAP sap) {
		if (sapList == null) {
			sapList = new Vector<TsSAP>();
		}
		sapList.add(sap);
	}
}
