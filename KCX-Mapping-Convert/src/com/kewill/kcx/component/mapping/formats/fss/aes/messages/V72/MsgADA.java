package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.AdaAdo;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsABF;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADB;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADD;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAEZ;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAND;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsANM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAWE;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Module		: Export/aes<br>
 * Created		: 19.07.2013<br>
 * Description	: V72 - FSS-Message ADA (KIDS-ExportDeclaration).
 * 				: neu: Positionssatz - Kennzeichen AH-stat Wert, mit 0 senden (TsAPO und TsEPO)
 * 
 * @author iwaniuk
 * @version 2.2.00
 */

public class MsgADA extends FssDatei {

	private TsVOR				vorSubset;
	private TsHead				headSubset;     //EI20120926
	private TsDAT   			datSubset;   
	private TsEDA   			edaSubset;
	private TsSAS 				sasSubset;  
	private List <TsABF> 		abfList;	
	
	private TsADR   			consignorSubset; //ausfuehrer
	private TsAPN   			consignorContact; 
	private TsADR   			consigneeSubset; //empfaenger
	private TsAPN   			consigneeContact;
	private TsADR   			declarantSubset;  //anmelder
	private TsAPN   			declarantContact;
	private TsADR   			agentSubset;      //vertreter
	private TsAPN   			agentContact;
	private TsADR   			subcontractorSubset;  //subunternehmer
	private TsAPN   			subcontractorContact;
	private TsADR   			finaluserSubset;      //endverwender 
	private TsAPN   			finaluserContact;
	
	private TsEAM   			eamSubset;
	private TsAPV               apvSubset;
	private List <TsAWE> 		aweList;	
	private List <TsANM> 		anmList;	
	private List <TsAEZ> 		aezList;
	private TsAND               andSubset;
	private List <TsAVS> 		avsList;	
	//private List <TsAED> 		aedList;   	//EI20120919: statt aedList jetzt adoList
	
	private List <MsgADAPos>	posList;	
	private List <AdaAdo>       adoList;  

	
	public MsgADA(String outFileName) {
		super(outFileName);	
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
	
	public TsDAT getDatSubset() {
		return datSubset;
	}
	public void setDatSubset(TsDAT dat) {
		this.datSubset = dat;
	}
	
	public TsEDA getEdaSubset() {
		return edaSubset;
	}	
	public void setEdaSubset(TsEDA eda) {
		this.edaSubset = eda;
	}
	
	public void setSasSubset(TsSAS tsSAS) {
		this.sasSubset = tsSAS;
	}
	public TsSAS getSasSubset() {
		return sasSubset;
	}
	
	public List<TsABF> getAbfList() {
		return abfList;
	}
	public void setAbfList(List<TsABF> abfList) {
		this.abfList = abfList;
	}
	public void addAbfList(TsABF abf) {
		if (abf == null) {
			return;
		}
		if (abfList == null) {
			abfList = new Vector<TsABF>();
		}
		this.abfList.add(abf);
	}	
	   
	public TsEAM getEamSubset() {
		return eamSubset;
	}
	public void setEamSubset(TsEAM argument) {		
		this.eamSubset = argument;
	}

	public void setApvSubset(TsAPV tsApn) {
		this.apvSubset = tsApn;
	}
	public TsAPV getApvSubset() {
		return apvSubset;
	}
	
	public List<TsAWE> getAweList() {
		return aweList;	
	}
	public void setAweList(List<TsAWE> list) {
		this.aweList = list;
	}
	public void addAweList(TsAWE awe) {
		if (awe == null) {
		    return;
		}
		if (aweList == null) {
			aweList = new Vector<TsAWE>();			
		}
		this.aweList.add(awe);
	}
		
	public List<TsANM> getAnmList() {
		return anmList;	
	}
	public void setAnmList(List<TsANM> list) {
		this.anmList = list;
	}
	public void addAnmList(TsANM anm) {
		if (anm == null) {
		    return;
		}
		if (anmList == null) {
			anmList = new Vector<TsANM>();			
		}
		this.anmList.add(anm);
	}

	public List<TsAEZ> getAezList() {
		return aezList;	
	}
	public void setAezList(List<TsAEZ> list) {
		this.aezList = list;
	}
	public void addAezList(TsAEZ aez) {
		if (aez == null) {
		    return;
		}
		if (aezList == null) {
			aezList = new Vector<TsAEZ>();			
		}
		this.aezList.add(aez);
	}			
	   
	public void setAndSubset(TsAND tsAnd) {
		this.andSubset = tsAnd;
	}
	public TsAND getAndSubset() {
		return andSubset;
	} 
	
	public List<TsAVS> getAvsList() {
		return avsList;	
	}
	public void setAvsList(List<TsAVS> list) {
		this.avsList = list;
	}
	public void addAvsList(TsAVS avs) {
		if (avs == null) {
		    return;
		}
		if (avsList == null) {
		    avsList = new Vector<TsAVS>();			
		}
		this.avsList.add(avs);
	}
	/*
	public List<TsAED> getAedList() {
		return aedList;
	}	
	public void setAedList(List<TsAED> list) {
		this.aedList = list;
	}	
	public void addAedList(TsAED aed) {
		if (aed == null) {
			return;
		}
		if (aedList == null) {
			aedList = new Vector<TsAED>();
		}
		this.aedList.add(aed);
	} 
	*/
	public List<MsgADAPos> getPosList() {
		return this.posList;
	}
	public void setPosList(List<MsgADAPos> list) {
		this.posList = list;
	}
	public void addPosList(MsgADAPos pos) {
		if (pos == null) {
		    return;
		}
		if (posList == null) {
		    posList = new Vector<MsgADAPos>();
		}
		this.posList.add(pos);
	}
	
	public List<AdaAdo> getAdoList() {
		return this.adoList;
	}
	public void setAdoList(List<AdaAdo> list) {
		this.adoList = list;
	}
	public void addAdoList(AdaAdo argument) {
		if (argument == null) {
		    return;
		}
		if (adoList == null) {
			adoList = new Vector<AdaAdo>();
		}
		this.adoList.add(argument);
	}
	
 /// Adressen:
	
	public TsADR getconsignorSubset() {
		return consignorSubset;
	}
	public void setConsignorSubset(TsADR adr) {
		this.consignorSubset = adr;
	}
	public TsAPN getConsignorContact() {
		return consignorContact;
	}
	public void setConsignorContact(TsAPN contact) {
		this.consignorContact = contact;
	}
	public void setConsignor(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consignorSubset == null) {
			consignorSubset = new TsADR();
		}
		consignorSubset.setAdrSubset(party, "1", beznr, posnr);
		
		// CK120829 
		if (party.getContactPerson() != null) {
			consignorContact = new TsAPN();
			consignorContact.setApnSubset(party, "1", beznr, posnr);	
		}
		
	}	
	
	public TsADR getConsigneeSubset() {
		return consigneeSubset;
	}
	public void setConsigneeSubset(TsADR adr) {
		this.consigneeSubset = adr;
	}
	public TsAPN getConsigneeContact() {
		return consigneeContact;
	}
	public void setConsigneeContact(TsAPN contact) {
		this.consigneeContact = contact;
	}
	public void setConsignee(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consigneeSubset == null) {
			consigneeSubset = new TsADR();
		}
		consigneeSubset.setAdrSubset(party, "2", beznr, posnr);
		
		// CK120829 
		if (party.getContactPerson() != null) {
			consigneeContact = new TsAPN();
			consigneeContact.setApnSubset(party, "2", beznr, posnr);	
		}		
	}
	
	public TsADR getAgentSubset() {
		return agentSubset;
	}
	public void setAgentSubset(TsADR adr) {
		this.agentSubset = adr;
	}
	public TsAPN getAgentContact() {
		return agentContact;
	}
	public void setAgentContact(TsAPN contact) {
		this.agentContact = contact;
	}
	public void setAgent(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (agentSubset == null) {
			agentSubset = new TsADR();
		}
		agentSubset.setAdrSubset(party, "4", beznr, posnr);

		// CK120829 
		if (party.getContactPerson() != null) {
			agentContact = new TsAPN();
			agentContact.setApnSubset(party, "4", beznr, posnr);	
		}		
		
	}

	public TsADR getDeclarantSubset() {
		return declarantSubset;
	}
	public void setDeclarantSubset(TsADR adr) {
		this.declarantSubset = adr;
	}
	public TsAPN getDeclaranContact() {
		return declarantContact;
	}
	public void setDeclaranContact(TsAPN contact) {
		this.declarantContact = contact;
	}
	public void setDeclarant(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (declarantSubset == null) {
			declarantSubset = new TsADR();
		}
		declarantSubset.setAdrSubset(party, "3", beznr, posnr);
		
		// CK120829 
		if (party.getContactPerson() != null) {
			declarantContact = new TsAPN();
			declarantContact.setApnSubset(party, "3", beznr, posnr);	
		}
	}

	public TsADR getSubcontractorSubset() {
		return subcontractorSubset;
	}
	public void setSubcontractorSubset(TsADR adr) {
		this.subcontractorSubset = adr;
	}
	public TsAPN getSubcontractorContact() {
		return subcontractorContact;
	}
	public void setSubcontractorContact(TsAPN contact) {
		this.subcontractorContact = contact;
	}
	public void setSubcontractor(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (subcontractorSubset == null) {
			subcontractorSubset = new TsADR();
		}
		subcontractorSubset.setAdrSubset(party, "5", beznr, posnr);
		
		// CK120829 
		if (party.getContactPerson() != null) {
			subcontractorContact = new TsAPN();
			subcontractorContact.setApnSubset(party, "5", beznr, posnr);	
		}
	}
	
	
	public TsADR getFinalUserSubset() {
		return finaluserSubset;
	}
	public void setFinalUserSubset(TsADR adr) {
		this.finaluserSubset = adr;
	}
	public TsAPN getFinalUserContact() {
		return finaluserContact;
	}
	public void setFinalUserContact(TsAPN contact) {
		this.finaluserContact = contact;
	}
	public void setFinalUser(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (finaluserSubset == null) {
			finaluserSubset = new TsADR();
		}
		finaluserSubset.setAdrSubset(party, "6", beznr, posnr);

		// CK120829 
		if (party.getContactPerson() != null) {
			finaluserContact = new TsAPN();
			finaluserContact.setApnSubset(party, "6", beznr, posnr);	
		}
	}
	
 ///////////////
	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException {
		String res = "";
				
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {			
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());	
			}
		} else {
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());
			}
		}		
		if (datSubset != null) {
			res = appendString(res, datSubset.teilsatzBilden());
		}
		if (edaSubset != null && !edaSubset.isEmpty()) {
			res = appendString(res, edaSubset.teilsatzBilden());
		}
		if (sasSubset != null && !sasSubset.isEmpty()) {
			res = appendString(res, sasSubset.teilsatzBilden());
		}
		if (abfList != null) {                     					 			
			for (TsABF abf : abfList) {				
				if (abf != null && !abf.isEmpty()) {
					res = appendString(res, abf.teilsatzBilden());
				}
			}
		}				
		if (consignorSubset != null && !consignorSubset.isEmpty()) {
			res = appendString(res, consignorSubset.teilsatzBilden());
		}
		if (consignorContact != null && !consignorContact.isEmpty()) {
			res = appendString(res, consignorContact.teilsatzBilden());
		}
		if (consigneeSubset != null && !consigneeSubset.isEmpty()) {
			res = appendString(res, consigneeSubset.teilsatzBilden());
		}
		if (consigneeContact != null && !consigneeContact.isEmpty()) {
			res = appendString(res, consigneeContact.teilsatzBilden());
		}
		if (declarantSubset != null && !declarantSubset.isEmpty()) {
			res = appendString(res, declarantSubset.teilsatzBilden());
		}
		if (declarantContact != null && !declarantContact.isEmpty()) {
			res = appendString(res, declarantContact.teilsatzBilden());
		}
		if (agentSubset != null && !agentSubset.isEmpty()) {
			res = appendString(res, agentSubset.teilsatzBilden());
		}
		if (agentContact != null && !agentContact.isEmpty()) {
			res = appendString(res, agentContact.teilsatzBilden());
		}
		if (subcontractorSubset != null && !subcontractorSubset.isEmpty()) {
			res = appendString(res, subcontractorSubset.teilsatzBilden());
		}
		if (subcontractorContact != null && !subcontractorContact.isEmpty()) {
			res = appendString(res, subcontractorContact.teilsatzBilden());
		}
		if (finaluserSubset != null && !finaluserSubset.isEmpty()) {
			res = appendString(res, finaluserSubset.teilsatzBilden());
		}
		if (finaluserContact != null && !finaluserContact.isEmpty()) {
			res = appendString(res, finaluserContact.teilsatzBilden());
		}
		if (eamSubset != null && !eamSubset.isEmpty()) {
			res = appendString(res, eamSubset.teilsatzBilden());
		}			
		if (apvSubset != null && !apvSubset.isEmpty()) {
			res = appendString(res, apvSubset.teilsatzBilden());
		}
		
		if (aweList != null) {                     					 			
			for (TsAWE awe : aweList) {				
				if (awe != null && !awe.isEmpty()) {
					res = appendString(res, awe.teilsatzBilden());
				}
			}
		}			
		if (anmList != null) {                     					 			
			for (TsANM anm : anmList) {				
				if (anm != null && !anm.isEmpty()) {
					res = appendString(res, anm.teilsatzBilden());
				}
			}
		}	
		if (aezList != null) {                     					 			
			for (TsAEZ aez : aezList) {				
				if (aez != null && !aez.isEmpty()) {
					res = appendString(res, aez.teilsatzBilden());
				}
			}
		}			
		if (andSubset != null && !andSubset.isEmpty()) {
			res = appendString(res, andSubset.teilsatzBilden());
		}		
		if (avsList != null) {                     					 			
			for (TsAVS avs : avsList) {				
				if (avs != null && !avs.isEmpty()) {
					res = appendString(res, avs.teilsatzBilden());
				}
			}
		}	
		/*
		if (aedList != null) {                     					  	
			for (TsAED aed : aedList) {				
				if (aed != null && !aed.isEmpty()) {
					res = appendString(res, aed.teilsatzBilden());
				}
			}
		}
		*/	
		if (posList != null) {	       
		for (MsgADAPos pos :  posList) {
			if (pos != null) {
				TsAPO apo = pos.getApoSubset();
				TsEPO epo = pos.getEpoSubset();  
				TsSAP sap = pos.getSapSubset(); 
				TsADR consignee = pos.getConsigneeSubset();  
				TsADR finaluser = pos.getFinaluserSubset(); 
				List <TsACO> acoList = pos.getAcoList();
				List <TsACN> acnList = pos.getAcnList(); 
				List <TsAZV> azvList = pos.getAzvList();  
				List <TsAED> aedPosList = pos.getAedList();
				List <TsBZL> bzlList = pos.getBzlList();  
				List <TsBAV> bavList = pos.getBavList(); 								
			
				if (apo != null && !apo.isEmpty()) {
					res = appendString(res, apo.teilsatzBilden());
				}
				if (epo != null && !epo.isEmpty()) {
					res = appendString(res, epo.teilsatzBilden());
				}
				if (sap != null && !sap.isEmpty()) {
					res = appendString(res, sap.teilsatzBilden());
				}
				if (consignee != null && !consignee.isEmpty()) {
					res = appendString(res, consignee.teilsatzBilden());
				}
				if (finaluser != null && !finaluser.isEmpty()) {
					res = appendString(res, finaluser.teilsatzBilden());
				}
				if (acoList != null) {					
					for (TsACO aco : acoList) {						
						if (aco != null && !aco.isEmpty()) {
							res = appendString(res, aco.teilsatzBilden()); 
						}
					}
				}
				if (acnList != null) {					
					for (TsACN acn : acnList) {						
						if (acn != null && !acn.isEmpty()) {
							res = appendString(res, acn.teilsatzBilden());
						}
					}
				}       
				if (azvList != null) {					
					for (TsAZV azv : azvList) {						
						if (azv != null && !azv.isEmpty()) {
							res = appendString(res, azv.teilsatzBilden());
						}
					}
				}   				
				if (bzlList != null) {					
					for (TsBZL bzl : bzlList) {						
						if (bzl != null && !bzl.isEmpty()) {
							res = appendString(res, bzl.teilsatzBilden());
						}
					}
				}
				if (bavList != null) {					
					for (TsBAV bav : bavList) {						
						if (bav != null && !bav.isEmpty()) {
							res = appendString(res, bav.teilsatzBilden());
						}
					}
				}	
				if (aedPosList != null) {					
					for (TsAED aed : aedPosList) {						
						if (aed != null && !aed.isEmpty()) {
							res = appendString(res, aed.teilsatzBilden()); 
						}
					}
				}				
			}		
		}
		}
		if (adoList != null) {
			for (AdaAdo adaADO : adoList) {		
			if (adaADO != null) {				
				TsADO ado = adaADO.getAdoSubset(); 
				TsADR adr1 = adaADO.getAdrSubset1();	
				TsADR adr2 = adaADO.getAdrSubset2();	
				List<TsADB> adbList = adaADO.getAdbList(); 
				List<TsADD> addList = adaADO.getAddList(); 
				
				if (ado != null && !ado.isEmpty()) {
					res = appendString(res, ado.teilsatzBilden());
				}
				if (adr1 != null && !adr1.isEmpty()) {
					res = appendString(res, adr1.teilsatzBilden());
				}
				if (adr2 != null && !adr2.isEmpty()) {
					res = appendString(res, adr2.teilsatzBilden());
				}
				if (adbList != null) {					
					for (TsADB adb : adbList) {						
						if (adb != null && !adb.isEmpty()) {
							res = appendString(res, adb.teilsatzBilden()); 
						}
					}
				}	
				if (addList != null) {					
					for (TsADD add : addList) {						
						if (add != null && !add.isEmpty()) {
							res = appendString(res, add.teilsatzBilden()); 
						}
					}
				}	
			}			
			}
		}
		return res;
   }	

}

