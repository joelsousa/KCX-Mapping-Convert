package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53;

/*
 * Funktion    : MsgASP.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : CSF GmbH / Alfred Krzoska
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * R�ckgabe    : keine
 * Aufruf      : 
 *
 * �nderungen:
 * -----------
 * Author      : krzoska
 * Datum       : 14.11.2008
 * Kennzeichen : AK20081114
 * Beschreibung: NAC �berlesen
 * Anmerkungen :
 * Parameter   :
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAFP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsASK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsASP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgASP<br>
 * Erstellt		: 03.09.2008<br>
 * Beschreibung	: -.
 * 
 * @author Krzoska
 * @version 5.3.00
 */
public class MsgASP {

	private TsVOR	vorSubset;
	private TsASP   aspSubset;   
	private TsASK   askSubset;
	private TsAFP   afpSubset;
	private List    <TsAFP>afpList;	
	
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
		this.afpList.add(afp);
	}	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("ASK")) {
                	askSubset = new TsASK();
            		afpList   = new Vector<TsAFP>();
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
                    afpList.add(afpSubset);
                } else if (lineType.equalsIgnoreCase("NAC")) {   //AK20081114
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
