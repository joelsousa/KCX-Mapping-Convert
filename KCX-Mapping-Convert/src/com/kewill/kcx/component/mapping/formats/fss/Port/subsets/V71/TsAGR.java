package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Gefahrgutangaben 1: 
 * 				: EI20131004: Erweiterung füer BHT
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAGR extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer des Hafenauftrags 	 
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3	
	 private String imdg	= "";	 // IMDG-Klasse: 1, 2.(1,2,3), 3, 4.(1,2,3), 5.(1,2), 6.(1,2),7, 8, 9
	 private String unnr 	= "";	 // UN-Nummer
	 private String techna	= "";	 // techn. Name des Gefahrguts
	 private String marpol	= "";	 // Marine Pollutant
	 private String flamp	= "";	 // Flammpunkt
	 private String flampe	= "";	 // 
	 private String vpsist	= "";	 // 
	 private String staukt	= "";	 // 
	 private String netgew 	= "";	 // 
	 private String nevgrp	= "";	 //  
	 private String expgew  = "";	 // netto Explosivmasse (Pulvergewicht
	 private String expspb  = "";	 // 
	 private String strkla  = "";	 // 
	 private String strzif  = "";	 // 
	 private String strunr  = "";	 // 
	 private String anzsol  = "";	 // BHT Gef-Pos-Anzahl-Soll       EI20131004
	 private String vpsol  = "";	 // BHT Gef-Verpackungsart-Code   EI20131004
	 private String lfdnr  = "";	 // BHT int(2)	Laufende Nummer   EI20131004
	
    public TsAGR() {
    	tsTyp = "AGR";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    posnr = fields[2];
	    if (size < 4) { return; }
	    pe2lfd = fields[3];
	    if (size < 5) { return; }
	    pe3lfd = fields[4];
	    if (size < 6) { return; }
	    imdg = fields[5];
	    if (size < 7) { return; }
	    unnr = fields[6];
	    if (size < 8) { return; }
	    techna = fields[7];
	    if (size < 9) { return; }
	    marpol = fields[8];
	    if (size < 10) { return; }
	    flamp = fields[9];
	    if (size < 11) { return; }
	    flampe = fields[10];
	    if (size < 12) { return; }
	    vpsist = fields[11];
	    if (size < 13) { return; }
	    staukt = fields[12];
	    if (size < 14) { return; }
	    netgew = fields[13];
	    if (size < 15) { return; }
	    nevgrp = fields[14];
	    if (size < 16) { return; }
	    expgew = fields[15];
	    if (size < 17) { return; }
	    expspb = fields[16];
	    if (size < 18) { return; }
	    strkla = fields[17];	    
	    if (size < 19) { return; }
	    strzif = fields[18];
	    if (size < 20) { return; }
	    strunr = fields[19];
	    if (size < 21) { return; }
	    anzsol = fields[20];
	    if (size < 22) { return; }
	    vpsol = fields[21];
	    if (size < 23) { return; }
	    lfdnr = fields[22];
    }
   
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(pe2lfd);
    	buff.append(trenner);
    	buff.append(pe3lfd);
    	buff.append(trenner);
    	buff.append(imdg);
    	buff.append(trenner);
    	buff.append(unnr);
    	buff.append(trenner);
    	buff.append(techna);
    	buff.append(trenner);
    	buff.append(marpol);
    	buff.append(trenner);
    	buff.append(flamp);
    	buff.append(trenner);
    	buff.append(flampe);
    	buff.append(trenner);
    	buff.append(vpsist);
    	buff.append(trenner);
    	buff.append(staukt);
    	buff.append(trenner);
    	buff.append(netgew);
    	buff.append(trenner);
    	buff.append(nevgrp);
    	buff.append(trenner);
    	buff.append(expgew);
    	buff.append(trenner);
    	buff.append(expspb);
    	buff.append(trenner);
    	buff.append(strkla);
    	buff.append(trenner);
    	buff.append(strzif);
    	buff.append(trenner);
    	buff.append(strunr);
    	buff.append(trenner);
    	buff.append(anzsol);
    	buff.append(trenner);
    	buff.append(vpsol);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	
    	return new String(buff);   
    }
   
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getPosnr() {
    	 return posnr;
    }
    public void setPosnr(String posnr) {
    	this.posnr = Utils.checkNull(posnr);
    }

    public String getPe2lfd() {
    	 return pe2lfd;
    }
    public void setPe2lfd(String pe2lfd) {
    	this.pe2lfd = Utils.checkNull(pe2lfd);
    }

    public String getPe3lfd() {
    	 return pe3lfd;
    }
    public void setPe3lfd(String pe3lfd) {
    	this.pe3lfd = Utils.checkNull(pe3lfd);
    }

    public String getImdg() {
    	 return imdg;
    }
    public void setImdg(String imdg) {
    	this.imdg = Utils.checkNull(imdg);
    }
    
    public String getUnnr() {
   	 	return unnr;
    }
    public void setUnnr(String argument) {
    	this.unnr = Utils.checkNull(argument);
    }
   
    public String getTechna() {
   	 	return techna;
    }
    public void setTechna(String argument) {
    	this.techna = Utils.checkNull(argument);
    }
   
    public String getMarpol() {
   	 	return marpol;
    }
    public void setMarpol(String argument) {
    	this.marpol = Utils.checkNull(argument);
    }
       
    public String getFlamp() {
   	 	return flamp;
    }
    public void setFlamp(String argument) {
    	this.flamp = Utils.checkNull(argument);
    }
   
    public String getFlampe() {
   	 	return flampe;
    }
    public void setFlampe(String argument) {
    	this.flampe = Utils.checkNull(argument);
    }
    
    public String getVpsist() {
   	 	return vpsist;
    }
    public void setVpsist(String argument) {
    	this.vpsist = Utils.checkNull(argument);
    }
    
    public String getStaukt() {
   	 	return staukt;
    }
    public void setStaukt(String argument) {
    	this.staukt = Utils.checkNull(argument);
    }
   
    public String getNetgew() {
   	 	return netgew;
    }
    public void setNetgew(String argument) {
    	this.netgew = Utils.checkNull(argument);
    }
    
    public String getNevgrp() {
   	 	return nevgrp;
    }
    public void setNevgrp(String argument) {
    	this.nevgrp = Utils.checkNull(argument);
    }
    
    public String getExpgew() {
   	 	return expgew;
    }
    public void setExpgew(String argument) {
    	this.expgew = Utils.checkNull(argument);
    }

    public String getExpspb() {
   	 	return expspb;
    }
    public void setExpspb(String argument) {
    	this.expspb = Utils.checkNull(argument);
    }
    
    public String getStrkla() {
   	 	return strkla;
    }
    public void setStrkla(String argument) {
    	this.strkla = Utils.checkNull(argument);
    }
    
    public String getStrzif() {
   	 	return strzif;
    }
    public void setStrzif(String argument) {
    	this.expgew = Utils.checkNull(argument);
    }
    
    public String getStrunr() {
   	 	return strunr;
    }
    public void setStrunr(String argument) {
    	this.strunr = Utils.checkNull(argument);
    }   
    
    
    public String getAnzsol() {
		return anzsol;
	}

	public void setAnzsol(String anzsol) {
		this.anzsol = anzsol;
	}

	public String getVpsol() {
		return vpsol;
	}

	public void setVpsol(String vpsol) {
		this.vpsol = vpsol;
	}

	public String getLfdnr() {
		return lfdnr;
	}
	public void setLfdnr(String lfdnr) {
		this.lfdnr = lfdnr;
	}

	public boolean isEmpty() {
	  return  Utils.isStringEmpty(imdg) &&
		  Utils.isStringEmpty(unnr) &&		 
		  Utils.isStringEmpty(techna) &&
		  Utils.isStringEmpty(marpol) &&
		  Utils.isStringEmpty(flamp) &&
		  Utils.isStringEmpty(flampe) &&
		  Utils.isStringEmpty(vpsist) &&
		  Utils.isStringEmpty(staukt) &&
		  Utils.isStringEmpty(netgew) &&
		  Utils.isStringEmpty(nevgrp) &&		
		  Utils.isStringEmpty(expgew);
    }

}

