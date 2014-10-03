/*
 * Function    	: MsgVAN.java
 * Date        	: 02.09.2010
 * Author      	: Kewill  / Christine Kron
 * Description 	: Contains the Message ExportDeclaration in the ZABIS FSS-Format VAN
 *				: Version 6.0

 * Changes 
 * -----------
 * Author      	: 
 * Date        	: 
 * Description 	: 
 */
package com.kewill.kcx.component.mapping.formats.fss.ncts.messages;


import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAG;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAK;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVBR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVDZ;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVED;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVTV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVVS;

/**
 * Modul		: MsgVAN<br>
 * Erstellt		: 02.09.2010<br>
 * Beschreibung	: FSS format VAN.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class MsgVAN extends FssMessage62 {

	private TsVOR				vorSubset;
	private TsVAN   			vanSubset;   
	private TsVAK   			consignorSubset; 
	private TsVAK   			consigneeSubset;
	private TsVAK   			authSubset;
	private TsVAK   			principalSubset;
	private TsVAK   			consignorSecuritySubset;  //EI20110523
	private TsVAK   			consigneeSecuritySubset;  //EI20110523
	private List <TsVDZ> 		vdzList;
	private List <TsVSI> 		vsiList;
	private List <TsVVS> 		vvsList;
	private TsVSU				vsuSubset;
	private TsSAS				sasSubset;
	private List <TsVBR> 		vbrList;
	
	private List <MsgVANPos>	posList;


	public void setConsignor(PartyNCTS party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consignorSubset == null) {
			consignorSubset = new TsVAK();
		}
		consignorSubset.setAdrSubset(party, "1", beznr, posnr);
	}	
	
	public void setConsignee(PartyNCTS party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consigneeSubset == null) {
			consigneeSubset = new TsVAK();
		}
		consigneeSubset.setAdrSubset(party, "2", beznr, posnr);
	}
	
	public void setPrincipal(PartyNCTS party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (principalSubset == null) {
			principalSubset = new TsVAK();
		}
		principalSubset.setAdrSubset(party, "3", beznr, posnr);
	}	

	public void setAuth(PartyNCTS party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (authSubset == null) {
			authSubset = new TsVAK();
		}
		authSubset.setAdrSubset(party, "4", beznr, posnr);
	}
	
	public void setConsignorSecurity(PartyNCTS party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consignorSecuritySubset == null) {
			consignorSecuritySubset = new TsVAK();
		}
		consignorSecuritySubset.setAdrSubset(party, "5", beznr, posnr);
	}	
	
	public void setConsigneeSecurity(PartyNCTS party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consigneeSecuritySubset == null) {
			consigneeSecuritySubset = new TsVAK();
		}
		consigneeSecuritySubset.setAdrSubset(party, "6", beznr, posnr);
	}
		
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	public TsVAN getVanSubset() {
		return vanSubset;
	}
	public void setVanSubset(TsVAN vanSubset) {
		this.vanSubset = vanSubset;
	}
	
	public TsVAK getConsignorSubset() {
		return consignorSubset;
	}
	public void setConsignorSubset(TsVAK consignorSubset) {
		this.consignorSubset = consignorSubset;
	}
	public TsVAK getConsigneeSubset() {
		return consigneeSubset;
	}
	public void setConsigneeSubset(TsVAK consigneeSubset) {
		this.consigneeSubset = consigneeSubset;
	}
	public TsVAK getAuthSubset() {
		return authSubset;
	}
	public void setAuthSubset(TsVAK authSubset) {
		this.authSubset = authSubset;
	}
	public TsVAK getPrincipalSubset() {
		return principalSubset;
	}
	public void setPrincipalSubset(TsVAK principalSubset) {
		this.principalSubset = principalSubset;
	}
	public TsVAK getConsignorSecuritySubset() {
		return consignorSecuritySubset;
	}
	public void setConsignorSecuritySubset(TsVAK subset) {
		this.consignorSecuritySubset = subset;
	}
	public TsVAK getConsigneeSecuritySubset() {
		return consigneeSecuritySubset;
	}
	public void setConsigneeSecuritySubset(TsVAK subset) {
		this.consigneeSecuritySubset = subset;
	}
	
	
	public List<TsVDZ> getVdzList() {
		return vdzList;
	}
	public void setVdzList(List<TsVDZ> vdzList) {
		this.vdzList = vdzList;
	}
	public void addVdzList(TsVDZ vdz) {
		if (vdz == null) {
			return;
		}
		if (vdzList == null) {
			vdzList = new Vector<TsVDZ>();
		}
		this.vdzList.add(vdz);
	}
	
	public List<TsVVS> getVvsList() {
		return vvsList;
	}

	public void setVvsList(List<TsVVS> vvsList) {
		this.vvsList = vvsList;
	}

	public void addVvsList(TsVVS vvs) {
		if (vvs == null) {
			return;
		}
		if (vvsList == null) {
			vvsList = new Vector<TsVVS>();
		}
		this.vvsList.add(vvs);
	}
	
	public TsVSU getVsuSubset() {
		return vsuSubset;
	}
	public void setVsuSubset(TsVSU vsuSubset) {
		this.vsuSubset = vsuSubset;
	}
	public TsSAS getSasSubset() {
		return sasSubset;
	}
	public void setSasSubset(TsSAS sasSubset) {
		this.sasSubset = sasSubset;
	}

	public List<TsVBR> getVbrList() {
		return vbrList;
	}
	public void setVbrList(List<TsVBR> vbrList) {
		this.vbrList = vbrList;
	}
	public void addVbrList(TsVBR vbr) {
		if (vbr == null) {
			return;
		}
		if (vbrList == null) {
			vbrList = new Vector<TsVBR>();
		}
		this.vbrList.add(vbr);
	}
	public List<TsVSI> getVsiList() {
		return vsiList;
	}
	public void setVsiList(List<TsVSI> vsiList) {
		this.vsiList = vsiList;
	}

	public void addVsiList(TsVSI vsi) {
		if (vsi == null) {
			return;
		}
		if (vsiList == null) {
			vsiList = new Vector<TsVSI>();
		}
		this.vsiList.add(vsi);
	}
	public List<MsgVANPos> getPosList() {
		return posList;
	}	
	
	public void setPosList(List<MsgVANPos> argument) {
		this.posList = argument;
	}
	public void addPosList(MsgVANPos argument) {
		if (argument == null) {
		    return;
		}
		if (posList == null) {
		    posList = new Vector<MsgVANPos>();
		}
		this.posList.add(argument);
	}

	
	public String getFssString() throws FssException {
		String res = "";
			
		if (vorSubset != null && !vorSubset.isEmpty()) {
			res = appendString(res, vorSubset.teilsatzBilden());
		}
		//EI20111010: if (vanSubset != null && !vanSubset.isEmpty()) {
		if (vanSubset != null) {   //EI20111010: it is not right, to check isEmpty(..) for only 4 fields
			//                       they will be filled in ZABIS from Kundenstamm 
			res = appendString(res, vanSubset.teilsatzBilden());
		}
		if (consignorSubset != null && !consignorSubset.isEmpty()) {
			res = appendString(res, consignorSubset.teilsatzBilden());
		}
		if (consigneeSubset != null && !consigneeSubset.isEmpty()) {
			res = appendString(res, consigneeSubset.teilsatzBilden());
		}
		if (authSubset != null && !authSubset.isEmpty()) {
			res = appendString(res, authSubset.teilsatzBilden());
		}
		if (principalSubset != null && !principalSubset.isEmpty()) {
			res = appendString(res, principalSubset.teilsatzBilden());
		}
		if (consignorSecuritySubset != null && !consignorSecuritySubset.isEmpty()) {
			res = appendString(res, consignorSecuritySubset.teilsatzBilden());
		}
		if (consigneeSecuritySubset != null && !consigneeSecuritySubset.isEmpty()) {
			res = appendString(res, consigneeSecuritySubset.teilsatzBilden());
		}
		if (vdzList != null) {
			int vdzSize = vdzList.size();
			for (int j = 0; j < vdzSize; j++) {
				TsVDZ vdz = (TsVDZ) vdzList.get(j);
				if (vdz != null && !vdz.isEmpty()) {
					res = appendString(res, vdz.teilsatzBilden());
				}
			}
		}		
		if (vsiList != null) {
			int vsiSize = vsiList.size();
			for (int j = 0; j < vsiSize; j++) {
				TsVSI vsi = (TsVSI) vsiList.get(j);
				if (vsi != null && !vsi.isEmpty()) {
					res = appendString(res, vsi.teilsatzBilden());
				}
			}
		}		
		if (vvsList != null) {
			int vvsSize = vvsList.size();
			for (int j = 0; j < vvsSize; j++) {
				TsVVS vvs = (TsVVS) vvsList.get(j);
				if (vvs != null && !vvs.isEmpty()) {
					res = appendString(res, vvs.teilsatzBilden());
				}
			}
		}	
		if (vsuSubset != null && !vsuSubset.isEmpty()) {
			res = appendString(res, vsuSubset.teilsatzBilden());
		}

		if (sasSubset != null && !sasSubset.isEmpty()) {
			res = appendString(res, sasSubset.teilsatzBilden());
		}
		
		if (vbrList != null) {
			int vbrSize = vbrList.size();
			for (int j = 0; j < vbrSize; j++) {
				TsVBR vbr = (TsVBR) vbrList.get(j);
				if (vbr != null && !vbr.isEmpty()) {
					res = appendString(res, vbr.teilsatzBilden());
				}
			}
		}	
		
		int posSize = 0;
		if (posList != null) {
	        posSize = posList.size();
		}
		for (int i = 0; i < posSize; i++) {			
			MsgVANPos pos = posList.get(i);
			
			TsVPO vpo = pos.getVpoSubset();
			if (vpo != null && !vpo.isEmpty()) {
				res = appendString(res, vpo.teilsatzBilden());
			}

			List <TsVAP> vapList = pos.getVapList();
			if (vapList != null) {
				int vapSize = vapList.size();
				for (int j = 0; j < vapSize; j++) {
					TsVAP vap = (TsVAP) vapList.get(j);
					if (vap != null && !vap.isEmpty()) {
						res = appendString(res, vap.teilsatzBilden()); 
					}
				}
			}
			
			List <TsVCO> vcoList = pos.getVcoList();
			if (vcoList != null) {
				int vcoSize = vcoList.size();
				for (int j = 0; j < vcoSize; j++) {
					TsVCO vco = (TsVCO) vcoList.get(j);
					if (vco != null && !vco.isEmpty()) {
						res = appendString(res, vco.teilsatzBilden()); 
					}
				}
			}

			List <TsVCN> vcnList = pos.getVcnList();
			if (vcnList != null) {
				int vcnSize = vcnList.size();
				for (int j = 0; j < vcnSize; j++) {
					TsVCN vcn = (TsVCN) vcnList.get(j);
					if (vcn != null && !vcn.isEmpty()) {
						res = appendString(res, vcn.teilsatzBilden()); 
					}
				}
			}
						
			List <TsBSU> bsuList = pos.getBsuList();
			if (bsuList != null) {
				int bsuSize = bsuList.size();
				for (int j = 0; j < bsuSize; j++) {
					TsBSU bsu = (TsBSU) bsuList.get(j);
					if (bsu != null && !bsu.isEmpty()) {
						res = appendString(res, bsu.teilsatzBilden()); 
					}
				}
			}	
			
			List <TsVTV> vtvList = pos.getVtvList();
			if (vtvList != null) {
				int vtvSize = vtvList.size();
				for (int j = 0; j < vtvSize; j++) {
					TsVTV vtv = (TsVTV) vtvList.get(j);
					if (vtv != null && !vtv.isEmpty()) {
						res = appendString(res, vtv.teilsatzBilden()); 
					}
				}
			}

			List <TsVED> vedList = pos.getVedList();
			if (vedList != null) {
				int vedSize = vedList.size();
				for (int j = 0; j < vedSize; j++) {
					TsVED ved = (TsVED) vedList.get(j);
					if (ved != null && !ved.isEmpty()) {
						res = appendString(res, ved.teilsatzBilden()); 
					}
				}
			}
           
			List <TsVAG> vagList = pos.getVagList();	//EI20111205 neu:		
			if (vagList != null) {
				int vagSize = vagList.size();
				for (int j = 0; j < vagSize; j++) {
					TsVAG vag = (TsVAG) vagList.get(j);
					if (vag != null && !vag.isEmpty()) {
						res = appendString(res, vag.teilsatzBilden()); 
					}
				}
			}
			List <TsVSU> vsuList = pos.getVsuList();	//EI20111205 neu - wird noch nicht gemapped		
			if (vsuList != null) {
				int vsuSize = vsuList.size();
				for (int j = 0; j < vsuSize; j++) {
					TsVSU vsu = (TsVSU) vsuList.get(j);
					if (vsu != null && !vsu.isEmpty()) {
						res = appendString(res, vsu.teilsatzBilden()); 
					}
				}
			}
			List <TsSAP> sapList = pos.getSapList();   //EI20111205 neu - wird noch nicht gemapped	
			if (sapList != null) {
				int sapSize = sapList.size();
				for (int j = 0; j < sapSize; j++) {
					TsSAP sap = (TsSAP) sapList.get(j);
					if (sap != null && !sap.isEmpty()) {
						res = appendString(res, sap.teilsatzBilden()); 
					}
				}
			}
		}	
		
		return res;
   }	
}

