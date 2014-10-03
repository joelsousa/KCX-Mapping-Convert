package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 31.07.2012<br>
 * Description	: V70 - FSS-Message ADI (KIDS-PreNotification).
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgADI extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsDAT   datSubset;   
	private TsEDA   edaSubset;
	private TsATK   atkSubset;		
	private TsADR   consignorSubset; //ausfuehrer
	private TsAPN   consignorContact; 	
	private TsADR   consigneeSubset; //empfaenger
	//private TsAPN   consigneeContact;
	private TsADR   declarantSubset;  //anmelder
	private TsAPN   declarantContact;
	private TsADR   agentSubset;      //vertreter
	private TsAPN   agentContact;
	private TsADR   subcontractorSubset;  //subunternehmer
	private TsAPN   subcontractorContact;	
	
	private List   <MsgADIPos>posList;	
	private MsgADIPos adiPos;	
		
	public MsgADI(String outFileName) {
		super(outFileName);		
		
		vorSubset = new TsVOR("");
		headSubset = new TsHead("");
		datSubset = new TsDAT();
		edaSubset = new TsEDA();
		atkSubset = new TsATK();
		consignorSubset = new TsADR();
		consigneeSubset = new TsADR();
		agentSubset = new TsADR();
		declarantSubset = new TsADR();
		subcontractorSubset = new TsADR();		
		posList = new Vector<MsgADIPos>();		
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
	
	public TsATK getAtkSubset() {
		return atkSubset;
	}
	public void setAtkSubset(TsATK atk) {
		this.atkSubset = atk;
	}

	public TsADR getConsignorSubset() {
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
		if (consignorContact != null) {
			consignorContact.setApnSubset(party, "1", beznr, posnr);
		}
	}	
	
	public TsADR getConsigneeSubset() {
		return consigneeSubset;
	}
	public void setConsigneeSubset(TsADR adr) {
		this.consigneeSubset = adr;
	}
	public void setConsignee(Party party, String beznr, String posnr) {
		if (consigneeSubset == null) {
			consigneeSubset = new TsADR();	
		}
		consigneeSubset.setAdrSubset(party, "2", beznr, "0");
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
		if (agentContact != null) {
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
		if (declarantContact != null) {
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
		if (subcontractorContact != null) {
			subcontractorContact.setApnSubset(party, "5", beznr, posnr);
		}
	}
	
	public List<MsgADIPos> getPosList() {
		return posList;
	}	
	
	public void setPosList(List<MsgADIPos> list) {
		this.posList = list;
	}
	public void addPosList(MsgADIPos argument) {
		this.posList.add(argument);
	}

	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException {
		String res = "";		
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			headSubset.mapVor2Head(vorSubset);
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
		if (atkSubset != null && !atkSubset.isEmpty()) {					
			res = appendString(res, atkSubset.teilsatzBilden());
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
		if (posList != null) {		
			for (MsgADIPos pos : posList) {						
			if (pos != null) {
				if (pos.getApoSubset() != null && !pos.getApoSubset().isEmpty()) {
					res = appendString(res, pos.getApoSubset().teilsatzBilden());
				}
				if (pos.getAtpSubset() != null && !pos.getAtpSubset().isEmpty()) {
					res = appendString(res, pos.getAtpSubset().teilsatzBilden());				
				}	
				if (pos.getAtiList() != null) {
					for (TsATI ati : pos.getAtiList()) {				
					if (ati != null && !ati.isEmpty())	{							
						res = appendString(res, ati.teilsatzBilden());
					}
					}
				}
				if (pos.getConsigneeSubset() != null && !pos.getConsigneeSubset().isEmpty()) {
					res = appendString(res, pos.getConsigneeSubset().teilsatzBilden());
				}
				if (pos.getAcoList() != null) {
					for (TsACO aco : pos.getAcoList()) {				
						if (aco != null && !aco.isEmpty())	{							
							res = appendString(res, aco.teilsatzBilden());
						}
					}
				}
				if (pos.getAedList() != null) {
					for (TsAED aed : pos.getAedList()) {				
						if (aed != null && !aed.isEmpty())	{							
							res = appendString(res, aed.teilsatzBilden());
						}
					}
				}
				
			}
			}
		}
		return res;
	}
		
}


