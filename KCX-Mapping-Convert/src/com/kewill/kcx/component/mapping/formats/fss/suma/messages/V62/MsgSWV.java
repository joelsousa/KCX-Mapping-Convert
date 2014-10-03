package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSWA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSWP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSWV;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: Manifest<br>
 * Date Created	: 19.11.2012<br>
 * Description	: MsgSWV/ GoodsReleasedInternal.
 * 
 * @author Krzoska
 * @version 1.0.00
 */
public class MsgSWV extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsSWV   swvSubset; 
	private TsSWP   swpSubset;
	private ArrayList   <TsSWP>swpList;
	private TsSWA   swaSubset;
	private ArrayList   <TsSWA>swaList;

	public MsgSWV() {
		super();  
		vorSubset = new TsVOR(""); 
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public ArrayList<TsSWP> getSwpList() {
		return swpList;
	}
	public void addSwpList(TsSWP swp) {
		this.swpList.add(swp);
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
                    Utils.log("(MsgSWP readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("SWV")) {
                	swvSubset = new TsSWV();
                	String[] swv = line.split("" + FssUtils.FSS_FS);
                    swvSubset.setFields(swv);                  	
            		swpList = new ArrayList<TsSWP>();
                } else if (lineType.equalsIgnoreCase("SWP")) {
                    swpSubset = new TsSWP();
                    String[] swp = line.split("" + FssUtils.FSS_FS);
                    swpSubset.setFields(swp); 
                    swpList.add(swpSubset);
                } else if (lineType.equalsIgnoreCase("SWA")) {
                    swaSubset = new TsSWA();
                    String[] swa = line.split("" + FssUtils.FSS_FS);
                    if (swaList == null) {
                    	swaList = new ArrayList<TsSWA>();
                    }
                    swaSubset.setFields(swa); 
                    swaList.add(swaSubset);
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

	public TsSWV getSwvSubset() {
		return swvSubset;
	}

	public void setSwvSubset(TsSWV swvSubset) {
		this.swvSubset = swvSubset;
	}

	public TsSWP getSwpSubset() {
		return swpSubset;
	}

	public void setSwpSubset(TsSWP swpSubset) {
		this.swpSubset = swpSubset;
	}

	public TsSWA getSwaSubset() {
		return swaSubset;
	}

	public void setSwaSubset(TsSWA swaSubset) {
		this.swaSubset = swaSubset;
	}

	public ArrayList<TsSWA> getSwaList() {
		return swaList;
	}

}


