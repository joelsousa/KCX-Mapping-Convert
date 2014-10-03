package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64;

import java.io.BufferedReader;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHI;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAN1;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAN2;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsBEW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsDVT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsEMP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsERC;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsERK;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsGEH;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsMIN;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsSOF;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsVST;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgEZA /ImportDeclaration.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgEZA extends FssMessage{
	
	private TsVOR	vorSubset;
	private TsAN1   an1Subset;    // Hauptsatz 1
	private TsAN2   an2Subset;    // Hauptsatz 2
	private TsADR   anmelder;   
	private TsADR   versender; 
	private TsADR   erwerber;   
	private TsADR   empfaenger;
	private TsADR   kaeufer;   
	private TsADR   verkaeufer;   
	private TsADR   zollwertanmelder;   
	private TsADR   vertreterZWA;
	private TsADR	beteiligterFuerRechnung;
	private List<TsEMP> empList;  // zusaetzliche Empfaenger 
	private List<TsERC> ercList;  // Cuntainer 
	private List <TsBSU> bsuList;  // SumA 
	private List <TsBZL> bzlList;  // SumA 
	private List <TsBAV> bavList;  // SumA 
	private List <TsUNT> untList;  // SumA 
	private List <TsERK> erkList;  // 
	private TsDVT   dvtSubset;
	private TsTXT   txtSubset;  // Texte 	
	private List <MsgEZAPos> posList;
	
	public MsgEZA() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public void setAN1Subset(TsAN1 argument) {
		an1Subset = argument;
    }
	public TsAN1 getAN1Subset() {
		return an1Subset;
	}

	public void setAN2Subset(TsAN2 argument) {
		an2Subset = argument;
    }
	public TsAN2 getAN2Subset() {
		return an2Subset;
	}
	
	public void setAnmelder(TsADR argument) { //typ = 1
		anmelder = argument;
    }
	public TsADR getAnmelder() {
		return anmelder;
	}
	
	public void setVersender(TsADR argument) {  //typ = 3
		versender = argument;
    }
	public TsADR getVersender() {
		return versender;
	}
	
	public void setErwerber(TsADR argument) { //typ = 5
		erwerber = argument;
    }
	public TsADR getErwerber() {
		return erwerber;
	}
	
	public void setEmpfaenger(TsADR argument) { //typ = 4
		empfaenger = argument;
    }
	public TsADR getEmpfaenger() {
		return empfaenger;
	}
	
	public void setKaeufer(TsADR argument) { //typ = 6
		kaeufer = argument;
    }
	public TsADR getKaeufer() {
		return kaeufer;
	}
	
	public void setVerkaeufer(TsADR argument) { //typ = 7
		verkaeufer = argument;
    }	
	public TsADR getVerkaeufer() { 
		return verkaeufer;
	}
 
	public void setZollwertanmelder(TsADR argument) { //typ = 8
		zollwertanmelder = argument;
    }
	public TsADR getZollwertanmelder() {
		return zollwertanmelder;
	}
	
	public void setVertreterZWA(TsADR argument) { //typ = 9
		vertreterZWA = argument;
    }
	public TsADR getVertreterZWA() {
		return vertreterZWA;
	}
	
	public TsADR getBeteiligterFuerRechnung() {
		return beteiligterFuerRechnung;
	}

	public void setBeteiligterFuerRechnung(TsADR beteiligterFuerRechnung) {  //typ = 10
		this.beteiligterFuerRechnung = beteiligterFuerRechnung;
	}

	public void setEMPList(List <TsEMP> list) {
		empList = list;
    }
	public List <TsEMP> getEMPList() {
		return empList;
	}
	public void addEMPList(TsEMP subset) {
		if (empList == null) {
			empList = new Vector<TsEMP>();	
		}
		empList.add(subset);
    }
	
	public void setERCList(List <TsERC> list) {
		ercList = list;
    }
	public List <TsERC> getERCList() {
		return ercList;
	}
	public void addERCList(TsERC subset) {
		if (ercList == null) {
			ercList = new Vector<TsERC>();	
		}
		ercList.add(subset);
    } 
	public void setBSUList(List <TsBSU> list) {
		bsuList = list;
    }
	public List <TsBSU> getBSUList() {
		return bsuList;
	}
	public void addBSUList(TsBSU subset) {
		if (bsuList == null) {
			bsuList = new Vector<TsBSU>();	
		}
		bsuList.add(subset);
    }
	
	public void setBZLList(List <TsBZL> list) {
		bzlList = list;
    }
	public List <TsBZL> getBZLList() {
		return bzlList;
	}
	public void addBZLList(TsBZL subset) {
		if (bzlList == null) {
			bzlList = new Vector<TsBZL>();	
		}
		bzlList.add(subset);
    }
	
	public void setBAVList(List <TsBAV> list) {
		bavList = list;
    }
	public List <TsBAV> getBAVList() {
		return bavList;
	}
	public void addBAVList(TsBAV subset) {
		if (bavList == null) {
			bavList = new Vector<TsBAV>();	
		}
		bavList.add(subset);
    }
	
	
	public void setUNTList(List <TsUNT> list) {
		untList = list;
    }
	public List <TsUNT> getUNTList() {
		return untList;
	}
	public void addUNTList(TsUNT subset) {
		if (untList == null) {
			untList = new Vector<TsUNT>();	
		}
		untList.add(subset);
    }
	
	public void setERKList(List <TsERK> list) {
		erkList = list;
    }
	public List <TsERK> getERKList() {
		return erkList;
	}
	public void addERKList(TsERK subset) {
		if (erkList == null) {
			erkList = new Vector<TsERK>();	
		}
		erkList.add(subset);
    }
	
	public void setDVTSubset(TsDVT subset) { 
		dvtSubset = subset;
    }
	public TsDVT getDVTSubset() {
		return dvtSubset;
	}
		

	public TsTXT getTxtSubset() {
		return txtSubset;
	}

	public void setTxtSubset(TsTXT txtSubset) {
		this.txtSubset = txtSubset;
	}

	public void setEZAPosList(List <MsgEZAPos> list) {
		posList = list;
    }
	public List <MsgEZAPos> getEZAPosList() {
		return posList;
	}
	public void addEZAPosList(MsgEZAPos pos) {
		if (posList == null) {
			posList = new Vector<MsgEZAPos>();	
		}
		posList.add(pos);
	}
	
	public String getFssString() throws FssException {
		String res = "";	
		
		if (vorSubset != null && !vorSubset.isEmpty()) {	 
			res = appendString(res, vorSubset.teilsatzBilden());	
		}		
		if (an1Subset != null && !an1Subset.isEmpty()) {		
			res = appendString(res, an1Subset.teilsatzBilden());
		}
		if (an2Subset != null && !an2Subset.isEmpty()) {			
			res = appendString(res, an2Subset.teilsatzBilden());
		}		
		if (anmelder != null && !anmelder.isEmpty()) {			
			res = appendString(res, anmelder.teilsatzBilden());
		}
		if (versender != null && !versender.isEmpty()) {			
			res = appendString(res, versender.teilsatzBilden());
		}
		if (erwerber != null && !erwerber.isEmpty()) {			
			res = appendString(res, erwerber.teilsatzBilden());
		}
		if (empfaenger != null && !empfaenger.isEmpty()) {			
			res = appendString(res, empfaenger.teilsatzBilden());
		}
		if (kaeufer != null && !kaeufer.isEmpty()) {			
			res = appendString(res, kaeufer.teilsatzBilden());
		}
		if (verkaeufer != null && !verkaeufer.isEmpty()) {			
			res = appendString(res, verkaeufer.teilsatzBilden());
		}
		if (zollwertanmelder != null && !zollwertanmelder.isEmpty()) {			
			res = appendString(res, zollwertanmelder.teilsatzBilden());
		}
		if (vertreterZWA != null && !vertreterZWA.isEmpty()) {			
			res = appendString(res, vertreterZWA.teilsatzBilden());
		}		
		if (empList != null) {                     					 
			int empSize = empList.size();
			for (int j = 0; j < empSize; j++) {
				TsEMP emp = (TsEMP) empList.get(j);
				if (emp != null && !emp.isEmpty()) {
					res = appendString(res, emp.teilsatzBilden());
				}
			}
		}
		if (ercList != null) {                     					 
			int ercSize = ercList.size();
			for (int j = 0; j < ercSize; j++) {
				TsERC erc = (TsERC) ercList.get(j);
				if (erc != null && !erc.isEmpty()) {
					res = appendString(res, erc.teilsatzBilden());
				}
			}
		}
		
		if (bsuList != null) {                     					 
			int bsuSize = bsuList.size();
			for (int j = 0; j < bsuSize; j++) {
				TsBSU bsu = (TsBSU) bsuList.get(j);
				if (bsu != null && !bsu.isEmpty()) {
					res = appendString(res, bsu.teilsatzBilden());
				}
			}
		}
		if (bzlList != null) {                     					 
			int bzlSize = bsuList.size();
			for (int j = 0; j < bzlSize; j++) {
				TsBZL bzl = (TsBZL) bzlList.get(j);
				if (bzl != null && !bzl.isEmpty()) {
					res = appendString(res, bzl.teilsatzBilden());
				}
			}
		}
		if (bavList != null) {                     					 
			int bavSize = bavList.size();
			for (int j = 0; j < bavSize; j++) {
				TsBAV bav = (TsBAV) bavList.get(j);
				if (bav != null && !bav.isEmpty()) {
					res = appendString(res, bav.teilsatzBilden());
				}
			}
		}
		if (untList != null) {                     					 
			int unbSize = untList.size();
			for (int j = 0; j < unbSize; j++) {
				TsUNT unt = (TsUNT) untList.get(j);
				if (unt != null && !unt.isEmpty()) {
					res = appendString(res, unt.teilsatzBilden());
				}
			}
		}
		if (erkList != null) {                     					 
			int erkSize = erkList.size();
			for (int j = 0; j < erkSize; j++) {
				TsERK erk = (TsERK) erkList.get(j);
				if (erk != null && !erk.isEmpty()) {
					res = appendString(res, erk.teilsatzBilden());
				}
			}
		}
		if (dvtSubset != null && !dvtSubset.isEmpty()) {			
			res = appendString(res, dvtSubset.teilsatzBilden());
		}

		if (txtSubset != null && !txtSubset.isEmpty()) {
			res = appendString(res, txtSubset.teilsatzBilden());
		}

		if (posList != null) {                     					 
			int posSize = posList.size();
			for (int j = 0; j < posSize; j++) {
				MsgEZAPos pos = (MsgEZAPos) posList.get(j);
				if (pos != null) {					
					//res = appendString(res, getEZAPos(pos));  					
					res = res + getEZAPos(pos);
				}
			}
		}	
		
		return res;
	}
	
    public void readMessage(BufferedReader message) throws FssException {
    	
    	 //z.Z. nicht benoetigt:	       
    }
    
    private String getEZAPos(MsgEZAPos pos) throws FssException {
		String ret = "";
		
		if (pos == null) {
			return ret;
		}
		if (pos.getPO1Subset() != null) {
			 if (!pos.getPO1Subset().isEmpty()) {
				 ret = appendString(ret, pos.getPO1Subset().teilsatzBilden());
			 }
		}
		if (pos.getPO2Subset() != null) {
			 if (!pos.getPO2Subset().isEmpty()) {
				 ret = appendString(ret, pos.getPO2Subset().teilsatzBilden());
			 }
		}
		if (pos.getPRNSubset() != null) {
			 if (!pos.getPRNSubset().isEmpty()) {
				 ret = appendString(ret, pos.getPRNSubset().teilsatzBilden());
			 }
		}
		if (pos.getUNPList() != null) {
			 if (!pos.getUNPList().isEmpty()) {
				 int unpSize = pos.getUNPList().size();
					for (int j = 0; j < unpSize; j++) {
						TsUNP unp = (TsUNP) pos.getUNPList().get(j);
						if (unp != null && !unp.isEmpty()) {
							ret = appendString(ret, unp.teilsatzBilden());
						}
					}
			 }
		}
		if (pos.getGEHList() != null) {
			 if (!pos.getGEHList().isEmpty()) {
				 int gehSize = pos.getGEHList().size();
					for (int j = 0; j < gehSize; j++) {
						TsGEH geh = (TsGEH) pos.getGEHList().get(j);
						if (geh != null && !geh.isEmpty()) {
							ret = appendString(ret, geh.teilsatzBilden());
						}
					}
			 }
		}
		if (pos.getVSTList() != null) {
			 if (!pos.getVSTList().isEmpty()) {
				 int vstSize = pos.getVSTList().size();
					for (int j = 0; j < vstSize; j++) {
						TsVST vst = (TsVST) pos.getVSTList().get(j);
						if (vst != null && !vst.isEmpty()) {
							ret = appendString(ret, vst.teilsatzBilden());
						}
					}
			 }
		}
		if (pos.getKONSubset() != null) {
			 if (!pos.getKONSubset().isEmpty()) {
				 ret = appendString(ret, pos.getKONSubset().teilsatzBilden());
			 }
		}
		if (pos.getAHIList() != null) {
			 if (!pos.getAHIList().isEmpty()) {
				 int ahiSize = pos.getAHIList().size();
					for (int j = 0; j < ahiSize; j++) {
						TsAHI ahi = (TsAHI) pos.getAHIList().get(j);
						if (ahi != null && !ahi.isEmpty()) {
							ret = appendString(ret, ahi.teilsatzBilden());
						}
					}
			 }
		}
		if (pos.getMINList() != null) {
			 if (!pos.getMINList().isEmpty()) {
				 int minSize = pos.getMINList().size();
					for (int j = 0; j < minSize; j++) {
						TsMIN min = (TsMIN) pos.getMINList().get(j);
						if (min != null && !min.isEmpty()) {
							ret = appendString(ret, min.teilsatzBilden());
						}
					}
			 }
		}
		if (pos.getBEWList() != null) {
			 if (!pos.getBEWList().isEmpty()) {
				 int bewSize = pos.getBEWList().size();
					for (int j = 0; j < bewSize; j++) {
						TsBEW bew = (TsBEW) pos.getBEWList().get(j);
						if (bew != null && !bew.isEmpty()) {
							ret = appendString(ret, bew.teilsatzBilden());
						}
					}
			 }
		}
		if (pos.getSOFList() != null) {
			 if (!pos.getSOFList().isEmpty()) {
				 int sofSize = pos.getSOFList().size();
					for (int j = 0; j < sofSize; j++) {
						TsSOF sof = (TsSOF) pos.getSOFList().get(j);
						if (sof != null && !sof.isEmpty()) {
							ret = appendString(ret, sof.teilsatzBilden());
						}
					}
			 }
		}
		
		return ret;
	}
}

