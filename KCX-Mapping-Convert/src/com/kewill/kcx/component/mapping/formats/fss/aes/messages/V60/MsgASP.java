package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

/*
 * Funktion    : MsgASP.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : CSF GmbH / Alfred Krzoska
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

public class MsgASP {

	private TsVOR	vorSubset;
	private TsASP   aspSubset;   
	private TsASK   askSubset;
	private TsAFP   afpSubset;
	private List    <TsAFP>afpList = null;
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}

	public TsASK getAskSubset() {
		return askSubset;
	}
	public void setAskSubset(TsASK askSubset) {
		this.askSubset = askSubset;
	}
	
	public TsASP getAspSubset() {
		return aspSubset;
	}
	public void setAspSubset(TsASP aspSubset) {
		this.aspSubset = aspSubset;
	}
	public TsAFP getAfpSubset() {
		return afpSubset;
	}
	public void setAfpSubset(TsAFP afpSubset) {
		this.afpSubset = afpSubset;
	}
	
	public List<TsAFP> getAfpList() {
		return afpList;
	
	}
	
	public void addAfpList(TsAFP afp) {
		if (afpList == null)
			afpList = new  Vector<TsAFP>();
		this.afpList.add(afp);
	}	

	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("ASK")) {
                	askSubset = new TsASK();
                    String[] ask = line.split("" + FssUtils.FSS_FS);
                    askSubset.setFields(ask);
                } else if (lineType.equalsIgnoreCase("ASP")) {
                    aspSubset = new TsASP();
                    String[] asp = line.split("" + FssUtils.FSS_FS);
                    aspSubset.setFields(asp);
                } else if (lineType.equalsIgnoreCase("AFP")) {
                    afpSubset = new TsAFP();
                    String[] afp = line.split("" + FssUtils.FSS_FS);
                    afpSubset.setFields(afp);
                    addAfpList(afpSubset);
                    //afpList.add(afpSubset);
                } else if (lineType.equalsIgnoreCase("NAC")) {    //AK20081117
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
