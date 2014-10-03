package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V71;

import java.io.BufferedReader;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V71.common.ContainerData;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V71.common.PorPos;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKA;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKD;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKI;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKN;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKS;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKV;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKZ;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsMRN;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V70.TsAKR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71.TsANB;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71.TsLCL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Module		: Port<br>
 * Created		: 12.12.20113<br>
 * Description	: MsgPOR - Hafenauftrag.
 * 				: V71 new for BHT: TsANB, new Ts in MsgPORPos and ContainerData
 * 
 * @author iwaniuk
 * @version 7.1.00
 */
public class MsgPOR extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsAKR   akrSubset;   
	private TsAKI   akiSubset;   
	private TsAKA   akaSubset;  
	private List <TsAKS> aksSubsetList;
	private TsAKD   akdSubset;   
	private TsAKT   aktSubset;  
	private TsAKN   aknSubset;    
	private TsAKV   akvSubset;     
	private TsAKZ   akzSubset;    
	private List <TsTXT>   txtSubsetList;  
	private TsANB   anbSubset;          //new V71 for BHT
	private List <MsgPORPos> posList;
	private List <ContainerData> containerList;
	
	public MsgPOR() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
    
	public void setAKRSubset(TsAKR argument) {
		akrSubset = argument;
    }
	public TsAKR getAKRSubset() {
		return akrSubset;
	}

	public void setAKISubset(TsAKI argument) {
		akiSubset = argument;
    }
	public TsAKI getAKISubset() {
		return akiSubset;
	}
	
	public void setAKASubset(TsAKA argument) {
		akaSubset = argument;
    }
	public TsAKA getAKASubset() {
		return akaSubset;
	}
	
	public void setAKDSubset(TsAKD argument) {
		akdSubset = argument;
    }
	public TsAKD getAKDSubset() {
		return akdSubset;
	}
	
	public void setAKTSubset(TsAKT argument) {
		aktSubset = argument;
    }
	public TsAKT getAKTSubset() {
		return aktSubset;
	}
	
	public void setAKNSubset(TsAKN argument) {
		aknSubset = argument;
    }
	public TsAKN getAKNSubset() {
		return aknSubset;
	}
	
	public void setAKVSubset(TsAKV argument) {
		akvSubset = argument;
    }
	public TsAKV getAKVSubset() {
		return akvSubset;
	}
	
	public void setAKZSubset(TsAKZ argument) {
		akzSubset = argument;
    }
	public TsAKZ getAKZSubset() {
		return akzSubset;
	}

	public void setTXTSubsetList(List<TsTXT> argument) {
		txtSubsetList = argument;
    }
	public List<TsTXT> getTXTSubsetList() {
		return txtSubsetList;
	}	
	
	public void setAKSSubsetList(List <TsAKS> list) {
		aksSubsetList = list;
    }
	public List <TsAKS> getAKSSubsetList() {
		return aksSubsetList;
	}
	public void addAKSSubsetList(TsAKS subset) {
		if (aksSubsetList == null) {
			aksSubsetList = new Vector<TsAKS>();	
		}
		aksSubsetList.add(subset);
    }
	
	public void setPosList(List <MsgPORPos> list) {
		posList = list;
    }
	public List <MsgPORPos> getPosList() {
		return posList;
	}
	public void addPosList(MsgPORPos subset) {
		if (posList == null) {
			posList = new Vector<MsgPORPos>();	
		}
		posList.add(subset);
    }
	
	public void setContainerList(List <ContainerData> list) {
		containerList = list;
    }
	public List <ContainerData> getContainerList() {
		return containerList;
	}
	public void addContainerList(ContainerData subset) {
		if (containerList == null) {
			containerList = new Vector<ContainerData>();	
		}
		containerList.add(subset);
    }
	
	public void setANBSubset(TsANB argument) {
		anbSubset = argument;
    }
	public TsANB getANBSubset() {
		return anbSubset;
	}
	
	public String getFssString() throws FssException {	 
		return getFssString("");
	}
	public String getFssString(String firstTs) throws FssException {		
		String res = "";	
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {		
			//headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());
			}
		} else {	
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());
			}
		}		
		if (akrSubset != null) {	 
			res = appendString(res, akrSubset.teilsatzBilden());	
		}
		if (akiSubset != null && !akiSubset.isEmpty()) {	 
			res = appendString(res, akiSubset.teilsatzBilden());	
		}
		if (akaSubset != null && !akaSubset.isEmpty()) {	 
			res = appendString(res, akaSubset.teilsatzBilden());	
		}		
		if (aksSubsetList != null) {                     					 			
			for (TsAKS aks : aksSubsetList) {				
				if (aks != null && !aks.isEmpty()) {
					res = appendString(res, aks.teilsatzBilden());
				}
			}
		}
		if (akdSubset != null && !akdSubset.isEmpty()) {	 
			res = appendString(res, akdSubset.teilsatzBilden());	
		}
		if (aktSubset != null && !aktSubset.isEmpty()) {	 
			res = appendString(res, aktSubset.teilsatzBilden());	
		}
		if (aknSubset != null && !aknSubset.isEmpty()) {	 
			res = appendString(res, aknSubset.teilsatzBilden());	
		}
		if (akvSubset != null && !akvSubset.isEmpty()) {	 
			res = appendString(res, akvSubset.teilsatzBilden());	
		}
		if (akzSubset != null && !akzSubset.isEmpty()) {	 
			res = appendString(res, akzSubset.teilsatzBilden());	
		}		
		if (txtSubsetList != null) {                     					 			
			for (TsTXT txt : txtSubsetList) {				
				if (txt != null && !txt.isEmpty()) {
					res = appendString(res, txt.teilsatzBilden());	
				}
			}
		}
		if (anbSubset != null && !anbSubset.isEmpty()) {	//EI20131212 
			res = appendString(res, anbSubset.teilsatzBilden());	
		}
		if (posList != null) {                     					 			
			for (MsgPORPos pos : posList) {				
				if (pos != null && !pos.isEmpty()) {
					res = res + this.posBilden(pos);
				}
			}
		}
		if (containerList != null) {                     					 			
			for (ContainerData cont : containerList) {				
				if (cont != null && !cont.isEmpty()) {
					res = res + this.containerBilden(cont);
				}
			}
		}
		return res;
	}
	
    public void readMessage(BufferedReader message) throws FssException {
    	
    	 //z.Z. nicht benoetigt:	       
    }
    
    private String posBilden(MsgPORPos pos) throws FssException {
		String ret = "";
		
		if (pos == null || pos.isEmpty()) {
			return ret;
		}
		if (pos.getAPKSubset() != null && !pos.getAPKSubset().isEmpty()) {	 
			ret = appendString(ret, pos.getAPKSubset().teilsatzBilden());	
		}
		
		PorPos ebene1 = pos.getPosEbene1();		
		if (ebene1 != null) {	
			ret = ret + getPorPos(ebene1, 1);	
			List <PorPos>  list = pos.getPosEbene2List();
			if (list != null) {
				for (PorPos ebene2 : list) {
					if (ebene2 != null) {
						ret = ret + getPorPos(ebene2, 2);
					}
				}
			}			
		}
		return ret;
	}
 
    private String getPorPos(PorPos ebene, int nr) throws FssException {
    	String ret = "";
    	if (ebene == null) {
    		return ret;
    	}
    	
    	if (ebene.getACOSubset() != null && !ebene.getACOSubset().isEmpty()) {	 
			ret = appendString(ret, ebene.getACOSubset().teilsatzBilden());	
		}		
		if (ebene.getMRNSubsetList() != null && !ebene.getMRNSubsetList().isEmpty()) {							
			for (TsMRN mrn : ebene.getMRNSubsetList()) {						
				if (mrn != null && !mrn.isEmpty()) {
					ret = appendString(ret, mrn.teilsatzBilden());
				}
			}			
		}
		//EI20131011: if (nr == 2) {
		//nur ebene2: 
			if (ebene.getAEData() != null && !ebene.getAEData().isEmpty()) {
				if (ebene.getAEData().getAEDSubset() != null && !ebene.getAEData().getAEDSubset().isEmpty()) {	 
					ret = appendString(ret, ebene.getAEData().getAEDSubset().teilsatzBilden());	
				}	
				if (ebene.getAEData().getADR1Subset() != null && !ebene.getAEData().getADR1Subset().isEmpty()) {	 
					ret = appendString(ret, ebene.getAEData().getADR1Subset().teilsatzBilden());	
				}
				if (ebene.getAEData().getADR2Subset() != null && !ebene.getAEData().getADR2Subset().isEmpty()) {	 
					ret = appendString(ret, ebene.getAEData().getADR2Subset().teilsatzBilden());	
				}
				if (ebene.getAEData().getAEPSubset() != null && !ebene.getAEData().getAEPSubset().isEmpty()) {	 
					ret = appendString(ret, ebene.getAEData().getAEPSubset().teilsatzBilden());	
				}	
			}
		//EI20131011: }
		if (ebene.getTXTSubsetList() != null && !ebene.getTXTSubsetList().isEmpty()) {							
			for (TsTXT txt : ebene.getTXTSubsetList()) {						
				if (txt != null && !txt.isEmpty()) {
					ret = appendString(ret, txt.teilsatzBilden());
				}
			}			
		}
		if (ebene.getPOBSubset() != null && !ebene.getPOBSubset().isEmpty()) { //EI20131212	 
			ret = appendString(ret, ebene.getPOBSubset().teilsatzBilden());	
		}
		if (ebene.getAGRSubset() != null && !ebene.getAGRSubset().isEmpty()) {	 
			ret = appendString(ret, ebene.getAGRSubset().teilsatzBilden());	
		}	
		if (ebene.getAGISubset() != null && !ebene.getAGISubset().isEmpty()) {	 
			ret = appendString(ret, ebene.getAGISubset().teilsatzBilden());	
		}
		if (ebene.getAGTSubset() != null && !ebene.getAGTSubset().isEmpty()) {	 
			ret = appendString(ret, ebene.getAGTSubset().teilsatzBilden());	
		}
		if (ebene.getAGXSubset() != null && !ebene.getAGXSubset().isEmpty()) {	 
			ret = appendString(ret, ebene.getAGXSubset().teilsatzBilden());	
		}	
    	return ret;
    }
    
	private String containerBilden(ContainerData container) throws FssException {
		String ret = "";
				
		if (container == null) {
			return ret;
		}
		if (container.getACRSubset() != null && !container.getACRSubset().isEmpty()) {	 
			ret = appendString(ret, container.getACRSubset().teilsatzBilden());	
		}
		if (container.getCTBSubset() != null && !container.getCTBSubset().isEmpty()) {	 //EI20121212
			ret = appendString(ret, container.getCTBSubset().teilsatzBilden());	
		}
		if (container.getACTSubset() != null && !container.getACTSubset().isEmpty()) {	 
			ret = appendString(ret, container.getACTSubset().teilsatzBilden());	
		}
		if (container.getCPZSubset() != null && !container.getCPZSubset().isEmpty()) {	 //EI20121212
			ret = appendString(ret, container.getCPZSubset().teilsatzBilden());	
		}		
		if (container.getLclList() != null && !container.getLclList().isEmpty()) {	     //EI20121212						
			for (TsLCL lcl : container.getLclList()) {						
				if (lcl != null && !lcl.isEmpty()) {
					ret = appendString(ret, lcl.teilsatzBilden());
				}
			}			
		}
		return ret;				
	}					
}

