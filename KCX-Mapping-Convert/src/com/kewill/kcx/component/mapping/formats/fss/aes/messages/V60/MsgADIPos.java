/*
 * Function    : MsgADP.java
 * Date    : 02.10.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description:
  * ----------------------------------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 10.03.2009
 * Label       : EI20090310
 * Description : V60 checked
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;

public class MsgADIPos  {
			
	private TsAPO   apoSubset;
	private TsATP   atpSubset;	
	private List    <TsACO>acoList;
	
	public MsgADIPos() {
		super();	
		
		//iwa - die subsets werden erst bei beadrf initialisiert
		//apoSubset = new TsAPO();		
		//atpSubset = new TsATP();				
		acoList = new Vector<TsACO>();
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

	public List getAcoList() {
		return acoList;
	}
	public void setAcoList(List argument) {
		this.acoList = argument;
	}
	public void addAcoList(TsACO argument) {
		this.acoList.add(argument);
	}
}	




