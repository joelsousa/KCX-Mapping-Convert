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
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSI;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSST;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Created		: 17.06.2013<br>
 * Description	: MsgSST - CUSSTP - Bekanntgabe einer Maﬂnahme. 
 * 				  
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgSST extends FssMessage {
	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsSST   sstSubset; 	
	private ArrayList <MsgSSTPos> posList;
	private TsKUN   kunSubset;
	private ArrayList <TsKUP>kupPosList;  //EI20140312
	
	public MsgSST() {
		super();  
		headSubset = new TsHead(); 
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
    	
	public TsSST getSSTSubset() {
		return sstSubset;
	}
	public void setSSTSubset(TsSST ts) {
		this.sstSubset = ts;
	}

	public ArrayList<MsgSSTPos> getPosList() {
		return posList;
	}
	public void setPosList(ArrayList<MsgSSTPos> list) {
		posList = list;
	}
	public void addPosList(MsgSSTPos pos) {
		if (posList == null) {
			posList = new ArrayList<MsgSSTPos>();
		}
		this.posList.add(pos);
	}

	public TsVOR getVorSubset() {
		return vorSubset;
	}

	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
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
            MsgSSTPos pos = null;

            while ((line = message.readLine()) != null) {
            	String lineType = "";
            	if (line.length() > 3) {
            		lineType = line.substring(0, 3);
            	}
            	
            	if (Config.getLogXML()) {
                    Utils.log("(MsgSST readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("SST")) {
                	sstSubset = new TsSST();
                	String[] sst = line.split("" + FssUtils.FSS_FS);
                    sstSubset.setFields(sst);                  	
            		posList = new ArrayList<MsgSSTPos>();
            		kupPosList = new ArrayList<TsKUP>();
                } else if (lineType.equalsIgnoreCase("SSP")) {
                	pos = new MsgSSTPos();
                	TsSSP sspSubset = new TsSSP();
                    String[] ssp = line.split("" + FssUtils.FSS_FS);
                    sspSubset.setFields(ssp); 
                    pos.setSspSubset(sspSubset);                        
                } else if (lineType.equalsIgnoreCase("SSA")) {
                	TsSSA ssaSubset = new TsSSA();
                	String[] ssa = line.split("" + FssUtils.FSS_FS);
                    ssaSubset.setFields(ssa); 
                    pos.setSsaSubset(ssaSubset); 
                } else if (lineType.equalsIgnoreCase("SSI")) {
                	TsSSI ssiSubset = new TsSSI();
                	String[] ssi = line.split("" + FssUtils.FSS_FS);
                    ssiSubset.setFields(ssi); 
                    pos.setSsiSubset(ssiSubset); 
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

	public TsSST getSstSubset() {
		return sstSubset;
	}

	public void setSstSubset(TsSST sstSubset) {
		this.sstSubset = sstSubset;
	}

	

}


