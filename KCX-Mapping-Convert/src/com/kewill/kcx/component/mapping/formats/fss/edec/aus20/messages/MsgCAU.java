package com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages;

import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAU;

/**
 * Module        :   EDEC Export 70 
 * Created       :   07.11.2012
 * Description   :   MsgCAU Cancellation.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class MsgCAU extends FssMessage60 {

	private TsVOR	vorSubset;
	private TsHead	headSubset;  //EI20120926
	private TsCAU   cauSubset;

	public MsgCAU() {
		cauSubset = new TsCAU();
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
	
	public TsCAU getCauSubset() {
		return cauSubset;
	}

	public void setCauSubset(TsCAU cauSubset) {
		this.cauSubset = cauSubset;
	}
	
	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException { //EI20121107
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
		if (cauSubset != null && !cauSubset.isEmpty()) {		
			res = appendString(res, cauSubset.teilsatzBilden());
		}
		return res;
	}
	
}
