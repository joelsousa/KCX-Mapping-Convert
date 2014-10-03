/*
 * Funktion    : MsgCANPos.java
 * Titel       :
 * Erstellt    : 21.10.2008
 * Author      : Alfred Krzoska
 * Beschreibung: ExportDeclaration Kids2Fss EDEC
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPA;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPB;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPC;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPD;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPE;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPF;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPM;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPN;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPS;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPU;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPV;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of ImportDeclaration Positions. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgCKKPos extends FssMessage60 {
	private TsCPS cpsSubset;
	private TsCPC cpcSubset = null;
	private List<TsCPV> cpvList;	       //max 99
	private List<TsCPU> cpuList;	       //max 99	
	private List <TsCPB> cpbList;	       //max 99 
	private List <TsCPN> cpnList;	       //max 9
	private List <TsCPM> cpmList;	       //max 99
	private List <TsCPR> cprList; 	       //max 9
	private TsCPF cpfSubset = null;        //0-1        
	private List <TsCPA> cpaList;          // max 99
	private TsCPE cpeSubset;	           //0-1
	private List <TsCPD> cpdList;          // max 99
	
	
	public MsgCKKPos(String outFileName) {
		super();
		cpsSubset = new TsCPS();
		cpcSubset = new TsCPC();		
		cpvList = new Vector<TsCPV>();
		cpuList = new Vector<TsCPU>();
		cpbList = new Vector<TsCPB>();		
		cpnList = new Vector<TsCPN>();
		cpmList = new Vector<TsCPM>();
		cprList = new Vector<TsCPR>();
		cpfSubset = new TsCPF();		
		cpaList = new Vector<TsCPA>();
		cpeSubset = new TsCPE();	
		cpdList = new Vector<TsCPD>();
	}

	public TsCPS getCpsSubset() {
		return cpsSubset;	
	}
	public void setCpsSubset(TsCPS argument) {
		this.cpsSubset = argument;
	}

	public List<TsCPV> getCpvList() {
		return cpvList;	
	}
	public void setCpvList(List <TsCPV> list) {
		if (list == null) {
			return;
		}
		this.cpvList = list;
	}
	
	public List<TsCPU> getCpuList() {
		return cpuList;	
	}
	public void setCpuList(List <TsCPU> list) {
		if (list == null) {
			return;
		}
		this.cpuList = list;
	}

	public List<TsCPB> getCpbList() {
		return cpbList;
	}	
	public void setCpbList(List <TsCPB> list) {
		if (list == null) {
			return;
		}
		this.cpbList = list;
	}	

	public TsCPC getCpcSubset() {
		return cpcSubset;	
	}
	public void setCpcSubset(TsCPC subset) {
		if (subset == null) {
			return;
		}
		this.cpcSubset = subset;
	}	
	
	public List<TsCPN> getCpnList() {
		return cpnList;
	}
	public void setCpnList(List <TsCPN> list) {
		if (list == null) {
			return;
		}
		this.cpnList = list;
	}
	
	public List<TsCPM> getCpmList() {
		return cpmList;
	}
	public void setCpmList(List <TsCPM> list) {
		if (list == null) {
			return;
		}
		this.cpmList = list;
	}
	 
	public List<TsCPR> getCprList() {
		return cprList;
	}
	public void setCprList(List <TsCPR> list) {
		if (list == null) {
			return;
		}
		this.cprList = list;
	}
	
	public TsCPF getCpfSubset() {
			return cpfSubset;	
	}
	public void setCpfSubset(TsCPF subset) {
		if (subset == null) {
				return;
		}
		this.cpfSubset = subset;
	}
	
	public List<TsCPA> getCpaList() {
		return cpaList;
	}
	public void setCpaList(List <TsCPA> list) {
		if (list == null) {
			return;
		}
		this.cpaList = list;
	}
	
	public TsCPE getCpeSubset() {
		return cpeSubset;
	}
	public void setCpeSubset(TsCPE subset) {
		if (subset == null) {
			return;
		}
		this.cpeSubset = subset;
	}
		
	public List<TsCPD> getCpdList() {
		return cpdList;
	}
	public void setCpdList(List <TsCPD> list) {
		if (list == null) {
			return;
		}
		this.cpdList = list;
	}
}


