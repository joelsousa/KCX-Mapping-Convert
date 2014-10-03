package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Rückmeldungen Kopfdaten  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKO extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String lfdnr	= "";	 // 
	 private String from	= "";	 // Dakosy/BHT
	 private String art		= "";	 // Art der Rückmeldung: AP-accepted, RE-rejected, W-warnung, UV-terminal hat die daten bekommen
	 private String date	= "";	 // Datum der Rückmeldung
	 private String anspna 	= "";	 // Ansprechpartner  Hafensystem
	 private String anspte 	= "";	 // Ansprechpartner telefon
	 private String anspem	= "";	 // Ansprechpartner e-Mail
	 private String kaicde	= "";	 // Kaibetrieb zum Hafenauftrag
	 private String regnr	= "";	 // Registriernummer der Anmeldung
	 private String stacod	= "";	 // Status zur Meldung

    public TsAKO() {
	    tsTyp = "AKO";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    lfdnr = fields[2];
	    if (size < 4) { return; }
	    from = fields[3];
	    if (size < 5) { return; }
	    art = fields[4];
	    if (size < 6) { return; }
	    date = fields[5];
	    if (size < 7) { return; }
	    anspna = fields[6];
	    if (size < 8) { return; }
	    anspte = fields[7];
	    if (size < 9) { return; }
	    anspem = fields[8];
	    if (size < 10) { return; }
	    kaicde = fields[9];
	    if (size < 11) { return; }
	    regnr = fields[10];
	    if (size < 12) { return; }
	    stacod = fields[11];	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(from);
    	buff.append(trenner);
    	buff.append(art);
    	buff.append(trenner);
    	buff.append(date);
    	buff.append(trenner);
    	buff.append(anspna);
    	buff.append(trenner);
    	buff.append(anspte);
    	buff.append(trenner);
    	buff.append(anspem);
    	buff.append(trenner);
    	buff.append(kaicde);
    	buff.append(trenner);
    	buff.append(regnr);
    	buff.append(trenner);
    	buff.append(stacod);
    	buff.append(trenner);    	
    	
    	return new String(buff);   
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }

    public String getLfdnr() {
    	 return lfdnr;
    }
    public void setLfdnr(String argument) {
    	this.lfdnr = Utils.checkNull(argument);
    }

    public String getFrom() {
    	 return from;
    }
    public void setFrom(String argument) {
    	this.from = Utils.checkNull(argument);
    }

    public String getArt() {
    	 return art;
    }
    public void setArt(String argument) {
    	this.art = Utils.checkNull(argument);
    }

    public String getDate() {
    	 return date;
    }
    public void setDate(String argument) {
    	this.date = Utils.checkNull(argument);
    }
    
    public String getAnspna() {
   	 	return anspna;
    }
    public void setAnspna(String argument) {
    	this.anspna = Utils.checkNull(argument);
    }
    
    public String getAnspte() {
   	 	return anspte;
    }
    public void setAnspte(String argument) {
    	this.anspte = Utils.checkNull(argument);
    }
   
    public String getAnspem() {
   	 	return anspem;
    }
    public void setAnspem(String argument) {
    	this.anspem = Utils.checkNull(argument);
    }
   
    public String getKaicde() {
   	 	return kaicde;
    }
    public void setKaicde(String argument) {
    	this.kaicde = Utils.checkNull(argument);
    }
       
    public String getRegnr() {
   	 	return regnr;
    }
    public void setRegnr(String argument) {
    	this.regnr = Utils.checkNull(argument);
    }
   
    public String getStacod() {
   	 	return stacod;
    }
    public void setStacod(String argument) {
    	this.stacod = Utils.checkNull(argument);
    }   
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(lfdnr) &&
		  Utils.isStringEmpty(from) &&
		  Utils.isStringEmpty(art) &&
		  Utils.isStringEmpty(date) &&
		  Utils.isStringEmpty(anspna) &&
		  Utils.isStringEmpty(anspem) &&
		  Utils.isStringEmpty(kaicde) &&
		  Utils.isStringEmpty(regnr) &&		  	
		  Utils.isStringEmpty(stacod);
    }

}

