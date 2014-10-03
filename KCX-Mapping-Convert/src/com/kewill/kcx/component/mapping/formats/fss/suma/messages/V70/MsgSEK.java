package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSEK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSEP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Created		: 17.06.2013<br>
 * Description	: MsgSEK - CUSFIN - Mitteilung der Erledigung - NotificationOfCompletion.
 * 				  
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgSEK extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsSEK   sekSubset; 
	private ArrayList   <TsSEP>sepList;
	private TsKUN   kunSubset;
	private ArrayList <TsKUP>kupPosList;  //EI20140312

	public MsgSEK() {
		super();  
		headSubset = new TsHead(); 
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
    	
	public TsSEK getSekSubset() {
		return sekSubset;
	}
	public void setSekSubset(TsSEK sekSubset) {
		this.sekSubset = sekSubset;
	}

	public ArrayList<TsSEP>getSepList() {
		return sepList;
	}
	public void setSepList(ArrayList<TsSEP> list) {
		sepList = list;
	}
	public void addSepList(TsSEP sbp) {
		if (sepList == null) {
			sepList = new ArrayList<TsSEP>();
		}
		this.sepList.add(sbp);
	}
	
	public TsKUN getKunSubset() {
		return kunSubset;
	}
	public void setKunSubset(TsKUN kun) {
		this.kunSubset = kun;
	}
	
	public ArrayList<TsKUP> getKupPosList() {
		return kupPosList;
	}
	public void addKupPosList(TsKUP scp) {
		if (kupPosList == null) {
			kupPosList = new ArrayList<TsKUP>();
		}
		this.kupPosList.add(scp);
	}

	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
            	String lineType = "";
            	if (line.length() > 3) {
            		lineType = line.substring(0, 3);
            	}
            	
            	if (Config.getLogXML()) {
                    Utils.log("(MsgEVK readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("SEK")) {
                	sekSubset = new TsSEK();
                	String[] str = line.split("" + FssUtils.FSS_FS);
                    sekSubset.setFields(str);                  	
            		sepList = new ArrayList<TsSEP>();
            		kupPosList = new ArrayList<TsKUP>();
                } else if (lineType.equalsIgnoreCase("SEP")) {
                	TsSEP sepSubset = new TsSEP();
                    String[] sep = line.split("" + FssUtils.FSS_FS);
                    sepSubset.setFields(sep); 
                    sepList.add(sepSubset);
                } else if (lineType.equalsIgnoreCase("KUN")) {
                	kunSubset = new TsKUN();
                    String[] str = line.split("" + FssUtils.FSS_FS);
                    kunSubset.setFields(str);  
                } else if (lineType.equalsIgnoreCase("KUP")) {
                	TsKUP kupSubset = new TsKUP();
                    String[] kup = line.split("" + FssUtils.FSS_FS);
                    kupSubset.setFields(kup); 
                    kupPosList.add(kupSubset);   
                } else if (lineType.equalsIgnoreCase("") || lineType.equalsIgnoreCase("NAC")) {
                	 //leere Zeile oder Nachlaufsatz "NAC" ignorieren
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}


