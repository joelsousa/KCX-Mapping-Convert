/*
 * Funktion    : MsgCAE.java
 * Titel       :
 * Erstellt    : 20.10.2008
 * Author      : Miro houdek
 * Beschreibung: cancellation response
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages;

import java.io.BufferedReader;
import java.io.IOException;


import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAE;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

public class MsgCAE {

	private TsVOR	vorSubset;
	private TsCAE   caeSubset;   

	public MsgCAE() {
		super();		
		
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	
	public TsCAE getCaeSubset() {
		return caeSubset;
	}
	public void setCaeSubset(TsCAE argument) {
		this.caeSubset = argument;
	}
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("CAE")) {
                	caeSubset = new TsCAE();            		
                    String[] cae = line.split("" + FssUtils.FSS_FS);
                    caeSubset.setFields(cae);                               
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


