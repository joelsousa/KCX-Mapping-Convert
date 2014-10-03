package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATP;   
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;

/**
 * Module		: Export/aes<br>
 * Created		: 31.07.2012<br>
 * Description	: V70 - FSS-Message ADI-Position (KIDS-PreNotification.GoodsItem).
 * 
 * @author iwaniuk
 * @version 2.1.00
 */ 

public class MsgADIPos  {
	
	private TsAPO         apoSubset;
	private TsATP         atpSubset;	
	private List <TsATI>  atiList;  //new for V21
	private TsADR         consigneeSubset; //empfaenger new for V21
	private List <TsACO>  acoList;
	private List <TsAED>  aedList;  //new for V21
	
	public MsgADIPos() {
		super();		
	}
	
	public TsAPO getApoSubset() {
		return apoSubset;
	}
	public void setApoSubset(TsAPO argument) {
		this.apoSubset = argument;
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
	public void setAtiList(List<TsATI> argument) {
		this.atiList = argument;
	}
	public void addAtiList(TsATI argument) {
		if (atiList == null) {
			atiList = new Vector<TsATI>();
		}
		this.atiList.add(argument);
	}
	
	public List<TsACO> getAcoList() {
		return acoList;
	}
	public void setAcoList(List<TsACO> argument) {
		this.acoList = argument;
	}
	public void addAcoList(TsACO argument) {
		if (acoList == null) {
			acoList = new Vector<TsACO>();
		}
		this.acoList.add(argument);
	}
	
	public List<TsAED> getAedList() {
		return aedList;
	}
	public void setAedList(List<TsAED> argument) {
		this.aedList = argument;
	}
	public void addAedList(TsAED argument) {
		if (aedList == null) {
			aedList = new Vector<TsAED>();
		}
		this.aedList.add(argument);
	}
	
	public TsADR getConsigneeSubset() {
		return 	consigneeSubset;
	}
	public void setConsigneeSubset(TsADR argument) {
		this.consigneeSubset = argument;
	}
	public void setConsignee(Party party, String beznr, String posnr) {
		if (consigneeSubset == null) {
			consigneeSubset = new TsADR();	
		}
		consigneeSubset.setAdrSubset(party, "2", beznr, posnr);
	}
}	




