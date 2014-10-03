package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSCK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSCP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Date Created	: 08.02.2013<br>
 * Description	: MsgSCK/ Verarbeitungsergebnisse /ProcessingResults.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgSCK extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsSCK   sckSubset; 	
	private ArrayList <TsSCP>scpList;

	public MsgSCK() {
		super();  
		vorSubset = new TsVOR(""); 
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public void setSckSubset(TsSCK argument) {
		sckSubset = argument;
    }
	public TsSCK getSckSubset() {
		return sckSubset;
	}
	
	public ArrayList<TsSCP> getScpList() {
		return scpList;
	}
	public void addScpList(TsSCP scp) {
		this.scpList.add(scp);
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
                if (lineType.equalsIgnoreCase("SCK")) {
                	sckSubset = new TsSCK();
                	String[] str = line.split("" + FssUtils.FSS_FS);
                    sckSubset.setFields(str);                  	
            		scpList = new ArrayList<TsSCP>();
                } else if (lineType.equalsIgnoreCase("SCP")) {
                	TsSCP scpSubset = new TsSCP();
                    String[] scp = line.split("" + FssUtils.FSS_FS);
                    scpSubset.setFields(scp); 
                    scpList.add(scpSubset);
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


