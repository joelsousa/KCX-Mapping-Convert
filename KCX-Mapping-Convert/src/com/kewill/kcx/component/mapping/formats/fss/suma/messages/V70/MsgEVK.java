package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsEVK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsEVP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsKUP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: Manifest<br>
 * Date Created	: 12.11.2012<br>
 * Description	: MsgEVK/ GoodsReleasedExternal.
 * 
 * @author Kron
 * @version 1.0.00
 */
public class MsgEVK extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsEVK   evkSubset;		
	private ArrayList    <TsEVP>evpList;
	private TsKUN   kunSubset;
	private ArrayList <TsKUP>kupPosList;  //EI20140312

	public MsgEVK() {
		super();  
		vorSubset = new TsVOR(""); 
	}
	
	/**
	 * Nachrichtendatei einlesen und Inhalt in Teilsatzklassen speichern.
	 * 
	 * @param message  Reader aus dem die Nachrichtendatei eingelesen werden kann.
	 * @throws FssException Wenn ein unbekannter Zeilentyp gelesen wurde.
	 */
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
                if (lineType.equalsIgnoreCase("EVK")) {
                	evkSubset = new TsEVK();
                	String[] crl = line.split("" + FssUtils.FSS_FS);
                    evkSubset.setFields(crl);                  	
            		evpList = new ArrayList<TsEVP>();
            		kupPosList = new ArrayList<TsKUP>();
                } else if (lineType.equalsIgnoreCase("EVP")) {
                	TsEVP evpSubset = new TsEVP();
                    String[] evp = line.split("" + FssUtils.FSS_FS);
                    evpSubset.setFields(evp); 
                    evpList.add(evpSubset);
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
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead headSubset) {
		this.headSubset = headSubset;
	}

	public TsEVK getEvkSubset() {
		return evkSubset;
	}
	public void setEvkSubset(TsEVK evkSubset) {
		this.evkSubset = evkSubset;
	}
	
	public void setEvpList(ArrayList<TsEVP> evpList) {
		this.evpList = evpList;
	}
	public ArrayList<TsEVP> getEvpList() {
		return evpList;
	}	
	public void addEvpList(TsEVP evp) {
		this.evpList.add(evp);
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
}


