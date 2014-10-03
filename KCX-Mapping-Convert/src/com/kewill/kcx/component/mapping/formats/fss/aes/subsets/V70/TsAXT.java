package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;

/**
 * Modul		:	AES
 * Erstellt		:	08.08.2013
 * Beschreibung	:	Daten zur Ausfuhr 
 *				:   neu in AES22: Sachbearbeiter - ist aber immer FSS-V70
 *
 * @author 			iwaniuk
 * @version 2.1.00
 */

public class TsAXT extends Teilsatz {

    private String tsTyp  = "";                    //Ts-Schlüssel
    private String beznr  = "";                   //Bezugsnummer
    private String asgart = "";                   //Art des Ausgang
    private String asgdat = "";                   //Datum des Ausgangs  
    private String vetdat = "";                   //Datum des vorgesehenen Ausgangs  
    private String tetdst = "";                   //Tatsächliche Ausgangszollstelle  
    private String verm   = "";                   //Vermerk   
    private String sb     = "";                   //Sachbearbeiter (an8)       //EI20130808


    public TsAXT() {
        tsTyp = "AXT";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }		
        tsTyp         = fields[0];    	
        if (size < 2) { return;	}       
        beznr         = fields[1];
        if (size < 3) { return; }	
        asgart        = fields[2];
        if (size < 4) { return; }	
        asgdat        = fields[3];   
        if (size < 5) { return; }	
        vetdat        = fields[4]; 
        if (size < 6) { return; }	
        tetdst        = fields[5];  
        if (size < 7) { return; }	
        verm          = fields[6];   
        if (size < 8) { return; }	
        sb            = fields[7];  
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(asgart);
        buff.append(trenner);
        buff.append(asgdat);
        buff.append(trenner); 
        buff.append(vetdat);
        buff.append(trenner);           
        buff.append(tetdst);
        buff.append(trenner); 
        buff.append(verm);
        buff.append(trenner);  
        if (sb != null && !sb.isEmpty()) { //wegen AES22, ist aber keine neue FSS-Version, solange nicht an
        				  //alle Kunden ausgeliefert, darf der Teilsatz nicht laenger sein 
        	buff.append(sb);
        	buff.append(trenner); 
        }
        return new String(buff);
    }

	public String getTsTyp() {
		return tsTyp;
	}

	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getAsgart() {
		return asgart;
	}

	public void setAsgart(String asgart) {
		this.asgart = Utils.checkNull(asgart);
	}

	public String getAsgdat() {
		return asgdat;
	}

	public void setAsgdat(String asgdat) {
		this.asgdat = Utils.checkNull(asgdat);
	}

	public String getVetdat() {
		return vetdat;
	}

	public void setVetdat(String vetdat) {
		this.vetdat = Utils.checkNull(vetdat);
	}

	public String getTetdst() {
		return tetdst;
	}

	public void setTetdst(String tetdst) {
		this.tetdst = Utils.checkNull(tetdst);
	}

	public String getVerm() {
		return verm;
	}
	//EI20131014: public void setVerm(String sb) {
	public void setVerm(String verm) {   //EI20131014
		this.verm = Utils.checkNull(verm);
	}
	
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public boolean isEmpty() {
		return ( Utils.isStringEmpty(asgart)  && Utils.isStringEmpty(asgdat) 
		  && Utils.isStringEmpty(vetdat) && Utils.isStringEmpty(tetdst) 
		  && Utils.isStringEmpty(verm) && Utils.isStringEmpty(sb));		  			
	}
}




 
