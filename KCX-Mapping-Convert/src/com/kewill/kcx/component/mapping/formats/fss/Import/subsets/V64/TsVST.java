package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsVST
 * Erstellt             :       13.09.2011
 * Beschreibung 		:       Verbrauchssteuer.
 *								Es können insgesamt maximal 3 Verbrauchsteuern zur Position übermittelt werden.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsVST extends Teilsatz {

    private String vstbnr		 = "";	 // Bezugsnummer
    private String vstpnr		 = "";	 // Positionsnummer
    private String vbstc		 = "";	 // Verbrauchssteuercode
    private String menge		 = "";	 // Verbrauchssteuermenge
    private String meinh		 = "";	 // Maßeinheit zur Verbrauchssteuermenge
    private String qmeinh		 = "";	 // Qualifikator zur Maßeinheit
    private String grprz		 = "";	 // Grad/Prozent zur Verbrauchssteuer
    private String preis		 = "";	 // Verbrauchssteuerwert

    public TsVST() {
	    tsTyp = "VST";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    vstbnr = fields[1];

	    if (size < 3) { return; }
	    vstpnr = fields[2];

	    if (size < 4) { return; }
	    vbstc = fields[3];

	    if (size < 5) { return; }
	    menge = fields[4];

	    if (size < 6) { return; }
	    meinh = fields[5];

	    if (size < 7) { return; }
	    qmeinh = fields[6];

	    if (size < 8) { return; }
	    grprz = fields[7];

	    if (size < 9) { return; }
	    preis = fields[8];
    }



    public String getVstbnr() {
    	 return vstbnr;
    }


    public void setVstbnr(String vstbnr) {
    	this.vstbnr = Utils.checkNull(vstbnr);
    }


    public String getVstpnr() {
    	 return vstpnr;
    }


    public void setVstpnr(String vstpnr) {
    	this.vstpnr = Utils.checkNull(vstpnr);
    }


    public String getVbstc() {
    	 return vbstc;
    }


    public void setVbstc(String vbstc) {
    	this.vbstc = Utils.checkNull(vbstc);
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


    public String getGrprz() {
    	 return grprz;
    }


    public void setGrprz(String grprz) {
    	this.grprz = Utils.checkNull(grprz);
    }


    public String getPreis() {
    	 return preis;
    }


    public void setPreis(String preis) {
    	this.preis = Utils.checkNull(preis);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(vstbnr);
    	buff.append(trenner);
    	buff.append(vstpnr);
    	buff.append(trenner);
    	buff.append(vbstc);
    	buff.append(trenner);
    	buff.append(menge);
    	buff.append(trenner);
    	buff.append(meinh);
    	buff.append(trenner);
    	buff.append(qmeinh);
    	buff.append(trenner);
    	buff.append(grprz);
    	buff.append(trenner);
    	buff.append(preis);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
	    return Utils.isStringEmpty(vbstc) &&
	    	Utils.isStringEmpty(menge) &&
	    	Utils.isStringEmpty(meinh) &&
	    	Utils.isStringEmpty(qmeinh) &&
	    	Utils.isStringEmpty(grprz) &&
	    	Utils.isStringEmpty(preis);
    
    }
}