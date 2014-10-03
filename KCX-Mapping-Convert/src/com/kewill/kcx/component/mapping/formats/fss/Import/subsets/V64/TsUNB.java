package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsUNB

 * Erstellt             :       13.09.2011
 * Beschreibung 		:       Unbare Sicherheiten
 				
 				 
 				
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsUNB extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String arunb		 = "";	 // Art der unbaren Sicherheit
    private String beunb		 = "";	 // Betrag der unbaren Sicherheit

    public TsUNB() {
	    tsTyp = "UNB";
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
	    arunb = fields[3];

	    if (size < 5) { return; }
	    beunb = fields[4];
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


    public String getArunb() {
    	 return arunb;
    }


    public void setArunb(String arunb) {
    	this.arunb = Utils.checkNull(arunb);
    }


    public String getBeunb() {
    	 return beunb;
    }


    public void setBeunb(String beunb) {
    	this.beunb = Utils.checkNull(beunb);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(arunb);
    	buff.append(trenner);
    	buff.append(beunb);
    	buff.append(trenner);

    	return new String(buff);
    
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(regkz) &&
    	Utils.isStringEmpty(lfdnr) &&
    	Utils.isStringEmpty(arunb) &&
    	Utils.isStringEmpty(beunb);
    }
}
