package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsCRL

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Kopfteilsatz zur Nachricht CURREL
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsCRL extends Teilsatz {

    private String lfdnr		 = "";	 // laufende Nummer
    private String korant		 = "";	 // Korrekturnummer
    private String arbnr		 = "";	 // Arbeitsnummer
    private String regnr		 = "";	 // Registriernummer
    private String beznr		 = "";	 // Bezugsnummer
    private String zollsb		 = "";	 // Name des Bearbeiters beim Zoll

    public TsCRL() {
	    tsTyp = "CRL";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    lfdnr = fields[1];

	    if (size < 3) { return; }
	    korant = fields[2];

	    if (size < 4) { return; }
	    arbnr = fields[3];

	    if (size < 5) { return; }
	    regnr = fields[4];

	    if (size < 6) { return; }
	    beznr = fields[5];

	    if (size < 7) { return; }
	    zollsb = fields[6];
    }



    public String getLfdnr() {
    	 return lfdnr;
    }


    public void setLfdnr(String lfdnr) {
    	this.lfdnr = Utils.checkNull(lfdnr);
    }


    public String getKorant() {
    	 return korant;
    }


    public void setKorant(String korant) {
    	this.korant = Utils.checkNull(korant);
    }


    public String getArbnr() {
    	 return arbnr;
    }


    public void setArbnr(String arbnr) {
    	this.arbnr = Utils.checkNull(arbnr);
    }


    public String getRegnr() {
    	 return regnr;
    }


    public void setRegnr(String regnr) {
    	this.regnr = Utils.checkNull(regnr);
    }


    public String getBeznr() {
    	 return beznr;
    }


    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }


    public String getZollsb() {
    	 return zollsb;
    }


    public void setZollsb(String zollsb) {
    	this.zollsb = Utils.checkNull(zollsb);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(korant);
    	buff.append(trenner);
    	buff.append(arbnr);
    	buff.append(trenner);
    	buff.append(regnr);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(zollsb);
    	buff.append(trenner);

    	return new String(buff);
    
    }


    public boolean isEmpty() {
	  return    Utils.isStringEmpty(arbnr) &&
    	Utils.isStringEmpty(regnr) &&
    	Utils.isStringEmpty(beznr) &&
    	Utils.isStringEmpty(zollsb);
    }

}
