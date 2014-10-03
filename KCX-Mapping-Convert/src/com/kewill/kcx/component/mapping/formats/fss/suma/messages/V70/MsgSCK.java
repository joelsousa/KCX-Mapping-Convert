package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSCK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSCP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Date Created	: 17.06.2013<br>
 * Description	: MsgSCK - CUSREC - Verarbeitungsergebnisse /ProcessingResults.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgSCK extends FssMessage {
	private TsVOR vorSubset;
	private TsHead	headSubset;
	private TsSCK   sckSubset; 	
	private ArrayList <TsSCP>scpKopfList;  //20
	private ArrayList <TsSCP>scpPosList;  //999999
	private TsKUN   kunSubset;
	private ArrayList <TsKUP>kupPosList;  //EI20140312

	public MsgSCK() {
		super();  
		headSubset = new TsHead(""); 
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
	
	public void setSckSubset(TsSCK argument) {
		sckSubset = argument;
    }
	public TsSCK getSckSubset() {
		return sckSubset;
	}
	
	public ArrayList<TsSCP> getScpKopfList() {
		return scpKopfList;
	}
	public void addScpKopfList(TsSCP scp) {
		if (scpKopfList == null) {
			scpKopfList = new ArrayList<TsSCP>();
		}
		this.scpKopfList.add(scp);
	}	
	public ArrayList<TsSCP> getScpPosList() {
		return scpPosList;
	}
	public void addScpPosList(TsSCP scp) {
		if (scpPosList == null) {
			scpPosList = new ArrayList<TsSCP>();
		}
		this.scpPosList.add(scp);
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
                    Utils.log("(MsgSCK readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("SCK")) {
                	sckSubset = new TsSCK();
                	String[] str = line.split("" + FssUtils.FSS_FS);
                    sckSubset.setFields(str);                  	
            		scpKopfList = new ArrayList<TsSCP>();
            		scpPosList = new ArrayList<TsSCP>();
            		kupPosList = new ArrayList<TsKUP>();
                } else if (lineType.equalsIgnoreCase("SCP")) {
                	TsSCP scpSubset = new TsSCP();
                    String[] scp = line.split("" + FssUtils.FSS_FS);
                    scpSubset.setFields(scp); 
                    int nr = Integer.parseInt(scpSubset.getPosnr());  //EI20130704
                    if (nr == 0) {
                    	scpKopfList.add(scpSubset);
                    } else if (nr > 0) {
                    	scpPosList.add(scpSubset);
                    }                    
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


