/*
 * Funktion    : MsgADPos.java
 * Titel       :
 * Erstellt    : 05.09.2008
 * Author      : Elisabeth Iwaniuk
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

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsNVE;

/**
 * Modul		: MsgADPos<br>
 * Erstellt		: 05.09.2008<br>
 * Beschreibung	: -.
 * 
 * @author Iwaniuk
 * @version 5.3.00
 */
public class MsgADPos {


	private TsAPO   apoSubset;  	
	private List    <TsACO>acoList;
	private TsNVE	nveSubset;         // bis Version 5.3
	private List    <TsACN>acnList;	
	private List    <TsAZV>azvList;		
	private List    <TsAED>aedList;		
	
	
	public MsgADPos() {
		super();
		
		acoList = new Vector<TsACO>();
		acnList = new Vector<TsACN>();
		azvList = new Vector<TsAZV>();
		aedList = new Vector<TsAED>();
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

	public TsNVE getNveSubset() {
		return nveSubset;
	}
	public void setNveSubset(TsNVE argument) {
		this.nveSubset = argument;
	}
	
}	

