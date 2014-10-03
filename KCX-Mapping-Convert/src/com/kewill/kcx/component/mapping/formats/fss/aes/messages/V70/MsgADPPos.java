package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;

/**
 * Module		: MsgADP<br>
 * Created		: 28.07.2012<br>
 * Description	: Einlesen einer FSS-ADPPos Nachricht. 
 * 				: ZABIS V70
 * 
 * @author  iwaniuk
 * @version 2.1.00
 */

public class MsgADPPos {


	private TsAPO   apoSubset;  
	private TsSAP   sapSubset;		
	private List    <TsADR>adrList;		
	private TsATP   atpSubset;  	  //EI20120919: is doch veraendert das letzte Leld asvfr ist nicht mehr da
	private List    <TsATI>atiList;	  //is doch unveraendert, geaendert hat sich nur (zabis) formatierung	
	private List    <TsACO>acoList;	
	private List    <TsACN>acnList;	
	private List    <TsAZV>azvList;	
	private List    <TsBZL>bzlList;	 	
	private List    <TsBAV>bavList;		
	private List    <TsAED>aedList;		
	
	
	public MsgADPPos() {
		super();
		adrList = new Vector<TsADR>();
		atiList = new Vector<TsATI>();
		acoList = new Vector<TsACO>();
		acnList = new Vector<TsACN>();
		azvList = new Vector<TsAZV>();
		bzlList = new Vector<TsBZL>();
		bavList = new Vector<TsBAV>();
		aedList = new Vector<TsAED>();
	}
	
	public TsAPO getApoSubset() {
		return apoSubset;
	}
	public void setApoSubset(TsAPO argument) {
		this.apoSubset = argument;
	}
	public TsSAP getSapSubset() {
		return sapSubset;
	}
	public void setSapSubset(TsSAP argument) {
		this.sapSubset = argument;
	}

	public List<TsADR> getAdrList() {
		return adrList;
	}
	public void addAdrList(TsADR argument) {
	    this.adrList.add(argument);
	}	
	
	public TsATP getAtpSubset() {
		return atpSubset;
	}
	public void setAtpSubset(TsATP argument) {
		this.atpSubset = argument;
	}
	
	public List<TsATI> getAtiList() {
		return atiList;
	}	
	public void addAtiList(TsATI argument) {
		this.atiList.add(argument);
	}	
	
	public List<TsBZL> getBzlList() {
		return bzlList;
	}
	public void addBzlList(TsBZL argument) {
		this.bzlList.add(argument);
	}	
	
	public List<TsBAV> getBavList() {
		return bavList;
	}
	public void addBavList(TsBAV argument) {
		this.bavList.add(argument);
	}	
	
	public List<TsACO> getAcoList() {
		return acoList;
	}
	public void addAcoList(TsACO argument) {
		this.acoList.add(argument);
	}
	
	public List<TsACN> getAcnList() {
		return acnList;
	}
	public void addAcnList(TsACN argument) {
		this.acnList.add(argument);
	}	
	
	public List<TsAZV> getAzvList() {
		return azvList;
	}
	public void addAzvList(TsAZV argument) {
		this.azvList.add(argument);
	}	
	
	public List<TsAED> getAedList() {
		return aedList;
	}
	public void addAedList(TsAED argument) {
		this.aedList.add(argument);
	}

}	

