/*
 * Funktion    : MsgEAMPos.java
 * Titel       :
 * Erstellt    : 13.10.2008
 * Author      : Kewill CSF / houdek
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;

/**
 * Modul		: MsgEAMPos<br>
 * Erstellt		: 13.10.2008<br>
 * Beschreibung	: -.
 * 
 * @author houdek
 * @version 5.3.00
 */
public class MsgEAMPos extends FssMessage53 {
			
	private TsAPO   apoSubset;
	private TsEPO   epoSubset;
	private TsAZV   azvSubset;
	//V60: private TsADR   consignee;
	private List    <TsAZV>azvList;	
	
	public MsgEAMPos() {
		super();	
		apoSubset = new TsAPO();
		epoSubset = new TsEPO();
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
	
	public TsAZV getAzvSubset() {
		return azvSubset;
	}
	public List getAzvList() {
		return azvList;
	}
	public void setAzvList(List <PreviousDocument>list, String beznr, String posnr) {
		this.azvList = createAZVList(list, beznr, posnr);
	}
	public void addAzvList(TsAZV argument) {
		this.azvList.add(argument);
	}
	
}	




