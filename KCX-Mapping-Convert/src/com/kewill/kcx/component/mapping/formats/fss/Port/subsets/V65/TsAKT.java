package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.Transportdaten / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKT extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String crnnr	= "";	 // Hafensystem Schiffs-/ Abfahrtsnummer;	
	 private String rswnr	= "";	 // Reisenummer des Reeders/Maklers (Hauptlauf)
	 private String frafco	= "";	 // Frachtführer/Reeder  (Hauptlauf)
	 private String rufzei	= "";	 // Funkrufzeichen (Hauptlauf
	 private String shpnam 	= "";	 // Schiffsname 
	 private String eta		= "";	 // Ankunftsdatum 
	 private String ets		= "";	 // Abfahrtsdatum 
	 private String polloc	= "";	 // Ladeort, -hafen
	 private String podloc	= "";	 // Löschhafen
	 private String poeloc	= "";	 // Endhafen
	 private String ortbco	= "";	 // Bestimmungsort Code
	 private String ortbca 	= "";	 // Bestimmungsort Code Art
	 private String ortbna	= "";	 // Bestimmungsort Name	
	 private String imonr	= "";	 // IMO-Nummer Schiff
	 
    public TsAKT() {
	    tsTyp = "AKT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    crnnr = fields[2];
	    if (size < 4) { return; }
	    rswnr = fields[3];
	    if (size < 5) { return; }
	    frafco = fields[4];
	    if (size < 6) { return; }
	    rufzei = fields[5];
	    if (size < 7) { return; }
	    shpnam = fields[6];
	    if (size < 8) { return; }
	    eta = fields[7];
	    if (size < 9) { return; }
	    ets = fields[8];
	    if (size < 10) { return; }
	    polloc = fields[9];
	    if (size < 11) { return; }
	    podloc = fields[10];
	    if (size < 12) { return; }
	    poeloc = fields[11];
	    if (size < 13) { return; }
	    ortbco = fields[12];
	    if (size < 14) { return; }
	    ortbca = fields[13];
	    if (size < 15) { return; }
	    ortbna = fields[14];
	    if (size < 16) { return; }
	    imonr = fields[15];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(crnnr);
    	buff.append(trenner);
    	buff.append(rswnr);
    	buff.append(trenner);
    	buff.append(frafco);
    	buff.append(trenner);
    	buff.append(rufzei);
    	buff.append(trenner);
    	buff.append(shpnam);
    	buff.append(trenner);
    	buff.append(eta);
    	buff.append(trenner);
    	buff.append(ets);
    	buff.append(trenner);
    	buff.append(polloc);
    	buff.append(trenner);
    	buff.append(podloc);
    	buff.append(trenner);
    	buff.append(poeloc);
    	buff.append(trenner);
    	buff.append(ortbco);
    	buff.append(trenner);
    	buff.append(ortbca);
    	buff.append(trenner);
    	buff.append(ortbna);
    	buff.append(trenner);
    	buff.append(imonr);
    	buff.append(trenner);
    	
    	return new String(buff);   
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getCrnnr() {
    	 return crnnr;
    }
    public void setCrnnr(String crnnr) {
    	this.crnnr = Utils.checkNull(crnnr);
    }

    public String getRswnr() {
    	 return rswnr;
    }
    public void setRswnr(String rswnr) {
    	this.rswnr = Utils.checkNull(rswnr);
    }

    public String getFrafco() {
    	 return frafco;
    }
    public void setFrafco(String frafco) {
    	this.frafco = Utils.checkNull(frafco);
    }

    public String getRufzei() {
    	 return rufzei;
    }
    public void setRufzei(String rufzei) {
    	this.rufzei = Utils.checkNull(rufzei);
    }
    
    public String getShpnam() {
   	 	return shpnam;
    }
    public void setShpnam(String argument) {
    	this.shpnam = Utils.checkNull(argument);
    }
   
    public String getEta() {
   	 	return eta;
    }
    public void setEta(String argument) {
    	this.eta = Utils.checkNull(argument);
    }
   
    public String getEts() {
   	 	return ets;
    }
    public void setEts(String argument) {
    	this.ets = Utils.checkNull(argument);
    }
       
    public String getPolloc() {
   	 	return polloc;
    }
    public void setPolloc(String argument) {
    	this.polloc = Utils.checkNull(argument);
    }
   
    public String getPodloc() {
   	 	return podloc;
    }
    public void setPodloc(String argument) {
    	this.podloc = Utils.checkNull(argument);
    }
    
    public String getPoeloc() {
   	 	return poeloc;
    }
    public void setPoeloc(String argument) {
    	this.poeloc = Utils.checkNull(argument);
    }
    
    public String getOrtbco() {
   	 	return ortbco;
    }
    public void setOrtbco(String argument) {
    	this.ortbco = Utils.checkNull(argument);
    }
   
    public String getOrtbca() {
   	 	return ortbca;
    }
    public void setOrtbca(String argument) {
    	this.ortbca = Utils.checkNull(argument);
    }
    
    public String getOrtbna() {
   	 	return ortbna;
    }
    public void setOrtbna(String argument) {
    	this.ortbna = Utils.checkNull(argument);
    }
    
    public String getImonr() {
   	 	return imonr;
    }
    public void setImonr(String argument) {
    	this.imonr = Utils.checkNull(argument);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(crnnr) &&
		  Utils.isStringEmpty(rswnr) &&
		  Utils.isStringEmpty(frafco) &&
		  Utils.isStringEmpty(frafco) &&
		  Utils.isStringEmpty(shpnam) &&
		  Utils.isStringEmpty(eta) &&
		  Utils.isStringEmpty(ets) &&
		  Utils.isStringEmpty(polloc) &&
		  Utils.isStringEmpty(podloc) &&
		  Utils.isStringEmpty(poeloc) &&
		  Utils.isStringEmpty(ortbco) &&
		  Utils.isStringEmpty(ortbca) &&
		  Utils.isStringEmpty(ortbna) &&		
		  Utils.isStringEmpty(imonr);
    }

}

