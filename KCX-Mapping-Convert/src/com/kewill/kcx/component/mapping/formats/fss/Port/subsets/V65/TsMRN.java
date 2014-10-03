package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag Zusatzangaben  / MsgPOR. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsMRN extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3
	 private String mrn	    = "";	 // MRN
	 private String mrnpo 	= "";	 // MRN-Positionsnummer	 
	 private String mrnid	= "";	 // MRN Packstück-ID
	 private String mrnall	= "";	 // Kz. MRN-Nummer vollständig in der Anmeldung
	 private String mrnmin	= "";	 // Kz. MRN-Mindermenge
	 private String eigmas	= "";	 // Eigenmasse für Mindermenge
	 private String rohm	= "";	 // Rohmasse für Mindermenge
	 private String kzkomp	= "";	 // KNZ MRN-Position komplett abmelden
	 private String ldbe	= "";	 // Bestimmungsland
	 private String ldve 	= "";	 // Versendungsland
	
    public TsMRN() {
	    tsTyp = "MRN";
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
	    mrn = fields[5];
	    if (size < 7) { return; }
	    mrnpo = fields[6];	 
	    if (size < 8) { return; }
	    mrnid = fields[7];
	    if (size < 9) { return; }
	    mrnall = fields[8];
	    if (size < 10) { return; }
	    mrnmin = fields[9];
    	if (size < 11) { return; }
    	eigmas = fields[10];
	    if (size < 12) { return; }
	    rohm = fields[11];
	    if (size < 13) { return; }
	    kzkomp = fields[12];
	    if (size < 14) { return; }
	    ldbe = fields[13];
	    if (size < 15) { return; }
	    ldve = fields[14];	    
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
    	buff.append(mrn);
    	buff.append(trenner);
    	buff.append(mrnpo);
    	buff.append(trenner);
    	buff.append(mrnid);
    	buff.append(trenner);
    	buff.append(mrnall);
    	buff.append(trenner);
    	buff.append(mrnmin);
    	buff.append(trenner);
    	buff.append(eigmas);
    	buff.append(trenner);
    	buff.append(rohm);
    	buff.append(trenner);
    	buff.append(kzkomp);
    	buff.append(trenner);
    	buff.append(ldbe);
    	buff.append(trenner);
    	buff.append(ldve);
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

    public String getMrn() {
    	 return mrn;
    }
    public void setMrn(String mrn) {
    	this.mrn = Utils.checkNull(mrn);
    }
    
    public String getMrnpo() {
   	 	return mrnpo;
    }
    public void setMrnpo(String argument) {
    	this.mrnpo = Utils.checkNull(argument);
    }
    
    public String getMrnid() {
    	return mrnid;
    }
    public void setMrnid(String mrn) {
   	 	this.mrnid = Utils.checkNull(mrn);
    }
   
    public String getMrnall() {
  	 	return mrnall;
    }
    public void setMrnall(String argument) {
    	this.mrnall = Utils.checkNull(argument);
    }
   
    public String getMrnmin() {
    	return mrnmin;
    }
    public void setMrnmin(String mrn) {
    	this.mrnmin = Utils.checkNull(mrn);
    }
  
    public String getEigmas() {
 	 	return eigmas;
    }
    public void setEigmas(String argument) {
    	this.eigmas = Utils.checkNull(argument);
    }
  
    public String getRohm() {
    	return rohm;
    }
    public void setRohm(String mrn) {
    	this.rohm = Utils.checkNull(mrn);
    }
 
    public String getKzkomp() {
	 	return kzkomp;
    }
    public void setKzkomp(String argument) {
    	this.kzkomp = Utils.checkNull(argument);
    }
 
    public String getLdbe() {
    	return ldbe;
    }
    public void setLdbe(String mrn) {
    	this.ldbe = Utils.checkNull(mrn);
    }

    public String getLdve() {
	 	return ldve;
    }
    public void setLdve(String argument) {
    	this.ldve = Utils.checkNull(argument);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(mrn) &&
		  Utils.isStringEmpty(mrnall) &&
		  Utils.isStringEmpty(kzkomp) &&
		  Utils.isStringEmpty(mrnmin) &&
		  Utils.isStringEmpty(eigmas) &&		
		  Utils.isStringEmpty(rohm);
    }   
}

