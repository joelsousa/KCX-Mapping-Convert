package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Kopfdaten. 
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAED extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer des Hafenauftrags 	 
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3	
	 private String regnr	= "";	 // Nummer der AE
	 private String artzol 	= "";	 // Art der Anmeldung
	 private String zbanm	= "";	 // 
	 private String ldbe	= "";	 // 
	 private String ldve	= "";	 // 
	 private String veomrn	= "";	 // MRN des Versandscheines
	 private String kz100	= "";	 // Kz. Wert > 1000 EUR 
	 private String kzbef	= "";	 // Kennzeichen Befreiung 
	 private String kzmow 	= "";	 // Kennzeichen Marktordnungswaren 
	 private String regmow	= "";	 // Registrier-/Bezugsnummer (DOK); ATB-Nr. der summar. Anmeldung (MIT) 	
	 private String kdnran  = "";	 // 
	 private String kdnrve  = "";	 // 
	 private String kdnrem  = "";	 // 
	 private String kdnrmi  = "";	 // 
	 private String esuma  = "";	 // 
	 private String asuma  = "";	 // 
	 private String vorpap  = "";	 // 
	 private String bewnr  = "";	 // 
	 private String anmid  = "";	 // 
	 private String empid  = "";	 // 
	 private String befrei  = "";	 // 
	 private String pruef  = "";	 // 
	 private String extdst  = "";	 // 
	
	 
    public TsAED() {
    	tsTyp = "AED";
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
	    regnr = fields[5];
	    if (size < 7) { return; }
	    artzol = fields[6];
	    if (size < 8) { return; }
	    zbanm = fields[7];
	    if (size < 9) { return; }
	    ldbe = fields[8];
	    if (size < 10) { return; }
	    ldve = fields[9];
	    if (size < 11) { return; }
	    veomrn = fields[10];
	    if (size < 12) { return; }
	    kz100 = fields[11];
	    if (size < 13) { return; }
	    kzbef = fields[12];
	    if (size < 14) { return; }
	    kzmow = fields[13];
	    if (size < 15) { return; }
	    regmow = fields[14];
	    if (size < 16) { return; }
	    kdnran = fields[15];
	    if (size < 17) { return; }
	    kdnrve = fields[16];
	    if (size < 18) { return; }
	    kdnrem = fields[17];	    
	    if (size < 19) { return; }
	    kdnrmi = fields[18];
	    if (size < 20) { return; }
	    esuma = fields[19];
	    if (size < 21) { return; }
	    asuma = fields[20];
	    if (size < 22) { return; }
	    vorpap = fields[21];
	    if (size < 23) { return; }
	    bewnr = fields[22];
	    if (size < 24) { return; }
	    anmid = fields[23];
	    if (size < 25) { return; }
	    empid = fields[24];
	    if (size < 26) { return; }
	    befrei = fields[25];
	    if (size < 27) { return; }
	    pruef = fields[26];
	    if (size < 28) { return; }
	    extdst = fields[27];
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
    	buff.append(regnr);
    	buff.append(trenner);
    	buff.append(artzol);
    	buff.append(trenner);
    	buff.append(zbanm);
    	buff.append(trenner);
    	buff.append(ldbe);
    	buff.append(trenner);
    	buff.append(ldve);
    	buff.append(trenner);
    	buff.append(veomrn);
    	buff.append(trenner);
    	buff.append(kz100);
    	buff.append(trenner);
    	buff.append(kzbef);
    	buff.append(trenner);
    	buff.append(kzmow);
    	buff.append(trenner);
    	buff.append(regmow);
    	buff.append(trenner);
    	buff.append(kdnran);
    	buff.append(trenner);
    	buff.append(kdnrve);
    	buff.append(trenner);
    	buff.append(kdnrem);
    	buff.append(trenner);
    	buff.append(kdnrmi);
    	buff.append(trenner);
    	buff.append(esuma);
    	buff.append(trenner);
    	buff.append(asuma);
    	buff.append(trenner);
    	buff.append(vorpap);
    	buff.append(trenner);
    	buff.append(bewnr);
    	buff.append(trenner);
    	buff.append(anmid);
    	buff.append(trenner);
    	buff.append(empid);
    	buff.append(trenner);
    	buff.append(befrei);
    	buff.append(trenner);
    	buff.append(pruef);
    	buff.append(trenner);
    	buff.append(extdst);
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

    public String getRegnr() {
    	 return regnr;
    }
    public void setRegnr(String argument) {
    	this.regnr = Utils.checkNull(argument);
    }
    
    public String getArtzol() {
   	 	return artzol;
    }
    public void setArtzol(String argument) {
    	this.artzol = Utils.checkNull(argument);
    }
   
    public String getZbanm() {
   	 	return zbanm;
    }
    public void setZbanm(String argument) {
    	this.zbanm = Utils.checkNull(argument);
    }
   
    public String getLdbe() {
   	 	return ldbe;
    }
    public void setLdbe(String argument) {
    	this.ldbe = Utils.checkNull(argument);
    }
       
    public String getLdve() {
   	 	return ldve;
    }
    public void setLdve(String argument) {
    	this.ldve = Utils.checkNull(argument);
    }
   
    public String getVeomrn() {
   	 	return veomrn;
    }
    public void setVeomrn(String argument) {
    	this.veomrn = Utils.checkNull(argument);
    }
    
    public String getKz100() {
   	 	return kz100;
    }
    public void setKz100(String argument) {
    	this.kz100 = Utils.checkNull(argument);
    }
    
    public String getKzbef() {
   	 	return kzbef;
    }
    public void setKzbef(String argument) {
    	this.kzbef = Utils.checkNull(argument);
    }
   
    public String getKzmow() {
   	 	return kzmow;
    }
    public void setKzmow(String argument) {
    	this.kzmow = Utils.checkNull(argument);
    }
    
    public String getRegmow() {
   	 	return regmow;
    }
    public void setRegmow(String argument) {
    	this.regmow = Utils.checkNull(argument);
    }
    
    public String getKdnran() {
   	 	return kdnran;
    }
    public void setKdnran(String argument) {
    	this.kdnran = Utils.checkNull(argument);
    }

    public String getKdnrve() {
   	 	return kdnrve;
    }
    public void setKdnrve(String argument) {
    	this.kdnrve = Utils.checkNull(argument);
    }
    
    public String getKdnrem() {
   	 	return kdnrem;
    }
    public void setKdnrem(String argument) {
    	this.kdnrem = Utils.checkNull(argument);
    }
    
    public String getKdnrmi() {
   	 	return kdnrmi;
    }
    public void setKdnrmi(String argument) {
    	this.kdnran = Utils.checkNull(argument);
    }
    
    public String getEsuma() {
   	 	return esuma;
    }
    public void setEsuma(String argument) {
    	this.esuma = Utils.checkNull(argument);
    }
    
    public String getAsuma() {
   	 	return asuma;
    }
    public void setAsuma(String argument) {
    	this.asuma = Utils.checkNull(argument);
    }
    
    public String getVorpap() {
   	 	return vorpap;
    }
    public void setVorpap(String argument) {
    	this.vorpap = Utils.checkNull(argument);
    }
 
    public String getBewnr() {
   	 	return bewnr;
    }
    public void setBewnr(String argument) {
    	this.bewnr = Utils.checkNull(argument);
    }
    
    public String getAnmid() {
   	 	return anmid;
    }
    public void setAnmid(String argument) {
    	this.anmid = Utils.checkNull(argument);
    }
    
    public String getEmpid() {
   	 	return empid;
    }
    public void setEmpid(String argument) {
    	this.empid = Utils.checkNull(argument);
    }
    
    public String getBefrei() {
   	 	return befrei;
    }
    public void setBefrei(String argument) {
    	this.befrei = Utils.checkNull(argument);
    }
    
    public String getPruef() {
   	 	return pruef;
    }
    public void setPruef(String argument) {
    	this.pruef = Utils.checkNull(argument);
    }
    
    public String getExtdst() {
   	 	return extdst;
    }
    public void setExtdst(String argument) {
    	this.extdst = Utils.checkNull(argument);
    }
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(regnr) &&
		  Utils.isStringEmpty(artzol) &&		 
		  Utils.isStringEmpty(zbanm) &&
		  Utils.isStringEmpty(ldbe) &&
		  Utils.isStringEmpty(ldve) &&
		  Utils.isStringEmpty(veomrn) &&
		  Utils.isStringEmpty(kz100) &&
		  Utils.isStringEmpty(kzbef) &&
		  Utils.isStringEmpty(kzmow) &&
		  Utils.isStringEmpty(regmow) &&		
		  Utils.isStringEmpty(kdnran);
    }

}

