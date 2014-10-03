package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsACP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPR;

/**
 * Module		:	Export/aes
 * Created		:	19.07.2013
 * Description	:	MsgPos.
 * 				: 	neue Msg EXIT Rückgabe Kontrollmaßnahme / ControlNotification
 *
 * @author 	iwaniuk
 * @version 2.2.00 (Zabis V72)
 */

public class MsgACRPos  {
	private TsAPR   aprSubset; 			
	private TsACP   acpSubset;	
	

	public MsgACRPos() {
	
	}

	public TsAPR getAprSubset() {
		return aprSubset;
	}
	public void setAprSubset(TsAPR aprSubset) {
		this.aprSubset = aprSubset;
	}

	public TsACP getAcpSubset() {
		return acpSubset;
	}
	public void setAcpSubset(TsACP acpSubset) {
		this.acpSubset = acpSubset;
	}	
}	





