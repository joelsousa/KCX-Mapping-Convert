package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUR;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUA;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Created		: 19.12.2012<br>
 * Description	: MsgSUK/ Gestellung-/Aufteilungsdaten = NotificationOfPresentation.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgSUK extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsSUK   sukSubset; 
	private TsSUR   surSubset;
	private TsSUA   suaSubset;
	private List    <TsSUP>supList;

	public MsgSUK() {
		super();  
		vorSubset = new TsVOR(""); 
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
    	
	public TsSUK getSukSubset() {
		return sukSubset;
	}
	public void setSukSubset(TsSUK sukSubset) {
		this.sukSubset = sukSubset;
	}

	public TsSUR getSurSubset() {
		return surSubset;
	}
	public void setSurSubset(TsSUR surSubset) {
		this.surSubset = surSubset;
	}

	public TsSUA getSuaSubset() {
		return suaSubset;
	}
	public void setSuaSubset(TsSUA suaSubset) {
		this.suaSubset = suaSubset;
	}

	public List<TsSUP> getSupList() {
		return supList;
	}
	public void setSupList(List<TsSUP> list) {
		supList = list;
	}
	public void addSupList(TsSUP evp) {
		if (supList == null) {
			supList = new ArrayList<TsSUP>();
		}
		this.supList.add(evp);
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
                    Utils.log("(MsgSUK readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("SUK")) {
                	sukSubset = new TsSUK();
                	String[] suk = line.split("" + FssUtils.FSS_FS);
                    sukSubset.setFields(suk);                  	
            		supList = new Vector<TsSUP>();
                }else if (lineType.equalsIgnoreCase("SUR")) {
                	surSubset = new TsSUR();
                	String[] sur = line.split("" + FssUtils.FSS_FS);
                    surSubset.setFields(sur);    
                } else if (lineType.equalsIgnoreCase("SUA")) {
                	suaSubset = new TsSUA();
                	String[] sua = line.split("" + FssUtils.FSS_FS);
                    suaSubset.setFields(sua);    
                } else if (lineType.equalsIgnoreCase("SUP")) {
                	TsSUP supSubset = new TsSUP();
                    String[] sup = line.split("" + FssUtils.FSS_FS);
                    supSubset.setFields(sup); 
                    addSupList(supSubset);
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
	
	public String getFssString() throws FssException {
		String res = "";	
		
		if (headSubset != null) {	 
			res = appendString(res, headSubset.teilsatzBilden());	
		} else if (vorSubset != null) {	 
			res = appendString(res, vorSubset.teilsatzBilden());	
		}
		
		if (sukSubset != null) {	 
			res = appendString(res, sukSubset.teilsatzBilden());	
		}
		if (surSubset != null) {	 
			res = appendString(res, surSubset.teilsatzBilden());	
		}
		if (suaSubset != null) {	 
			res = appendString(res, suaSubset.teilsatzBilden());	
		}
		if (supList != null) {                     					 			
			for (TsSUP sup : supList) {				
				if (sup != null && !sup.isEmpty()) {
					res = appendString(res, sup.teilsatzBilden());
				}
			}
		}
		
		return res;
	}
}


