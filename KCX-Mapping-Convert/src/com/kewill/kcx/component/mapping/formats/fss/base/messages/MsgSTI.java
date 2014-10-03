package com.kewill.kcx.component.mapping.formats.fss.base.messages;

/*
 * Funktion    : MsgSTI.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : CSF GmbH / Heise
 * Beschreibung: STI (Internal Status Information)
 * 			   : V70 neu TsHead
 * Rückgabe    : keine
 */

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSTI;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsANR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

public class MsgSTI {

	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20121128
	private TsSTI   stiSubset;
	// laut Basisdoku gibt es immer nur einen Teilsatz ANR
	private TsANR	anrSubset;
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
	
    public TsSTI getStiSubset() {
		return stiSubset;
	}
	public void setStiSubset(TsSTI stiSubset) {
		this.stiSubset = stiSubset;
	}
	
	public TsANR getAnrSubset() {
		return anrSubset;
	}
	public void setAnrSubset(TsANR anrSubset) {
		this.anrSubset = anrSubset;
	}

	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("STI")) {
                	stiSubset = new TsSTI();
                    String[] sti = line.split("" + FssUtils.FSS_FS);
                    stiSubset.setFields(sti);
                } else if (lineType.equalsIgnoreCase("ANR")) {
                    anrSubset = new TsANR();
                    String[] anr = line.split("" + FssUtils.FSS_FS);
                    anrSubset.setFields(anr);
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
