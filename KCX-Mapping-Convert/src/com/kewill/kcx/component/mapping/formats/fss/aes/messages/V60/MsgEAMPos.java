/*
 * Funktion    : MsgEAMPos.java
 * Titel       :
 * Erstellt    : 13.10.2008
 * Author      : Kewill CSF / houdek
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * -----------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Label       : EI20090424
 * Description : new Member: consignee
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;

public class MsgEAMPos extends FssMessage60 {
			
	private TsAPO   apoSubset;
	private TsEPO   epoSubset;
	private TsAZV   azvSubset;
	private TsADR   consignee;     //EI20090424
	private List    <TsAZV>azvList;	
	
	public MsgEAMPos() {
		super();	
		apoSubset = new TsAPO();
		epoSubset = new TsEPO();
		consignee = new TsADR();
		azvList = new Vector<TsAZV>();	
	}
	public TsAPO getApoSubset() {
		return apoSubset;
	}
	public void setApoSubset(TsAPO argument) {
		this.apoSubset = argument;
	}
	public void setApoSubset(MsgExpEntPos msgExpEntPos, String beznr) {
		if (apoSubset == null) {
			apoSubset = new TsAPO();
		}
		apoSubset.setApoSubset(msgExpEntPos, beznr);
	}
	
	public TsEPO getEpoSubset() {
		return epoSubset;
	}
	public void setEpoSubset(TsEPO argument) {
		this.epoSubset = argument;
	}
	/*
	public void setEpoSubset(MsgExpEntPos msgExpEntPos, String beznr) {
		if (epoSubset == null) {
			epoSubset = new TsEPO();	
		}
		epoSubset.setEpoSubset(msgExpEntPos, beznr);
	}	
    */
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
	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR argument) {
		this.consignee = argument;
	}
	public void setConsignee(Party party, String beznr, String posnr) {
		if (consignee == null)
			consignee = new TsADR();	
		consignee.setAdrSubset(party, "2", beznr, posnr);
	}	
}	




