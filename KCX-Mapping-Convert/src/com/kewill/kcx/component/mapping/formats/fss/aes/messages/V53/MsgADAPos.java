/*
 * Funktion    : MsgADAPos.java
 * Titel       :
 * Erstellt    : 15.10.2008
 * Author      : Kewill CSF / krzoska
 * Beschreibung: Position  subsets to MsgADA
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEPO;

/**
 * Modul		: MsgADAPos<br>
 * Erstellt		: 15.10.2008<br>
 * Beschreibung	: Position  subsets to MsgADA.
 * 
 * @author krzoska
 * @version 5.3.00
 */
public class MsgADAPos extends FssMessage53 {
	private TsAPO   apoSubset;		
	private TsEPO   epoSubset;
	

	private List    <TsACO>acoList;
	private List    <TsACN>acnList;
	private List    <TsAZV>azvList;
	private List 	<TsAED>aedList;
	
	public MsgADAPos() {
		super();	
		
	}
	public TsAPO getApoSubset() {
		return apoSubset;
	}
	public void setApoSubset(TsAPO argument) {
		this.apoSubset = argument;
	}

	public List<TsACO> getAcoList() {
		return acoList;
	}
	public void setAcoList(List <Packages>list, String beznr, String posnr) {
		this.acoList = createACOList(list, beznr, posnr);
	}
	public void addACOList(TsACO argument) {
		if (acoList == null) {
			acoList = new Vector<TsACO>();			
		}

		this.acoList.add(argument);
	}
	public List<TsACN> getAcnList() {
		return acnList;
	
	}
	
	public void setAcnList(List <String>list, String beznr, String posnr) {
		this.acnList = createACNList(list, beznr, posnr);	
	}
	
	public void setAzvList(List <PreviousDocument>list, String beznr, String posnr) {
		this.azvList = createAZVList(list, beznr, posnr);	
	}

	public void setAedList(List <Document>list, String beznr, String posnr) {
		this.aedList = createAEDList(list, beznr, posnr);	
	}
	
	public List<TsAZV> getAzvList() {
		return azvList;
	
	}
	public List<TsAED> getAedList() {
		return aedList;
	
	}
	
	public TsEPO getEpoSubset() {
		return epoSubset;
	
	}
	public void setEpoSubset(TsEPO epoSubset) {
		this.epoSubset = epoSubset;
	}
	
	public void addACNList(TsACN acn) {
		if (acnList == null) {
			acnList = new Vector<TsACN>();			
		}

		acnList.add(acn);
	}
	
	public void addAZVList(TsAZV azv) {
		if (azvList == null) {
			azvList = new Vector<TsAZV>();			
		}

		azvList.add(azv);
	}

	public void addAEDList(TsAED aed) {
		if (aedList == null) {
			aedList = new Vector<TsAED>();			
		}

		aedList.add(aed);
	}
	
	
}	




