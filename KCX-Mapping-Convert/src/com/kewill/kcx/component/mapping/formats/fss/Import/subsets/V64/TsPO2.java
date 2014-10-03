package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsPO2
 * Erstellt             :       12.09.2011
 * Beschreibung 		:       2. Positionshauptsatz
 *
 * @author                      Alfred Krzoska
 *
 */
 
public class TsPO2 extends Teilsatz {

    private String po2bnr		 = "";	 // Bezugsnummer
    private String po2pnr		 = "";	 // Positionsnummer
    private String vrbort		 = "";	 // Ort des Verbringens
    private String abgort		 = "";	 // Abgangsort (Luftfracht)
    private String puland		 = "";	 // Ursprungsland der Position
    private String eigm		 	 = "";	 // Eigenmasse	
    private String prohm		 = "";	 // Rohmasse der Position
    private String stkanz		 = "";	 // Anzahl der Packstücke
    private String stkart		 = "";	 // Art der Packstücke ab Version 4 werden 
    									 // hier die alphanumerischen Werte wie in SumA erwartet
    private String stkzei		 = "";	 // Packstücke Zeichen und Nummern
    private String beabeg		 = "";	 // beantragte Begünstigung	vgl. Deutsche Codeliste
    private String beding		 = "";	 // Bedingung D=zur Verarbeitung bestimmt
    private String zollw		 = "";	 // Zollwert der Position
    private String eust		 	 = "";	 // Kosten für EUSt der Position
    private String rpreis		 = "";	 // Rechnungspreis der Position
    private String rabatt		 = "";	 // Ab-/Zuschlag zum Rechnungspreis
    private String skonto		 = "";	 // Skonto zum Rechnungspreis
    private String waenet		 = "";	 // Währung zum Nettopreis
    private String knet		 	 = "";	 // Kurs zum Nettopreis
    private String kznet		 = "";	 // KZ Kurs netto vereinbart zum Nettopreis
    //AK111027
    //private String netto		 = "";	 // Nettopreis
    private String mzahl		 = "";	 // mittelbare Zahlung
    private String waemz		 = "";	 // Währung zu mittelbare Zahlung
    private String kmzahl		 = "";	 // Kurs zu mittelbare Zahlung
    private String kzzahl		 = "";	 // KZ Kurs mittelbare Zahlung
    private String wmahst		 = "";	 // AH statistische Menge
    private String meast		 = "";	 // Maßeinheit zur AH statistischen Menge
    private String qmeast		 = "";	 // Qualifikator zur AH-Stat. Maßeinheit
    private String ahwert		 = "";	 // AH statistischer Wert
    private String wmzoll		 = "";	 // Warenmenge Zoll
    private String mezoll		 = "";	 // Maßeinheit zur Warenmenge Zoll
    private String qmezol		 = "";	 // Qualifikator zur Maßeinheit Warenmenge Zoll
    private String wmazol		 = "";	 // Warenmenge Agrarzoll
    private String meazol		 = "";	 // Maßeinheit zur Warenmenge Agrarzoll
    private String qmeazo		 = "";	 // Qualifikator zur Maßeinheit Warenmenge Agrarzoll
    private String vered		 = "";	 // Veredelungsentgelt / Wertsteigerung nach PV
    private String zuskeu		 = "";	 // zusätzliche Kosten für EUSt
    private String tabak		 = "";	 // Tabaksteuerzeichennummer

    public TsPO2() {
	    tsTyp = "PO2";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    po2bnr = fields[1];

	    if (size < 3) { return; }
	    po2pnr = fields[2];

	    if (size < 4) { return; }
	    vrbort = fields[3];

	    if (size < 5) { return; }
	    abgort = fields[4];

	    if (size < 6) { return; }
	    puland = fields[5];

	    if (size < 7) { return; }
	    eigm = fields[6];

	    if (size < 8) { return; }
	    prohm = fields[7];

	    if (size < 9) { return; }
	    stkanz = fields[8];

	    if (size < 10) { return; }
	    stkart = fields[9];

	    if (size < 11) { return; }
	    stkzei = fields[10];

	    if (size < 12) { return; }
	    beabeg = fields[11];

	    if (size < 13) { return; }
	    beding = fields[12];

	    if (size < 14) { return; }
	    zollw = fields[13];

	    if (size < 15) { return; }
	    eust = fields[14];

	    if (size < 16) { return; }
	    rpreis = fields[15];

	    if (size < 17) { return; }
	    rabatt = fields[16];

	    if (size < 18) { return; }
	    skonto = fields[17];

	    if (size < 19) { return; }
	    waenet = fields[18];

	    if (size < 20) { return; }
	    knet = fields[19];

	    if (size < 21) { return; }
	    kznet = fields[20];
	    
	    //AK111027
//	    if (size < 22) { return; }
//	    netto = fields[21];

	    if (size < 22) { return; }
	    mzahl = fields[21];

	    if (size < 23) { return; }
	    waemz = fields[22];

	    if (size < 24) { return; }
	    kmzahl = fields[23];

	    if (size < 25) { return; }
	    kzzahl = fields[24];

	    if (size < 26) { return; }
	    wmahst = fields[25];

	    if (size < 27) { return; }
	    meast = fields[26];

	    if (size < 28) { return; }
	    qmeast = fields[27];

	    if (size < 29) { return; }
	    ahwert = fields[28];

	    if (size < 30) { return; }
	    wmzoll = fields[29];

	    if (size < 31) { return; }
	    mezoll = fields[30];

	    if (size < 32) { return; }
	    qmezol = fields[31];

	    if (size < 33) { return; }
	    wmazol = fields[32];

	    if (size < 34) { return; }
	    meazol = fields[33];

	    if (size < 35) { return; }
	    qmeazo = fields[34];

	    if (size < 36) { return; }
	    vered = fields[35];

	    if (size < 37) { return; }
	    zuskeu = fields[36];

	    if (size < 38) { return; }
	    tabak = fields[37];
    }



    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(po2bnr);
    	buff.append(trenner);
    	buff.append(po2pnr);
    	buff.append(trenner);
    	buff.append(vrbort);
    	buff.append(trenner);
    	buff.append(abgort);
    	buff.append(trenner);
    	buff.append(puland);
    	buff.append(trenner);
    	buff.append(eigm);
    	buff.append(trenner);
    	buff.append(prohm);
    	buff.append(trenner);
    	buff.append(stkanz);
    	buff.append(trenner);
    	buff.append(stkart);
    	buff.append(trenner);
    	buff.append(stkzei);
    	buff.append(trenner);
    	buff.append(beabeg);
    	buff.append(trenner);
    	buff.append(beding);
    	buff.append(trenner);
    	buff.append(zollw);
    	buff.append(trenner);
    	buff.append(eust);
    	buff.append(trenner);
    	buff.append(rpreis);
    	buff.append(trenner);
    	buff.append(rabatt);
    	buff.append(trenner);
    	buff.append(skonto);
    	buff.append(trenner);
    	buff.append(waenet);
    	buff.append(trenner);
    	buff.append(knet);
    	buff.append(trenner);
    	buff.append(kznet);
    	buff.append(trenner);
    	//AK111027
    	//buff.append(netto);
    	//buff.append(trenner);
    	buff.append(mzahl);
    	buff.append(trenner);
    	buff.append(waemz);
    	buff.append(trenner);
    	buff.append(kmzahl);
    	buff.append(trenner);
    	buff.append(kzzahl);
    	buff.append(trenner);
    	buff.append(wmahst);
    	buff.append(trenner);
    	buff.append(meast);
    	buff.append(trenner);
    	buff.append(qmeast);
    	buff.append(trenner);
    	buff.append(ahwert);
    	buff.append(trenner);
    	buff.append(wmzoll);
    	buff.append(trenner);
    	buff.append(mezoll);
    	buff.append(trenner);
    	buff.append(qmezol);
    	buff.append(trenner);
    	buff.append(wmazol);
    	buff.append(trenner);
    	buff.append(meazol);
    	buff.append(trenner);
    	buff.append(qmeazo);
    	buff.append(trenner);
    	buff.append(vered);
    	buff.append(trenner);
    	buff.append(zuskeu);
    	buff.append(trenner);
    	buff.append(tabak);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public String getPo2bnr() {
		return po2bnr;
	}

	public void setPo2bnr(String po2bnr) {
		this.po2bnr = Utils.checkNull(po2bnr);
	}

	public String getPo2pnr() {
		return po2pnr;
	}

	public void setPo2pnr(String po2pnr) {
		this.po2pnr = Utils.checkNull(po2pnr);
	}

	public String getVrbort() {
		return vrbort;
	}

	public void setVrbort(String vrbort) {
		this.vrbort = Utils.checkNull(vrbort);
	}

	public String getAbgort() {
		return abgort;
	}

	public void setAbgort(String abgort) {
		this.abgort = Utils.checkNull(abgort);
	}

	public String getPuland() {
		return puland;
	}

	public void setPuland(String puland) {
		this.puland = Utils.checkNull(puland);
	}

	public String getEigm() {
		return eigm;
	}

	public void setEigm(String eigm) {
		this.eigm = Utils.checkNull(eigm);
	}

	public String getProhm() {
		return prohm;
	}

	public void setProhm(String prohm) {
		this.prohm = Utils.checkNull(prohm);
	}

	public String getStkanz() {
		return stkanz;
	}

	public void setStkanz(String stkanz) {
		this.stkanz = Utils.checkNull(stkanz);
	}

	public String getStkart() {
		return stkart;
	}

	public void setStkart(String stkart) {
		this.stkart = Utils.checkNull(stkart);
	}

	public String getStkzei() {
		return stkzei;
	}

	public void setStkzei(String stkzei) {
		this.stkzei = Utils.checkNull(stkzei);
	}

	public String getBeabeg() {
		return beabeg;
	}

	public void setBeabeg(String beabeg) {
		this.beabeg = Utils.checkNull(beabeg);
	}

	public String getBeding() {
		return beding;
	}

	public void setBeding(String beding) {
		this.beding = Utils.checkNull(beding);
	}

	public String getZollw() {
		return zollw;
	}

	public void setZollw(String zollw) {
		this.zollw = Utils.checkNull(zollw);
	}

	public String getEust() {
		return eust;
	}

	public void setEust(String eust) {
		this.eust = Utils.checkNull(eust);
	}

	public String getRpreis() {
		return rpreis;
	}

	public void setRpreis(String rpreis) {
		this.rpreis = Utils.checkNull(rpreis);
	}

	public String getRabatt() {
		return rabatt;
	}

	public void setRabatt(String rabatt) {
		this.rabatt = Utils.checkNull(rabatt);
	}

	public String getSkonto() {
		return skonto;
	}

	public void setSkonto(String skonto) {
		this.skonto = Utils.checkNull(skonto);
	}

	public String getWaenet() {
		return waenet;
	}

	public void setWaenet(String waenet) {
		this.waenet = Utils.checkNull(waenet);
	}

	public String getKnet() {
		return knet;
	}

	public void setKnet(String knet) {
		this.knet = Utils.checkNull(knet);
	}

	public String getKznet() {
		return kznet;
	}

	public void setKznet(String kznet) {
		this.kznet = Utils.checkNull(kznet);
	}
    //AK111027
	//public String getNetto() {
	//	return netto;
	//}

	//public void setNetto(String netto) {
	//	this.netto = Utils.checkNull(netto);
	//}

	public String getMzahl() {
		return mzahl;
	}

	public void setMzahl(String mzahl) {
		this.mzahl = Utils.checkNull(mzahl);
	}

	public String getWaemz() {
		return waemz;
	}

	public void setWaemz(String waemz) {
		this.waemz = Utils.checkNull(waemz);
	}

	public String getKmzahl() {
		return kmzahl;
	}

	public void setKmzahl(String kmzahl) {
		this.kmzahl = Utils.checkNull(kmzahl);
	}

	public String getKzzahl() {
		return kzzahl;
	}

	public void setKzzahl(String kzzahl) {
		this.kzzahl = Utils.checkNull(kzzahl);
	}

	public String getWmahst() {
		return wmahst;
	}

	public void setWmahst(String wmahst) {
		this.wmahst = Utils.checkNull(wmahst);
	}

	public String getMeast() {
		return meast;
	}

	public void setMeast(String meast) {
		this.meast = Utils.checkNull(meast);
	}

	public String getQmeast() {
		return qmeast;
	}

	public void setQmeast(String qmeast) {
		this.qmeast = Utils.checkNull(qmeast);
	}

	public String getAhwert() {
		return ahwert;
	}

	public void setAhwert(String ahwert) {
		this.ahwert = Utils.checkNull(ahwert);
	}

	public String getWmzoll() {
		return wmzoll;
	}

	public void setWmzoll(String wmzoll) {
		this.wmzoll = Utils.checkNull(wmzoll);
	}

	public String getMezoll() {
		return mezoll;
	}

	public void setMezoll(String mezoll) {
		this.mezoll = Utils.checkNull(mezoll);
	}

	public String getQmezol() {
		return qmezol;
	}

	public void setQmezol(String qmezol) {
		this.qmezol = Utils.checkNull(qmezol);
	}

	public String getWmazol() {
		return wmazol;
	}

	public void setWmazol(String wmazol) {
		this.wmazol = Utils.checkNull(wmazol);
	}

	public String getMeazol() {
		return meazol;
	}

	public void setMeazol(String meazol) {
		this.meazol = Utils.checkNull(meazol);
	}

	public String getQmeazo() {
		return qmeazo;
	}

	public void setQmeazo(String qmeazo) {
		this.qmeazo = Utils.checkNull(qmeazo);
	}

	public String getVered() {
		return vered;
	}

	public void setVered(String vered) {
		this.vered = Utils.checkNull(vered);
	}

	public String getZuskeu() {
		return zuskeu;
	}

	public void setZuskeu(String zuskeu) {
		this.zuskeu = Utils.checkNull(zuskeu);
	}

	public String getTabak() {
		return tabak;
	}

	public void setTabak(String tabak) {
		this.tabak = Utils.checkNull(tabak);
	}

	public boolean isEmpty() {
		return  Utils.isStringEmpty(vrbort) &&
	    	Utils.isStringEmpty(abgort) &&
	    	Utils.isStringEmpty(puland) &&
	    	Utils.isStringEmpty(eigm) &&
	    	Utils.isStringEmpty(prohm) &&
	    	Utils.isStringEmpty(stkanz) &&
	    	Utils.isStringEmpty(stkart) &&
	    	Utils.isStringEmpty(stkzei) &&
	    	Utils.isStringEmpty(beabeg) &&
	    	Utils.isStringEmpty(beding) &&
	    	Utils.isStringEmpty(zollw) &&
	    	Utils.isStringEmpty(eust) &&
	    	Utils.isStringEmpty(rpreis) &&
	    	Utils.isStringEmpty(rabatt) &&
	    	Utils.isStringEmpty(skonto) &&
	    	Utils.isStringEmpty(waenet) &&
	    	Utils.isStringEmpty(knet) &&
	    	Utils.isStringEmpty(kznet) &&
	    	Utils.isStringEmpty(mzahl) &&
	    	Utils.isStringEmpty(waemz) &&
	    	Utils.isStringEmpty(kmzahl) &&
	    	Utils.isStringEmpty(kzzahl) &&
	    	Utils.isStringEmpty(wmahst) &&
	    	Utils.isStringEmpty(meast) &&
	    	Utils.isStringEmpty(qmeast) &&
	    	Utils.isStringEmpty(ahwert) &&
	    	Utils.isStringEmpty(wmzoll) &&
	    	Utils.isStringEmpty(mezoll) &&
	    	Utils.isStringEmpty(qmezol) &&
	    	Utils.isStringEmpty(wmazol) &&
	    	Utils.isStringEmpty(meazol) &&
	    	Utils.isStringEmpty(qmeazo) &&
	    	Utils.isStringEmpty(vered) &&
	    	Utils.isStringEmpty(zuskeu) &&
	    	Utils.isStringEmpty(tabak);
    }

}
