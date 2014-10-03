package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsKON

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Teilsatz mit Kontingentangaben

 *
 * @author                      Alfred Krzoska
 *
 */

public class TsKON extends Teilsatz {

    private String konbnr		 = "";	 // Bezugsnummer
    private String konpnr		 = "";	 // Positionsnummer
    private String begmge		 = "";	 // Begünstigungsmenge nur ganzzahlige Werte erlaubt
    private String begmeh		 = "";	 // Begünstigungsmaßeinheit
    private String qbegme		 = "";	 // Qualifikator zur Begünstigungsmaßeinheit

    public TsKON() {
	    tsTyp = "KON";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    konbnr = fields[1];

	    if (size < 3) { return; }
	    konpnr = fields[2];

	    if (size < 4) { return; }
	    begmge = fields[3];

	    if (size < 5) { return; }
	    begmeh = fields[4];

	    if (size < 6) { return; }
	    qbegme = fields[5];
    }



    public String getKonbnr() {
    	 return konbnr;
    }


    public void setKonbnr(String konbnr) {
    	this.konbnr = Utils.checkNull(konbnr);
    }


    public String getKonpnr() {
    	 return konpnr;
    }


    public void setKonpnr(String konpnr) {
    	this.konpnr = Utils.checkNull(konpnr);
    }


    public String getBegmge() {
    	 return begmge;
    }


    public void setBegmge(String begmge) {
    	this.begmge = Utils.checkNull(begmge);
    }


    public String getBegmeh() {
    	 return begmeh;
    }


    public void setBegmeh(String begmeh) {
    	this.begmeh = Utils.checkNull(begmeh);
    }


    public String getQbegme() {
    	 return qbegme;
    }


    public void setQbegme(String qbegme) {
    	this.qbegme = Utils.checkNull(qbegme);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(konbnr);
    	buff.append(trenner);
    	buff.append(konpnr);
    	buff.append(trenner);
    	buff.append(begmge);
    	buff.append(trenner);
    	buff.append(begmeh);
    	buff.append(trenner);
    	buff.append(qbegme);
    	buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
	  return  Utils.isStringEmpty(begmge) &&
    	Utils.isStringEmpty(begmeh) &&
    	Utils.isStringEmpty(qbegme);
    }
}
