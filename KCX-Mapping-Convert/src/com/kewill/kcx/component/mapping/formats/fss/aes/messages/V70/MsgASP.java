package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsASP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 31.09.2012<br>
 * Description	: Message ASP == KIDS:DeclarationResponse. 
 * 				: ZABIS V70: TsASP erweitert 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgASP {

	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20121128
	private TsASK   askSubset;
	private TsASP   aspSubset;   		
	private List    <TsAFP>afpList = null;
	
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
	
	public void setAfpList(List<TsAFP> list) {
		this.afpList = list;
	}	
	public List<TsAFP> getAfpList() {
		return afpList;
	
	}	
	public void addAfpList(TsAFP afp) {
		if (afpList == null) {
			afpList = new  Vector<TsAFP>();
		}
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
                    String[] ask = line.split("" + FssUtils.FSS_FS);
                    askSubset.setFields(ask);
                } else if (lineType.equalsIgnoreCase("ASP")) {
                    aspSubset = new TsASP();
                    String[] asp = line.split("" + FssUtils.FSS_FS);
                    aspSubset.setFields(asp);
                } else if (lineType.equalsIgnoreCase("AFP")) {
                	TsAFP afpSubset = new TsAFP();
                    String[] afp = line.split("" + FssUtils.FSS_FS);
                    afpSubset.setFields(afp);
                    addAfpList(afpSubset);
                    //afpList.add(afpSubset);
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
