package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V70; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Containerangaben.
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsACR extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String connr	= "";	 // Containernummer
	 private String typ		= "";	 // Containertyp ISO
	 private String owner	= "";	 // Container-/Shippers Owner
	 private String voller	= "";	 // Kennzeichen 4=voll / 5=leer	 
	 private String grgew 	= "";	 // 
	 private String trgew	= "";	 // 
	 private String negew	= "";	 // 
	 private String tempe	= "";	 // Temperatur Einheit
	 private String tempu	= "";	 // Temperatur Wert Untergrenze
	 private String tempo	= "";	 // Temperatur Wert Obergrenze
	 private String siegl1	= "";	 // Siegelnummer 
	 private String siegl2	= "";	 // Siegelnummer 
	 private String siegl3 	= "";	 // Siegelnummer 
	 private String siegl4	= "";	 // Siegelnummer 	
	 private String mod  	= "";	 // Angaben zur Kühlart
	 private String bunr  	= "";	 // Buchungsnummer
	 private String ladung  = "";	 // Containerladung, nur BHT: 1=sammler, 2=voll, 3=leer
	 private String prodid  = "";	 // new V70 Warencode: only BHT, ZAPP not used
	 
    public TsACR() {
	    tsTyp = "ACR";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    connr = fields[2];
	    if (size < 4) { return; }
	    typ = fields[3];
	    if (size < 5) { return; }
	    owner = fields[4];
	    if (size < 6) { return; }
	    voller = fields[5];
	    if (size < 7) { return; }
	    grgew = fields[6];
	    if (size < 8) { return; }
	    trgew = fields[7];
	    if (size < 9) { return; }
	    negew = fields[8];
	    if (size < 10) { return; }
	    tempe = fields[9];
	    if (size < 11) { return; }
	    tempu = fields[10];
	    if (size < 12) { return; }
	    tempo = fields[11];
	    if (size < 13) { return; }
	    siegl1 = fields[12];
	    if (size < 14) { return; }
	    siegl2 = fields[13];
	    if (size < 15) { return; }
	    siegl3 = fields[14];
	    if (size < 16) { return; }
	    siegl4 = fields[15];
	    if (size < 17) { return; }
	    mod = fields[16];
	    if (size < 18) { return; }
	    bunr = fields[17];
	    if (size < 19) { return; }
	    ladung = fields[18];
	    if (size < 20) { return; }
	    prodid = fields[19];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);
    	buff.append(typ);
    	buff.append(trenner);
    	buff.append(owner);
    	buff.append(trenner);
    	buff.append(voller);
    	buff.append(trenner);
    	buff.append(grgew);
    	buff.append(trenner);
    	buff.append(trgew);
    	buff.append(trenner);
    	buff.append(negew);
    	buff.append(trenner);
    	buff.append(tempe);
    	buff.append(trenner);
    	buff.append(tempu);
    	buff.append(trenner);
    	buff.append(tempo);
    	buff.append(trenner);
    	buff.append(siegl1);
    	buff.append(trenner);
    	buff.append(siegl2);
    	buff.append(trenner);
    	buff.append(siegl3);
    	buff.append(trenner);
    	buff.append(siegl4);
    	buff.append(trenner);
    	buff.append(mod);
    	buff.append(trenner);
    	buff.append(bunr);
    	buff.append(trenner);
    	buff.append(ladung);
    	buff.append(trenner); 
    	buff.append(prodid);
    	buff.append(trenner); 
    	
    	return new String(buff);   
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getConnr() {
    	 return connr;
    }
    public void setConnr(String connr) {
    	this.connr = Utils.checkNull(connr);
    }

    public String getTyp() {
    	 return typ;
    }
    public void setTyp(String typ) {
    	this.typ = Utils.checkNull(typ);
    }

    public String getOwner() {
    	 return owner;
    }
    public void setOwner(String owner) {
    	this.owner = Utils.checkNull(owner);
    }

    public String getVoller() {
    	 return voller;
    }
    public void setVoller(String voller) {
    	this.voller = Utils.checkNull(voller);
    }
    
    public String getGgrgew() {
   	 	return grgew;
    }
    public void setGrgew(String argument) {
    	this.grgew = Utils.checkNull(argument);
    }
   
    public String getTrgew() {
   	 	return trgew;
    }
    public void setTrgew(String argument) {
    	this.trgew = Utils.checkNull(argument);
    }
   
    public String getNegew() {
   	 	return negew;
    }
    public void setNegew(String argument) {
    	this.negew = Utils.checkNull(argument);
    }
       
    public String getTempe() {
   	 	return tempe;
    }
    public void setTempe(String argument) {
    	this.tempe = Utils.checkNull(argument);
    }
   
    public String getTempu() {
   	 	return tempu;
    }
    public void setTempu(String argument) {
    	this.tempu = Utils.checkNull(argument);
    }
    
    public String getTempo() {
   	 	return tempo;
    }
    public void setTempo(String argument) {
    	this.tempo = Utils.checkNull(argument);
    }
    
    public String getSiegl1() {
   	 	return siegl1;
    }
    public void setSiegl1(String argument) {
    	this.siegl1 = Utils.checkNull(argument);
    }
    
    public String getSiegl2() {
   	 	return siegl2;
    }
    public void setSiegl2(String argument) {
    	this.siegl2 = Utils.checkNull(argument);
    }
   
    public String getSiegl3() {
   	 	return siegl3;
    }
    public void setSiegl3(String argument) {
    	this.siegl3 = Utils.checkNull(argument);
    }
    
    public String getSiegl4() {
   	 	return siegl4;
    }
    public void setSiegl4(String argument) {
    	this.siegl4 = Utils.checkNull(argument);
    }
    
    public String getMod() {
   	 	return mod;
    }
    public void setMod(String argument) {
    	this.mod = Utils.checkNull(argument);
    }

    
    public String getBunr() {
   	 	return bunr;
    }
    public void setBunr(String argument) {
    	this.bunr = Utils.checkNull(argument);
    }
    
    public String getLadung() {
   	 	return ladung;
    }
    public void setLadung(String argument) {
    	this.ladung = Utils.checkNull(argument);
    }
    
    public String getProdid() {
   	 	return prodid;
    }
    public void setProdid(String argument) {
    	this.prodid = Utils.checkNull(argument);
    }
    
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(connr) &&
		  Utils.isStringEmpty(typ) &&
		  Utils.isStringEmpty(owner) &&
		  Utils.isStringEmpty(voller) &&
		  Utils.isStringEmpty(grgew) &&
		  Utils.isStringEmpty(trgew) &&
		  Utils.isStringEmpty(negew) &&
		  Utils.isStringEmpty(tempe) &&
		  Utils.isStringEmpty(tempo) &&
		  Utils.isStringEmpty(siegl1) &&
		  Utils.isStringEmpty(siegl2) &&
		  Utils.isStringEmpty(siegl3) &&
		  Utils.isStringEmpty(siegl4) &&		
		  Utils.isStringEmpty(mod);
    }

}

