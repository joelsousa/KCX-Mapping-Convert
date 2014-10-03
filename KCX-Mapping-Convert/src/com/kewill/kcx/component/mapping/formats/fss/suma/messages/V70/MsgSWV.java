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
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSWA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSWP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSWV;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Date Created	: 17.06.2013<br>
 * Description	: MsgSWV - CUSTST - GoodsReleasedInternal - Verwahrmitteilung. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgSWV extends FssMessage {
	
	private TsVOR   vorSubset;
	private TsHead	headSubset;
	private TsSWV   swvSubset; 	
	private ArrayList   <TsSWP>swpList;	
	private ArrayList   <TsSWA>swaList;
	private TsKUN   kunSubset;
	private ArrayList <TsKUP>kupPosList;  //EI20140312

	public MsgSWV() {
		super();  
		headSubset = new TsHead(""); 
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}


	public void setVorSubset(TsHead ts) {
		this.headSubset = ts;
	}

	public TsHead getHeadSubset() {
		return headSubset;
	}

	public void setHeadSubset(TsHead headSubset) {
		this.headSubset = headSubset;
	}

    
	public TsSWV getSwvSubset() {
		return swvSubset;
	}

	public void setSwvSubset(TsSWV swvSubset) {
		this.swvSubset = swvSubset;
	}	
	
	public ArrayList<TsSWA> getSwaList() {
		return swaList;
	}
	
	public ArrayList<TsSWP> getSwpList() {
		return swpList;
	}
	public void addSwpList(TsSWP swp) {
		this.swpList.add(swp);
	}
	
	public TsKUN getKunSubset() {
		return kunSubset;
	}
	public void setKunSubset(TsKUN subset) {
		this.kunSubset = subset;
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
                    Utils.log("(MsgSWP readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("SWV")) {
                	swvSubset = new TsSWV();
                	String[] swv = line.split("" + FssUtils.FSS_FS);
                    swvSubset.setFields(swv);                  	
            		swpList = new ArrayList<TsSWP>();
            		kupPosList = new ArrayList<TsKUP>();
                } else if (lineType.equalsIgnoreCase("SWP")) {
                	TsSWP swpSubset = new TsSWP();
                    String[] swp = line.split("" + FssUtils.FSS_FS);
                    swpSubset.setFields(swp); 
                    swpList.add(swpSubset);
                } else if (lineType.equalsIgnoreCase("SWA")) {
                	TsSWA swaSubset = new TsSWA();
                    String[] swa = line.split("" + FssUtils.FSS_FS);
                    if (swaList == null) {
                    	swaList = new ArrayList<TsSWA>();
                    }
                    swaSubset.setFields(swa); 
                    swaList.add(swaSubset);
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


