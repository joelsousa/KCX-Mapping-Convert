package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.util.ArrayList;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsACP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsAAR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsACK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Exit/aes<br>
 * Created		: 29.07.2013<br>
 * Description	: V72 - FSS-Message AXT (KIDS-ExitControlNotification).
 *				: neue Nachricht in V72: EXIT Rückgabe Kontrollmaßnahme / ExitControlNotification
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgACR extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsAAR   aarSubset;  
	private TsACK   ackSubset;   
	private ArrayList <MsgACRPos>	posList;	
			
	public MsgACR(String outFileName) {
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
	
	public TsACK getAckSubset() {
		return ackSubset;
	}
	public void setAckSubset(TsACK ack) {
		this.ackSubset = ack;
	}
		
	public ArrayList<MsgACRPos> getPosList() {
		return this.posList;
	}
	public void setPosList(ArrayList<MsgACRPos> list) {
		this.posList = list;
	}
	public void addPosList(MsgACRPos pos) {
		if (pos == null) {
		    return;
		}
		if (posList == null) {
		    posList = new ArrayList<MsgACRPos>();
		}
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
		if (aarSubset != null && !aarSubset.isEmpty()) {			
			res = appendString(res, aarSubset.teilsatzBilden());
		}
		if (ackSubset != null && !ackSubset.isEmpty()) {			
			res = appendString(res, ackSubset.teilsatzBilden());
		}
		if (posList != null) {	       
			for (MsgACRPos pos :  posList) {
				if (pos != null) {
					TsAPR apr = pos.getAprSubset();
					TsACP acp = pos.getAcpSubset();  
					
					if (apr != null && !apr.isEmpty()) {
						res = appendString(res, apr.teilsatzBilden());
					}
					if (acp != null && !acp.isEmpty()) {
						res = appendString(res, acp.teilsatzBilden());
					}
				}
			}
		}
		
		Utils.log("(MsgACR EXIT = " + ackSubset.teilsatzBilden());
		
		return res;
	}	
}
