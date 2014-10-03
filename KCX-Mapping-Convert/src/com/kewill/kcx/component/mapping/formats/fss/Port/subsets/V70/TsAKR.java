package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V70; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.Referenzangaben / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKR extends Teilsatz {

    private String beznr	= "";	 // Bezugsnummer
    private String sptref   = "";	 // Spediteursauftragsnummer
    private String spdknr	= "";	 // Kundennummer Spediteur
    private String spdtin	= "";	 // TIN Spediteur
    private String bhtref	= "";	 // BHT-Referenz/Kopf
    private String snddat	= "";	 // Erstellungs-/Sendedatum des Antrages
    private String artna	= "";	 // Nachrichtenfunktion
    private String knztst 	= "";	 // Kennzeichen Testantrag
    private String sb	 	= "";	 // new V70
    private String spdeori 	= "";	 // new V70
    private String spdnl 	= "";	 // new V70

    public TsAKR() {
	    tsTyp = "AKR";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    sptref = fields[2];
	    if (size < 4) { return; }
	    spdknr = fields[3];
	    if (size < 5) { return; }
	    spdtin = fields[4];
	    if (size < 6) { return; }
	    bhtref = fields[5];
	    if (size < 7) { return; }
	    snddat = fields[6];
	    if (size < 8) { return; }
	    artna = fields[7];
	    if (size < 9) { return; }
	    knztst = fields[8];
	    if (size < 10) { return; }
    	sb = fields[9];
	    if (size < 11) { return; }
	    spdeori = fields[10];
	    if (size < 12) { return; }
	    spdnl = fields[11];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(sptref);
    	buff.append(trenner);
    	buff.append(spdknr);
    	buff.append(trenner);
    	buff.append(spdtin);
    	buff.append(trenner);
    	buff.append(bhtref);    	
    	buff.append(trenner);
    	buff.append(snddat);
    	buff.append(trenner);
    	buff.append(artna);
    	buff.append(trenner);
    	buff.append(knztst);
    	buff.append(trenner);
    	buff.append(sb);
    	buff.append(trenner);
    	buff.append(spdeori);
    	buff.append(trenner);
    	buff.append(spdnl);
    	buff.append(trenner);
 
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }
   
    public String getSptref() {
    	 return sptref;
    }
    public void setSptref(String argument) {
    	this.sptref = Utils.checkNull(argument);
    }

    public String getSpdknr() {
    	 return spdknr;
    }
    public void setSpdknr(String argument) {
    	this.spdknr = Utils.checkNull(argument);
    }

    public String getSpdtin() {
    	 return spdtin;
    }
    public void setSpdtin(String argument) {
    	this.spdtin = Utils.checkNull(argument);
    }

    public String getBhtref() {
    	 return bhtref;
    }
    public void setBhtref(String argument) {
    	this.bhtref = Utils.checkNull(argument);
    }
    
    public String getSnddat() {
   	 	return snddat;
   }
   public void setSnddat(String argument) {
   		this.snddat = Utils.checkNull(argument);
   }
   
   
   public String getArtna() {
  		return artna;
   }
   public void setArtna(String argument) {
   	this.artna = Utils.checkNull(argument);
 	}
   
    public String getKnztst() {
   		return knztst;
    }
    public void setKnztst(String argument) {
    	this.knztst = Utils.checkNull(argument);
  	}

    public String getSb() {
   	 	return sb;
    }
    public void setSb(String argument) {
    	this.sb = Utils.checkNull(argument);
    }
   
    public String getSpdeori() {
   	 	return spdeori;
    }
    public void setSpdeori(String argument) {
    	this.spdeori = Utils.checkNull(argument);
    }
    
    public String getSpdnl() {
   	 	return spdnl;
    }
    public void setSpdnl(String argument) {
    	this.spdnl = Utils.checkNull(argument);
    }
    
    public boolean isEmpty() {
	return  Utils.isStringEmpty(sptref) &&
    	Utils.isStringEmpty(spdknr) &&
    	Utils.isStringEmpty(spdtin) &&
    	Utils.isStringEmpty(bhtref) &&
    	Utils.isStringEmpty(snddat) &&
    	Utils.isStringEmpty(artna);
    }
}

