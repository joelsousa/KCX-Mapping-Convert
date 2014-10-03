/*
 * Function    : MsgADI.java
 * Date        : 02.10.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : created
 * ----------------------------------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 10.03.2009
 * Label       : EI20090310
 * Description : V60 checked
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;

public class MsgADI extends FssDatei {

	private TsVOR	vorSubset;
	private TsDAT   datSubset;   
	private TsEDA   edaSubset;
	private TsATK   atkSubset;	
	private TsADR   adrSubset;
	private TsADR   consignor; 
	private TsADR   consignee;
	private TsADR   declarant;
	private TsADR   agent; 
	private TsADR   subcontractor;
	//private TsADR   forwarder;  wird nicht gebraucht
	
	private List   <MsgADIPos>posList;	
	private MsgADIPos adiPos;	
	
	private TsAPO   apoSubset;
	private TsATP   atpSubset;
	private TsACO   acoSubset;
			
	public MsgADI(String outFileName) {
		super(outFileName);		
		
		vorSubset = new TsVOR("");
		datSubset = new TsDAT();
		edaSubset = new TsEDA();
		atkSubset = new TsATK();
		consignor = new TsADR();
		consignee = new TsADR();
		agent = new TsADR();
		declarant = new TsADR();
		subcontractor = new TsADR();
		//forwarder = new TsADR();
		posList = new Vector<MsgADIPos>();		
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
	
	public TsATK getAtkSubset() {
		return atkSubset;
	}
	public void setAtkSubset(TsATK atk) {
		this.atkSubset = atk;
	}

	public TsADR getConsignor() {
		return consignor;
	}
	public void setConsignor(TsADR adr) {
		this.consignor = adr;
	}
	public void setConsignor(Party party, String beznr) {
		//EI20120801 : ueberall stand hier consignee
		if (consignor == null)
			consignor = new TsADR();	
		consignor.setAdrSubset(party, "1", beznr, "0");
	}
	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR adr) {
		this.consignee = adr;
	}
	public void setConsignee(Party party, String beznr) {
		if (consignee == null)
			consignee = new TsADR();	
		consignee.setAdrSubset(party, "2", beznr, "0");
	}
	
	public TsADR getAgent() {
		return agent;
	}
	public void setAgent(TsADR adr) {
		this.agent = adr;
	}
	public void setAgent(Party party, String beznr) {
		//EI20120801 : ueberall stand hier consignee
		if (agent == null)
			agent = new TsADR();	
		agent.setAdrSubset(party, "4", beznr, "0");
	}
	
	public TsADR getDeclarant() {
		return declarant;
	}
	public void setDeclarant(TsADR adr) {
		this.declarant = adr;
	}
	public void setDeclarant(Party party, String beznr) {
		//EI20120801 : ueberall stand hier consignee
		if (declarant == null)
			declarant = new TsADR();	
		declarant.setAdrSubset(party, "3", beznr, "0");
	}
	
	public TsADR getSubcontractor() {
		return subcontractor;
	}
	public void setSubcontractor(TsADR adr) {
		this.subcontractor = adr;
	}
	public void setSubcontractor(Party party, String beznr) {
		//EI20120801 : ueberall stand hier consignee
		if (subcontractor == null)
			subcontractor = new TsADR();	
		subcontractor.setAdrSubset(party, "5", beznr, "0");
	}
		
	public List<MsgADIPos> getPosList() {
		return posList;
	}	
	
	public void setPosList(List argument) {
		this.posList = argument;
	}
	public void addPosList(MsgADIPos argument) {
		this.posList.add(argument);
	}

	public StringBuffer writeADI() throws FssException {
		StringBuffer res = new StringBuffer();		
		String s  = "";
		
		if(vorSubset != null && !vorSubset.isEmpty()) 		 
			res = appendString(res, vorSubset.teilsatzBilden());	
		if(datSubset != null && !datSubset.isEmpty())			
			res = appendString(res, datSubset.teilsatzBilden());
		if(edaSubset != null && !edaSubset.isEmpty())
			res = appendString(res, edaSubset.teilsatzBilden());
		if(atkSubset != null && !atkSubset.isEmpty()) 						
			res = appendString(res, atkSubset.teilsatzBilden());
	
		if(consignor != null && !consignor.isEmpty())
			res = appendString(res, consignor.teilsatzBilden());
		if(consignee != null && !consignee.isEmpty())
			res = appendString(res, consignee.teilsatzBilden());
		if(declarant != null && !declarant.isEmpty())
			res = appendString(res, declarant.teilsatzBilden());
		if(agent != null && !agent.isEmpty())
			res = appendString(res, agent.teilsatzBilden());
		if( subcontractor != null && !subcontractor.isEmpty())
			res = appendString(res, subcontractor.teilsatzBilden());
					
		for (int i = 0; i < posList.size(); i++) {			
			MsgADIPos tmpPos = new MsgADIPos();
			tmpPos = posList.get(i);
			if( tmpPos.getApoSubset() != null && !tmpPos.getApoSubset().isEmpty())
				res = appendString(res, tmpPos.getApoSubset().teilsatzBilden());
			if( tmpPos.getAtpSubset() != null && !tmpPos.getAtpSubset().isEmpty())
				res = appendString(res, tmpPos.getAtpSubset().teilsatzBilden());				
													
			for (int j = 0; j < tmpPos.getAcoList().size(); j++) {
				TsACO tmpACO = new TsACO();
				tmpACO = (TsACO)tmpPos.getAcoList().get(j);
				if( tmpACO != null && !tmpACO.isEmpty())								
					res = appendString(res, tmpACO.teilsatzBilden());					
			}
		}		
		
		return res;
	}
	
	private StringBuffer appendString ( StringBuffer in, String appendStr) throws FssException {			
		StringBuffer stb = new StringBuffer();
		
		stb.append(in);
		stb.append(appendStr);
		stb.append(Utils.LF);
		return stb;
	}
}


