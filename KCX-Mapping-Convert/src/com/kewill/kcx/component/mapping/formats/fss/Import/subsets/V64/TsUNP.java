package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsUNP
 * Erstellt             :       12.09.2011
 * Beschreibung 		:       Unterlagen im Positionsteil.

 								Es können insgesamt maximal 98 Unterlagen zur Position
 								übermittelt werden. Die Angaben werden sequentiell eingearbeitet
 								und intern mit einer laufenden Nummer versehen.

 *
 * @author                      Alfred Krzoska
 *
 */

public class TsUNP extends Teilsatz {

    private String beznr		 = "";	 // Bezugsnummer
    private String posnr		 = "";	 // Positionsnummer
    private String untber		 = "";	 // Bereich der Unterlage
    private String untart		 = "";	 // Art der Unterlage
    private String untnr		 = "";	 // Nr. der Unterlage
    private String untdat		 = "";	 // Datum der Unterlage
    private String kzvorl		 = "";	 // Kennzeichen Vorhanden
    private String menge		 = "";	 // Abschreibungsmenge/Abschreibungswert
    private String meinh		 = "";	 // Maßeinheit (Abschreibung)
    private String qmeinh		 = "";	 // Qualifikator Maßeinheit (Abschreibung)

    public TsUNP() {
	    tsTyp = "UNP";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    beznr = fields[1];

	    if (size < 3) { return; }
	    posnr = fields[2];

	    if (size < 4) { return; }
	    untber = fields[3];

	    if (size < 5) { return; }
	    untart = fields[4];

	    if (size < 6) { return; }
	    untnr = fields[5];

	    if (size < 7) { return; }
	    untdat = fields[6];

	    if (size < 8) { return; }
	    kzvorl = fields[7];

	    if (size < 9) { return; }
	    menge = fields[8];

	    if (size < 10) { return; }
	    meinh = fields[9];

	    if (size < 11) { return; }
	    qmeinh = fields[10];
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(untber);
    	buff.append(trenner);
    	buff.append(untart);
    	buff.append(trenner);
    	buff.append(untnr);
    	buff.append(trenner);
    	buff.append(untdat);
    	buff.append(trenner);
    	buff.append(kzvorl);
    	buff.append(trenner);
    	buff.append(menge);
    	buff.append(trenner);
    	buff.append(meinh);
    	buff.append(trenner);
    	buff.append(qmeinh);
    	buff.append(trenner);

    	return new String(buff);
    }

    public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getUntber() {
		return untber;
	}

	public void setUntber(String untber) {
		this.untber = Utils.checkNull(untber);
	}

	public String getUntart() {
		return untart;
	}

	public void setUntart(String untart) {
		this.untart = Utils.checkNull(untart);
	}

	public String getUntnr() {
		return untnr;
	}

	public void setUntnr(String untnr) {
		this.untnr = Utils.checkNull(untnr);
	}

	public String getUntdat() {
		return untdat;
	}

	public void setUntdat(String untdat) {
		this.untdat = Utils.checkNull(untdat);
	}

	public String getKzvorl() {
		return kzvorl;
	}

	public void setKzvorl(String kzvorl) {
		this.kzvorl = Utils.checkNull(kzvorl);
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

	public boolean isEmpty() {
      return   Utils.isStringEmpty(untber) &&
    	Utils.isStringEmpty(untart) &&
    	Utils.isStringEmpty(untnr) &&
    	Utils.isStringEmpty(untdat) &&
    	Utils.isStringEmpty(kzvorl) &&
    	Utils.isStringEmpty(menge) &&
    	Utils.isStringEmpty(meinh) &&
    	Utils.isStringEmpty(qmeinh);

    }

}
