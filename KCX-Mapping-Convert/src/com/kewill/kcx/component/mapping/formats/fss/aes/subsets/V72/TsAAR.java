package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;

/**
 * Modul		:	AES
 * Erstellt		:	19.07.2013
 * Beschreibung	:	Daten zur EXIT. 
 *				:   neu in V72: bestb(besonderer Tatbestand), gesanz(Gesamtanzahl Packstücke)
 *
 * @author 	iwaniuk
 * @version 2.2/72
 */


public class TsAAR extends Teilsatz {

    private String tsTyp   = "";                    //Ts-Schlüssel
    private String beznr   = "";                   //Bezugsnummer
    private String mrn     = "";                   
    private String fregnr  = "";                   //Registriernummer Fremdsystem
    private String spdkdnr = "";                  //Spediteur - Kundennummer
    private String spdeori = "";                
    private String spdnl   = "";                     
    private String spdid   = "";                  //Spediteur - Identifikationsart zur EORI-TIN
    private String ausdst  = "";                  //Ausgangszolldienststelle
    private String bestb   = "";                  //neu V72 - besonderer Tatbestand
    private String gesanz  = "";                  //neu V72 - Gesamtanzahl Packstücke


    public TsAAR() {
        tsTyp = "AAR";
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
        mrn           = fields[2];
        if (size < 4) { return; }	
        fregnr        = fields[3];   
        if (size < 5) { return; }	
        spdkdnr       = fields[4]; 
        if (size < 6) { return; }	
        spdeori       = fields[5];  
        if (size < 7) { return; }	
        spdnl         = fields[6];   
        if (size < 8) { return; }	
        spdid         = fields[7];  
        if (size < 9) { return; }	
        ausdst         = fields[8];   
        if (size < 10) { return; }	
        bestb         = fields[9];  
        if (size < 11) { return; }	
        gesanz        = fields[10]; 
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(mrn);
        buff.append(trenner);
        buff.append(fregnr);
        buff.append(trenner); 
        buff.append(spdkdnr);
        buff.append(trenner);           
        buff.append(spdeori);
        buff.append(trenner); 
        buff.append(spdnl);
        buff.append(trenner);  
        buff.append(spdid);
        buff.append(trenner); 
        buff.append(ausdst);
        buff.append(trenner);        
        buff.append(bestb); 
        buff.append(trenner);
        buff.append(gesanz); 
        buff.append(trenner);
        
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

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getFregnr() {
		return fregnr;
	}

	public void setFregnr(String fregnr) {
		this.fregnr = Utils.checkNull(fregnr);
	}

	public String getSpdkdnr() {
		return spdkdnr;
	}

	public void setSpdkdnr(String spdkdnr) {
		this.spdkdnr = Utils.checkNull(spdkdnr);
	}

	public String getSpdeori() {
		return spdeori;
	}

	public void setSpdeori(String spdeori) {
		this.spdeori = Utils.checkNull(spdeori);
	}

	public String getSpdnl() {
		return spdnl;
	}
	public void setSpdnl(String sb) {
		this.spdnl = Utils.checkNull(spdnl);
	}
	
	public String getSpdid() {
		return spdid;
	}
	public void setSpdid(String sb) {
		this.spdid = Utils.checkNull(sb);
	}

	public String getAusdst() {
		return ausdst;
	}
	public void setAusdst(String beznr) {
		this.ausdst = Utils.checkNull(beznr);
	}
	
	public String getBestb() {
		return bestb;
	}	
	public void setBestb(String beznr) {
		this.bestb = Utils.checkNull(beznr);
	}	
	
	public String getGesanz() {
		return gesanz;
	}
	public void setGesanz(String gesanz) {
		this.gesanz = Utils.checkNull(gesanz);
	}

	public boolean isEmpty() {
		return ( Utils.isStringEmpty(mrn)  && Utils.isStringEmpty(fregnr) 
		  && Utils.isStringEmpty(spdkdnr) && Utils.isStringEmpty(spdeori) 
		  && Utils.isStringEmpty(spdnl) && Utils.isStringEmpty(spdid));		  			
	}
}




 
