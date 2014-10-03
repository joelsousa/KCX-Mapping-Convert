package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsMIN

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Minderungsangaben.
 								Es können maximal 3 Minderungsangaben  übermittelt  werden.

 *
 * @author                      Alfred Krzoska
 *
 */

public class TsMIN extends Teilsatz {

    private String minbnr		 = "";	 // Bezugsnummer
    private String minpnr		 = "";	 // Positionsnummer
    private String mabgr		 = "";	 // mindernde Abgabengruppe vgl. Deutsche Codeliste
    private String mprs		 	 = "";	 // Minderungsbetrag

    public TsMIN() {
	    tsTyp = "MIN";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    minbnr = fields[1];

	    if (size < 3) { return; }
	    minpnr = fields[2];

	    if (size < 4) { return; }
	    mabgr = fields[3];

	    if (size < 5) { return; }
	    mprs = fields[4];
    }



    public String getMinbnr() {
    	 return minbnr;
    }


    public void setMinbnr(String minbnr) {
    	this.minbnr = Utils.checkNull(minbnr);
    }


    public String getMinpnr() {
    	 return minpnr;
    }


    public void setMinpnr(String minpnr) {
    	this.minpnr = Utils.checkNull(minpnr);
    }


    public String getMabgr() {
    	 return mabgr;
    }


    public void setMabgr(String mabgr) {
    	this.mabgr = Utils.checkNull(mabgr);
    }


    public String getMprs() {
    	 return mprs;
    }


    public void setMprs(String mprs) {
    	this.mprs = Utils.checkNull(mprs);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(minbnr);
    	buff.append(trenner);
    	buff.append(minpnr);
    	buff.append(trenner);
    	buff.append(mabgr);
    	buff.append(trenner);
    	buff.append(mprs);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
      return Utils.isStringEmpty(mabgr) &&
    	Utils.isStringEmpty(mprs);
    }

}
