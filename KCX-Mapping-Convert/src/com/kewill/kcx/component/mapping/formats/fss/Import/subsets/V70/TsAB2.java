package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module           :  Import
 * Created          :  12.11.2012
 * Description  	:  2. Hauptsatz des Abgabenbescheids. 
 				 
 *
 * @author  iwaniuk
 * @version 6.4.00
 */

public class TsAB2 extends Teilsatz {
   
    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String regdat		 = "";	 // Registrierdatum
    private String rkzsi		 = "";	 // Registrierkennzeichen Sicherheit
    private String zzskto		 = "";	 // Kontonummer Zollzahlstelle
    private String zzsblz		 = "";	 // Bankleitzahl Zollzahlstelle
    private String zzsbnk		 = "";	 // Kreditinstitut Zollzahlstelle
    private String gabgbe		 = "";	 // Gesamtabgabenbetrag
    private String gsilbe		 = "";	 // Summe der zu erhebenden Sicherheiten
    private String gsicbe		 = "";	 // Summe der errechneten Sicherheiten
    private String bsicbe		 = "";	 // Barsicherheit
    private String unbsic		 = "";	 // Summe der unbar zu leistenden Sicherheiten
    private String frmsic		 = "";	 // Form der Sicherheitsleistung
    private String vrzsic		 = "";	 // Verzicht auf Sicherheitsleistung
    private String anmust		 = "";	 // Anmelder Umsatzsteuer ID
    private String kzvsta		 = "";	 // KZ Vorsteuerabzug
    private String kzgesa		 = "";	 // Kennzeichen Gesamtschuldnerschaft
    //V70: private String rechzb		 = "";	 // Zollnummer Für Rechnung
    private String kzxml		 = "";   //new V70

    public TsAB2() {
	    tsTyp = "AB2";
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
	    regdat = fields[3];

	    if (size < 5) { return; }
	    rkzsi = fields[4];

	    if (size < 6) { return; }
	    zzskto = fields[5];

	    if (size < 7) { return; }
	    zzsblz = fields[6];

	    if (size < 8) { return; }
	    zzsbnk = fields[7];

	    if (size < 9) { return; }
	    gabgbe = fields[8];

	    if (size < 10) { return; }
	    gsilbe = fields[9];

	    if (size < 11) { return; }
	    gsicbe = fields[10];

	    if (size < 12) { return; }
	    bsicbe = fields[11];

	    if (size < 13) { return; }
	    unbsic = fields[12];

	    if (size < 14) { return; }
	    frmsic = fields[13];

	    if (size < 15) { return; }
	    vrzsic = fields[14];

	    if (size < 16) { return; }
	    anmust = fields[15];

	    if (size < 17) { return; }
	    kzvsta = fields[16];

	    if (size < 18) { return; }
	    kzgesa = fields[17];

	    if (size < 19) { return; }
	    kzxml = fields[18];

	    
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

    public String getRegdat() {
    	 return regdat;
    }
    public void setRegdat(String regdat) {
    	this.regdat = Utils.checkNull(regdat);
    }

    public String getRkzsi() {
    	 return rkzsi;
    }
    public void setRkzsi(String rkzsi) {
    	this.rkzsi = Utils.checkNull(rkzsi);
    }

    public String getZzskto() {
    	 return zzskto;
    }
    public void setZzskto(String zzskto) {
    	this.zzskto = Utils.checkNull(zzskto);
    }

    public String getZzsblz() {
    	 return zzsblz;
    }
    public void setZzsblz(String zzsblz) {
    	this.zzsblz = Utils.checkNull(zzsblz);
    }

    public String getZzsbnk() {
    	 return zzsbnk;
    }
    public void setZzsbnk(String zzsbnk) {
    	this.zzsbnk = Utils.checkNull(zzsbnk);
    }

    public String getGabgbe() {
    	 return gabgbe;
    }
    public void setGabgbe(String gabgbe) {
    	this.gabgbe = Utils.checkNull(gabgbe);
    }

    public String getGsilbe() {
    	 return gsilbe;
    }
    public void setGsilbe(String gsilbe) {
    	this.gsilbe = Utils.checkNull(gsilbe);
    }

    public String getGsicbe() {
    	 return gsicbe;
    }
    public void setGsicbe(String gsicbe) {
    	this.gsicbe = Utils.checkNull(gsicbe);
    }

    public String getBsicbe() {
    	 return bsicbe;
    }
    public void setBsicbe(String bsicbe) {
    	this.bsicbe = Utils.checkNull(bsicbe);
    }

    public String getUnbsic() {
    	 return unbsic;
    }
    public void setUnbsic(String unbsic) {
    	this.unbsic = Utils.checkNull(unbsic);
    }

    public String getFrmsic() {
    	 return frmsic;
    }
    public void setFrmsic(String frmsic) {
    	this.frmsic = Utils.checkNull(frmsic);
    }

    public String getVrzsic() {
    	 return vrzsic;
    }
    public void setVrzsic(String vrzsic) {
    	this.vrzsic = Utils.checkNull(vrzsic);
    }

    public String getAnmust() {
    	 return anmust;
    }
    public void setAnmust(String anmust) {
    	this.anmust = Utils.checkNull(anmust);
    }

    public String getKzvsta() {
    	 return kzvsta;
    }
    public void setKzvsta(String kzvsta) {
    	this.kzvsta = Utils.checkNull(kzvsta);
    }

    public String getKzgesa() {
    	 return kzgesa;
    }


    public void setKzgesa(String kzgesa) {
    	this.kzgesa = Utils.checkNull(kzgesa);
    }
    public String getKzxml() {
    	 return kzxml;
    }

    public void setKzxml(String rechzb) {
    	this.kzxml = Utils.checkNull(rechzb);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);    	
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(regdat);
    	buff.append(trenner);
    	buff.append(rkzsi);
    	buff.append(trenner);
    	buff.append(zzskto);
    	buff.append(trenner);
    	buff.append(zzsblz);
    	buff.append(trenner);
    	buff.append(zzsbnk);
    	buff.append(trenner);
    	buff.append(gabgbe);
    	buff.append(trenner);
    	buff.append(gsilbe);
    	buff.append(trenner);
    	buff.append(gsicbe);
    	buff.append(trenner);
    	buff.append(bsicbe);
    	buff.append(trenner);
    	buff.append(unbsic);
    	buff.append(trenner);
    	buff.append(frmsic);
    	buff.append(trenner);
    	buff.append(vrzsic);
    	buff.append(trenner);
    	buff.append(anmust);
    	buff.append(trenner);
    	buff.append(kzvsta);
    	buff.append(trenner);
    	buff.append(kzgesa);
    	buff.append(trenner);
    	buff.append(kzxml);
    	buff.append(trenner);

    	return new String(buff);
    
    }


    public boolean isEmpty() {
		return  Utils.isStringEmpty(regdat) &&
	    	Utils.isStringEmpty(rkzsi) &&
	    	Utils.isStringEmpty(zzskto) &&
	    	Utils.isStringEmpty(zzsblz) &&
	    	Utils.isStringEmpty(zzsbnk) &&
	    	Utils.isStringEmpty(gabgbe) &&
	    	Utils.isStringEmpty(gsilbe) &&
	    	Utils.isStringEmpty(gsicbe) &&
	    	Utils.isStringEmpty(bsicbe) &&
	    	Utils.isStringEmpty(unbsic) &&
	    	Utils.isStringEmpty(frmsic) &&
	    	Utils.isStringEmpty(vrzsic) &&
	    	Utils.isStringEmpty(anmust) &&
	    	Utils.isStringEmpty(kzvsta) &&
	    	Utils.isStringEmpty(kzgesa) &&
	    	Utils.isStringEmpty(kzxml);
    }

}
