package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;


import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsEVK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsEVP;
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
public class MsgEVK extends FssMessage{
	
	private TsVOR	vorSubset;
	private TsEVK   evkSubset; 
	private TsEVP   evpSubset;
	private List    <TsEVP>evpList;

	public MsgEVK() {
		super();  
		vorSubset = new TsVOR(""); 
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public void setEVKSubset(TsEVK argument) {
		evkSubset = argument;
    }
	public TsEVK getEVKSubset() {
		return evkSubset;
	}
	public List<TsEVP> getEvpList() {
		return evpList;
	}
	//public void addAbfList(TsEVP evp) {
	public void addEvpList(TsEVP evp) {
		this.evpList.add(evp);
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
            		evpList = new Vector<TsEVP>();
                } else if (lineType.equalsIgnoreCase("EVP")) {
                    evpSubset = new TsEVP();
                    String[] evp = line.split("" + FssUtils.FSS_FS);
                    evpSubset.setFields(evp); 
                    evpList.add(evpSubset);
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


