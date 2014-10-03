package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsABU
 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Abgabenzeilen.
 *								Abgabenzeilen ABU können bis zu 20 mal pro Position vorkommen; 
								alle Angaben sind auf je eine Art der Abgabe bezogen.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsABU extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String abus		 	 = "";	 // Art der Abgabe
    private String abgru		 = "";	 // Abgabengruppe
    private String brhin		 = "";	 // Berechnungshinweis
    private String erabbe		 = "";	 // errechneter Abgabenbetrag
    private String abbe		 	 = "";	 // Abgabenbetrag
    private String apvmin		 = "";	 // angewandter PV-Minderungsbetrag
    private String vsme		 	 = "";	 // Maßeinheit zur Verbrauchssteuer
    private String qvsme		 = "";	 // Qualifikator zur Maßeinheit zur Ver-brauchssteuer
    private String vscd		 	 = "";	 // Verbrauchssteuercode	siehe EZT-Modul
    private String vsgp		 	 = "";	 // Grad/Prozent zur Verbrauchssteuer
    private String vsmg		 	 = "";	 // Verbrauchssteuermenge
    private String vswe		 	 = "";	 // Verbrauchssteuerwert
    private String kontnr		 = "";	 // Kontingentnummer zum Abgabensatz

    public TsABU() {
	    tsTyp = "ABU";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

		if (size < 2) { return; }
		regkz = fields[1];
	
		if (size < 3) { return; }
		lfdnr = fields[2];
	
		if (size < 4) { return; }
		posnr = fields[3];
	
		if (size < 5) { return; }
		abus = fields[4];
	
		if (size < 6) { return; }
		abgru = fields[5];
	
		if (size < 7) { return; }
		brhin = fields[6];
	
		if (size < 8) { return; }
		erabbe = fields[7];
	
		if (size < 9) { return; }
		abbe = fields[8];
	
		if (size < 10) { return; }
		apvmin = fields[9];
	
		if (size < 11) { return; }
		vsme = fields[10];
	
		if (size < 12) { return; }
		qvsme = fields[11];
	
		if (size < 13) { return; }
		vscd = fields[12];
	
		if (size < 14) { return; }
		vsgp = fields[13];
	
		if (size < 15) { return; }
		vsmg = fields[14];
	
		if (size < 16) { return; }
		vswe = fields[15];
	
		if (size < 17) { return; }
		kontnr = fields[16];
    }


    public String getRegkz() {
    	 return regkz;
    }

    public void setRegkz(String regkz) {
    	this.regkz = Utils.checkNull(regkz);
    }


    public String getLfdnr() {
    	 return lfdnr;
    }


    public void setLfdnr(String lfdnr) {
    	this.lfdnr = Utils.checkNull(lfdnr);
    }


    public String getPosnr() {
    	return posnr;
    }


    public void setPosnr(String posnr) {
    	this.posnr = Utils.checkNull(posnr);
    }


    public String getAbus() {
    	return abus;
    }


    public void setAbus(String abus) {
    	this.abus = Utils.checkNull(abus);
    }


    public String getAbgru() {
    	return abgru;
    }


    public void setAbgru(String abgru) {
    	this.abgru = Utils.checkNull(abgru);
    }


    public String getBrhin() {
    	return brhin;
    }


    public void setBrhin(String brhin) {
    	this.brhin = Utils.checkNull(brhin);
    }


    public String getErabbe() {
    	return erabbe;
    }


    public void setErabbe(String erabbe) {
    	this.erabbe = Utils.checkNull(erabbe);
    }


    public String getAbbe() {
    	return abbe;
    }


    public void setAbbe(String abbe) {
    	this.abbe = Utils.checkNull(abbe);
    }


    public String getApvmin() {
    	return apvmin;
    }


    public void setApvmin(String apvmin) {
    	this.apvmin = Utils.checkNull(apvmin);
    }


    public String getVsme() {
    	return vsme;
    }


    public void setVsme(String vsme) {
    	this.vsme = Utils.checkNull(vsme);
    }


    public String getQvsme() {
    	return qvsme;
    }


    public void setQvsme(String qvsme) {
    	this.qvsme = Utils.checkNull(qvsme);
    }


    public String getVscd() {
    	return vscd;
    }

    public void setVscd(String vscd) {
    	this.vscd = Utils.checkNull(vscd);
    }


    public String getVsgp() {
    	return vsgp;
    }


    public void setVsgp(String vsgp) {
    	this.vsgp = Utils.checkNull(vsgp);
    }


    public String getVsmg() {
    	return vsmg;
    }


    public void setVsmg(String vsmg) {
    	this.vsmg = Utils.checkNull(vsmg);
    }


    public String getVswe() {
    	return vswe;
    }


    public void setVswe(String vswe) {
    	this.vswe = Utils.checkNull(vswe);
    }


    public String getKontnr() {
    	return kontnr;
    }


    public void setKontnr(String kontnr) {
    	this.kontnr = Utils.checkNull(kontnr);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(abus);
    	buff.append(trenner);
    	buff.append(abgru);
    	buff.append(trenner);
    	buff.append(brhin);
    	buff.append(trenner);
    	buff.append(erabbe);
    	buff.append(trenner);
    	buff.append(abbe);
    	buff.append(trenner);
    	buff.append(apvmin);
    	buff.append(trenner);
    	buff.append(vsme);
    	buff.append(trenner);
    	buff.append(qvsme);
    	buff.append(trenner);
    	buff.append(vscd);
    	buff.append(trenner);
    	buff.append(vsgp);
    	buff.append(trenner);
    	buff.append(vsmg);
    	buff.append(trenner);
    	buff.append(vswe);
    	buff.append(trenner);
    	buff.append(kontnr);
    	buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
       return Utils.isStringEmpty(abus) &&
    	Utils.isStringEmpty(abgru) &&
    	Utils.isStringEmpty(brhin) &&
    	Utils.isStringEmpty(erabbe) &&
    	Utils.isStringEmpty(abbe) &&
    	Utils.isStringEmpty(apvmin) &&
    	Utils.isStringEmpty(vsme) &&
    	Utils.isStringEmpty(qvsme) &&
    	Utils.isStringEmpty(vscd) &&
    	Utils.isStringEmpty(vsgp) &&
    	Utils.isStringEmpty(vsmg) &&
    	Utils.isStringEmpty(vswe) &&
    	Utils.isStringEmpty(kontnr);
    }
}
