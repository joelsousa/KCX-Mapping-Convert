/*
 * Modul    	: KidsToUids.java
 * Titel       	:
 * Date        	: 17.10.2008
 * @author      : Kewill CSF / Christine Kron
 * Description 	: Contains the Message ExportDeclaration in the ZABIS FSS-Format  
 * 			    : Version 6.0
 * Parameters  	:
 *  
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 05.05.2009
 * Label       : EI20090505
 * Description : removed argument "typ" from setConsignee(...) aso.
 *
 * Author      : EI
 * Date        : 14.08.2009
 * Label       : EI20090814
 * Description : BAV, BZL 
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsABF;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Modul		: MsgADA<br>
 * Erstellt		: 12.05.2009<br>
 * Beschreibung	: .
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MsgADA extends FssMessage60 {

	private TsVOR				vorSubset;
	private TsDAT   			datSubset;   
	private TsEDA   			edaSubset;
	private TsSAS 				sasSubset;
	private List <TsABF> 		abfList;
	private TsATK 				atkSubset;	
	
	private TsADR   			consignorSubset; 
	private TsADR   			consigneeSubset;
	private TsADR   			declarantSubset;
	private TsADR   			agentSubset; 
	private TsADR   			subcontractorSubset;
	
	private TsEAM   			eamSubset;
	private List <TsAVS> 		avsList;	
	private TsAED 				aedSubset;			
	private List <TsAED> 		aedList;    //AK20090415
	
	private List <MsgADAPos>	posList;
	
	//EI20090427: private List<TsADO> 		adoList;
	private TsADO               adoSubset;  //EI20090427
	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
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

	public TsADR getconsignorSubset() {
		return consignorSubset;
	}
	public void setconsignorSubset(TsADR adr) {
		this.consignorSubset = adr;
	}
	public void setConsignor(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consignorSubset == null) {
			consignorSubset = new TsADR();
		}
		consignorSubset.setAdrSubset(party, "1", beznr, posnr);
	}	
	
	public TsADR getconsigneeSubset() {
		return consigneeSubset;
	}
	public void setconsigneeSubset(TsADR adr) {
		this.consigneeSubset = adr;
	}
	
	//AK20090409
	public void setConsignee(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consigneeSubset == null) {
			consigneeSubset = new TsADR();
		}
		consigneeSubset.setAdrSubset(party, "2", beznr, posnr);
	}
	
	public TsADR getagentSubset() {
		return agentSubset;
	}
	public void setagentSubset(TsADR adr) {
		this.agentSubset = adr;
	}
	public void setAgent(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (agentSubset == null) {
			agentSubset = new TsADR();
		}
		agentSubset.setAdrSubset(party, "4", beznr, posnr);
	}

	public TsADR geteclarantSubset() {
		return declarantSubset;
	}
	public void seteclarantSubset(TsADR adr) {
		this.declarantSubset = adr;
	}
	public void setDeclarant(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (declarantSubset == null) {
			declarantSubset = new TsADR();
		}
		declarantSubset.setAdrSubset(party, "3", beznr, posnr);
	}

	public TsADR getsubcontractorSubset() {
		return subcontractorSubset;
	}
	public void setsubcontractorSubset(TsADR adr) {
		this.subcontractorSubset = adr;
	}
	public void setSubcontractor(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (subcontractorSubset == null) {
			subcontractorSubset = new TsADR();
		}
		subcontractorSubset.setAdrSubset(party, "5", beznr, posnr);
	}
	
	public List<MsgADAPos> getPosList() {
		return posList;
	}	
	
	public void setPosList(List<MsgADAPos> argument) {
		this.posList = argument;
	}
	public void addPosList(MsgADAPos argument) {
		if (argument == null) {
		    return;
		}
		if (posList == null) {
		    posList = new Vector<MsgADAPos>();
		}
		this.posList.add(argument);
	}
	
    /* Struktur der Nachricht DAT
     * 
     * Struktur der Nachricht:
Vorlaufsatz VOR
Hauptsatz 1.Teil DAT
    [ Hauptsatz 2.Teil EDA ]
    [ Teilsatz "save and security" SAS ]
    [ Beförderungsroute ABF 99x ]
    [ Ausfuhrerstattungen ATK ]
    [ Adresssatz ADR ]
    [ ergänzende Daten EAM ]
    [ Verschlüsse AVS 99x ]
    [ Unterlagen AED ]
    Positionssatz APO 999x
        [ Ergänzungssatz Position EPO ]
        [ Teilsatz "save and security" SAP ]
        [ Adresssatz ADR ]
        [ Ausfuhrerstattung ATP ]
            [ Ausfuhrerstattung Inhaltsstoff ATI 99x ]
        [ 1. Packstücke ACO 99x ]
            [ Packstückidentifizierung NVE 99999x]
            .
            .
        [ 99. Packstücke ACO ]
            [ Packstückidentifizierung NVE ]
            [ Packstückidentifizierung NVE ]
        [ Containerdaten ACN 99x ]
        [ Vorpapiere AZV 9x ]
        [ Beendigungsanteil ZL BZL 999x ]
        [ Beendigungsanteil AV/UV BAV 999x ]
        [ Unterlagen AED 99x ]
    [1. Dokumentenkopf ADO ]
        [ Adresssatz ADR 2x ]
        [ Bemerkungen ADB 6x ]
        [ 1. Dokumentenposition ADD ]
        .
        .
        [ 28/23. Dokumentenposition ADD ]
    .
    .
    [ n. Dokumentenkopf ADO ]
        [ Adresssatz ADR 2x ]
        [ Bemerkungen ADB 6x ]
        [ Dokumentenposition ADD ]

    */
	public String getFssString() throws FssException {
		String res = "";
		MsgADAPos pos = null;
		TsAPO apo = null;
		TsEPO epo = null;    //EI20090429
		TsSAP sap = null;    //EI20090429
		TsADR cons = null;    //EI20090429
		TsATP atp = null;
		TsACO aco = null;
		List <TsACO> acoList;
		TsACN acn = null;         //EI20090429
		List <TsACN> acnList;     //EI20090429
		TsAZV azv = null;         //EI20090429
		List <TsAZV> azvList;     //EI20090429
		TsAED aedPos = null;      //EI20090429
		List <TsAED> aedPosList;  //EI20090429

		TsBZL bzl = null;         //EI20090814
		List <TsBZL> bzlList;     //EI20090814
		TsBAV bav = null;         //EI20090814
		List <TsBAV> bavList;     //EI20090814
		
		if (vorSubset != null && !vorSubset.isEmpty()) {
			res = appendString(res, vorSubset.teilsatzBilden());
		}
		if (datSubset != null && !datSubset.isEmpty()) {
			res = appendString(res, datSubset.teilsatzBilden());
		}
		if (edaSubset != null && !edaSubset.isEmpty()) {
			res = appendString(res, edaSubset.teilsatzBilden());
		}
		if (sasSubset != null && !sasSubset.isEmpty()) {
			res = appendString(res, sasSubset.teilsatzBilden());
		}
		if (abfList != null) {                     					 //EI20090427
			int abfSize = abfList.size();
			for (int j = 0; j < abfSize; j++) {
				TsABF abf = (TsABF) abfList.get(j);
				if (abf != null && !abf.isEmpty()) {
					res = appendString(res, abf.teilsatzBilden());
				}
			}
		}		
		if (atkSubset != null && !atkSubset.isEmpty()) {
			res = appendString(res, atkSubset.teilsatzBilden());
		}
		if (consignorSubset != null && !consignorSubset.isEmpty()) {
			res = appendString(res, consignorSubset.teilsatzBilden());
		}
		if (consigneeSubset != null && !consigneeSubset.isEmpty()) {
			res = appendString(res, consigneeSubset.teilsatzBilden());
		}
		if (declarantSubset != null && !declarantSubset.isEmpty()) {
			res = appendString(res, declarantSubset.teilsatzBilden());
		}
		if (agentSubset != null && !agentSubset.isEmpty()) {
			res = appendString(res, agentSubset.teilsatzBilden());
		}
		if (subcontractorSubset != null && !subcontractorSubset.isEmpty()) {
			res = appendString(res, subcontractorSubset.teilsatzBilden());
		}
		
		if (eamSubset != null && !eamSubset.isEmpty()) {
			res = appendString(res, eamSubset.teilsatzBilden());
		}
		if (avsList != null) {                     					 //EI20090427
			int avsSize = avsList.size();
			for (int j = 0; j < avsSize; j++) {
				TsAVS avs = (TsAVS) avsList.get(j);
				if (avs != null && !avs.isEmpty()) {
					res = appendString(res, avs.teilsatzBilden());
				}
			}
		}			
		//if(aedSubset != null && !aedSubset.isEmpty())                //EI20090427
		//	res = appendString(res, aedSubset.teilsatzBilden());
		if (aedList != null) {                     					   //EI20090427
			int aedSize = aedList.size();
			for (int j = 0; j < aedSize; j++) {
				TsAED aed = (TsAED) aedList.get(j);
				if (aed != null && !aed.isEmpty()) {
					res = appendString(res, aed.teilsatzBilden());
				}
			}
		}	

		int posSize = 0;
		if (posList != null) {
	        posSize = posList.size();
		}
		for (int i = 0; i < posSize; i++) {			
			pos = posList.get(i);
			
			apo = pos.getApoSubset();
			epo = pos.getEpoSubset();  //EI20090429
			sap = pos.getSapSubset();  //EI20090429
			cons = pos.getAdrSubset();  //EI20090429
			atp = pos.getAtpSubset();
			
			if (apo != null && !apo.isEmpty()) {
				res = appendString(res, apo.teilsatzBilden());
			}
			if (epo != null && !epo.isEmpty()) {
				res = appendString(res, epo.teilsatzBilden());
			}
			if (sap != null && !sap.isEmpty()) {
				res = appendString(res, sap.teilsatzBilden());
			}
			if (cons != null && !cons.isEmpty()) {
				res = appendString(res, cons.teilsatzBilden());
			}
			if (atp != null && !atp.isEmpty()) {
				res = appendString(res, atp.teilsatzBilden());
			}
					
			acoList = pos.getAcoList();
			if (acoList != null) {
				int acoSize = acoList.size();
				for (int j = 0; j < acoSize; j++) {
					aco = (TsACO) acoList.get(j);
					if (aco != null && !aco.isEmpty()) {
						res = appendString(res, aco.teilsatzBilden()); 
					}
				}
			}
			
			acnList = pos.getAcnList();
			if (acnList != null) {
				int acnSize = acnList.size();
				for (int j = 0; j < acnSize; j++) {
					acn = (TsACN) acnList.get(j);
					if (acn != null && !acn.isEmpty()) {
						res = appendString(res, acn.teilsatzBilden());
					}
				}
			}
			
			azvList = pos.getAzvList();
			if (azvList != null) {
				int azvSize = azvList.size();
				for (int j = 0; j < azvSize; j++) {
					azv = (TsAZV) azvList.get(j);
					if (azv != null && !azv.isEmpty()) {
						res = appendString(res, azv.teilsatzBilden());
					}
				}
			}
			
			//EI20090814 beginn
			bzlList = pos.getBzlList();
			if (bzlList != null) {
				int bzlSize = bzlList.size();
				for (int j = 0; j < bzlSize; j++) {
					bzl = (TsBZL) bzlList.get(j);
					if (bzl != null && !bzl.isEmpty()) {
						res = appendString(res, bzl.teilsatzBilden());
					}
				}
			}
			bavList = pos.getBavList();
			if (bavList != null) {
				int bavSize = bavList.size();
				for (int j = 0; j < bavSize; j++) {
					bav = (TsBAV) bavList.get(j);
					if (bav != null && !bav.isEmpty()) {
						res = appendString(res, bav.teilsatzBilden());
					}
				}
			}			
			//EI20090814 end  
			
			aedPosList = pos.getAedList();
			if (aedPosList != null) {
				int aedSize = aedPosList.size();
				for (int j = 0; j < aedSize; j++) {
					aedPos = (TsAED) aedPosList.get(j);
					if (aedPos != null && !aedPos.isEmpty()) {
						res = appendString(res, aedPos.teilsatzBilden()); 
					}
				}
			}	
		}		
		
		if (adoSubset != null && !adoSubset.isEmpty()) {
			res = appendString(res, adoSubset.teilsatzBilden());
		}
		return res;
   }	
	
	//
	public TsEAM getEamSubset() {
		return eamSubset;
	}
	public void setEamSubset(TsEAM argument) {		
		this.eamSubset = argument;
	}
	
	public List<TsAVS> getAvsList() {
		return avsList;
	
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
	
	public void setSasSubset(TsSAS tsSAS) {
		this.sasSubset = tsSAS;
	}
	public TsSAS getSasSubset() {
		return sasSubset;
	}
	
	public TsATK getAtkSubset() {
		return atkSubset;
	}
	public void setAtkSubset(TsATK atkSubset) {
		this.atkSubset = atkSubset;
	}
	
	public TsAED getAedSubset() {
		return aedSubset;
	}
	public void setAedSubset(TsAED aedSubset) {
		this.aedSubset = aedSubset;
	}
	
	//AK20090415
	public List<TsAED> getAedList() {
		return aedList;
	}
	//AK20090415
	public void setAedList(List<TsAED> aedList) {
		this.aedList = aedList;
	}
	//AK20090415
	public void addAedList(TsAED aed) {
		if (aed == null) {
			return;
		}
		if (aedList == null) {
			aedList = new Vector<TsAED>();
		}
		this.aedList.add(aed);
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
	/* EI20090427:
	public List<TsADO> getAdoList() {
		return adoList;
	}
	public void setAdoList(List<TsADO> adoList) {
		this.adoList = adoList;
	}
	public void addAdoList(TsADO ado) {
		if (ado == null) 
			return;
		if (adoList == null)  
			adoList = new Vector<TsADO>();			
		this.adoList.add(ado);
	}
	*/
	public void setAvsList(List<TsAVS> avsList) {
		this.avsList = avsList;
	}
}

