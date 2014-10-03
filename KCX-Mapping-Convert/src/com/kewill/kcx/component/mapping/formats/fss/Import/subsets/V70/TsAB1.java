package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module           :  Import 70
 * Created          :  12.11.2012
 * Description 		:  1. Hauptsatz des Abgabenbescheids.

 *
 * @author  iwaniuk
 * @version 7.0.00
 */

public class TsAB1 extends Teilsatz {

    private String ab1bnr		 = "";	 // Bezugsnummer
    private String regnr		 = "";	 // Registriernummer
    private String korant		 = "";	 // Korrekturnummer
    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbeschieds
    private String antart		 = "";	 // Art der Zollanmeldung
    private String wkz		 	 = "";	 // W‰hrungskennzeichen
    private String bardat		 = "";	 // F‰lligkeitsdatum Barzahlung
    private String sicdat		 = "";	 // F‰lligkeitsdatum Sicherheitsleistung
    private String abgdat		 = "";	 // Datum der Mitteilung des Abgabenbescheids
    private String erfdat		 = "";	 // Datum der buchm‰ﬂigen Erfassung
    private String abes		 	 = "";	 // Kennzeichen Erledigung
    private String kzvtr		 = "";	 // Kennzeichen Vertetungsverh‰ltnis
    private String kzkbg		 = "";	 // Kennz. Kleinbetrag
    private String kzzaab		 = "";	 // Kennzeichen Zahlungsaufforderung Abgaben
    private String kzzaau		 = "";	 // Kennzeichen Zahlungsaufforderung Aufschub
    private String kzzasi		 = "";	 // Kennzeichen Zahlungsaufforderung Sicherheit
    //V70: private String anmzb		 = "";	 // Zollnummer des Anmelders
    private String dstnr		 = "";	 // Nummer der Dienststelle
    private String dstnam		 = "";	 // Name der Dienststelle
    private String zbearb		 = "";	 // Bearbeiter beim Zoll
    private String dsttel		 = "";	 // Telefonnummer der Dienststelle
    //V70: private String vertzb		 = "";	 // Zollnummer des Vertreters
    private String zzsnam		 = "";	 // Name der Zollzahlstelle
    //V70: private String empzb		 = "";	 // Zollnummer des Empf‰ngers
    private String kzrebe		 = "";	 // Kennzeichen Rechtsbehelf
    private String kzgein		 = "";	 // Kennzeichen Eingangszollstelle ge‰ndert
    private String rebhza		 = "";	 // Bezeichnung Rechtsbehelf HZA
    private String zzsiban		 = "";	 // IBAN der Zollzahlstelle
    private String zzsbic		 = "";	 // BIC der Zollzahlstelle
    private String kzeust		 = "";	 // Kennzeichen EUSt
    private String anmeori		 = "";	 // new V70
    private String anmnl		 = "";	 // new V70
    private String verteori		 = "";	 // new V70
    private String vertnl		 = "";	 // new V70
    private String empeori		 = "";	 // new V70
    private String empnl		 = "";	 // new V70
    private String recheori		 = "";	 // new V70
    private String rechnl		 = "";	 // new V70
    

    public TsAB1() {
	    tsTyp = "AB1";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    ab1bnr = fields[1];

	    if (size < 3) { return; }
	    regnr = fields[2];

	    if (size < 4) { return; }
	    korant = fields[3];

	    if (size < 5) { return; }
	    regkz = fields[4];

	    if (size < 6) { return; }
	    lfdnr = fields[5];

	    if (size < 7) { return; }
	    antart = fields[6];

	    if (size < 8) { return; }
	    wkz = fields[7];

	    if (size < 9) { return; }
	    bardat = fields[8];

	    if (size < 10) { return; }
	    sicdat = fields[9];

	    if (size < 11) { return; }
	    abgdat = fields[10];

	    if (size < 12) { return; }
	    erfdat = fields[11];

	    if (size < 13) { return; }
	    abes = fields[12];

	    if (size < 14) { return; }
	    kzvtr = fields[13];

	    if (size < 15) { return; }
	    kzkbg = fields[14];

	    if (size < 16) { return; }
	    kzzaab = fields[15];

	    if (size < 17) { return; }
	    kzzaau = fields[16];

	    if (size < 18) { return; }
	    kzzasi = fields[17];
	    if (size < 19) { return; }
	    dstnr = fields[18];
	    if (size < 20) { return; }
	    dstnam = fields[19];
	    if (size < 21) { return; }
	    zbearb = fields[20];
	    if (size < 22) { return; }
	    dsttel = fields[21];
	    if (size < 23) { return; }	   
	    zzsnam = fields[22];
	    if (size < 24) { return; }
	    kzrebe = fields[23];	    
	    if (size < 25) { return; }
	    kzgein = fields[24];
	    if (size < 26) { return; }
	    rebhza = fields[25];
	    if (size < 27) { return; }
	    zzsiban = fields[26];
	    if (size < 28) { return; }
	    zzsbic = fields[27];
	    if (size < 29) { return; }
	    kzeust = fields[28];	    
	    if (size < 30) { return; }
	    anmeori = fields[29];
	    if (size < 31) { return; }
	    anmnl = fields[30];
	    if (size < 32) { return; }
	    verteori = fields[31];
	    if (size < 33) { return; }
	    vertnl = fields[32];
	    if (size < 34) { return; }
	    empeori = fields[33];
	    if (size < 35) { return; }
	    empnl = fields[34];
	    if (size < 36) { return; }
	    recheori = fields[35];
	    if (size < 37) { return; }
	    rechnl = fields[36];
    }


    public String getAb1bnr() {
    	 return ab1bnr;
    }

    public void setAb1bnr(String ab1bnr) {
    	this.ab1bnr = Utils.checkNull(ab1bnr);
    }

    public String getRegnr() {
    	 return regnr;
    }

    public void setRegnr(String regnr) {
    	this.regnr = Utils.checkNull(regnr);
    }

    public String getKorant() {
    	 return korant;
    }

    public void setKorant(String korant) {
    	this.korant = Utils.checkNull(korant);
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

    public String getAntart() {
    	 return antart;
    }

    public void setAntart(String antart) {
    	this.antart = Utils.checkNull(antart);
    }

    public String getWkz() {
    	 return wkz;
    }

    public void setWkz(String wkz) {
    	this.wkz = Utils.checkNull(wkz);
    }

    public String getBardat() {
    	 return bardat;
    }

    public void setBardat(String bardat) {
    	this.bardat = Utils.checkNull(bardat);
    }

    public String getSicdat() {
    	 return sicdat;
    }

    public void setSicdat(String sicdat) {
    	this.sicdat = Utils.checkNull(sicdat);
    }

    public String getAbgdat() {
    	 return abgdat;
    }

    public void setAbgdat(String abgdat) {
    	this.abgdat = Utils.checkNull(abgdat);
    }

    public String getErfdat() {
    	 return erfdat;
    }


    public void setErfdat(String erfdat) {
    	this.erfdat = Utils.checkNull(erfdat);
    }

    public String getAbes() {
    	 return abes;
    }

    public void setAbes(String abes) {
    	this.abes = Utils.checkNull(abes);
    }

    public String getKzvtr() {
    	 return kzvtr;
    }

    public void setKzvtr(String kzvtr) {
    	this.kzvtr = Utils.checkNull(kzvtr);
    }

    public String getKzkbg() {
    	 return kzkbg;
    }

    public void setKzkbg(String kzkbg) {
    	this.kzkbg = Utils.checkNull(kzkbg);
    }

    public String getKzzaab() {
    	 return kzzaab;
    }

    public void setKzzaab(String kzzaab) {
    	this.kzzaab = Utils.checkNull(kzzaab);
    }

    public String getKzzaau() {
    	 return kzzaau;
    }

    public void setKzzaau(String kzzaau) {
    	this.kzzaau = Utils.checkNull(kzzaau);
    }

    public String getKzzasi() {
    	 return kzzasi;
    }

    public void setKzzasi(String kzzasi) {
    	this.kzzasi = Utils.checkNull(kzzasi);
    }

    public String getAnmeori() {
    	 return anmeori;
    }

    public void setAnmeori(String anmzb) {
    	this.anmeori = Utils.checkNull(anmzb);
    }

    public String getAnmnl() {
    	return anmnl;
    }

   public void setAnmnl(String anmzb) {
   		this.anmnl = Utils.checkNull(anmzb);
   }
   
    public String getDstnr() {
    	 return dstnr;
    }

    public void setDstnr(String dstnr) {
    	this.dstnr = Utils.checkNull(dstnr);
    }

    public String getDstnam() {
    	 return dstnam;
    }

    public void setDstnam(String dstnam) {
    	this.dstnam = Utils.checkNull(dstnam);
    }

    public String getZbearb() {
    	 return zbearb;
    }

    public void setZbearb(String zbearb) {
    	this.zbearb = Utils.checkNull(zbearb);
    }

    public String getDsttel() {
    	 return dsttel;
    }

    public void setDsttel(String dsttel) {
    	this.dsttel = Utils.checkNull(dsttel);
    }

    public String getVerteori() {
    	 return verteori;
    }

    public void setVerteori(String value) {
    	this.verteori = Utils.checkNull(value);
    }

    public String getVertnl() {
   	 	return vertnl;
    }

    public void setVertnl(String value) {
   		this.vertnl = Utils.checkNull(value);
    }
   
    public String getZzsnam() {
    	 return zzsnam;
    }

    public void setZzsnam(String zzsnam) {
    	this.zzsnam = Utils.checkNull(zzsnam);
    }

    public String getEmpeori() {
    	 return empeori;
    }

    public void setEmpeori(String value) {
    	this.empeori = Utils.checkNull(value);
    }

    public String getEmpnl() {
   	 	return empnl;
    }
    
    public void setEmpnl(String value) {
   		this.empnl = Utils.checkNull(value);
    }
    
    public String getKzrebe() {
    	 return kzrebe;
    }

    public void setKzrebe(String kzrebe) {
    	this.kzrebe = Utils.checkNull(kzrebe);
    }

    public String getKzgein() {
    	 return kzgein;
    }

    public void setKzgein(String kzgein) {
    	this.kzgein = Utils.checkNull(kzgein);
    }

    public String getRebhza() {
    	 return rebhza;
    }

    public void setRebhza(String rebhza) {
    	this.rebhza = Utils.checkNull(rebhza);
    }

    public String getZzsiban() {
    	 return zzsiban;
    }

    public void setZzsiban(String zzsiban) {
    	this.zzsiban = Utils.checkNull(zzsiban);
    }

    public String getZzsbic() {
    	 return zzsbic;
    }

    public void setZzsbic(String zzsbic) {
    	this.zzsbic = Utils.checkNull(zzsbic);
    }

    public String getKzeust() {
    	 return kzeust;
    }

    public void setKzeust(String kzeust) {
    	this.kzeust = Utils.checkNull(kzeust);
    }
    
    public String getRecheori() {
   	 	return recheori;
    }

    public void setRecheori(String value) {
    	this.recheori = Utils.checkNull(value);
    }

    public String getRechnl() {
  	 	return rechnl;
    }
   
    public void setRechnl(String value) {
  		this.rechnl = Utils.checkNull(value);
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(ab1bnr);
    	buff.append(trenner);
    	buff.append(regnr);
    	buff.append(trenner);
    	buff.append(korant);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(antart);
    	buff.append(trenner);
    	buff.append(wkz);
    	buff.append(trenner);
    	buff.append(bardat);
    	buff.append(trenner);
    	buff.append(sicdat);
    	buff.append(trenner);
    	buff.append(abgdat);
    	buff.append(trenner);
    	buff.append(erfdat);
    	buff.append(trenner);
    	buff.append(abes);
    	buff.append(trenner);
    	buff.append(kzvtr);
    	buff.append(trenner);
    	buff.append(kzkbg);
    	buff.append(trenner);
    	buff.append(kzzaab);
    	buff.append(trenner);
    	buff.append(kzzaau);
    	buff.append(trenner);
    	buff.append(kzzasi);
    	buff.append(trenner);    	
    	buff.append(dstnr);
    	buff.append(trenner);
    	buff.append(dstnam);
    	buff.append(trenner);
    	buff.append(zbearb);
    	buff.append(trenner);
    	buff.append(dsttel);
    	buff.append(trenner);    	
    	buff.append(zzsnam);
    	buff.append(trenner);    	
    	buff.append(kzrebe);
    	buff.append(trenner);
    	buff.append(kzgein);
    	buff.append(trenner);
    	buff.append(rebhza);
    	buff.append(trenner);
    	buff.append(zzsiban);
    	buff.append(trenner);
    	buff.append(zzsbic);
    	buff.append(trenner);
    	buff.append(kzeust);
    	buff.append(trenner);
    	buff.append(anmeori);
    	buff.append(trenner);
    	buff.append(anmnl);
    	buff.append(trenner);
    	buff.append(verteori);
    	buff.append(trenner);
    	buff.append(vertnl);
    	buff.append(trenner);
    	buff.append(empeori);
    	buff.append(trenner);
    	buff.append(empnl);
    	buff.append(trenner);
    	buff.append(recheori);
    	buff.append(trenner);
    	buff.append(rechnl);
    	buff.append(trenner);

    	return new String(buff);

    }



    public boolean isEmpty() {
		return   Utils.isStringEmpty(antart) &&
	    	Utils.isStringEmpty(wkz) &&
	    	Utils.isStringEmpty(bardat) &&
	    	Utils.isStringEmpty(sicdat) &&
	    	Utils.isStringEmpty(abgdat) &&
	    	Utils.isStringEmpty(erfdat) &&
	    	Utils.isStringEmpty(abes) &&
	    	Utils.isStringEmpty(kzvtr) &&
	    	Utils.isStringEmpty(kzkbg) &&
	    	Utils.isStringEmpty(kzzaab) &&
	    	Utils.isStringEmpty(kzzaau) &&
	    	Utils.isStringEmpty(kzzasi) &&
	    	Utils.isStringEmpty(anmeori) &&
	    	Utils.isStringEmpty(dstnr) &&
	    	Utils.isStringEmpty(dstnam) &&
	    	Utils.isStringEmpty(zbearb) &&
	    	Utils.isStringEmpty(dsttel) &&
	    	Utils.isStringEmpty(verteori) &&
	    	Utils.isStringEmpty(zzsnam) &&
	    	Utils.isStringEmpty(empeori) &&
	    	Utils.isStringEmpty(kzrebe) &&
	    	Utils.isStringEmpty(kzgein) &&
	    	Utils.isStringEmpty(rebhza) &&
	    	Utils.isStringEmpty(zzsiban) &&
	    	Utils.isStringEmpty(zzsbic) &&
	    	Utils.isStringEmpty(kzeust);
    }
}
