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

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCBW;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCL;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCMD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCNZ;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCPO;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCPZ;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCSG;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCST;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCUN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVE;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVM;

/**
 * Modul		: MsgCANPos.<br>
 * Erstellt		: 20.10.2008<br>
 * Beschreibung : ExportDeclaration KIDS2Fss EDEC 
 * 
 * @author krzoska
 * @version 1.1.00
 */
public class MsgCANPos extends FssMessage60 {
	private TsCPO cpoSubset;
	private TsCPZ cpzSubset = null;
	private List<TsCCL> cclList;	
	private List<TsCBW> cbwList;	
	private List <TsCUN> cunList; 
	private List <TsCVA> cvaList;
	private List <TsCVM> cvmList;               //EI20110429 new
	private List <TsCMD> cmdList;               //EI20110429 new
	private List <TsCNZ> cnzList;               //EI20110429 new
	private TsCVE cveSubset = null;                 //EI20110429 new  
	private List <TsCST> cstList;               //EI20110429 new
	private List <TsCSG> csgList;               //EI20110429 new
	
	
	public MsgCANPos(String outFileName) {
		super();
		cpoSubset = new TsCPO();		
		cclList = new Vector<TsCCL>();
		cbwList = new Vector<TsCBW>();
		cunList = new Vector<TsCUN>();
		cvaList = new Vector<TsCVA>();
		cvmList = new Vector<TsCVM>();
		cmdList = new Vector<TsCMD>();
		cnzList = new Vector<TsCNZ>();
		cstList = new Vector<TsCST>();
		csgList = new Vector<TsCSG>();
		//tsCPZ = new TsCPZ();
		//tsCVE = new TsCVE();
	}

	public TsCPO getCpoSubset() {
		return cpoSubset;	
	}
	public void setCpoSubset(TsCPO argument) {
		this.cpoSubset = argument;
	}

	public List<TsCCL> getCclList() {
		return cclList;	
	}
	public void setCclList(List <TsCCL> list) {
		if (list == null) {
			return;
		}
		this.cclList = list;
	}
/*
	public void setCclList(List <Packages>list, String beznr, String posnr) {
		this.cclList = createCCLList(list, beznr, posnr);
	}
*/	
	
	public List<TsCBW> getCbwList() {
		return cbwList;	
	}
	public void setCbwList(List <TsCBW> list) {
		if (list == null) {
			return;
		}
		this.cbwList = list;
	}
/*
	public void setCbwList(List <Permit>list, String beznr, String posnr) {
		this.cbwList = createCBWList(list, beznr, posnr);
	}
*/
	public List<TsCUN> getCunList() {
		return cunList;
	}	
	public void setCunList(List <TsCUN> list) {
		if (list == null) {
			return;
		}
		this.cunList = list;
	}	
/*
	public void setCunList(List <Document> list, String beznr, String posnr) {
		this.cunList = createCUNList(list, beznr, posnr);
	}
*/
	public List<TsCVA> getCvaList() {
		return cvaList;
	}
	public void setCvaList(List <TsCVA> list) {
		if (list == null) {
			return;
		}
		this.cvaList = list;
	}
/*
	public void setCvaList(List <PreviousDocument> list, String beznr, String posnr) {
		this.cvaList = createCVAList(list, beznr, posnr);
	}
*/

	public TsCPZ getCpzSubset() {
		return cpzSubset;	
	}
	public void setCpzSubset(TsCPZ subset) {
		if (subset == null) {
			return;
		}
		this.cpzSubset = subset;
	}	
	
	public List<TsCVM> getCvmList() {
		return cvmList;
	}
	public void setCvmList(List <TsCVM> list) {
		if (list == null) {
			return;
		}
		this.cvmList = list;
	}
	
	public List<TsCMD> getCmdList() {
		return cmdList;
	}
	public void setCmdList(List <TsCMD> list) {
		if (list == null) {
			return;
		}
		this.cmdList = list;
	}
	 
	public List<TsCNZ> getCnzList() {
		return cnzList;
	}
	public void setCnzList(List <TsCNZ> list) {
		if (list == null) {
			return;
		}
		this.cnzList = list;
	}
	
	public TsCVE getCveSubset() {
			return cveSubset;	
	}
	public void setCveSubset(TsCVE subset) {
		if (subset == null) {
				return;
		}
		this.cveSubset = subset;
	}
	
	public List<TsCST> getCstList() {
		return cstList;
	}
	public void setCstList(List <TsCST> list) {
		if (list == null) {
			return;
		}
		this.cstList = list;
	}
	
	public List<TsCSG> getCsgList() {
		return csgList;
	}
	public void setCsgList(List <TsCSG> list) {
		if (list == null) {
			return;
		}
		this.csgList = list;
	}
		
}


