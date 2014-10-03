package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsGEH
 * Erstellt             :       13.09.2011
 * Beschreibung 		:       Gehaltsangaben.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsGEH extends Teilsatz {

    private String gehbnr		 = "";	 // Bezugsnummer
    private String gehpnr		 = "";	 // Positionsnummer
    private String ghart		 = "";	 // Gehaltsart
    private String ghprz		 = "";	 // Grad/Prozent der Gehaltsangaben

    public TsGEH() {
	    tsTyp = "GEH";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    gehbnr = fields[1];

	    if (size < 3) { return; }
	    gehpnr = fields[2];

	    if (size < 4) { return; }
	    ghart = fields[3];

	    if (size < 5) { return; }
	    ghprz = fields[4];
    }



    public String getGehbnr() {
    	 return gehbnr;
    }


    public void setGehbnr(String gehbnr) {
    	this.gehbnr = Utils.checkNull(gehbnr);
    }


    public String getGehpnr() {
    	 return gehpnr;
    }


    public void setGehpnr(String gehpnr) {
    	this.gehpnr = Utils.checkNull(gehpnr);
    }


    public String getGhart() {
    	 return ghart;
    }


    public void setGhart(String ghart) {
    	this.ghart = Utils.checkNull(ghart);
    }


    public String getGhprz() {
    	 return ghprz;
    }


    public void setGhprz(String ghprz) {
    	this.ghprz = Utils.checkNull(ghprz);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(gehbnr);
    	buff.append(trenner);
    	buff.append(gehpnr);
    	buff.append(trenner);
    	buff.append(ghart);
    	buff.append(trenner);
    	buff.append(ghprz);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
    	return 	Utils.isStringEmpty(gehpnr) &&
	    	Utils.isStringEmpty(ghart) &&
	    	Utils.isStringEmpty(ghprz);
    }

}
