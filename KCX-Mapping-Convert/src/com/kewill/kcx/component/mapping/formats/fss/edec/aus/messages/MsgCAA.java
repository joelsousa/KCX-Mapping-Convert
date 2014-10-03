/*
 * Funktion    : MsgADP.java
 * Titel       :
 * Erstellt    : 05.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

public class MsgCAA {

	private TsVOR	vorSubset;
	private TsCAA   caaSubset;   

	public MsgCAA() {
		super();
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsCAA getCaaSubset() {
		return caaSubset;
	}
	public void setCaaSubset(TsCAA argument) {
		this.caaSubset = argument;
	}
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("CAA")) {
                	caaSubset = new TsCAA();            		
                    String[] caa = line.split("" + FssUtils.FSS_FS);
                    caaSubset.setFields(caa);                               
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}


