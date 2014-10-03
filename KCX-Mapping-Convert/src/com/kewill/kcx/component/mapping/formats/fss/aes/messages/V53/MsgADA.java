
 /* Funktion   : MsgADA.java
 * Title       :
 * Date        : 14.10.2008
 * Author      : Kewill CSF / krzoska
 * Description : Creates output Kids to
 * 
 * Changes
 * -----------
 * Author      : EI
 * Date        : 14.05.2009
 * Label       : EI20090514
 * Description : writePrevious(...) war auskommentiert (!!!???)
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgADA<br>
 * Erstellt		: 14.10.2008<br>
 * Beschreibung	: Creates output Kids to.
 * 
 * @author krzoska
 * @version 5.3.00
 */
public class MsgADA extends FssDatei {
	private TsVOR	vorSubset;
 
	private TsEDA   edaSubset;
	private TsDAT   datSubset;
	private TsEAM   eamSubset;
	
	private TsADR   consignor; 
	private TsADR   consignee;
	private TsADR   declarant;
	private TsADR   agent; 
	private TsADR   subcontractor;
	private TsAVS   avsSubset;
	private List <TsAVS> avsList;
	
	private List <MsgADAPos> posList;
	
	public MsgADA(String outFileName) {
		super(outFileName);		
		posList = new Vector<MsgADAPos>();
	}	
	
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

	public TsADR getConsignor() {
		return consignor;
	}
	public void setConsignor(TsADR adr) {
		this.consignor = adr;
	}
	public void setConsignor(Party party, String typ, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consignor == null) {
			consignor = new TsADR();
		}
		consignor.setAdrSubset(party, typ, beznr, posnr);
	}
	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR adr) {
		this.consignee = adr;
	}
	public void setConsignee(Party party, String typ, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (consignee == null) {
			consignee = new TsADR();
		}
		consignee.setAdrSubset(party, typ, beznr, posnr);
	}
	
	public TsADR getAgent() {
		return agent;
	}
	public void setAgent(TsADR adr) {
		this.agent = adr;
	}
	public void setAgent(Party party, String typ, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (agent == null) {
			agent = new TsADR();
		}
		agent.setAdrSubset(party, typ, beznr, posnr);
	}
	
	public TsADR getDeclarant() {
		return declarant;
	}
	public void setDeclarant(TsADR adr) {
		this.declarant = adr;
	}
	public void setDeclarant(Party party, String typ, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (declarant == null) {
			declarant = new TsADR();
		}
		declarant.setAdrSubset(party, typ, beznr, posnr);
	}
	
	public TsADR getSubcontractor() {
		return subcontractor;
	}
	public void setSubcontractor(TsADR adr) {
		this.subcontractor = adr;
	}
	public void setSubcontractor(Party party, String typ, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (subcontractor == null) {
			subcontractor = new TsADR();
		}
		subcontractor.setAdrSubset(party, typ, beznr, posnr);
	}
	
/*	public void setAvsList(List<String> sealNumberList, String referenceNumber) {
		int size = 0;
		String seal = null;
		
		
		if ( sealNumberList != null ) {
			size = sealNumberList.size();
			avsList = new Vector <TsAVS>();
		
			for (int i = 0; i< size ; i++)  {
				seal = sealNumberList.get(i);
				
				this.avsList.add()
			}
		}
		this.avsList = createAVSList(sealNumberList, referenceNumber);
	}
*/
	public List<MsgADAPos> getPosList() {
		return posList;
	}	
	
	public void setPosList(List<MsgADAPos> argument) {
		this.posList = argument;
	}
	public void addPosList(MsgADAPos argument) {
		this.posList.add(argument);
	}

	
	
	public String writeADA() throws FssException {
		

		MsgADAPos pos = null;
		StringBuffer res = new StringBuffer();	
		TsAPO apo;
		TsEPO epo;
		
		/*

			VOR
				Hauptsatz 1.Teil DAT
				[ Hauptsatz 2.Teil EDA ]
					[ Adresssatz ADR ]
					[ ergaenzende Daten EAM ]
					[ Verschluesse AVS 99x]
					Positionssatz APO 999x
						[ Ergänzungssatz Position EPO ]
						[ 1. Packstuecke ACO ]
						.
						.
						[ 99. Packstuecke ACO ]
						[ Containerdaten ACN 99x]
						[ Vorpapiere AZV 9x]
						[ Unterlagen AED 99x]

		 */
		
		if (vorSubset != null) {
			res.append(vorSubset.teilsatzBilden() + Utils.LF);
		}
		if (datSubset != null) {
			res.append(datSubset.teilsatzBilden() + Utils.LF);
		}
		if (edaSubset != null) {
			res.append(edaSubset.teilsatzBilden() + Utils.LF); 
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
		if (agent != null && !agent.isEmpty()) {
			res.append(agent.teilsatzBilden() + Utils.LF);
		}
		if (subcontractor != null && !subcontractor.isEmpty()) {
			res.append(subcontractor.teilsatzBilden() + Utils.LF);
		}
		
		if (eamSubset != null) {
			res.append(eamSubset.teilsatzBilden() + Utils.LF);
		}
		
		
		if (avsList != null) {
			for (int i = 0, listSize = avsList.size(); i < listSize; i++) {
				avsSubset = avsList.get(i);
				res.append(avsSubset.teilsatzBilden() + Utils.LF);
			}
		}
 
					
		for (int i = 0; i < posList.size(); i++) {			
			pos = posList.get(i);
			
			apo = pos.getApoSubset();
			if (apo != null && !apo.isEmpty()) {
				res.append(apo.teilsatzBilden() + Utils.LF);
				epo = pos.getEpoSubset();
				if (epo != null) {
		    		res.append(epo.teilsatzBilden() + Utils.LF);
				}
				res.append(writeACOList(pos));
				res.append(writeACNList(pos));
				res.append(writeAZVList(pos));
				res.append(writeAEDList(pos));
			}
		}
		
		return res.toString(); 
	}
	
	private String writeACOList(MsgADAPos adapos) throws FssException {
		List <TsACO> acoList = adapos.getAcoList();
		TsACO aco = null;
		StringBuffer stb = new StringBuffer();
		
		if (acoList != null)  {
			for (int i = 0; i < acoList.size(); i++) {
				aco = acoList.get(i);
				if (!aco.isEmpty()) {
					stb.append(aco.teilsatzBilden() + Utils.LF);
				}
			}
		}
		
		return stb.toString();
		
	}
	
	private String writeACNList(MsgADAPos adapos) throws FssException {
		List <TsACN> acnList = adapos.getAcnList();
		TsACN acn = null;
		StringBuffer stb = new StringBuffer();
		
		if (acnList != null)  {
			for (int i = 0; i < acnList.size(); i++) {
				acn = acnList.get(i);
				if (!acn.isEmpty()) {
					stb.append(acn.teilsatzBilden() + Utils.LF);
				}
			}
		}
		return stb.toString();
	}
	
	private String writeAZVList(MsgADAPos adapos) throws FssException {
		List <TsAZV> azvList = adapos.getAzvList();
		TsAZV azv = null;
		StringBuffer stb = new StringBuffer();
		
		if (azvList != null)  {
			for (int i = 0; i < azvList.size(); i++) {
				azv = azvList.get(i);
				if (!azv.isEmpty()) {
					stb.append(azv.teilsatzBilden() + Utils.LF);
				}
			}
		}
		return stb.toString();
	}

	private String writeAEDList(MsgADAPos adapos) throws FssException {
		List <TsAED> aedList = adapos.getAedList();
		TsAED aed = null;
		StringBuffer stb = new StringBuffer();
		
		if (aedList != null)  {
			for (int i = 0; i < aedList.size(); i++) {
				aed = aedList.get(i);
				if (!aed.isEmpty()) {
					stb.append(aed.teilsatzBilden() + Utils.LF);
				}
			}
		}
		return stb.toString();
	}

	public TsEAM getEamSubset() {
		return eamSubset;
	}

	public void setEamSubset(TsEAM eamSubset) {
		this.eamSubset = eamSubset;
	}
	
	public List<TsAVS> getAvsList() {
		return avsList;
	}

	public void addAvsList(TsAVS avs) {
		if (avsList == null) { avsList = new Vector<TsAVS>(); }
		this.avsList.add(avs);
	}
}
