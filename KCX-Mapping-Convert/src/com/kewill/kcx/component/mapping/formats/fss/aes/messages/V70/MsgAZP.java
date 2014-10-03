package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 27.08.2008<br>
 * Description	: Message AZP .
 * 				: ZABIS V70: neu TsHead
 * 
 * TODO: kopiert von V60 - noch nicht in die Map-Klassen eingebunden (ist aber schon im V60 mit Head ausgestattet)
 *  
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgAZP {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20121128
	private TsAFK   afkSubset;
	private TsAFP[] afpSubset;
	private int anzAFP = 0;
	
	public MsgAZP() {
		afpSubset = new TsAFP[999];
	}
	
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            TsAFP tmp = null;

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AFK")) {
                	afkSubset = new TsAFK();
                    String[] afk = line.split("" + FssUtils.FSS_FS);
                    afkSubset.setFields(afk);
                } else if (lineType.equalsIgnoreCase("AFP")) {
                	tmp = new TsAFP();
                    String[] afp = line.split("" + FssUtils.FSS_FS);
                    tmp.setFields(afp);
                    addAfpSubset(tmp);
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
	
	public TsAFK getAfkSubset() {
		return afkSubset;
	}

	public void setAfkSubset(TsAFK afkSubset) {
		this.afkSubset = afkSubset;
	}

	public TsAFP[] getAfpSubsets() {
		return afpSubset;
	}

	public void addAfpSubset(TsAFP afp) {
		this.afpSubset[anzAFP] = afp;
		anzAFP++;
	}
    
    public int getAnzAFP() {
    	return anzAFP;
    }
}
