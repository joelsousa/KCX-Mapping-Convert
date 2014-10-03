package com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPA;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPB;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPD;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPM;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPN;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPS;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPU;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPV;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKA;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKC;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKK;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKV;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of ImportDeclaration. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgCKK extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;     			
	private TsCKK	ckkSubset = null;  	
	private TsCKA 	consignor = null;   //1 
	private TsCKA 	consignee = null;   //2	
	private TsCKA 	declarant = null;	//3 	
	private TsCKA 	representative = null;	//4 		
	
	private List <TsCKC> ckcList;                //max 99 ContainerNumber	
	private List <TsCKV> ckvList;                //max 9 
	private List <TsCKR> ckrList;                //max 99 
	private List <MsgCKKPos>ckkPosList;          //max 999
	

	public MsgCKK(String outFileName) {
		super(outFileName);
		vorSubset = new TsVOR("");		
		ckkPosList = new Vector<MsgCKKPos>();
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
	
	public TsCKK getCkkSubset() {
		return ckkSubset;
	}
	public void setCkkSubset(TsCKK vor) {
		this.ckkSubset = vor;
	}
	
	public TsCKA getDeclarant() {
		return declarant;	
	}
	public void setDeclarant(TsCKA argument) {
		if (argument == null) {
			return;
		}
		if (declarant == null) {
			declarant = new TsCKA();
		}
		this.declarant = argument;
	}	

	public TsCKA getConsignor() {
		return consignor;	
	}
	public void setConsignor(TsCKA argument) {
		if (argument == null) {
			return;
		}
		if (consignor == null) {
			consignor = new TsCKA();
		}
		this.consignor = argument;
	}
	
	public TsCKA getConsignee() {
		return consignee;	
	}
	public void setConsignee(TsCKA argument) {
		if (argument == null) {
			return;
		}
		if (consignee == null) {
			consignee = new TsCKA();
		}
		this.consignee = argument;
	}			
	
	public TsCKA geRrepresentative() {
		return representative;	
	}
	public void setRepresentative(TsCKA argument) {
		if (argument == null) {
			return;
		}
		if (representative == null) {
			representative = new TsCKA();
		}
		this.representative = argument;
	}	
		
	public List<TsCKC> getCkcList() {
		return ckcList;
	}
	public void setCkcList(List<TsCKC> argument) {
		if (argument == null) {
			return;
		}		
		if (ckcList == null) {
			ckcList = new Vector<TsCKC>();
		}
		this.ckcList = argument;
	}	
	public void addCkcList(TsCKC argument) {
		if (argument == null) {
			return;
		}		
		if (ckcList == null) {
			ckcList = new Vector<TsCKC>();
		}
		this.ckcList.add(argument);
	}	
		
	public List<MsgCKKPos> getCkkPosList() {
		return ckkPosList;	
	}

	public void setCkkPosList(List<MsgCKKPos> list) {
		this.ckkPosList = list;
	}

	public void addCkkPosList(MsgCKKPos argument) {
		if (argument == null) {
			return;			
		}
		if (ckkPosList == null) {
			ckkPosList = new Vector<MsgCKKPos>();
		}
		this.ckkPosList.add(argument);
	}

	public List<TsCKV> getCkvList() {
		return ckvList;
	}
	public void setCkvList(List<TsCKV> argument) {		
		this.ckvList = argument;
	}	
	public void addCkvList(TsCKV argument) {
		if (argument == null) {
			return;
		}		
		if (ckvList == null) {
			ckvList = new Vector<TsCKV>();
		}
		this.ckvList.add(argument);
	}	
	
	public List<TsCKR> getCkrList() {
		return ckrList;
	}
	public void setCkrList(List<TsCKR> argument) {		
		this.ckrList = argument;
	}	
	public void addCkrList(TsCKR argument) {
		if (argument == null) {
			return;
		}		
		if (ckrList == null) {
			ckrList = new Vector<TsCKR>();
		}
		this.ckrList.add(argument);
	}		
	
//--------------------	
	public String getFssString() throws FssException {	  
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException {		
		StringBuffer res = new StringBuffer();			
					
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			//headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res.append(headSubset.teilsatzBilden() + Utils.LF);
			}
		} else {
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res.append(vorSubset.teilsatzBilden() + Utils.LF);
			}
		}		
		if (ckkSubset != null) {
			res.append(ckkSubset.teilsatzBilden() + Utils.LF);
		}
		if (consignor != null && !consignor.isEmpty()) {
			res.append(consignor.teilsatzBilden() + Utils.LF);
		}
		if (consignee != null && !consignee.isEmpty()) {
			res.append(consignee.teilsatzBilden() + Utils.LF);	
		}		
		if (declarant != null && !declarant.isEmpty()) {
			res.append(declarant.teilsatzBilden() + Utils.LF);
		}
		if (representative != null && !representative.isEmpty()) {
			res.append(representative.teilsatzBilden() + Utils.LF);	
		}
		
		if (ckcList != null) {
			res.append(writeCkcList());
		}				
		if (ckvList != null) {
			res.append(writeCkvList());
		}		
		if (ckrList != null) {
			res.append(writeCkrList());
		}
		
		
		if (ckkPosList != null) {
			res.append(writeCkkPosList());
		}

		return res.toString(); 	
	}

	private String writeCkkPosList() throws FssException {
		StringBuffer stb = new StringBuffer();
		if (ckkPosList != null) {								
			for (MsgCKKPos pos : ckkPosList) {			
				
				TsCPS cpsSubset = pos.getCpsSubset();
				if (cpsSubset != null && !cpsSubset.isEmpty()) {
					
					stb.append(cpsSubset.teilsatzBilden() + Utils.LF);						
					stb.append(pos.getCpcSubset().teilsatzBilden() + Utils.LF);										
					stb.append(writeCpvList(pos.getCpvList()));
					stb.append(writeCpuList(pos.getCpuList()));
					stb.append(writeCpbList(pos.getCpbList()));										
					stb.append(writeCpnList(pos.getCpnList()));					
					stb.append(writeCpmList(pos.getCpmList()));
					stb.append(writeCprList(pos.getCprList()));
					if (pos.getCpfSubset() != null && !pos.getCpfSubset().isEmpty()) {
						stb.append(pos.getCpfSubset().teilsatzBilden() + Utils.LF);	
					}
					stb.append(writeCpaList(pos.getCpaList()));
					if (pos.getCpeSubset() != null && !pos.getCpeSubset().isEmpty()) {
						stb.append(pos.getCpeSubset().teilsatzBilden() + Utils.LF);	
					}
					stb.append(writeCpdList(pos.getCpdList()));
				}
			}
		}
		return stb.toString();
	}
	
	private String writeCkcList() throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (ckcList != null) {			
			for (TsCKC ccnSubset : ckcList) {				
				if (ccnSubset != null && !ccnSubset.isEmpty()) {
					stb.append(ccnSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	
	private String writeCkvList() throws FssException {
		StringBuffer stb = new StringBuffer();	
		if (ckvList != null) {			
			for (TsCKV ckvSubset : ckvList) {				
				if (ckvSubset != null && !ckvSubset.isEmpty()) {
					stb.append(ckvSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	
	private String writeCkrList() throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (ckrList != null) {			
			for (TsCKR ckrSubset : ckrList) {				
				if (ckrSubset != null && !ckrSubset.isEmpty()) {
					stb.append(ckrSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	
	private String writeCpvList(List <TsCPV> list) throws FssException {				
		StringBuffer stb = new StringBuffer();		
		if (list != null)  {			
			for (TsCPV cpv : list) {				
				if (cpv != null && !cpv.isEmpty()) {
					stb.append(cpv.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();		
	}

	private String writeCpuList(List <TsCPU> list) throws FssException {	
		StringBuffer stb = new StringBuffer();		
		if (list != null)  {			
			for (TsCPU cpu : list) {				
				if (cpu != null && !cpu.isEmpty()) {
					stb.append(cpu.teilsatzBilden() + Utils.LF);
				}
			}
		}
		return stb.toString();
	}
	
	private String writeCpbList(List <TsCPB> list) throws FssException {		
		StringBuffer stb = new StringBuffer();		
		if (list != null)  {			
			for (TsCPB cpb : list) {				
				if (cpb != null && !cpb.isEmpty()) {
					stb.append(cpb.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();		
	}
	
	
	private String writeCpnList(List <TsCPN> list)throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (list != null) {			
			for (TsCPN cpn : list) {				
				if (cpn != null && !cpn.isEmpty()) {
					stb.append(cpn.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	
	private String writeCpmList(List <TsCPM> list) throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (list != null) {			
			for (TsCPM cpm : list) {					
				if (cpm != null && !cpm.isEmpty()) {
					stb.append(cpm.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	
	private String writeCprList(List <TsCPR> list) throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (list != null) {			
			for (TsCPR cpr : list) {					
				if (cpr != null && !cpr.isEmpty()) {
					stb.append(cpr.teilsatzBilden() + Utils.LF);
				}
			}
		}			
		return stb.toString();
	}
	
	private String writeCpaList(List <TsCPA> list) throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (list != null) {			
			for (TsCPA cpa : list) {					
				if (cpa != null && !cpa.isEmpty()) {
					stb.append(cpa.teilsatzBilden() + Utils.LF);
				}
			}
		}			
		return stb.toString();
	}
	
	private String writeCpdList(List <TsCPD> list) throws FssException {
		StringBuffer stb = new StringBuffer();		
		if (list != null) {			
			for (TsCPD cpd : list) {					
				if (cpd != null && !cpd.isEmpty()) {
					stb.append(cpd.teilsatzBilden() + Utils.LF);
				}
			}
		}			
		return stb.toString();
	}
}
