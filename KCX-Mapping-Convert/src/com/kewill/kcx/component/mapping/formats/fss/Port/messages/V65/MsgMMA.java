package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsMIK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Module       : Port<br>
 * Created      : 24.10.2011<br>
 * Description	: Mindermengen Anmeldung.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgMMA extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsMIK	mikSubset;

	public MsgMMA() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR subset) {
		this.vorSubset = subset;
	}
    
	public TsMIK getMikSubset() {
		return mikSubset;
	}
	public void setMikSubset(TsMIK subset) {
		this.mikSubset = subset;
	}
	
	public String getFssString() throws FssException {
		String res = "";	
		
		if (vorSubset != null && !vorSubset.isEmpty()) {	 
			res = appendString(res, vorSubset.teilsatzBilden());	
		}
		if (mikSubset != null && !mikSubset.isEmpty()) {	 
			res = appendString(res, mikSubset.teilsatzBilden());	
		}
		
		return res;
	}
    public void readMessage(BufferedReader message) throws FssException {
    	//z.Z. nicht benoetigt:	 
    }

}

