package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsAUP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2013<br>
 * Description	: Message AUP.
 * 				: V72: neu in TsAUP: artwvl, svldat
 *   
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgAUP {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20121128
	private TsAUP   aupSubset;   

	
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
    
	public void setAUP(TsAUP aup) {
		aupSubset = aup;
    }
	public TsAUP getAupSubset() {
		return aupSubset;
	}

    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AUP")) {
                	aupSubset = new TsAUP();
                    String[] aup = line.split("" + FssUtils.FSS_FS);
                    aupSubset.setFields(aup);
                } else if (lineType.equalsIgnoreCase("NAC")) {
                	// Nachlaufsatz NAC nicht verarbeiten
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
