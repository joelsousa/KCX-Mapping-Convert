package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V72; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 12.12.2013<br>
 * Description	: Hafenauftrag	Adressierungen  / MsgPOR.
 * 				: new in V72:  rdreori, rdrnl
 * 
 * @author iwaniuk
 * @version 7.2.00
 */

public class TsAKD extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String kaicde	= "";	 // Terminal-Code
	 private String offer	= "";	 // Angebotsnummer / Offerte
	 private String extdst	= "";	 // Ausgangszolldienststelle 
	 private String rdrcde	= "";	 // Reedereiagent/Makler Code
	 private String rdrknr 	= "";	 // Kundennummern Reedereiagent/Makler
	 private String rdrkai	= "";	 // Kai-Konto des Reedereiagenten/Makler
	 private String arecde	= "";	 // Abweichender Rechnungsempfänger Code
	 private String areknr	= "";	 // Kundennummern abweichender Rechnungsempfänger Kundenstamm ZABIS
	 private String arekai	= "";	 // Kai-Konto des abweichenden Rechnungsempfängers
	 private String fobcde	= "";	 // FOB-Spediteur Code
	 private String fobknr	= "";	 // Kundennummern FOB-Spediteur
	 private String arefob 	= "";	 // FOB-Positionsnummer
	 private String tlycde	= "";	 // Tally Code	
	 private String tlyknr	= "";	 // Kundennummern Tally	
	 private String rdreori	= "";	 // new V72
	 private String rdrnl	= "";	 //	new V72 
	 
    public TsAKD() {
	    tsTyp = "AKD";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    kaicde = fields[2];
	    if (size < 4) { return; }
	    offer = fields[3];
	    if (size < 5) { return; }
	    extdst = fields[4];
	    if (size < 6) { return; }
	    rdrcde = fields[5];
	    if (size < 7) { return; }
	    rdrknr = fields[6];
	    if (size < 8) { return; }
	    rdrkai = fields[7];
	    if (size < 9) { return; }
	    arecde = fields[8];
	    if (size < 10) { return; }
	    areknr = fields[9];
	    if (size < 11) { return; }
	    arekai = fields[10];
	    if (size < 12) { return; }
	    fobcde = fields[11];
	    if (size < 13) { return; }
	    fobknr = fields[12];
	    if (size < 14) { return; }
	    arefob = fields[13];
	    if (size < 15) { return; }
	    tlycde = fields[14];
	    if (size < 16) { return; }
	    tlyknr = fields[15];
	    if (size < 17) { return; }
	    rdreori = fields[16];
	    if (size < 18) { return; }
	    rdrnl = fields[17];
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(kaicde);
    	buff.append(trenner);
    	buff.append(offer);
    	buff.append(trenner);
    	buff.append(extdst);
    	buff.append(trenner);
    	buff.append(rdrcde);
    	buff.append(trenner);
    	buff.append(rdrknr);
    	buff.append(trenner);
    	buff.append(rdrkai);
    	buff.append(trenner);
    	buff.append(arecde);
    	buff.append(trenner);
    	buff.append(areknr);
    	buff.append(trenner);
    	buff.append(arekai);
    	buff.append(trenner);
    	buff.append(fobcde);
    	buff.append(trenner);
    	buff.append(fobknr);
    	buff.append(trenner);
    	buff.append(arefob);
    	buff.append(trenner);
    	buff.append(tlycde);
    	buff.append(trenner);
    	buff.append(tlyknr);
    	buff.append(trenner); 
    	buff.append(rdreori);
    	buff.append(trenner); 
    	buff.append(rdrnl);
    	buff.append(trenner); 
    	
    	return new String(buff);    
    }    

    public String getBeznr() {
    	return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }

    public String getKaicde() {
    	 return kaicde;
    }
    public void setKaicde(String argument) {
    	this.kaicde = Utils.checkNull(argument);
    }

    public String getOffer() {
    	 return offer;
    }
    public void setOffer(String argument) {
    	this.offer = Utils.checkNull(argument);
    }

    public String getExtdst() {
    	 return extdst;
    }
    public void setExtdst(String argument) {
    	this.extdst = Utils.checkNull(argument);
    }

    public String getRdrcde() {
    	 return rdrcde;
    }
    public void setRdrcde(String argument) {
    	this.rdrcde = Utils.checkNull(argument);
    }

    public String getRdrknr() {
    	return rdrknr;
    }
    public void setRdrknr(String argument) {
    	this.rdrknr = Utils.checkNull(argument);
    }   
    
    public String getRdrkai() {
    	return rdrkai;
    }
    public void setRdrkai(String argument) {
    	this.rdrkai = Utils.checkNull(argument);
    } 
        
    public String getArecde() {
    	return arecde;
    }
    public void setArecde(String argument) {
    	this.arecde = Utils.checkNull(argument);
    } 
    
    public String getAreknr() {
    	return areknr;
    }
    public void setAreknr(String argument) {
    	this.areknr = Utils.checkNull(argument);
    } 
    
    public String getArekai() {
    	return arekai;
    }
    public void setArekai(String argument) {
    	this.arekai = Utils.checkNull(argument);
    }
    
    public String getFobcde() {
    	return fobcde;
    }
    public void setFobcde(String argument) {
    	this.fobcde = Utils.checkNull(argument);
    }
    
    public String getFobknr() {
    	return fobknr;
    }
    public void setFobknr(String argument) {
    	this.fobknr = Utils.checkNull(argument);
    }
    
    public String getArefob() {
    	return arefob;
    }
    public void setArefob(String argument) {
    	this.arefob = Utils.checkNull(argument);
    }
    
    public String getTlycde() {
    	return tlycde;
    }
    public void setTlycde(String argument) {
    	this.tlycde = Utils.checkNull(argument);
    }
    
    public String getTlyknr() {
    	return tlyknr;
    }
    public void setTlyknr(String argument) {
    	this.tlyknr = Utils.checkNull(argument);
    }
    
    public String getRdreori() {
    	return rdreori;
    }
    public void setRdreori(String argument) {
    	this.rdreori = Utils.checkNull(argument);
    }
    
    public String getRdrnl() {
    	return rdrnl;
    }
    public void setRdrnl(String argument) {
    	this.rdrnl = Utils.checkNull(argument);
    }
      
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(kaicde) &&
	  	  Utils.isStringEmpty(offer) &&
	  	  Utils.isStringEmpty(extdst) &&
		  Utils.isStringEmpty(rdrcde) &&
		  Utils.isStringEmpty(rdrknr) &&
		  Utils.isStringEmpty(rdrkai) &&
		  Utils.isStringEmpty(arecde) &&
		  Utils.isStringEmpty(areknr) &&
		  Utils.isStringEmpty(areknr) &&
		  Utils.isStringEmpty(fobcde) &&
		  Utils.isStringEmpty(fobknr) &&
		  Utils.isStringEmpty(arefob) &&
		  Utils.isStringEmpty(tlycde) &&
		  Utils.isStringEmpty(tlycde) &&
		  Utils.isStringEmpty(rdreori) &&
		  Utils.isStringEmpty(rdrnl);		
    }

}

