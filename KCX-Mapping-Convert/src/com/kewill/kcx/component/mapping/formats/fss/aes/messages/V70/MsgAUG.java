package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAUG;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsOldAUG;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 31.09.2012<br>
 * Description	: Message AUG Rueckgabe der Anmahnung. 
 * 				: neu in V70
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgAUG {
	
	private TsVOR	 vorSubset;
	private TsHead	 headSubset;     //EI20121128
	private TsOldAUG augOldSubset; 
	private TsAUG    augSubset;  
	private List     <TsADR>adrList;  //ausfuehrer, anmelder, vertreter, subunternehmer
	
	private boolean augOld = false; 
	
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
	
	public void setAug(TsAUG subset) {
		augSubset = subset;
    }
	public TsAUG getAugSubset() {
		return augSubset;
	}

	public List<TsADR> getAdrList() {
		return adrList;
	}	
	public void setAdrList(List<TsADR> list) {
		this.adrList = list;
	}
	public void addAdrList(TsADR adr) {
		if (adrList == null) {
			adrList = new Vector<TsADR>();
		}
		this.adrList.add(adr);
	}
	   
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            adrList = new Vector<TsADR>();

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                if (line.length() > 3) {
            		lineType = line.substring(0, 3);
            	}
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AUG")) {
                	augSubset = new TsAUG();
                	String[] aug = line.split("" + FssUtils.FSS_FS);
                	int len = aug.length;                	
                	if (len < 6) {   //EI20121009 die reihenfolge der if/else umgetauscht, damit ev. auch später OK ist
                		augOldSubset = new TsOldAUG();
                		augOldSubset.setFields(aug);
                		augSubset.setBeznr(augOldSubset.getBeznr());
                		augSubset.setSawdat(augOldSubset.getSawdat());
                		augSubset.setAnmdat(augOldSubset.getAnmdat());                	
                	} else {
                		augSubset.setFields(aug);  
                	}
                    
                } else if (lineType.equalsIgnoreCase("ADR")) {
                    TsADR adrSubset = new TsADR();
                    String[] adr = line.split("" + FssUtils.FSS_FS);
                    adrSubset.setFields(adr); 
                    addAdrList(adrSubset);  
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
