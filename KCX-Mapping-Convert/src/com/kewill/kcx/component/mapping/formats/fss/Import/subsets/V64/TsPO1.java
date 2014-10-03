package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsPO1
 * Erstellt             :       09.09.2011
 * Beschreibung 		:       1. Positionshauptsatz
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsPO1 extends Teilsatz {

    private String po1bnr		 = "";	 // Bezugsnummer
    private String po1pnr		 = "";	 // Positionsnummer
    private String wanr		 = "";	 // Warentarifnummer
    private String wazus1		 = "";	 // Tarifnummernzusatz 1
    private String wazus2		 = "";	 // Tarifnummernzusatz 2
    private String verf		 = "";	 // Verfahrenscode  vgl. Deutsche Codeliste; Stelle 3-4 kann auch 00 sein
    private String zuverf		 = "";	 // KZ Abgabensteuerung     vgl. Deutsche Codeliste
    private String wabes		 = "";	 // Warenbeschreibung
    private String txhiab		 = "";	 // Text zu Hinzurechnungen / Abzügen
    private String txzus		 = "";	 // Zusatztext zur Position
    private String artnr		 = "";	 // Artikelnummer
    private String artprs		 = "";	 // Artikelpreis    wie Rechnungspreis zur Position in Währung aus fzban2_waehr
    private String eucode		 = "";	 // EU-Code vgl. Deutsche Codeliste
    private String kostst		 = "";	 // Kostenstelle
    private String menge		 = "";	 // Positionsmenge
    private String meinh		 = "";	 // Maßeinheit Positionsmenge
    private String qmeinh		 = "";	 // Qualifikator zur Maßeinheit Positionsmenge
    private String kontnr		 = "";	 // Kontingentnummer
    private String kontnr2		 = "";	 // zweite Kontingentnummer

    public TsPO1() {
	    tsTyp = "PO1";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    po1bnr = fields[1];

	    if (size < 3) { return; }
	    po1pnr = fields[2];

	    if (size < 4) { return; }
	    wanr = fields[3];

	    if (size < 5) { return; }
	    wazus1 = fields[4];

	    if (size < 6) { return; }
	    wazus2 = fields[5];

	    if (size < 7) { return; }
	    verf = fields[6];

	    if (size < 8) { return; }
	    zuverf = fields[7];

	    if (size < 9) { return; }
	    wabes = fields[8];

	    if (size < 10) { return; }
	    txhiab = fields[9];

	    if (size < 11) { return; }
	    txzus = fields[10];

	    if (size < 12) { return; }
	    artnr = fields[11];

	    if (size < 13) { return; }
	    artprs = fields[12];

	    if (size < 14) { return; }
	    eucode = fields[13];

	    if (size < 15) { return; }
	    kostst = fields[14];

	    if (size < 16) { return; }
	    menge = fields[15];

	    if (size < 17) { return; }
	    meinh = fields[16];

	    if (size < 18) { return; }
	    qmeinh = fields[17];

	    if (size < 19) { return; }
	    kontnr = fields[18];

	    if (size < 20) { return; }
	    kontnr2 = fields[19];
    }



    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(po1bnr);
    	buff.append(trenner);
    	buff.append(po1pnr);
    	buff.append(trenner);
    	buff.append(wanr);
    	buff.append(trenner);
    	buff.append(wazus1);
    	buff.append(trenner);
    	buff.append(wazus2);
    	buff.append(trenner);
    	buff.append(verf);
    	buff.append(trenner);
    	buff.append(zuverf);
    	buff.append(trenner);
    	buff.append(wabes);
    	buff.append(trenner);
    	buff.append(txhiab);
    	buff.append(trenner);
    	buff.append(txzus);
    	buff.append(trenner);
    	buff.append(artnr);
    	buff.append(trenner);
    	buff.append(artprs);
    	buff.append(trenner);
    	buff.append(eucode);
    	buff.append(trenner);
    	buff.append(kostst);
    	buff.append(trenner);
    	buff.append(menge);
    	buff.append(trenner);
    	buff.append(meinh);
    	buff.append(trenner);
    	buff.append(qmeinh);
    	buff.append(trenner);
    	buff.append(kontnr);
    	buff.append(trenner);
    	buff.append(kontnr2);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public String getPo1bnr() {
		return po1bnr;
	}

	public void setPo1bnr(String po1bnr) {
		this.po1bnr = Utils.checkNull(po1bnr);
	}

	public String getPo1pnr() {
		return po1pnr;
	}

	public void setPo1pnr(String po1pnr) {
		this.po1pnr = Utils.checkNull(po1pnr);
	}

	public String getWanr() {
		return wanr;
	}

	public void setWanr(String wanr) {
		this.wanr = Utils.checkNull(wanr);
	}

	public String getWazus1() {
		return wazus1;
	}

	public void setWazus1(String wazus1) {
		this.wazus1 = Utils.checkNull(wazus1);
	}

	public String getWazus2() {
		return wazus2;
	}

	public void setWazus2(String wazus2) {
		this.wazus2 = Utils.checkNull(wazus2);
	}

	public String getVerf() {
		return verf;
	}

	public void setVerf(String verf) {
		this.verf = Utils.checkNull(verf);
	}

	public String getZuverf() {
		return zuverf;
	}

	public void setZuverf(String zuverf) {
		this.zuverf = Utils.checkNull(zuverf);
	}

	public String getWabes() {
		return wabes;
	}

	public void setWabes(String wabes) {
		this.wabes = Utils.checkNull(wabes);
	}

	public String getTxhiab() {
		return txhiab;
	}

	public void setTxhiab(String txhiab) {
		this.txhiab = Utils.checkNull(txhiab);
	}

	public String getTxzus() {
		return txzus;
	}

	public void setTxzus(String txzus) {
		this.txzus = Utils.checkNull(txzus);
	}

	public String getArtnr() {
		return artnr;
	}

	public void setArtnr(String artnr) {
		this.artnr = Utils.checkNull(artnr);
	}

	public String getArtprs() {
		return artprs;
	}

	public void setArtprs(String artprs) {
		this.artprs = Utils.checkNull(artprs);
	}

	public String getEucode() {
		return eucode;
	}

	public void setEucode(String eucode) {
		this.eucode = Utils.checkNull(eucode);
	}

	public String getKostst() {
		return kostst;
	}

	public void setKostst(String kostst) {
		this.kostst = Utils.checkNull(kostst);
	}

	public String getMenge() {
		return menge;
	}

	public void setMenge(String menge) {
		this.menge = Utils.checkNull(menge);
	}

	public String getMeinh() {
		return meinh;
	}

	public void setMeinh(String meinh) {
		this.meinh = Utils.checkNull(meinh);
	}

	public String getQmeinh() {
		return qmeinh;
	}

	public void setQmeinh(String qmeinh) {
		this.qmeinh = Utils.checkNull(qmeinh);
	}

	public String getKontnr() {
		return kontnr;
	}

	public void setKontnr(String kontnr) {
		this.kontnr = Utils.checkNull(kontnr);
	}

	public String getKontnr2() {
		return kontnr2;
	}

	public void setKontnr2(String kontnr2) {
		this.kontnr2 = Utils.checkNull(kontnr2);
	}

	public boolean isEmpty() {
	  return  Utils.isStringEmpty(wanr) &&
    	Utils.isStringEmpty(wazus1) &&
    	Utils.isStringEmpty(wazus2) &&
    	Utils.isStringEmpty(verf) &&
    	Utils.isStringEmpty(zuverf) &&
    	Utils.isStringEmpty(wabes) &&
    	Utils.isStringEmpty(txhiab) &&
    	Utils.isStringEmpty(txzus) &&
    	Utils.isStringEmpty(artnr) &&
    	Utils.isStringEmpty(artprs) &&
    	Utils.isStringEmpty(eucode) &&
    	Utils.isStringEmpty(kostst) &&
    	Utils.isStringEmpty(menge) &&
    	Utils.isStringEmpty(meinh) &&
    	Utils.isStringEmpty(qmeinh) &&
    	Utils.isStringEmpty(kontnr) &&
    	Utils.isStringEmpty(kontnr2);
    }

}
