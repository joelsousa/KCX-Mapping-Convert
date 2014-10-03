package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: V70 - FSS-Message ADN (KIDS-Amendment).
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgADN extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsAAK   aakSubset;  
	private TsADR   declarantSubset; //new for V70 Beteiligtenangaben: Anmelder(3) == Declarant 
	private TsADR   agentSubset;	 //new for V70 Beteiligtenangaben: Vertreter des Anmelders(4) == Agent	
	private List<TsAAP> posList;
		
		
	
	public MsgADN(String outFileName) {
		super(outFileName);		
	
		vorSubset = new TsVOR("E");	
		headSubset = new TsHead("");
		aakSubset = new TsAAK();
		declarantSubset = new TsADR("3");
		agentSubset = new TsADR("4");
		posList = new Vector <TsAAP>();		
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
	
	public TsAAK getAakSubset() {
		return aakSubset;
	}
	public void setAakSubset(TsAAK argument) {
		this.aakSubset = argument;
	}
	
	public TsADR getDeclarantSubset() {
		return declarantSubset;
	}
	public void setDeclarantSubset(TsADR argument) {
		this.declarantSubset = argument;
	}
	
	public TsADR getAgentSubset() {
		return agentSubset;
	}
	public void setAgentSubset(TsADR argument) {
		this.agentSubset = argument;
	}
	
	public void addPosList(TsAAP pos) {
		this.posList.add(pos);
	}
	
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
		if (!aakSubset.isEmpty()) {
			res = appendString(res, aakSubset.teilsatzBilden());
		}
		if (!declarantSubset.isEmpty()) {
			res = appendString(res, declarantSubset.teilsatzBilden());
		}
		if (!agentSubset.isEmpty()) {
			res = appendString(res, agentSubset.teilsatzBilden());
		}
		for (TsAAP aapSubset : posList) {				
			if (!aapSubset.isEmpty()) {
				res = appendString(res, aapSubset.teilsatzBilden());	
			}
		}
		return res;
	}
		
}


