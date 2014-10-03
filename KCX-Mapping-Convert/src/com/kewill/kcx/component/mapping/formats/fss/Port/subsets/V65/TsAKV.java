package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.Transport Vorlauf / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKV extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String vtpcde	= "";	 // Vorlauf Transportart, Code
	 private String vtpid	= "";	 // Vorlauf Funkrufzeichen
	 private String vtpken	= "";	 // Vorlauf Transportmittel Identifikation
	 private String vlifda	= "";	 // Vorlauf voraussichtliches Lieferdatum
	 private String trkvrm 	= "";	 // Vermerke zur An- bzw. Auslieferung 
	 private String trknam	= "";	 // Name des Truckunternehmens 
	 private String trkcde	= "";	 // Trucker-DBH-Code 
	
    public TsAKV() {
	    tsTyp = "AKV";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    vtpcde = fields[2];
	    if (size < 4) { return; }
	    vtpid = fields[3];
	    if (size < 5) { return; }
	    vtpken = fields[4];
	    if (size < 6) { return; }
	    vlifda = fields[5];
	    if (size < 7) { return; }
	    trkvrm = fields[6];
	    if (size < 8) { return; }
	    trknam = fields[7];
	    if (size < 9) { return; }
	    trkcde = fields[8];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(vtpcde);
    	buff.append(trenner);
    	buff.append(vtpid);
    	buff.append(trenner);
    	buff.append(vtpken);
    	buff.append(trenner);
    	buff.append(vlifda);
    	buff.append(trenner);
    	buff.append(trkvrm);
    	buff.append(trenner);
    	buff.append(trknam);
    	buff.append(trenner);
    	buff.append(trkcde);
    	buff.append(trenner);    	
    	
    	return new String(buff);    
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getVtpcde() {
    	 return vtpcde;
    }
    public void setVtpcde(String vtpcde) {
    	this.vtpcde = Utils.checkNull(vtpcde);
    }

    public String getVtpid() {
    	 return vtpid;
    }
    public void setVtpid(String vtpid) {
    	this.vtpid = Utils.checkNull(vtpid);
    }

    public String getVtpken() {
    	 return vtpken;
    }
    public void setVtpken(String vtpken) {
    	this.vtpken = Utils.checkNull(vtpken);
    }

    public String getVlifda() {
    	 return vlifda;
    }
    public void setVlifda(String vlifda) {
    	this.vlifda = Utils.checkNull(vlifda);
    }
    
    public String getTrkvrm() {
   	 	return trkvrm;
    }
    public void setTrkvrm(String argument) {
    	this.trkvrm = Utils.checkNull(argument);
    }
   
    public String getTrknam() {
   	 	return trknam;
    }
    public void setTrknam(String argument) {
    	this.trknam = Utils.checkNull(argument);
    }
   
    public String getTrkcde() {
   	 	return trkcde;
    }
    public void setTrkcde(String argument) {
    	this.trkcde = Utils.checkNull(argument);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(vtpcde) &&
		  Utils.isStringEmpty(vtpid) &&
		  Utils.isStringEmpty(vtpken) &&
		  Utils.isStringEmpty(vlifda) &&
		  Utils.isStringEmpty(trkvrm) &&
		  Utils.isStringEmpty(trknam) &&		 
		  Utils.isStringEmpty(trkcde);
    }
    
}

