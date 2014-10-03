package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsPRN
 * Erstellt             :       12.09.2011
 * Beschreibung 		:       Angaben zur Präferenz
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsPRN extends Teilsatz {

    private String prfbnr		 = "";	 // Bezugsnummer
    private String prfpnr		 = "";	 // Positionsnummer
    private String prnw		 	 = "";	 // Präferenznachweis	vgl. Deutsche Codeliste
    private String prnwnr		 = "";	 // Präferenznachweisnummer
    private String prndat		 = "";	 // Datum 	JJJJMMTT
    private String prnkzv		 = "";	 // Kennzeichen Vorhanden

    public TsPRN() {
	    tsTyp = "PRN";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    prfbnr = fields[1];

	    if (size < 3) { return; }
	    prfpnr = fields[2];

	    if (size < 4) { return; }
	    prnw = fields[3];

	    if (size < 5) { return; }
	    prnwnr = fields[4];

	    if (size < 6) { return; }
	    prndat = fields[5];

	    if (size < 7) { return; }
	    prnkzv = fields[6];
    }



    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(prfbnr);
    	buff.append(trenner);
    	buff.append(prfpnr);
    	buff.append(trenner);
    	buff.append(prnw);
    	buff.append(trenner);
    	buff.append(prnwnr);
    	buff.append(trenner);
    	buff.append(prndat);
    	buff.append(trenner);
    	buff.append(prnkzv);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public String getPrfbnr() {
		return prfbnr;
	}

	public void setPrfbnr(String prfbnr) {
		this.prfbnr = Utils.checkNull(prfbnr);
	}

	public String getPrfpnr() {
		return prfpnr;
	}

	public void setPrfpnr(String prfpnr) {
		this.prfpnr = Utils.checkNull(prfpnr);
	}

	public String getPrnw() {
		return prnw;
	}

	public void setPrnw(String prnw) {
		this.prnw = Utils.checkNull(prnw);
	}

	public String getPrnwnr() {
		return prnwnr;
	}

	public void setPrnwnr(String prnwnr) {
		this.prnwnr = Utils.checkNull(prnwnr);
	}

	public String getPrndat() {
		return prndat;
	}

	public void setPrndat(String prndat) {
		this.prndat = Utils.checkNull(prndat);
	}

	public String getPrnkzv() {
		return prnkzv;
	}

	public void setPrnkzv(String prnkzv) {
		this.prnkzv = Utils.checkNull(prnkzv);
	}

	public boolean isEmpty() {
	return   Utils.isStringEmpty(prnw) &&
    	Utils.isStringEmpty(prnwnr) &&
    	Utils.isStringEmpty(prndat) &&
    	Utils.isStringEmpty(prnkzv);

    }

}
