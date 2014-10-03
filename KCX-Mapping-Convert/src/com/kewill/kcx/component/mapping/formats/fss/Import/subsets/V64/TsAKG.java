package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsAKG
 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Kontingente
								Die Kontingente können je Typ maximal 2 mal vorkommen.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsAKG extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String akgtyp		 = "";	 // Typ1 = angewandte Kontingente2 = angerechnete Kontingente
    private String kontnr		 = "";	 // Kontingentnummer
    private String zollw		 = "";	 // angerechneter Zollwert
    private String eust		 	 = "";	 // angerechnete Kosten für EUSt
    private String mgebeg		 = "";	 // angerechnete Begünstigungsmenge
    private String mebeg		 = "";	 // Maßeinheit zur angerechneten Begünstigungsmenge
    private String qmebeg		 = "";	 // Qualifikator zur Maßeinheit zur angerechneten Begünstigungsmenge
    private String wmzoll		 = "";	 // angerechnete Warenmenge Zoll
    private String mezoll		 = "";	 // Maßeinheit zur angerechneten Warenmenge Zoll
    private String qmezol		 = "";	 // Qualifikator zur Maßeinheit zur angerechneten Warenmenge Zoll
    private String wmazol		 = "";	 // angerechnete Warenmenge Agrarzoll
    private String meazol		 = "";	 // Maßeinheit zur angerechneten Warenmenge Agrarzoll
    private String qmeazol		 = "";	 // Qualifikator zur Maßeinheit zur angerechneten Warenmenge Agrarzoll

    public TsAKG() {
	    tsTyp = "AKG";
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
	    akgtyp = fields[4];

	    if (size < 6) { return; }
	    kontnr = fields[5];

	    if (size < 7) { return; }
	    zollw = fields[6];

	    if (size < 8) { return; }
	    eust = fields[7];

	    if (size < 9) { return; }
	    mgebeg = fields[8];

	    if (size < 10) { return; }
	    mebeg = fields[9];

	    if (size < 11) { return; }
	    qmebeg = fields[10];

	    if (size < 12) { return; }
	    wmzoll = fields[11];

	    if (size < 13) { return; }
	    mezoll = fields[12];

	    if (size < 14) { return; }
	    qmezol = fields[13];

	    if (size < 15) { return; }
	    wmazol = fields[14];

	    if (size < 16) { return; }
	    meazol = fields[15];

	    if (size < 17) { return; }
	    qmeazol = fields[16];
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


    public String getAkgtyp() {
    	 return akgtyp;
    }


    public void setAkgtyp(String akgtyp) {
    	this.akgtyp = Utils.checkNull(akgtyp);
    }


    public String getKontnr() {
    	 return kontnr;
    }


    public void setKontnr(String kontnr) {
    	this.kontnr = Utils.checkNull(kontnr);
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


    public String getMgebeg() {
    	 return mgebeg;
    }


    public void setMgebeg(String mgebeg) {
    	this.mgebeg = Utils.checkNull(mgebeg);
    }


    public String getMebeg() {
    	 return mebeg;
    }


    public void setMebeg(String mebeg) {
    	this.mebeg = Utils.checkNull(mebeg);
    }


    public String getQmebeg() {
    	 return qmebeg;
    }


    public void setQmebeg(String qmebeg) {
    	this.qmebeg = Utils.checkNull(qmebeg);
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


    public String getQmeazol() {
    	 return qmeazol;
    }


    public void setQmeazol(String qmeazol) {
    	this.qmeazol = Utils.checkNull(qmeazol);
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
    	buff.append(akgtyp);
    	buff.append(trenner);
    	buff.append(kontnr);
    	buff.append(trenner);
    	buff.append(zollw);
    	buff.append(trenner);
    	buff.append(eust);
    	buff.append(trenner);
    	buff.append(mgebeg);
    	buff.append(trenner);
    	buff.append(mebeg);
    	buff.append(trenner);
    	buff.append(qmebeg);
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
    	buff.append(qmeazol);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
	  return  Utils.isStringEmpty(akgtyp) &&
		  Utils.isStringEmpty(kontnr) &&
		  Utils.isStringEmpty(zollw) &&
		  Utils.isStringEmpty(eust) &&
		  Utils.isStringEmpty(mgebeg) &&
		  Utils.isStringEmpty(mebeg) &&
		  Utils.isStringEmpty(qmebeg) &&
		  Utils.isStringEmpty(wmzoll) &&
		  Utils.isStringEmpty(mezoll) &&
		  Utils.isStringEmpty(qmezol) &&
		  Utils.isStringEmpty(wmazol) &&
		  Utils.isStringEmpty(meazol) &&
		  Utils.isStringEmpty(qmeazol);
    }

}

