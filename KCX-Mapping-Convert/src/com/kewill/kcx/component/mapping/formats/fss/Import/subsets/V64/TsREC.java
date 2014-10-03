package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsREC. 

 * Erstellt             :       22.09.2011
 * Beschreibung 		:       Kopfsatz der Rückmeldung CUSREC 
 				

 *
 * @author                      Alfred Krzoska
 * @version 1.0.00
 */

public class TsREC extends Teilsatz {

    private String beznr		 = "";	 // Bezugsnummer
    private String regnr		 = "";	 // Registriernummer (Bezugsvorgang)
    private String arbnr		 = "";	 // Arbeitsnummer
    private String regdat		 = "";	 // Datum der Registrierung

    public TsREC() {
	    tsTyp = "REC";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    beznr = fields[1];

	    if (size < 3) { return; }
	    regnr = fields[2];

	    if (size < 4) { return; }
	    arbnr = fields[3];

	    if (size < 5) { return; }
	    regdat = fields[4];
    }



    public String getBeznr() {
    	 return beznr;
    }


    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }


    public String getRegnr() {
    	 return regnr;
    }


    public void setRegnr(String regnr) {
    	this.regnr = Utils.checkNull(regnr);
    }


    public String getArbnr() {
    	 return arbnr;
    }


    public void setArbnr(String arbnr) {
    	this.arbnr = Utils.checkNull(arbnr);
    }


    public String getRegdat() {
    	 return regdat;
    }


    public void setRegdat(String regdat) {
    	this.regdat = Utils.checkNull(regdat);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(regnr);
    	buff.append(trenner);
    	buff.append(arbnr);
    	buff.append(trenner);
    	buff.append(regdat);
    	buff.append(trenner);

    	return new String(buff);
    
    }

    public boolean isEmpty() {
	return Utils.isStringEmpty(arbnr) &&
    	Utils.isStringEmpty(regdat);
    }

}
